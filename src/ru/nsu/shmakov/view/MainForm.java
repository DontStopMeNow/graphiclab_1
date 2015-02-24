package ru.nsu.shmakov.view;

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
    private ImagePanel Image;

    public ImagePanel getImage() {
        return Image;
    }

    public MainForm() {
        super("Main Frame");
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 600);
        setVisible(true);

    }
}
