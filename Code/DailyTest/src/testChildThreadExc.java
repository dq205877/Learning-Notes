import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingQueue;

public class testChildThreadExc implements Runnable{
    public static void main(String[] args) {

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(0);

        try {
        linkedBlockingQueue.put("a");

        }catch (Exception e){}
        Thread thread = new Thread(new testChildThreadExc());
//        thread.start();
        while(true){
            System.out.println("000000000000000");
        }

    }

    @Override
    public void run(){
        while(true) {
            System.out.println("111111111111111111111111111111111111111111111111111111111");
            throwExe();
        }
    }

    public void throwExe(){
        throw new RuntimeException();

//        throw new InvocationTargetException();
    }
}
