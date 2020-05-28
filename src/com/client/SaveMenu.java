package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveMenu implements ActionListener {
    private static final String SAVE = "save";
    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    private static final String CLASS_PATH = CLASS_PREFIX + SERVICE;

    private JFrame iframe,frame;
    private JTextField money;

    public SaveMenu(){
        iframe = new JFrame("Save");
        iframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JPanel ip0 = new JPanel();
        ip0.add(new JLabel("金额:"));
        money = new JTextField(20);
        ip0.add(money);

        JPanel ip1 = new JPanel();
        JButton confirm = new JButton("确定");
        confirm.addActionListener(this);
        ip1.add(confirm);
        JButton cancel = new JButton("返回");
        cancel.addActionListener(this);
        ip1.add(cancel);

        iframe.add(ip0);
        iframe.add(ip1);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
        iframe.setBounds(500,500,350,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("确定")){
            double textMoney = Double.parseDouble(money.getText());

            if(textMoney < 0){
                JOptionPane.showMessageDialog(iframe,"输入金额非法");
            }else if(textMoney > 20000){
                JOptionPane.showMessageDialog(iframe,"输入金额超20000");
            }else{
                Class<?>[] paramTypes = {String.class, double.class};
                Object[] param = {LoginMenu.currentUser,textMoney};
                String result = Remote.invoke(CLASS_PATH,SAVE,paramTypes,param);

                if(!result.equals("")){
                    JOptionPane.showMessageDialog(frame,result);
                }else {
                    JOptionPane.showMessageDialog(frame,"存款失败");
                }
            }
        }else if(cmd.equals("返回")){
            iframe.setVisible(false);
            new Menu();
        }
    }

    public static void main(String[] args) {
        new SaveMenu();
    }
}
