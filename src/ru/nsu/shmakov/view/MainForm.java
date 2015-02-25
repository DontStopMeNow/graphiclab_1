package ru.nsu.shmakov.view;

import ru.nsu.shmakov.controller.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private ImagePanel imagePanel;

    private AnimationSpinerController    animationSpinerController;
    private BlendController              blendController;
    private FilterController             filterController;

    public AnimationSpinerController getAnimationSpinerController() {
        return animationSpinerController;
    }

    public void setAnimationSpinerController(AnimationSpinerController animationSpinerController) {
        this.animationSpinerController = animationSpinerController;
    }

    public BlendController getBlendController() {
        return blendController;
    }

    public void setBlendController(BlendController blendController) {
        this.blendController = blendController;
    }

    public FilterController getFilterController() {
        return filterController;
    }

    public void setFilterController(FilterController filterController) {
        this.filterController = filterController;
    }

    public InitController getInitController() {
        return initController;
    }

    public void setInitController(InitController initController) {
        this.initController = initController;
    }

    public StartStopAnimationController getStartStopAnimationController() {
        return startStopAnimationController;
    }

    public void setStartStopAnimationController(StartStopAnimationController startStopAnimationController) {
        this.startStopAnimationController = startStopAnimationController;
    }

    private InitController               initController;
    private StartStopAnimationController startStopAnimationController;


    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public JRadioButton getFilterRadioButton() {
        return filterRadioButton;
    }

    public JRadioButton getBlendRadioButton() {
        return blendRadioButton;
    }

    public JButton getInitButton() {
        return initButton;
    }

    public JButton getStartStopButton() {
        return startStopButton;
    }

    public JSpinner getAnimationSpinner() {
        return animationSpinner;
    }

    public MainForm() {
        super("Main Frame");
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new DimensionUIResource(600, 600));

        setSize(900, 600);
        setVisible(true);

        filterRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterController.doAction(filterRadioButton.isSelected());
            }
        });

        blendRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blendController.doAction(blendRadioButton.isSelected());
            }
        });
        animationSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (0 > (Integer) animationSpinner.getValue())
                    animationSpinner.setValue((Integer) 360);
                if (360 < (Integer) animationSpinner.getValue())
                    animationSpinner.setValue((Integer) 0);

                animationSpinerController.doAction((Integer) animationSpinner.getValue());
            }
        });

        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initController.doAction();
            }
        });
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopAnimationController.doAction();
            }
        });
    }
}
