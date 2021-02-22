package MatrixBasics;

import MatrixExceptions.MatrixProperSizeException;

public class MultipleMatricesOperations implements MultipleMatricesOperationsInter{

    public boolean areEqualSize(Matrix matrix1, Matrix matrix2){
        if(matrix1.width == matrix2.width && matrix1.height == matrix2.height){
            return true;
        }else {
            return false;
        }
    }

    public boolean areMultiplicative(Matrix matrix1, Matrix matrix2){
        if(matrix1.width== matrix2.height){
            return true;
        }else{
            return false;
        }
    }

    public Matrix add(Matrix matrix1, Matrix matrix2) throws MatrixProperSizeException {
        if(areEqualSize(matrix1,matrix2)){
            Matrix result = new Matrix(matrix1.height, matrix2.width);
            for(int i = 0; i< matrix1.height; i++){
                for(int j = 0; j< matrix2.width; j++){
                    result.RowList.get(i)[j] = matrix1.RowList.get(i)[j] + matrix2.RowList.get(i)[j];
                }
            }
            return result;
        }else{
            System.out.println("Matrices are not proper size!");
            throw new MatrixProperSizeException();
        }
    }

    public Matrix multiply(Matrix matrix1, Matrix matrix2) throws MatrixProperSizeException{
        if(areMultiplicative(matrix1, matrix2)){
            Matrix result = new Matrix(matrix1.height, matrix2.width);
            for(int i = 0; i< matrix1.height; i++){
                for(int j = 0; j< matrix2.width; j++) {
                    result.RowList.get(i)[j]=0;
                    for(int k = 0; k< matrix2.height; k++){
                        result.RowList.get(i)[j] = result.RowList.get(i)[j] + matrix1.RowList.get(i)[k] * matrix2.RowList.get(k)[j];
                    }
                }
            }
            return result;
        }else{
            System.out.println("Matrices are not proper size!");
            throw new MatrixProperSizeException();
        }
    }


    public Matrix multiply_by_scalar(Matrix matrix1, double scalar) {
        Matrix result = new Matrix(matrix1.height, matrix1.width);
        for (int i = 0; i < matrix1.height; i++) {
            for (int j = 0; j < matrix1.width; j++) {
                result.RowList.get(i)[j] = scalar * matrix1.RowList.get(i)[j];
            }
        }
        return result;
    }

    @Override
    public double dot_product(Matrix vector1, Matrix vector2) throws MatrixProperSizeException {
        if(vector1.width == vector2.width && vector1.height == 1 && vector2.height == 1){
            return multiply(vector1,vector2.transposeMatrix()).RowList.get(0)[0];
        }else{
            System.out.println("NOT PROPER SIZE");
            throw new MatrixProperSizeException();
        }
    }

    public double vectorNorm(Matrix vector) throws MatrixProperSizeException {
        return Math.sqrt(dot_product(vector,vector));
    }

    @Override
    public double vectorNormsquared(Matrix vector) throws MatrixProperSizeException {
        return dot_product(vector,vector);
    }

}
