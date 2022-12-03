import java.util.HashMap;
import java.util.Map;

public class testHashMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        f();
    }


    public static void f(){
        String[] a  = new String[2];
        Object[] b = a;
        a[0]="hi";
        b[1]=Integer.valueOf(42);//王垠blog
    }
}
