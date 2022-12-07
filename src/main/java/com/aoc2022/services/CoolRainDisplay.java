package com.aoc2022.services;

import javax.swing.*;
import java.awt.*;

public class CoolRainDisplay {
    public static void main(String[] args) {
        int numRaindrops = 100;
        int[] xPositions = new int[numRaindrops];
        int[] yPositions = new int[numRaindrops];
        int[] xVelocities = new int[numRaindrops];
        int[] yVelocities = new int[numRaindrops];
        Color color = Color.BLUE; // use only one color for the raindrops
        // initialize the raindrop positions, velocities, and colors
        for (int i = 0; i < numRaindrops; i++) {
            xPositions[i] = (int) (Math.random() * 1000); // random x position
            yPositions[i] = (int) (Math.random() * 1000); // random y position
            xVelocities[i] = (int) (Math.random() * 3) - 1; // random x velocity
            yVelocities[i] = (int) (Math.random() * 25) + 1; // random y velocity, faster than before
        }

// create a graphics object to draw on
        JFrame frame = new JFrame("Cool Rain Display");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // draw the clouds
                g.setColor(new Color(200, 200, 200)); // gray clouds
                g.fillOval(0, 0, 1000, 250); // smaller cloud at the top of the screen
                g.fillArc(-100, -100, 1200, 300, 0, 180); // smaller arc to fill the space around the cloud
                g.fillOval(500, 100, 600, 150); // smaller cloud in the middle of the screen
                g.fillOval(200, 150, 400, 100); // smaller cloud at the bottom of the screen

                // draw the raindrops as small circles with a fixed size
                int size = 2; // fixed size for all raindrops
                g.setColor(color);
                for (int i = 0; i < numRaindrops; i++) {
                    g.fillOval(xPositions[i], yPositions[i], size, size);
                }

                // draw the lightning flashes
                if (Math.random() < 0.1) { // only draw lightning flashes 10% of the time
                    Graphics2D g2d = (Graphics2D)g; // cast the Graphics object to a Graphics2D object
                    g2d.setColor(new Color(255, 255, 150)); // brighter white color
                    BasicStroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {5, 5}, 0);
                    g2d.setStroke(stroke); // thicker and dashed lightning flash

                    // draw multiple lines with different coordinates and probabilities to create a more jagged, complex, and random shape for the lightning flash
                    if (Math.random() < 0.1) { // only draw lightning flashes 10% of the time
                        g2d.drawLine(200 + (int)(Math.random() * 100) - 50, (int)(Math.random() * 100), 200 + (int)(Math.random() * 100) - 50, 1000); // draw a lightning flash from the left cloud
                    }
                    if (Math.random() < 0.1) { // only draw lightning flashes 10% of the time
                        g2d.drawLine(500 + (int)(Math.random() * 100) - 50, (int)(Math.random() * 100), 500 + (int)(Math.random() * 100) - 50, 1000); // draw a lightning flash from the middle cloud
                    }
                    if (Math.random() < 0.1) { // only draw lightning flashes 10% of the time
                        g2d.drawLine(800 + (int)(Math.random() * 100) - 50, (int)(Math.random() * 100), 800 + (int)(Math.random() * 100) - 50, 1000); // draw a lightning flash from the right cloud
                    }

                }
            }
        };
        panel.setBackground(new Color(0, 0, 50)); // darker blue background
        frame.add(panel);
        frame.setVisible(true);

// simulate the rain for 100 time steps
        for (int t = 0; t < 1000; t++) {
            // update the positions of the raindrops based on their velocities
            for (int i = 0; i < numRaindrops; i++) {
                xPositions[i] += xVelocities[i];
                yPositions[i] += yVelocities[i];

                // check if the raindrop has reached the bottom of the screen
                if (yPositions[i] > 1000) {
// if so, reset its position to the top of the screen
                    yPositions[i] = 0;
                    xPositions[i] = (int) (Math.random() * 1000); // random x position
                }
            }
            // repaint the screen with the updated raindrop positions
            panel.repaint();

            // pause for 100 milliseconds before simulating the next time step
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                // do nothing
            }
        }
    }
}
