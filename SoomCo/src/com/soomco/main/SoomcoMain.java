package com.soomco.main;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.soomco.category.Beauty;
import com.soomco.category.CategoryCard;
import com.soomco.category.Cooking;
import com.soomco.category.Education;
import com.soomco.category.Exercise;
import com.soomco.category.Interior;
import com.soomco.category.Language;
import com.soomco.category.Lesson;
import com.soomco.category.Programming;
import com.soomco.chat.ChatMain;
import com.soomco.home.Home;
import com.soomco.member.Login;
import com.soomco.member.RegistForm;
import com.soomco.member.SoomcoMember;
import com.soomco.util.db.DBManager;
import com.soomco.util.hover.HoverButton;
import com.soomco.util.image.ImageUtil;

//merge test
public class SoomcoMain extends JFrame {
	// 상수 선언
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

// 최상위 페이지들
	public static final int HOME = 0;
	// 하위페이지_소카테고리
	public static final int BEAUTY = 1;
	public static final int COOKING = 2;
	public static final int EDUCATION = 3;
	public static final int EXERCISE = 4;
	public static final int LANGUAGE = 5;
	public static final int INTERIOR = 6;
	public static final int LESSON = 7;
	public static final int PROGRAMMING = 8;
	public static final int LOGIN = 9;
	public static final int REGISTFORM = 10;

	JPanel p_main;// 전체를 담을 패널
	JPanel p_header; // 웹사이트의 주 메뉴를 포함할 컨테이너 패널 == p_header
	JPanel p_header_null;// p_header에서 가운데 공백을 줄 패널
	JPanel p_content; // 여기에 모든 페이지가 미리 붙어져있을것임//추후 showPage 메서드로 보일지여부 설정
	JScrollPane scroll;
	JLabel la_footer;// 제작자, 역할등의 라벨
	JPanel p_soomco;
	JPanel p_footer;
	JPanel p_footer_west;
	JPanel p_footer_center;
	JPanel p_footer_east;
	JPanel p_footer_line;
	
	JLabel la_footer1;
	
	
	ImageIcon icon;
	Image img;
	public String[] dirName = { "Beauty", "Cooking", "Education", "Exercise", "Language", "Interior", "Lesson",
			"Programming" };

	String[] topName = { "뷰티", "요리", "교육", "운동", "외국어", "인테리어", "레슨", "프로그래밍" };

	String[] iconName = { "BeautyIcon.png", "CookingIcon.png", "EducationIcon.png", "ExerciseIcon.png",
			"LanguageIcon.png", "InteriorIcon.png", "lessonIcon.png", "ProgrammingIcon.png" };

	String[] header_title = { "홈", "1:1 채팅", "로그인", "회원가입" };
	public JButton[] header = new JButton[header_title.length];

	String[] header_theme = { "Programming", "Lesson", "Interior", "Beauty", "Cooking", "Education", "Language",
			"Exercise" };
	public HoverButton[] theme = new HoverButton[header_theme.length];

	public ArrayList<String> sURL;
	public ArrayList<String> sTitle;
	public ArrayList<Image> sImage;

	Font customFont;
	
	ChatMain chatMain;
	CategoryCard categoryCard;

	// 페이지 구성
	Page[] page = new Page[11];// 최상위페이지들

	private DBManager dbManager;
	private Connection con;
	private SoomcoMember soomcoMember;
	// 로그인 상태인지 여부를 알 수 있는 변수
	private boolean hasSession = false;

	public SoomcoMain() {
		sURL = new ArrayList<String>();
		sTitle = new ArrayList<String>();
		sImage = new ArrayList<Image>();
		dbManager = new DBManager();
		soomcoMember = new SoomcoMember();
		p_main = new JPanel();
		p_header = new JPanel();
		
		
		p_footer_west=new JPanel();
		p_footer_center=new JPanel();
		p_footer_east=new JPanel();
		
		
		p_footer=new JPanel();
		p_header_null = new JPanel();// 일단 보류
		p_content = new JPanel();
		scroll = new JScrollPane(p_content);
		la_footer = new JLabel("<html>&nbsp;상호명:(주)숨은코딩.SoomCo<br>"
				+ "&nbsp;Copyright ©SoomCo. All Rights Reserved.<br>"
				+ "_________________________________________________________________</html>", SwingConstants.LEFT);
			la_footer1=new JLabel("<html>&nbsp;김수연:https://github.com/RAMA4368<br>"
								+ "&nbsp;김지언:https://github.com/hihihi11008<br>"
								+ "&nbsp;배성민:https://github.com/ISPserver<br>"
								+ "&nbsp;신형진:https://github.com/ShinHyungjin<html>",SwingConstants.LEFT);

		con = dbManager.connect();
		
		//푸터 디자인
		p_footer.setLayout(new BorderLayout());
		p_footer.setPreferredSize(new Dimension(WIDTH,100));
		p_footer.setBackground(Color.BLUE);

		p_footer_center.setLayout(new GridLayout(1,2));
		
		
		//WIDTH/3-300   //WIDTH/3-100
		p_footer_west.setPreferredSize(new Dimension(100,100));
		p_footer_west.setBackground(new Color(253, 116, 31));
		p_footer_center.setPreferredSize(new Dimension(WIDTH ,100));
		p_footer_center.setBackground(new Color(253, 116, 31));
		p_footer_east.setPreferredSize(new Dimension(100,100));
		p_footer_east.setBackground(new Color(253, 116, 31));
		
//		p_footer_west.add(la_footer1);
		
		p_footer.add(p_footer_west,BorderLayout.WEST);
		p_footer.add(p_footer_center,BorderLayout.CENTER);
		p_footer.add(p_footer_east,BorderLayout.EAST);
		p_footer_center.add(la_footer);
		p_footer_center.add(la_footer1);
		
		
		// 숨코 로고 넣기
		String path1 = "res/soomco/soomco.png";
		icon = ImageUtil.getIcon(this.getClass(), path1, 200, 70);
		img = icon.getImage();
		
		
		p_soomco = new JPanel() {
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
				g2.drawImage(img, 0, 0, null);
			}
		};
		p_soomco.setPreferredSize(new Dimension(300, 90));

		// 폰트적용
		try {
			customFont = Font
					.createFont(Font.TRUETYPE_FONT,
							new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(22f);					
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		if (con == null) {
			JOptionPane.showMessageDialog(this, "데이터베이스에 접속할 수 없습니다.");
			System.exit(0);
		} else {
			this.setTitle("숨은코딩! SoomCo에 오신걸 환영합니다.");
		}
		//매칭종료기간이 오늘인 유저의 상태 true업데이트, 관련 채팅 메시지 삭제
	      if(CheckMatching()==false){
	        System.out.println("deadline이 오늘 날짜인 유저 없음");
	      }

		for (int i = 0; i < theme.length; i++) {
			String path = "res/" + dirName[i] + "/" + iconName[i];
			icon = ImageUtil.getIcon(this.getClass(), path, 60, 60);
			theme[i] = new HoverButton();
			theme[i].setIcon(icon);
			theme[i].setBorderPainted(false);
			theme[i].setContentAreaFilled(false);
			theme[i].setPreferredSize(new Dimension(60, 60));
		}
		p_header.setBackground(Color.WHITE);
		p_header.add(p_soomco, BorderLayout.WEST);
		p_header.add(p_header_null);
		p_header.setPreferredSize(new Dimension(WIDTH, 80));
		p_header_null.setPreferredSize(new Dimension(150, 80));
		p_header_null.setBackground(Color.WHITE);
		// 메인 네비게이션 생성
		for (int i = 0; i < header.length; i++) {
			header[i] = new JButton(header_title[i]);
			p_header.add(header[i], BorderLayout.EAST);// 패널에 네비게이션 부착
			header[i].setBorderPainted(false);
			header[i].setContentAreaFilled(false);
			header[i].setForeground(new Color(253, 116, 31));
			header[i].setFont(customFont);
		}

		// 페이지 구성
		// 프로그래밍/운동/레슨/인테리어/요리/외국어/교육/뷰티
		page[0] = new Home(this);
		page[1] = new Beauty(this);
		page[2] = new Cooking(this);
		page[3] = new Education(this);
		page[4] = new Exercise(this);
		page[5] = new Language(this);
		page[6] = new Interior(this);
		page[7] = new Lesson(this);
		page[8] = new Programming(this);
		page[9] = new Login(this);
		page[10] = new RegistForm(this);

		// 스타일적용
		p_content.setPreferredSize(new Dimension(WIDTH, HEIGHT + 210));
		p_main.setPreferredSize(new Dimension(WIDTH, HEIGHT + 210)); // 완전전체패널
		p_content.setBackground(Color.WHITE);
		la_footer.setFont(new Font("Medium", Font.BOLD, 11));
		la_footer.setPreferredSize(new Dimension(700, 100));
		la_footer.setOpaque(true); 
		la_footer.setBackground(new Color(253, 116, 31));
		
		la_footer1.setFont(new Font("Medium", Font.BOLD, 11));
		la_footer1.setPreferredSize(new Dimension(700, 100));
		la_footer1.setOpaque(true); 
		la_footer1.setBackground(new Color(253, 116, 31));
		
		// 조립
		p_main.setLayout(new BorderLayout());
		p_main.add(p_header, BorderLayout.NORTH);

		// 모든 페이지를 미리 붙여놓자!!
		for (int i = 0; i < page.length; i++) {
			p_content.add(page[i]);
		}
		
		p_main.add(scroll);// 센터에 페이지 부착
		p_main.add(p_footer, BorderLayout.SOUTH);

		this.add(p_main);
		

		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);

		// 프레임과 리스너연결
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
				System.exit(0);
			}
		});
		showPage(HOME);
		// 헤더 버튼과 리스너연결
		for (int i = 0; i < header.length; i++) {
			header[i].addActionListener((e) -> {
				Object obj = e.getSource();
				if (obj == header[0]) { // home 버튼 클릭시 Home 화면 전환
					showPage(HOME);
					setTitle("SoomCo에 오신걸 환영합니다.");

				}else if (obj == header[1]) { // 1:1Chat 클릭시 쪽지함 new 생성
		               if(soomcoMember.getId()==null) {
		                   JOptionPane.showMessageDialog(this, "로그인을 해주세요.");
		                }else {                  
		                    ChatMain chatMain = new ChatMain(ChatMain.getEstimateForm(),this, "쪽지함");
		                    chatMain.setTitle(soomcoMember.getId()+"의 쪽지함");
		                }
		         	}  
				else if (obj == header[2]) { // Login 버튼 클릭시 Login 화면 전환
					if (hasSession) {
						int ans = JOptionPane.showConfirmDialog(SoomcoMain.this, "로그아웃 하시겠습니까?");

						if (ans == JOptionPane.OK_OPTION) {
							Login login = (Login) page[SoomcoMain.LOGIN];
							login.logout();
						}
					} else {
						showPage(LOGIN);						
					}
				} else if (obj == header[3]) { // Join 버튼 클릭시 Registform 화면 전환
					showPage(REGISTFORM);
				}
			});
		}

		for (int i = 0; i < theme.length; i++) {
			theme[i].addActionListener((e) -> {
				Object obj = e.getSource();
				if (obj == theme[0]) {
					showPage(BEAUTY);
					setTitle("보기좋은 떡이 맛도 좋다는 듯이, 좋은 이미지는 좋은 인상을 남깁니다. Beauty");
				} else if (obj == theme[1]) {
					showPage(COOKING);
					setTitle("맛있는 음식은 언제나 감동을 주기 마련입니다. Cooking");
				} else if (obj == theme[2]) {
					showPage(EDUCATION);
					setTitle("공부는 지성인이 되기위한 걸음이자 마음수련에 가장 좋은 행동입니다. Education");
				} else if (obj == theme[3]) {
					showPage(EXERCISE);
					setTitle("정신적인 성장이 공부라면, 육체적인 성장은 운동입니다. Exercise");
				} else if (obj == theme[4]) {
					showPage(LANGUAGE);
					setTitle("더 많은 친구들, 더 많은 지식을 위해서는 외국인과의 소통도 중요합니다. Language");
				} else if (obj == theme[5]) {
					showPage(INTERIOR);
					setTitle("인테리어는 아이들을 안전하게 해주며 효율적인 공간활용을 가능하게 합니다. Interior");
				} else if (obj == theme[6]) {
					showPage(LESSON);
					setTitle("스트레스 발산을 위해 취미 하나쯤은 어떠신가요? Lesson");
				} else if (obj == theme[7]) {
					showPage(PROGRAMMING);
					setTitle("MinJinHo와 함께라면 어떠한 언어라도 두렵지 않습니다. Programming");
				}
			});
		}
	}

	// 보여질 컨텐트와 가려질 컨텐트를 제어하는 메서드
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj); // 제거 대상
		this.add(addObj);// 부착 대상
		((JPanel) addObj).updateUI();
	}

	// 보여질 페이지와 않보여질 페이지를 설정하는 메서드
	public void showPage(int showIndex) { // 매개변수로는 보여주고 싶은 페이지 넘버
		for (int i = 0; i < page.length; i++) {// 모든 페이지를 대상으로..
			if (i == showIndex) {
				page[i].setVisible(true); // 보이게할 페이지
			} else {
				page[i].setVisible(false); // 않보이게할 페이지
			}
		}
	}
	//현재 날짜 얻는 함수
	   public String getCurrentDate() {
	      Calendar cal = Calendar.getInstance();
	      SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	      String now = formatDate.format(cal.getTime()); //현재 년월일 get
	      
	      return now;
	   }
	   
	   //deanline이 오늘인 유저 찾기
	   public boolean CheckMatching() {   
	      PreparedStatement pstmt = null;          
	      ResultSet rs = null;            
	      boolean hasCheck = false;
	      String mid;
	      //deadline이 현재날짜와 같은 m_id 뽑기
	      String sql = "select m_id from member where m_deadline='"+getCurrentDate()+"' and m_matching='true' ";//ChatMain클래스 함수 재사용
	      try {
	         pstmt = con.prepareStatement(sql);              
	         rs = pstmt.executeQuery();         
	         while(rs.next()) {
	            hasCheck=true;
	            mid = rs.getString("m_id");
	            UpdateMatching(mid); //상태 업데이트 함수 호출
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {         
	         this.dbManager.close(pstmt,rs);
	      }   
	      return hasCheck; 
	   }
	   
	   //dead라인이 오늘날짜와 같은 유저(즉, 매칭기간이 끝난 유저) status update
	   public void UpdateMatching(String mid) {
	         PreparedStatement pstmt=null;      
	      String sql = "update member set m_status='true', m_matching='false' where m_id='"+mid+"'";
	      
	      try {         
	         pstmt = con.prepareStatement(sql);         
	         pstmt.executeUpdate();
	         deleteMessage(mid);
	      } catch (SQLException e) {      
	         e.printStackTrace();
	      } finally {
	         dbManager.close(pstmt);
	      }
	   }
	   
	   //deadline이 마감되서 관련된 채팅 메시지 전부 삭제하는 함수
	   public void deleteMessage(String mid) {   
	      PreparedStatement pstmt = null;      
	      //해당유저 채팅메시지 전부 삭제
	      String sql = "delete from chat where m_id='"+mid+"' ";
	      try {
	         pstmt = con.prepareStatement(sql);               
	         pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {         
	         dbManager.close(pstmt);
	      }               
	   }

	public HoverButton setButtonIcon(HoverButton theme, int index) {
		String path = "res/" + dirName[index] + "/" + iconName[index];
		icon = ImageUtil.getIcon(this.getClass(), path, 70, 80);
		theme = new HoverButton();
		theme.setIcon(icon);
		theme.setBorderPainted(false);
		theme.setContentAreaFilled(false);
		theme.setPreferredSize(new Dimension(70, 80));
		theme.addActionListener((e) -> {
			if (index == 0) {
				showPage(BEAUTY);
				setTitle("보기좋은 떡이 맛도 좋다는 듯이, 좋은 이미지는 좋은 인상을 남깁니다. Beauty");
			} else if (index == 1) {
				showPage(COOKING);
				setTitle("맛있는 음식은 언제나 감동을 주기 마련입니다. Cooking");
			} else if (index == 2) {
				showPage(EDUCATION);
				setTitle("공부는 지성인이 되기위한 걸음이자 마음수련에 가장 좋은 행동입니다. Education");
			} else if (index == 3) {
				showPage(EXERCISE);
				setTitle("정신적인 성장이 공부라면, 육체적인 성장은 운동입니다. Exercise");
			} else if (index == 4) {
				showPage(LANGUAGE);
				setTitle("더 많은 친구들, 더 많은 지식을 위해서는 외국인과의 소통도 중요합니다. Language");
			} else if (index == 5) {
				showPage(INTERIOR);
				setTitle("인테리어는 아이들을 안전하게 해주며 효율적인 공간활용을 가능하게 합니다. Interior");
			} else if (index == 6) {
				showPage(LESSON);
				setTitle("스트레스 발산을 위해 취미 하나쯤은 어떠신가요? Lesson");
			} else if (index == 7) {
				showPage(PROGRAMMING);
				setTitle("MinJinHo와 함께라면 어떠한 언어라도 두렵지 않습니다. Programming");
			}
		});
		return theme;
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	public Connection getCon() {
		return con;
	}

	public Page[] getPage() {
		return page;
	}

	public boolean isHasSession() {
		return hasSession;
	}

	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}

	public SoomcoMember getSoomcoMember() {
		return soomcoMember;
	}

	public void setSoomcoMember(SoomcoMember soomcoMember) {
		this.soomcoMember = soomcoMember;
	}

	
	
	
	public static void main(String[] args) {
		new SoomcoMain();
	}

}