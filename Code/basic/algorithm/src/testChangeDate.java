import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class testChangeDate {
    public static void main(String[] args) {
        String date="2020-12-31";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat d =new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(d.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        c.roll(Calendar.DAY_OF_YEAR,1);//roll不能跨年
        c.add(Calendar.DAY_OF_YEAR,1);
        String r =null;
        r=d.format(c.getTime());
        System.out.println(r);

    }
}
