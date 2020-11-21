package com.soomco.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.soomco.db.DBSystem;

public class MainPage extends JFrame{
	
	public static final int WIDTH=1200;
	public static final int HEIGHT=900;
	
	public static final int HOME=0;
	public static final int SUB_CATEGORY=1;
	public static final int ESTIMATE=2;
	public static final int LOGIN=3;
	public static final int MEMBER_REGIST=4;
	public static final int CHAT=5;
		
	JPanel user_container;
	JPanel p_content;
	JPanel p_navi;
	
	String[] navi_title= {"SoomCo","Login/Logout","1:1Chat","Join"};
	JButton[] navi=new JButton[navi_title.length];//[][][][][] 諛곗뿴�깮�꽦
	
	
	Page[] page =new Page[6];
	
	JLabel la_footer;
	private DBSystem dbSystem;
	private Connection con;
	
	
	public MainPage() {
		dbSystem = new DBSystem();
		user_container = new JPanel();
		p_content = new JPanel();
		p_navi = new JPanel();
		la_footer = new JLabel("Wellcome SoomCo Service", SwingConstants.CENTER);
		
		con = dbSystem.connect();
		
		if(con==null) {
			JOptionPane.showMessageDialog(this,"DB접속에 실패했습니다...");
			System.exit(0);
		}else {
			this.setTitle("혁신과 전문성을 보장하는 SoomCo에 오신것을 환영합니다");
		}
		
		
		
		for(int i=0;i<navi.length;i++) {
			navi[i] = new JButton(navi_title[i]);
			p_navi.add(navi[i]);
		}
		
		
		page[0] = new Home(this);
		page[1] = new Product(this);
		page[2] = new QnA(this);
		page[3] = new MyPage(this);
		page[4] = new Login(this);
		page[5] = new ProductDetail(this);
		page[6] = new RegistForm(this);
	
		
		user_container.setPreferredSize(new Dimension(WIDTH, HEIGHT-50));
		user_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH, 50));
		la_footer.setFont(new Font("Arial Black",Font.BOLD,19));
		
		
		user_container.setLayout(new BorderLayout());
		user_container.add(p_navi, BorderLayout.NORTH);

		
		for(int i=0;i<page.length;i++) {
			p_content.add(page[i]);
		}
		
		user_container.add(p_content);
		
		this.add(user_container);
		this.add(la_footer, BorderLayout.SOUTH);

		
		setSize(1200,900);
		setVisible(true);
		setLocationRelativeTo(null);
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbSystem.disConnect(con);
				System.exit(0);
			}
		});
		
		
		for(int i=0;i<navi.length;i++) {
			navi[i].addActionListener((e)->{
				Object obj = e.getSource();
				if(obj==navi[0]) { 
					showPage(0);
				}else if(obj==navi[1]) {
					showPage(1);
				}else if(obj==navi[2]) {
					showPage(2);
				}else if(obj==navi[3]) {
					showPage(3);
				}else if(obj==navi[4]) {
					showPage(4);
				}
			});
		}		
		
	}

	
	
	public void showPage(int showIndex) { 
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true); 
			}else {
				page[i].setVisible(false); 
			}
		}
	}
	
	
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj); 
		this.add(addObj); 
		
		((JPanel)addObj).updateUI();
	}

	public DBSystem getDBSystem() {
		return dbSystem;
	}
	
	public Connection getCon() {
		return con;
	}
	
	public Page[] getPage() {
		return page;
	}
	
	public static void main(String[] args) {
		new MainPage();

	}
}