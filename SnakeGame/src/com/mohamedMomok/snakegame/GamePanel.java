package com.mohamedMomok.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 600;
	private static final int UNIT_SIZE = 25;
	private static final int DELAY = 75;
	private final int[] x = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private final int[] y = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private int bodyParts = 6;
	private char direction = 'R';
	private Timer timer;
	
	public GamePanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new MyKeyAdapter());
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
		
		switch (direction) {
		case 'U':
			y[0]-=UNIT_SIZE;
			break;
		case 'D':
			y[0]+=UNIT_SIZE;
			break;
		case 'L':
			x[0]-=UNIT_SIZE;
			break;
		case 'R':
			x[0]+=UNIT_SIZE;
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		
	}
	private class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction !='R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction !='L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction !='D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction !='U') {
					direction = 'D';
				}
				break;
			}
		}
		
	}


}
