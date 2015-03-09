package ru.nsu.shmakov.model;

/**
 * Created by kyb1k on 03.03.15.
 */
public class PuzzleTimer extends Thread {
    @Override
    public void run()
    {
        while(true) {
            if (puzzle.isAnimated()) {
                puzzle.setTime((puzzle.getTime() + 1));
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public PuzzleTimer(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    private Puzzle puzzle;
}
