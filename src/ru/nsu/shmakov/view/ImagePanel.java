package ru.nsu.shmakov.view;

import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;
import ru.nsu.shmakov.model.Renderer;
import ru.nsu.shmakov.model.Slicer;

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






            BufferedImage tex = null;
            try {
                //tex.isAlphaPremultiplied();
                tex = ImageIO.read(new File("./resources/puzzle.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            Slicer s = new Slicer(4, 4, tex);
            int shiftX = (image.getWidth()  - tex.getWidth ())/2;
            int shiftY = (image.getHeight() - tex.getHeight())/2;
            ArrayList<MyPolygon> polygons = s.sliceToTriangles(shiftX, shiftY);


            /*
            ArrayList<MyPolygon> polygons =  new ArrayList<MyPolygon>();//s.sliceToTriangles();

            polygons.add(new MyPolygon(new MyPolygonVertex(0, 0, 0, 0), new MyPolygonVertex(0, 32, 0, 0.5), new MyPolygonVertex(32, 0, 0.5, 0)));
            polygons.add(new MyPolygon(new MyPolygonVertex(200, 0, 0, 0), new MyPolygonVertex(328, 0, 0, 1), new MyPolygonVertex(200, 128, 1, 0)));
            polygons.add(new MyPolygon(new MyPolygonVertex(300, 300, 0, 0), new MyPolygonVertex(358, 0, 0, 0.9), new MyPolygonVertex(0, 358, 0.9, 0)));
            */
            /*
            image = new MyImage(getWidth(),
                    getHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);

            imageWidth  = image.getWidth () - 20;
            imageHeight = image.getHeight() - 20;

            int bg[] = new int[imageHeight*imageWidth];
            for (int i = 0; i < imageHeight*imageWidth; ++i)
                bg[i] = Color.pink.getRGB();


            image.setRGB(5, 5, imageWidth, imageHeight, bg, 0, 0);
            */


            imageWidth  = image.getWidth ();
            imageHeight = image.getHeight();

            int bg[] = new int[imageHeight*imageWidth];
            for (int i = 0; i < imageHeight*imageWidth; ++i)
                bg[i] = Color.pink.getRGB();


            image.setRGB(0, 0, imageWidth, imageHeight, bg, 0, 0);

            Renderer.getInstance().renderPolygons(polygons, tex, image);


            g2d.drawImage(image, ((getWidth()- imageWidth)/2), (getHeight()- imageHeight)/2, new Color(255,255,255), null);
        }
    }

    private int imageWidth;
    private int imageHeight;


    private Graphics2D g2d;

    private MyImage image = new MyImage(400, 400, BufferedImage.TYPE_4BYTE_ABGR);
    private MyImage background;
}
