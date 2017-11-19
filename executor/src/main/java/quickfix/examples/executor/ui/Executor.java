package quickfix.examples.executor.ui;

import quickfix.Application;
import quickfix.SessionID;
import quickfix.field.BeginString;
import quickfix.field.SenderCompID;
import quickfix.field.TargetCompID;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Executor {
    private JTable table1;
    private JPanel panel1;
    private JButton acceptButton;
    private JButton fillButton;


    private Application application;

    public Executor(OrderTableModel orderTableModel, Application application) {
//        TableModel dataModel = new
//                AbstractTableModel() {
//                    public int getColumnCount() {
//                        return 10;
//                    }
//
//                    public int getRowCount() {
//                        return 10;
//                    }
//
//                    public Object getValueAt(int row, int col) {
//                        return row * col;
//                    }
//                };

        this.application = application;

        table1.setModel(orderTableModel);
    }

    public static void main(String[] args) {

//        OrderTableModel orderTableModel = new OrderTableModel();
//
//        Order order = new Order();
//        order.setSessionID(new SessionID(new BeginString("Hello"), new SenderCompID("Sender"),new TargetCompID("Target")));
//
//        orderTableModel.addOrder(order);
//
//
//        JFrame frame = new JFrame("Executor");
//        frame.setContentPane(new Executor(orderTableModel).panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }

    public static void initialize(OrderTableModel orderTableModel, Application application) {

        JFrame frame = new JFrame("Executor");
        frame.setContentPane(new Executor(orderTableModel, application).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

