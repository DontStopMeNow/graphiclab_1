package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.MyGraphic2D;
import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Иван on 23.02.2015.
 */
public class Renderer {
    private static Renderer ourInstance = new Renderer();

    public static Renderer getInstance() {
        return ourInstance;
    }

    public static void renderPolygons(ArrayList<MyPolygon> polygons, MyImage texture, MyImage result) {
        if(0 == polygons.size())
            return;

        for(int i = 0; i < polygons.size(); ++i) {

            MyGraphic2D mg2d = result.getMyGraphic2D();
            mg2d.drawTriangle(polygons.get(i).getXY1(), polygons.get(i).getXY2(), polygons.get(i).getXY3(), Color.gray);
        }
    }

    private Renderer() {
    }
}
