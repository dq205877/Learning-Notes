import java.util.Arrays;

public class PrintArray {
    public static void main(String[] args) {

        Object[] paramValues={1,2,3,3};
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < paramValues.length; i++) {
            System.out.println(paramValues[i] + ", ");
        }
        System.out.println("------------------------------------------------------------");
        for(Object n: paramValues)
            System.out.println(n+", ");
        System.out.println("------------------------------------------------------------");
        System.out.println( Arrays.toString(paramValues) );
        System.out.println("------------------------------------------------------------");
        System.out.println(Arrays.asList(paramValues));
        System.out.println("------------------------------------------------------------");
        Arrays.asList(paramValues).stream().forEach(s -> System.out.println(s));//java8
        System.out.println("------------------------------------------------------------");
    }
}
