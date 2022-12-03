import java.util.ArrayList;
import java.util.List;

public class testGeneric {
    public static void main(String[] args) {
        testGeneric a = new testGeneric();
//        a.xxx();
        xxx(new ArrayList<>());
        List< ? super Integer> list=new ArrayList<>();
        list.add(1);
    }
    public static void xxx(List< ? super Integer> list){
        list.add(1);
        System.out.println(list);
    }
}
