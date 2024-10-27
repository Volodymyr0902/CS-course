package com.shpp.cs.a.lectures.lec10;

import com.shpp.cs.a.console.TextProgram;

public class SuperArrays extends TextProgram {

    @Override
    public void run() {
        int[][] superArray = createArray(4, 5);
        showArray(superArray);
    }

    private void showArray(int[][] superArray) {
        for (int rows = 0; rows < superArray.length; rows++) {
            for (int cols = 0; cols < superArray[rows].length; cols++) {
                print(superArray[rows][cols]);
            }
            println();
        }
    }

    private int[][] createArray(int x, int y) {
        int[][] arr = new int[x][y];
        for (int rows = 0; rows < arr.length; rows++) {
            for (int cols = 0; cols < arr[rows].length; cols++) {
                arr[rows][cols] = rows + cols;
            }
        }
        return arr;
    }

}
