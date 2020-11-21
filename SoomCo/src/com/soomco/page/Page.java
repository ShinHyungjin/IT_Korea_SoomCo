package com.soomco.page;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	private MainPage mainPage;
	
	public MainPage getMainPage() {
		return mainPage;
	}

	public Page(MainPage mainPage) {
		this.mainPage = mainPage;
		this.setPreferredSize(new Dimension(MainPage.WIDTH,MainPage.HEIGHT-100));
	}

}
