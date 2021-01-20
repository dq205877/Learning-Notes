import java.util.Arrays;

public class turnArroundArray {
    public static void main(String[] args) {
        int[][] testArray= {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printArray(testArray);
        testArray=changeArray(testArray);
        printArray(testArray);
    }

    static int[][] changeArray(int[][] intArray){
        int[][] out= null;
        //检验入参
        //转动数组
        //初始化数组大小
        for(int a=0;a<intArray.length;a++){
            for(int b=0;b<intArray[a].length;b++){
                out= new int[intArray[a].length][intArray.length];
                break;
            }
        }
        //填充数据
        //向右90
//        for(int a=0;a<intArray.length;a++){
//            for(int b=0;b<intArray[a].length;b++){
//                out[b][a]=intArray[intArray.length-1-a][b];
//            }
//        }
        //向左90
        for(int a=0;a<intArray.length;a++){
            for(int b=0;b<intArray[a].length;b++){
                out[b][a]=intArray[a][intArray[a].length-1-b];
            }
        }
        return  out;
    }

    static void printArray(int[][] intArray){
        for(int a=0;a<intArray.length;a++){
            System.out.println(Arrays.toString(intArray[a]));
        }
    }
}
