package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InquiryMenu implements ActionListener {

    private JFrame iframe,frame;
    private JPanel ip0,ip1;
    private JButton confirm,back;

    private static final String INQUIRY = "inquiry";

    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    private static final String CLASS_PATH = CLASS_PREFIX + SERVICE;

    public InquiryMenu(){

        iframe = new JFrame("查询");
        iframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        ip0 = new JPanel();
        ip0.add(new JLabel("账号id:"+LoginMenu.currentUser));

        ip1 = new JPanel();
        confirm = new JButton("查询");
        confirm.addActionListener(this);
        back = new JButton("返回");
        back.addActionListener(this);
        ip1.add(confirm);
        ip1.add(back);

        iframe.add(ip0);
        iframe.add(ip1);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
        iframe.setBounds(500,300,350,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if(cmd.equals("查询")){
            Class<?>[] paramTypes = {String.class};
            Object[] param = {LoginMenu.currentUser};
            String result = Remote.invoke(CLASS_PATH, INQUIRY,paramTypes,param);

            if (!result.equals("")) {
                JOptionPane.showMessageDialog(frame, result);

            }else {
                JOptionPane.showMessageDialog(frame, "查询失败!");
            }
        }else if(cmd.equals("返回")){
            iframe.setVisible(false);
            new Menu();
        }
    }

    public static void main(String[] args) {
        new InquiryMenu();
    }
}


