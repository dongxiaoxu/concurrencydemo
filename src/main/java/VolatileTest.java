/**
 * Created by dongxiaoxu on 2019/7/31.
 */
public class VolatileTest {

    private final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        synchronized (lock) {
            lock.notify();
        }
    }
}
