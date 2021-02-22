package MatrixBasics;

import MatrixExceptions.MatrixProperSizeException;

public interface MultipleMatricesOperationsInter {
    boolean areEqualSize(Matrix matrix1, Matrix matrix2);
    boolean areMultiplicative(Matrix matrix1, Matrix matrix2);
    Matrix add(Matrix matrix1, Matrix matrix2) throws MatrixProperSizeException;
    Matrix multiply(Matrix matrix1, Matrix matrix2) throws MatrixProperSizeException;
    Matrix multiply_by_scalar(Matrix matrix1, double scalar);
    double dot_product(Matrix vector1, Matrix vector2) throws MatrixProperSizeException;
    double vectorNorm(Matrix vector) throws MatrixProperSizeException;
    double vectorNormsquared(Matrix vector) throws MatrixProperSizeException;
}
