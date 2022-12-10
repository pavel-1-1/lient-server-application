package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) throws ConnectException, UnknownHostException {
        String host = "freza";
        int port = 8080;
        Scanner input = new Scanner(System.in);

        //while (true) {
            try (Socket clientSocket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String value = in.readLine();
                System.out.println(value);
                boolean clientStart = true;
                while (clientStart) {
                    String nextLine = input.nextLine();
                    assert nextLine != null;
                    if (nextLine.equals("end")) {
                        //out.println(nextLine);
                        clientStart = false;
                    }
                    try {
                        out.println(nextLine);
                        String resp = in.readLine();
                        System.out.println(resp);
                    }catch (IOException e) {
                        System.out.println("ервер не доступен!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }
}
