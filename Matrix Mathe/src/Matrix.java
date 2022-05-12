
import java.util.Random;

import javax.naming.spi.DirStateFactory.Result;

public class Matrix {
    private double[][] values;
    private String label;
    public String detinfo = "";

    public Matrix() {

        this.label = "Standart Matrix";
        this.values = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    this.setValue(i, j, 1);
                } else {
                    this.setValue(i, j, 0);
                }
            }
        }
    }

    public Matrix(String label, int row, int columns) {
        this.values = new double[row][columns];
        this.label = label;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                this.values[i][j] = 0;
            }

        }
    }

    public Matrix(String label, int row, int column, double value) {
        this.values = new double[row][column];
        this.label = label;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.values[i][j] = value;
            }

        }
    }

    public Matrix(int row, int column, int maxvalue) {
        this.values = new double[row][column];
        this.label = "Random Matrix (max:" + maxvalue + ")";
        Random rand = new Random();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.values[i][j] = rand.nextInt(maxvalue);

            }

        }
    }

    private void swapCollumns(int column1, int column2){
        double s;
        this.setLabel(this.getLabel()+"*");
        for (int i = 0; i < this.rows(); i++) {
            s=getValue(i, column1);
            this.setValue(i, column1, this.getValue(i, column2));
            this.setValue(i, column2,s);
        }
    }
    private void swapRows(int row1, int row2){
        double s;
        this.setLabel(this.getLabel()+"*");
        for (int i = 0; i < this.columns(); i++) {
            s= this.getValue(row1, i);
            this.setValue(row1, i, this.getValue(row2, i));
            this.setValue(row2, i, s);
        }
    }
    private void mulRow (int row, double multiplier){
        this.setLabel(this.getLabel()+"*");
        for (int i = 0; i < this.columns(); i++) {
            this.setValue(row, i, this.getValue(row,i)*multiplier);            
        }
    }
    private void mulColumn (int column, double multiplier){
        this.setLabel(this.getLabel()+"*");
        for (int i = 0; i < this.rows(); i++) {
            this.setValue(i,column, this.getValue(i,column)*multiplier);            
        }
    }
    private void addRows(int row1, int mitrow2, double multiplier){
        for (int i = 0; i < this.columns(); i++) {
            this.setValue(row1, i,  this.getValue(row1, i) + this.getValue(mitrow2, i)*multiplier);
        }
    }
    private void addCollumns(int columns1, int mitcolumns2, double multiplier){
        for (int i = 0; i < this.rows(); i++) {
            this.setValue(i,columns1,  this.getValue(i,mitcolumns2 )*multiplier);
        }
    }
    public Matrix invertSetup(){
        if (this.det()==0){throw new IllegalArgumentException("Matrix hat nicht Determinante 0 also gibt es kein inverses");}
        Matrix EM = this.getIdentity();
        this.print();
        EM.print();
        Matrix Result = new Matrix("ZwischenMatrix zum Invertieren",this.rows(),this.columns()*2);
        for (int i = 0; i < Result.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                Result.setValue(i, j, this.getValue(i, j));
            }
            for (int j = this.columns(); j < Result.columns(); j++) {
                Result.setValue(i, j, EM.getValue(i, j-this.columns()));
            }
            
        }
        Result.print();
        Result.invertCalc();
        Result.print();
        for (int i = 0; i < Result.rows(); i++) {
            for (int j = this.columns(); j < Result.columns(); j++) {
                this.setValue(i, j-this.columns(), Result.getValue(i, j));
            }
            
        }
        return this;
    }

    private void invertCalc(){
        for (int k = 0; k  < this.rows(); k++) {
            for (int m = 0; m < this.columns(); m++) {
                if(k==m){
                    this.mulRow(k, 1/this.getValue(k, m));
                }
            }
        }
        this.print();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns()/2; j++) {
                if(i!=j&&this.getValue(i, j)!=0){
                    this.addRows(i, j, -1*this.getValue(i, j));
                    this.print();
                    this.invertCalc();
                }
            }
        }
       

    }

    public void print() {
        System.out.print("\nName:\t" + this.label + "\n\n");
        for (int i = 0; i < this.rows(); i++) {

            System.out.print("     |");
            for (int j = 0; j < this.columns(); j++) {
                System.out.print("-------|");
            }
            System.out.print("\n     |");
            for (int j = 0; j < this.columns(); j++) {
                System.out.print(" " + this.getValue(i, j) + "   |");
            }
            System.out.print("\n     |");
            for (int j = 0; j < this.columns(); j++) {
                System.out.print("       |");
            }
            System.out.print("\n");
        }
        System.out.print("      ");
        for (int j = 0; j < this.columns(); j++) {
            System.out.print("--------");
        }
        System.out.print("\n");
    }

    public void printallinfo() {
        System.out.print("\nName:\t" + this.label + "\n");
        System.out.print("Zeilen: " + this.rows() + "\tSpalten: " + this.columns() + "\n");

        this.print();
        System.out.println("\n\nDeterminante ist:  " + this.det());
        System.out.print(this.detinfo + "\n\n\n");

    }

    public double[][] getValues() {
        return this.values;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int rows() {
        return this.values.length;
    }

    public int columns() {
        return this.values[0].length;
    }

    public double getValue(int row, int column) {

        return this.values[row][column];
    }

    public void setValue(int row, int column, double value) {

        if (column >= this.columns() || column < 0 || row >= this.rows() || row < 0)
            throw new IllegalArgumentException();

        else
            this.values[row][column] = value;

    }

    public Matrix getIdentity() {
        Matrix Result = new Matrix("Identity of "+this.getLabel(),this.rows(),this.columns());
        if (Result.rows() == Result.columns()) {

            for (int i = 0; i < Result.rows(); i++) {
                for (int j = 0; j < Result.columns(); j++) {
                    if (i == j) {
                        Result.setValue(i, j, 1);
                    } else {
                        Result.setValue(i, j, 0);
                    }

                }
            }
        }
        return Result;
    }

    public Matrix mul(double value) {
        Matrix Result = new Matrix("Result of:\t" + this.getLabel() + " * " + value, this.rows(), this.columns());
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                Result.setValue(i, j, this.getValue(i, j) * value);
            }

        }
        return Result;
    }

    public Matrix mul(Matrix mulvalue) {
        if (this.columns() == mulvalue.rows()) {
            Matrix Result = new Matrix("Result of:\t" + this.getLabel() + " * " + mulvalue.getLabel(), this.rows(),
                    mulvalue.columns());
            for (int i = 0; i < Result.rows(); i++) {
                for (int j = 0; j < Result.columns(); j++) {
                    for (int k = 0; k < this.columns(); k++) {
                        Result.setValue(i, j, Result.getValue(i, j) + this.getValue(i, k) * mulvalue.getValue(k, j));
                    }

                }

            }
            return Result;
        } else {
            throw new IllegalArgumentException("Matritzen können nicht miteinander Multipliziert werden: "
                    + this.getLabel() + " * " + mulvalue.getLabel());

        }

    }

    public Matrix neg() {
        Matrix Result = this;
        Result.setLabel(Result.label + "  negiert");
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                Result.setValue(i, j, this.getValue(i, j) * -1);
            }
        }
        return Result;

    }

    public Matrix add(Matrix addMatrix) {
        if (this.columns() == addMatrix.columns() && this.rows() == addMatrix.rows()) {
            Matrix Result = new Matrix("Result of " + this.getLabel() + " + " + addMatrix.getLabel(), this.rows(),
                    this.columns());
            for (int i = 0; i < this.rows(); i++) {
                for (int j = 0; j < this.columns(); j++) {
                    Result.setValue(i, j, this.getValue(i, j) + addMatrix.getValue(i, j));
                }

            }
            return Result;
        } else
            throw new IllegalArgumentException("Matritzen können nicht miteinander Addiert werden: " + this.getLabel()
                    + " + " + addMatrix.getLabel());
    }

    public Matrix transpose() {
        Matrix Result = new Matrix("Result of transpose of: " + this.getLabel(), this.columns(), this.rows());
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                Result.setValue(j, i, this.getValue(i, j));
            }

        }
        return Result;
    }

    public Matrix cut(int row, int column) {
        Matrix Result = new Matrix("Result of " + this.getLabel() + ".cut(" + row + "," + column + ")", this.rows() - 1,
                this.columns() - 1);
        int k = 0;
        for (int i = 0; i < Result.rows(); i++) {
            int p = 0;
            for (int j = 0; j < Result.columns(); j++) {
                if (i == row) {
                    k = 1;
                }
                if (j == column) {
                    p = 1;
                }
                Result.setValue(i, j, this.getValue(i + k, j + p));

            }
        }
        return Result;
    }

    public double det() {
        if (this.columns() == 1 && this.rows() == 1) {
            return this.getValue(0, 0);
        } else if (this.columns() == 2 && this.rows() == 2) {
            return ((this.getValue(0, 0) * this.getValue(1, 1)) - (this.getValue(1, 0) * this.getValue(0, 1)));

        } else {
            if (this.columns() == this.rows()) {
                double result = 0;

                for (int i = 0; i < this.rows(); i++) {
                    Matrix uMatrix = this.cut(i, 0);
                    result = result + ((Math.pow(-1, i)) * this.getValue(i, 0) * uMatrix.det());
                    this.detinfo = this.detinfo + " + (" + Math.pow(-1, i) + " * " + this.getValue(i, 0) + " * "
                            + uMatrix.detinfo + ")";
                }

                return result;

            } else
                throw new IllegalArgumentException(
                        "Matrix(" + this.getLabel()
                                + ") ist nicht Quadratisch, Determinante kann nicht bestimmt werden");
        }

    }

}