package MatrixBasics;

import java.util.ArrayList;
import java.util.List;

public class IdentityMatrix extends Matrix {
    public List<double[]> RowList;

    public IdentityMatrix(int size) {
        super(size);
        super.printMatrix();
        this.RowList = new ArrayList<>();
            for(int i = 0; i<size; i++){
                double[] fast_list = new double[size];
                RowList.add(fast_list);
            }
        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                if(i==j){
                    RowList.get(i)[j] = 1;
                }else{
                    RowList.get(i)[j] = 0;
                }
            }
        }
        System.out.println(RowList.get(3)[4]);
    }
}
