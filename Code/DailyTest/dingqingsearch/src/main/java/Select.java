import java.util.Arrays;

public class Select {
    public static void main(String[] args) {
        int arrInt[] = new int[]{1,2,3,3,5,4,7,6,10};
        arrInt = sortBySelect(arrInt);
        System.out.println(Arrays.toString(arrInt));
    }

    private static int[] sortBySelect(int orginal[]){
        if(orginal == null && orginal.length == 0){
            return null;
        }else {
            System.out.println(Arrays.toString(orginal));
            for(int i = 0;i < orginal.length-1;i++){
                for(int j = 0;j < orginal.length-1;j++){//可以少一次for(int j = 1;j < orginal.length-1;j++){
                    if(orginal[i]<orginal[j]){
                        int temp = orginal[i];
                        orginal[i] = orginal[j];
                        orginal[j] = temp;
                    }
                }
            }
            return orginal;
        }

    }
}
