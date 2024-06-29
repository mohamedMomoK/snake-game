package com.mohamedMomok.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 600;
	private static final int UNIT_SIZE = 25;
	private static final int DELAY = 75;
	private final int[] x = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private final int[] y = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private int bodyParts = 6;
	private Timer timer;
	
	public GamePanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		initializeSnake();
		startGame();	
		
	}
	
	private void initializeSnake() {
		for (int i =0; i<bodyParts; i++) {
			x[i]=50-i*UNIT_SIZE;
			y[i]=50;
		}
			
	}
		
	private void startGame() {
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	private void draw(Graphics g) {
		for (int i =0; i<bodyParts; i++) {
			if (i==0) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(new Color(45,180,0));
			}
			g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
		}
	}
	
	private void move() {
		for(int i= bodyParts; i>0; i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		x[0]+=UNIT_SIZE;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		
	}


}
