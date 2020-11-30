package com.soomco.util.hover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//테마버튼에만 사용
public class HoverButton extends JButton {
	float alpha = 0.6f;

	public HoverButton() {
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
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
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		super.paintComponent(g2);
	}

	public class ML extends MouseAdapter {
		public void mouseExited(MouseEvent me) {
			new Thread(new Runnable() {
				public void run() {
					for (float i = 1f; i >= .6f; i -= .03f) {
						setAlpha(i);
						try {
							Thread.sleep(30);
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}

		public void mouseEntered(MouseEvent me) {
			new Thread(new Runnable() {
				public void run() {
					for (float i = .6f; i <= 1f; i += .03f) {
						setAlpha(i);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
						}
					}
				}
			}).start();
		}

		public void mousePressed(MouseEvent me) {
			new Thread(new Runnable() {
				public void run() {
					for (float i = 1f; i >= 0.6f; i -= .1f) {
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