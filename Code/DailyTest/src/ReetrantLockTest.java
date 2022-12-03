import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockTest {
    static volatile int i=0;
    public static void main(String[] args) {
        ReentrantLock reetrantLock = new ReentrantLock();
        Condition condition = reetrantLock.newCondition();
        reetrantLock.lock();
        new Thread(()->i=add(i)).start();
        reetrantLock.unlock();
        try {
            condition.await();
            condition.signal();
        }catch (InterruptedException e){

        }
        int d =i=3;
        System.out.println(d);
    }

    static Integer add(int i){
        while (i<100000) {
            System.out.println(i);

            i++;
        }
       return i;
    }
}
