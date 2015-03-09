package ru.nsu.shmakov.data;

import java.awt.*;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kyb1k on 03.03.15.
 */
public class BresenhamsUtil {
    public static Map<Integer, ArrayList<Integer>> getBresenhamLine(MyPolygonVertex a, MyPolygonVertex b, Point countOfPixes) {
        //ArrayList<Point> result = new ArrayList<Point>();
        int count = 0;
        Map<Integer, ArrayList<Integer> > res = new HashMap<Integer, ArrayList<Integer>>();

        boolean steep = false;
        int tmp = 0;
        int x1 = (int) Math.round(a.getX());
        int y1 = (int) Math.round(a.getY());

        int x2 = (int) Math.round(b.getX());
        int y2 = (int) Math.round(b.getY());

        if (Math.abs(x1 - x2) < Math.abs(y1 - y2)) {
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
        for (int x = x1; x <= x2; x++) {
            if (steep) {
                if (!res.containsKey(x)) {
                    res.put(x, new ArrayList<Integer>());
                }
                res.get(x).add(y);
                //result.add(new Point(y, x));
            } else {
                if (!res.containsKey(y)) {
                    res.put(y, new ArrayList<Integer>());
                }
                res.get(y).add(x);
                //result.add(new Point(x, y));
            }
            //System.out.print("(" + result.get(countOfPixes).getX() +" ; "+ result.get(countOfPixes).getY() +")");
            ++ count;


            error += derror;

            if (error > dx) {
                y += (y2 > y1 ? 1:-1);
                error -= dx*2;
            }
        }
        /*
        for (int i : res.keySet()) {

            System.out.print(i + " : " + res.get(i).toString() + "; ");
        }
        */
        //System.out.println(countOfPixes);
        //System.out.println();
        countOfPixes.x = count;
        return res;
    }
}
