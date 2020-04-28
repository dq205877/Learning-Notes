# **自我介绍**

1、三个优势和亮点，没有表明自己的潜力，基本情况（方便提问）

2、面试岗位相关符合点（有相关，可干活）

3、言简意赅（为什么胜任，为什么应聘。）

4、时间控制，表现出对岗位理解及自己的职业规划

###### Mysql

存储引擎、事务、锁、索引

###### Redis

数据类型、过期策略、淘汰策略、缓存穿透、缓存雪崩、分布式锁

###### Spring

Spring IOC、Spring AOP、Spring MVC

###### 事务的特性

原子性、一致性、隔离性、持久性

###### 事务的隔离性

并发异常：第一类丢失更新、第二类丢失更新、脏读、不可重复读、幻读

隔离级别：Read Uncommitted、Read Committed、Repeatable Read、Serializable

###### Spring事务管理

声明式事务

编程式事务

###### 锁范围：

表级锁：开销小、加锁快，发生锁冲突的概率高、并发度低，不会出现死锁。

行级锁：开销大、加锁慢，发生锁冲突的概率低、并发度高，会出现死锁。

S共享锁：行级，读取一行;

X排他锁：行级，更新一行;

IS意向共享锁：表级，准备加共享锁；

IX意向排他锁：表级，准备加排他锁；

NK间隙锁：行级，使用范围条件时（WHERE），对范围内不存在的记录加锁。一是为了防止幻读，二是为了满足恢复和复制的需要。

###### 两个事务时

|      | IS   | IX   | S    | X    |
| ---- | ---- | ---- | ---- | ---- |
| IS   |      |      |      | no   |
| IX   |      |      | no   | no   |
| S    |      | no   |      | no   |
| X    | no   | no   | no   | no   |

###### 加锁

增加行级锁之前，InnoDB会自动给表加意向锁；

执行DML语句时，InnoDB会自动给数据加排他锁；

执行DQL语句时

S     SELECT... FROM..WHERE...       LOCK   IN SHARE MODE; 

X      SELECT... FROM..WHERE...            FOR UPDATE; 

NK:上述SQL采用范围条件时（WHERE），InnoDB对不存在的记录自动增加间隙锁。





悲观锁（数据库）

乐观锁（自定义）











1、spring事务注解？

 答对了Transtional
2、过滤器和拦截器的区别？

 不知道
3、分页实现？

 说真分页假分页 mysql limit 10,19 错了，limit 10,10。虽然记录了下标不是从1开始。
4、mybatis循环？

 没答上，虽然用过foreach











中山大学 python程序设计思考题

1、一本书的页码为1、2、3、……，她的中间一页被撕掉了，余下的各页码之和正好是2007,被撕掉的是第几页。

```java
public static void main(String[] args) {
    int total=0,page=1,diff=2007,cutpage=0;
    while (total<diff){
        total=page+total;
        page++;
    }
    cutpage=total-2007;
    System.out.println("total:"+total+"page:"+page +"cut page:"+cutpage);
}
```

2、一个两倍数，其十位与个位上的数字交换以后，所得的两位数比原来的小27,则满足条件的两位数共有几个。

①

```java
public static void main(String[] args) {
    int i,j,k,count=0;
    String aa,a,b,c;
    for(i=41;i<100;i++){
        for(j=10;j<100;j++){
            StringBuilder d = new StringBuilder();
            k=i-j;
            a =String.valueOf(i);
            aa =String.valueOf(j);
            b=a.substring(0,1);
            c=a.substring(1,2);
            d.append(c);
            d.append(b);
            if(k==27 && aa.equals(d.toString())){
                count++;
                System.out.println(j);
                System.out.println(count);
            }
        }
    }
}
```

②

```java
public static void main(String[] args) {
    int i,j,k,count=0;
    String aa,a,b,c;
    for(i=10;i<100;i++){
            StringBuilder d = new StringBuilder();
            a =String.valueOf(i);
            b=a.substring(0,1);
            c=a.substring(1,2);
            d.append(c);
            d.append(b);
            aa=d.toString();
            j= Integer.valueOf(aa);
            k=i-j;
            if(k==27&& j>=10){
                count++;
                System.out.println(j);
                System.out.println(count);
            }
    }
}
```

3、有四个个位数a、b、c、d，由它们组成的四位数abcd和两位数ab、cd满足(ab+cd)*(ab+cd)=abcd。满足该条件的四位数a、b、c、d共有几个。

```java
 public static void main(String[] args) {
        int a,b,c,d;
//        StringBuilder  ab = new StringBuilder();
//        StringBuilder  cd = new StringBuilder();
//        StringBuilder  abcd = new StringBuilder();
        for(a=0;a<10;a++) {
            for (b = 0; b < 10; b++) {
                for (c = 0; c < 10; c++) {
                    for (d = 0; d < 10; d++) {
                        StringBuilder ab = new StringBuilder();
                        StringBuilder cd = new StringBuilder();
                        StringBuilder abcd = new StringBuilder();
                        ab.append(String.valueOf(a));
                        ab.append(String.valueOf(b));
                        cd.append(String.valueOf(c));
                        cd.append(String.valueOf(d));
                        abcd.append(ab).append(cd);
                        Integer total = 0;
                        Integer abaddcd = 0;
                        total = Integer.valueOf(abcd.toString());
                        abaddcd = Integer.valueOf(ab.toString()) + Integer.valueOf(cd.toString());
                        if (abaddcd * abaddcd == total) {
                            System.out.println(""+total+"  "+a+b+c+d);
                        }
                        ab=null;
                        cd=null;
                        abcd=null;
                    }
                }
            }
        }
    }
```



经典算法问题 - 最大连续子数列和

1、三个循环， 遍历所有情况取最大和

```java
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
```

2、子数列和为 sum[m]-sum[n],length 为m-n;

```java
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
```

3、分而治之

按照子数列的长度，分两半，左边大取左边，右边大取右边，横跨中间点的取和，这三种情况取最大值保留！

```java
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
```

4、推导公式

```
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
```

