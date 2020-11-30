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

public class Interior extends Page {
	CategorySame categorySame;
	JPanel p_content;
	int sub_size = 6;
	String name = "Interior";
	ArrayList<String> sub_img = new ArrayList<String>();
	ArrayList<String> sub_name = new ArrayList<String>();
	int top_id = 4;
	

	public Interior(SoomcoMain soomcoMain) {
		super(soomcoMain);
		
		getSubImg();
		
		p_content = new JPanel();
		p_content.setPreferredSize(new Dimension(soomcoMain.WIDTH - 180, soomcoMain.HEIGHT));
		p_content.setBackground(Color.white);

		categorySame = new CategorySame(soomcoMain, sub_size, name, sub_img, sub_name);

		p_content.add(categorySame.getPanel());
		add(p_content);
		setBackground(Color.white);
	}
	
	public void getSubImg() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sub_img, sub_name from sub_interests where top_id="+top_id;
		
		try {
			pstmt = getSoomcoMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
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