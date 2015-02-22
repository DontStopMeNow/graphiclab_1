package ru.nsu.shmakov.view;

import ru.nsu.shmakov.data.MyGraphic2D;
import ru.nsu.shmakov.data.MyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
            MyGraphic2D myGraphic2D = image.getMyGraphic2D();
            myGraphic2D.drawTriangle(0,0, 20, 20, 0, 40, Color.blue);
            g2d.drawImage(image, ((getWidth()- imageWidth)/2), (getHeight()- imageHeight)/2, new Color(255,255,255), null);
        }
    }

    private int imageWidth = 400;
    private int imageHeight = 400;
    private Graphics2D g2d;
    private MyImage image = new MyImage(imageWidth,
                                        imageHeight,
                                        BufferedImage.TYPE_4BYTE_ABGR);

}
