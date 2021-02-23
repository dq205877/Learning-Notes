import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class testHashMap {

    public static void main(String[] args) {
        Map hashMap = new HashMap();
        Map concurrentHashMap = new ConcurrentHashMap();
        Map treeMap = new TreeMap();
        Map weakHashMap = new WeakHashMap();
    }
}
