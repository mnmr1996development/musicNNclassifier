import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Matrix mike = new Matrix(3, 3);

        //this
        int tight[] = {9, 3, 4,7};

        Matrix ideal = new Matrix(7,1);

        //ideal row must be equal to fi
        Matrix input = new Matrix(9,1);
        NeuralNetStruct milo = new NeuralNetStruct(tight, .1);

        Matrix k = new Matrix(4, 5);

        Matrix l = new Matrix(6, 4);

        Matrix f = new Matrix(3, 6);


        //Matrix hill = new Matrix(5, 1);


        for (int i = 0; i < l.getRow(); i++) {
            for (int j = 0; j < l.getCol(); j++) {
                l.setSpot(i, j, i + j);
            }
        }

        for (int i = 0; i < input.getRow(); i++) {
            for (int j = 0; j < input.getCol(); j++) {
                input.setSpot(i, j, i+j);
            }
        }
        for (int i = 0; i < f.getRow(); i++) {
            for (int j = 0; j < f.getCol(); j++) {
                f.setSpot(i, j, i + j);
            }
        }

       ideal.setSpot(0,0,1);




        milo.Train(input,ideal);
        //milo.show_Errors();
       // milo.show_outputs();
        //milo.show_Errors();


        //milo.ForwardPropGuess(help).display();

    }


}
