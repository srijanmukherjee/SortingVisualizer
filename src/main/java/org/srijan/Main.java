package org.srijan;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame();
            mainFrame.setSize(640, 480);
            mainFrame.setTitle("Swing | Sorting Visualizer");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.add(new MainPanel());
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
        });
    }
}