package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by Иван on 25.02.2015.
 */
public class InitController {
    private Puzzle puzzle;

    public InitController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction() {
        puzzle.initAnimation();
        puzzle.setTime(0);
    }
}
