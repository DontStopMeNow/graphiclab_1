package ru.nsu.shmakov.model;

import ru.nsu.shmakov.controller.*;
import ru.nsu.shmakov.data.FilterType;
import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;
import ru.nsu.shmakov.data.MyPolygonVertex;
import ru.nsu.shmakov.view.ImagePanel;
import ru.nsu.shmakov.view.MainForm;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Иван on 25.02.2015.
 */
public class Puzzle {

    public Puzzle(String fileName){
        try {
            texture = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Cannot open texture image.");
            throw new RuntimeException("Cannot open texture image");
        }

        FilterController filterController = new FilterController(this);
        BlendController  blendController  = new BlendController (this);
        AnimationSpinerController animationSpinerController = new AnimationSpinerController(this);
        InitController initController = new InitController(this);
        StartStopAnimationController startStopAnimationController = new StartStopAnimationController(this);
        ImagePanelController imagePanelController = new ImagePanelController(this);

        mainForm = new MainForm();

        mainForm.setFilterController(filterController);
        mainForm.setBlendController (blendController );
        mainForm.setAnimationSpinerController(animationSpinerController);
        mainForm.setInitController(initController);
        mainForm.setStartStopAnimationController(startStopAnimationController);
        mainForm.setImagePanelController(imagePanelController);

        puzzleTimer = new PuzzleTimer(this);

        panel = mainForm.getImagePanel();
        slicer = new Slicer(4, 4, texture);
        image  = new MyImage(mainForm.getDrawPanelWidth(), mainForm.getDrawPanelHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        scale = 1;
        slicer.setScale(scale);
        polygons = slicer.sliceToTriangles((image.getWidth () - texture.getWidth ())/2,
                                           (image.getHeight() - texture.getHeight())/2);
        int imageWidth  = image.getWidth ();
        int imageHeight = image.getHeight();

        int bg[] = new int[imageHeight*imageWidth];
        for (int i = 0; i < imageHeight*imageWidth; ++i)
            bg[i] = Color.pink.getRGB();

        image.setRGB(0, 0, imageWidth, imageHeight, bg, 0, 0);

        int localTime;
        if(time >= 180)
            localTime = Math.abs(time - 360);
        else
            localTime = time;

        Animator.makeLinerAnimation(polygons, localTime, imageHeight, imageWidth, 0.1);
        Renderer.getInstance().setBlended(blended);
        Renderer.getInstance().setFilterType(filterType);
        Renderer.getInstance().renderPolygons(polygons, texture, image);


        panel.setImage(image);
        puzzleTimer.start();
    }

    public void reRender() {
        int imageWidth  = image.getWidth ();
        int imageHeight = image.getHeight();
        //if(animated)
        //    time = (time + 1) % 361;

        int bg[] = new int[imageHeight*imageWidth];
        for (int i = 0; i < imageHeight*imageWidth; ++i)
            bg[i] = Color.pink.getRGB();

        image.setRGB(0, 0, imageWidth, imageHeight, bg, 0, 0);

        int localTime;

        if(time >= 180)
            localTime = Math.abs(time - 360);
        else
            localTime = time;

        Animator.makeLinerAnimation(polygons, localTime, imageHeight, imageWidth, 0.1);

        Renderer.getInstance().setBlended(blended);
        Renderer.getInstance().setFilterType(filterType);
        Renderer.getInstance().renderPolygons(polygons, texture, image);



        panel.setImage(image);
        mainForm.repaint();
    }

    public void initAnimation() {
        time = 0;
        image = new MyImage(mainForm.getDrawPanelWidth(), mainForm.getDrawPanelHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        slicer.setScale(scale);
        polygons = slicer.sliceToTriangles((image.getWidth () - texture.getWidth ())/2,
                                           (image.getHeight() - texture.getHeight())/2);
        reRender();
    }

    public void startStopAnimation() {
        animated = !animated;
    }


    private FilterType filterType = FilterType.NONE;
    private boolean blended = false;

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
        reRender();
    }

    public boolean isBlended() {
        return blended;
    }

    public void setBlended(boolean blended) {
        this.blended = blended;
        reRender();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {

        time %= 361;
        this.time = time;
        //mainForm.setTime(time);
        reRender();
    }

    public void getPolygonInfo(int x, int y) {
        int imageCoordX = (x - (mainForm.getDrawPanelWidth () - image.getWidth ())/2);
        int imageCoordY = (y - (mainForm.getDrawPanelHeight() - image.getHeight())/2);

        //System.out.println(imageCoordX + " " + imageCoordY);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < polygons.size(); ++i) {
            double shiftX = polygons.get(i).getShiftX();
            double shiftY = polygons.get(i).getShiftY();
            MyPolygonVertex center = polygons.get(i).getCenter();
            double rotationAngle = polygons.get(i).getRotationAngle();


            MyPolygonVertex a = (MyPolygonVertex) polygons.get(i).getP1().clone();
            MyPolygonVertex b = (MyPolygonVertex) polygons.get(i).getP2().clone();
            MyPolygonVertex c = (MyPolygonVertex) polygons.get(i).getP3().clone();

            // Считаем сдвиги

            a.setX(a.getX() + (int) Math.round(shiftX));
            b.setX(b.getX() + (int) Math.round(shiftX));
            c.setX(c.getX() + (int) Math.round(shiftX));

            a.setY(a.getY() + (int) Math.round(shiftY));
            b.setY(b.getY() + (int) Math.round(shiftY));
            c.setY(c.getY() + (int) Math.round(shiftY));

            // Считаем вращение, относительно центра
            // По-хорошему добабавить бы матрицы
            double cos = Math.cos(rotationAngle);
            double sin = Math.sin(rotationAngle);

            int aX = a.getX() - center.getX();
            int aY = a.getY() - center.getY();
            a.setX((int)Math.round(aX * cos - aY * sin + center.getX()));
            a.setY((int)Math.round(aX * sin + aY * cos + center.getY()));

            int bX = b.getX() - center.getX();
            int bY = b.getY() - center.getY();
            b.setX((int)Math.round(bX * cos - bY * sin + center.getX()));
            b.setY((int)Math.round(bX * sin + bY * cos + center.getY()));

            int cX = c.getX() - center.getX();
            int cY = c.getY() - center.getY();
            c.setX((int)Math.round(cX * cos - cY * sin + center.getX()));
            c.setY((int)Math.round(cX * sin + cY * cos + center.getY()));

            int r1 = (a.x - imageCoordX)*(b.y - a.y) - (b.x - a.x)*(a.y - imageCoordY);
            int r2 = (b.x - imageCoordX)*(c.y - b.y) - (c.x - b.x)*(b.y - imageCoordY);
            int r3 = (c.x - imageCoordX)*(a.y - c.y) - (a.x - c.x)*(c.y - imageCoordY);




            if (r1*r2 >= 0 && r2*r3 >=0) {
                sb.append(polygons.get(i).getCountOfBlendedPixes());
                sb.append('/');
                sb.append(polygons.get(i).getCountOfPixes());
                sb.append(' ');
                sb.append(polygons.get(i).getCountOfPixesInEdges());
                sb.append("\n");
                break;
            }


        }
        mainForm.setState(sb.toString());
        //System.out.println(imageCoordX + " " + imageCoordY);
    }

    public boolean isAnimated() {
        return animated;
    }

    private boolean animated = false;
    private Slicer slicer;
    private MainForm mainForm;
    private BufferedImage texture;
    private MyImage image;
    private ImagePanel panel;
    private PuzzleTimer puzzleTimer;
    int scale = 1;
    ArrayList<MyPolygon> polygons;
    private int time;

}
