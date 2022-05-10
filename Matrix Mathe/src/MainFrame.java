import java.awt.*;
import javax.swing.*;

//import javafx.scene.paint.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel pMatrixinput;
    JPanel pMatrixoutput;
    private int pMatrixinpotwidth = 200;
    private int pMatrixinpotheight = 200;
    private int pMatrixinpotoffwidth = 50;
    private int pMatrixinpotoffheight = 50;
    private JTextField labelM[][];
    private JTextField tfSkalar;
    private JLabel LLösungZiffer;
    private int rows = 3;
    private int columns = 3;
    private int matrixcount = 0;
    private int MaxMatritzen = 10;
    private Matrix[] Master = new Matrix[MaxMatritzen];
    private JTextField LabelMatrixName = new JTextField();
    JComboBox<String> CBstoredMatrix1 = new JComboBox<>();
    JComboBox<String> CBstoredMatrix2 = new JComboBox<>();
    JComboBox<String> cBOperator = new JComboBox<>();

    public MainFrame() {

        // Init JFrame
        setLayout(null);
        setTitle("Matritzen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel eingabe der Matrix
        pMatrixinput = new JPanel();
        pMatrixinput.setSize(pMatrixinpotwidth, pMatrixinpotheight);
        pMatrixinput.setVisible(true);
        pMatrixinput.setLayout(null);
        pMatrixinput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pMatrixinput.setBounds(pMatrixinpotoffwidth, pMatrixinpotoffheight, pMatrixinpotwidth, pMatrixinpotheight);
        add(pMatrixinput);

        pMatrixoutput = new JPanel();
        pMatrixoutput.setLayout(null);
        pMatrixoutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pMatrixoutput.setBounds(pMatrixinpotoffwidth + 650, pMatrixinpotoffheight, pMatrixinpotwidth,
                pMatrixinpotheight);
        add(pMatrixoutput);

        // init Buttons
        incColumns();
        incRows();
        decColumns();
        decRows();
        SaveButton();
        tfMatrixLabel();
        ClearButton();
        OperationButton();

        Menuee();
        TFScalarinit();
        OperationBox();
        LLösungZifferinit();

        // Ende
        pack();
        setSize(1000, 600);
        setVisible(true);

    }

    public void showMatrixFrame(Matrix shownMatrix) {
        JFrame Name = new JFrame();
        Name.setLayout(null);
        Name.setTitle(shownMatrix.getLabel());
        // Name.setDefaultCloseOperation(Name.EXIT_ON_CLOSE);
        Name.setSize(300, 320);
        Name.setVisible(true);
        int tfwidth = (Name.getWidth() / shownMatrix.columns()) - 5;
        int tfheight = (Name.getHeight() / shownMatrix.rows()) - 12;
        JLabel Labelresults[][] = new JLabel[shownMatrix.rows()][shownMatrix.columns()];
        for (int i = 0; i < shownMatrix.rows(); i++) {
            for (int j = 0; j < shownMatrix.columns(); j++) {
                Labelresults[i][j] = new JLabel();
                Labelresults[i][j].setLayout(null);
                Labelresults[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                Labelresults[i][j].setBounds(tfwidth * j + 1, tfheight * i + 1, tfwidth, tfheight);
                Labelresults[i][j].setText(Double.toString(shownMatrix.getValue(i, j)));
                Labelresults[i][j].setVisible(true);
                Labelresults[i][j].setHorizontalAlignment(JTextField.CENTER);
                Name.add(Labelresults[i][j]);

            }

        }

    }

    public void ErrorMessage(String Message) {
        JDialog Error = new JDialog();
        Error.setTitle(Message);
        Error.setSize(400, 50);
        Error.setModal(true);
        Error.setVisible(true);

    }

    public void SaveButton() {
        // SAVE buttons
        {
            JButton B_Save = new JButton();
            int Width = 90;
            int Height = 40;
            B_Save.setSize(Width, Height);
            B_Save.setText("Save");
            B_Save.setVisible(true);
            B_Save.setLayout(null);
            B_Save.setBorder(BorderFactory.createLineBorder(Color.black));
            B_Save.setBounds(pMatrixinpotoffwidth,
                    pMatrixinpotheight + pMatrixinpotoffheight + 50,
                    Width, Height);
            add(B_Save);
            B_Save.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {
                    Matrix Input = new Matrix(LabelMatrixName.getText(), rows, columns);

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {
                            Input.setValue(i, j, Integer.parseInt(labelM[i][j].getText()));

                        }
                    }
                    storeMatrix(Input);
                    showMatrixFrame(Input);
                    Input.print();

                }
            });
        }
    }

    public void ClearButton() {
        {
            JButton B_Save = new JButton();
            int Width = 90;
            int Height = 40;
            B_Save.setSize(Width, Height);
            B_Save.setText("Clear");
            B_Save.setVisible(true);
            B_Save.setLayout(null);
            B_Save.setBorder(BorderFactory.createLineBorder(Color.black));
            B_Save.setBounds(pMatrixinpotwidth + pMatrixinpotoffwidth - Width,
                    pMatrixinpotheight + pMatrixinpotoffheight + 50,
                    Width, Height);
            add(B_Save);
            B_Save.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {
                    ClearPanel();

                }
            });
        }
    }

    public void TFScalarinit() {
        tfSkalar = new JTextField();
        tfSkalar.setLayout(null);
        tfSkalar.setBounds(pMatrixinpotoffwidth + pMatrixinpotwidth + 100,
                pMatrixinpotoffheight + pMatrixinpotheight / 2 - 20, 30, 30);
        tfSkalar.setHorizontalAlignment(JTextField.CENTER);
        tfSkalar.setBorder(BorderFactory.createLineBorder(Color.black));
        tfSkalar.setVisible(true);
        tfSkalar.setText("0");
        this.add(tfSkalar);
    }

    public void LLösungZifferinit() {
        LLösungZiffer = new JLabel();
        LLösungZiffer.setLayout(null);
        LLösungZiffer.setBounds(pMatrixinpotoffwidth + pMatrixinpotwidth + 450,
                pMatrixinpotoffheight + pMatrixinpotheight + 10, 200, 100);
        LLösungZiffer.setHorizontalAlignment(JTextField.CENTER);
        // LLösungZiffer.setBorder(BorderFactory.createLineBorder(Color.black));
        LLösungZiffer.setVisible(true);
        LLösungZiffer.setText("");
        this.add(LLösungZiffer);
    }

    public void SetLösung(String LösM) {
        LLösungZiffer.setText(LösM);
    }

    public void Menuee() {
        CBstoredMatrix1.addItem("Skalar");

        for (int i = 0; i < matrixcount; i++) {
            CBstoredMatrix1.addItem(Master[i].getLabel());
            CBstoredMatrix2.addItem(Master[i].getLabel());
        }
        CBstoredMatrix1.setBounds(pMatrixinpotoffwidth + pMatrixinpotwidth + 100, pMatrixinpotoffheight, 100, 30);
        CBstoredMatrix2.setBounds(pMatrixinpotoffwidth + pMatrixinpotwidth + 240, pMatrixinpotoffheight, 100, 30);
        this.add(CBstoredMatrix1);
        this.add(CBstoredMatrix2);
    }

    public void OperationBox() {
        cBOperator.setBounds(pMatrixinpotoffwidth + pMatrixinpotwidth + 200, pMatrixinpotoffheight + 50, 60, 30);
        cBOperator.addItem("*");
        cBOperator.addItem("+");
        cBOperator.addItem("-");
        cBOperator.addItem("det");
        this.add(cBOperator);
    }

    public void OperationButton() {// unvolständig
        // SAVE buttons
        {
            JButton B_Operate = new JButton();
            int Width = 40;
            int Height = 40;
            B_Operate.setSize(Width, Height);
            B_Operate.setText("=");
            B_Operate.setVisible(true);
            B_Operate.setLayout(null);
            B_Operate.setBorder(BorderFactory.createLineBorder(Color.black));
            B_Operate.setBounds(pMatrixinpotwidth + pMatrixinpotoffwidth + 200,
                    (pMatrixinpotheight + pMatrixinpotoffheight) / 2, Width, Height);
            add(B_Operate);
            B_Operate.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {
                    switch (String.valueOf(cBOperator.getSelectedItem())) {
                        case "*":
                            if (CBstoredMatrix1.getSelectedItem() == "Skalar") {
                                for (int j = 0; j < matrixcount; j++) {
                                    if (Master[j].getLabel() == CBstoredMatrix2.getSelectedItem()) {
                                        setMatrixResult(Master[j].mul(Integer.valueOf(tfSkalar.getText())));
                                    }
                                }
                            } else {
                                for (int i = 0; i < matrixcount; i++) {

                                    if (Master[i].getLabel() == CBstoredMatrix1.getSelectedItem()) {
                                        for (int j = 0; j < matrixcount; j++) {
                                            if (Master[j].getLabel() == CBstoredMatrix2.getSelectedItem()) {
                                                if (Master[i].columns() == Master[j].rows()) {
                                                    setMatrixResult(Master[i].mul(Master[j]));
                                                } else
                                                    ErrorMessage("Matritzen haben nicht die Passende Größe");
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "+":
                            if (CBstoredMatrix1.getSelectedItem() == "Skalar") {
                                ErrorMessage("Matrix kann nicht mit einem Skalar addiert werden");
                            } else {
                                for (int i = 0; i < matrixcount; i++) {
                                    if (Master[i].getLabel() == CBstoredMatrix1.getSelectedItem()) {
                                        for (int j = 0; j < matrixcount; j++) {
                                            if (Master[j].getLabel() == CBstoredMatrix2.getSelectedItem()) {
                                                if (Master[i].columns() == Master[j].columns()
                                                        && Master[i].rows() == Master[j].rows()) {
                                                    setMatrixResult(Master[i].add(Master[j]));
                                                } else
                                                    ErrorMessage("Matritzen haben nicht die Passende Größe");
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "-":
                            for (int i = 0; i < matrixcount; i++) {
                                if (Master[i].getLabel() == CBstoredMatrix1.getSelectedItem()) {
                                    for (int j = 0; j < matrixcount; j++) {
                                        if (Master[j].getLabel() == CBstoredMatrix2.getSelectedItem()) {
                                            if (Master[i].columns() == Master[j].columns()
                                                    && Master[i].rows() == Master[j].rows()) {
                                                setMatrixResult(Master[i].add(Master[j].neg()));
                                            } else
                                                ErrorMessage("Matritzen haben nicht die Passende Größe");
                                        }
                                    }
                                }
                            }
                            break;
                        case "det":
                            for (int i = 0; i < matrixcount; i++) {
                                if (Master[i].getLabel() == CBstoredMatrix1.getSelectedItem()) {
                                    SetLösung("Determinante von " + Master[i].getLabel() + " = "
                                            + Double.toString(Master[i].det()));
                                }
                            }
                            break;

                        default:
                            break;
                    }

                }
            });
        }
    }

    public void tfMatrixLabel() {
        int tfwidth = 100;
        int tfheight = 20;

        LabelMatrixName.setSize(tfwidth, tfheight);
        LabelMatrixName.setLayout(null);
        LabelMatrixName.setBorder(BorderFactory.createLineBorder(Color.black));
        LabelMatrixName.setBounds(pMatrixinpotoffwidth, pMatrixinpotoffheight - tfheight, tfwidth, tfheight);
        LabelMatrixName.setText("Name");
        LabelMatrixName.setVisible(true);
        this.add(LabelMatrixName);

    }

    public void storeMatrix(Matrix toSave) {

        Master[this.matrixcount] = toSave;
        CBstoredMatrix1.addItem(Master[matrixcount].getLabel());
        CBstoredMatrix2.addItem(Master[matrixcount].getLabel());
        this.matrixcount++;

    }

    public void incColumns() {
        // vergrößerungs column buttons
        {
            JButton B_add_column = new JButton();
            int AddColumnWidth = 40;
            int AddColumnHeight = 40;
            B_add_column.setSize(AddColumnWidth, AddColumnHeight);
            B_add_column.setText("+");
            B_add_column.setVisible(true);
            B_add_column.setLayout(null);
            B_add_column.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            B_add_column.setBounds(pMatrixinpotwidth + pMatrixinpotoffwidth,
                    pMatrixinpotheight / 2 + pMatrixinpotoffheight + 10,
                    AddColumnWidth, AddColumnHeight);
            add(B_add_column);
            B_add_column.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pMatrixinput.removeAll();
                    columns++;
                    setMatrixinputtf(rows, columns);

                }
            });
        }
    }

    public void decColumns() {
        // verkleinerungs column buttons
        {
            JButton B_remove_column = new JButton();
            int AddColumnWidth = 40;
            int AddColumnHeight = 40;
            B_remove_column.setSize(AddColumnWidth, AddColumnHeight);
            B_remove_column.setText("-");
            B_remove_column.setVisible(true);
            B_remove_column.setLayout(null);
            B_remove_column.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            B_remove_column.setBounds(pMatrixinpotwidth + pMatrixinpotoffwidth,
                    pMatrixinpotheight / 2 + pMatrixinpotoffheight - AddColumnHeight - 10,
                    AddColumnWidth, AddColumnHeight);
            add(B_remove_column);
            B_remove_column.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (columns > 1) {
                        pMatrixinput.removeAll();
                        columns--;
                        setMatrixinputtf(rows, columns);
                    }

                }
            });
        }
    }

    public void incRows() {
        // vergrößerungs row buttons
        {
            JButton B_add_row = new JButton();
            int AddRowWidth = 40;
            int AddRowHeight = 40;
            B_add_row.setSize(AddRowWidth, AddRowHeight);
            B_add_row.setText("+");
            B_add_row.setVisible(true);
            B_add_row.setLayout(null);
            B_add_row.setBorder(BorderFactory.createLineBorder(Color.black));
            B_add_row.setBounds(pMatrixinpotwidth / 2 + pMatrixinpotoffwidth + 10,
                    pMatrixinpotheight + pMatrixinpotoffheight,
                    AddRowWidth, AddRowHeight);
            add(B_add_row);
            B_add_row.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pMatrixinput.removeAll();
                    rows++;
                    setMatrixinputtf(rows, columns);
                }
            });
        }
    }

    public void decRows() {
        {
            JButton B_remove_row = new JButton();
            int AddRowWidth = 40;
            int AddRowHeight = 40;
            B_remove_row.setSize(AddRowWidth, AddRowHeight);
            B_remove_row.setText("-");
            B_remove_row.setVisible(true);
            B_remove_row.setLayout(null);
            B_remove_row.setBorder(BorderFactory.createLineBorder(Color.black));
            B_remove_row.setBounds(pMatrixinpotwidth / 2 + pMatrixinpotoffwidth - AddRowWidth - 10,
                    pMatrixinpotheight + pMatrixinpotoffheight,
                    AddRowWidth, AddRowHeight);
            add(B_remove_row);
            B_remove_row.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rows > 1) {
                        pMatrixinput.removeAll();
                        rows--;
                        setMatrixinputtf(rows, columns);
                    }
                }
            });
        }
    }

    public void setMatrixinputtf(int rows, int columns) {
        // Textfelder
        int tfwidth = pMatrixinpotwidth / columns;
        int tfheight = pMatrixinpotheight / rows;
        this.labelM = new JTextField[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labelM[i][j] = new JTextField();
                labelM[i][j].setSize(tfwidth, tfheight);
                labelM[i][j].setLayout(null);
                labelM[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                labelM[i][j].setBounds(tfwidth * j, tfheight * i, tfwidth, tfheight);
                labelM[i][j].setText("0");
                labelM[i][j].setVisible(true);
                labelM[i][j].setHorizontalAlignment(JTextField.CENTER);
                labelM[i][j].selectAll();
                pMatrixinput.add(labelM[i][j]);

            }
        }

        pMatrixinput.setVisible(false);
        pMatrixinput.setVisible(true);

    }

    public void setMatrixResult(Matrix Result) {
        // Textfelder
        pMatrixoutput.removeAll();
        int tfwidth = pMatrixoutput.getWidth() / Result.columns();
        int tfheight = pMatrixoutput.getHeight() / Result.rows();
        JLabel Labelresults[][] = new JLabel[Result.rows()][Result.columns()];
        for (int i = 0; i < Result.rows(); i++) {
            for (int j = 0; j < Result.columns(); j++) {
                Labelresults[i][j] = new JLabel();
                Labelresults[i][j].setLayout(null);
                Labelresults[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                Labelresults[i][j].setBounds(tfwidth * j + 1, tfheight * i + 1, tfwidth, tfheight);
                Labelresults[i][j].setText(Double.toString(Result.getValue(i, j)));
                Labelresults[i][j].setVisible(true);
                Labelresults[i][j].setHorizontalAlignment(JTextField.CENTER);
                pMatrixoutput.add(Labelresults[i][j]);

            }

        }
        pMatrixoutput.setVisible(false);
        pMatrixoutput.setVisible(true);

    }

    public void ClearPanel() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                labelM[i][j].setText("0");
                labelM[i][j].selectAll();
            }
        }
        LabelMatrixName.setText("Name");
        pMatrixinput.setVisible(false);
        pMatrixinput.setVisible(true);
    }

    public int getrows() {
        return this.rows;
    }

    public int getcolumns() {
        return this.columns;
    }

}
