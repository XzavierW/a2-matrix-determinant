package com.student_work;
import java.util.Random;

/**
 * DeterminantCalculation class - Assignment 2
 * CS 1342 - Principles of Computer Science 2
 * Author: Xzavier Washington
 * Date: 09/18/2025
 */
public class DeterminantCalculation {
    public static void main(String[] args) {
        Random rand = new Random();

        // A = 2x2
        Matrix A = new Matrix(2, 2);
        fillRandom(A, rand);
        A.print();
        System.out.println("Determinant of A = " + A.determinant());
        System.out.println("---------------------------------------");

        // B = 3x3
        Matrix B = new Matrix(3, 3);
        fillRandom(B, rand);
        B.print();
        System.out.println("Determinant of B = " + B.determinant());
        System.out.println("---------------------------------------");

        // C = 4x4
        Matrix C = new Matrix(4, 4);
        fillRandom(C, rand);
        C.print();
        System.out.println("Determinant of C = " + C.determinant());
        System.out.println("---------------------------------------");

        // D = 2x3 (not square → det = -1)
        Matrix D = new Matrix(2, 3);
        fillRandom(D, rand);
        D.print();
        System.out.println("Determinant of D = " + D.determinant());
        System.out.println("---------------------------------------");
    }

    // Helper method
    private static void fillRandom(Matrix m, Random rand) {
        int[][] arr = m.getElements();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = rand.nextInt(10);
            }
        }
    }
}
