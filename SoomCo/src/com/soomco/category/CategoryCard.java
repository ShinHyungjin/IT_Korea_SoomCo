package com.soomco.category;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import com.soomco.Esimate.EstimateForm;
import com.soomco.chat.ChatMain;
import com.soomco.main.SoomcoMain;
import com.soomco.util.image.ImageUtil;

//카드 자체를 생성하는 거푸집
//뷰티
public class CategoryCard extends JPanel {
	ChatMain chatMain; //chatMain 멤버변수 선언
	SoomcoMain soomcoMain;//soomcoMain 멤버변수 선언	
	JPanel p_card;
	JPanel p_image;
	JLabel la_title;

	int index = 0;// 타이틀배열 인덱스
	public String sub_name;//소카테고리 이름 변수
		
	Image img2;//이미지 변수

	// 라인디자인
	LineBorder line = new LineBorder(Color.black, 1, false);//보더 라인
	Border bb = new SoftBevelBorder(SoftBevelBorder.RAISED);// 버튼처럼 올라와보이는 효과

	Font customFont;

	//매개변수로 soomcoMain, CategoryCard클래스 생성하는 관심사(대) 이름, 관심사(소) 사진,이름을 받음 
	public CategoryCard(SoomcoMain soomcoMain, String name, String sub_img, String sub_name) {
		this.soomcoMain = soomcoMain;				
		this.sub_name = sub_name; //클릭한 소카테고리 이름
		LineBorder line1 = new LineBorder(Color.ORANGE, 3, false); //마우스 올려놓으면 orange보더 
		LineBorder line2 = new LineBorder(Color.black, 1, false); //마우스 내려놓으면 black보더
		try {
			//커스텀 폰트 초기화
			customFont = Font
					.createFont(Font.TRUETYPE_FONT,
							new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf"))
					.deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		//해당 소카테고리 이미지 얻기
		img2 = (ImageUtil.getIcon(this.getClass(), "res/" + name + "/" + sub_img, 220, 170).getImage());
		p_card = new JPanel(); // 이미지, 라벨 같이 넣을 패널
		p_card.setBackground(Color.white);
		la_title = new JLabel(sub_name, SwingConstants.CENTER);
		p_card.setBorder(line);
		// 이미지 생성
		p_image = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img2, 0, 0, this);
			}
		};

		p_card.add(p_image);
		p_card.add(la_title);
		add(p_card);
		
		setBackground(Color.white);

		p_card.setPreferredSize(new Dimension(230, 220));
		p_image.setPreferredSize(new Dimension(220, 170));
		la_title.setPreferredSize(new Dimension(200, 50));
		
		//이미지 클릭시 견적서 생성
		p_image.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (soomcoMain.getSoomcoMember().getId() == null) {// 로그인 안한 상태면 로그인 요구
					JOptionPane.showMessageDialog(soomcoMain, "로그인을 해주세요.");
					soomcoMain.showPage(SoomcoMain.LOGIN);
				} else if (soomcoMain.getSoomcoMember().getPosition().equals("고수")) {// 로그인 유저가 고수면 견적서 안띄움
					JOptionPane.showMessageDialog(soomcoMain, "초보만 견적서 열람이 가능합니다.");
				} else {// 로그인 유저가 초보일때만 견적서 띄움
					JFrame est = new EstimateForm(CategoryCard.this.soomcoMain, chatMain, sub_name);
				}
			}
		});

		// 폰트적용 및 보더 디자인
		la_title.setFont(customFont);
		// la_title.setBorder(bb);

		// 폰트적용 및 보더 디자인
		la_title.setFont(customFont);

		// 카드에 마우스 올리놓은 상태에 나타나는 효과
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				p_card.setBorder(line1);
			}
		});
		// 카드에 마우스 내려놓은 상태에 나타나는 효과
		this.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				p_card.setBorder(line2);
			}
		});

	}
}