package com.soomco.home;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.soomco.card.CardItem;
import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;
import com.soomco.member.SoomcoMember;
import com.soomco.util.image.ImageUtil;

public class Home extends Page {
	JPanel p_content; // 현재 보이는 컨텐트
	JPanel p_image; // image 패널
	JPanel p_magazine; // 고수 매거진
	JPanel p_totalIcon;// 아이콘 카드 전체를올릴 Grid 패널
	public static JPanel p_location; // 우리지역 숨은 고수 or 회원
	public JLabel la_location_title;
	JLabel la_magazine_title;
	JPanel p_north; // 북쪽에 붙을 패널 : 아이콘패널, 화면전환 패널
	JPanel p_center;// 센터에 붙을 패널 : 나의 숨은고수 라벨, 카드
	JPanel p_south;// 남쪽에 붙을 패널 : 숨은고수 매거진 라벨, 카드
	JPanel p_gosu;// 나의 숨은고수 라벨 올릴 패널
	JPanel p_maga; // 숨은고수 매거진 라벨 올라갈패널
	JPanel p_center_top;// 나의 숨은고수 라벨, deco,deco1 올라갈 패널
	JPanel p_north_west;	//아이콘 토탈패널 위에 올라갈 여백 공간
	JPanel p_north_center;	//아이콘 토탈 패널 위에올라갈 뷰티/교육/외국어/레슨패널
	JPanel p_north_east;	//아이콘 토탈 패널 위에 올라갈 요리/운동/인테리어/프로그래밍
	
	JPanel p_south_top;
	JPanel p_south_bottom;

	ImageIcon icon;
	String[] imageName = { "BeautyImage.png", "CookingImage.png", "EducationImage.png", "ExerciseImage.png",
			"LanguageImage.PNG", "InteriorImage.png", "LessonImage.PNG", "ProgrammingImage.png" };
	String[] titleName = { "뷰티", "요리", "교육", "운동", "외국어", "인테리어", "레슨", "프로그래밍" };
	
	JLabel[] la_title = new JLabel[imageName.length]; // 이미지 타이틀 붙을 라벨
	JPanel[] icon_card = new JPanel[imageName.length];// 아이콘과 타이틀 같이붙일 패널
	JPanel[] p_icon = new JPanel[8]; // icon 패널
	
	String[] simage = {"private_lesson.png","logo.png","english_speaking.png", "wedding.png", "makeup.png", "wedding_picture.png" };

	public ArrayList<Integer> seq= new ArrayList<Integer>();		

	int i;
	int n = 0;
	Font customFont; // 타이틀 크기 20
	Font customFont2; // 아이콘 타이틀 크기15

	Image[] img = new Image[imageName.length];
	Image img2;
	int index = 0;

	Thread t;
	float opacity = 1.0f;
	float opacity_control = 0.01f;
	boolean flag = true;

	Elements elements;
	Document doc;

	public String m_name, m_top_interests, m_sub_interests, m_introduce, m_image, m_matching_count;
	ArrayList<CardItem> itemList;// 생성된 멤버(고수학생)을 담게될리스트

	private ArrayList<Integer> random = new ArrayList<Integer>();

	public Home(SoomcoMain soomcoMain) {
		super(soomcoMain);
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		p_content = new JPanel();// 헤더 푸터 빼고 센터 담을 패널
		p_gosu = new JPanel(); //
		p_location = new JPanel(); // 고수 카드 올라갈 패널
		p_maga = new JPanel();
		p_center_top = new JPanel();
		p_south_top = new JPanel();
		p_south_bottom = new JPanel();

		p_north_west = new JPanel();
		p_north_center = new JPanel();
		p_north_east=new JPanel();

		p_center_top.setPreferredSize(new Dimension(soomcoMain.WIDTH, soomcoMain.HEIGHT/3-220));
		p_center_top.setBackground(Color.white);
		
		p_south_top.setLayout(new BorderLayout());
		p_south_top.setPreferredSize(new Dimension(soomcoMain.WIDTH, soomcoMain.HEIGHT/3-220));
		p_south_top.setBackground(Color.white);
		
		p_south_bottom.setPreferredSize(new Dimension(soomcoMain.WIDTH, 380));
		p_south_bottom.setBackground(Color.white);

		p_north.setLayout(new GridLayout(1, 2));
		p_north.setPreferredSize(new Dimension(soomcoMain.WIDTH, 500));
		p_north.setBackground(Color.white);

		p_center.setLayout(new BorderLayout());
		p_center.setPreferredSize(new Dimension(soomcoMain.WIDTH, 280));
		p_center.setBackground(Color.white);

		p_south.setLayout(new BorderLayout());
		p_south.setPreferredSize(new Dimension(soomcoMain.WIDTH, 450));
		p_south.setBackground(Color.white);

		p_content.setLayout(new GridLayout(3, 1));
		p_content.setPreferredSize(new Dimension(soomcoMain.WIDTH+200, soomcoMain.HEIGHT+200));
		p_content.setBackground(Color.white);

		p_gosu.setPreferredSize(new Dimension(soomcoMain.WIDTH, soomcoMain.HEIGHT/3-220));
		p_gosu.setBackground(Color.WHITE);
		
		p_maga.setPreferredSize(new Dimension(200, soomcoMain.HEIGHT/3-220));
		p_maga.setBackground(Color.WHITE);

		p_totalIcon = new JPanel(); // 아이콘 카드를 올릴 패널
		
		p_totalIcon.setLayout(new BorderLayout());
		p_north_west.setPreferredSize(new Dimension(soomcoMain.WIDTH/2-370,300));
		p_north_west.setBackground(Color.WHITE);
		p_totalIcon.add(p_north_west,BorderLayout.WEST);
		
		p_north_center.setPreferredSize(new Dimension(soomcoMain.WIDTH/2-350,300));
		p_north_center.setBackground(Color.WHITE);
		p_totalIcon.add(p_north_center,BorderLayout.CENTER);
		
		p_north_east.setPreferredSize(new Dimension(soomcoMain.WIDTH/2-180,300));
		p_north_east.setBackground(Color.WHITE);
		p_totalIcon.add(p_north_east,BorderLayout.EAST);
		
		
		p_totalIcon.setPreferredSize(new Dimension(soomcoMain.WIDTH/2, 300));
		p_totalIcon.setBackground(Color.white);

		customFont = this.setTitleFont(25);
		customFont2 = this.setTitleFont(15);

		for (int i = 0; i < 4; i++) {
			String path = "res/Magazine/" + simage[i];
			icon = ImageUtil.getIcon(this.getClass(), path, 300, 150);
			img2 = icon.getImage(); // 숨코메인의 Image 배열에 없음. 넣어야함
			getSoomcoMain().sImage.add(img2);
		}

		for (i = 0; i < imageName.length; i++) {
			String path = "res/HomeImage/" + imageName[i];
			icon = ImageUtil.getIcon(this.getClass(), path, 500, 340);
			img[i] = icon.getImage();
		}
		
		p_north_center.setLayout(new GridLayout(4,1));
		p_north_east.setLayout(new GridLayout(4,1));
		
		//0,2,4,6  = center  
		//1,3,5,7  = east
		for (i = 0; i < imageName.length; i++) {
				
			p_icon[i] = new JPanel();
			p_icon[i].add(soomcoMain.theme[i]);
			p_icon[i].setBackground(Color.white);
			icon_card[i] = new JPanel(); // 아이콘,라벨 같이 올릴 카드
			icon_card[i].add(p_icon[i]);
			icon_card[i].setBackground(Color.white);
			
			la_title[i] = new JLabel(titleName[i], SwingConstants.CENTER); // 라벨에 타이틀 넣기
			la_title[i].setPreferredSize(new Dimension(70, 50));
			icon_card[i].add(la_title[i]);
			icon_card[i].setPreferredSize(new Dimension(70, 50));
			la_title[i].setFont(customFont2);
			la_title[i].setBackground(Color.WHITE);
			
			if(i%2==0) {
				p_north_center.add(icon_card[i]);				
			}else if(i%2==1){
				p_north_east.add(icon_card[i]);
			}
		}
		

		getSoomcoMain().sURL = getSoomCoXML("link", "https");

		for (int j = 0; j < 3; j++) { // 매거진 패널에 3개 카드 생성 후 부착 및 UI업데이트
			getCreateItem(p_south_bottom, 288, 160, j);
			p_content.updateUI();
		}

		p_north.add(p_totalIcon);

		// 쓰레드 그려질 패널
		p_image = new JPanel() {
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				g2.drawImage(img[index], 0, 0, null);
			}
		};

		p_north.add(p_image);
		p_content.add(p_north);
		p_content.add(p_center);
		
		la_location_title = new JLabel("딱! 맞는 숨은고수를 소개합니다", SwingConstants.CENTER);
		la_magazine_title = new JLabel("숨은고수 매거진", SwingConstants.CENTER);

		la_location_title.setFont(customFont);
		la_magazine_title.setFont(customFont);

		la_location_title.setPreferredSize(new Dimension(400, 40));
		la_magazine_title.setPreferredSize(new Dimension(360, 40));

		p_gosu.add(la_location_title);
		
		p_location.setPreferredSize(new Dimension(SoomcoMain.WIDTH, 180));
		p_location.setBackground(Color.white);

		p_center_top.setLayout(new BorderLayout());
		p_center_top.add(p_gosu, BorderLayout.CENTER);
		p_center.add(p_center_top, BorderLayout.NORTH);
		p_center.add(p_location, BorderLayout.CENTER);

		p_maga.add(la_magazine_title);	
		p_south_top.add(p_maga, BorderLayout.CENTER);

		p_south.add(p_south_top, BorderLayout.NORTH);
		p_south.add(p_south_bottom, BorderLayout.CENTER);

		p_content.add(p_south);

		t = new Thread() {
			public void run() {
				try {
					while (true) {
						if (opacity < 0.001 || opacity > 0.991) {
							opacity_control *= -1.0;
							if (opacity > 0.991)
								Thread.sleep(1000);
						}
						if (opacity < 0.001) {
							if (index == imageName.length - 1) {
								index = -1;
							}
							index++;
						}
						opacity += opacity_control;
						// System.out.println(opacity);
						repaint();
						Thread.sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

		// 스타일적용
		p_image.setPreferredSize(new Dimension(SoomcoMain.WIDTH - 400, SoomcoMain.HEIGHT - 460));// *******
		add(p_content);
		p_content.updateUI();
		setBackground(Color.white);
		setPreferredSize(new Dimension(SoomcoMain.WIDTH+200, SoomcoMain.HEIGHT+500));
		
		//고수,초보 로그인시 조건에 맞게 카드뿌려지게하기 
	      callMemberCard(null);
	}

	// SoomGo xml을 JSoup 라이브러리를 이용하여 메거진 URL 얻어오기
	public ArrayList<String> getSoomCoXML(String select, String indexOf) { // Home 생성자 마지막 부분에서 호출한다
		ArrayList<String> list = new ArrayList<String>(); // 매서드 반환값인 url 을 모은 ArrayList
		elements = null; // 초기화
		doc = null; // 초기화
		try {
			doc = Jsoup.connect("https://rss.blog.naver.com/brave_mobile_mkt.xml").get(); // 해당 url에 있는 text를 문서화 함
			System.out.println(doc.title()); // 최상위 title <![CDATA[ 숨고 : 숨은고수 공식블로그 ]]>

			elements = doc.select("item").select(select); // url만을 추출하기 위해서 문서내의 <item> 태그중 매개변수로 받은 태그인 <link>요소의 내용들을
															// 갖는 객체
			int urlstart = 0, urlend = 0; // String.indexOf를 위해 시작점~끝나는 지점을 갖는 변수

			Elements el = doc.select("item title"); // <title>은 <link> 태그와는 달리 시작점, 끝점이 다소 난해하여 일회성으로만 사용
			int teststart = 0, testend = 0; // 위 urlstart, end와 같음

			String s_title = el.toString(); // <title>은 태그 내의 내용으로는 규칙을 찾을 수 없으므로, <title> </title>을 포함한 모든 내용을 뽑아옴

			while (true) {
				teststart = s_title.indexOf("<title>", testend); // <title>내용을 문서 내의 'testend'위치부터 찾기 시작하여 찾았다면 해당 위치값을,
																	// 못찾았다면 -1을 반환 (-1 = 문서끝까지 찾아봤지만 해당 내용은 없다)
				testend = s_title.indexOf("</title>", teststart + 1); // </title>내용을 문서 내의 'teststart'위치부터 찾기 시작함

				if ((teststart == -1 || testend == -1)) // 둘 중 하나라도 -1이 반환된다면 문서를 다 찾아보았지만 더 이상 없었음.. == 무한루프 종료
					break;

				System.out.print("testStart : " + teststart + "\ttestEnd : " + testend + "\n"); // <title>시작점 ~
																								// </title>끝점 위치
				System.out.println(
						"==>   " + s_title.substring(teststart + 16, testend - 3) + "\t ▶Success Insert String !\n");
				// <title>태그는 사실 '<title><![CDATA['로 시작하기 때문에 해당 자리수 만큼 이동한 뒤의 내용이 진짜 title이다 즉
				// '<'에서 '[' 까지 16자리수를 차지하기 때문에 그 뒤부터 추출해야함
				// end도 ]]>가 3자리이므로 그것보다 앞 3자리까지만 추출해야함

				getSoomcoMain().sTitle.add(s_title.substring(teststart + 16, testend - 3)); // 진정한 title 내용이 list에 저장
			}

			// 위와 같은내용..

			while (true) {
				urlstart = elements.text().indexOf(indexOf, urlend);
				urlend = elements.text().indexOf(indexOf, urlstart + 1);
				if ((urlstart == -1 || urlend == -1))
					break;
				System.out.print("urlStart : " + urlstart + "\turlEnd : " + urlend + "\n");
				System.out.println("==>   " + elements.text().substring(urlstart, urlend) + "\t ▶Success Insert "
						+ select + " !\n");
				list.add(elements.text().substring(urlstart, urlend - 1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 등록되어있는 멤버 수 얻어오기
	public double getMemberCount() {
		PreparedStatement pstmt = null;
		double count = 0;
		ResultSet rs = null;

		String sql = "select count(m_seq) from member";
		try {
			pstmt = getSoomcoMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count(m_seq)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getSoomcoMain().getDbManager().close(pstmt);
		}
		return count;
	}
	
	public ArrayList<Integer> getRandom() {
		return random;
	}

	public void setRandom(ArrayList<Integer> random) {
		this.random = random;
	}

	//랜덤과 조건에 맞게 카드를 뿌려질 라스트 메서드 ! 
	   public void callMemberCard(String card_Position) {
	     getIfSeq(card_Position);//로그인회원의 포지션을 넣기 
	     getRandomSeq();
	     
	     for (int i =0; i<random.size(); i++) {
	    	 if (i == 7) break;	          
	    	 getMemberCardList(random, i, card_Position);
	    	 p_content.updateUI();
	     }
	   }
	   
	 //고수&초보 조건이 있는 !! m_position=? 인 m_seq를 뽑는다 
	   public ArrayList<Integer> getIfSeq(String card_position) {
	      PreparedStatement pstmt=null;
	      ResultSet rs =null;
	      if(card_position==null || card_position.equals("초보")) {         
	         card_position="고수";//카드에 뿌려질 사람이 고수      
	      }else {
	         card_position="초보";
	      }
	      String sql="select m_seq, m_position from member where m_status='true' and m_position='"+card_position+"'";
	      try {
	         pstmt = getSoomcoMain().getCon().prepareStatement(sql);
	         rs = pstmt.executeQuery();
	       
	         while(rs.next()) {
	            seq.add(rs.getInt("m_seq"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         getSoomcoMain().getDbManager().close(pstmt, rs);
	      }
	      return seq; 
	   }
	   
	   //seq ArrayList에서 랜덤으로 불러오기 
	   public ArrayList<Integer> getRandomSeq() {
	      int get_Seq = 0;
	      int n = seq.size();
	      for(int i=0; i<n;i++) {
	         Collections.shuffle(seq);
	         get_Seq= seq.get(0);
	         random.add(get_Seq);
	         seq.remove(0);
	      }
	      return random;
	   }

	   //멤버 가져오기 _로그인되지 않은 상태에서 
	   public void getMemberCardList(ArrayList<Integer> random,int index,String card_position) {
	         PreparedStatement pstmt=null;
	         ResultSet rs =null; 
	         String sql=null;
	         
	         try {
	            if(card_position==null) {
	                sql = "select m_name, m_location, m_top_interests, m_sub_interests, m_position,m_introduce, m_image, m_matching_count from member where M_SEQ=?";
	                pstmt = getSoomcoMain().getCon().prepareStatement(sql);
	                pstmt.setInt(1, (int)random.get(index));// 랜덤 m_seq
	               
	            } else if (card_position.equals("초보")) {         
	               String getLocationName=getSoomcoMain().getSoomcoMember().getLocation();
	               sql = "select m_name, m_location, m_top_interests, m_sub_interests, m_position, m_introduce, m_image, m_matching_count from member where m_seq=? and m_location=?";
	               pstmt = getSoomcoMain().getCon().prepareStatement(sql);
	                pstmt.setInt(1, (int) random.get(index));// 랜덤 m_seq
	                pstmt.setString(2, getLocationName);
	               
	            }else if (card_position.equals("고수")){
	               String getInterestsName=getSoomcoMain().getSoomcoMember().getTop_interests();
	                sql = "select m_name, m_location, m_top_interests, m_sub_interests, m_position, m_introduce, m_image, m_matching_count from member where M_SEQ=? and M_TOP_INTERESTS=?";
	                pstmt = getSoomcoMain().getCon().prepareStatement(sql);
	                pstmt.setInt(1, (int) random.get(index));// 랜덤 m_seq
	                pstmt.setString(2, getInterestsName);// 
	            }
	            rs = pstmt.executeQuery();
	            itemList = new ArrayList<CardItem>();
	            
	            while (rs.next()) {//레코드가 있는 만큼 
	              SoomcoMember sm = new SoomcoMember(); 
	              
	               sm.setName(rs.getString("m_name"));
	               sm.setLocation(rs.getString("m_location"));
	               sm.setTop_interests(rs.getString("m_top_interests"));
	               sm.setSub_interests(rs.getString("m_sub_interests"));
	               sm.setPosition(rs.getString("m_position"));
	               sm.setIntroduce(rs.getString("m_introduce"));
	               sm.setImage(rs.getNString("m_image"));
	               sm.setMatching_count(Integer.parseInt(rs.getString("m_matching_count")));

	               itemList.add(getCreateItem(sm));
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }finally {
	            getSoomcoMain().getDbManager().close(pstmt, rs);
	         }
	      }

	 //멤버 카드 생성하기 
	   public CardItem getCreateItem(SoomcoMember sm) {
	      CardItem item = new CardItem(this, sm, 150, 220);
	      p_location.add(item);
	      return item;//생성후 반환까지하자 
	   }
	
	public CardItem getCreateItem(JPanel p, int width, int hegiht, int j) {
		CardItem item = null;
		item = new CardItem(this, width, hegiht, j);
		p.add(item);
		return item;// 생성후 반환까지하자
	}
		

	// 웹사이트 팝업으로 띄우기(매거진_고수)
	public void popupBlog(String url) {
		try {
			System.out.println(url);
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}