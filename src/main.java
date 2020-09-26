import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Matrix mike = new Matrix(3, 3);
        int tight[] = {5, 4, 6};
        NeuralNetStruct milo = new NeuralNetStruct(tight);


        Matrix k = new Matrix(3, 5);

        Matrix l = new Matrix(2, 3);

        Matrix f = new Matrix(6, 1);


        Matrix hill = new Matrix(5, 1);

        for (int i = 0; i < hill.getRow(); i++) {
            hill.setSpot(i, 0, i);
        }

        for (int i = 0; i < l.getRow(); i++) {
            for (int j = 0; j < l.getCol(); j++) {
                l.setSpot(i, j, i + j);
            }
        }

        for (int i = 0; i < k.getRow(); i++) {
            for (int j = 0; j < k.getCol(); j++) {
                k.setSpot(i, j, 1);
            }
        }

        f.setSpot(0,0,1);

        //milo.setMat(0,k);
        //milo.setMat(1,l);
        //hill.display();
        //milo.getIthMat(0).display();
        //milo.display();
        //milo.getIthMat(0).addBias();
        //milo.getIthMat(1).addBias();
        //milo.getIthMat(1).display();
        //milo.multiplyMatrices(milo.getIthMat(1),milo.multiplyMatrices(milo.getIthMat(0),hill)).display();
        //milo.ForwardPropGuess(hill);

        milo.ForwardPropGuess(hill).display();
        System.out.println();
        milo.getError(hill,f).display();
    }


}
