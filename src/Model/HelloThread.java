package Model;

public class HelloThread implements Runnable {
    int index;
    public HelloThread(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(String.format("Hello world, %d", index));
    }
}
