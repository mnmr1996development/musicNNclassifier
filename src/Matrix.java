import java.util.*;

public class Matrix {
    private int row;
    private int col;
    double [][] matrix;


    Matrix(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new double[row][col];
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    Matrix(double[][] m) {
        this.matrix = m;
        this.col = m.length;
        this.row = m[0].length;
    }

    public double getValue(int i, int j){
        return matrix[i][j];
    }

    public void setSpot(int i, int j, double setter){
        if(i < row && j < col){
            matrix[i][j] = setter;
        }
        else {
            System.out.print("out of bounds you moron");
        }
    }

    public double [] getArray() {
        double [] res = new double [row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i * col + j] = matrix[i][j];
            }
        }
        return res;
    }
    // this just takes a matrix and gives it a random value
    public void randomizer() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = Math.random()*2-1;
            }
        }
    }
    public void activate() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = (1)/(1+Math.exp((float)-matrix[i][j]));
            }
        }
    }



    void mutate(float rate) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                float rand = (float) Math.random();
                if (rand < rate) {
                    matrix[i][j] = matrix[i][j] + (Math.random() / 5.0);
                    if (matrix[i][j] > 1) {
                        matrix[i][j] = 1;
                    }
                    if (matrix[i][j] < -1) {
                        matrix[i][j] = -1;
                    }
                }
            }
        }
    }

    void display() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    void addBias() {
        double [][]temp = new double[row + 1][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        for(int i = 0; i < col; i++){
            temp[row][i] = 1;
        }
        this.row++;
        this.matrix = temp;
    }





}
