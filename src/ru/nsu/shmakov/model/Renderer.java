package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.MyGraphic2D;
import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;
import ru.nsu.shmakov.data.MyPolygonVertex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Иван on 23.02.2015.
 */
public class Renderer {
    private static Renderer ourInstance = new Renderer();

    public static Renderer getInstance() {
        return ourInstance;
    }

    public static void renderPolygons(ArrayList<MyPolygon> polygons, BufferedImage texture, MyImage result) {
        if(0 == polygons.size())
            return;

        for(int i = 0; i < polygons.size(); ++i) {

            //Affine mapping
            MyGraphic2D mg2d = result.getMyGraphic2D();


            MyPolygonVertex a = (MyPolygonVertex) polygons.get(i).getP1().clone();
            MyPolygonVertex b = (MyPolygonVertex) polygons.get(i).getP2().clone();
            MyPolygonVertex c = (MyPolygonVertex) polygons.get(i).getP3().clone();

            MyPolygonVertex tmpVertex;

            // отсортируем вершины полигона по Y
            if (a.getY() > b.getY()) {
                tmpVertex = a;
                a = b;
                b = tmpVertex;
            }
            if (a.getY() > c.getY()) {
                tmpVertex = a;
                a = c;
                c = tmpVertex;
            }
            if (b.getY() > c.getY()) {
                tmpVertex = b;
                b = c;
                c = tmpVertex;
            }
            // посчитаем du/dx и dv/dx
            // считаем по самой длинной линии (т.е. проходящей через вершину B, в общем случае через высоту)

            double k = (double)(b.getY() - a.getY()) / (c.getY() - a.getY());
            double x_start = a.getX() + (c.getX() - a.getX()) * k;
            double u_start = a.getU() + (c.getU() - a.getU()) * k; // U
            double v_start = a.getV() + (c.getV() - a.getV()) * k; // V

            // todo: запилить нормальный класс вершина с 4 координатами, а не эту жесть с поинтами [+]

            double x_end = b.getX();
            double u_end = b.getU();
            double v_end = b.getV();
            double du = (u_start - u_end) / (x_start - x_end);
            double dv = (v_start - v_end) / (x_start - x_end);
            int currentX = 0;
            int currentY = 0;

            for (currentY = (int)a.getY(); currentY <= (int)c.getY(); currentY++) {
                // считаем x/u/v для стороны AC
                k = (double)(currentY - a.getY()) / (c.getY() - a.getY());
                x_start = a.getX() + (c.getX() - a.getX()) * k;
                u_start = a.getU() + (c.getU() - a.getU()) * k;
                v_start = a.getV() + (c.getV() - a.getV()) * k;

                if (currentY >= b.getY()) {
                    // мы находимся ниже вершины B, считаем x/u/v для стороны BC
                    k = (double)(currentY - b.getY()) / (c.getY() - b.getY());
                    x_end = b.getX() + (c.getX() - b.getX()) * k;
                    u_end = b.getU() + (c.getU() - b.getU()) * k;
                    v_end = b.getV() + (c.getV() - b.getV()) * k;
                } else {
                    // мы находимся выше вершины B, считаем x/u/v для стороны AC
                    k = (double)(currentY - a.getY()) / (b.getY() - a.getY());
                    x_end = a.getX() + (b.getX() - a.getX()) * k;
                    u_end = a.getU() + (b.getU() - a.getU()) * k;
                    v_end = a.getV() + (b.getV() - a.getV()) * k;
                }
                double tmp = 0;
                // x_start должен находиться левее x_end
                if (x_start > x_end) {
                    tmp = x_start; x_start = x_end; x_end = tmp;
                    tmp = u_start; u_start = u_end; u_end = tmp;
                    tmp = v_start; v_start = v_end; v_end = tmp;
                }

                // готовим u, v к текстурированию
                double u = u_start;
                double v = v_start;

                // текстурируем строку
                for (currentX = (int)x_start; currentX <= (int)x_end; currentX++) {

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

                    Color color = new Color(texture.getRGB(intU ,intV));

                    mg2d.drawPixel(currentX, currentY, color);
                    u += du;
                    v += dv;
                }

            }

            //Graphics2D g2 = result.createGraphics();
            //g2.drawImage(texture, 2, 2, new Color(255,255,255), null);


            mg2d.drawTriangle(new Point(polygons.get(i).getP1().getX(), polygons.get(i).getP1().getY()),
                              new Point(polygons.get(i).getP2().getX(), polygons.get(i).getP2().getY()),
                              new Point(polygons.get(i).getP3().getX(), polygons.get(i).getP3().getY()),
                              Color.gray);
        }
    }

    private Renderer() {
    }
}
