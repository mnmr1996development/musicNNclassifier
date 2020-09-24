public class NeuralNetStruct {

    private Matrix input;
    private Matrix hidden1;
    private Matrix hidden2;
    private Matrix output;


    private int row1;
    private int row2;
    private int row3;
    private int row4;
    private int col1;
    private int col2;
    private int col3;
    private int col4;

    NeuralNetStruct(int inr, int inc, int h1r, int h1c, int h2r, int h2c, int our, int ouc){
        row1 = inr;
        row2 = h1r;
        row3 = h2r;
        row4 = our;
        col1 = inc;
        col2 = h1c;
        col3 = h2c;
        col3 = ouc;
        input = new Matrix(inr, inc);
        hidden1 = new Matrix(h1r, h1c);
        hidden2 = new Matrix(h2r, h2c);
        output = new Matrix(our, ouc);
    }

    void display() {
        System.out.println("Input hidden matrix:");
        input.display();
        System.out.println("Hidden hidden matrix:");
        hidden1.display();
        System.out.println("Hidden hidden matrix:");
        hidden1.display();
        System.out.println("Hidden output matrix");
        output.display();
    }

    void mutate(float rate) {
        input.mutate(rate);
        hidden1.mutate(rate);
        hidden2.mutate(rate);
        output.mutate(rate);
    }

}

