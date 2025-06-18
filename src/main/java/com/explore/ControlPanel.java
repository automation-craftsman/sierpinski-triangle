package com.explore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    private final TrianglePanel trianglePanel;
    private final JButton pauseBtn;
    private final JButton colorBtn;
    private final JButton bgBtn;
    private final JSlider speedSlider;
    private final JTextField dotInput;

    private final Color[] colors = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.MAGENTA, Color.ORANGE, Color.CYAN
    };
    private int colorIndex = 0;

    public ControlPanel(TrianglePanel panel) {
        this.trianglePanel = panel;
        setLayout(new FlowLayout());

        add(new JLabel("Dots:"));
        dotInput = new JTextField("10000", 6);
        add(dotInput);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(this::startClicked);
        add(startBtn);

        pauseBtn = new JButton("Pause");
        pauseBtn.addActionListener(e -> {
            trianglePanel.togglePause();
            pauseBtn.setText(trianglePanel.isPaused() ? "Resume" : "Pause");
        });
        add(pauseBtn);

        colorBtn = new JButton("Color");
        colorBtn.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % colors.length;
            trianglePanel.setDotColor(colors[colorIndex]);
        });
        add(colorBtn);

        bgBtn = new JButton("Background");
        bgBtn.addActionListener(e -> trianglePanel.toggleBackgroundColor());
        add(bgBtn);

        add(new JLabel("Speed:"));
        speedSlider = new JSlider(1, 100, 10);
        speedSlider.addChangeListener(e -> trianglePanel.setSpeed(speedSlider.getValue()));
        add(speedSlider);
    }

    private void startClicked(ActionEvent e) {
        try {
            int dots = Integer.parseInt(dotInput.getText().trim());
            trianglePanel.setTotalDots(dots);
            trianglePanel.startPlotting();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of dots.");
        }
    }
}
