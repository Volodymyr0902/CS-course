package com.shpp.cs.a.lectures.lec08;

public class Arrays {
    public static void main(String[] args) {

        int[][] twoD = new int[4][5];
        int i, j, k = 0;

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 5; j++) {
                twoD[i][j] = k;
                System.out.print(twoD[i][j] + " ");
                k++;
            }
            System.out.println();
        }

        //------------------------------------------//

        int[][] nonReg = new int[4][];
        nonReg[0] = new int[1];
        nonReg[1] = new int[2];
        nonReg[2] = new int[3];
        nonReg[3] = new int[4];

        k = 1;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < nonReg[i].length; j++) {
                nonReg[i][j] = k;
                System.out.print(nonReg[i][j] + " ");
                k++;
            }
            System.out.println();
        }

    }
}