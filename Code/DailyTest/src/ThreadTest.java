import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest extends Thread{

    private    volatile   int i = 1;
    public  volatile boolean  flag = true;
    @Override
    public void run() {
        synchronized(this) {
            while (flag) {
                i=new AtomicInteger(i).incrementAndGet();
                System.out.println("23232" + Thread.currentThread().getName());
                System.out.println(i);
            }
            System.out.println("stop");
        }
    }

    public static void main(String[] args) {
        ThreadTest thread = new ThreadTest();
        ThreadTest thread2 = new ThreadTest();
        System.out.println(thread.i);
        thread.start();
//        thread2.start();

        synchronized (Thread.currentThread()) {
        while (true) {
            System.out.println(thread.i + "5555");
                if (thread.i > 2) {
                    thread.flag = false;
                    break;
                }
                System.out.println(thread.i + "5555");

            }
        }
    }
}
