import java.lang.reflect.Array;
import java.util.Arrays;

public class subArrBigSum_1_1 {

    int[] arr = {1,-3,2,4,-1,2,-3,2};
    public static void main(String[] args) {
        int[] arr = {1,-3,2,4,-1,2,-3,2};
        int biggest = 0;
        int[] arrsum =new int[100];//
        for(int i=1; i<arr.length;i++){
            subArrBigSum_1_1 subArrBigSum_1_1 = new subArrBigSum_1_1();
            biggest=subArrBigSum_1_1.fregment(1,i);
        }

        System.out.println(biggest);
    }
        int fregment(int left, int right){;
        if(left==right){
            return arr[left];
        }else {
            //划分为两个规模更小的问题
            int mid = left + right >> 1;
            int lans = fregment(left, mid);
            int rans = fregment(mid + 1, right);

            //横跨分割点的情况
            int sum = 0, lmax = arr[mid], rmax = arr[mid + 1];
            for(int i = mid; i >= left; i--) {
            sum += arr[i];
            if(sum > lmax) lmax = sum;
            }
            sum = 0;
            for(int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            if(sum > rmax) rmax = sum;
            }

            //答案是三种情况的最大值
            int ans = lmax + rmax;
            if(lans > ans) ans = lans;
            if(rans > ans) ans = rans;

            return ans;
        }

    }
}
//#include <stdio.h>
//
////N是数组长度，num是待计算的数组，放在全局区是因为可以开很大的数组
//        int N, num[16777216];
//
//        int solve(int left, int right)
//        {
//        //序列长度为1时
//        if(left == right)
//        return num[left];
//
//        //划分为两个规模更小的问题
//        int mid = left + right >> 1;
//        int lans = solve(left, mid);
//        int rans = solve(mid + 1, right);
//
//        //横跨分割点的情况
//        int sum = 0, lmax = num[mid], rmax = num[mid + 1];
//        for(int i = mid; i >= left; i--) {
//        sum += num[i];
//        if(sum > lmax) lmax = sum;
//        }
//        sum = 0;
//        for(int i = mid + 1; i <= right; i++) {
//        sum += num[i];
//        if(sum > rmax) rmax = sum;
//        }
//
//        //答案是三种情况的最大值
//        int ans = lmax + rmax;
//        if(lans > ans) ans = lans;
//        if(rans > ans) ans = rans;
//
//        return ans;
//        }
//
//        int main()
//        {
//        //输入数据
//        scanf("%d", &N);
//        for(int i = 1; i <= N; i++)
//        scanf("%d", &num[i]);
//
//        printf("%d\n", solve(1, N));
//
//        return 0;
//        }