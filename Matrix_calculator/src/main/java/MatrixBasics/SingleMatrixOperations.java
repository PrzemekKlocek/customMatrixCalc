package MatrixBasics;

import MatrixExceptions.MatrixProperSizeException;

public interface SingleMatrixOperations {
    void printMatrix();
    boolean isSquare();
    void swapRows(int i, int j);
    void eye() throws MatrixProperSizeException;
//    public void multiplyRow(int row_num, float scalar);
//    public void subRows(int minuend, int subtrahend)
}
