package com.mathewestafanous;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

public class InteractPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JLabel rangeLbl;
    private JTextField rangetxt;
    private JButton rangebtn;

    private JLabel choiceLbl;
    private JComboBox<String> dropBox;
    private String[] numOption = {"1", "2", "3", "4", "5", "6"};

    private JTextField txtField;
    private JButton btn;
    private JLabel btnLable;

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
                    processAllGraphs();
                    graphPan.repaint();
                } catch (Exception e) {
                    System.out.println(e);
                    rangetxt.setText("Invalid Input");
                }
            }
        });

        this.choiceLbl = new JLabel("Select #");
        this.dropBox = new JComboBox<String>(numOption);
        this.dropBox.setSelectedIndex(0);

        this.btnLable = new JLabel("Function f(x) =");
        this.txtField = new JTextField(20);
        this.btn = new JButton("Graph");
        this.btn.setPreferredSize(new Dimension(100, 30));
        this.dropBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                GraphingPanel graphPan = MainFrame.getGraphingPanel();
                HashMap<Integer, String> graphEquations = graphPan.getGraphEquations();
                int key = dropBox.getSelectedIndex();
				txtField.setText(graphEquations.get(key));
			}
        });


        this.btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    equation = txtField.getText();
                    processThisGraph();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        add(this.rangeLbl);
        add(this.rangetxt);
        add(this.rangebtn);

        add(this.choiceLbl);
        add(this.dropBox);

        add(this.btnLable);
        add(this.txtField);
        add(this.btn);
    }

    private void processAllGraphs() {
        GraphingPanel graphPan = MainFrame.getGraphingPanel();
        List<Integer> graphKeys = graphPan.getGraphList();
        HashMap<Integer, String> graphEquations = graphPan.getGraphEquations();
        int key = 0;
        for(int i = 0; i < graphKeys.size(); i++) {
            key = graphKeys.get(i);
            equation = graphEquations.get(key);
            processCoordinates(graphPan, key);
        }
    }

    private void processThisGraph() {
        GraphingPanel graphPan = MainFrame.getGraphingPanel();
        int key = this.dropBox.getSelectedIndex();
        if(equation.toLowerCase().equals("clear")) {
            graphPan.removeCoordinates(key);
            graphPan.repaint();
            return;
        }

        processCoordinates(graphPan, key);
    }

    private void processCoordinates(GraphingPanel graphPan, int key) {
        double[][] coordinates = FunctionProcessor.processFunction(equation);
        if(coordinates == null) {
            txtField.setText("Invalid Equation");
            return;
        }
        graphPan.setCoordinates(key, coordinates, equation);
        graphPan.repaint();
    }
}