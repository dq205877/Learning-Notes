public class testString {

    public static void main(String[] args) {
        String a= "2332";
        String b= "dd";
        String ab =new String ("2332");
        String abc = new String ("2332");
        String c= "23"+new String("32");

        String d= "23"+new String("32");
        String e= d;
        System.out.println(a==ab);
        System.out.println(abc==ab);
        System.out.println(a==c);
        System.out.println(c==d);
        System.out.println(a==e);
    }
}
