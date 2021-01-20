package d20201112;

import java.util.Arrays;

public class SortArray2 {
	public static void main(String args[]) {
		int[] arr= {1,2,3,5,4,6,8,7};
		int[] arrPro=sortArrayByParityII(arr);
//		int A= arr.length;
//		int i = 0;
//        for (int x : arr) {
//            if (x % 2 == 0) {
//                arr[i] = x;
//                i += 2;
//            }
//        }
//        i = 1;
//        for (int x : arr) {
//            if (x % 2 == 1) {
//                arr[i] = x;
//                i += 2;
//            }
//        }
       System.out.print(Arrays.toString(arr));

       System.out.print(Arrays.toString(arrPro));
           
	}   

	public static int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int[] ans = new int[n];

        int i = 0;
        for (int x : A) {
            if (x % 2 == 0) {
                ans[i] = x;
                i += 2;
            }
        }
        i = 1;
        for (int x : A) {
            if (x % 2 == 1) {
                ans[i] = x;
                i += 2;
            }
        }
        return ans;
    }

}
