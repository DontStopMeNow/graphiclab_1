package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.MyPolygon;
import ru.nsu.shmakov.data.MyPolygonVertex;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Иван on 22.02.2015.
 */
public class Slicer {
    public Slicer(int cols, int rows, BufferedImage image) {
        if (0 >= cols || 0 >= rows)
            throw new RuntimeException("Invalid cols/rows");
        if (null == image)
            throw new NullPointerException();

        this.cols = cols;
        this.rows = rows;
        this.image = image;
    }

    public ArrayList<MyPolygon> sliceToTriangles(int shiftX, int shiftY) {
        ArrayList<MyPolygon> polygons = new ArrayList<MyPolygon>();

        int imageHeight = image.getHeight();
        int imageWidth  = image.getWidth ();

        double cellWidth  = (double)imageWidth /cols * scale;
        double cellHeight = (double)imageHeight/rows * scale;
        double du = 1./cols;
        double dv = 1./rows;

        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {





                MyPolygonVertex v1 = new MyPolygonVertex((int)Math.round(c    *cellWidth) + shiftX/scale, (int)Math.round(r    *cellHeight) + shiftY/scale,  c      * du, r * dv);
                MyPolygonVertex v2 = new MyPolygonVertex((int)Math.round((c+1)*cellWidth) + shiftX/scale, (int)Math.round(r    *cellHeight) + shiftY/scale, (c + 1) * du, r * dv);
                MyPolygonVertex v3 = new MyPolygonVertex((int)Math.round(c    *cellWidth) + shiftX/scale, (int)Math.round((r+1)*cellHeight) + shiftY/scale,  c      * du, (r + 1) * dv);
                MyPolygonVertex v4 = new MyPolygonVertex((int)Math.round((c+1)*cellWidth) + shiftX/scale, (int)Math.round((r+1)*cellHeight) + shiftY/scale, (c + 1) * du, (r + 1) * dv);

                MyPolygon p1 = new MyPolygon(v1, v2, v3);
                MyPolygon p2 = new MyPolygon(v2, v3, v4);

                //p1.setRotationAngle(Math.PI/2);
                //p2.setRotationAngle(Math.PI/7);

                polygons.add(p1);
                polygons.add(p2);
            }
        }

        return polygons;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public BufferedImage getImage() {
        return image;
    }

    private int cols = 1;
    private int rows = 1;
    private int scale = 1;

    public void setCols(int cols) {
        if (0 >= cols)
            throw new RuntimeException("Invalid cols");
        this.cols = cols;
    }

    public void setRows(int rows) {
        if (0 >= rows)
            throw new RuntimeException("Invalid rows");
        this.rows = rows;
    }

    public void setImage(BufferedImage image) {
        if (null == image)
            throw new NullPointerException();
        this.image = image;
    }

    public int getScale() {

        return scale;
    }

    public void setScale(int scale) {
        if (0 >= scale)
            throw new RuntimeException("Invalid scale");
        this.scale = scale;
    }

    private BufferedImage image;
}
