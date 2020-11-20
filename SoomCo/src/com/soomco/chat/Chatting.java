package com.soomco.chat;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Chatting extends JFrame{
	JPanel p_center, p_south,p_east;
	JPanel p_join; //접속
	JTextArea area_text;
	JButton bt_match, bt_out, bt_send;
	JTextField t_text;
	JScrollPane scroll;

	public Chatting() {
	
		p_south=new JPanel();
		p_east =new JPanel();
		p_join = new JPanel();

		area_text = new JTextArea(); 
		bt_match = new JButton("매칭하기");
		bt_out = new JButton("대화방나가기");
		bt_send=new JButton("전송");
		t_text=new JTextField(20);
		scroll = new JScrollPane(area_text);
		
		add(scroll);

		p_east.setLayout(new GridLayout(3,1));
		p_east.add(bt_match);
		p_east.add(bt_out);
		p_east.add(p_join);
		
		p_south.add(t_text);
		p_south.add(bt_send);
		
		add(p_east,BorderLayout.EAST);
		add(p_south,BorderLayout.SOUTH);
		
		area_text.setBackground(Color.WHITE);
		area_text.setFont(new Font("돋움", Font.PLAIN, 15));
		p_join.setBackground(Color.YELLOW);
	
		p_east.setPreferredSize(new Dimension(100,600));
		p_south.setPreferredSize(new Dimension(600,100));
		
		bt_match.setPreferredSize(new Dimension(100,100));
		bt_out.setPreferredSize(new Dimension(100,100));
		bt_send.setPreferredSize(new Dimension(100,100));
	
		setSize(600,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
	
	}
	public static void main(String[] args) {
		new Chatting();
	}
}

