package com.soomco.member;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;

public class RegistForm extends Page{
	JPanel p_content; //로그인폼과 동일한 목적으로 ...
	JTextField t_mid;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JButton bt_regist;
	
	public RegistForm(SoomcoMain SoomcoMain) {
		super(SoomcoMain);
		p_content = new JPanel();
		
		t_mid = new JTextField("아이디 입력");
		t_pass = new JPasswordField();
		t_name = new JTextField("이름 입력");
		t_phone = new JTextField("연락처 입력");
		t_email = new JTextField("이메일 입력");
		bt_regist = new JButton("회원가입");
		
		//스타일 
		p_content.setPreferredSize(new Dimension(400, 350));
		p_content.setBackground(Color.WHITE);
		Dimension d = new Dimension(380,25);
		t_mid.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		//조립 
		p_content.add(t_mid);
		p_content.add(t_pass);
		p_content.add(t_name);
		p_content.add(t_phone);
		p_content.add(t_email);
		p_content.add(bt_regist);
		
		add(p_content);
		
		bt_regist.addActionListener((e)-> {
			if(checkId(t_mid.getText())) {
				JOptionPane.showMessageDialog(RegistForm.this, "중복된 ID입니다.\n다른 ID를 사용하세요.");
			}else {
				SoomcoMember vo = new SoomcoMember();// Empty 상태
				vo.setMid(t_mid.getText());
				vo.setPass(new String(t_pass.getPassword()));
				vo.setName(t_name.getText());
				vo.setPhone(t_phone.getText());
				vo.setEmail(t_email.getText());
				int result = regist(vo);//중복 조회
				if(result==0) {
					JOptionPane.showMessageDialog(RegistForm.this, "등록실패");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "회원가입 성공");
				}
			}
		});
	}
	
	//회원 존재여부 체크 --> true면 중복 존재, 회원가입 시키면 안됨
	public boolean checkId(String mid) {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		boolean flag=false;
		
		String sql="select * from shop_member where mid=?";
		
		try {
			pstmt=getSoomcoMain().getCon().prepareStatement(sql);
			pstmt.setString(1, mid);
			rs=pstmt.executeQuery();
			flag=rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getSoomcoMain().getDbManager().close(pstmt,rs);
		}
		return flag;
	}
	
	//회원 등록
	private int regist(SoomcoMember SoomcoMember) {
		PreparedStatement pstmt=null;
		int result = 0;//메서드의 지역변수는 컴파일러가 초기화하지 않으므로, 반드시 개발자가 초기화
		
		String sql="insert into shop_member(member_id,mid,pass,name,phone,email)";
		sql+= " values(seq_shop_member.nextval,?,?,?,?,?)";
		try {
			pstmt=getSoomcoMain().getCon().prepareStatement(sql);
			//바인드 변수 대입
			pstmt.setString(1, SoomcoMember.getMid());
			pstmt.setString(2, SoomcoMember.getPass());
			pstmt.setString(3, SoomcoMember.getName());
			pstmt.setString(4, SoomcoMember.getPhone());
			pstmt.setString(5, SoomcoMember.getEmail());
			
			//쿼리 수행
			result  = pstmt.executeUpdate();//쿼리 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getSoomcoMain().getDbManager().close(pstmt);
		}
		return result;
	}
}