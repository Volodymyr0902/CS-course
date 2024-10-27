package com.shpp.cs.a.lectures.lec14;

import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp extends WindowProgram {
    JLabel text = new JLabel("Type: ");
    JTextField input = new JTextField(5);
    JButton sender = new JButton("SEND");

    public void run () throws IllegalMonitorStateException {
        add(text, 50, 50);
        add(input, 100, 50);
        add(sender, 200, 50);
        addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sender) {
            printThis();
        } else {
            println("nope");
        }
    }

    public void printThis() {
        try {
            Socket s = new Socket("localhost", 5001);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            String t1 = input.getText();
            println(t1);
            out.println("dhr");
        } catch (IOException e) {
            println("Failed!");
        }
    }
}












//            PrintWriter out = new PrintWriter(s.getOutputStream());
//            out.println("Hello");
//            out.println("World!");
//
//            out.close();
//            s.close();

