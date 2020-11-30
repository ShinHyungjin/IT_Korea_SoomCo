package com.soomco.category;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;

public class Beauty extends Page {
	CategorySame categorySame;
	JPanel p_content;
	int sub_size = 6;
	String name = "Beauty";
	ArrayList<String> sub_img = new ArrayList<String>();
	ArrayList<String> sub_name = new ArrayList<String>();
	int top_id = 8;
	

	public Beauty(SoomcoMain soomcoMain) {
		super(soomcoMain);
		
		getSubImg();//해당 카테고리의 서브카테고리 파일이름, 서브카테고리 이름들을 ArrayList에 추가
		
		p_content = new JPanel();
		p_content.setPreferredSize(new Dimension(SoomcoMain.WIDTH - 180, SoomcoMain.HEIGHT));
		p_content.setBackground(Color.white);
		
		//CategorySame에 매개변수로 soomcoMain, 서브카테고리 갯수, 카테고리 이름, 서브카테고리 이미지ArrayList, 이름ArrayList 넘겨서 생성
		categorySame = new CategorySame(soomcoMain, sub_size, name, sub_img, sub_name);

		p_content.add(categorySame.getPanel()); //categorySame 패널을 반환받아 p_content 패널에 부착
		
		add(p_content);
		setBackground(Color.white);
	}
	
	public void getSubImg() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sub_interests 테이블에서 해당 탑카테고리인 파일이름, 서브카테고리 이름들을 얻어옴
		String sql = "select sub_img, sub_name from sub_interests where top_id="+top_id;
		
		try {
			pstmt = getSoomcoMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//ArrayList에 추가
			while(rs.next()) {
				sub_img.add(rs.getString("sub_img"));
				sub_name.add(rs.getString("sub_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getSoomcoMain().getDbManager().close(pstmt,rs);
		}
	}
}