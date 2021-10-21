import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPClient {

    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        try {

            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendingBuffer = new byte[1024];
            byte[] receiveBuffer = new byte[1024];

            while (true) {

                System.out.println(">");
                String data = (String)cin.readLine();

                sendingBuffer = data.getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendingPacket = new DatagramPacket(sendingBuffer, sendingBuffer.length, IPAddress, PORT);

                socket.send(sendingPacket);

                if(data.contains("bye"))
                    break;

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("from server , packet info:" + receivePacket.getSocketAddress());

                System.out.println("from server:" + receivedData);
            }
        }

        finally {

            socket.close();
        }

    }

}
