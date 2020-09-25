import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Matrix mike = new Matrix(1,3);
        int tight [] = {1,2,3};
        NeuralNetStruct milo = new NeuralNetStruct(tight);


        Matrix k = new Matrix(2,1);

        Matrix l = new Matrix(3,2);
        k.setSpot(1,0,1);
        k.setSpot(0,0,2);


        for(int i = 0; i < l.getRow(); i++){
            for (int j =0; j < l.getCol(); j++){
                l.setSpot(i,j,i+j);
            }
        }





        System.out.println();
        milo.setMat(0,k);

        System.out.println(milo.getIthMat(1).getRow());
        System.out.println(milo.getIthMat(1).getCol());


    }



}
