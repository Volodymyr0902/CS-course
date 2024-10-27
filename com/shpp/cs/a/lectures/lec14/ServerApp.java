package com.shpp.cs.a.lectures.lec14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5001);
            Socket s = ss.accept();

            BufferedReader scan = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String message = "";
            while ((message = scan.readLine()) != null) {
                System.out.println(message);
            }

            scan.close();
            s.close();
            ss.close();
            System.out.println("Closed");
        } catch (IOException e) {
            System.out.println("Server down");
        }
    }
}
