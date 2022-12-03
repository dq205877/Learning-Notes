import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class testThreadLoop extends Thread{

    static int j=0;
    public static void main(String[] args) {
        int i=0;

         new Thread(()->{
            while(i!=1){
                while (!Thread.currentThread().isAlive()){
                    System.out.println("death");

                }
                System.out.println(Thread.currentThread().isInterrupted());
                  Integer child = new Integer(0);
                ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(200);
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                        while(j<2000){

                        }
                        System.out.println(j++);
                        System.out.println(Thread.currentThread().getName());
                    }
                });



            }
        System.out.println("loop down");
        },"Thread").start();
    }

}
