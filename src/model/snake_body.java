package model;

import java.awt.Color;

import javax.swing.JButton;

public class snake_body {
	
	private JButton body;
	private int x,y;
	private int nextx, nexty;
	
	public snake_body(JButton body, int x, int y, int nextx, int nexty) {
		super();
		this.body = body;
		this.x = x;
		this.y = y;
		this.nextx = nextx;
		this.nexty = nexty;
	}

	
	public int getNextx() {
		return nextx;
	}



	public void setNextx(int nextx) {
		this.nextx = nextx;
	}



	public int getNexty() {
		return nexty;
	}



	public void setNexty(int nexty) {
		this.nexty = nexty;
	}



	public snake_body() {
		
	}

	public JButton getBody() {
		return body;
	}

	public void setBody(JButton body) {
		this.body = body;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
