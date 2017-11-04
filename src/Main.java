import Model.TCPServer;
import Repository.Repository;

public class Main {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++){
//            new Thread(new HelloThread(i)).start();
//        }
        Repository repo = new Repository("myparking.db");

        TCPServer server = new TCPServer(repo,1234);
        server.run();
    }
}
