package clientApp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static Socket clientSocket;
    private static final String name = "Netology";
    private static final String messFromServ = "Message from Server: ";

    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {

        try {
            try {
                clientSocket = new Socket("localhost", 8181);
                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

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
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}





