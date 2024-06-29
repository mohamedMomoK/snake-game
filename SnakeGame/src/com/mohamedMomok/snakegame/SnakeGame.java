package com.mohamedMomok.snakegame;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
	
	private GamePanel gamePanel;
	
	public SnakeGame() {
		this.gamePanel = new GamePanel();
		add(gamePanel);
		setTitle("Snake Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			SnakeGame snakeGame = new SnakeGame();
			snakeGame.setVisible(true);
		});
	}

}
