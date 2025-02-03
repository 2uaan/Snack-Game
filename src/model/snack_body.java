package model;

import java.awt.Color;

import javax.swing.JButton;

public class snack_body {
	
	private JButton body;
	private int x,y;
	
	public snack_body(JButton body, int x, int y) {
		super();
		this.body = body;
		this.x = x;
		this.y = y;
	}
	
	public snack_body() {
		
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
