package ru.nsu.shmakov.model;

import ru.nsu.shmakov.data.*;

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
            double shiftX = polygons.get(i).getShiftX();
            double shiftY = polygons.get(i).getShiftY();
            MyPolygonVertex center = polygons.get(i).getCenter();
            double rotationAngle = polygons.get(i).getRotationAngle();


            MyPolygonVertex a = (MyPolygonVertex) polygons.get(i).getP1().clone();
            MyPolygonVertex b = (MyPolygonVertex) polygons.get(i).getP2().clone();
            MyPolygonVertex c = (MyPolygonVertex) polygons.get(i).getP3().clone();

            // Считаем сдвиги

            a.setX(a.getX() + (int)Math.round(shiftX));
            b.setX(b.getX() + (int) Math.round(shiftX));
            c.setX(c.getX() + (int) Math.round(shiftX));

            a.setY(a.getY() + (int) Math.round(shiftY));
            b.setY(b.getY() + (int) Math.round(shiftY));
            c.setY(c.getY() + (int) Math.round(shiftY));

            // Считаем вращение, относительно центра
            // По-хорошему добабавить бы матрицы
            a.setX((int)Math.round((a.getX() - center.getX()) * Math.cos(rotationAngle) - (a.getY() - center.getY()) * Math.sin(rotationAngle) + center.getX()));
            b.setX((int)Math.round((b.getX() - center.getX()) * Math.cos(rotationAngle) - (b.getY() - center.getY()) * Math.sin(rotationAngle) + center.getX()));
            c.setX((int)Math.round((c.getX() - center.getX()) * Math.cos(rotationAngle) - (c.getY() - center.getY()) * Math.sin(rotationAngle) + center.getX()));

            a.setY((int)Math.round((a.getX() - center.getX()) * Math.sin(rotationAngle) + (a.getY() - center.getY()) * Math.cos(rotationAngle) + center.getY()));
            b.setY((int)Math.round((b.getX() - center.getX()) * Math.sin(rotationAngle) + (b.getY() - center.getY()) * Math.cos(rotationAngle) + center.getY()));
            c.setY((int)Math.round((c.getX() - center.getX()) * Math.sin(rotationAngle) + (c.getY() - center.getY()) * Math.cos(rotationAngle) + center.getY()));

            //Affine mapping
            // отсортируем вершины полигона по Y

            MyGraphic2D mg2d = result.getMyGraphic2D();
            MyPolygonVertex tmpVertex;
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



                    Color color = null;
                    if (blended) {
                        color = new Color(getFilteredColor(u, v, texture), true);
                        mg2d.drawPixelRGBA(currentX, currentY, color);
                    }
                    else {
                        color = new Color(getFilteredColor(u, v, texture));
                        mg2d.drawPixelRGB(currentX, currentY, color);
                    }


                    u += du;
                    v += dv;
                }

            }

            if (bordered)
                mg2d.drawTriangle(new Point(a.getX(), a.getY()),
                                  new Point(b.getX(), b.getY()),
                                  new Point(c.getX(), c.getY()),
                                  Color.gray);
        }
    }

    private static int getFilteredColor(double u, double v, BufferedImage texture) {
        int intColor = 0;
        if (filterType == FilterType.NONE) {
            intColor = Filter.getInstance().nearestFilter (u, v, texture);
        }
        else if(filterType == FilterType.BILINEAR) {
            intColor = Filter.getInstance().bilinearFilter(u, v, texture);
        }
        return intColor;
    }

    private static boolean blended  = true;
    private static boolean bordered = true;
    private static FilterType filterType = FilterType.NONE;

    public static boolean isBlended() {
        return blended;
    }

    public static void setBlended(boolean blended) {
        Renderer.blended = blended;
    }

    public static FilterType getFilterType() {
        return filterType;
    }

    public static boolean isBordered() {
        return bordered;
    }

    public static void setBordered(boolean bordered) {
        Renderer.bordered = bordered;
    }

    public static void setFilterType(FilterType filterType) {
        Renderer.filterType = filterType;
    }
}
