package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("server started");
        int port = 8080;
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                socket = serverSocket.accept();
                Thread newClient = new NewClient(socket);
                newClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
