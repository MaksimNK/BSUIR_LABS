import java.io.*;
import java.net.*;

public class ServerTCP extends Thread {

    private ServerSocket serverSocket;

    public ServerTCP() throws IOException{
        serverSocket = new ServerSocket(8080);
    }

    public void run(){

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                String str = in.readUTF();
                System.out.println(str);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String translateStr = translate("ru", "en", str);
                out.writeUTF(translateStr);

            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        try {

            Thread thread = new ServerTCP();
            thread.start();

        } catch (Exception e){
            System.out.println(e);
        }

    }
    private static String translate (String langFrom, String langTo, String text) throws IOException {

        String urlStr = "https://script.google.com/macros/s/AKfycbx8nGX4MoUoqiJEb3zKYIqEJivzoCAWrrd4dIquuiDIZOxS7YGOsQj8r7H68k-hpULHqQ/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;

        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
