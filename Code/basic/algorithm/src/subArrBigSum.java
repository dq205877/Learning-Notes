public class subArrBigSum {
    public static void main(String[] args) {
        int[] arr = {1,-3,2,4,-1,2,-3,2};
        int biggest = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=1;j<arr.length;j++){
                int sum = 0;
                for(int k =i;k<=j;k++){
                    sum = sum+ arr[k];
                }
                if(sum>biggest){
                    biggest=sum;
                }
            }
        }
        System.out.println(biggest);
    }
}
