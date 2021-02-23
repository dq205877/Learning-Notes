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

    static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(5);
    static volatile Integer pi=0;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> create(pi));
        Thread thread1 = new Thread(()->consumer());
        thread.start();
        thread1.start();

    }

    public static void create(Integer i){
        while (true){
//            linkedBlockingQueue.offer(i++);
//            pi++;
            try {
                pi++;
                System.out.println(pi);
            linkedBlockingQueue.put(i);
                System.out.println("create:"+i);
            System.out.println("size:"+linkedBlockingQueue.size());
            }catch (InterruptedException e){}
        }
    }
    public static void consumer(){
        while (true){
            try {

                Thread.sleep(1000);
                System.out.println("consumer:"+linkedBlockingQueue.take());
                pi--;
            }catch (InterruptedException e){}
        }
    }
}
