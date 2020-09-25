import java.util.ArrayList;

public class NeuralNetStruct {


    public ArrayList<Matrix> reuasableNN = new ArrayList<Matrix>();


    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> cols = new ArrayList<Integer>();
    private ArrayList<Double> error = new ArrayList<Double>();


    NeuralNetStruct(int [] nodeSet){
        if(nodeSet.length >= 2)
        for(int i = 0; i < nodeSet.length-1; i++){
            cols.add(nodeSet[i]);
            rows.add(nodeSet[i+1]);
        }

        for(int i = 0; i < nodeSet.length-1; i++){
            reuasableNN.add(new Matrix(rows.get(i),cols.get(i)));
        }

        for(int i = 0; i < reuasableNN.size(); i++) {
            reuasableNN.get(i).randomizer();
        }

        for(int i = 0; i < nodeSet[nodeSet.length-1]; i++){
            error.add(0.0);
        }

    }

    public void display() {
        for(int i = 0; i < reuasableNN.size(); i++) {
            System.out.println("Layer: " +  i);
            reuasableNN.get(i).display();
        }
    }

    public void showErrors(){
        System.out.println("Errors");
        for(int i = 0; i < error.size(); i++){
            System.out.print(error.get(i) + "\t");
        }
    }

    public void mutate(float rate) {
        for(int i = 0; i < reuasableNN.size(); i++) {
            reuasableNN.get(i).mutate(rate);
        }
    }

    public void setMat(int row, Matrix m) {
        if(row == 0){
            if(m.getRow() == reuasableNN.get(1).getCol()){
                reuasableNN.set(0,m);
            }
            else {
                System.out.println("row 1 isn't the right dimensions");
            }
        }
        else if(row < reuasableNN.size()){
            if(m.getRow() == reuasableNN.get(row-1).getCol() &&
                    m.getCol() == reuasableNN.get(row+1).getCol()){
                reuasableNN.set(row,m);
            }
            else {
                System.out.println("dimensions are wrong");
            }
        }
        else if(row == reuasableNN.size()-1) {
            if(m.getRow() == reuasableNN.get(row-1).getCol()){
                reuasableNN.set(row,m);
            }
            else {
                System.out.println("dimensions are wrong");
            }
        }
        else if(row == reuasableNN.size()){
            if(m.getRow() == reuasableNN.get(row-1).getCol()){
                reuasableNN.add(m);
            }
            else {
                System.out.println("dimensions are wrong");
            }
        }
        else{
            System.out.println("row number too big");
        }
    }

    public Matrix multiplyMatrices(Matrix mat1, Matrix mat2){
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

    public Matrix getIthMat(int i){
        return reuasableNN.get(i);
    }


//    public Matrix ForwardProp (Matrix inputMatrix) {
//        inputMatrix.addBias();
//        Matrix first_hiddenLayer = multiplyMatrices(input, inputMatrix);
//        first_hiddenLayer.activate();
//        first_hiddenLayer.addBias();
//        Matrix second_hiddenLayer = multiplyMatrices(hidden1, first_hiddenLayer);
//        second_hiddenLayer.activate();
//        second_hiddenLayer.addBias();
//        Matrix third_hidden = multiplyMatrices(hidden2, second_hiddenLayer);
//        third_hidden.activate();
//        third_hidden.addBias();
//        Matrix outputLayer = multiplyMatrices(hidden2, third_hidden);
//        outputLayer.activate();
//        return outputLayer;
//    }

//    public Matrix ForwardPropGuess (Matrix inputMatrix) {
//        Matrix k = new Matrix();
//        for(int i = 0; i < reuasableNN.size()-1; i++){
//                k.addBias();
//                k = multiplyMatrices(reuasableNN.get(i),k);
//        }
//        return k;
//    }

//    public Matrix BackProp (Matrix outputMatrix, double error) {
//        inputMatrix.addBias();
//        Matrix first_hiddenLayer = multiplyMatrices(input, inputMatrix);
//        first_hiddenLayer.activate();
//        first_hiddenLayer.addBias();
//        Matrix second_hiddenLayer = multiplyMatrices(hidden1, first_hiddenLayer);
//        second_hiddenLayer.activate();
//        second_hiddenLayer.addBias();
//        Matrix third_hidden = multiplyMatrices(hidden2, second_hiddenLayer);
//        third_hidden.activate();
//        third_hidden.addBias();
//        Matrix outputLayer = multiplyMatrices(hidden2, third_hidden);
//        outputLayer.activate();
//        return outputLayer;
//    }

}

