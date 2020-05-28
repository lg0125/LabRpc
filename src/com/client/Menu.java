package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    private JFrame iframe;

    public Menu(){
        iframe = new JFrame("ATM");
        iframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JButton inquiry = new JButton("Inquiry");
        JButton withdraw = new JButton("Withdraw");
        JButton save = new JButton("Save");
        JButton exit = new JButton("Quit");

        inquiry.addActionListener(this);
        withdraw.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        JPanel mp0 = new JPanel();
        mp0.add(new JLabel("Select"));

        JPanel mp1 = new JPanel();
        mp1.add(withdraw);
        mp1.add(inquiry);
        mp1.add(save);
        mp1.add(exit);
        mp1.setLayout(new GridLayout(2,2,20,20));

        iframe.setLayout(new BorderLayout());
        iframe.setBounds(500,500,450,300);
        iframe.add(mp0,BorderLayout.NORTH);
        iframe.add(mp1,BorderLayout.CENTER);
        iframe.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "Inquiry":
                new InquiryMenu();
                break;
            case "Withdraw":
                new WithdrawMenu();
                break;
            case "Save":
                new SaveMenu();
                break;
            case "Quit":
                iframe.setVisible(false);
                new LoginMenu();
                JOptionPane.showMessageDialog(null, "请记得取走你的银行卡");
                break;
        }

        iframe.setVisible(false);

    }


    public static void main(String[] args) {
        new Menu();
    }
}
