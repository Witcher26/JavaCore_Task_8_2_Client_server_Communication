package serverApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8181);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Сервер запущен!");

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String word = in.readLine();
        System.out.println("Подключен порт с номером: " + word);

        out.write("Подключение к Серверу произошло удачно!" + "\n");
        out.flush();

        out.write("Write your name" + "\n");
        out.flush();

        String name = in.readLine();
        System.out.println("The name client is " + name);

        out.write("Are you child? (yes/no)" + "\n");
        out.flush();

        word = in.readLine();
        if (word.equalsIgnoreCase("yes")) {
            String messageChild = String.format("Welcome to the kids area, %s! Let's play!", name);
            out.write(messageChild + "\n");
            out.flush();
        }

        if (word.equalsIgnoreCase("no")) {
            String messageAdult = String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name);
            out.write(messageAdult + "\n");
            out.flush();
        }
    }
}

