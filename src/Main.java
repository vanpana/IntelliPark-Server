import Model.HelloThread;
import Model.TCPServer;

public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++){
//            new Thread(new HelloThread(i)).start();
//        }
        TCPServer server = new TCPServer(1234);
        server.run();
    }
}
