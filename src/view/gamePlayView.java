package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

import model.snack_body;

public class gamePlayView extends JFrame{

	private JButton playground[][] = new JButton[20][20];
	private snack_body snack[];
	
	public static void main(String[] args) {
		new gamePlayView().setVisible(true);
	}
	
	public gamePlayView() {
		JPanel frame = new JPanel();
		snack = new snack_body[2];
		snack[0] = new snack_body();
		snack[0].setX(5);
		snack[0].setY(5);

		snack[1] = new snack_body();
		snack[1].setX(5);
		snack[1].setY(4);
		
		setTitle("Game Play");
		setSize(410,435);
		setLocationRelativeTo(null);
		setResizable(false);
		
		frame.setSize(this.getWidth(), this.getHeight());
		frame.setBackground(Color.black);
		frame.setLayout(new GridLayout(20,20));
		add(frame, BorderLayout.CENTER);
		
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x<20; x++) {
				playground[x][y] = new JButton((x+1 + y*20)%2 +"");
				playground[x][y].setMargin(new Insets(0, 0, 0, 0));
				playground[x][y].setBorder(null);
				playground[x][y].setBackground(Color.cyan);
				for (int i = 0; i < snack.length; i++) {
					if (x == snack[i].getX() && y == snack[i].getY()) {
						snack[i].setBody(playground[x][y]);
						playground[x][y].setBackground(Color.green);
						playground[x][y].setText("");
					}
				}
				frame.add(playground[x][y]);
			}
		}
		
	}
	
}
