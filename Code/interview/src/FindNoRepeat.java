/**
 * 2n+1长数组，其中有一个不是一对的数值，打印出该数值。
 */
public class FindNoRepeat {
    public static void main(String[] args) {
        int [] arr = {1,1,2,2,3};
        int flag = 0;
        for(int i:arr){
            for(int j: arr){
                if(i==j){
                    flag++;
                }
            }
            if(flag==1){//只有一次出现
                System.out.println(i);
            }
            flag=0;//重置
        }

        int [] arrmytest = {1,1,2,2,3};
        int flagmytest = 2;
        for(int i:arrmytest){
            for(int j: arrmytest){
                if(i==j){
                    flagmytest--;
                }
            }
            if(flagmytest==1){//只有一次出现
                System.out.println(i);
            }
            flagmytest=2;//重置
        }
    }
}
