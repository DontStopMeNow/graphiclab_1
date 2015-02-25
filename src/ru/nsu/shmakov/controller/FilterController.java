package ru.nsu.shmakov.controller;

import ru.nsu.shmakov.data.FilterType;
import ru.nsu.shmakov.model.Puzzle;

/**
 * Created by Иван on 25.02.2015.
 */
public class FilterController {
    private Puzzle puzzle;

    public FilterController(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void doAction(boolean f) {
        if(f == true)
            puzzle.setFilterType(FilterType.BILINEAR);
        else
            puzzle.setFilterType(FilterType.NONE);
    }
}
