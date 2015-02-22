package ru.nsu.shmakov.data;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

/**
 * Created by Иван on 22.02.2015.
 */
public class MyImage extends BufferedImage {
    public MyImage(int width, int height, int imageType) {
        super(width, height, imageType);
        myGraphic2D = new MyGraphic2D(this);
    }

    public MyImage(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
        myGraphic2D = new MyGraphic2D(this);
    }

    public MyImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
        myGraphic2D = new MyGraphic2D(this);
    }

    public MyGraphic2D getMyGraphic2D() {
        return myGraphic2D;
    }

    private MyGraphic2D myGraphic2D;
}
