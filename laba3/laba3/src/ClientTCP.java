import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String serverName = "server";
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            OutputStream outputStream = clientSocket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);
            String str = scanner.nextLine();
            out.writeUTF(str);

            //out.writeUTF("Привет из " + clientSocket.getLocalSocketAddress());

            InputStream inputStream = clientSocket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);

            System.out.println(in.readUTF());

            clientSocket.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
