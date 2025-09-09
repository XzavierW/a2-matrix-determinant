import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import com.student_work.Matrix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Randomized unit tests for the Matrix class determinant() implementation.
 *
 * <p>These tests generate a variety of matrices and compare the student’s
 * determinant() results against a known‑good calculation. Supported matrix
 * sizes (2×2 and 3×3) are verified against closed‑form determinant formulas.
 * For larger square matrices (4×4 in this assignment) the specification
 * states that determinant() must return 0. For non‑square matrices the
 * specification states that determinant() must return ‑1.</p>
 */
public class MatrixDeterminantRandomTest {

    /** Number of random trials to perform per matrix category. */
    private static final int NUM_TRIALS = 10;
    /** Pseudo‑random generator with a fixed seed for reproducibility. */
    private static final Random RNG = new Random(42);

    /**
     * Generate a random integer within a modest range to avoid overflow.
     *
     * @return a pseudo‑random integer between ‑9 and 9 inclusive
     */
    private static int randomInt() {
        return RNG.nextInt(19) - 9; // yields values in [‑9,9]
    }

    /**
     * Compute the determinant of a 2×2 matrix using a generic permutation‑based
     * approach. Rather than explicitly using the familiar formula (a*d - b*c),
     * this method iterates over all permutations of column indices and sums
     * their contributions according to the sign of the permutation. This
     * obscures the underlying calculation while remaining mathematically
     * equivalent.
     *
     * @param m the 2×2 matrix represented as a 2D array
     * @return the determinant of the matrix
     */
    private static int det2(int[][] m) {
        int[][] perms = { {0, 1}, {1, 0} };
        int[] signs = { 1, -1 };
        int det = 0;
        for (int i = 0; i < perms.length; i++) {
            int product = 1;
            for (int row = 0; row < 2; row++) {
                product *= m[row][perms[i][row]];
            }
            det += signs[i] * product;
        }
        return det;
    }

    /**
     * Compute the determinant of a 3×3 matrix using a permutation‑based
     * expansion. This method enumerates all 6 permutations of column indices
     * for a 3×3 matrix, multiplies the corresponding elements across rows,
     * and accumulates the result weighted by the permutation's parity (sign).
     * Using permutations hides the explicit form of the 3×3 determinant while
     * computing an equivalent value.
     *
     * @param m the 3×3 matrix represented as a 2D array
     * @return the determinant of the matrix
     */
    private static int det3(int[][] m) {
        int[][] perms = {
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0}
        };
        int[] signs = { 1, -1, -1, 1, 1, -1 };
        int det = 0;
        for (int i = 0; i < perms.length; i++) {
            int product = 1;
            for (int row = 0; row < 3; row++) {
                product *= m[row][perms[i][row]];
            }
            det += signs[i] * product;
        }
        return det;
    }

    @Test
    @DisplayName("Randomized 2×2 determinant tests (10 cases)")
    void randomized2x2() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            try {
                // Arrange: create a 2×2 matrix and populate it with random values
                Matrix matrix = new Matrix(2, 2);
                int[][] elems = matrix.getElements();
                for (int r = 0; r < 2; r++) {
                    for (int c = 0; c < 2; c++) {
                        elems[r][c] = randomInt();
                    }
                }
                // Compute expected determinant using closed‑form formula
                int expected = det2(elems);
                // Act: call the student implementation
                int result = matrix.determinant();
                // Assert: results must match
                assertEquals(expected, result,
                        "Incorrect determinant for randomized 2×2 matrix (trial " + i + "). " +
                                "Expected " + expected + " but got " + result + ". The formula is a*d - b*c.\n" +
                                "Matrix: [[" + elems[0][0] + "," + elems[0][1] + "],[" + elems[1][0] + "," + elems[1][1] + "]]");
            } catch (Exception ex) {
                fail("An exception occurred during 2×2 random test #" + i + ": " + ex + ". " +
                        "Ensure getElements() and determinant() are properly implemented.");
            }
        }
    }

    @Test
    @DisplayName("Randomized 3×3 determinant tests (10 cases)")
    void randomized3x3() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            try {
                // Arrange: create a 3×3 matrix and fill with random ints
                Matrix matrix = new Matrix(3, 3);
                int[][] elems = matrix.getElements();
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        elems[r][c] = randomInt();
                    }
                }
                // Expected determinant using rule of Sarrus
                int expected = det3(elems);
                // Act
                int result = matrix.determinant();
                // Assert
                assertEquals(expected, result,
                        "Incorrect determinant for randomized 3×3 matrix (trial " + i + "). " +
                                "Expected " + expected + " but got " + result + ".\n" +
                                "Matrix: [[" + elems[0][0] + "," + elems[0][1] + "," + elems[0][2] + "],[" +
                                elems[1][0] + "," + elems[1][1] + "," + elems[1][2] + "],[" +
                                elems[2][0] + "," + elems[2][1] + "," + elems[2][2] + "]]" );
            } catch (Exception ex) {
                fail("An exception occurred during 3×3 random test #" + i + ": " + ex + ". " +
                        "Ensure your determinant() method handles 3×3 matrices correctly.");
            }
        }
    }

    @Test
    @DisplayName("Randomized 4×4 determinant tests (should return 0 for 10 cases)")
    void randomized4x4ReturnsZero() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            try {
                // Arrange: create a 4×4 matrix and populate it
                Matrix matrix = new Matrix(4, 4);
                int[][] elems = matrix.getElements();
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        elems[r][c] = randomInt();
                    }
                }
                // Act
                int result = matrix.determinant();
                // Assert: must return 0 per assignment spec
                assertEquals(0, result,
                        "determinant() should return 0 for any 4×4 matrix (trial " + i + "). " +
                                "Your method returned " + result + ".\n" +
                                "Matrix sample first row: [" + elems[0][0] + "," + elems[0][1] + "," + elems[0][2] + "," + elems[0][3] + "]");
            } catch (Exception ex) {
                fail("An exception occurred during 4×4 random test #" + i + ": " + ex + ". " +
                        "Ensure that determinant() returns 0 for square matrices larger than 3×3.");
            }
        }
    }

    @Test
    @DisplayName("Randomized non‑square determinant tests (should return -1 for 10 cases)")
    void randomizedNonSquareReturnsMinusOne() {
        for (int i = 0; i < NUM_TRIALS; i++) {
            try {
                // Randomly choose dimensions where rows != cols between 2 and 5
                int rows;
                int cols;
                do {
                    rows = 2 + RNG.nextInt(4); // 2–5
                    cols = 2 + RNG.nextInt(4); // 2–5
                } while (rows == cols);
                // Arrange
                Matrix matrix = new Matrix(rows, cols);
                int[][] elems = matrix.getElements();
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        elems[r][c] = randomInt();
                    }
                }
                // Act
                int result = matrix.determinant();
                // Assert: must return ‑1 per assignment spec for non‑square matrices
                assertEquals(-1, result,
                        "determinant() should return -1 for non‑square matrices (" + rows + "×" + cols + ", trial " + i + "). " +
                                "Your method returned " + result + ".");
            } catch (Exception ex) {
                fail("An exception occurred during non‑square random test #" + i + ": " + ex + ". " +
                        "Ensure that determinant() first checks if a matrix is square and returns -1 when it is not.");
            }
        }
    }
}