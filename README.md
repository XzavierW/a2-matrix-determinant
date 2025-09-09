# CS 1342 — Principles of Computer Science II
**Fall 2025**

# Assignment 2

**Due:** at or before midnight on **Sep 18, 2025 (Thursday)**

---

## Intro
In this assignment you’ll practice encapsulating data and behavior in a small Java program while reviewing linear algebra basics.  
The core problem is to compute the determinant of a matrix. Determinants are useful for reasoning about invertibility, systems of linear equations, and geometric transformations. You’ll implement this for 2x2 and 3x3 matrices and handle other sizes or non-square matrices gracefully.

---

## Submission (GitHub → Canvas workflow)

1) **Work in GitHub Classroom.** Commit and **push** your changes regularly. Each push triggers the GitHub autograder.
2) **Use autograder feedback.** Your **base logic grade** comes from the autograder results. Keep pushing until all required checks **pass**.
3) **Finalize for Canvas.** When you are confident your solution is correct and you followed all steps:
    - Create a ZIP containing **only** these two source files:
        - `Matrix.java`
        - `DeterminantCalculation.java`
    - Name the file exactly: **`firstname_lastname_a2.zip`** (e.g., `lawrence_klinkert_a2.zip`).
4) **Submit on Canvas** before the deadline by uploading the ZIP from step 3.

> Notes
> - The GitHub push step is **part of the submission process** and determines your base grade via the autograder.
> - Do not include compiled files or IDE metadata—**source files only**.

---

## Late submission policies
- Submitted **0–2 days** after the due date: **20%** penalty
- Submitted **2–4 days** after the due date: **40%** penalty
- Submitted **4–7 days** after the due date: **60%** penalty
- **No credit** if submitted **seven days** after the due date

---

## Instructions
- Include **comments at the beginning of each program**. Points will be deducted for missing or inadequate comments.
- Use **proper indentation** as shown in class notes. Points will be deducted for poor indentation.
- Use **meaningful names** for variables and classes. Points will be deducted for uninformative names.

---

## Problem
In this assignment, you will compute the **determinant** of a **2×2** matrix and a **3×3** matrix.

For a **2×2** matrix:

```
[ a  b ]
[ c  d ]
```

its determinant is calculated as `a*d - b*c`.

For a **3×3** matrix:

```
[ a  b  c ]
[ p  q  r ]
[ x  y  z ]
```

its determinant is calculated as:

```
a(q*z−r*y) − b(p*z−r*x) + c(p*y−q*x) = a*q*z + b*r*x + c*p*y − a*r*y − b*p*z − c*q*x
```

You will **initialize a matrix with random integers** and **calculate and print** its determinant value.

## Example 
We’ll use these matrices as a running example:

- A (2x2)  
```
  [ [1, 2], [3, 4] ] -> det(A) = (1*4 - 3*2) = -2
```
- B (3x3)  
```
  [ [2, 1, 3], [0, 1, 4], [5, 2, 0] ] -> det(B) =  
  a*q*z + b*r*x + c*p*y - a*r*y - b*p*z - c*q*x =  
  2*1*0 + 1*4*5 + 3*0*2 - 2*4*2 - 1*0*0 - 3*1*5 = -11
```
- C (4x4) 
```
unsupported in this assignment -> determinant method returns 0
```
- D (2x3) 
```
not square -> determinant method returns -1
```
---

## Programming Requirements

### Matrix class
Create an **integer matrix** class named `Matrix`.

Member variables
- int[][] elements — the matrix data (rows x columns).

Key methods
- Matrix(int M, int N) — constructor that creates an MxN integer matrix (assume M,N > 0).
- int[][] getElements() — returns a reference to the internal 2D array so you can set values.
- int numberOfRows() — returns the row count.
- int numberOfColumns() — returns the column count.
- boolean isSquareMatrix() — returns true iff rows == columns.
- int determinant() — returns:
    - the proper determinant for 2x2 and 3x3 matrices (using the formulas in the spec),
    - 0 for other square sizes (e.g., 4x4),
    - -1 if the matrix is not square.
- void print() — prints the matrix, one row per line, with elements separated by spaces.

### Test class
Create a **test class** named `DeterminantCalculation` that includes the **`main`** method. 

In `main`:

1. Create an integer matrix of size **2×2**. Initialize it with your own values. **Print the matrix and its determinant**.
2. Create an integer matrix of size **3×3**. Initialize it with your own values. **Print the matrix and its determinant**.
3. Create an integer matrix of size **4×4**. Initialize it with your own values. **Print the matrix and its determinant**.
    - Confirmation: you should get **`0`** for the determinant because our code **does not calculate** the determinant for sizes other than **2×2** or **3×3**.
4. Create an integer matrix of size **2×3**. Initialize it with your own values. **Print the matrix and its determinant**.
    - Confirmation: you should get **`-1`** for the determinant because it is **not a square matrix**.

> **Hint:** You can use **random numbers** to initialize a matrix. Restrict values to the range **1..10** so that it is easy to verify the answer. The TAs will use their own values to test the program.

## Sample Input
There is no console input. For illustration, suppose main creates the example matrices from above:
- A = [[1, 2], [3, 4]]
- B = [[2, 1, 3], [0, 1, 4], [5, 2, 0]]
- C = [[7, 4, 1, 6], [3, 9, 2, 8], [5, 5, 5, 5], [1, 0, 0, 1]]
- D = [[9, 3, 7], [2, 8, 4]]


## Sample Output
The exact formatting depends on Matrix.print() and the messages in main. A representative run using the example matrices might look like:

```
 1 2
 3 4
 Determinant of A = -2
 ---------------------------------------
 2 1 3
 0 1 4
 5 2 0
 Determinant of B = -11
 ---------------------------------------
 7 4 1 6
 3 9 2 8
 5 5 5 5
 1 0 0 1
 Determinant of C = 0
 ---------------------------------------
 9 3 7
 2 8 4
 Determinant of D = -1
 ---------------------------------------
```


## Explanation (how the outputs are obtained)
- For 2x2: det([[a, b], [c, d]]) = a\*d - b\*c. Example A: 1\*4 - 2\*3 = -2.
- For 3x3 (using the provided formula): a\*q\*z + b\*r\*x + c\*p\*y - a\*r\*y - b\*p\*z - c\*q\*x.  
  Example B (rows [2,1,3], [0,1,4], [5,2,0]) evaluates to -11 as shown above.
- For other square sizes (e.g., 4x4), determinant() returns 0 by design in this assignment.
- For non-square matrices (e.g., 2x3), determinant() returns -1 to indicate “not applicable.”



---

## Evaluation (what we check)
To earn full credit, make sure your submission satisfies **all** of the following expectations.

- **Compiles cleanly.** Your code must build with no errors or missing files. Submissions that do not compile may be returned for corrections on a short timeline.
- **Runs without crashes** and produces **correct outputs** for each required case:
    - 2×2 and 3×3 matrices: determinant computed using the specified formulas.
    - 4×4 matrix: determinant method returns `0` (unsupported size by design).
    - 2×3 matrix: determinant method returns `-1` (not square).
- **Program structure matches the spec.** Provide the classes and methods exactly as described (`Matrix` with required attributes/methods, and the `DeterminantCalculation` test driver).
- **No hard‑coded test matrices.** Use values in main class *or* generate them at runtime (e.g., random in a small range). Do **not** write your program so it only works for one specific, hard-coded matrix.
- **Documentation quality.** Include meaningful **file/class/method header comments**, describe parameters/returns where appropriate, and use **clear variable/class names**. Follow the indentation/formatting guidance from class notes.
- **Matrix printing is readable.** Use a tidy, consistent layout so the grader can easily verify your results.


