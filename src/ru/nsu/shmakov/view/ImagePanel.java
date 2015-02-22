package ru.nsu.shmakov.view;

import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

            ArrayList<MyPolygon> polygons = new ArrayList<MyPolygon>();
            polygons.add(new MyPolygon(new Point(0, 0), new Point(20, 0), new Point(0, 20), new Point(0, 0), new Point(0, 0), new Point(0, 0)));
            polygons.add(new MyPolygon(new Point(40, 0), new Point(80, 40), new Point(40, 80), new Point(0, 0), new Point(0, 0), new Point(0, 0)));
            ru.nsu.shmakov.model.Renderer.getInstance().renderPolygons(polygons, image, image);

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
