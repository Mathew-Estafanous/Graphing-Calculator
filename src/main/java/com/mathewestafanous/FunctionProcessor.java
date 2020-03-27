package com.mathewestafanous;

import org.mariuszgromada.math.mxparser.*;
public class FunctionProcessor {
    public static double[][] processFunction(String equation) {
        equation = equation.toLowerCase();
        if (equation.indexOf("=") != -1 || equation.indexOf("x") == -1 || equation.length() == 0) {
            return null;
        }

        double[] all_xVal = identifyValues();
        if(all_xVal == null) {
            return null;
        }

        double[][] coordinates = new double[all_xVal.length][];
        for(int i = 0; i < all_xVal.length; i++) {
            Function fx = new Function("f(x) =" + equation);
            String num = String.valueOf(all_xVal[i]);
            String combination = "f(" + num + ")";
            Expression e1 = new Expression(combination, fx);
            double[] result = {all_xVal[i], e1.calculate()};
            coordinates[i] = result;
        }
        return coordinates;
    }

    private static double[] identifyValues() {
        GraphingPanel graphPan = MainFrame.getGraphingPanel();
        int width = graphPan.getGraphWidth();

        double xAxis = graphPan.getXAxis();
        if(xAxis == 0) { return null;}

        double calcPerGroup = width / (xAxis * 2);
        double xValSpace = 1/ calcPerGroup;
        double[] xVals = new double[width];
        double xValue = -xAxis;
        for(int x = 0; x < xVals.length; x++) {
            xVals[x] = xValue;
            xValue += xValSpace;
        }
        return xVals;
    }
}