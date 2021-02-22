package MatrixInvariants;

import MatrixBasics.Matrix;
import MatrixExceptions.MatrixProperSizeException;

public interface MatrixInvariantsInter {

    double Determinant(Matrix matrix) throws MatrixProperSizeException;

    int Rank(Matrix matrix) throws MatrixProperSizeException;

    double Trace(Matrix matrix) throws MatrixProperSizeException;

    double[] Eigenvalues(Matrix matrix, int iterations) throws MatrixProperSizeException;
}
