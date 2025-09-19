package com.student_work;
import java.util.Random;


/**
 Matrix class - Assignment 2
 CS 1342 - Principles of Computer Science 2
 Author: Xzavier Washington
 Date: 09/18/2025
*/

public class Matrix {
    private int[][] data;
    private int rows, cols;

    // Constructor for given dimensions with random values
    public Matrix(int rows, int cols, Random rand) {
        this.rows = rows;
        this.cols = cols;
        data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = rand.nextInt(10); // random numbers 0–9
            }
        }
    }

    // Existing constructor if you want to supply fixed data
    public Matrix(int[][] input) {
        this.rows = input.length;
        this.cols = input[0].length;
        data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(input[i], 0, data[i], 0, cols);
        }
    }

    public void printMatrix() {
        for (int[] row : data) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Determinant calculation (basic version, recursive)
    public int determinant() {
        if (rows != cols) {
            return -1; // Not a square matrix
        }
        return determinantRecursive(data);
    }

    private int determinantRecursive(int[][] mat) {
        int n = mat.length;
        if (n == 1) return mat[0][0];
        if (n == 2) return mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0];

        int det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * mat[0][col] * determinantRecursive(minor(mat, 0, col));
        }
        return det;
    }

    private int[][] minor(int[][] mat, int row, int col) {
        int n = mat.length;
        int[][] minor = new int[n-1][n-1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c++] = mat[i][j];
            }
            r++;
        }
        return minor;
    }
}
