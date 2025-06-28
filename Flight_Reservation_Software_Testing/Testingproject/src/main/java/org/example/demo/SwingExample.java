package org.example.demo;

import javax.swing.*;

class SwingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing GUI");
        JButton button = new JButton("Click Me");
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hello, World!"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.add(button);
        frame.setVisible(true);
    }
}