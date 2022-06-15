import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 8080;
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.printf("New connection accepted, port is %d. Write your name?\n", clientSocket.getPort());
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d. Are you child? (yes/no)", name, clientSocket.getPort()));
                final String answer = in.readLine();
                switch (answer) {
                    case "yes":
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    case "no":
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    default:
                        System.out.println("Wrong answer!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}