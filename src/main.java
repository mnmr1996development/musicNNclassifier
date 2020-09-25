import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Matrix mike = new Matrix(1,3);
        int tight [] = {1,2,3};
        NeuralNetStruct milo = new NeuralNetStruct(tight);


        Matrix k = new Matrix(2,1);

        Matrix l = new Matrix(3,2);

        Matrix f = new Matrix(1,2);

        k.setSpot(1,0,1);
        k.setSpot(0,0,2);


        for(int i = 0; i < l.getRow(); i++){
            for (int j =0; j < l.getCol(); j++){
                l.setSpot(i,j,i+j);
            }
        }

        for(int i = 0; i < f.getRow(); i++){
            for (int j =0; j < f.getCol(); j++){
                f.setSpot(i,j,2);
            }
        }

        f.display();
        milo.setMat(0,k);
        milo.setMat(1,l);
        System.out.println();
        milo.ForwardPropGuess(f).display();


      milo.display();





    }



}
