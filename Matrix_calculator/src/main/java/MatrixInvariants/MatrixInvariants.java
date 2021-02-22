package MatrixInvariants;

import MatrixBasics.Matrix;
import MatrixBasics.MultipleMatricesOperations;
import MatrixExceptions.MatrixProperSizeException;
import MatrixToolOperations.MatrixToolOperations;
import MatrixTransDeco.MatrixTransDeco;

public class MatrixInvariants implements MatrixInvariantsInter {

    public MatrixInvariants(){};

    public double Determinant(Matrix matrix) throws MatrixProperSizeException {
        MatrixTransDeco decoTools = new MatrixTransDeco();
        double epsilon = 0.0000000001;
        Matrix[] rowEchelonList = decoTools.rowEchelonTest(matrix,true);
        matrix = rowEchelonList[0];
        double sign = rowEchelonList[1].RowList.get(0)[0];
        if(matrix.isSquare()){
            double det = 1;
            for(int i =0; i< matrix.height; i++){
                det = det*matrix.RowList.get(i)[i];
            }
            if(Math.abs(det - Math.floor(det)) < epsilon) {
                det = (double) Math.floor(det);
            } else if (Math.abs(det - Math.ceil(det)) < epsilon) {
                det = (double) Math.ceil(det);
            }
            return det*Math.pow(-1, sign);
        }
        else{
            System.out.println("Determinant is not defined for non-square matrix!");
            throw new MatrixProperSizeException();
        }
    }


    public int Rank(Matrix matrix) throws MatrixProperSizeException {
        MatrixToolOperations matrixTools = new MatrixToolOperations();
        MatrixTransDeco matrixDeco = new MatrixTransDeco();
        double epsilon = 0.01;
        Matrix[] rowEchelonList = matrixDeco.rowEchelonTest(matrix,true);
        matrix = rowEchelonList[0];
        int rank = 0;
        for(int i=0; i< matrix.height; i++){
            if(!matrixTools.isRowEmpty(matrix,i)){
                rank+=1;
            }
        }
        return rank;
    }


    public double Trace(Matrix matrix) throws MatrixProperSizeException {
        if(matrix.isSquare()){
            double trace = 0;
            double epsilon = 0.001;
            for(int i = 0; i< matrix.width; i++){
                trace += matrix.RowList.get(i)[i];
            }
            if(Math.abs(trace - Math.floor(trace)) < epsilon) {
                trace = (double) Math.floor(trace);
            } else if (Math.abs(trace - Math.ceil(trace)) < epsilon) {
                trace = (double) Math.ceil(trace);
            }
            return trace;
        }else{
            System.out.println("Trace is not defined for non-square matrix!");
            throw new MatrixProperSizeException();
        }
    }

    @Override
    public double[] Eigenvalues(Matrix matrix, int iterations) throws MatrixProperSizeException {
        if(!matrix.isSquare()){
            System.out.println("Eigenproblem is not defined for non-square matrix!");
            throw new MatrixProperSizeException();}

        MatrixTransDeco deco = new MatrixTransDeco();
        MultipleMatricesOperations mult = new MultipleMatricesOperations();
        double[] eigenlist = new double[matrix.height];
        Matrix A_k = matrix;
        Matrix A_kn;
        for(int i = 0; i < iterations; i++) {
            Matrix[] QRdeco = deco.QR(A_k);
            A_kn = mult.multiply(QRdeco[1], QRdeco[0]);
            A_k = A_kn;
        }
        for(int i = 0; i < matrix.height; i++){
            eigenlist[i] = A_k.RowList.get(i)[i];
        }

        return eigenlist;
    }
}
