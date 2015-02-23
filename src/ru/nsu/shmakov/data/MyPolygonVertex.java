package ru.nsu.shmakov.data;

/**
 * Created by Иван on 23.02.2015.
 */
public class MyPolygonVertex implements Cloneable {
    public int x;
    public int y;
    public double u;
    public double v;

    public MyPolygonVertex(int x, int y, double u, double v) {
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
    }

    public MyPolygonVertex(MyPolygonVertex another) {
        x = another.x;
        y = another.y;
        u = another.u;
        v = another.v;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }
}
