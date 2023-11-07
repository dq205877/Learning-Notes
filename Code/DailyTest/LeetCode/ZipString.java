import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ZipString {
	
	public static void main(String[] args) {
		char[] input = {'a'};
		System.out.println(compress(input));
	}
	
	public static int compress(char[] chars) {
		if(chars.length == 1) {
			return 1;
		}
		List<Map<String,Integer>> list = new ArrayList();
        int j = 0,count = 0;
        for(int i = 0;i < chars.length;i++){
            if(chars[i] == chars[j]){
                count++;
                 if(i == chars.length - 1) {
	            	 Map<String,Integer> map1 = new HashMap();
	                 map1.put(String.valueOf(chars[j]),count);
	                 list.add(map1);
                }
            } else {
                Map<String,Integer> map = new HashMap();
                map.put(String.valueOf(chars[j]),count);
                list.add(map);

                j = i;
                count = 1;
                if(i == chars.length - 1) {
	            	 Map<String,Integer> map1 = new HashMap();
	                 map1.put(String.valueOf(chars[j]),count);
	                 list.add(map1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Map<String,Integer> map : list){
            Iterator<Entry<String,Integer>> iter = map.entrySet().iterator();
            while(iter.hasNext()){
                Entry<String,Integer> entry = iter.next();
                sb.append(entry.getKey());
                if(!String.valueOf(entry.getValue()).equals("1")){
                    sb.append(String.valueOf(entry.getValue()));
                }
            }
        }
        char[] sbCharArray = new char[sb.length()];
        for(int i = 0;i < sb.length();i++) {
        	sbCharArray[i] = sb.charAt(i);
            chars[i] = sbCharArray[i];
        }
        return sb.length();
    }
}
