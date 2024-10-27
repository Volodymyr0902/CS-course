package com.shpp.cs.a.lectures.midexam.examPlants;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Plants extends WindowProgram implements PlantsConstants, ComponentListener {

    private Random random;
    private GRect[][] board;
    private Color[][] boardMap;
    private GRect clickedCell;

    public void run() {
        addComponentListener(this);
        random = new Random();
        board = new GRect[ROWS_NUM][COLS_NUM];
        boardMap = new Color[ROWS_NUM][COLS_NUM];

        addMouseListeners();
        setSeeds();
        drawBoard();

        while (true) {
            pause(1000);
            fallOneRow();
            redraw();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setWater(e.getX(), e.getY());
    }

    public void redraw() {
        removeAll();
        drawBoard();
    }

    public void setSeeds() {
        int randomRow, randomCol;
        boolean[] unavailableCells = new boolean[COLS_NUM];

        for (int i = 0; i < SEEDS_NUM; i++) {
            do {
                randomRow = random.nextInt(ROWS_NUM);
                randomCol = random.nextInt(COLS_NUM);
            } while (unavailableCells[randomCol]);

            boardMap[randomRow][randomCol] = SEED_COLOR;
            unavailableCells[randomCol] = true;
        }
    }

    public void drawBoard() {
        int cellWidth = getWidth() / COLS_NUM;
        int cellHeight = getHeight() / ROWS_NUM;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                drawCell(row, col, cellWidth, cellHeight);
            }
        }
    }

    private void drawCell(int row, int col, int cellWidth, int cellHeight) {
        int xOffset = col * cellWidth;
        int yOffset = row * cellHeight;

        board[row][col] = new GRect(xOffset, yOffset, cellWidth, cellHeight);
        board[row][col].setFillColor(pickFillColor(row, col));
        board[row][col].setFilled(true);
        add(board[row][col]);
    }

    public Color pickFillColor(int row, int col) {
        if (boardMap[row][col] == SEED_COLOR) {
            return SEED_COLOR;
        } else if (boardMap[row][col] == WATER_COLOR) {
            return WATER_COLOR;
        } else if (boardMap[row][col] == GROWN_SEED_COLOR) {
            return GROWN_SEED_COLOR;
        } else {
            return AIR_COLOR;
        }
    }

    public void fallOneRow() {
        Color[][] latestBoardMap = fillMapCopy();
        for (int i = 0; i < boardMap.length; i++) {
            for (int j = 0; j < boardMap[i].length; j++) {

                if (latestBoardMap[i][j] == WATER_COLOR) {

                    if (i < boardMap.length - 1) {
                        if (latestBoardMap[i + 1][j] == SEED_COLOR && i == boardMap.length - 2) {
                            boardMap[i][j] = GROWN_SEED_COLOR;
                            boardMap[i + 1][j] = GROWN_SEED_COLOR;
                        } else if (latestBoardMap[i + 1][j] == GROWN_SEED_COLOR) {
                            boardMap[i][j] = GROWN_SEED_COLOR;
                        } else {
                            boardMap[i][j] = AIR_COLOR;
                            boardMap[i + 1][j] = WATER_COLOR;
                        }
                    } else {
                        boardMap[i][j] = AIR_COLOR;
                        boardMap[i + 1][j] = WATER_COLOR;
                    }

                } else if (latestBoardMap[i][j] == SEED_COLOR && i < boardMap.length - 1) {
                    boardMap[i][j] = AIR_COLOR;
                    boardMap[i + 1][j] = SEED_COLOR;
                }
            }
        }
    }

    private Color[][] fillMapCopy() {
        Color[][] latestBoardMap = new Color[ROWS_NUM][COLS_NUM];
        for (int i = 0; i < boardMap.length; i++) {
            for (int j = 0; j < boardMap[i].length; j++) {
                latestBoardMap[i][j] = boardMap[i][j];
            }
        }
        return latestBoardMap;
    }

    public void setWater(double x, double y) {
        clickedCell = (GRect) getElementAt(x, y);
        clickedCell.setFillColor(WATER_COLOR);
        clickedCell.setFilled(true);

        outer:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getBounds().equals(clickedCell.getBounds())) {
                    boardMap[i][j] = WATER_COLOR;
                    break outer;
                }
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        redraw();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
