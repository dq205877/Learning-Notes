import java.util.ArrayList;
import java.util.List;

public class testArrayListRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("dd");
        list.add("bb");
        list.add("dd");
        list.add("cc");
        list.remove("dd");
        System.out.println(list);
    }
}
