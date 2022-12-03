import java.util.Arrays;
import java.util.HashSet;

public class TwoCount {
    public static void main(String[] args) {
        int [] array = {1,2,3,7,10};
        int[] result = twoSum(array,12);
        System.out.println(Arrays.toString(result));
    }

    public static  int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashSet<Integer> set = new HashSet<Integer>();
        for(int a:nums){
            set.add(a);
        }
        int dd=0;boolean flag=false;
        for(int i=0;i<nums.length;i++){
            int tem = target-nums[i];
            if(set.contains(tem) &&!flag){
                result[0]=i;
                dd= tem;
                flag=true;
            }
            if(flag && (dd==nums[i])){
                result[1]=i;
                return result;
            }
        }
        return  null;
    }
}
