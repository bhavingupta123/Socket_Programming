import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/*

REF

https://medium.com/javarevisited/fundamentals-of-udp-socket-programming-in-java-4a6972370592

https://www.codejava.net/java-se/networking/java-udp-client-server-program-example

https://www.binarytides.com/udp-socket-programming-in-java/

*/

public class UDPServer {

    private static final int PORT = 9090;

    public static void main(String[] args) throws SocketException {

        DatagramSocket socket = new DatagramSocket(PORT);

        try{

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            DatagramPacket inputPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            System.out.println("awaiting client connection");

            while (true) {
                socket.receive(inputPacket);

                String receivedData = new String(inputPacket.getData());
                System.out.println("client sent:" + receivedData);

                if(receivedData.contains("bye"))
                    break;

                sendBuffer = receivedData.toUpperCase(Locale.ROOT).getBytes(StandardCharsets.UTF_8);

                InetAddress senderAddress = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();

                DatagramPacket outPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length,
                        senderAddress, senderPort
                );

                socket.send(outPacket);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            socket.close();
        }
    }
}
