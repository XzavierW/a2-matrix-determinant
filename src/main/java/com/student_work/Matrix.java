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

    // Constructor required by assignment (MxN matrix, values start at 0)
    public Matrix(int M, int N) {
        this.rows = M;
        this.cols = N;
        data = new int[M][N]; // initialized to 0 by default
    }

    // Allow initialization with existing 2D array
    public Matrix(int[][] input) {
        this.rows = input.length;
        this.cols = input[0].length;
        data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(input[i], 0, data[i], 0, cols);
        }
    }

    // Return reference to internal 2D array
    public int[][] getElements() {
        return data;
    }

    // ✅ Add methods expected by test file
    public int numberOfRows() {
        return rows;
    }

    public int numberOfColumns() {
        return cols;
    }

    public boolean isSquareMatrix() {
        return rows == cols;
    }

    // Assignment originally had printMatrix(), but test expects print()
    public void print() {
        for (int[] row : data) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Determinant calculation as per assignment rules
    public int determinant() {
        if (rows != cols) {
            return -1; // Not a square matrix
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
            return 0; // Larger than 3x3 → return 0
        }
    }
}
