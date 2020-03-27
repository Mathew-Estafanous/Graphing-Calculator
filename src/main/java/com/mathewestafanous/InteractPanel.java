package com.mathewestafanous;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InteractPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JLabel rangeLbl;
    private JTextField rangetxt;
    private JButton rangebtn;

    private JTextField txtField;
    private JButton btn;
    private JLabel label;

    private String equation = "";

    public InteractPanel() {
        this.rangeLbl = new JLabel("Range [x, y]");
        this.rangetxt = new JTextField(10);
        this.rangebtn = new JButton("Update");
        this.rangebtn.setPreferredSize(new Dimension(75, 30));
        this.rangebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String rangeInput = rangetxt.getText();
                String[] rangeArray = rangeInput.split(",");
                try {
                    GraphingPanel graphPan = MainFrame.getGraphingPanel();
                    graphPan.createGraphAxis(Integer.parseInt(rangeArray[0]), Integer.parseInt(rangeArray[1]));
                    processGraph();
                    graphPan.repaint();
                } catch (Exception e) {
                    System.out.println(e);
                    rangetxt.setText("Invalid Input");
                }
            }
        });


        this.label = new JLabel("Function f(x) =");
        this.txtField = new JTextField(20);
        this.btn = new JButton("Graph");
        this.btn.setPreferredSize(new Dimension(100, 30));

        this.btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                equation = txtField.getText();
                processGraph();
            }
        });

        add(this.rangeLbl);
        add(this.rangetxt);
        add(this.rangebtn);
        add(this.label);
        add(this.txtField);
        add(this.btn);
    }

    public void processGraph() {
        double[][] coordinates = FunctionProcessor.processFunction(equation);
        if(coordinates == null) {
            txtField.setText("Invalid Equation");
            return;
        }

        GraphingPanel graphPan = MainFrame.getGraphingPanel();
        graphPan.setCoordinates(coordinates);
        graphPan.repaint();
    }
}