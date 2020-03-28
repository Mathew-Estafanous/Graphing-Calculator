package com.mathewestafanous;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphingPanel extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int graphwidth;
    private int graphHeight;

    private double xSpacing;
    private double ySpacing;
    private double xAxis;
    private double yAxis;

    private HashMap<Integer, double[][]> graph_coordinate = new HashMap<Integer, double[][]>();
    private HashMap<Integer, String> graph_equations = new HashMap<Integer, String>();
    private List<Integer> graph_locations = new ArrayList<Integer>();

    private double[][] coordinates;
    private static Color axisColour = Color.BLACK;
    private static Color[] graphColours = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA, Color.yellow};

    public GraphingPanel(int width, int height) {
        this.graphHeight = height;
        this.graphwidth = width;
    }

    public void createGraphAxis(double xAxis, double yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.xSpacing = (this.graphwidth / 2) / xAxis;
        this.ySpacing = (this.graphHeight / 2) / yAxis;
        System.out.println("X: " + this.xSpacing + " Y: " + this.ySpacing);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xMidPoint = this.graphwidth / 2;
        int yMidPoint = this.graphHeight / 2;
        int yBotPoint = this.graphHeight;

        g.setColor(axisColour);
        g.drawLine(xMidPoint, 0, xMidPoint, yBotPoint); // y-axis
        g.drawLine(0, yMidPoint, this.graphwidth, yMidPoint); // X-axis

        Graphics2D g2 = (Graphics2D) g;
        if (xAxis != 0 && yAxis != 0) {
            int lineLength = 3;

            int yBot = yMidPoint - lineLength;
            int yTop = yMidPoint + lineLength;
            for (int x = 1; x <= xAxis; x++) {
                double xMaxLoc = xMidPoint + (this.xSpacing * x);
                double xMinLoc = xMidPoint - (this.xSpacing * x);

                Shape xMaxline = new Line2D.Double(xMaxLoc, yTop, xMaxLoc, yBot);
                Shape xMinline = new Line2D.Double(xMinLoc, yTop, xMinLoc, yBot);
                g2.draw(xMaxline);
                g2.draw(xMinline);
            }

            int xLeft = xMidPoint - lineLength;
            int xRight = xMidPoint + lineLength;
            for (int y = 1; y <= yAxis; y++) {
                double yMaxLoc = yMidPoint + (this.ySpacing * y);
                double yMinLoc = yMidPoint - (this.ySpacing * y);

                Shape yMaxLine = new Line2D.Double(xLeft, yMaxLoc, xRight, yMaxLoc);
                Shape yMinLine = new Line2D.Double(xLeft, yMinLoc, xRight, yMinLoc);

                g2.draw(yMaxLine);
                g2.draw(yMinLine);
            }
        }

        if(!graph_coordinate.isEmpty()){
            createFunction(g2);
        }
    }

    public void createFunction(Graphics2D g2) {
        for(int j = 0; j < graph_locations.size(); j++) {
            int graphKey = graph_locations.get(j);
            coordinates = graph_coordinate.get(graphKey);
            for(int i = 0; i < coordinates.length - 1; i++) {
                double xCord_1 = i;
                double xCord_2 = i + 1;
                double yCord_1 = coordinates[i][1];
                double yCord_2 = coordinates[i + 1][1];
                double graphYCord_1 = (yCord_1 - yAxis) * -this.ySpacing;
                double graphYCord_2 = (yCord_2 - yAxis) * -this.ySpacing;
                Shape line = new Line2D.Double(xCord_1, graphYCord_1, xCord_2, graphYCord_2);

                g2.setColor(graphColours[graphKey]);
                g2.draw(line);
            }
        }
    }

    //Getter & Setter Files
    public int getGraphWidth() {
        return graphwidth;
    }

    public double getXAxis() {
        return xAxis;
    }

    public List getGraphList() {
        return graph_locations;
    }

    public HashMap getGraphEquations() {
        return graph_equations;
    }

    public void setCoordinates(int key, double[][] coordinates, String equation) {
        if(graph_coordinate.containsKey(key)) {
            graph_coordinate.replace(key, coordinates);
            graph_equations.replace(key, equation);
            return;
        }

        graph_coordinate.put(key, coordinates);
        graph_equations.put(key, equation);
        graph_locations.add(key);
    }

    public void removeCoordinates(int key) {
        int index = graph_locations.indexOf(key);
        if(index == -1) { return; }
        
        graph_coordinate.remove(key);
        graph_locations.remove(index);
        graph_equations.remove(key);
    }
}