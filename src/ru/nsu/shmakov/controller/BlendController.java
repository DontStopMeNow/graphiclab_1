package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by Иван on 25.02.2015.
 */
public class BlendController {
    private Puzzle puzzle;

    public BlendController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction(boolean f) {
        puzzle.setBlended(f);
    }
}
