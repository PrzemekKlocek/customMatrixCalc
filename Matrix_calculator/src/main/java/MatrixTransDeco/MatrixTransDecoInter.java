package MatrixTransDeco;

import MatrixBasics.Matrix;
import MatrixExceptions.MatrixProperSizeException;

import java.util.List;

public interface MatrixTransDecoInter {

    public Matrix[] rowEchelonTest(Matrix matrix, boolean factorsMatrix) throws MatrixProperSizeException;
    public Matrix[] LU(Matrix matrix) throws MatrixProperSizeException;
    public Matrix[] QR(Matrix matrix) throws MatrixProperSizeException;
    public void numProt(Matrix matrix, double epsilon);

}
