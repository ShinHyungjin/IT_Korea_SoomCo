package com.soomco.Esimate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EstimateVO {
	private String ch_gender;//지역
	private String t_goal;//목표
	private String t_price1; //가격min
	private String t_price2; //가격max
	private String ch_location;//지역
	private String t_hasSkill;//보유지식
	public String getCh_gender() {
		return ch_gender;
	}
	public void setCh_gender(String ch_gender) {
		this.ch_gender = ch_gender;
	}
	public String getT_goal() {
		return t_goal;
	}
	public void setT_goal(String t_goal) {
		this.t_goal = t_goal;
	}
	public String getT_price1() {
		return t_price1;
	}
	public void setT_price1(String t_price1) {
		this.t_price1 = t_price1;
	}	
	public String getT_price2() {
		return t_price2;
	}
	public void setT_price2(String t_price2) {
		this.t_price2 = t_price2;
	}
	public String getCh_location() {
		return ch_location;
	}
	public void setCh_location(String ch_location) {
		this.ch_location = ch_location;
	}
	public String getT_hasSkill() {
		return t_hasSkill;
	}
	public void setT_hasSkill(String t_hasSkill) {
		this.t_hasSkill = t_hasSkill;
	}
	
	
}
