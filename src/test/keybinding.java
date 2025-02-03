package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class keybinding extends JFrame {
    JLabel label;

    public keybinding() {
        label = new JLabel("0");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        // Lấy InputMap và ActionMap
        InputMap inputMap = label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = label.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("UP"), "incre");
        actionMap.put("incre", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.parseInt(label.getText());
				temp++;
				label.setText(temp+"");
			}
		});
        
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "decre");
        actionMap.put("decre", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = Integer.parseInt(label.getText());
				temp--;
				label.setText(temp+"");
			}
		});

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new keybinding();
    }
}
