import com.student_work.Matrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exhaustive unit tests for the Matrix class and its determinant calculation.
 * Tests cover all required functionality including constructors, getters,
 * square matrix detection, and determinant calculation for various matrix sizes.
 */
public class MatrixDeterminantRandomTest {

    private Matrix matrix2x2;
    private Matrix matrix3x3;
    private Matrix matrix4x4;
    private Matrix matrix2x3;
    private Matrix matrix3x2;
    private Matrix matrix1x1;
    private Matrix matrix5x5;

    @BeforeEach
    void setUp() {
        // Initialize test matrices with known values
        matrix2x2 = new Matrix(2, 2);
        matrix3x3 = new Matrix(3, 3);
        matrix4x4 = new Matrix(4, 4);
        matrix2x3 = new Matrix(2, 3);
        matrix3x2 = new Matrix(3, 2);
        matrix1x1 = new Matrix(1, 1);
        matrix5x5 = new Matrix(5, 5);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create matrix with correct dimensions")
        void testConstructorDimensions() {
            Matrix m = new Matrix(3, 4);
            assertEquals(3, m.numberOfRows());
            assertEquals(4, m.numberOfColumns());
        }

        @Test
        @DisplayName("Should initialize matrix with zero values")
        void testConstructorInitialization() {
            Matrix m = new Matrix(2, 2);
            int[][] elements = m.getElements();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    assertEquals(0, elements[i][j]);
                }
            }
        }

        @ParameterizedTest
        @CsvSource({
                "1, 1",
                "10, 10",
                "100, 100",
                "1, 100",
                "100, 1"
        })
        @DisplayName("Should handle various matrix sizes")
        void testVariousSizes(int rows, int cols) {
            Matrix m = new Matrix(rows, cols);
            assertEquals(rows, m.numberOfRows());
            assertEquals(cols, m.numberOfColumns());
        }
    }

    @Nested
    @DisplayName("Getter Methods Tests")
    class GetterTests {

        @Test
        @DisplayName("getElements should return modifiable reference")
        void testGetElementsModifiable() {
            Matrix m = new Matrix(2, 2);
            int[][] elements = m.getElements();
            elements[0][0] = 5;
            elements[1][1] = 10;

            assertEquals(5, m.getElements()[0][0]);
            assertEquals(10, m.getElements()[1][1]);
        }

        @Test
        @DisplayName("numberOfRows should return correct value")
        void testNumberOfRows() {
            assertEquals(2, matrix2x2.numberOfRows());
            assertEquals(3, matrix3x3.numberOfRows());
            assertEquals(4, matrix4x4.numberOfRows());
            assertEquals(2, matrix2x3.numberOfRows());
        }

        @Test
        @DisplayName("numberOfColumns should return correct value")
        void testNumberOfColumns() {
            assertEquals(2, matrix2x2.numberOfColumns());
            assertEquals(3, matrix3x3.numberOfColumns());
            assertEquals(4, matrix4x4.numberOfColumns());
            assertEquals(3, matrix2x3.numberOfColumns());
        }
    }

    @Nested
    @DisplayName("Square Matrix Detection Tests")
    class SquareMatrixTests {

        @Test
        @DisplayName("Should identify square matrices correctly")
        void testSquareMatrices() {
            assertTrue(matrix2x2.isSquareMatrix());
            assertTrue(matrix3x3.isSquareMatrix());
            assertTrue(matrix4x4.isSquareMatrix());
            assertTrue(matrix1x1.isSquareMatrix());
            assertTrue(matrix5x5.isSquareMatrix());
        }

        @Test
        @DisplayName("Should identify non-square matrices correctly")
        void testNonSquareMatrices() {
            assertFalse(matrix2x3.isSquareMatrix());
            assertFalse(matrix3x2.isSquareMatrix());
            assertFalse(new Matrix(1, 2).isSquareMatrix());
            assertFalse(new Matrix(5, 3).isSquareMatrix());
        }
    }

    @Nested
    @DisplayName("2x2 Matrix Determinant Tests")
    class Determinant2x2Tests {

        @Test
        @DisplayName("Should calculate determinant of identity matrix")
        void testIdentityMatrix() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 1;
            elements[0][1] = 0;
            elements[1][0] = 0;
            elements[1][1] = 1;

            assertEquals(1, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should calculate determinant of zero matrix")
        void testZeroMatrix() {
            // Matrix is already initialized with zeros
            assertEquals(0, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should calculate determinant with positive result")
        void testPositiveDeterminant() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 3;
            elements[0][1] = 2;
            elements[1][0] = 1;
            elements[1][1] = 4;

            // det = 3*4 - 2*1 = 12 - 2 = 10
            assertEquals(10, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should calculate determinant with negative result")
        void testNegativeDeterminant() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 1;
            elements[0][1] = 2;
            elements[1][0] = 3;
            elements[1][1] = 4;

            // det = 1*4 - 2*3 = 4 - 6 = -2
            assertEquals(-2, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should handle negative elements")
        void testNegativeElements() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = -2;
            elements[0][1] = 3;
            elements[1][0] = 4;
            elements[1][1] = -5;

            // det = (-2)*(-5) - 3*4 = 10 - 12 = -2
            assertEquals(-2, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should handle large numbers")
        void testLargeNumbers() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 1000;
            elements[0][1] = 2000;
            elements[1][0] = 3000;
            elements[1][1] = 4000;

            // det = 1000*4000 - 2000*3000 = 4000000 - 6000000 = -2000000
            assertEquals(-2000000, matrix2x2.determinant());
        }
    }

    @Nested
    @DisplayName("3x3 Matrix Determinant Tests")
    class Determinant3x3Tests {

        @Test
        @DisplayName("Should calculate determinant of identity matrix")
        void testIdentityMatrix() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = 0; elements[0][2] = 0;
            elements[1][0] = 0; elements[1][1] = 1; elements[1][2] = 0;
            elements[2][0] = 0; elements[2][1] = 0; elements[2][2] = 1;

            assertEquals(1, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should calculate determinant of zero matrix")
        void testZeroMatrix() {
            // Matrix is already initialized with zeros
            assertEquals(0, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should calculate determinant using example from spec")
        void testSpecExample() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 2; elements[0][1] = 1; elements[0][2] = 3;
            elements[1][0] = 0; elements[1][1] = 1; elements[1][2] = 4;
            elements[2][0] = 5; elements[2][1] = 2; elements[2][2] = 0;

            // Using formula: a*q*z + b*r*x + c*p*y - a*r*y - b*p*z - c*q*x
            // = 2*1*0 + 1*4*5 + 3*0*2 - 2*4*2 - 1*0*0 - 3*1*5
            // = 0 + 20 + 0 - 16 - 0 - 15 = -11
            assertEquals(-11, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should calculate positive determinant")
        void testPositiveDeterminant() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = 2; elements[0][2] = 3;
            elements[1][0] = 0; elements[1][1] = 1; elements[1][2] = 4;
            elements[2][0] = 5; elements[2][1] = 6; elements[2][2] = 0;

            // = 1*1*0 + 2*4*5 + 3*0*6 - 1*4*6 - 2*0*0 - 3*1*5
            // = 0 + 40 + 0 - 24 - 0 - 15 = 1
            assertEquals(1, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle negative elements")
        void testNegativeElements() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = -1; elements[0][1] = 2; elements[0][2] = -3;
            elements[1][0] = 4; elements[1][1] = -5; elements[1][2] = 6;
            elements[2][0] = -7; elements[2][1] = 8; elements[2][2] = -9;

            // = (-1)*(-5)*(-9) + 2*6*(-7) + (-3)*4*8 - (-1)*6*8 - 2*4*(-9) - (-3)*(-5)*(-7)
            // = -45 - 84 - 96 + 48 + 72 + 105 = 0
            assertEquals(0, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle matrix with row of zeros")
        void testRowOfZeros() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = 2; elements[0][2] = 3;
            elements[1][0] = 0; elements[1][1] = 0; elements[1][2] = 0;
            elements[2][0] = 4; elements[2][1] = 5; elements[2][2] = 6;

            assertEquals(0, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle matrix with column of zeros")
        void testColumnOfZeros() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = 0; elements[0][2] = 3;
            elements[1][0] = 4; elements[1][1] = 0; elements[1][2] = 6;
            elements[2][0] = 7; elements[2][1] = 0; elements[2][2] = 9;

            assertEquals(0, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle upper triangular matrix")
        void testUpperTriangular() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 2; elements[0][1] = 3; elements[0][2] = 4;
            elements[1][0] = 0; elements[1][1] = 5; elements[1][2] = 6;
            elements[2][0] = 0; elements[2][1] = 0; elements[2][2] = 7;

            // For triangular matrix, det = product of diagonal = 2*5*7 = 70
            assertEquals(70, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle lower triangular matrix")
        void testLowerTriangular() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 3; elements[0][1] = 0; elements[0][2] = 0;
            elements[1][0] = 4; elements[1][1] = 5; elements[1][2] = 0;
            elements[2][0] = 6; elements[2][1] = 7; elements[2][2] = 8;

            // For triangular matrix, det = product of diagonal = 3*5*8 = 120
            assertEquals(120, matrix3x3.determinant());
        }
    }

    @Nested
    @DisplayName("Unsupported Matrix Size Tests")
    class UnsupportedSizeTests {

        @Test
        @DisplayName("Should return 0 for 1x1 matrix")
        void test1x1Matrix() {
            matrix1x1.getElements()[0][0] = 5;
            assertEquals(0, matrix1x1.determinant());
        }

        @Test
        @DisplayName("Should return 0 for 4x4 matrix")
        void test4x4Matrix() {
            int[][] elements = matrix4x4.getElements();
            // Fill with some values
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    elements[i][j] = i * 4 + j + 1;
                }
            }
            assertEquals(0, matrix4x4.determinant());
        }

        @Test
        @DisplayName("Should return 0 for 5x5 matrix")
        void test5x5Matrix() {
            int[][] elements = matrix5x5.getElements();
            // Fill with some values
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    elements[i][j] = i + j;
                }
            }
            assertEquals(0, matrix5x5.determinant());
        }

        @ParameterizedTest
        @ValueSource(ints = {6, 7, 8, 9, 10, 20, 100})
        @DisplayName("Should return 0 for various large square matrices")
        void testLargeSquareMatrices(int size) {
            Matrix m = new Matrix(size, size);
            assertEquals(0, m.determinant());
        }
    }

    @Nested
    @DisplayName("Non-Square Matrix Tests")
    class NonSquareMatrixTests {

        @Test
        @DisplayName("Should return -1 for 2x3 matrix")
        void test2x3Matrix() {
            int[][] elements = matrix2x3.getElements();
            elements[0][0] = 9; elements[0][1] = 3; elements[0][2] = 7;
            elements[1][0] = 2; elements[1][1] = 8; elements[1][2] = 4;

            assertEquals(-1, matrix2x3.determinant());
        }

        @Test
        @DisplayName("Should return -1 for 3x2 matrix")
        void test3x2Matrix() {
            int[][] elements = matrix3x2.getElements();
            elements[0][0] = 1; elements[0][1] = 2;
            elements[1][0] = 3; elements[1][1] = 4;
            elements[2][0] = 5; elements[2][1] = 6;

            assertEquals(-1, matrix3x2.determinant());
        }

        @Test
        @DisplayName("Should return -1 for 1x2 matrix")
        void test1x2Matrix() {
            Matrix m = new Matrix(1, 2);
            m.getElements()[0][0] = 5;
            m.getElements()[0][1] = 10;

            assertEquals(-1, m.determinant());
        }

        @Test
        @DisplayName("Should return -1 for 2x1 matrix")
        void test2x1Matrix() {
            Matrix m = new Matrix(2, 1);
            m.getElements()[0][0] = 3;
            m.getElements()[1][0] = 7;

            assertEquals(-1, m.determinant());
        }

        @ParameterizedTest
        @CsvSource({
                "1, 3",
                "3, 1",
                "2, 5",
                "5, 2",
                "10, 11",
                "11, 10"
        })
        @DisplayName("Should return -1 for various non-square dimensions")
        void testVariousNonSquareMatrices(int rows, int cols) {
            Matrix m = new Matrix(rows, cols);
            assertEquals(-1, m.determinant());
        }
    }

    @Nested
    @DisplayName("Edge Cases and Boundary Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle maximum integer values in 2x2")
        void testMaxIntValues2x2() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = Integer.MAX_VALUE / 2;
            elements[0][1] = 1;
            elements[1][0] = 1;
            elements[1][1] = 2;

            // This tests for potential overflow
            int expected = (Integer.MAX_VALUE / 2) * 2 - 1;
            assertEquals(expected, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should handle minimum integer values in 2x2")
        void testMinIntValues2x2() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = Integer.MIN_VALUE / 2;
            elements[0][1] = 1;
            elements[1][0] = 1;
            elements[1][1] = 2;

            int expected = (Integer.MIN_VALUE / 2) * 2 - 1;
            assertEquals(expected, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should handle alternating signs pattern")
        void testAlternatingSignsPattern() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = -1; elements[0][2] = 1;
            elements[1][0] = -1; elements[1][1] = 1; elements[1][2] = -1;
            elements[2][0] = 1; elements[2][1] = -1; elements[2][2] = 1;

            assertEquals(0, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle diagonal matrix")
        void testDiagonalMatrix() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 2; elements[0][1] = 0; elements[0][2] = 0;
            elements[1][0] = 0; elements[1][1] = 3; elements[1][2] = 0;
            elements[2][0] = 0; elements[2][1] = 0; elements[2][2] = 4;

            // For diagonal matrix, det = product of diagonal = 2*3*4 = 24
            assertEquals(24, matrix3x3.determinant());
        }

        @Test
        @DisplayName("Should handle symmetric matrix")
        void testSymmetricMatrix() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 1; elements[0][1] = 2; elements[0][2] = 3;
            elements[1][0] = 2; elements[1][1] = 4; elements[1][2] = 5;
            elements[2][0] = 3; elements[2][1] = 5; elements[2][2] = 6;

            // = 1*4*6 + 2*5*3 + 3*2*5 - 1*5*5 - 2*2*6 - 3*4*3
            // = 24 + 30 + 30 - 25 - 24 - 36 = -1
            assertEquals(-1, matrix3x3.determinant());
        }
    }

    @Nested
    @DisplayName("Print Method Tests")
    class PrintMethodTests {

        @Test
        @DisplayName("Should print 2x2 matrix correctly")
        void testPrint2x2() {
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 1; elements[0][1] = 2;
            elements[1][0] = 3; elements[1][1] = 4;

            // Test that print doesn't throw exception
            assertDoesNotThrow(() -> matrix2x2.print());
        }

        @Test
        @DisplayName("Should print 3x3 matrix correctly")
        void testPrint3x3() {
            int[][] elements = matrix3x3.getElements();
            elements[0][0] = 2; elements[0][1] = 1; elements[0][2] = 3;
            elements[1][0] = 0; elements[1][1] = 1; elements[1][2] = 4;
            elements[2][0] = 5; elements[2][1] = 2; elements[2][2] = 0;

            assertDoesNotThrow(() -> matrix3x3.print());
        }

        @Test
        @DisplayName("Should print non-square matrix correctly")
        void testPrintNonSquare() {
            int[][] elements = matrix2x3.getElements();
            elements[0][0] = 9; elements[0][1] = 3; elements[0][2] = 7;
            elements[1][0] = 2; elements[1][1] = 8; elements[1][2] = 4;

            assertDoesNotThrow(() -> matrix2x3.print());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should handle sequence of operations on same matrix")
        void testSequenceOfOperations() {
            Matrix m = new Matrix(2, 2);

            // Initial state
            assertEquals(0, m.determinant());

            // Modify to identity
            int[][] elements = m.getElements();
            elements[0][0] = 1;
            elements[1][1] = 1;
            assertEquals(1, m.determinant());

            // Modify to have determinant -2
            elements[0][1] = 2;
            elements[1][0] = 3;
            elements[1][1] = 4;
            assertEquals(-2, m.determinant());
        }

        @Test
        @DisplayName("Should verify determinant properties for 2x2")
        void testDeterminantProperties2x2() {
            // Property: det(A) = 0 if and only if matrix is singular
            int[][] elements = matrix2x2.getElements();
            elements[0][0] = 2; elements[0][1] = 4;
            elements[1][0] = 1; elements[1][1] = 2;

            // Rows are proportional, so det = 0
            assertEquals(0, matrix2x2.determinant());
        }

        @Test
        @DisplayName("Should verify determinant properties for 3x3")
        void testDeterminantProperties3x3() {
            // Property: Swapping two rows negates determinant
            Matrix m1 = new Matrix(3, 3);
            Matrix m2 = new Matrix(3, 3);

            int[][] e1 = m1.getElements();
            e1[0][0] = 1; e1[0][1] = 2; e1[0][2] = 3;
            e1[1][0] = 4; e1[1][1] = 5; e1[1][2] = 6;
            e1[2][0] = 7; e1[2][1] = 8; e1[2][2] = 9;

            int[][] e2 = m2.getElements();
            e2[0][0] = 4; e2[0][1] = 5; e2[0][2] = 6; // Swapped row 0 and 1
            e2[1][0] = 1; e2[1][1] = 2; e2[1][2] = 3;
            e2[2][0] = 7; e2[2][1] = 8; e2[2][2] = 9;

            assertEquals(-m1.determinant(), m2.determinant());
        }
    }

    @Nested
    @DisplayName("Random Value Tests")
    class RandomValueTests {

        @Test
        @DisplayName("Should handle random values in range 1-10 for 2x2")
        void testRandomValues2x2() {
            Matrix m = new Matrix(2, 2);
            int[][] elements = m.getElements();

            // Test with values mentioned in spec (1-10)
            elements[0][0] = 7;
            elements[0][1] = 3;
            elements[1][0] = 9;
            elements[1][1] = 2;

            // det = 7*2 - 3*9 = 14 - 27 = -13
            assertEquals(-13, m.determinant());
        }

        @Test
        @DisplayName("Should handle random values in range 1-10 for 3x3")
        void testRandomValues3x3() {
            Matrix m = new Matrix(3, 3);
            int[][] elements = m.getElements();

            elements[0][0] = 5; elements[0][1] = 8; elements[0][2] = 2;
            elements[1][0] = 3; elements[1][1] = 7; elements[1][2] = 9;
            elements[2][0] = 1; elements[2][1] = 4; elements[2][2] = 6;

            // Calculate using formula
            int det = 5*7*6 + 8*9*1 + 2*3*4 - 5*9*4 - 8*3*6 - 2*7*1;
            assertEquals(det, m.determinant());
        }
    }
}