package com.soomco.main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	SoomcoMain soomcoMain;
	
	public SoomcoMain getSoomcoMain() {
		return soomcoMain;
	}
	
	public Page(SoomcoMain soomcomain) {
		this.soomcoMain=soomcomain;
		this.setPreferredSize(new Dimension(SoomcoMain.WIDTH, SoomcoMain.HEIGHT-100));
	}
}
