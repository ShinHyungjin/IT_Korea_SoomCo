package com.soomco.Esimate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.soomco.chat.ChatMain;
import com.soomco.chat.ChatMainVO;
import com.soomco.main.SoomcoMain;
import com.soomco.member.SoomcoMember;

public class EstimateForm extends JFrame{
//	성별-콤보
//	목표- 텍스트아레아 
//	비용- 텍스트2개 ~
//	원하는 지역- 콤보 
//	지식보유내용- 텍스트아레아 
//	견적보내기- 버튼 
	JPanel p_content; //로그인폼과 동일한 목적으로 ...	
	JComboBox<String> ch_gender;//지역
	JTextArea t_goal;//목표
	JScrollPane scroll;//자기 소개 스크롤
	JTextField t_price1; //가격min
	JLabel la_anotation;// ~ (사이)
	JTextField t_price2; //가격max
	JComboBox<String> ch_location;//지역
	JTextArea t_hasSkill;//보유지식
	JScrollPane scroll2;//자기 소개 스크롤
	JTextField t_date1, t_date2;//고용 날짜
	JLabel la_anotation2;// ~ (사이)	
	JButton bt_send;//견적 전송 버튼
	Border bb =new SoftBevelBorder(SoftBevelBorder.RAISED);//버튼처럼 올라와보이는 효과		
	
	String[] area = {"선택하기", "강원도","경기도","경상남도","경상북도","광주광역시","대구광역시","대전광역시","부산광역시",
			"서울특별시","세종특별자치시","울산광역시","인천광역시","전라남도","전라북도","제주도","충청남도","충청북도"};	
	String[] gender = {"선택하기", "남자","여자"};		
	public static SoomcoMain soomcoMain;
	public static ChatMain chatMain;
	public static String selectedCatogory;//선택한 서브카테고리 이름
	ArrayList<String> Employable = new ArrayList<String>(); 
	private ChatMainVO chatVO;
	SoomcoMember soomcoMember;
	String clickStatus = "견적제출";
	boolean flag = true;
	
	 //String name은 서브카테고리명 받은 매개변수 String selectedCatogory추가해야함(테스트때문에 뺴놈)
	public EstimateForm(SoomcoMain soomcoMain, ChatMain chatMain, String selectedCatogory) {
		this.soomcoMain=soomcoMain;
		this.chatMain=chatMain;		
		this.selectedCatogory=selectedCatogory;
		soomcoMember = soomcoMain.getSoomcoMember();
		p_content = new JPanel();
		ch_gender = new JComboBox<String>();
		t_goal = new JTextArea();
		scroll = new JScrollPane(t_goal);
		t_price1 = new JTextField();
		la_anotation = new JLabel(" ~");
		t_price2 = new JTextField();
		ch_location = new JComboBox<String>();
		t_hasSkill = new JTextArea();
		scroll2 = new JScrollPane(t_hasSkill);
		t_date1 = new HintTextField("YYYY-MM-DD");		
		la_anotation2 = new JLabel(" ~");
		t_date2 = new HintTextField("YYYY-MM-DD");
		
		bt_send = new JButton("견적 제출");
		
		//스타일 
		p_content.setPreferredSize(new Dimension(400, 740));
		p_content.setBackground(Color.WHITE);
		
		//폰트 && 보더 설정
		setTextBorder(ch_gender, "성별",false);
		setTextBorder(scroll, "목표",false);
		setTextBorder(t_price1, "최소가격", false);
		setTextBorder(la_anotation, "~",true);
		setTextBorder(t_price2, "최대가격",false);
		setTextBorder(ch_location, "원하는 지역",false);
		setTextBorder(scroll2, "지식 보유 내용",false);
		setTextBorder(t_date1, "시작 날짜", false);
		setTextBorder(la_anotation2, "~",true);
		setTextBorder(t_date2, "종료 날짜", false);
				
		
		//사이즈 설정
		Dimension d = new Dimension(380,50);
		ch_gender.setPreferredSize(new Dimension(180,60));
		scroll.setPreferredSize(new Dimension(380,200));
		t_price1.setPreferredSize(new Dimension(180,60));
		la_anotation.setPreferredSize(new Dimension(30,60));
		t_price2.setPreferredSize(new Dimension(180,60));
		ch_location.setPreferredSize(new Dimension(380,60));
		scroll2.setPreferredSize(new Dimension(380,200));		
		t_date1.setPreferredSize(new Dimension(180,60));
		la_anotation2.setPreferredSize(new Dimension(30,60));
		t_date2.setPreferredSize(new Dimension(180,60));
		bt_send.setPreferredSize(new Dimension(380,40));
		bt_send.setBackground(new Color(253, 116, 31));
		bt_send.setForeground(Color.white);
				
		for(int i=0; i<area.length; i++) ch_location.addItem(area[i]);//지역												
		for(int i=0; i<gender.length; i++) ch_gender.addItem(gender[i]);//성별
			
		//조립
		p_content.add(ch_gender);
		p_content.add(scroll);
		p_content.add(t_price1);
		p_content.add(la_anotation);
		p_content.add(t_price2);		
		p_content.add(ch_location);
		p_content.add(scroll2);
		p_content.add(t_date1);
		p_content.add(la_anotation2);
		p_content.add(t_date2);
		
		p_content.add(bt_send);
		
		add(p_content);
		
		setSize(450, 800);
		setVisible(true);
		setLocationRelativeTo(null);	
		
		bt_send.addActionListener((e)-> {			
			//쿼리문 -> 채팅 오픈
			if(soomcoMember.getStatus().equals("false")) {
				JOptionPane.showMessageDialog(this, "아직 진행중인 견적이 있습니다. 쪽지함을 확인하세요.");
				dispose();
				soomcoMain.showPage(SoomcoMain.HOME);
			}else{				
				getRandomID();
				if(flag) {
					new ChatMain(this,soomcoMain, clickStatus);
					dispose(); //현재 프레임만 종료시키는 함수					
				}
			}
		});
		
	}
	
	// 등록되어있는 멤버 수 얻어오기 
	   public ArrayList getMemberCount(String selectedCatogory) {//selectedCategory는 선택한 서브카테고리
	      PreparedStatement pstmt = null; 	      
	      ResultSet rs = null;
	      
	      //포지션이 고수이고, 등록되어 있는 관심사가 유저가 선턱한 카테고리인 고수 뽑기
	      String sql = "select m_id from member where m_sub_interests='"+selectedCatogory+"' and m_position='고수' and m_status='true'";	     
	      try {
	      pstmt = soomcoMain.getCon().prepareStatement(sql);        
	      rs = pstmt.executeQuery();	      
	      while(rs.next()) {
	    	  Employable.add(rs.getString("m_id")); //조건에 해당하는 고수 몇 명인지 뽑기
	      }
	   } catch (SQLException e) {
	      e.printStackTrace();
	   }finally {		   
		   soomcoMain.getDbManager().close(pstmt,rs);
	   }
	    return Employable; //조건 해당하는 고수들 들어있는 ArrayList
	   }
	
	public void getRandomID() {
		//getMemberCount에서 조건(고용가능한)에 맞는 고수의 ID를 배열에 담고
		//그 배열을 반환한 뒤, 0부터 배열길이까지의 숫자중 랜덤으로 1개를 뽑는다.
		ArrayList<String> canEmploy = getMemberCount(selectedCatogory);
		int min = 0;
		int max = canEmploy.size()-1;
		int randomID = (int)(Math.random() * max);
		
		if(canEmploy.size()==0){
			JOptionPane.showMessageDialog(this, "선택한 관심사에 등록된 고수가 없습니다.");
			flag=false;
			dispose();
		}else {
			sendData(canEmploy.get(randomID).toString());//얻어온 고용가능한 고수의 ID를 매개변수로 넘겨서 sendData함수 호츌			
		}
	}
	
	public void sendData(String t_id) {
		
		PreparedStatement pstmt=null;		
		String sql = "insert into chat(chat_id,m_id,content,t_id) values(seq_chat.nextval,?,?,?)";
		StringBuilder sb= new StringBuilder();//전송할 채팅 내용을 담을 Stringbuilder
		try {			
			pstmt = soomcoMain.getCon().prepareStatement(sql);			
			pstmt.setString(1, soomcoMember.getId());
			sb.append("["+soomcoMember.getId()+"]:");
			sb.append(System.getProperty("line.separator"));
			sb.append("--"+soomcoMember.getId()+"님의 견적서--");
			sb.append(System.getProperty("line.separator"));
			sb.append("이름: "+ ch_gender.getSelectedItem());
			sb.append(System.getProperty("line.separator"));
			sb.append("목표: "+ t_goal.getText());
			sb.append(System.getProperty("line.separator"));
			sb.append("가격: "+ t_price1.getText()+"원 ~ "+t_price2.getText()+"원");
			sb.append(System.getProperty("line.separator"));
			sb.append("지역: "+ ch_location.getSelectedItem());
			sb.append(System.getProperty("line.separator"));
			sb.append("보유지식: "+ t_hasSkill.getText());
			sb.append(System.getProperty("line.separator"));
			sb.append("고용기간: "+ t_date1.getText()+"부터"+t_date2.getText()+"까지");
			sb.append(System.getProperty("line.separator"));
			sb.append(System.getProperty("line.separator"));
			pstmt.setString(2, sb.toString());
			pstmt.setString(3, t_id);
						
			chatVO = new ChatMainVO(); //chat에서 견적제출한 정보 사용위해 vo생성
			chatVO.setM_id(soomcoMember.getId());
			chatVO.setContent(sb.toString());
			chatVO.setT_id(t_id);
						
			pstmt.executeUpdate();
			
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			soomcoMain.getDbManager().close(pstmt);
		}	
	}
	
	
	//JTextField, JLabel 컴포넌트 Font 설정 메서드(오버로딩)
	public  void setTextBorder(JComponent textField ,String text,boolean isLabel) {
		try {
	          Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
	          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	          //register the font
	          ge.registerFont(customFont);
	          	          
	          textField.setFont(customFont);//텍스트필드 폰트 설정
	          //isLabel이 true면 Label이란 뜻이고 border 안줌, false면 JTextField로 인식,  setBorder메서드 실행
	          if(!isLabel) {
	        	  textField.setBorder(BorderFactory.createTitledBorder(bb, text , TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, customFont, new Color(253, 116, 31)));//보더 속성			        	  
	          }
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch(FontFormatException e) {
	          e.printStackTrace();
	      }
	}
	
	//password는 출력형식이 다르기때문에  오버로딩
	public  void setTextBorder(JComponent textField ,String text,int pass) {
		try {	         
	          Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
	          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	          //register the font
	          ge.registerFont(customFont);
	          	          	          
	          //pass가  1이면 JPassword임
	          if(pass==1) {
	        	  textField.setBorder(BorderFactory.createTitledBorder(bb, text , TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, customFont, new Color(253, 116, 31)));//보더 속성			        	  
	          }
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch(FontFormatException e) {
	          e.printStackTrace();
	      }
	}
	
	//Choice 컴포넌트 Font 설정 메서드(오버로딩)
	public void setTextBorder(Component textField ,String text) {
		try {
	          //create the font to use. Specify the size!
	          Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
	          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	          //register the font
	          ge.registerFont(customFont);
	          	          
	          textField.setFont(customFont);//텍스트필드 폰트 설정	          	         
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch(FontFormatException e) {
	          e.printStackTrace();
	      }
	}
	
	
	public ChatMainVO getChatVO() {
		return chatVO;//견적서에 작성한 내용 저장한 VO
	}

	public void setChatVO(ChatMainVO chatVO) {
		this.chatVO = chatVO;//견적서에 작성한 내용 저장한 VO
	}

	public static void main(String[] args) {
		new EstimateForm(soomcoMain,chatMain,selectedCatogory);
	}
	
	
}

