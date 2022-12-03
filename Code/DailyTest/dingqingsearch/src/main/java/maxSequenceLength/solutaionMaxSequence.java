package maxSequenceLength;

/*贪心算法
* 1.建立数学模型来描述问题；
2.把求解的问题分成若干个子问题；
3.对每一子问题求解，得到子问题的局部最优解；
4.把子问题的局部最优解合成原来问题的一个解。
* */
public class solutaionMaxSequence {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,3,5,2,3,5,5,1,3,5,7,9,2,3}));
    }

    private static int solution(int[] arr){
        int start = 0;
        int max = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] <= arr[i-1]){
                start = i;
            }
            max = Math.max(max,i- start+1);
        }
        return max;
    }
}
