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

    public void drawPixelRGB(int x, int y, Color color){
        if(x >= width || x < 0 || y >= height || y < 0 )
            return;
            //throw new RuntimeException("Out of range");
        int imR = color.getRed();
        int imG = color.getGreen();
        int imB = color.getBlue();

        Color newColor = new Color(imR, imG, imB);

        image.setRGB(x, y, newColor.getRGB());
    }

    public void drawPixelRGBA(int x, int y, Color color){
        if(x >= width || x < 0 || y >= height || y < 0 )
            return;
            //throw new RuntimeException("Out of range");

        int imR = color.getRed();
        int imG = color.getGreen();
        int imB = color.getBlue();
        double alpha = color.getAlpha()/ 255.;

        int bgRGB = image.getRGB(x, y);

        int bgR = ((bgRGB >> 16) & 0xff);
        int bgG = ((bgRGB >> 8 ) & 0xff);
        int bgB =   bgRGB & 0xff;


        Color newColor = new Color( (int)Math.floor((1 - alpha) * bgR + alpha * imR),
                                    (int)Math.floor((1 - alpha) * bgG + alpha * imG),
                                    (int)Math.floor((1 - alpha) * bgB + alpha * imB));


        image.setRGB(x, y, newColor.getRGB());
    }

    public void drawLine(Point p1, Point p2, Color color) {
        drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), color);
    }

    public void drawLine(Integer x1, Integer y1,
                         Integer x2, Integer y2, Color color) {
        if(     x1 >= width || x1 < 0 || y1 >= height || y1 < 0 ||
                x2 >= width || x2 < 0 || y2 >= height || y2 < 0 )
            return;
            //throw new RuntimeException("Out of range");

        boolean steep = false;
        int tmp = 0;
        if (Math.abs(x1 - x2)<Math.abs(y1 - y2)) {
            tmp = x1;
            x1 = y1;
            y1 = tmp;

            tmp = x2;
            x2 = y2;
            y2 = tmp;
            steep = true;
        }

        if (x1 > x2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;

            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;
        int derror = Math.abs(dy)*2;
        int error = 0;

        int y = y1;
        for (int x = x1; x<=x2; x++) {
            if (steep) {
                drawPixelRGB(y, x, color);
            } else {
                drawPixelRGB(x, y, color);
            }
            error += derror;

            if (error > dx) {
                y += (y2 > y1 ? 1:-1);
                error -= dx*2;
            }
        }
    }

    public void drawTriangle(Point p1, Point p2, Point p3, Color color) {
        drawTriangle((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY(), (int)p3.getX(), (int)p3.getY(), color);
    }
    public void drawTriangle(Integer x1, Integer y1,
                             Integer x2, Integer y2,
                             Integer x3, Integer y3, Color color) {
        int tmp = 0;

        if (y1 > y2) {
            tmp = x1;
            x1 = x2;
            x2 = tmp;

            tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        if (y1 > y3) {
            tmp = x1;
            x1 = x3;
            x3 = tmp;

            tmp = y1;
            y1 = y3;
            y3 = tmp;
        }

        if (y2 > y3) {
            tmp = x2;
            x2 = x3;
            x3 = tmp;

            tmp = y2;
            y2 = y3;
            y3 = tmp;
        }
        drawLine(x1, y1, x2, y2, color);
        drawLine(x2, y2, x3, y3, color);
        drawLine(x3, y3, x1, y1, color);
    }
}
