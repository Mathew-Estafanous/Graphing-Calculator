package com.mathewestafanous;

import javax.swing.SwingUtilities;


public final class App {
    public static void main(String[] arg) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
