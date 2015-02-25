package ru.nsu.shmakov.model;

import ru.nsu.shmakov.controller.*;
import ru.nsu.shmakov.data.FilterType;
import ru.nsu.shmakov.data.MyImage;
import ru.nsu.shmakov.data.MyPolygon;
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

        mainForm = new MainForm();

        mainForm.setFilterController(filterController);
        mainForm.setBlendController (blendController );
        mainForm.setAnimationSpinerController(animationSpinerController);
        mainForm.setInitController(initController);
        mainForm.setStartStopAnimationController(startStopAnimationController);

        panel = mainForm.getImagePanel();
        slicer = new Slicer(4, 4, texture);
        image = new MyImage(400, 400, BufferedImage.TYPE_4BYTE_ABGR);

        int scale = 2;
        slicer.setScale(scale);
        polygons = slicer.sliceToTriangles((image.getWidth () - texture.getWidth ())/2,
                                           (image.getHeight() - texture.getHeight())/2);
        int imageWidth  = image.getWidth ();
        int imageHeight = image.getHeight();

        int bg[] = new int[imageHeight*imageWidth];
        for (int i = 0; i < imageHeight*imageWidth; ++i)
            bg[i] = Color.pink.getRGB();

        image.setRGB(0, 0, imageWidth, imageHeight, bg, 0, 0);

        Renderer.getInstance().setBlended(blended);
        Renderer.getInstance().setFilterType(filterType);
        Renderer.getInstance().renderPolygons(polygons, texture, image);


        panel.setImage(image);

    }

    public void reRender() {
        int imageWidth  = image.getWidth ();
        int imageHeight = image.getHeight();

        int bg[] = new int[imageHeight*imageWidth];
        for (int i = 0; i < imageHeight*imageWidth; ++i)
            bg[i] = Color.pink.getRGB();

        image.setRGB(0, 0, imageWidth, imageHeight, bg, 0, 0);

        Renderer.getInstance().setBlended(blended);
        Renderer.getInstance().setFilterType(filterType);
        Renderer.getInstance().renderPolygons(polygons, texture, image);

        panel.setImage(image);
        mainForm.repaint();
    }

    public void initAnimation() {

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
        this.time = time;
    }

    private boolean animated = false;
    private Slicer slicer;
    private MainForm mainForm;
    private BufferedImage texture;
    private MyImage image;
    private ImagePanel panel;
    ArrayList<MyPolygon> polygons;
    private int time;

}
