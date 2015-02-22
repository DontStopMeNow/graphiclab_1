package ru.nsu.shmakov.data;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Иван on 22.02.2015.
 */
public class MyGraphic2D {
    private BufferedImage image;
    private int width;
    private int height;

    public MyGraphic2D(MyImage i) {
        if((MyImage)null == i)
            throw new RuntimeException("No buffered image");
        this.image = i;
        this.width = i.getWidth();
        this.height = i.getHeight();
    }

    public void drawPixel(int x, int y, char r, char g, char b, char a){
        if(x >= width || x < 0 || y >= height || y < 0 )
            throw new RuntimeException("Out of range");
        int intR = (int) r;
        int intG = (int) g;
        int intB = (int) b;
        int intA = (int) a;
        int color = (intA << 24) | (intR << 16) | (intG << 8) | intB;
        image.setRGB(x, y, color);
    }

    public void drawPixel(int x, int y, Color color){
        if(x >= width || x < 0 || y >= height || y < 0 )
            throw new RuntimeException("Out of range");
        image.setRGB(x, y, color.getRGB());
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {


    }
}
