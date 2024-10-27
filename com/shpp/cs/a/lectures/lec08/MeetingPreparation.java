package com.shpp.cs.a.lectures.lec08;

import javax.management.RuntimeErrorException;
import java.io.*;

public class MeetingPreparation {

    public static void main(String[] args) throws IOException {
        String a = "a";
        char a1 = a.charAt(0);
        System.out.println(a1);

        char b = 'b';
        String b1 = String.valueOf(b);
        System.out.println(b);

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String color = br.readLine();
//        switch (color) {
//            case "red":
//                System.out.println("Aggressive");
//                break;
//            case "green":
//                System.out.println("Calm");
//                break;
//            case "blue":
//                System.out.println("Cool");
//        }

//        try {
//            BufferedReader br = new BufferedReader(new FileReader("TextFile.txt"));
//            String text = br.readLine();
//            while (!text.equals("start")) {
//                text = br.readLine();
//            }
//            while (text != null) {
//                System.out.println(text);
//                text = br.readLine();
//            }
//            br.close();
//        } catch (IOException e) {
//            System.out.println("File path is wrong \n" + e.getMessage());
//        }

//       try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bw = new BufferedWriter(new FileWriter("TextFile.txt", true));
//            String input = br.readLine();
//            while (!input.equals("")) {
//                bw.write(input);
//                bw.newLine();
//                input = br.readLine();
//            }
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//           System.out.println("Failed " + e.getMessage());
//       }

        sum(5, 3, 7, 1, 98);
        showBackSlash();
        getStringLength();
        changeString();

//        try {
//            double d = 2/1;
//            System.out.println(d);
//            try {
//                throw new IllegalArgumentException();
//            } catch (IllegalArgumentException ee) {
//                System.out.println("Fail1");
//            }
//        } catch (ArithmeticException e) {
//            System.out.println("Fail2");
//
//        }

        //exc();

        BufferedReader br = new BufferedReader(new FileReader("poem.txt"));
        String textLine = "";
        readIt(textLine, br);
        br.close();

        System.out.println(('A' == 'A') == ('A' == 'A'));

        char n = 'A';
        n++;
        System.out.println(n);
    }

    private static void readIt(String textLine, BufferedReader br) throws IOException {
        if ((textLine = br.readLine()) != null) {
            System.out.println(textLine);
            readIt(textLine, br);
        }
    }

    public static void sum(int...x) {
        int total = 0;
        for (int y : x) {
            total += y;
        }
        System.out.println("The sum: " + total);
    }

    public static void showBackSlash() {
        char z = '\\';
        System.out.println(z);
    }

    public static void getStringLength() {
        String smth = "Hello";
        int l = smth.length();
    }

    public static void changeString() {
        String text = "My name is Vova";
        String noSpaces = text.replaceAll("\\s", "");
        System.out.println(noSpaces);
    }

    private static RuntimeException exc() {
        throw new RuntimeException();
    }

}
