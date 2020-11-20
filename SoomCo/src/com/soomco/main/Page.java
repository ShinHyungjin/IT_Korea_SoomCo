package com.soomco.main;

import java.awt.Dimension;
import javax.swing.JPanel;

public class Page extends JPanel{
	MainFrame mainFrame;
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}
	
	public Page(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		//this.setPreferredSize(new Dimension(mainFrame.WIDTH, mainFrame.HEIGHT-100));
	}
}
