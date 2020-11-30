package com.soomco.util.hover;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//매거진이랑 숨은고수,초보 카드에만 사용
public class HoverPanel extends JPanel {
	float alpha = 0.82f;
	LineBorder line1 = new LineBorder(Color.black, 1, false);
	LineBorder line2 = new LineBorder(Color.ORANGE, 1, false);

	public HoverPanel() {
		this.setBorder(line1);
		addMouseListener(new ML());
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		repaint();
	}

	public void paintComponent(java.awt.Graphics g) {
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));//불투명도 조절하는 메서드
		super.paintComponent(g2);
	}

	public class ML extends MouseAdapter {
		public void mouseExited(MouseEvent me) {
			setBorder(line1);
			new Thread(new Runnable() {
				public void run() {
					for (float i = 1f; i >= .82f; i -= .03f) {
						setAlpha(i);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}
		
		public void mouseEntered(MouseEvent me) {
			new Thread(new Runnable() {
				public void run() {
					for (float i = .82f; i <= 1f; i += .03f) {
						setAlpha(i);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}

		public void mouseReleased(MouseEvent me) {
			setBorder(line1);
		}

		public void mousePressed(MouseEvent me) {
			setBorder(line2);
			new Thread(new Runnable() {
				public void run() {
					for (float i = 1f; i >= 0.8f; i -= .1f) {
						setAlpha(i);
						try {
							Thread.sleep(1);
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}
	}
}