package com.soomco.card;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.soomco.main.Page;
import com.soomco.main.SoomcoMain;




public class Card extends Page{
	JPanel bt_container;//버튼을 묶어줄 컨테이너
	JButton bt_pay;//결제단계로 가기
	JButton bt_del; //장바구니 비우기
	
	//장바구니 역할을 컬렉션 프레임웤 객체를 선언
	HashMap<Integer,CardVO> cartList;
	JPanel p_content;//Cart에 직접 붙이지말고, 아이템들을 붙일 컨테이너 준비
	
	public Card(SoomcoMain SoomcoMain) {
		super(SoomcoMain);
		
		cartList = new HashMap<Integer,CardVO>();
				
		bt_container = new JPanel();
		bt_pay = new JButton("결제하기");
		bt_del = new JButton("장바구니 비우기");
		
		//스타일
		bt_container.setPreferredSize(new Dimension(SoomcoMain.WIDTH, 100));
		bt_container.setBackground(Color.cyan);
		
		getCartList();
		
		bt_container.add(bt_pay);
		bt_container.add(bt_del);
		add(bt_container,BorderLayout.SOUTH);
		
	}
	
	//장바구니에 넣기
	public void addCart(CardVO vo) {//상품1건을 장바구니에 추가하기
		cartList.put(vo.getProduct_id(), vo); //key와 값을 저장
	}

	//장바구니 삭제하기
	public void removeCart(int product_id) {//상품 1건을 장바구니에서 제거하기
		cartList.remove(product_id);
	}
	
	//장바구니 비우기
	public void removeAll() {//모든 상품을 장바구니에서 제거하기
		
	}
	
	//장바구니 변경
	public void updateCart(CardVO vo) {
		//해시맵에 들어있는 객체 중 해당 객체를 찾아내 VO 교체
		CardVO obj = cartList.get(vo.getProduct_id());//검색
		obj=vo;//기존 해시맵이 갖고 있던 vo 찾아내 주소 변경
	}
	
	//장바구니 목록 가져오기(주의: 맵은 순서가 없는 집합이므로 먼저 일렬로 늘어뜨려야 한다.)
	public void getCartList() { 
		Set<Integer> set = cartList.keySet();//키들을 set으로 반환받음. 즉 맵은 한번에 일렬로 늘어뜨리는게 아니라 key를 가져와야함
		
		Iterator<Integer> it = set.iterator();
		//add하기 전에 기존에 붙어있던 컴포넌트는 제거		
		int count=0;
		if(p_content!=null) {
			this.remove(p_content);
			this.repaint();
		}
		
		//동적으로 새로 생성
		p_content=new JPanel();
		p_content.setPreferredSize(new Dimension(SoomcoMain.WIDTH-350,600));
		
		while(it.hasNext()) {//요소가 있는 동안
			int key = it.next(); //요소를 추출
			//System.out.println("key:"+key);
			CardVO vo = cartList.get(key);
			//디자인을 표현하는 CartItem에 CartVO의 정보를 채워넣기
			CardItem item = new CardItem(vo);
			
			//홰시맵에 등록된 상품 삭제 후 리스트 호출
			item.bt_del.addActionListener((e)-> {
				if(JOptionPane.showConfirmDialog(Card.this, "장바구니에서 삭제하시겠습니까?")==JOptionPane.OK_OPTION) {
					removeCart(vo.getProduct_id());
					getCartList();
				}
			});
			
			item.bt_update.addActionListener((e)-> {
				if(JOptionPane.showConfirmDialog(Card.this, "장바구니를 수정하시겠습니까?")==JOptionPane.OK_OPTION) {
					int ea = Integer.parseInt(item.t_ea.getText());
					vo.setEa(ea);
					updateCart(vo);
					getCartList();
				}
			});
			
			p_content.add(item);
			count++;
		}
		add(p_content);
		this.updateUI();
	}
}
