import java.util.Arrays;

public class ArrayRound {
    public static void main(String[] args) {
//        int [][] arr={{1,2,3},{4,5,6},{7,8,9}};
        int [][] arr={{1,2,3},{4,5,6},{7,8,9},{10,11,12}};//00-03 01-13 02-23  30-00  31-01 32-02
        int first=0;
        int two=0;
        int [][] proArr =null;
        for(int [] i: arr){
            for(int j=0;j<i.length;j++){
                proArr= new int[i.length][arr.length];//初始化旋转后数组
                if(two==0) {
                    first = arr.length;
                    two = i.length;
                }

            }
        }
        if(first>two){//不方数组旋转
            for(int i=0;i< first;i++){
                for(int j=0;j<two && i<1;j++){
                    proArr[two-1][j] = arr[i][j];//初始化旋转后数组
                }
            }
        }else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    proArr[i][j] = arr[arr[i].length - j-1][i];//初始化旋转后数组
                }
            }
        }
        for(int [] i: proArr){//打印数组
            System.out.println(Arrays.toString(i));
        }

    }
}
