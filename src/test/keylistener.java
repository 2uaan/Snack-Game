package test;

import javax.swing.*;
import java.awt.event.*;

public class keylistener extends JFrame implements KeyListener {
    JLabel label;

    public keylistener() {
        label = new JLabel("Nhấn một phím bất kỳ...");
        add(label);
        addKeyListener(this);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        label.setText("Bạn vừa nhấn: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        label.setText("Bạn vừa thả: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không cần thiết trong nhiều trường hợp
    }

    public static void main(String[] args) {
        new keylistener();
    }
}
