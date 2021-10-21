import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text="";

            do {
                text = reader.readLine();
                System.out.println("in server got :" + text);
                writer.println("Server sends: " + text);

            } while (!text.equals("bye"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
