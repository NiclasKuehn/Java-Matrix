import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel pMatrixinput;
    private int pMatrixinpotwidth = 200;
    private int pMatrixinpotheight = 200;
    private int pMatrixinpotoffwidth = 50;
    private int pMatrixinpotoffheight = 10;
    private JTextField tf[][];
    int rows = 3;
    int columns = 3;

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

        setMatrixinputtf(rows, columns);
        // init Buttons
        incColumns();
        incRows();
        decColumns();
        decRows();
        SaveButton();

        // Ende
        pack();
        setSize(1000, 600);
        setVisible(true);

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
            B_Save.setBounds(pMatrixinpotwidth / 2 + pMatrixinpotoffwidth / 2 - Width / 2,
                    pMatrixinpotheight + pMatrixinpotoffheight + 60,
                    Width, Height);
            add(B_Save);
            B_Save.addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {
                    Matrix Input = new Matrix("Input", rows, columns);

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {
                            Input.setValue(i, j, Integer.parseInt(tf[i][j].getText()));

                        }
                    }
                    Input.print();
                }
            });
        }
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
                    pMatrixinpotheight / 2 + pMatrixinpotoffheight + 20,
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
                    pMatrixinpotheight / 2 + pMatrixinpotoffheight / 2 - 40,
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
            B_add_row.setBounds(pMatrixinpotwidth / 2 + pMatrixinpotoffwidth / 2 + 20,
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
            B_remove_row.setBounds(pMatrixinpotwidth / 2 + pMatrixinpotoffwidth / 2 - 40,
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
        this.tf = new JTextField[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tf[i][j] = new JTextField();
                tf[i][j].setSize(tfwidth, tfheight);
                tf[i][j].setLayout(null);
                tf[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tf[i][j].setBounds(tfwidth * j, tfheight * i, tfwidth, tfheight);
                tf[i][j].setVisible(true);
                pMatrixinput.add(tf[i][j]);

            }

        }
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
