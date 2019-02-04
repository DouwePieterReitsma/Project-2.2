import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

class MTServer {

    private static boolean diskSearched = false;
    private static Set<String> stationSet = new HashSet<>();
    private static File directory = new File("C:\\Users\\kevin\\Desktop\\Testing\\");
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