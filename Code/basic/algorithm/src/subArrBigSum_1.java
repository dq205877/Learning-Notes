import java.util.Arrays;

public class subArrBigSum_1 {
    public static void main(String[] args) {
        int[] arr = {1,-3,2,4,-1,2,-3,2};
        int biggest = 0;
        int[] arrsum =new int[100];//
        for(int i=0;i<=arr.length;i++){
            if(i==0){
                arrsum[0]=arr[0];
            }else if(i+1<arr.length) {
                arrsum[i] = arr[i] + arrsum[i-1];
            }
        }
        System.out.println(Arrays.toString(arrsum));
        for(int i=1;i<=arr.length;i++){
            for(int j=1;j<arr.length;j++){
               int sum = arrsum[j]-arrsum[i-1];
                if(sum>biggest){
                    biggest=sum;
                }
            }
        }
        System.out.println(biggest);
    }
}
