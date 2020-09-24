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

    NeuralNetStruct(int nodesset1, int nodeset2, int nodeset3, int nodeset4, int nodeset5){
        //first Matrix in NN
        col1 = nodesset1;
        row1 = nodeset2;

        col2 = nodeset2;


        row2 = nodeset3;
        row3 = nodeset4;
        row4 = nodeset5;


        col3 = nodeset3;
        col4 = nodeset4;
        input = new Matrix(nodeset2, nodesset1);
        hidden1 = new Matrix(nodeset3, nodeset2);
        hidden2 = new Matrix(nodeset4, nodeset3);
        output = new Matrix(nodeset5, nodeset4);
        input.randomizer();
        hidden1.randomizer();
        hidden2.randomizer();
        output.randomizer();
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

    static Matrix multiplyMatrices(Matrix mat1, Matrix mat2){
        if(mat1.getCol() != mat2.getRow()){
            System.out.print("error");
        }
        Matrix product = new Matrix(mat1.getRow(),mat2.getCol());
        double var = 0;

        for(int i = 0; i < mat1.getRow(); i++){
            for (int j = 0 ; j < mat2.getCol(); j++){
                for (int k = 0; k < mat1.getCol(); k++){
                    var += mat1.getValue(i,k) * mat2.getValue(k,j);
                }
                product.setSpot(i,j,var);
                var = 0;
            }
        }
        return product;
    }

    public Matrix getMatIN(){
        return input;
    }

    public Matrix getMath1(){
        return hidden1;
    }
    public Matrix getMath2(){
        return hidden2;
    }
    public Matrix getMatout(){
        return output;
    }

    public Matrix ForwardProp (Matrix inputMatrix) {
        inputMatrix.addBias();
        Matrix first_hiddenLayer = multiplyMatrices(input, inputMatrix);
        first_hiddenLayer.activate();
        first_hiddenLayer.addBias();
        Matrix second_hiddenLayer = multiplyMatrices(hidden1, first_hiddenLayer);
        second_hiddenLayer.activate();
        second_hiddenLayer.addBias();
        Matrix third_hidden = multiplyMatrices(hidden2, second_hiddenLayer);
        third_hidden.activate();
        third_hidden.addBias();
        Matrix outputLayer = multiplyMatrices(hidden2, third_hidden);
        outputLayer.activate();
        return outputLayer;
    }

}

