package com.soomco.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Page extends JPanel{
   SoomcoMain soomcoMain;    
   Font customFont2;
    
   public SoomcoMain getSoomcoMain() {
      return soomcoMain;
   }
   
   public Page(SoomcoMain soomcomain) {
      this.soomcoMain=soomcomain;
      this.setPreferredSize(new Dimension(SoomcoMain.WIDTH, SoomcoMain.HEIGHT));
      setBackground(Color.WHITE);
   }
   
   //JTextField, JLabel 컴포넌트 Font 설정 메서드(오버로딩)
   public  void setTextBorder(JComponent textField ,String text,boolean isLabel) {
      try {
             //create the font to use. Specify the size!
             Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             //register the font
             ge.registerFont(customFont);
                          
             textField.setFont(customFont);//텍스트필드 폰트 설정
             //isLabel이 true면 Label이란 뜻,  JTextField면 border메서드 실행
             if(!isLabel) {
                textField.setBorder(BorderFactory.createTitledBorder(getBorder(), text , TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, customFont, new Color(253, 116, 31)));//보더 속성                      
             }
         } catch (IOException e) {
             e.printStackTrace();
         } catch(FontFormatException e) {
             e.printStackTrace();
         }
   }
   
   
   public  void setTextBorder(JComponent textField ,String text,int pass) {
      try {
             //create the font to use. Specify the size!
             Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             //register the font
             ge.registerFont(customFont);
                                       
             //isLabel이 true면 Label이란 뜻,  JTextField면 border메서드 실행
             if(pass==1) {
                textField.setBorder(BorderFactory.createTitledBorder(getBorder(), text , TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, customFont, new Color(253, 116, 31)));//보더 속성                      
             }
         } catch (IOException e) {
             e.printStackTrace();
         } catch(FontFormatException e) {
             e.printStackTrace();
         }
   }
   
   //Choice 컴포넌트 Font 설정 메서드(오버로딩)
   public void setTextBorder(Component textField ,String text) {
      try {
             //create the font to use. Specify the size!
             Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(20f);
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
             //register the font
             ge.registerFont(customFont);
                          
             textField.setFont(customFont);//텍스트필드 폰트 설정                         
         } catch (IOException e) {
             e.printStackTrace();
         } catch(FontFormatException e) {
             e.printStackTrace();
         }
   }
    
//글씨 크기만 다른 폰트 메서드
   public Font setTitleFont(float size) {
   try {
      customFont2 = Font.createFont(Font.TRUETYPE_FONT,
            new File("C:/Users/tjdal/workspace/java_workspace/SwingMall/font/Medium.ttf")).deriveFont(size);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
      ge.registerFont(customFont2);
   } catch (IOException e) {
      e.printStackTrace();
   } catch (FontFormatException e) {
      e.printStackTrace();
   }
   return customFont2;
   }
}