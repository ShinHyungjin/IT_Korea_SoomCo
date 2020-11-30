package com.soomco.member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.soomco.home.Home;
import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;

public class Login extends Page {
	JPanel p_content;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login;
	JButton bt_regist;
	SoomcoMember soomcoMember;
	Home home;

	public Login(SoomcoMain soomcoMain) {
		super(soomcoMain);
		this.home = (Home) soomcoMain.getPage()[0];
		soomcoMember = soomcoMain.getSoomcoMember();

		p_content = new JPanel();
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("Login");
		bt_regist = new JButton("회원가입");

		setTextBorder(t_id, "ID", false);
		setTextBorder(t_pass, "PASSWORD", 1);
		
		// 스타일
		p_content.setPreferredSize(new Dimension(450, 220));
		p_content.setBackground(Color.WHITE);
		t_id.setPreferredSize(new Dimension(350, 70));
		t_pass.setPreferredSize(new Dimension(350, 70));
		bt_login.setBackground(new Color(253, 116, 31));
		bt_login.setBorderPainted(false);
		bt_login.setForeground(Color.white);
		bt_login.setPreferredSize(new Dimension(120, 50));
		bt_login.setFont(new Font("굴림", Font.BOLD, 20));// 글씨체 바꾸기
		bt_regist.setPreferredSize(new Dimension(120, 50));
		bt_regist.setBackground(new Color(253, 116, 31));
		bt_regist.setBorderPainted(false);
		bt_regist.setForeground(Color.white);
		bt_regist.setFont(new Font("굴림", Font.BOLD, 20));// 글씨체 바꾸기

		// 조립
		p_content.add(t_id);
		p_content.add(t_pass);
		p_content.add(bt_login);
		p_content.add(bt_regist);

		add(p_content);

		t_id.requestFocus();// 아이디 컴포넌트에 포커스 올리기

		// 회원가입 버튼과 리스너 연결
		bt_regist.addActionListener((e) -> {
			getSoomcoMain().showPage(SoomcoMain.REGISTFORM);
		});

		// 로그인 버튼과 리스너연결
		bt_login.addActionListener((e) -> {
			soomcoMember.setId(t_id.getText());
			soomcoMember.setPassword(new String(t_pass.getPassword()));

			validCheck(soomcoMember);			
		});

		// 키보드 리스너 연결
		t_id.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					soomcoMember.setId(t_id.getText());
					soomcoMember.setPassword(new String(t_pass.getPassword()));

					validCheck(soomcoMember);					
				}
			}
		});

		t_pass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// 엔터치면..
					soomcoMember.setId(t_id.getText());
					soomcoMember.setPassword(new String(t_pass.getPassword()));

					validCheck(soomcoMember);					
				}
			}
		});
	}

	public void validCheck(SoomcoMember soomcoMember) {
		if (soomcoMember.getId().length() < 1) {// 문자열의 길이가 0이라면..
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
		} else if (soomcoMember.getPassword().length() < 1) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
		} else {
			if (login(soomcoMember) == false) {
				JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다.");
			} else {
				JOptionPane.showMessageDialog(this, "로그인 성공");
				// Home 페이지 보여주고, 카드 리무브
				Home.p_location.removeAll();
				home.seq.clear();// arrayList 비우기
				home.getRandom().clear();// arrayList 비우기
				// 나의 포지션에 맞게 다시카드 뿌리기!
				home.callMemberCard(soomcoMember.getPosition());// 로그인의 포지션
				
				if (soomcoMember.getPosition().equals("고수")) {
					home.la_location_title.setText("나의 전공  초보회원들");	
				} else if (soomcoMember.getPosition().equals("초보")) {
					home.la_location_title.setText("나의 지역 숨은고수들 ");										
				}
				getSoomcoMain().showPage(SoomcoMain.HOME);
				// 버튼의 라벨을 로그아웃으로 전환
				getSoomcoMain().header[2].setText("Logout");
				getSoomcoMain().header[2].setBackground(new Color(253, 116, 31));
				getSoomcoMain().header[2].setForeground(Color.black);
				getSoomcoMain().setHasSession(true);// 로그인 상태를 담는 값 true

			}
		}
	}

	// 회원 로그인 처리 메서드 정의: 로그인 성공 후 Home을 보여 줄 예정임
	// 아래의 메서드가 boolean형을 번환하여 유저가 있을시 true로 바꾸고
	public boolean login(SoomcoMember soomcoMember) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isCheck = false;

		String sql = "select * from member ";
		sql += " where m_id=? and m_password=?";// 입력한 아이디와 비번이 회원테이블에 존재하는지 여부를 판단해야함
		try {
			pstmt = getSoomcoMain().getCon().prepareStatement(sql);
			pstmt.setString(1, soomcoMember.getId());
			pstmt.setString(2, soomcoMember.getPassword());
			rs = pstmt.executeQuery();

			// rs.next() 가 참이면, 회원이 존재하는 것이므로 로그인으로 인정해 주자!!
			// 회원정보를 vo에 옮겨담자!!
			if (rs.next()) {
				soomcoMember.setSeq(rs.getInt("m_seq"));
				soomcoMember.setId(rs.getString("m_id"));
				soomcoMember.setPassword(rs.getString("m_password"));
				soomcoMember.setName(rs.getString("m_name"));
				soomcoMember.setLocation(rs.getString("m_location"));
				soomcoMember.setMail(rs.getString("m_mail"));
				soomcoMember.setGender(rs.getString("m_gender"));
				soomcoMember.setPosition(rs.getString("m_position"));
				soomcoMember.setIntroduce(rs.getString("m_introduce"));
				soomcoMember.setImage(rs.getString("m_image"));
				soomcoMember.setTop_interests(rs.getString("m_top_interests"));
				soomcoMember.setSub_interests(rs.getString("m_sub_interests"));
				soomcoMember.setStatus(rs.getString("m_status"));
				soomcoMember.setMatching_count(rs.getInt("m_matching_count"));
				soomcoMember.setDeadline(rs.getDate("m_deadline").toString());
				isCheck=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getSoomcoMain().getDbManager().close(pstmt, rs);
		}
		return isCheck;
	}
	
	// 로그아웃 처리
	// 1.hasSession 값을 false처리 2.버튼의 배경색 빼기 3.버튼의 텍스트 login으로 바꾸기
	public void logout() {
		getSoomcoMain().setHasSession(false);
		t_id.setText("");
		t_pass.setText("");
		getSoomcoMain().header[2].setBackground(null);
		getSoomcoMain().header[2].setForeground(null);
		getSoomcoMain().header[2].setText("login");
		getSoomcoMain().header[2].setForeground(new Color(253, 116, 31));		
		getSoomcoMain().showPage(SoomcoMain.LOGIN);

		Home.p_location.removeAll();
		home.seq.clear();// arrayList 비우기
		home.getRandom().clear();// a

		home.la_location_title.setText("딱! 맞는 숨은고수를 소개합니다");

		home.callMemberCard(null);
	}

}