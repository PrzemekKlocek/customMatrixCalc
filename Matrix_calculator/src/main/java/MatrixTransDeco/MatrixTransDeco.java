package MatrixTransDeco;

import MatrixBasics.IdentityMatrix;
import MatrixBasics.Matrix;
import MatrixExceptions.MatrixProperSizeException;
import MatrixToolOperations.MatrixToolOperations;
import MatrixBasics.MultipleMatricesOperations;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.min;

public class MatrixTransDeco implements MatrixTransDecoInter {

    public Matrix[] rowEchelonTest(Matrix matrix1, boolean factorsMatrix) throws MatrixProperSizeException {
        MatrixToolOperations toolOperations = new MatrixToolOperations();
        Matrix matrix = matrix1;
        Matrix LMatrix = new Matrix(matrix.height, matrix.width);
        for (int p = 0; p < matrix.height; p++) {
            for (int q = 0; q < matrix.width; q++) {
                if (p == q) {
                    LMatrix.RowList.get(p)[q] = 1;
                }
                if (q > p) {
                    LMatrix.RowList.get(p)[q] = 0;
                }
            }}

        int[] size = {matrix.height, matrix.width};
        Arrays.sort(size);
        int check = 0;
            Matrix[] sortingInfo = toolOperations.SortZeroRows(matrix);
            matrix = sortingInfo[0];
            int zero = 0;
            for (int i = 0; i < size[0]; i++) {
                if (i + zero >= matrix.width || zero >= matrix.width) {
                    break;
                }
                if (matrix.RowList.get(i)[i + zero] == 0) {
                    Matrix[] sortingInfo1 = toolOperations.SortZeroRows(matrix);
                    matrix = sortingInfo1[0];
                    sortingInfo[1].RowList.get(0)[0] += sortingInfo1[1].RowList.get(0)[0];
                }
                if (matrix.RowList.get(i)[i + zero] == 0) {
                    zero += 1;
                    i -= 1;
                    continue;
                }
                double factor = matrix.RowList.get(i)[i + zero];
                for (int below = i + 1; below < matrix.height; below++) {
                    double scalar = matrix.RowList.get(below)[i + zero] / factor;
                    if (scalar != 0) {
                        matrix.multiplyRow(i, scalar);
                        matrix.subRows(below, i);
                        matrix.multiplyRow(i, 1 / scalar);
                    }
                    if (factorsMatrix && matrix.isSquare()) {
                        if (scalar != 0) {
                            LMatrix.RowList.get(below)[i] = scalar;
                        } else {
                            LMatrix.RowList.get(below)[i] = 0;
                        }
                    }
                }
                Matrix[] sortingInfo2 = toolOperations.SortZeroRows(matrix);
                matrix = sortingInfo[0];
                sortingInfo[1].RowList.get(0)[0] += sortingInfo[1].RowList.get(0)[0];
            }
            numProt(matrix,0.0001);
//            matrix.printMatrix();
//            LMatrix.printMatrix();
            if(factorsMatrix){
                Matrix[] decomposition = new Matrix[2];
                decomposition[0] = matrix;
                decomposition[1] = LMatrix;
                return decomposition;
            }else{
                return sortingInfo;

        }}

    @Override
    public Matrix[] LU(Matrix matrix) throws MatrixProperSizeException {
        return rowEchelonTest(matrix, true);
    }

    @Override
    public Matrix[] QR(Matrix matrix) throws MatrixProperSizeException {

        MultipleMatricesOperations mult = new MultipleMatricesOperations();
        MatrixToolOperations tool = new MatrixToolOperations();

        int size_iter = min(matrix.height, matrix.width);
        System.out.println(size_iter);
        Matrix[] Q_list = new Matrix[size_iter];
        Matrix[] A_list = new Matrix[size_iter];
        Matrix[] Q_bar_list = new Matrix[size_iter];
        A_list[0] = matrix;

        for (int i = 0; i < size_iter-1; i++) {

            /////////////////KROK 1 - WYZNACZ Q_I Z A_I

            //1A) CREATE VECTOR X_I
            Matrix columnVector = new Matrix(1,matrix.height-i);
            for(int a = 0; a< matrix.height-i; a++){
                columnVector.RowList.get(0)[a] = A_list[i].transposeMatrix().RowList.get(0)[a];
            }
            System.out.println("WEKTOR X" + i);
            columnVector.printMatrix();


            //1B) COMPUTE NORM OF X_I, CREATE U_I = X_I - ||X_I||*E_I, COMPUTE ||U_I||
            double norm = mult.vectorNorm(columnVector);
            System.out.println("NORMA X" + i);
            System.out.println(norm);

            columnVector.RowList.get(0)[0] += (Math.abs(columnVector.RowList.get(0)[0])/columnVector.RowList.get(0)[0])*norm;
            System.out.println("WEKTOR U" + i);
            columnVector.printMatrix();

            norm = mult.vectorNorm(columnVector);
            System.out.println("NORMA U" + i);
            System.out.println(norm);


            //1C) COMPUTE Q_I = I - (2/||U||^2) * U * U^T
            System.out.println("KONTROLA JEDNOSTKOWEJ:");
            Matrix Q = new Matrix(size_iter-i,size_iter-i);
            Q.eye();
            Q.printMatrix();
            double norm_squared = mult.vectorNormsquared(columnVector);
            System.out.println("NORMA U" + i + " DO KWADRATU");
            System.out.println(norm_squared);
            Matrix prod_V = mult.multiply(columnVector.transposeMatrix(),columnVector);
            double SCALAR_CONST = (-2)/(norm_squared);
            Q = mult.add(Q, mult.multiply_by_scalar(prod_V,SCALAR_CONST));

            //////////////////////////////////////////////KROK 2: WYŚWIETL Q_I, WPISZ DO LISTY Q_L
            Q_list[i] = Q;
            System.out.println("MACIERZ Q"+i);
            Q.printMatrix();


            ///////////////////////////////////////////////KROK 3: WYLICZ Q_I * A_I, ZAPISZ DO Q_BAR
            Matrix prod = mult.multiply(Q_list[i],A_list[i]);
            System.out.println("MACIERZ Q" + i + "* A" + i);
            prod.printMatrix();
            Q_bar_list[i] = prod;


            /////////////////////////////////////////////KROK 4: DEMINORYZACJA Q_BAR_I, STWORZENIE A_I+1


            System.out.println("DEMINORYZACJA:");
            A_list[i + 1] = tool.deminorizeMatrix(prod);
            System.out.println("MACIERZ QBAR" + i +" ZDEMINORYZOWANA - MACIERZ A" + (i+1));
            A_list[i + 1].printMatrix();
            System.out.println("KONIEC KROKU "+ i);


//            if(A_list[i+1].height == 1){
//                A_list[i+1].RowList.get(0)[0] = Math.abs(A_list[i+1].RowList.get(0)[0]);
//                Q_list[i+1] = A_list[i+1];
//                break;
//            }

        }

        ///// KROK 5: ZAPISZ WSZYSTKIE MACIERZE Q JAKO MACIERZE ROZMIARU SIZE_ITER X SIZE_ITER
        for (int i = 0; i < size_iter-1; i++){
            Matrix Q_help = new Matrix(size_iter,size_iter);
            Q_help.eye();
            for(int row = i; row<size_iter; row++){
                for(int col = i; col < size_iter; col++){
                    Q_help.RowList.get(row)[col] = Q_list[i].RowList.get(row-i)[col-i];
                }
            }
            Q_list[i] = Q_help;
        }


        //// KROK 6: STWÓRZ MACIERZE Q I R Z LISTY Q_L
        Matrix orthogonalProd = new Matrix(size_iter, size_iter);
        orthogonalProd.eye();

        Matrix upperTriangular = matrix;


        for (int i = 0; i < size_iter-1; i++){
            orthogonalProd = mult.multiply(Q_list[i],orthogonalProd);
        }

        upperTriangular = mult.multiply(orthogonalProd,upperTriangular);
        orthogonalProd = orthogonalProd.transposeMatrix();

        Matrix[] resultMatrices = new Matrix[2];
        numProt(upperTriangular,0.00001);
        numProt(orthogonalProd, 0.00001);
        resultMatrices[0] = orthogonalProd;
        resultMatrices[1] = upperTriangular;
        return resultMatrices;
    }

    @Override
    public void numProt(Matrix matrix, double epsilon) {
        ///NUMERYCZNY PROTECT:
        for (double[] row : matrix.RowList) {
            for (int j = 0; j < matrix.width; j++)
                if (Math.abs(row[j] - Math.floor(row[j])) < epsilon) {
                    row[j] = (double) Math.floor(row[j]);
                } else if (Math.abs(row[j] - Math.ceil(row[j])) < epsilon) {
                    row[j] = (double) Math.ceil(row[j]);
                }
        }
    }


}
//    @Override
//    public List<Matrix> LU(Matrix matrix) { return null;}

