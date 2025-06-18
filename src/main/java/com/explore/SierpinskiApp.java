package com.explore;

import javax.swing.*;
import java.awt.*;

public class SierpinskiApp extends JFrame {

    private final TrianglePanel trianglePanel;
    private final ControlPanel controlPanel;

    public SierpinskiApp() {
        setTitle("Sierpiński Triangle Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        trianglePanel = new TrianglePanel();
        controlPanel = new ControlPanel(trianglePanel);

        add(trianglePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(800, 800);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }
}
