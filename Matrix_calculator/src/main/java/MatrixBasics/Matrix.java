package MatrixBasics;

import MatrixExceptions.MatrixProperSizeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Matrix implements SingleMatrixOperations{

    public int height;
    public int width;
    private boolean passer; //sprawdza, czy będziemy chcieli wpisywać wspolczynniki do macierzy
    public List<double[]> RowList = new ArrayList<double[]>();

    public Matrix(int height, int width, boolean passer) {
        this.height = height;
        this.width = width;
        this.passer = passer;
        if (passer) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < height; i++) {
                double[] fast_list = new double[width];
                for (int j = 0; j < width; j++) {
                    System.out.println("Value for cell: Row: " + (i + 1) + ", Column: " + (j + 1));
                    double cell = scanner.nextDouble();
                    fast_list[j] = cell;
                }
                RowList.add(fast_list);
            }
        }
    }

    public Matrix(int height){};

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.RowList = new ArrayList<double[]>();
        for(int i = 0; i<height; i++){
            double[] fast_list = new double[width];
            RowList.add(fast_list);
        }

    }


    public void printMatrix(){
        for (double[] row : RowList) {
            System.out.println("[" + Arrays.toString(row));
        }
    }


    public Matrix transposeMatrix() {
        Matrix result = new Matrix(width, height);
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                result.RowList.get(j)[i] = RowList.get(i)[j];
            }
        }
        return result;
    }

    public boolean isSquare() {
        if(height == width){
            return true;
        }else{
            return false;
        }
    }


    public void swapRows(int i, int j) {
        if(i<= height && j<= height) {
            double[] bucket= RowList.get(j);
            RowList.set(j, RowList.get(i));
            RowList.set(i,bucket);
        }else{
            System.out.println("There are not such rows in matrix!");
        }
    }

    @Override
    public void eye() throws MatrixProperSizeException {
        if (isSquare()) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == j) {
                        RowList.get(i)[j] = 1;
                    } else {
                        RowList.get(i)[j] = 0;
                    }
                }
            }
        }else{
            System.out.println("IDENTITY MATRIX CANNOT BE NONSQUARE!");
            throw new MatrixProperSizeException();
        }
    }


    public void multiplyRow(int row_num, double scalar) throws MatrixProperSizeException {
        if(row_num <= height) {
            for(int i = 0; i<width; i++){
                RowList.get(row_num)[i] = RowList.get(row_num)[i] * scalar;
            }
        }else{
            System.out.println("Determinant is not defined for non-square matrix!");
            throw new MatrixProperSizeException();
        }
    }


    public void subRows(int minuend, int subtrahend) throws MatrixProperSizeException {
        if(minuend <= height && subtrahend <=height) {
            for(int i = 0; i<width; i++){
                RowList.get(minuend)[i] = RowList.get(minuend)[i] - RowList.get(subtrahend)[i];
            }
        }else{
            System.out.println("Determinant is not defined for non-square matrix!");
            throw new MatrixProperSizeException();
        }
    }



//    public void multiplyRow(int row_num, float scalar) {
//        if(row_num <= height) {
//            for(float i : RowList.get(row_num)){
//                i = scalar*i;
//            }
//        }else{
//            System.out.println("There are not such rows in matrix!");
//        }
//    }



//    public void subRows(int minuend, int subtrahend) {
//        if(minuend <= height && subtrahend <=height) {
//            for(int i = 0; i<width; i++){
//                RowList.get(minuend)[i] = RowList.get(minuend)[i] - RowList.get(minuend)[subtrahend];
//            }
//        }else{
//            System.out.println("There are not such rows in matrix!");
//        }
//    }


}
