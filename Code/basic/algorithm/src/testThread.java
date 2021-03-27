public class testThread {

    public static void main(String[] args) {
        new Thread(testThread::enjoyMusic).start();
        enjoyRead();
    }
    public static void enjoyRead(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        for(;;) {
            System.out.println("enjoy reading book...");
        }
    }

    public static void enjoyMusic(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
        for(;;) {
            System.out.println("enjoy listening music...");
        }
    }
}
