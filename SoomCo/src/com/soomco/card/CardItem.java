//멤버한명을 카드로 표현 

package com.soomco.card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.soomco.home.Home;
import com.soomco.member.SoomcoMember;
import com.soomco.util.hover.HoverPanel;

public class CardItem extends HoverPanel {
	JPanel p_can; // 상품 이미지
	JPanel p_info; // 라벨들이 위치할 그리드 패널(3,1) 이름, 전공, 경력..?
	JLabel la_name, la_location_position, la_interests, la_career;//이름,지역,관심사,자기소개
	JLabel la_matching_count;// 고용횟수	
	Image img;
	Home home;
	SoomcoMember sm;
	Font customFont;

	public CardItem(Home home, SoomcoMember sm, int width, int height) { //home에서 매개변수로 home,soomcoMember(VO),길이,높이를 넣어서 생성
		this.home = home; //전달받은 Home
		this.sm = sm;//전달받은soomcoMember(VO)

		URL url;
		try {
			url = new URL(sm.getImage());//soomcoMember(VO)에서 이미지 경로를 얻어옴(회원가입할때 명시한 프로필사진 경로)
			img = ImageIO.read(url);//이미지경로를 넣어서 만든 Image클래스 변수
			img = img.getScaledInstance(width, height / 2, Image.SCALE_SMOOTH);//이미지 사이즈 조정
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//이미지를 그리는 캔퍼스(프로필 사진)
		p_can = new JPanel() {
			public void paint(Graphics g) {
				// g.setColor(new Color(177, 194, 148));
				// g.fillRect(0, 0, width, height/2);
				g.drawImage(img, 0, 0, p_can);
			}
		};
		  
		  la_interests = new JLabel(" "+sm.getTop_interests()+" "+ sm.getSub_interests());//soomcoMember(VO) 에서 관심사(대) 관심사(소) 카테고리 가져와서 연결해 적용
	      la_location_position = new JLabel(" "+sm.getLocation()+"    "+sm.getPosition());//soomcoMember(VO) 에서 지역 이름,고수or초보인지 가져와서 적용
	      la_name = new JLabel(" "+sm.getName());//soomcoMember(VO)에서 이름 가져와서 적용
	      la_career = new JLabel(" "+sm.getIntroduce());////soomcoMember(VO) 에서 자기소개(경력 등) 가져와서 적용
	      la_matching_count = new JLabel(" 고용횟수 " + Integer.toString(sm.getMatching_count()) + "회");//soomcoMember(VO) 에서 고용횟수 가져와서 적용)

	      // 스타일
	      setPreferredSize(new Dimension(width, height));	      
	      p_can.setPreferredSize(new Dimension(width, height / 2));
	      
	      //사이즈 조절(높이는 전체 높이에서 2분의 1로 나눈 후 다시 8로 나눈 높이)
	      la_interests.setPreferredSize(new Dimension(width, (height / 2)/6 ));
	      la_location_position.setPreferredSize(new Dimension(width, (height / 2) / 6));
	      la_name.setPreferredSize(new Dimension(width, (height / 2) / 8));
	      la_career.setPreferredSize(new Dimension(width, (height / 2) / 8));
	      la_matching_count.setPreferredSize(new Dimension(width, (height / 2) / 8));
	      
	      //폰트 설정
	      la_interests.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	      la_location_position.setFont(new Font("맑은 고딕", Font.BOLD, 12));
	      la_name.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
	      la_career.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
	      la_matching_count.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		
		//패널에 부착
		add(p_can);
		add(la_interests);
		add(la_location_position);
		add(la_name);
		add(la_career);
		add(la_matching_count);
		setBackground(Color.white);
	}
	
	//매거진에서 사용하기 위한 생성자 오버로딩
	public CardItem(Home home, int width, int height, int j) {
		this.home = home;
		
		//Home에서 정적으로 추가한 매거진 이미지ArrayList 요소 얻어서 크기 조정
		img = home.getSoomcoMain().sImage.get(j).getScaledInstance(width, height, Image.SCALE_SMOOTH);
		// 폰트적용
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT,
							new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf"))
					.deriveFont(18f);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(customFont);
		
		//이미지를 그리는 캔퍼스(매거진 사진)
		p_can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_can);
			}
		};
		
		
		String surl = home.getSoomcoMain().sURL.get(j); //크롤링으로 얻어온 매거진 랜덤 링크 얻어오기

		// 스타일
		setPreferredSize(new Dimension(290, 260));

		p_can.setPreferredSize(new Dimension(width, height));
		p_can.setBackground(Color.white);

		add(p_can);
		setBackground(Color.white);


		//매거진 타이틀 길이의 17만큼 나눈 숫자까지 반복
		for (int i = 0; i <= home.getSoomcoMain().sTitle.get(j).length() / 17; i++) {
			JLabel arr;
			//라벨 1개에 글자가 최대 17자까지만 들어갈 수 있음
			if (i == home.getSoomcoMain().sTitle.get(j).length() / 17) {
				arr = new JLabel(home.getSoomcoMain().sTitle.get(j).substring(i * 17,
						home.getSoomcoMain().sTitle.get(j).length()));
			} else {//17글자씩 뽑다가 17글자 이하인 나머지 글자 출력
				arr = new JLabel(home.getSoomcoMain().sTitle.get(j).substring(i * 17, (i + 1) * 17));
			}
			arr.setPreferredSize(new Dimension(width - 40, 18));
			arr.setFont(customFont);
			add(arr);
		}

		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				home.popupBlog(surl); //매거진 클릭시 크롤링한 매거진 홈페이지로 연결
			}
		});
	}
}
