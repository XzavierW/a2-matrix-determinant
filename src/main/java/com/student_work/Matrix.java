package com.student_work;

/**
 * Matrix class - Assignment 2
 * CS 1342 - Principles of Computer Science 2
 * Author: Xzavier Washington
 * Date: 09/18/2025
 */
public class Matrix {
    private int[][] data;
    private int rows, cols;

    // Constructor required by assignment
    public Matrix(int M, int N) {
        this.rows = M;
        this.cols = N;
        data = new int[M][N];
    }

    // Constructor for array input
    public Matrix(int[][] input) {
        this.rows = input.length;
        this.cols = input[0].length;
        data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(input[i], 0, data[i], 0, cols);
        }
    }

    // Return internal data
    public int[][] getElements() {
        return data;
    }

    public int numberOfRows() {
        return rows;
    }

    public int numberOfColumns() {
        return cols;
    }

    public boolean isSquareMatrix() {
        return rows == cols;
    }

    public void print() {
        for (int[] row : data) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Determinant as per assignment
    public int determinant() {
        if (rows != cols) {
            return -1;
        }

        if (rows == 1) {
            return data[0][0];
        } else if (rows == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        } else if (rows == 3) {
            int a = data[0][0], b = data[0][1], c = data[0][2];
            int d = data[1][0], e = data[1][1], f = data[1][2];
            int g = data[2][0], h = data[2][1], i = data[2][2];
            return a * (e * i - f * h)
                 - b * (d * i - f * g)
                 + c * (d * h - e * g);
        } else {
            return 0;
        }
    }
}
