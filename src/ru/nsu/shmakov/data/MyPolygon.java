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
    }

    public MyPolygonVertex getP1() {
        return p1;
    }

    public void setP1(MyPolygonVertex p1) {
        this.p1 = p1;
    }

    public MyPolygonVertex getP2() {
        return p2;
    }

    public void setP2(MyPolygonVertex p2) {
        this.p2 = p2;
    }

    public MyPolygonVertex getP3() {
        return p3;
    }

    public void setP3(MyPolygonVertex p3) {
        this.p3 = p3;
    }

    private MyPolygonVertex p1;
    private MyPolygonVertex p2;
    private MyPolygonVertex p3;
    private MyPolygonVertex center;
}
