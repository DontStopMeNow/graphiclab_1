package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.MyPolygon;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kyb1k on 03.03.15.
 */
public class Animator {
    public static void makeLinerAnimation(ArrayList<MyPolygon> polygons, int t, int imageHeight, int imageWidth, double dAngle) {
        for (int i = 0; i < polygons.size(); ++i) {

            double dx = polygons.get(i).getCenter().getX() - imageWidth /2;
            double dy = polygons.get(i).getCenter().getY() - imageHeight/2;
            double length = Math.sqrt(dx*dx + dy*dy);

            dx /= length;
            dy /= length;

            polygons.get(i).setShiftX(dx*t);
            polygons.get(i).setShiftY(dy*t);

            polygons.get(i).setRotationAngle((dAngle * t)%(2*Math.PI));

        }
    }
}
