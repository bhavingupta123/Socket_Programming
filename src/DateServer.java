import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream()));

        try {
            while (true) {
                String request = in.readLine();

                if (request.contains("date"))
                    out.println((new Date()).toString());
                else
                    out.println("type date");

                System.out.println("data send server");
            }
        }
        finally {
            out.close();
            in.close();
        }

    }
}
