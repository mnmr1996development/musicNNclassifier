import java.util.ArrayList;

public class NeuralNetStruct {


    public ArrayList<Matrix> reuasableNN = new ArrayList<Matrix>();


    private ArrayList<Integer> rows = new ArrayList<Integer>();
    private ArrayList<Integer> cols = new ArrayList<Integer>();
    private ArrayList<Matrix> all_errors = new ArrayList<>();
    private Matrix ideal;
    private double learningRate;

    public ArrayList<Matrix> bias = new ArrayList<Matrix>();



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


    public void show_Errors(){
        for(int i =0; i < all_errors.size(); i++){
            all_errors.get(i).display();
            System.out.println();
        }
    }

    public void mutate(float rate) {
        for(int i = 0; i < reuasableNN.size(); i++) {
            reuasableNN.get(i).mutate(rate);
        }
    }


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
        ithLayer = addBiasWeight(ithLayer,bias.get(0));
        ithLayer.activate();
        for (int i = 1; i < reuasableNN.size(); i++) {
            ithLayer = multiplyMatrices(reuasableNN.get(i), ithLayer);
            ithLayer = addBiasWeight(ithLayer, bias.get(i));
            ithLayer.activate();
        }
        return ithLayer;
    }

    public void set_all_errors (Matrix inputMatrix, Matrix idealOutput){
        Matrix temp = ForwardPropGuess(inputMatrix);
        all_errors.set(all_errors.size()-1,Subtract(idealOutput,temp));

        all_errors.set(all_errors.size()-2, multiplyMatrices(transpose(reuasableNN.get(reuasableNN.size()-1)), all_errors.get(all_errors.size()-1)));

        for(int i = all_errors.size()-2; i >=0; i--){
            all_errors.set(i, multiplyMatrices(transpose(reuasableNN.get(i+1)), all_errors.get(i+1)));
        }
    }




//    public Matrix BackProp (Matrix outputMatrix, double error) {
//
//    }

}

