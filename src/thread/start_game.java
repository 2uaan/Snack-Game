package thread;

import java.awt.Color;

import javax.swing.*;

import model.snack_body;

public class start_game extends Thread{
	
	private volatile boolean endgame;
	private JButton[][] gameFrame;
	private snack_body snack[];
	private int speed;
	private char direction;
	
	public start_game(JButton[][] gameFrame, snack_body[] snack) {
		super();
		this.endgame = false;
		this.gameFrame = gameFrame;
		this.snack = snack;
		this.direction = 'r';
		this.speed = 500;
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
			try {
				int increx=0, increy=0;
				switch (direction) {
					case 'l':{ increx = -1; increy = 0; break;	}
					case 'r':{ increx = 1; increy = 0; break;	}
					case 'u':{ increx = 0; increy = -1; break;	}
					case 'd':{ increx = 0; increy = 1; break;	}
				}
				
				gameFrame[snack[snack.length-1].getX()][snack[snack.length-1].getY()].setBackground(Color.cyan);
				
				snack[0].setNextx(snack[0].getX() + increx);
				snack[0].setNexty(snack[0].getY() + increy);
				
				if (snack[0].getNextx() == gameFrame.length) snack[0].setNextx(0);
				if (snack[0].getNexty() == gameFrame.length) snack[0].setNexty(0);
				if (snack[0].getNextx() == -1) snack[0].setNextx(gameFrame.length-1);
				if (snack[0].getNexty() == -1) snack[0].setNexty(gameFrame.length-1);
				
				for (int i = 0; i<snack.length; i++) {
					if (i != 0){
						snack[i].setNextx(snack[i-1].getX());
						snack[i].setNexty(snack[i-1].getY());
					}
					
				}
				
				for (int i = 0; i<snack.length; i++) {
					gameFrame[snack[i].getNextx()][snack[i].getNexty()].setBackground(Color.orange);
					if (i == 0) gameFrame[snack[i].getNextx()][snack[i].getNexty()].setBackground(Color.red);
					
					snack[i].setX(snack[i].getNextx());
					snack[i].setY(snack[i].getNexty());
					
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
