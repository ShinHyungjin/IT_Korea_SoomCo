package com.soomco.home;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;
import com.soomco.product.ProductDetail;
import com.soomco.product.ProductVO;

public class Home extends Page{
   JPanel p_content;//상품리스트를 담게될 패널, 추후 상세보기로 전환시 이패널 자체를 들어내버릴거양 
   ArrayList<ProductItem> itemList;//생성된 상품 아이템들을 담게될리스트( productitem 클래스내엣 이벤트를 구현하면 너무많은 정보를 넘겨야하므로 또한 페이지도 아니면서 너무 많은 정보를 가져야하므로 효율성 저하) 
   public Home(SoomcoMain SoomcoMain) {
      super(SoomcoMain);
      p_content = new JPanel();
      
      //스타일적용
      p_content.setPreferredSize(new Dimension(SoomcoMain.WIDTH-40, SoomcoMain.HEIGHT-150));
      
      add(p_content);

      getProductList();
      p_content.updateUI();
      
      //생성된 아이템들에 대해서 마우스 리스너 연결하기 
      for(ProductItem item :itemList) {
         item.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
               ProductDetail productDetail = (ProductDetail)getSoomcoMain().getPage()[SoomcoMain.PRODUCT_DETAIL];
               productDetail.init(item.vo, item.img);//아이템이 보유한 productVO전달
               productDetail.updateUI();
               getSoomcoMain().showPage(SoomcoMain.PRODUCT_DETAIL);
            }
         });
      }
   }
   
   //모든 상품 가져오기 
   public void getProductList() {
      PreparedStatement pstmt=null;
      ResultSet rs =null; 
      
      String sql = "select * from product";
      
      try {
         pstmt = getSoomcoMain().getCon().prepareStatement(sql);
         rs = pstmt.executeQuery();
         itemList = new ArrayList<ProductItem>();//상품 아이템 생성할때마다 리스트에 담기위함
         
         while (rs.next()) {//레코드가 있는 만큼 
            //vo하나를 생성한 후, rs의 데이터를 vo 옮긴다. 
            ProductVO vo = new ProductVO();
            vo.setProduct_id(rs.getInt("product_id"));
            vo.setSubcategory_id(rs.getInt("subcategory_id"));
            vo.setProduct_name(rs.getString("product_name"));
            vo.setBrand(rs.getString("brand")); 
            vo.setPrice(rs.getInt("price"));
            vo.setFilename(rs.getString("filename"));
            vo.setDetail(rs.getString("detail"));

            //완성된 vo를 이용하여 createItem()호출
            itemList.add(getCreateItem(vo));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         getSoomcoMain().getDbManager().close(pstmt, rs);
      }      
   }
   //상품 아이템 카드 생성하기 
   public ProductItem getCreateItem(ProductVO vo) {
      ProductItem item = new ProductItem(this,vo, 170, 180);
      p_content.add(item);      
      return item;//생성후 반환까지하자 
      
   }
}
