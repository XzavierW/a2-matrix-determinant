import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.student_work.Matrix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatrixDeterminantTest {

    @Test
    @DisplayName("2x2 example: [[1,2],[3,4]] -> det = -2")
    void determinant2x2_example() {
        try {
            // Arrange: initialize the matrix and set its elements
            Matrix A = new Matrix(2, 2);
            int[][] elements = A.getElements();
            elements[0][0] = 1; elements[0][1] = 2;
            elements[1][0] = 3; elements[1][1] = 4;
            // Act
            int result = A.determinant();
            // Assert
            assertEquals(-2, result,
                    "Incorrect determinant for 2x2 matrix. " +
                            "For a 2x2 matrix [[a,b],[c,d]], the determinant is a*d - b*c. " +
                            "Given [[1,2],[3,4]] it should be -2, but your method returned " + result + ".");
        } catch (Exception ex) {
            // Catch any unexpected exception and fail with an informative message
            fail("An exception occurred while testing the 2x2 case: " + ex + ". " +
                    "Ensure getElements() returns the internal array and determinant() is implemented correctly.");
        }
    }

    @Test
    @DisplayName("3x3 example: [[2,1,3],[0,1,4],[5,2,0]] -> det = -11")
    void determinant3x3_example() {
        try {
            // Arrange
            Matrix B = new Matrix(3, 3);
            int[][] elements = B.getElements();
            elements[0][0] = 2; elements[0][1] = 1; elements[0][2] = 3;
            elements[1][0] = 0; elements[1][1] = 1; elements[1][2] = 4;
            elements[2][0] = 5; elements[2][1] = 2; elements[2][2] = 0;
            // Act
            int result = B.determinant();
            // Assert
            assertEquals(-11, result,
                    "Incorrect determinant for 3x3 matrix. " +
                            "For a 3x3 matrix [[a,b,c],[p,q,r],[x,y,z]], use the formula " +
                            "a*q*z + b*r*x + c*p*y - a*r*y - b*p*z - c*q*x. " +
                            "Given [[2,1,3],[0,1,4],[5,2,0]] the determinant should be -11, " +
                            "but your method returned " + result + ".");
        } catch (Exception ex) {
            fail("An exception occurred while testing the 3x3 case: " + ex + ". " +
                    "Ensure your determinant() implementation follows the provided 3x3 formula and " +
                    "that getElements() properly exposes the internal array.");
        }
    }

    @Test
    @DisplayName("4x4 (square but unsupported) -> det = 0")
    void determinant4x4_returnsZero() {
        try {
            // Arrange
            Matrix C = new Matrix(4, 4);
            int[][] elements = C.getElements();
            elements[0][0] = 7;  elements[0][1] = 4;  elements[0][2] = 1;  elements[0][3] = 6;
            elements[1][0] = 3;  elements[1][1] = 9;  elements[1][2] = 2;  elements[1][3] = 8;
            elements[2][0] = 5;  elements[2][1] = 5;  elements[2][2] = 5;  elements[2][3] = 5;
            elements[3][0] = 1;  elements[3][1] = 0;  elements[3][2] = 0;  elements[3][3] = 1;
            // Act
            int result = C.determinant();
            // Assert
            assertEquals(0, result,
                    "Incorrect determinant for a 4x4 matrix. determinant() should return 0 " +
                            "for square matrices larger than 3x3, as computing the determinant is unsupported " +
                            "in this assignment. Your method returned " + result + ".");
        } catch (Exception ex) {
            fail("An exception occurred while testing the 4x4 case: " + ex + ". " +
                    "Ensure your determinant() method checks the matrix size and returns 0 " +
                    "for unsupported square matrices.");
        }
    }

    @Test
    @DisplayName("2x3 (not square) -> det = -1")
    void determinantNonSquare_returnsMinusOne() {
        try {
            // Arrange
            Matrix D = new Matrix(2, 3);
            int[][] elements = D.getElements();
            elements[0][0] = 9;  elements[0][1] = 3;  elements[0][2] = 7;
            elements[1][0] = 2;  elements[1][1] = 8;  elements[1][2] = 4;
            // Act
            int result = D.determinant();
            // Assert
            assertEquals(-1, result,
                    "Incorrect determinant for a non-square matrix. determinant() should return -1 " +
                            "to indicate that a matrix is not square. For a 2x3 matrix the determinant should be -1, " +
                            "but your method returned " + result + ".");
        } catch (Exception ex) {
            fail("An exception occurred while testing the non-square case: " + ex + ". " +
                    "Ensure that your determinant() method first checks if the matrix is square and " +
                    "returns -1 for non-square matrices.");
        }
    }
}
