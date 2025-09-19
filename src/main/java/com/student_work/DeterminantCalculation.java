package com.student_work;
import java.util.Random;


/**
DeterminantCalculation class - Assignment 2
 CS 1342 - Principles of Computer Science 2
 Author: Xzavier Washington
 Date: 09/18/2025
*/

public class DeterminantCalculation {
    public static void main(String[] args) {
        Random rand = new Random();

        // A = 2x2
        Matrix A = new Matrix(2, 2, rand);
        A.printMatrix();
        System.out.println("Determinant of A = " + A.determinant());
        System.out.println("---------------------------------------");

        // B = 3x3
        Matrix B = new Matrix(3, 3, rand);
        B.printMatrix();
        System.out.println("Determinant of B = " + B.determinant());
        System.out.println("---------------------------------------");

        // C = 4x4
        Matrix C = new Matrix(4, 4, rand);
        C.printMatrix();
        System.out.println("Determinant of C = " + C.determinant());
        System.out.println("---------------------------------------");

        // D = 2x3 (not square → det = -1)
        Matrix D = new Matrix(2, 3, rand);
        D.printMatrix();
        System.out.println("Determinant of D = " + D.determinant());
        System.out.println("---------------------------------------");
    }
}
