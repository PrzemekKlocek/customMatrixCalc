package MainEnter;


import MatrixBasics.IdentityMatrix;
import MatrixBasics.Matrix;
import MatrixExceptions.MatrixProperSizeException;
import MatrixBasics.MultipleMatricesOperations;
import MatrixInvariants.MatrixInvariants;
import MatrixToolOperations.MatrixToolOperations;
import MatrixTransDeco.MatrixTransDeco;

// klasa startup tworzenie obiektow

public class Main {
    public static void main(String[] args) throws MatrixProperSizeException {

        Matrix matrix = new Matrix(3,3, true);
//        Matrix matrix2x2 = new Matrix(2,1,true);
//        Matrix matrixENTER = matrix;
        MatrixInvariants inv = new MatrixInvariants();
        MatrixTransDeco deco = new MatrixTransDeco();
        MultipleMatricesOperations mult = new MultipleMatricesOperations();
        MatrixToolOperations tool = new MatrixToolOperations();


//        Matrix[] LUdeco = deco.rowEchelonTest(matrix, true);
//        System.out.println("WSPOLCZYNNIKI");
//        LUdeco[1].printMatrix();
//        System.out.println("SCHODKOWA");
//        LUdeco[0].printMatrix();
//        System.out.println("ILOCZYN KONTROLNY");
//        mult.multiply(LUdeco[1],LUdeco[0]).printMatrix();
//        System.out.println("WEJSCIE");
//        matrixENTER.printMatrix();
//        System.out.println("ILOCZYN:");
//        System.out.println(mult.dot_product(matrix,matrix1));
//        System.out.println("NORMA");
//        System.out.println(mult.vectorNorm(matrix1));
//        System.out.println("MINORYZACJA:");
//        tool.deminorizeMatrix(matrix_to_minor).printMatrix();


//        deco.numProt(matrix2x2,0.0001);
//        matrix2x2.printMatrix();
//        Matrix[] decoQR = deco.QR(matrix);
//        System.out.println("////////////////// MACIERZ Q:");
//        decoQR[0].printMatrix();
//        System.out.println("%%%%%%%%%%%%%%%%%% MACIERZ R:");
//        decoQR[1].printMatrix();
//        System.out.println("%%%%%%%%%%%%%%%%%% MACIERZ Q*R:");
//        Matrix matrixprod = mult.multiply(decoQR[0],decoQR[1]);
//        deco.numProt(matrixprod,0.0001);
//        matrixprod.printMatrix();
//        decoQR[1].printMatrix();
//        System.out.println("%%%%%%%%%%%%%%%%%% MACIERZ Q*QT:");
//        Matrix matrixprodq = mult.multiply(decoQR[0],decoQR[0].transposeMatrix());
//        deco.numProt(matrixprodq,0.0001);
//        matrixprodq.printMatrix();

        double[] eiglist = inv.Eigenvalues(matrix,20);

        for(int i = 0; i<eiglist.length; i++){
            System.out.println("WARTOSC WLASNA NR" + (i+1));
            System.out.println(eiglist[i]);
        }







    }
}
