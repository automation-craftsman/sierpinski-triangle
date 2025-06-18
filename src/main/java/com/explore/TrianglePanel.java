package com.explore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrianglePanel extends JPanel {

    private final List<Point> points = new ArrayList<>();
    private final Random random = new Random();

    private Point p1, p2, p3; // Triangle vertices
    private Point currentPoint;

    private int totalDots = 10000;
    private int plottedDots = 0;
    private boolean paused = false;

    private Color dotColor = Color.RED;
    private Color backgroundColor = Color.BLACK;

    private int delay = 5;
    private Timer timer;

    private double zoomFactor = 1.0;
    private int panX = 0, panY = 0;
    private int lastX, lastY;

    public TrianglePanel() {
        setBackground(backgroundColor);

        addMouseWheelListener(e -> {
            double delta = e.getPreciseWheelRotation();
            zoomFactor *= (delta > 0) ? 0.9 : 1.1;
            repaint();
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                panX += e.getX() - lastX;
                panY += e.getY() - lastY;
                lastX = e.getX();
                lastY = e.getY();
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        setupTimer();
        resetTriangle();
    }

    private void setupTimer() {
        timer = new Timer(delay, e -> {
            if (!paused && plottedDots < totalDots) {
                plotNextPoint();
                plottedDots++;
                repaint();
            } else {
                timer.stop();
            }
        });
    }

    private void resetTriangle() {
        int w = getWidth() == 0 ? 800 : getWidth();
        int h = getHeight() == 0 ? 800 : getHeight();
        p1 = new Point(w / 2, 50);
        p2 = new Point(50, h - 50);
        p3 = new Point(w - 50, h - 50);
        currentPoint = new Point(random.nextInt(w), random.nextInt(h));
    }

    public void startPlotting() {
        resetTriangle();
        points.clear();
        plottedDots = 0;
        timer.setDelay(delay);
        timer.start();
    }

    public void plotNextPoint() {
        Point[] vertices = {p1, p2, p3};
        Point target = vertices[random.nextInt(3)];
        int x = (currentPoint.x + target.x) / 2;
        int y = (currentPoint.y + target.y) / 2;
        currentPoint = new Point(x, y);
        points.add(new Point(currentPoint));
    }

    public void setTotalDots(int total) {
        this.totalDots = total;
    }

    public void togglePause() {
        this.paused = !paused;
        if (!paused && plottedDots < totalDots) {
            timer.start();
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public int getPlottedDots() {
        return plottedDots;
    }

    public void setDotColor(Color color) {
        this.dotColor = color;
        repaint();
    }

    public void toggleBackgroundColor() {
        backgroundColor = (backgroundColor == Color.BLACK) ? Color.WHITE : Color.BLACK;
        setBackground(backgroundColor);
        repaint();
    }

    public void setSpeed(int delayMs) {
        this.delay = delayMs;
        if (timer != null) {
            timer.setDelay(delayMs);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Apply zoom and pan
        g2.translate(panX, panY);
        g2.scale(zoomFactor, zoomFactor);

        // 1. Draw triangle outline
        g2.setColor(Color.LIGHT_GRAY); // Or any light color for visibility
        g2.setStroke(new BasicStroke(0.01F)); // Thin line
        g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        g2.drawLine(p2.x, p2.y, p3.x, p3.y);
        g2.drawLine(p3.x, p3.y, p1.x, p1.y);

        g2.setColor(dotColor);
        for (Point p : points) {
            g2.fillRect(p.x, p.y, 1, 1);
        }

        // Display count in corner
        g2.setTransform(new AffineTransform()); // Reset transform
        g2.setColor(Color.GRAY);
        g2.drawString("Dots: " + plottedDots + " / " + totalDots, 10, 20);
    }
}
