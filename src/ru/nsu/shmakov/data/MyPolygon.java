package ru.nsu.shmakov.data;

import java.awt.*;

/**
 * Created by Иван on 21.02.2015.
 */
public class MyPolygon {
    public MyPolygon(Point xy1, Point xy2, Point xy3, Point uv1, Point uv2, Point uv3) {
        if (    uv1.getX() < 0 || uv1.getX() > 1 || uv1.getY() < 0 || uv1.getY() > 1 ||
                uv2.getX() < 0 || uv2.getX() > 1 || uv2.getY() < 0 || uv2.getY() > 1 ||
                uv3.getX() < 0 || uv3.getX() > 1 || uv3.getY() < 0 || uv3.getY() > 1 ) {
            throw new RuntimeException("Invalid UV coordinate");
        }


        this.xy1 = xy1;
        this.xy2 = xy2;
        this.xy3 = xy3;
        this.uv1 = uv1;
        this.uv2 = uv2;
        this.uv3 = uv3;
    }

    public Point getXY1() {
        return xy1;
    }

    public void setXY1(Point xy1) {
        this.xy1 = xy1;
    }

    public Point getXY2() {
        return xy2;
    }

    public void setXY2(Point xy2) {
        this.xy2 = xy2;
    }

    public Point getXY3() {
        return xy3;
    }

    public void setXY3(Point xy3) {
        this.xy3 = xy3;
    }

    public Point getUV1() {
        return uv1;
    }

    public void setUV1(Point uv1) {
        this.uv1 = uv1;
    }

    public Point getUV2() {
        return uv2;
    }

    public void setUV2(Point uv2) {
        this.uv2 = uv2;
    }

    public Point getUV3() {
        return uv3;
    }

    public void setUV3(Point uv3) {
        this.uv3 = uv3;
    }

    private Point xy1;
    private Point xy2;
    private Point xy3;

    private Point uv1;
    private Point uv2;
    private Point uv3;
}
