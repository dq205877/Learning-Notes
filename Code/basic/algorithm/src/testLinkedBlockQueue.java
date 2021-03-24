import java.sql.SQLOutput;
import java.util.concurrent.LinkedBlockingQueue;

public class testLinkedBlockQueue {

    /*
    *
    *
    *
    *  private E dequeue() {
        // assert takeLock.isHeldByCurrentThread();
        // assert head.item == null;
        Node<E> h = head;   // 1 , 2,  3
        Node<E> first = h.next; //first =2
        h.next = h; // help GC //h.next=Node1 h= Node1 如果h.next是2则回收不了，为什么不h.next=null;
        head = first; //2,3
        E x = first.item;
        first.item = null;
        return x;
    }
    *
    * */

    static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(600000);
    static volatile Integer pi=0;
    static volatile Integer creatSum=0,consumerSum=0;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> create(pi));
        Thread thread1 = new Thread(()->consumer());
        thread.start();
        thread1.start();

    }

    public static synchronized void create(Integer i){//要用到synchronized关键字，锁定哪个对象就用哪个对象来执行notify(), notifyAll(),wait(), wait(long), wait(long, int)操作，否则就会报IllegalMonitorStateException异常。
        while (true){
            pi++;
//            linkedBlockingQueue.offer(pi);
            try {
                System.out.println(pi);
            linkedBlockingQueue.put(pi);
                creatSum= creatSum+pi;
                System.out.println("create:"+creatSum);
            System.out.println("size:"+linkedBlockingQueue.size());
            if(pi==666666666){
                testLinkedBlockQueue.class.wait();
                //Thread.currentThread().getClass().wait();
            }
//                Thread.sleep(2000);
            }catch (InterruptedException e){}
        }
    }
    public static void consumer(){
        while (true){
            try {

//                Thread.sleep(1000);
//                System.out.println(linkedBlockingQueue.take()+"----------");
                consumerSum= consumerSum+(Integer) linkedBlockingQueue.take();
                System.out.println("consumer:"+consumerSum);
//                pi--;
            }catch (InterruptedException e){}
        }
    }
}
