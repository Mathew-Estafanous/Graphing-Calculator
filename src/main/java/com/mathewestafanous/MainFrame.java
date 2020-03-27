package com.mathewestafanous;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int boardWidth = 850;
    private int boardHeight = 850;
    private int panelOffset = 50;

    private static InteractPanel interactPanel;
    private static GraphingPanel graphingPanel;
    private Dimension interactPanelSize;
    private Dimension graphPanelSize;
    private Color interactColor = Color.gray;

    public MainFrame() {
        super("Graphing Calculator");

        setLayout(new BorderLayout());

        interactPanel = new InteractPanel();
        interactPanelSize = new Dimension(boardWidth, panelOffset);
        interactPanel.setPreferredSize(interactPanelSize);
        interactPanel.setBackground(interactColor);

        graphingPanel = new GraphingPanel(boardWidth, boardHeight - panelOffset);
        graphPanelSize = new Dimension(boardWidth, boardHeight - panelOffset);
        graphingPanel.setPreferredSize(graphPanelSize);

        add(interactPanel, BorderLayout.NORTH);
        add(graphingPanel);

        setSize(boardWidth, boardHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static GraphingPanel getGraphingPanel() {
        return graphingPanel;
    }
}