package ru.nsu.shmakov.view;

import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;
import ru.nsu.shmakov.data.MyPolygonVertex;
import ru.nsu.shmakov.model.Renderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
            polygons.add(new MyPolygon(new MyPolygonVertex(0, 0, 0.1, 0.1), new MyPolygonVertex(1, 128, 0, 0.5), new MyPolygonVertex(128, 1, 0.5, 0)));

            BufferedImage tex = null;
            try {
                tex = ImageIO.read(new File("./resources/puzzle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //MyImage tex = new MyImage();
            Renderer.getInstance().renderPolygons(polygons, image, image);


            g2d.drawImage(image, ((getWidth()- imageWidth)/2), (getHeight()- imageHeight)/2, new Color(255,255,255), null);
            //g2d.drawImage(tex, ((getWidth()- imageWidth)/2), (getHeight()- imageHeight)/2, new Color(255,255,255), null);
        }
    }

    private int imageWidth = 400;
    private int imageHeight = 400;
    private Graphics2D g2d;
    private MyImage image = new MyImage(imageWidth,
                                        imageHeight,
                                        BufferedImage.TYPE_4BYTE_ABGR);

}
