package quickfix.examples.executor.ui;

import quickfix.Application;
import quickfix.FieldNotFound;
import quickfix.InvalidMessage;
import quickfix.SessionID;
import quickfix.field.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Executor {
    private JTable table1;
    private JPanel panel1;
    private JButton acceptButton;
    private JButton fillButton;


    private quickfix.examples.executor.Application application;

    public Executor(OrderTableModel orderTableModel,  quickfix.examples.executor.Application application) {
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
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Order o = orderTableModel.getOrder(table1.getSelectedRow());


                quickfix.Message message = null;
                try {
                    message = new quickfix.Message(o.getMessage());
                } catch (InvalidMessage invalidMessage) {
                    invalidMessage.printStackTrace();
                }


                OrderQty orderQty = new OrderQty();
                Symbol symbol = new Symbol();
                Side side = new Side();
                ClOrdID clOrdID = new ClOrdID();
                SessionID sessionID = o.getSessionID();


                try {
                    message.getField(orderQty);
                    message.getField(symbol);
                    message.getField(side);
                    message.getField(clOrdID);
                } catch (FieldNotFound fieldNotFound) {
                    fieldNotFound.printStackTrace();
                }

                Price price = new Price(1000);


                quickfix.fix40.ExecutionReport fill = new quickfix.fix40.ExecutionReport(new OrderID("9999"), new ExecID("9999"),
                        new ExecTransType(ExecTransType.NEW), new OrdStatus(OrdStatus.FILLED), symbol, side, orderQty, new LastShares(orderQty.getValue()), new LastPx(price.getValue()),
                        new CumQty(orderQty.getValue()), new AvgPx(price.getValue()));

                fill.set(clOrdID);

                application.sendMessage(sessionID, fill);
            }
        });
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

    public static void initialize(OrderTableModel orderTableModel, quickfix.examples.executor.Application application) {

        JFrame frame = new JFrame("Executor");
        frame.setContentPane(new Executor(orderTableModel, application).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

