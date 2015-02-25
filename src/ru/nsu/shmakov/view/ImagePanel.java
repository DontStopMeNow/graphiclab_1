package ru.nsu.shmakov.view;

import ru.nsu.shmakov.data.MyImage;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Иван on 21.02.2015.
 */

public class ImagePanel extends JPanel {
    public ImagePanel() {
        super(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        if(image != null) {
            Graphics2D g2 = image.createGraphics();
            g2d.drawImage(image, ((getWidth()- image.getWidth())/2), (getHeight()- image.getHeight())/2, new Color(255,255,255), null);
        }
    }

    private int imageWidth;
    private int imageHeight;


    private Graphics2D g2d;

    public MyImage getImage() {
        return image;
    }

    public void setImage(MyImage image) {
        this.image = image;
        repaint();
        repaint();
    }

    private MyImage image;
    private MyImage background;
}
