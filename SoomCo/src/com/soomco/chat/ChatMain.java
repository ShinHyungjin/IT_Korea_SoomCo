package com.soomco.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.soomco.Esimate.EstimateForm;
import com.soomco.main.SoomcoMain;
import com.soomco.member.SoomcoMember;

public class ChatMain extends JFrame {
	JPanel p_north;
	JPanel p_center;
	JScrollPane scroll;
	JPanel p_south;
	JButton bt_send, bt_match, bt_out;
	JTextField t_text;
	JTextArea area;
	Font customFont;
	JLabel la_send, la_match, la_out;
	
	public static String clickStatus; // 채팅창 띄운게 "견적제출"인지 "쪽지함" 오픈인지
	public static SoomcoMain soomcoMain;
	private static EstimateForm estimateForm;//ChatVO getter setter 사용하기 위해 선언
	
	SoomcoMember soomcoMember;//로그인 유무, 포지션 유무 확인 위해 선언
	StringBuilder sb = new StringBuilder(); //쪽지내용 담는 StringBuilder
	
	ChatMainVO chatMainVO; //견적서 제출시 채팅방오픈인지 확인 위함
	String c_tid;//견적서 제출시 상대(고수)ID 저장 후, 상태 업데이트 하기위해 필요, 처음 1번만 사용함		
	String c_content;//견적서 제출시 저장한 견적서 내용		
	String User_To_gosu;//t_id는 고수ID이며 초보가 전송할 대상ID
	String Gosu_To_user;//m_id는 초보ID이며 고수가 전송할 대상ID
	public ChatMain(EstimateForm estimateForm, SoomcoMain soomcoMain, String clickStatus) {
		this.soomcoMain = soomcoMain;//soomcoMain 얻어오기 (견적서로부터)
		this.clickStatus=clickStatus;//쪽지창 오픈 형식이 견적서 제출인지 or 메인에서 쪽지함 open 인지
		soomcoMember = soomcoMain.getSoomcoMember();//login시 저장되는 정보들이 들어있는 soomcoMember
		this.estimateForm=estimateForm;//견적서 넘겨 받음
		
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		t_text = new JTextField(15);
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_send = new JButton("");
		bt_match = new JButton("");
		bt_out = new JButton("");		
		
		la_send=new JLabel("전송");
		la_match=new JLabel("매칭하기");
		la_out=new JLabel("대화방나가기");
			

		// 폰트적용
		try {
			// create the font to use. Specify the size!
			customFont = Font
					.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf"))
					.deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
				
		//전송버튼에 붙일 라벨
		la_send.setForeground(Color.BLACK);
		la_send.setFont(customFont);		
		la_match.setForeground(Color.BLACK);
		la_match.setFont(customFont);
		la_out.setForeground(Color.BLACK);
		la_out.setFont(customFont);
		bt_send.add(la_send);
		bt_match.add(la_match);
		bt_out.add(la_out);
		
		
		//폰트적용
		t_text.setFont(customFont);
		area.setFont(customFont);					

		// 스타일
		scroll.setPreferredSize(new Dimension(350, 450));
		scroll.setBorder(BorderFactory.createTitledBorder("SoomCo"));
		area.setBackground(null);
		area.setBackground(Color.WHITE);

		bt_match.setPreferredSize(new Dimension(90, 50));
		bt_match.setBackground(new Color(253, 116, 31));
		la_match.setForeground(Color.white);
		bt_out.setPreferredSize(new Dimension(110, 50));
		bt_out.setBackground(new Color(253, 116, 31));
		la_out.setForeground(Color.white);
		bt_send.setBackground(new Color(253, 116, 31));
		la_send.setForeground(Color.white);		
		p_north.add(bt_match);
		p_north.add(bt_out);
		add(p_north, BorderLayout.NORTH);
		
		//채팅창 오픈 
		if(clickStatus.equals("쪽지함")) {			
			getChatMessage(); //쪽지함 오픈일때  누적 메시지 로드			
			area.append(sb.toString());//TextArea에 견적서 내용 append
			
		}else if(clickStatus.equals("견적제출")){
			getEstimateMessage();//견적서 제출일때  새로 쪽지함 오픈
			area.append(sb.toString());//TextArea에 견적서 내용 append
			updateStatus("false", c_tid);//견적제출이면 본인과 상대방 상태를 false로 바꿈

		}
		
		
		p_center.add(scroll);
		add(p_center, BorderLayout.CENTER);

		p_south.add(t_text);
		p_south.add(bt_send);

		
		t_text.setBorder(BorderFactory.createTitledBorder("SoomCo"));
		t_text.setBackground(null);
		
		add(p_south, BorderLayout.SOUTH);
		
		
		//매칭하기 버튼시 매칭카운트+1, m_deadLine 업데이트
		bt_match.addActionListener((e)-> {
			if(getCurrentDate().compareTo(soomcoMember.getDeadline()) < 0 ) {//현재날짜가 마감일자보다 낮으면 매칭완료된 상태이므로 나가지(대화삭제) 못함. --> 환불 불가능
				JOptionPane.showMessageDialog(this, "이미 매칭이 완료되었습니다.");
			}else if(soomcoMember.getStatus().equals("true")) {
				JOptionPane.showMessageDialog(this, "채팅중인 상대가 없습니다.");
			}else {
				getChat_tid();//상대방 아이디 User_To_gosu에 저장
				ConnectMatching();//자신과 상대방 matchingcount+1 , deadLine설정, matching을 true로 설정
				JOptionPane.showMessageDialog(this, getCurrentDate()+"일부터 "+getDeadLineDate()+"일까지 고용이 시작되었습니다.");
			}
		});
		
		//대화방 나가기( 초보,고수 상태 true로 변경 && 채팅 메시지 삭제
		bt_out.addActionListener((e) -> {
			if(soomcoMember.getStatus().equals("true")) {//현재날짜가 마감일자보다 낮으면 매칭완료된 상태이므로 나가지(대화삭제) 못함. --> 환불 불가능
				JOptionPane.showMessageDialog(this, "채팅중인 상대가 없습니다.");				
			}else if(getCurrentDate().compareTo(soomcoMember.getDeadline()) < 0 ) {
				JOptionPane.showMessageDialog(this, "매칭기간이 만료되지 않았습니다.");
			}
			else {			
				getChat_tid(); //자신과 연결된 상대방ID 얻기			
				updateStatus("true", User_To_gosu);//연결된 두 유저의 상태를 true로 변경(다른사람과 매칭, 견적제출 가능한 상태로),고수인지 초보인지에 따라 다름							
				updateStatus("true", Gosu_To_user);//연결된 두 유저의 상태를 true로 변경(다른사람과 매칭, 견적제출 가능한 상태로),고수인지 초보인지에 따라 다름							
				deleteMessage(User_To_gosu);//고수 채팅 메시지 전부 삭제
				deleteMessage(Gosu_To_user);//초보 채팅 메시지 전부 삭제
				dispose();
				soomcoMain.showPage(SoomcoMain.HOME);			
			}
		});
		
		//메시지 전송 버튼과 액션 리스너 연결
		bt_send.addActionListener((e)->{			
			if(t_text.getText().equals("")) {
        		JOptionPane.showMessageDialog(ChatMain.this, "메시지를 입력하세요.");
        	}else if(soomcoMember.getStatus().equals("true")){//대화방나가기 한 뒤, 아무것도 없는 채팅방에 메시지 작성시 연결요청 메시지
        		JOptionPane.showMessageDialog(ChatMain.this, "초보와 고수간 연결을 해주세요.");
        	} else {
        		getChat_tid();//메시지 전송 전, 보낼 대상 정하기	            	
        		sendMessage();//메시지 전송	            		            			            		
        	}
		});
		  
		//텍스트필드와 키보드 리스너 연결 
		t_text.addKeyListener(new KeyAdapter() {
	         public void keyReleased(KeyEvent e) {
	            if(e.getKeyCode()==KeyEvent.VK_ENTER) {//엔터치면..
	            	if(t_text.getText().equals("")) {
	            		JOptionPane.showMessageDialog(ChatMain.this, "메시지를 입력하세요.");//확인눌러서 종료
	            	}else if(soomcoMember.getStatus().equals("true")){
	            		JOptionPane.showMessageDialog(ChatMain.this, "초보와 고수간 연결을 해주세요.");
	            	}else {
	            		getChat_tid();//메시지 전송 전, 보낼 대상 정하기	            	
	            		sendMessage();//메시지 전송	            		            			            		
	            	}
	            }
	         }
	      });	      	
		
		//X버튼 클릭시 현재 프레임만 종료 이벤트
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setSize(400, 600);
		setVisible(true);		
		setLocationRelativeTo(null);
		getCurrentDate();
	}
	
	//초보가 견적서 제출시 보낸 메시지 출력 
	public void getEstimateMessage() {
		chatMainVO = estimateForm.getChatVO();			
		c_content = chatMainVO.getContent();
		c_tid = chatMainVO.getT_id();
		sb.append(c_content);		
	}
	
	//채팅함 눌렀을때 누적 메시지 출력
	public void getChatMessage() {
		PreparedStatement pstmt = null; 	      
		ResultSet rs = null;			
      
		// 채팅함에 로그인한 m-id나 t-id가 같은 사람의 메시지 뽑기 
		String sql = "select content from chat where m_id=? or t_id=? order by chat_id";
		try {
			pstmt = soomcoMain.getCon().prepareStatement(sql);
			pstmt.setString(1, soomcoMember.getId());
			pstmt.setString(2, soomcoMember.getId());
			rs = pstmt.executeQuery();	      
			
			while(rs.next()) {						
				String content = rs.getString("content");				
				
				sb.append(content+"\n"); //대화내용만 StringBuilder에 저장					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		   
			soomcoMain.getDbManager().close(pstmt,rs);
		}	
		
	}
	
	//누적 채팅메시지 전송
	public void sendMessage() {
		area.append("["+soomcoMember.getId()+"]: ");
		area.append(t_text.getText()+"\n");//area에 전송 메시지 출력		
		
		PreparedStatement pstmt=null;
		String sql = "insert into chat(chat_id,m_id,content,t_id) values(seq_chat.nextval,?,?,?)";
		try {
			pstmt = soomcoMain.getCon().prepareStatement(sql);
			pstmt.setString(1, soomcoMember.getId()); //로그인한 아이디
			pstmt.setString(2, "["+soomcoMember.getId()+"]: " + t_text.getText()); //[이름]: 형식+메시지
			if(soomcoMember.getPosition().equals("초보")) {//로그인한 사람이 초보면 고수에게 전송
				pstmt.setString(3, User_To_gosu);			
				//메인에서  1대1 채팅함 열었을때 --> 견적보낸 초보는 한명(견적제출 시 유저,고수 상태 false,다른사람은 견적 못보냄,즉 area엔 한명의 정보만 있음)-> 읽은area에 --username의 견적서--로 id 가져옴 
			}else{
				pstmt.setString(3, Gosu_To_user);//soomcoMember.getPosition()==고수 인경우, 즉 고수로 로그인한 경우	 유저에게 전송	
			}
			pstmt.executeUpdate();			
			t_text.setText("");//메시지 한번 전송 후 초기화
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			soomcoMain.getDbManager().close(pstmt);
		}
		
	}
	
	//member 테이블 m_status를 변경하는 쿼리
	public void updateStatus(String status, String c_tid) { //함수 호출시, 바꾸려는 status상태와, 상대방 아이디 매개변수 넣는다
		//set m_status를 1)견적서 연결됬을떄는 false로, 2) 매칭,대화나가기 했을땐 true로 매개변수 넘기기
		//본인아이디는 soomcoMember에서 get 가능, 		
		//애초에 초보로그인한상태 -> 메인에서 1대1 대화함에서는 둘다 status가 false인 상태임
		//견적서 제출시 뜬 챗창에선 상대방 아이디를 estimateForm에서 넘겨받아야함(chatMain이 견적서제출로 뜬 경우엔 맨위에서 바로 함수 호출)
		
		PreparedStatement pstmt=null;
		//견적서 제출시 쪽지 연결된 유저와 고수의 상태를 false로 변경 --> 다른 사람들은 견적서를 제출(개입) 불가능 상태		
		String sql = "update member set m_status='"+ status +"' where m_id='"+soomcoMember.getId()+"' or m_id='"+ c_tid +"' ";		
		try {			
			pstmt = soomcoMain.getCon().prepareStatement(sql);
			soomcoMember.setStatus(status);
			//쿼리수행
			pstmt.executeUpdate();

		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			soomcoMain.getDbManager().close(pstmt);
		}
		
	}
	
	//채팅창에 메시지 전송시 필요한 상대ID , 채팅 연결시 로그인user와 상대방 ID status 업데이트 하기 위해, 대화방 나가기 할떄 필요한 상대방ID 뽑는 함수
	public void getChat_tid() {
		PreparedStatement pstmt = null; 	      
		ResultSet rs = null;			
		
		//맨처음 견적서를 통해 채팅이 연결됬을때 저장한 m_id는 초보 , t_id는 고수 아이디임
		String sql = "select m_id, t_id from chat where m_id=? or t_id=? order by chat_id";
		try {
			pstmt = soomcoMain.getCon().prepareStatement(sql);
			pstmt.setString(1, soomcoMember.getId());			
			pstmt.setString(2, soomcoMember.getId());			
			rs = pstmt.executeQuery();	      
			//처음 채팅내용(견적서) 발견시
			if(rs.next()) {		
				Gosu_To_user = rs.getString("m_id");//m_id는 초보ID이며 고수가 전송할 대상ID
				User_To_gosu = rs.getString("t_id");//t_id는 고수ID이며 초보가 전송할 대상ID
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		   
			soomcoMain.getDbManager().close(pstmt,rs);
		}				
	}
	
	//대화방 나가기를 눌렀을때 쓰이는 채팅 메시지 전부 삭제하는 함수
	public void deleteMessage(String User_To_gosu) {
		PreparedStatement pstmt = null; 	      				
		
		//초보와 고수의 채팅메시지 전부 삭제
		String sql = "delete from chat where m_id='"+soomcoMember.getId()+"' or t_id='"+User_To_gosu+"'";
		try {
			pstmt = soomcoMain.getCon().prepareStatement(sql);					
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {		   
			soomcoMain.getDbManager().close(pstmt);
		}					
	}
	
	//현재 날짜 얻는 함수
	public String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String now = formatDate.format(cal.getTime()); //현재 년월일 get
		
		return now;
	}
	
	//견적서에 적혀있는 데드라인 출력
	public String getDeadLineDate() {		
		StringTokenizer st = new StringTokenizer(area.getText(), "부터");	
		String[] textArr = new String[2];					
		
		textArr[0] = st.nextToken();
		textArr[1] = st.nextToken();
		textArr[1] = textArr[1].split("까지")[0];// 견적서로 제출한 DeadLine을 얻어옴				g		
		
		return textArr[1];	
	}
	
	
	
	//매칭하기 눌렀을때 매칭카운트를 1 올리고
	public void ConnectMatching() {		
		PreparedStatement pstmt=null;
		int count = soomcoMember.getMatching_count()+1;
		String sql = "update member set m_matching='true', m_matching_count="+count+", m_deadLine='"+getDeadLineDate()+"' where m_id='"+soomcoMember.getId()+"' or m_id='"+ User_To_gosu +"' or m_id='"+ Gosu_To_user +"' ";
		
		try {			
			pstmt = soomcoMain.getCon().prepareStatement(sql);
			soomcoMember.setDeadline(getDeadLineDate()); // 매칭후, 한번더 눌렀을떄 알림띄우기위해 갱신
			//쿼리수행
			pstmt.executeUpdate();

		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			soomcoMain.getDbManager().close(pstmt);
		}
	}
	
	public static EstimateForm getEstimateForm() {
		return estimateForm;
	}

	public static void setEstimateForm(EstimateForm estimateForm) {
		ChatMain.estimateForm = estimateForm;
	}

	public static void main(String[] args) {
		new ChatMain(estimateForm, soomcoMain,clickStatus);
	}
}