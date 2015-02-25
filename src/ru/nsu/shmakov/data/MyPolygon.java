package ru.nsu.shmakov.data;

/**
 * Created by Иван on 21.02.2015.
 */
public class MyPolygon {
    public MyPolygon(MyPolygonVertex p1, MyPolygonVertex p2, MyPolygonVertex p3) {
        if (    p1.getU() < 0 || p1.getU() > 1 || p1.getV() < 0 || p1.getV() > 1 ||
                p2.getU() < 0 || p2.getU() > 1 || p2.getV() < 0 || p2.getV() > 1 ||
                p3.getU() < 0 || p3.getU() > 1 || p3.getV() < 0 || p3.getV() > 1 ) {
            throw new RuntimeException("Invalid UV coordinate");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        calculateCenter();
    }

    public MyPolygonVertex getCenter() {
        return center;
    }

    public MyPolygonVertex getP1() {
        return p1;
    }

    public void setP1(MyPolygonVertex p1) {
        this.p1 = p1;
        calculateCenter();
    }

    public MyPolygonVertex getP2() {
        return p2;
    }

    public void setP2(MyPolygonVertex p2) {
        this.p2 = p2;
        calculateCenter();
    }

    public MyPolygonVertex getP3() {
        return p3;
    }

    public void setP3(MyPolygonVertex p3) {
        this.p3 = p3;
        calculateCenter();
    }

    public double getShiftX() {
        return shiftX;
    }

    public void setShiftX(double shiftX) {
        this.shiftX = shiftX;
    }

    public double getShiftY() {
        return shiftY;
    }

    public void setShiftY(double shiftY) {
        this.shiftY = shiftY;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    private void calculateCenter() {
        center = new MyPolygonVertex((int)Math.round((p1.getX() + p2.getX() + p3.getX())/3),
                                     (int)Math.round((p1.getY() + p2.getY() + p3.getY())/3),
                                                     (p1.getU() + p2.getU() + p3.getU())/3,
                                                     (p1.getV() + p2.getV() + p3.getV())/3);
    }
    private MyPolygonVertex p1;
    private MyPolygonVertex p2;
    private MyPolygonVertex p3;
    private MyPolygonVertex center;

    private double shiftX = 0;
    private double shiftY = 0;
    private double rotationAngle = 0;


}
