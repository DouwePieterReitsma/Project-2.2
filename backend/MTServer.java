import java.net.*;

class MTServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        Socket connection;
        ServerSocket server = new ServerSocket(PORT);

        while (true) {

            connection = server.accept();
            Thread worker = new Thread(new Worker(connection));
            worker.start();
        }
    }
}