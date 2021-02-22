package MatrixToolOperations;

import MatrixBasics.Matrix;

public interface MatrixToolOperationsInter {
    Matrix MoveZeros(Matrix matrix, int col);
    Matrix[] SortZeroRows(Matrix matrix);
    int StartWithZeroes(double[] list);
    boolean isColumnEmpty(Matrix matrix, int col);
    int HasZeroBetween(Matrix matrix, int row);
    boolean isRowEmpty(Matrix matrix, int col);
    Matrix deminorizeMatrix(Matrix matrix);
}
