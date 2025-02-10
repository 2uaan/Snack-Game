package thread;

import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

import model.snake_body;
import view.gamePlayView;

public class start_game extends Thread{
	
	private volatile boolean endgame;
	private JButton[][] gameFrame;
	private snake_body snake[];
	private int speed;
	private char direction;
	private int[][] map;
	private JFrame frame;
	
	public start_game(JFrame frame ,JButton[][] gameFrame, snake_body[] snake, int[][] map) {
		super();
		this.frame = frame;
		this.endgame = false;
		this.gameFrame = gameFrame;
		this.snake = snake;
		this.direction = 'r';
		this.speed = 500;
		this.map = map;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public char getDirection() {
		return this.direction;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	@Override
	public void run() {
		while(!endgame) {
			String way = "";
			try {
				int increx=0, increy=0;
				switch (direction) {
					case 'l':{ increx = -1; increy = 0; way = "left"; break;}
					case 'r':{ increx = 1; increy = 0; way = "right"; break;	}
					case 'u':{ increx = 0; increy = -1; way = "up"; break;	}
					case 'd':{ increx = 0; increy = 1; way = "down"; break;	}
				}
				ImageIcon head = new ImageIcon("C:\\Users\\tlmqu\\OneDrive\\Desktop\\Java Project\\Snake_Game\\image\\snake_head_"+ way +".png");
				Image temp = head.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			
				gameFrame[snake[snake.length-1].getX()][snake[snake.length-1].getY()].setBackground(new Color(0xFFFE91));
				gameFrame[snake[snake.length-1].getX()][snake[snake.length-1].getY()].setIcon(null);
				
				
				snake[0].setNextx(snake[0].getX() + increx);
				snake[0].setNexty(snake[0].getY() + increy);
				
				if (snake[0].getNextx() == gameFrame.length) snake[0].setNextx(0);
				if (snake[0].getNexty() == gameFrame.length) snake[0].setNexty(0);
				if (snake[0].getNextx() == -1) snake[0].setNextx(gameFrame.length-1);
				if (snake[0].getNexty() == -1) snake[0].setNexty(gameFrame.length-1);
				if (map[snake[0].getNextx()][snake[0].getNexty()] == 1) {
					JOptionPane.showMessageDialog(frame,"You Lose", "Game Over", JOptionPane.WARNING_MESSAGE);
					endgame = true;
					frame.setVisible(false);
					new gamePlayView().setVisible(true);
				}
				
				for (int i = 1; i < snake.length; i++) {
					if (snake[0].getNextx() == snake[i].getX() && snake[0].getNexty() == snake[i].getY()) {
						JOptionPane.showMessageDialog(frame,"You Lose", "Game Over", JOptionPane.WARNING_MESSAGE);
						endgame = true;
						frame.setVisible(false);
						new gamePlayView().setVisible(true);
					}
				}
				
				for (int i = 0; i<snake.length; i++) {
					if (i != 0){
						snake[i].setNextx(snake[i-1].getX());
						snake[i].setNexty(snake[i-1].getY());
					}
					
				}
				
				for (int i = 0; i<snake.length; i++) {
					String bway = "";
					int xx = snake[i].getNextx() - snake[i].getX(), 
						yy = snake[i].getNexty() - snake[i].getY();
					
					if (xx == 0) bway = "ver";
					else bway = "hor";
					
					ImageIcon body = new ImageIcon("C:\\Users\\tlmqu\\OneDrive\\Desktop\\Java Project\\Snake_Game\\image\\snake_body_"+bway+".png");
					Image temp2 = body.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
					gameFrame[snake[i].getNextx()][snake[i].getNexty()].setIcon(new ImageIcon(temp2));
					if (i == 0) gameFrame[snake[i].getNextx()][snake[i].getNexty()].setIcon(new ImageIcon(temp));
					
					snake[i].setX(snake[i].getNextx());
					snake[i].setY(snake[i].getNexty());
					
				}
				
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Thread.sleep(0);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
		}
		
	}
	
	public void endGame() {
		endgame = true;
	}
}
