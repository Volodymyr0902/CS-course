package com.shpp.cs.a.lectures.lec08;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exceptions {

    public static void main(String[] args) throws MyException {


        String userInput = null;

        try (BufferedReader newbr = new BufferedReader(new InputStreamReader(System.in))){
            userInput = newbr.readLine();
            if (userInput.equals("")) {
                throw new MyException();
            }
            System.out.println(userInput);
        } catch (IOException e) {
            System.out.println("FAILURE");
        }

    }



    //    @Override
//    public void run() {
//        try {
//            readPoem();
//        } catch (IOException e) {
//            println("Failure!");
//        }
//    }
//
//    private void readPoem() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("poem.html"));
//        String anyLine;
//        while ((anyLine = br.readLine()) != null) {
//            println(anyLine);
//        }
//        br.close();
//    }

}
