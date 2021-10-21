import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJob {

    public static void main(String[] args) {

        try{
            int port=9090;
            ServerSocket socket = new ServerSocket(port);

            while (true){
                Socket s = socket.accept();
                System.out.println("new client");
                new ServerThread(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
