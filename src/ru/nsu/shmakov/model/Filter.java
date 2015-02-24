package ru.nsu.shmakov.model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Иван on 25.02.2015.
 */
public class Filter {
    private static Filter ourInstance = new Filter();

    public static Filter getInstance() {
        return ourInstance;
    }

    private Filter() {
    }

    public int nearestFilter(double u, double v, BufferedImage texture) {
        int intU = (int)Math.round(u*texture.getWidth());
        int intV = (int)Math.round(v*texture.getHeight());
        if(intU >= texture.getWidth())
            intU = texture.getWidth() - 1;
        if(0 > intU)
            intU = 0;

        if(intV >= texture.getHeight())
            intV = texture.getHeight() - 1;
        if(0 > intV)
            intV = 0;

        int color = texture.getRGB(intU, intV);
        return color;
    }

    public int bilinearFilter(double u, double v, BufferedImage texture) {
        u *= texture.getWidth() ;
        v *= texture.getHeight();

        int intU = (int)Math.floor(u);
        int intV = (int)Math.floor(v);

        if(intU >= texture.getWidth())
            intU = texture.getWidth() - 1;
        if(0 > intU)
            intU = 0;

        if(intV >= texture.getHeight())
            intV = texture.getHeight() - 1;
        if(0 > intV)
            intV = 0;

        double u_ratio = u - intU;
        double v_ratio = v - intV;
        double u_opposite = 1 - u_ratio;
        double v_opposite = 1 - v_ratio;

        Color colorTL;
        Color colorTR;
        Color colorBL;
        Color colorBR;

        colorTL = new Color(texture.getRGB(intU    , intV    ), true);

        if (intU + 1 == texture.getWidth()) {
            colorTR = new Color(texture.getRGB(intU    , intV    ), true);
        }
        else {
            colorTR = new Color(texture.getRGB(intU + 1, intV    ), true);
        }

        if (intV + 1 == texture.getHeight()) {
            colorBL = new Color(texture.getRGB(intU    , intV    ), true);
        }
        else {
            colorBL = new Color(texture.getRGB(intU    , intV + 1), true);
        }

        if (intV + 1 == texture.getHeight() || intU + 1 == texture.getWidth()) {
            colorBR = new Color(texture.getRGB(intU    , intV    ), true);
        }
        else {
            colorBR = new Color(texture.getRGB(intU + 1, intV + 1), true);
        }



        int intR = (int) Math.floor(((double)colorTL.getRed() * u_opposite  + (double)colorTR.getRed() * u_ratio) * v_opposite +
                                    ((double)colorBL.getRed() * u_opposite  + (double)colorBR.getRed() * u_ratio) * v_ratio);

        int intG = (int) Math.floor(((double)colorTL.getGreen() * u_opposite  + (double)colorTR.getGreen() * u_ratio) * v_opposite +
                                    ((double)colorBL.getGreen() * u_opposite  + (double)colorBR.getGreen() * u_ratio) * v_ratio);

        int intB = (int) Math.floor(((double)colorTL.getBlue() * u_opposite  + (double)colorTR.getBlue() * u_ratio) * v_opposite +
                                    ((double)colorBL.getBlue() * u_opposite  + (double)colorBR.getBlue() * u_ratio) * v_ratio);

        int intA = (int) Math.floor(((double)colorTL.getAlpha() * u_opposite  + (double)colorTR.getAlpha() * u_ratio) * v_opposite +
                                    ((double)colorBL.getAlpha() * u_opposite  + (double)colorBR.getAlpha() * u_ratio) * v_ratio);

        return (intA << 24) | (intR << 16) | (intG << 8) | intB;
    }

}
