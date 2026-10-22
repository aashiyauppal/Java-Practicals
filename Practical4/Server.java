import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server is waiting for client...");

            Socket s = ss.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

            PrintWriter out = new PrintWriter(
                s.getOutputStream(), true);

            String message = in.readLine();
            System.out.println("Client says: " + message);

            out.println("Hello Client! Message received.");

            s.close();
            ss.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}