package quickfix.examples.forger.ui;

import quickfix.examples.forger.Header;
import quickfix.field.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Forger {
    private JComboBox beginString;
    private JPanel forger;
    private JComboBox msgType;
    private JTextField senderCompID;
    private JTextField targetCompID;
    private JButton confirm;
    private JTextField clOrderID;
    private JTextField orderQty;
    private JComboBox ordType;
    private JTextField symbol;
    private JComboBox side;
    private JComboBox timeInForce;
    private JComboBox handlInst;

    public Forger() {


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Header header = new Header();
                Forger.this.getData(header);
                System.out.println(header.getSenderCompID());
                System.out.println(header.getTargetCompID());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Forger");
        frame.setContentPane(new Forger().forger);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    public void setData(Header data) {
        senderCompID.setText(data.getSenderCompID());
        targetCompID.setText(data.getTargetCompID());
    }

    public void getData(Header data) {
        data.setSenderCompID(senderCompID.getText());
        data.setTargetCompID(targetCompID.getText());

    }

    public boolean isModified(Header data) {
        if (senderCompID.getText() != null ? !senderCompID.getText().equals(data.getSenderCompID()) : data.getSenderCompID() != null)
            return true;
        if (targetCompID.getText() != null ? !targetCompID.getText().equals(data.getTargetCompID()) : data.getTargetCompID() != null)
            return true;
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ordType = new JComboBox<>(new OrdType[]{
                new OrdType(OrdType.MARKET)
                , new OrdType(OrdType.LIMIT)
                , new OrdType(OrdType.STOP)
        });

        side = new JComboBox<>(new Side[]{
                new Side(Side.BUY)
                , new Side(Side.SELL)
                , new Side(Side.SELL_SHORT)
        });

        timeInForce = new JComboBox<>(new TimeInForce[]{
                new TimeInForce(TimeInForce.DAY)
                , new TimeInForce(TimeInForce.FILL_OR_KILL)
                , new TimeInForce(TimeInForce.GOOD_TILL_CANCEL)
        });

        msgType = new JComboBox<>(new MsgType[]{
                new MsgType(MsgType.ORDER_SINGLE)
                , new MsgType(MsgType.ORDER_CANCEL_REJECT)

        });

        handlInst = new JComboBox<>( new HandlInst[]{
                new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PRIVATE)
                ,new HandlInst(HandlInst.AUTOMATED_EXECUTION_ORDER_PUBLIC)
        });
    }

}

