package com.shpp.cs.a.lectures.lec15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Reader extends Thread {
    static String text;

    @Override
    public void run() {
        text = read();
        while (text != null && !text.equals("stop")) {
            text = read();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("End");
    }

    public String read() {
        try {
            BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static String getText() {
        return text;
    }
}

class Writer extends Thread {
    @Override
    public void run() {
        ArrayList<String> inputs = new ArrayList<>();
        write(inputs);
    }

    public void write(ArrayList<String> inputs) {
        try {
            while (Reader.getText() == null) {
                Thread.onSpinWait();
            }
            String text = Reader.getText();
            inputs.add(text);
            while (!Reader.getText().equals("stop")) {
                for (String input : inputs) {
                    System.out.print(input + "\n");
                    Thread.sleep(1000);
                }
                if (!text.equals(Reader.getText())) {
                    this.write(inputs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Multithreading {
    public static void main(String[] args) {
        Reader thread1 = new Reader();
        thread1.start();

        Writer thread2 = new Writer();
        thread2.start();
    }
}
