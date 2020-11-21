package com.soomco.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.soomco.card.Card;
import com.soomco.card.CardVO;
import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;


public class ProductDetail extends Page{
   public JPanel p_content;//상세내용을 담게될 패널 
   JPanel p_can;//큰 상품 이미지 그려질 패널 
   JPanel p_option; // 옵션 선택 영역
   JLabel la_brand;
   JLabel la_product_name;
   JLabel la_price;
   
   Choice ch_color;
   Choice ch_size;
   
   JButton bt_buy;
   JButton bt_cart;
   
   private ProductVO vo;
   private Image img;
   
   //상세페이지 호출시 상품 1개의 정보는 vo에 그려질 이미지는 img로 전달받자 
   public ProductDetail(SoomcoMain SoomcoMain) {
      super(SoomcoMain);
      p_content = new JPanel();
      p_can = new JPanel() {
         public void paint(Graphics g) {
            g.drawImage(img, 0, 0, p_can);
         }
      };
      p_option = new JPanel();
      la_brand = new JLabel();
      la_product_name = new JLabel();
      la_price = new JLabel();
      
      ch_color = new Choice();
      ch_size = new Choice();
      
      //색상채우기 ,사이즈   (원래는 db에서 가져온..ㄴ ㅇㄴ)
      ch_color.add("red");
      ch_color.add("black");
      ch_color.add("white");
      
      ch_size.add("s");
      ch_size.add("m");
      ch_size.add("l");
      
      bt_buy = new JButton("구매하기");
      bt_cart = new JButton("장바구니");
      
      //스타일 
      p_content.setPreferredSize(new Dimension(SoomcoMain.WIDTH-100, SoomcoMain.HEIGHT-200));
//      p_content.setBackground(new Color(204, 133, 154));
      
      Dimension d = new Dimension(SoomcoMain.WIDTH/3,40);
      
      la_brand.setPreferredSize(d);
      la_product_name.setPreferredSize(d);
      la_price.setPreferredSize(d);
      bt_buy.setPreferredSize(new Dimension(200, 30));
      bt_cart.setPreferredSize(new Dimension(200, 30));
      
      ch_color.setPreferredSize(d);
      ch_size.setPreferredSize(d);
      
      
      la_brand.setFont(new Font("Verdana", Font.ITALIC, 40));
      la_product_name.setFont(new Font("Verdana", Font.ITALIC, 25));
      la_price.setFont(new Font("Verdana", Font.ITALIC, 25));
      bt_buy.setBackground(new Color(192, 228, 241));
      bt_cart.setBackground(new Color(172, 208, 192));
      //조립
      p_option.add(la_brand);
      p_option.add(la_product_name);
      p_option.add(la_price);
      p_option.add(ch_color);
      p_option.add(ch_size);
      p_option.add(bt_buy);
      p_option.add(bt_cart);
      
      
      p_content.setLayout(new GridLayout(1, 2));
      p_content.add(p_can);
      p_content.add(p_option);
      
      add(p_content);
      
      //장바구니 페이지 열기
      bt_cart.addActionListener((e)-> {
    	  registCart();//장바구니 등록
    	  
    	  //장바구니에 정보가 담겼다고 알려주고, 장바구니 이동 여부 확인해야함
    	  int ans = JOptionPane.showConfirmDialog(ProductDetail.this, "장바구니에 상품이 담겼습니다.\n장바구니로 이동하시겠어요?");
    	  
    	  if(ans==JOptionPane.OK_OPTION) {
    		  getSoomcoMain().showPage(SoomcoMain.CART);    		  
    	  }
      });
   }
   
   //상세페이지가 보여질때 데이터를 채워넣는 메서드 ( 생성자에서 하면 디자인 처리에 타이밍적인 제한이 많다 )
   public void init(ProductVO vo,Image img) {
	   this.vo=vo;//멤버변수에 현재 보고있는 상품 vo 주입	   
	   la_brand.setText(vo.getBrand());
	   la_product_name.setText(vo.getProduct_name());
	   la_price.setText(Integer.toString(vo.getPrice()));
	   this.img = img;
	   this.img =this.img.getScaledInstance(400, 350, Image.SCALE_SMOOTH);
      
   }
   
   //장바구니에 등록(DB로 보관되지 않고, 오직 메모리상으로 지정할 예정)
   public void registCart() {
	   Card cartPage = (Card)getSoomcoMain().getPage()[SoomcoMain.CART];//장바구니 페이지에 접근
	   
	   CardVO cartVO = new CardVO();//Empty Vo생성
	   cartVO.setProduct_id(vo.getProduct_id());//현재 보고있는 상품 이용해 CartVO에 채우기
	   cartVO.setBrand(vo.getBrand());
	   cartVO.setProduct_name(vo.getProduct_name());
	   cartVO.setPrice(vo.getPrice());
	   cartVO.setFilename(vo.getFilename());
	   cartVO.setDetail(vo.getDetail());
	   cartVO.setEa(1);//장바구니에 담을때는 기본이 1개임
	   //선택한 색상
	   cartVO.setColor(ch_color.getSelectedItem());
	   //선택한 사이즈
	   cartVO.setSize(ch_size.getSelectedItem());
	   cartPage.addCart(cartVO);//장바구니에 상품 1건 추가하기
	   cartPage.getCartList();//장바구니 목록 구성
   }
   
   public ProductVO getVo() {
      return vo;
   }

   public void setVo(ProductVO vo) {
      this.vo = vo;
   }

   public Image getImg() {
      return img;
   }

   public void setImg(Image img) {
      this.img = img;
   }
}