import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {

    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT); // listen to connections

        System.out.println("waitign for client connection");

        Socket client = listener.accept();

        System.out.println("server conneceed to client");

        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        out.println((new Date()).toString());

        System.out.println("data send server");

        client.close();
        listener.close();
    }
}
