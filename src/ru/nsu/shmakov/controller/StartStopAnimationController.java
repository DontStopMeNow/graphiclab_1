package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by Иван on 25.02.2015.
 */
public class StartStopAnimationController {
    private Puzzle puzzle;

    public StartStopAnimationController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction() {
        puzzle.startStopAnimation();
    }
}
