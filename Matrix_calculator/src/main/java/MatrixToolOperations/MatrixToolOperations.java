package MatrixToolOperations;

import MatrixBasics.Matrix;
import MatrixTransDeco.MatrixTransDeco;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class MatrixToolOperations implements MatrixToolOperationsInter{

    @Override
    public Matrix MoveZeros(Matrix matrix1, int col) {
        Matrix matrix = matrix1;
        int isColumnNonEmpty = 0;
        for(int i = 0; i < matrix.height; i++){
            if(matrix.RowList.get(i)[col]!=0){
                isColumnNonEmpty +=1;
            }
        }
        if(isColumnNonEmpty!=0){
            int step = 0;
            for(int i = 0; i < matrix.height - step; i++){
                if(matrix.RowList.get(i)[col] == 0){
                    while(matrix.RowList.get(matrix.height-1-step)[col] == 0){
                        step+=1;
                    }
                    matrix.swapRows(i, matrix.height - 1 - step);
                    step+=1;
                    int j = 1;
                    while(matrix.RowList.get(matrix.height - step - j)[col] == 0){
                        j+=1;
                    }
                    step+=j;
                    step-=1;
                }
                if(i + step > matrix.height){break;}
            }
        }
        return matrix;
    }

    @Override
    public Matrix[] SortZeroRows(Matrix matrix1) {
        Matrix[] result = new Matrix[2];
        int swap_count = 0;
        Matrix matrix = matrix1;
        for(int i = 0; i<matrix.height; i++){
            int long_ind = i;
            for (int j = i; j<matrix.height; j++){
                if(StartWithZeroes(matrix.RowList.get(long_ind)) > StartWithZeroes(matrix.RowList.get(j))){
                    long_ind = j;
                }
            }
            if(i!=long_ind) {
                matrix.swapRows(i, long_ind);
                swap_count += 1;
            }
        }
        Matrix arti_matrix = new Matrix(1,1);
        arti_matrix.RowList.get(0)[0] = swap_count;
        result[0] = matrix;
        result[1] = arti_matrix;
        return result;
    }

    @Override
    public int StartWithZeroes(double[] list) {
        int i = 0;
        while(list[i]==0){
            i++;
            if(i + 1 == list.length){
                break;
            }
        }
        return i;
    }

    @Override
    public boolean isColumnEmpty(Matrix matrix, int col) {
        for(int i = 0; i < matrix.height; i++){
            if(matrix.RowList.get(i)[col]!=0){
                return false;
            }
        }
        return true;
    }

    //SPRAWDZA, CZY W WEKTORZE WYSTEPUJE ZERO POMIEDZY NIEZEROWYMI WSPOLCZYNNIKAMI Z POMINIECIEM POCZATKOWYCH ZER
    @Override
    public int HasZeroBetween(Matrix matrix, int row) {
        for(int i = 0; i < matrix.width-1; i++){
            if(matrix.RowList.get(row)[i]!=0  &&  matrix.RowList.get(row)[i+1]==0){
                return i+1;
            }
        }
        return 0;
    }

    public boolean isRowEmpty(Matrix matrix, int row) {
        for(int i = 0; i < matrix.width; i++){
            if(matrix.RowList.get(row)[i]!=0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Matrix deminorizeMatrix(Matrix matrix) {

        if(matrix.height == 1 || matrix.width == 1){
            return matrix;
        }
        Matrix minor = new Matrix(matrix.height-1, matrix.width-1);
        for(int i = 0; i< matrix.height-1; i++){
            for(int j = 0;j< matrix.width-1;j++){
                minor.RowList.get(i)[j] = matrix.RowList.get(i+1)[j+1];

            }
        }

        return minor;
    }
}
