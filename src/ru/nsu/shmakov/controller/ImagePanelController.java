package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by kyb1k on 03.03.15.
 */
public class ImagePanelController {
    private Puzzle puzzle;

    public ImagePanelController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction(int x, int y) {
        puzzle.getPolygonInfo(x, y);
    }
}
