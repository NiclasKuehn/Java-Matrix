public class App {
    public static void main(String[] args) throws Exception {
        Matrix test1 = new Matrix("b", 3, 3);
        test1.setValue(0, 0, 1);
        test1.setValue(0, 1, 3);
        test1.setValue(0, 2, 0);
        test1.setValue(1, 0, 0);
        test1.setValue(1, 1, 1);
        test1.setValue(1, 2, 1);
        test1.setValue(2, 0, 1);
        test1.setValue(2, 1, 2);
        test1.setValue(2, 2, -1);

        test1.print();
        Matrix test2 = new Matrix("b", 3, 3);
        test2.setValue(0, 0, 1);
        test2.setValue(0, 1, 1);
        test2.setValue(0, 2, 0);
        test2.setValue(1, 0, 0);
        test2.setValue(1, 1, 2);
        test2.setValue(1, 2, 1);
        test2.setValue(2, 0, 1);
        test2.setValue(2, 1, 0);
        test2.setValue(2, 2, -3);
        test2.print();
        test2.mul(test1).print();

        System.out.println("Determinante ist:  " + test1.det());
        System.out.println(test1.detinfo);
        Matrix t = new Matrix(1, 5, 5);
        t.print();

        MainFrame myFrame = new MainFrame();
        myFrame.setMatrixinputtf(3, 3);

    }
}
