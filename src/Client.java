import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            String hostIP= "127.0.0.1";
            int port=9090;
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

            Socket socket = new Socket(hostIP,port);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String  data;

            do {
                System.out.println(">");
                data = (String)cin.readLine();

                writer.println(data);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String response = reader.readLine();

                System.out.println(response);

            }while (!data.equals("bye"));

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
