package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import model.*;
import thread.start_game;

public class gamePlayView extends JFrame{

	private JButton playground[][] = new JButton[30][30];
	private snake_body snake[];
	private map map = new map();
	private int[][] mapArr = map.map4;
	
	public static void main(String[] args) {
		new gamePlayView().setVisible(true);
	}
	
	public gamePlayView() {
		JPanel frame = new JPanel();
		snake = new snake_body[2];
		snake[0] = new snake_body();
		snake[0].setX(10);
		snake[0].setY(10);

		for (int i = 1; i < snake.length; i++) snake[i] = new snake_body();
		
		setTitle("Game Play");
		setSize(610,635);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("C:\\Users\\tlmqu\\OneDrive\\Desktop\\Java Project\\Snake_Game\\image\\snake.png").getImage());
		setResizable(false);
		
		frame.setSize(this.getWidth(), this.getHeight());
		frame.setBackground(Color.black);
		frame.setLayout(new GridLayout(playground.length,playground.length));
		add(frame, BorderLayout.CENTER);
		
		ImageIcon wall = new ImageIcon("C:\\Users\\tlmqu\\OneDrive\\Desktop\\Java Project\\Snake_Game\\image\\wall.png");
		Image tempw = wall.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		
		for (int y = 0; y < playground.length; y++) {
			for (int x = 0; x<playground.length; x++) {
				playground[x][y] = new JButton("");
				playground[x][y].setMargin(new Insets(0, 0, 0, 0));
				playground[x][y].setBorder(null);
				if (mapArr[x][y] == 1) playground[x][y].setIcon(new ImageIcon(tempw));
				playground[x][y].setBackground(new Color(0xFFFE91));
				frame.add(playground[x][y]);
			}
		}
		
		start_game sg = new start_game(this, playground, snake, mapArr);
		sg.start();

		
//
//Keyboard Listener
//
		
		InputMap inputmap = frame.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionmap = frame.getActionMap();
		
		inputmap.put(KeyStroke.getKeyStroke("MINUS"), "slow");
		actionmap.put("slow", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double speed = (double) sg.getSpeed();
				int temp = (int) (1000 / speed);
				if (temp >= 4) {					
					temp-=2;
					temp = (int) (1000 / ((double) temp));
					System.out.println(temp);
					sg.setSpeed(temp);
				}
			}
			
		});
		inputmap.put(KeyStroke.getKeyStroke("EQUALS"), "fast");
		actionmap.put("fast", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double speed = (double) sg.getSpeed();
				int temp = (int) (1000 / speed);
				temp+=2;
				if (temp <= 20) {					
					temp = (int) (1000 / ((double) temp));
					System.out.println(temp);
					sg.setSpeed(temp);
				}
			}
			
		});
		
		inputmap.put(KeyStroke.getKeyStroke("UP"), "up");
		actionmap.put("up", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sg.getDirection() != 'd') sg.setDirection('u');
				
			}
			
		});
		inputmap.put(KeyStroke.getKeyStroke("DOWN"), "down");
		actionmap.put("down", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sg.getDirection() != 'u') sg.setDirection('d');
			}
			
		});
		inputmap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		actionmap.put("right", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sg.getDirection() != 'l') sg.setDirection('r');
			}
			
		});
		inputmap.put(KeyStroke.getKeyStroke("LEFT"), "left");
		actionmap.put("left", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sg.getDirection() != 'r') sg.setDirection('l');
			}
			
		});
		
//
//Interrupt thread when window is closing
//
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              
                    sg.endGame(); // Dừng luồng
                    try {
                        sg.join(); // Chờ luồng dừng hẳn trước khi đóng cửa sổ
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    setVisible(false); // Đóng cửa sổ
                }
        
        });
	}
	
}
