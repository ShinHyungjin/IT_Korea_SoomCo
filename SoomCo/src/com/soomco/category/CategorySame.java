package com.soomco.category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;
import com.soomco.util.hover.HoverButton;

public class CategorySame extends Page {   
   JPanel p_content; //전체 패널
   JPanel p_navi;//오렌지색 바 영역
   JPanel p_north;// 카테고리아이콘 올라갈 영역
   JPanel p_center;// 인기서비스 라벨 올라갈 영역
   JPanel p_south;// 카테고리 이미지 패널 올라갈 영역
   JPanel p_bt; //아이콘 넣을 패널
   JPanel p_card; //카드 패널     
   JLabel la_service;//인기서비스 라벨
   public Font customFont;
   
   // 라인디자인
   LineBorder line = new LineBorder(Color.BLACK, 1, false);// 라인에 색 넣기
   Border bb = new SoftBevelBorder(SoftBevelBorder.RAISED);// 버튼처럼 올라와보이는 효과
   int index = 0;// 타이틀, 사진배열 인덱스
   ArrayList<CategoryCard> cardList; //카드가 담긴 ArrayList   
   

   public HoverButton[] theme = new HoverButton[8]; //HoverButton 클래스 배열 선언
   public SoomcoMain soomcoMain;
   
   //매개변수로 soomcoMain, 소카테고리 갯수, 카테고리 이름, 소카테고리 이미지, 소카테고리 이름 매개변수로 받음 
   public CategorySame(SoomcoMain soomcoMain, int sub_size, String name, ArrayList<String> sub_img, ArrayList<String> sub_name) {
      super(soomcoMain);
      this.soomcoMain=soomcoMain;
      // 폰트적용
      try {
         customFont = Font.createFont(Font.TRUETYPE_FONT,
               new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
         ge.registerFont(customFont);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (FontFormatException e) {
         e.printStackTrace();
      }
      
      //전체 패널
      p_content=new JPanel(); 
      //매개변수로 받은 서브카테고리 숫자가 6이면 height를 700으로 그렇지 않으면 1000으로 설정하는 삼항연산자
      p_content.setPreferredSize(new Dimension(soomcoMain.WIDTH-180, (sub_size/3)==2? 700:1000));
      p_content.setBackground(Color.white);
      
      //navigation 패널
      p_navi = new JPanel();
      p_navi.setBackground(Color.white);
      
      //이미지아이콘 버튼 붙일 패널
      p_bt = new JPanel(); // 버튼들 붙일패널
      p_bt.setPreferredSize(new Dimension(soomcoMain.WIDTH,100));
      p_bt.setBackground(Color.white);
      
      cardList = new ArrayList<>(); //카드들이 담길 ArrayList
      
      p_north = new JPanel(); // 카테고리아이콘 올라갈 영역
      p_center = new JPanel();// 인기서비스 라벨 올라갈 영역
      p_south = new JPanel();// 카테고리 이미지 패널 올라갈 영역
      
      //카드 생성(매개변수로 받은 카드 갯수 크기만큼)
      for (int i=0; i < sub_size; i++) {
         cardList.add(createCard(i, name, sub_img.get(i), sub_name.get(i)));
      }
      
      // 아이콘 버튼 생성
      for (int i = 0; i < 8; i++) {
         p_bt.add(soomcoMain.setButtonIcon(theme[i], i));//soomcoMain의 setButtonIcon함수를 통해 아이콘 버튼 생성후,패널 부착         
      }
    
      //디자인
      p_navi.setBackground(Color.ORANGE);
      p_navi.setPreferredSize(new Dimension(SoomcoMain.WIDTH-300, 20));

      p_north.setBackground(Color.ORANGE);
      p_north.add(p_navi);
      p_north.add(p_bt);

      // 인기서비스 패널, 라벨
      p_center.setPreferredSize(new Dimension(SoomcoMain.WIDTH, 50));
      p_center.setBackground(Color.WHITE);
      la_service = new JLabel("인기 서비스");
      
      la_service.setPreferredSize(new Dimension(100, 50));
      p_center.add(la_service);
      
      p_north.setPreferredSize(new Dimension(SoomcoMain.WIDTH, 120));
      p_content.add(p_north, BorderLayout.NORTH);
      p_content.add(p_center, BorderLayout.CENTER);

      // 카테고리 카드 붙이기
      p_content.add(p_south, BorderLayout.SOUTH);
      p_south.setPreferredSize(new Dimension(SoomcoMain.WIDTH-180, SoomcoMain.HEIGHT));
      //la_service.setBackground(Color.ORANGE);
      p_north.setBackground(Color.WHITE);
      p_south.setBackground(Color.WHITE);

      // 폰트적용, 폰트 디자인, 보더 디자인
      la_service.setFont(customFont);
      // la_service.setBorder(bb);
      
      add(p_content);
      //매개변수로 받은 서브카테고리 숫자가 6이면 height를 700으로 그렇지 않으면 1000으로 설정하는 삼항연산자
      setPreferredSize(new Dimension(soomcoMain.WIDTH-180, (sub_size/3)==2? 700:1000));
      
   }
   
   //현재 패널을 반환하는 게터
   public JPanel getPanel() {
      return this;
   }

   // 카테고리 카드 자체를 생성
   public CategoryCard createCard(int i, String name, String sub_img, String sub_name) {
      CategoryCard item = new CategoryCard(soomcoMain, name, sub_img, sub_name);
      p_south.add(item); // 남쪽패널에 카드 붙이기
      return item; // 생성후 반환
   }

}