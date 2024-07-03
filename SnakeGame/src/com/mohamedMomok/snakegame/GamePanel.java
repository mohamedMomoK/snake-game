package com.mohamedMomok.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 600;
	private static final int UNIT_SIZE = 25;
	private static final int DELAY = 75;
	private final int[] x = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private final int[] y = new int[(PANEL_WIDTH*PANEL_HEIGHT)/(UNIT_SIZE*UNIT_SIZE)];
	private int bodyParts = 6;
	private int applesEaten;
	private int appleX;
	private int appleY;
	private char direction = 'R';
	private boolean running = false;
	private Timer timer;
	private Random random;
	
	public GamePanel() {
		random = new Random();
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
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	private void newApple() {
		appleX = random.nextInt((int) (PANEL_WIDTH/UNIT_SIZE))* UNIT_SIZE;
		appleY = random.nextInt((int) (PANEL_HEIGHT/UNIT_SIZE))* UNIT_SIZE;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(running) {
			draw(g);
			
		}else {
			gameOver(g);
		}
	}
	
	private void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
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
	
	private void checkApple() {
		if((x[0]==appleX) && (y[0]==appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	private void checkCollisions() {
		// Check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Check if head touches left border
        if (x[0] < 0) {
            running = false;
        }

        // Check if head touches right border
        if (x[0] >= PANEL_WIDTH) {
            running = false;
        }

        // Check if head touches top border
        if (y[0] < 0) {
            running = false;
        }

        // Check if head touches bottom border
        if (y[0] >= PANEL_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
		
	}
	
	private void gameOver(Graphics g) {
		// Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (PANEL_WIDTH - metrics.stringWidth("Game Over")) / 2, PANEL_HEIGHT / 2);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		
		}
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
