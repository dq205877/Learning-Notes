import java.util.Arrays;

public class subArrBigSum_2 {
    public static void main(String[] args) {
        int[] arr = {1,-3,2,4,-1,2,-3,2};
        int biggest = 0;
        int[] arrsum =new int[100];//
//        for(int i=0;i<=arr.length;i++){
//            if(i==0){
//                arrsum[0]=arr[0];
//            }else if(i+1<arr.length) {
//                arrsum[i] = arr[i] + arrsum[i-1];
//            }
//        }
        /**
         * 很多动态规划算法非常像数学中的递推。我们如果能找到一个合适的递推公式，就能很容易的解决问题。
         * 我们用dp[n]表示以第n个数结尾的最大连续子序列的和，于是存在以下递推公式：
         * dp[n] = max(0, dp[n-1]) + num[n]
         * 仔细思考后不难发现这个递推公式是正确的，则整个问题的答案是max(dp[m]) | m∈[1, N]。
         */
        System.out.println(Arrays.toString(arrsum));
        for(int i=1;i<arr.length;i++){
            if(arr[i-1]>0){
                arr[i]+=arr[i-1];
            }else {
                arr[i]+=0;
            }
            if(arr[i]>biggest){
                biggest=arr[i];
            }

        }
        System.out.println(biggest);
    }
}
