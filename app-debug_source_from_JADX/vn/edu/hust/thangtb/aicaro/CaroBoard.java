package vn.edu.hust.thangtb.aicaro;

import java.lang.reflect.Array;

public class CaroBoard {
    private int size;
    private int[][] square = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{this.size, this.size}));

    public CaroBoard(int n) {
        this.size = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.square[i][j] = 0;
            }
        }
    }

    public void set(int a, int b, int x) {
        this.square[a][b] = x;
    }

    public int[][] getSquare() {
        return this.square;
    }

    public void setSquare(int[][] square) {
        this.square = square;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
