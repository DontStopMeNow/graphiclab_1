package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by Иван on 25.02.2015.
 */
public class AnimationSpinerController {
    private Puzzle puzzle;

    public AnimationSpinerController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction(int t) {
        puzzle.setTime(t);
    }
}
