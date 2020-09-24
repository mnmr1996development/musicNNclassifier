public class main {
    public static void main(String[] args) {
        Matrix mike = new Matrix(1,3);

        NeuralNetStruct milo = new NeuralNetStruct(3,5,4,3,6);

        System.out.println(milo.getMatIN().getRow() + " " + milo.getMatIN().getCol()+ "\n");

        System.out.println(milo.getMath1().getRow() + " " + milo.getMath1().getCol() + "\n");

        System.out.println(milo.getMath2().getRow() + " " + milo.getMath2().getCol()+ "\n");

        System.out.println(milo.getMatout().getRow() + " " + milo.getMatout().getCol()+ "\n");

        milo.display();
    }



}
