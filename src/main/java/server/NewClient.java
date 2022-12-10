package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class NewClient extends Thread {
    private final Socket socket;

    protected NewClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("new client: " + socket.getPort() + socket.getLocalAddress());
            out.println("start go: ");
            boolean startServer = true;
            String inStr;
            while (startServer) {
                inStr = in.readLine();
                assert inStr != null;
                if (inStr.equals("end")) {
                   break;
                }
                out.println(String.format("hello %s ", inStr));
                System.out.println(inStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}