import java.util.ArrayList;

public class NeuralNetStruct {

    public ArrayList<Matrix> reuasableNN = new ArrayList<Matrix>();
    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> cols = new ArrayList<Integer>();
    private ArrayList<Matrix> all_errors = new ArrayList<Matrix>();
    private ArrayList<Matrix> storeOutputs = new ArrayList<Matrix>();
    private Matrix ideal;
    private double learningRate;
    private ArrayList<Matrix> bias = new ArrayList<Matrix>();

    NeuralNetStruct(int [] nodeSet, double learnRate){
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

        Matrix k;

        for(int i = 1; i <  nodeSet.length; i++){
            k = new Matrix(nodeSet[i],1);
            all_errors.add(k);
            storeOutputs.add(k);
        }

        for(int i = 1; i < nodeSet.length; i++){
            Matrix temp = new Matrix(nodeSet[i],1);
            temp.randomizer();
            this.bias.add(temp);
        }

        this.learningRate = learnRate;
    }

    public Matrix transpose(Matrix wtf){
        Matrix temp = new Matrix(wtf.getCol(), wtf.getRow());
        for(int i = 0; i < wtf.getRow(); i++){
            for(int j =0; j < wtf.getCol(); j++){
                temp.setSpot(j,i,wtf.getValue(i,j));
            }
        }
        return temp;
    }

    public void display() {
        for(int i = 0; i < reuasableNN.size(); i++) {
            System.out.println("Layer: " +  i);
            reuasableNN.get(i).display();
        }
    }

    public double getLearningRate(){
        return this.learningRate;
    }

    public void ChangeLearningRate(double newRate){
        this.learningRate = newRate;
    }

    Matrix addBiasWeight(Matrix mat,Matrix bias){
        if(mat.getRow() == bias.getRow() && mat.getCol() == bias.getCol()){
            Matrix temp = new Matrix(mat.getRow(),mat.getCol());
            for(int i =0; i < mat.getRow(); i++){
                for (int j = 0; j < mat.getCol(); j++){
                    temp.setSpot(i,j, mat.getValue(i,j)+bias.getValue(i,j));
                }
            }
            return temp;
        }
        else{
            return null;
        }
    }

    //sigmoid function
    public Matrix activate(Matrix input) {
        Matrix temp = new Matrix(input.getRow(),input.getCol());
        for (int i = 0; i < input.getRow(); i++) {
            for (int j = 0; j < input.getCol(); j++) {
                temp.setSpot(i,j, (1)/(1+Math.exp((float)-input.getValue(i,j))));
            }
        }
        return temp;
    }

    public Matrix activationDerivative(Matrix input) {
        return HadamardProduct(activate(input), subtractFrom(1, activate(input)));
    }



    public void show_Errors(){
        for(int i =0; i < all_errors.size(); i++){
            all_errors.get(i).display();
            System.out.println();
        }
    }

    public void show_outputs(){
        for(int i =0; i < storeOutputs.size(); i++){
            storeOutputs.get(i).display();
            System.out.println();
        }
    }


    //for my own testing purposes that my math is okay
    public void setMat(int MatPlace, Matrix m) {
        if(MatPlace == 0){
            if(m.getRow() == reuasableNN.get(1).getCol()){
                reuasableNN.set(0,m);
            }
            else {
                System.out.println("row 1 isn't the right dimensions");
            }
        }
        else if(MatPlace < reuasableNN.size()-1 && reuasableNN.size() > 2){
            if(m.getRow() == reuasableNN.get(MatPlace+1).getCol() &&
                    m.getCol() == reuasableNN.get(MatPlace-1).getRow()){
                reuasableNN.set(MatPlace,m);
            }
            else {
                System.out.println("dimensions are wrong middle");
            }
        }
        else if(MatPlace == reuasableNN.size()-1){
            if(m.getCol() == reuasableNN.get(MatPlace-1).getRow()){
                reuasableNN.set(MatPlace,m);
            }
            else {
                System.out.println("dimensions are wrong end");
            }
        }
        else if(MatPlace == reuasableNN.size()){
            if(m.getCol() == reuasableNN.get(MatPlace-1).getRow()){
                reuasableNN.add(m);
            }
            else {
                System.out.println("Can't append");
            }
        }
        else{
            System.out.println("row number too big");
        }
    }

    public Matrix multiplyMatrices(Matrix mat1, Matrix mat2){
        if(mat1.getCol() != mat2.getRow()){
            System.out.println(mat1.getCol() + " Has to be equal to " + mat2.getRow());
            return null;
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

    public void setIdeal(Matrix ideal){
        if(ideal.getRow() == all_errors.get(all_errors.size()-1).getRow() && ideal.getCol() == 1) {
            this.ideal = ideal;
        }
        else {
            System.out.println("The Amount of output Nodes Doesn't match the ideal matrix size");
        }
    }

    public Matrix Subtract(Matrix a, Matrix b){
        Matrix subdedMAt = new Matrix(a.getRow(), a.getCol());
        if(a.getCol() == b.getCol() && a.getRow() == b.getRow()){
            for(int i = 0; i < a.getRow(); i++){
                for (int j = 0; j < a.getCol(); j++){
                    subdedMAt.setSpot(i,j, a.getValue(i,j)- b.getValue(i,j));
                }
            }
            return subdedMAt;
        }
        return null;
    }

    public void printBiases(){
        for(int i =0; i < bias.size();i++){
            bias.get(i).display();
            System.out.println();
        }
    }

    public Matrix getIthMat(int i){
        return reuasableNN.get(i);
    }


    public Matrix ForwardPropGuess (Matrix inputMatrix) {

       Matrix ithLayer = multiplyMatrices(reuasableNN.get(0),inputMatrix);
        ithLayer = activate(addBiasWeight(ithLayer,bias.get(0)));
        storeOutputs.set(0,ithLayer);
        for (int i = 1; i < reuasableNN.size(); i++) {
            ithLayer = multiplyMatrices(reuasableNN.get(i), ithLayer);
            ithLayer = activate(addBiasWeight(ithLayer, bias.get(i)));
            storeOutputs.set(i,ithLayer);
        }
        return ithLayer;
    }

    public Matrix subtractFrom(int number, Matrix input){
        Matrix temp = new Matrix(input.getRow(), input.getCol());
        for(int i =0; i < input.getRow(); i++){
            for(int j =0; j < input.getCol(); j++){
                temp.setSpot(i,j, number-input.getValue(i,j));
            }
        }
        return temp;
    }

    //for the learning rate
    Matrix multiplyBy(Matrix temp, double Rate){
        for(int i =0; i < temp.getRow(); i++){
            for(int j =0; j < temp.getCol(); j++){
                temp.setSpot(i,j, temp.getValue(i,j)*Rate);
            }
        }
        return temp;
    }

    //
    Matrix HadamardProduct(Matrix input, Matrix input2){
        Matrix temp = new Matrix(input.getRow(), input.getCol());
        for(int i =0; i < temp.getRow(); i++){
            for(int j =0; j < temp.getCol(); j++){
                temp.setSpot(i,j, input.getValue(i,j)*input.getValue(i,j));
            }
        }
        return temp;
    }

    public void set_all_errors (Matrix inputMatrix, Matrix idealOutput){
        Matrix temp = ForwardPropGuess(inputMatrix);
        all_errors.set(all_errors.size()-1,Subtract(idealOutput,temp));

        all_errors.set(all_errors.size()-2, multiplyMatrices(transpose(reuasableNN.get(reuasableNN.size()-1)), all_errors.get(all_errors.size()-1)));

        for(int i = all_errors.size()-2; i >=0; i--){
            all_errors.set(i, multiplyMatrices(transpose(reuasableNN.get(i+1)), all_errors.get(i+1)));
        }
    }

    public void Train(Matrix inputMatrix, Matrix idealOutput){
        set_all_errors(inputMatrix, idealOutput);
        for(int i = storeOutputs.size()-1; i >= 0; i--) {
            Matrix map = activationDerivative(storeOutputs.get(i));
            map = HadamardProduct(storeOutputs.get(i), map);
            map = multiplyBy(map, this.learningRate);
            bias.get(i).AddBy(map);
            if(i == 0){
                reuasableNN.set(i,Subtract(reuasableNN.get(i),multiplyMatrices(map, transpose(inputMatrix))));
            }
            else {
                reuasableNN.set(i,Subtract(reuasableNN.get(i),multiplyMatrices(map, transpose(storeOutputs.get(i-1)))));
            }
        }
    }


}

