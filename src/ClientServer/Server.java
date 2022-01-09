package ClientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    public ServerSocket serverSocket;
    public Socket socket;
    public static Vector<Worker> listUser = new Vector<>();

    public Server() throws IOException, Exception {
        try {
            serverSocket = new ServerSocket(12000);
            System.out.println("Server started....");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client accepted");
                Worker worker = new Worker(socket);
                Thread thread = new Thread(worker);
                listUser.add(worker);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if(socket != null){
                socket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
    public static void main(String[] args) throws IOException, Exception {
        Server sv = new Server();
    }
}
