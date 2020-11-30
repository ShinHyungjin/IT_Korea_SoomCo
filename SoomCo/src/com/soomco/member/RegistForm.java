package com.soomco.member;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.PSource;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;

public class RegistForm extends Page{
   JPanel p_content; //로그인폼과 동일한 목적으로 ...         
   JTextField t_id; //ID
   JPasswordField t_pass; //비밀번호
   JTextField t_name;//이름
   JComboBox<String> ch_location;//지역   
   JTextField t_mail;//이메일 앞부분
   JLabel la_anotation;//@
   JComboBox<String> ch_email;//이메일 뒷부분
   JComboBox<String> ch_gender;//성별
   JComboBox<String> ch_position;//선생 or 학생
   JTextArea t_introduce;//자기 소개
   JScrollPane scroll;//자기 소개 스크롤
   JTextField t_image;//프로필 사진 경로
   JComboBox<String> ch_interests1;//관심사(대)   
   JComboBox<String> ch_interests2;//관심사(소)
   JButton bt_regist;//회원가입 버튼 
   
   String[] area = {"선택하기", "강원도","경기도","경상남도","경상북도","광주광역시","대구광역시","대전광역시","부산광역시",
         "서울특별시","세종특별자치시","울산광역시","인천광역시","전라남도","전라북도","제주도","충청남도","충청북도"};
   
   String[] emailForm = {"선택하기", "naver.com","hanmail.net","gmail.com","nate.com"};
   
   String[] sex = {"선택하기", "남자","여자"};
   String[] position = {"선택하기", "초보","고수"};
   String[] interest_top = {"선택하기", "Programming", "Lesson", "Interior", "Beauty", "Cooking", "Education", "Language", "Exercise" };
   
   String[] Programming= {"C++","C","Java","Python","HTML","JSP","React.js","Node.JS","Android"};
   String[] Exercise= {"요가", "필라테스", "헬스", "수영", "골프", "스키"};
   String[] Lesson= {"보컬","드럼","기타","연기","댄스","미술"};
   String[] Interior= {"실내","페인트","욕실","타일","도배","조명"};
   String[] Cooking= {"한식","중식","일식","양식","퓨전","제과제빵"};
   String[] Language= {"일본어","중국어","태국어","러시아어","이탈리아어","독일어","스페인어","불어"};
   String[] Education= {"영어","수학","국어","과학","한국사","한문","토익"};
   String[] Beauty= {"메이크업","미용","네일아트","특수분장","웨딩","문신"};
   
   public RegistForm(SoomcoMain SoomcoMain) {
      super(SoomcoMain);      
      p_content = new JPanel();
      t_id = new JTextField();
      t_pass = new JPasswordField();
      t_name = new JTextField();
      ch_location = new JComboBox<String>();
      t_mail = new JTextField();
      la_anotation = new JLabel("@");
      ch_email = new JComboBox<String>();
      ch_gender = new JComboBox<String>();
      ch_position = new JComboBox<String>();
      t_introduce = new JTextArea();
      scroll = new JScrollPane(t_introduce);
      t_image = new JTextField();
      ch_interests1 = new JComboBox<String>();
      ch_interests2 = new JComboBox<String>();
      bt_regist = new JButton("회원등록");

      
      //스타일 
      p_content.setPreferredSize(new Dimension(400, 900));
      p_content.setBackground(Color.WHITE);
      ch_location.setBackground(Color.WHITE);
      ch_email.setBackground(Color.WHITE);
      ch_gender.setBackground(Color.WHITE);
      ch_position.setBackground(Color.WHITE);
      ch_interests1.setBackground(Color.WHITE);
      ch_interests2.setBackground(Color.WHITE);
      t_introduce.setBackground(Color.WHITE);
      bt_regist.setBackground(new Color(253, 116, 31));
      bt_regist.setForeground(Color.WHITE);
      bt_regist.setFont(new Font("굴림", Font.BOLD, 20));
      bt_regist.setBorderPainted(false);
      
      
      //폰트 && 보더 설정
      setTextBorder(t_id, "ID", false);
      setTextBorder(t_pass, "Password", 1);
      setTextBorder(t_name, "이름", false);
      setTextBorder(ch_location, "지역",false);
      setTextBorder(t_mail, "이메일", false);
      setTextBorder(la_anotation, "@",true);
      setTextBorder(ch_email, "선택",false);
      setTextBorder(ch_gender, "성별",false);
      setTextBorder(ch_position, "누구세요?",false);
      setTextBorder(scroll, "자기소개",false);
      setTextBorder(t_image, "사진경로", false);
      setTextBorder(ch_interests1, "관심사(대)",false);
      setTextBorder(ch_interests2, "관심사(소)",false);
      
      //사이즈 설정
      Dimension d = new Dimension(380,50);
      t_id.setPreferredSize(d);      
      t_pass.setPreferredSize(d);
      t_name.setPreferredSize(d);
      ch_location.setPreferredSize(new Dimension(380,60));
      t_mail.setPreferredSize(new Dimension(180,50));
      la_anotation.setPreferredSize(new Dimension(30,50));
      ch_email.setPreferredSize(new Dimension(170,60));
      ch_gender.setPreferredSize(new Dimension(180,60));
      ch_position.setPreferredSize(new Dimension(180,60));
      scroll.setPreferredSize(new Dimension(380,200));
      t_image.setPreferredSize(d);
      ch_interests1.setPreferredSize(new Dimension(180,60));
      ch_interests2.setPreferredSize(new Dimension(180,60));
      bt_regist.setPreferredSize(new Dimension(380,40));
      
      
      //지역
      for(int i=0; i<area.length; i++) ch_location.addItem(area[i]);               
      //이메일
      for(int i=0; i<emailForm.length; i++) ch_email.addItem(emailForm[i]);         
      //성별
      for(int i=0; i<sex.length; i++) ch_gender.addItem(sex[i]);
      //포지션
      for(int i=0; i<position.length; i++) ch_position.addItem(position[i]);
      //관심사(대)
      for(int i=0; i<interest_top.length; i++) ch_interests1.addItem(interest_top[i]);      
      
      //조립
      p_content.add(t_id);
      p_content.add(t_pass);
      p_content.add(t_name);
      p_content.add(ch_location);
      p_content.add(t_mail);
      p_content.add(la_anotation);
      p_content.add(ch_email);
      p_content.add(ch_gender);
      p_content.add(ch_position);
      p_content.add(scroll);
      p_content.add(t_image);
      p_content.add(ch_interests1);
      p_content.add(ch_interests2);   

      p_content.add(bt_regist);      
      add(p_content);
      
      ch_interests1.addItemListener(new ItemListener() {         
         @Override
         public void itemStateChanged(ItemEvent e) {
            ch_interests2.removeAllItems();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                   Object item = e.getItem();                   
                   if(item=="Programming") getInterestSub(item, "Programming", Programming);                   
                   else if(item=="Lesson") getInterestSub(item, "Lesson", Lesson);                   
                   else if(item=="Interior") getInterestSub(item, "Interior", Interior);
                   else if(item=="Beauty") getInterestSub(item, "Beauty", Beauty);
                   else if(item=="Cooking") getInterestSub(item, "Cooking", Cooking);
                   else if(item=="Education") getInterestSub(item, "Education", Education);
                   else if(item=="Language") getInterestSub(item, "Language", Language);
                   else if(item=="Exercise") getInterestSub(item, "Exercise", Exercise);                                       
                }            
         }         
      });
      
      bt_regist.addActionListener((e)-> {
         if(checkId(t_id.getText())) {
            JOptionPane.showMessageDialog(RegistForm.this, "중복된 ID입니다.\n다른 ID를 사용하세요.");
         }else {
            SoomcoMember vo = new SoomcoMember();// Empty 상태
            vo.setId(t_id.getText());
            vo.setPassword(new String(t_pass.getPassword()));
            vo.setName(t_name.getText());
            vo.setLocation(ch_location.getSelectedItem().toString());
            vo.setMail(t_mail.getText()+"@"+ch_email.getSelectedItem().toString());
            vo.setGender(ch_gender.getSelectedItem().toString());
            vo.setPosition(ch_position.getSelectedItem().toString());
            vo.setIntroduce(t_introduce.getText());
            vo.setImage(t_image.getText());
            vo.setTop_interests((ch_interests1.getSelectedItem().toString()));
            if(ch_interests2.getSelectedItem()==null){
               JOptionPane.showMessageDialog(this, "정보을 입력해주세요.");
            }else {
            vo.setSub_interests((ch_interests2.getSelectedItem().toString()));                        
            }
            int result = regist(vo);//중복 조회
            if(result==0) {
               JOptionPane.showMessageDialog(RegistForm.this, "등록실패");
            }else {
               JOptionPane.showMessageDialog(RegistForm.this, "회원가입 성공");
               getSoomcoMain().showPage(SoomcoMain.LOGIN);
            }
         }
      });
      
   }
   
   //관심사(소) 채우는 함수
   public void getInterestSub(Object item, String name, String arr[]) {      
      if(item==name) {
           for(int i=0; i<arr.length; i++) {
              ch_interests2.addItem(arr[i]);
           }
        }
   };
   
   //회원 존재여부 체크 --> true면 중복 존재, 회원가입 시키면 안됨
   public boolean checkId(String mid) {
      PreparedStatement pstmt=null;
      ResultSet rs = null;
      boolean flag=false;
      
      String sql="select * from member where m_id=?";
      
      try {
         pstmt=getSoomcoMain().getCon().prepareStatement(sql);
         pstmt.setString(1, mid);
         rs=pstmt.executeQuery();
         flag=rs.next();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         getSoomcoMain().getDbManager().close(pstmt,rs);
      }
      return flag;
   }
   
   //회원 등록
   private int regist(SoomcoMember SoomcoMember) {
      PreparedStatement pstmt=null;
      int result = 0;//메서드의 지역변수는 컴파일러가 초기화하지 않으므로, 반드시 개발자가 초기화
      
      String sql="insert into member(M_SEQ, M_ID, M_PASSWORD ,M_NAME ,M_LOCATION ,M_MAIL ,M_GENDER ,M_POSITION ,M_INTRODUCE ,M_IMAGE ,M_TOP_INTERESTS, M_SUB_INTERESTS )";
      sql+= " values(seq_member.nextval,?,?,?,?,?,?,?,?,?,?,?)";
      try {
         pstmt=getSoomcoMain().getCon().prepareStatement(sql);
         //바인드 변수 대입
         pstmt.setString(1, SoomcoMember.getId());
         pstmt.setString(2, SoomcoMember.getPassword());
         pstmt.setString(3, SoomcoMember.getName());
         pstmt.setString(4, SoomcoMember.getLocation());
         pstmt.setString(5, SoomcoMember.getMail());
         pstmt.setString(6, SoomcoMember.getGender());
         pstmt.setString(7, SoomcoMember.getPosition());
         pstmt.setString(8, SoomcoMember.getIntroduce());
         pstmt.setString(9, SoomcoMember.getImage());
         pstmt.setString(10, SoomcoMember.getTop_interests());
         pstmt.setString(11, SoomcoMember.getSub_interests());         
         
         //쿼리 수행
         result  = pstmt.executeUpdate();//쿼리 수행
         t_id.setText("");
         t_pass.setText("");
         t_name.setText(""); 
         ch_location.setSelectedIndex(0);
         t_mail.setText("");         
         ch_email.setSelectedIndex(0);
         ch_gender.setSelectedIndex(0);
         ch_position.setSelectedIndex(0);
         t_introduce.setText("");
         t_image.setText("");
         ch_interests1.setSelectedIndex(0);
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         getSoomcoMain().getDbManager().close(pstmt);
      }
      return result;
   }
   
}