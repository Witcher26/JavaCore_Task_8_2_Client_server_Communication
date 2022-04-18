package clientapp;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String name = "Netology";
    private static final String messFromServ = "Message from Server: ";

    public static void main(String[] args) {

        try {
            try (Socket clientSocket = new Socket("localhost", 8181);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                System.out.printf("Подключение к серверу с портом %s ", clientSocket.getPort() + "\n");

                String word = Integer.toString(clientSocket.getPort());
                out.write(word + "\n");
                out.flush();

                String serverWord = in.readLine();
                System.out.println(messFromServ + serverWord);

                serverWord = in.readLine();
                System.out.println(messFromServ + serverWord);
                if (serverWord.equalsIgnoreCase("Write your name")) {
                    out.write(name + "\n");
                    out.flush();
                }

                serverWord = in.readLine();
                System.out.println(messFromServ + serverWord);
                if (serverWord.equalsIgnoreCase("Write your name")) {
                    out.write("My name is Netology" + "\n");
                    out.flush();
                }
                out.write("yes" + "\n");
                out.flush();

                serverWord = in.readLine();
                System.out.println(messFromServ + serverWord);
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}





