package ru.nsu.shmakov;

import javax.swing.*;

/**
 * Created by Иван on 18.02.2015.
 */
public class MainForm extends JFrame {
    private JRadioButton filterRadioButton;
    private JRadioButton blendRadioButton;
    private JButton initButton;
    private JButton startStopButton;
    private JSpinner animationSpinner;
    private JPanel rootPanel;

    public MainForm() {
        super("Main Frame");
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
