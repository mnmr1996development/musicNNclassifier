import java.util.*;

public class Matrix {
    private int row;
    private int col;
    double [][] matrix;

    Matrix(int row, int col){
        this.row = row;
        this.col = col;
        matrix = new double[row][col];
    }

    Matrix(double[][] m) {
        matrix = m;
        col = m.length;
        row = m[0].length;
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
        temp[row][col - 1] = 1;
        row = row + 1;
        matrix = temp;
    }

    Matrix crossover(Matrix p) {
        Matrix temp = new Matrix(row, col);
        int randR = (int) Math.floor(Math.random()*row);
        int randC = (int) Math.floor(Math.random()*col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i< randR)|| (i==randR && j<=randC)) {
                    temp.matrix[i][j] = matrix[i][j];
                } else {
                    temp.matrix[i][j] = p.matrix[i][j];
                }
            }
        }
        return temp;
    }
}
