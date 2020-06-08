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



多线程

2020-05-27

001

**简述 synchronized 和 java.util.concurrent.locks.Lock**

**的异同？**

答：

Lock 是 Java 5 以后引入的新的 API，和关键字 synchronized 相比主要相同点：

Lock 能完成 synchronized 所实现的所有功能；主要不同点：Lock 有比synchronized 更精确的线程语义和更好的性能，而且不强制性的要求一定要获得锁。synchronized 会自动释放锁，而 Lock 一定要求程序员手工释放，并且最好在 finally 块中释放（这是释放外部资源的最好的地方）。

2020-05-28

002

**Thread 类的 sleep()方法和对象的 wait()方法都可以让线**

**程暂停执行，它们有什么区别?**

答：

sleep()方法（休眠）是线程类（Thread）的静态方法，调用此方法会让当前线程

暂停执行指定的时间，将执行机会（CPU）让给其他线程，但是对象的锁依然保

持，因此休眠时间结束后会自动恢复（线程回到就绪状态，请参考第 66 题中的线

程状态转换图）。wait()是 Object 类的方法，调用对象的 wait()方法导致当前线

程放弃对象的锁（线程暂停执行），进入对象的等待池（wait pool），只有调用

对象的 notify()方法（或 notifyAll()方法）时才能唤醒等待池中的线程进入等锁池

（lock pool），如果线程重新获得对象的锁就可以进入就绪状态。

**补充：**可能不少人对什么是进程，什么是线程还比较模糊，对于为什么需要多线

程编程也不是特别理解。简单的说：进程是具有一定独立功能的程序关于某个数

据集合上的一次运行活动，是操作系统进行资源分配和调度的一个独立单位；线

程是进程的一个实体，是 CPU 调度和分派的基本单位，是比进程更小的能独立运

行的基本单位。线程的划分尺度小于进程，这使得多线程程序的并发性高；进程

在执行时通常拥有独立的内存单元，而线程之间可以共享内存。使用多线程的编

程通常能够带来更好的性能和用户体验，但是多线程的程序对于其他程序是不友

好的，因为它可能占用了更多的 CPU 资源。当然，也不是线程越多，程序的性能

就越好，因为线程之间的调度和切换也会浪费 CPU 时间。时下很时髦的 Node.

js就采用了单线程异步 I/O 的工作模式。

2020-05-29

003

**线程的 sleep()方法和 yield()方法有什么区别？**

答：

① sleep()方法给其他线程运行机会时不考虑线程的优先级，因此会给低优先级的

线程以运行的机会；yield()方法只会给相同优先级或更高优先级的线程以运行的

机会；

② 线程执行 sleep()方法后转入阻塞（blocked）状态，而执行 yield()方法后转

入就绪（ready）状态；

③ sleep()方法声明抛出 InterruptedException，而 yield()方法没有声明任何异

常；

④ sleep()方法比 yield()方法（跟操作系统 CPU 调度相关）具有更好的可移植性。

2020-05-30

004

Java中的volatile 变量是什么？
volatile是一个特殊的修饰符，只有成员变量才能使用它。在Java并发程序缺少同步类的情况下，多线程对成员变量的操作对其它线程是透明的。volatile变量可以保证下一个读取操作会在前一个写操作之后发生，就是上一题的volatile变量规则。

2020-05-31

005

Java中什么是竞态条件？ 举个例子说明。
竞态条件会导致程序在并发情况下出现一些bugs。多线程对一些资源的竞争的时候就会产生竞态条件，如果首先要执行的程序竞争失败排到后面执行了， 那么整个程序就会出现一些不确定的bugs。这种bugs很难发现而且会重复出现，因为线程间的随机竞争。

2020-06-01

006

## 什么是不可变对象，它对写并发应用有什么帮助？

另一个多线程经典面试问题，并不直接跟线程有关，但间接帮助很多。这个java面试问题可以变的非常棘手，如果他要求你写一个不可变对象，或者问你为什么String是不可变的。

immutable Objects(不可变对象)就是那些一旦被创建，它们的状态就不能被改变的Objects，每次对他们的改变都是产生了新的immutable的对象，而mutable Objects(可变对象)就是那些创建后，状态可以被改变的Objects.

如何在Java中写出Immutable的类？ 

1. immutable对象的状态在创建之后就不能发生改变，任何对它的改变都应该产生一个新的对象。 

2. immutable类的所有的属性都应该是final的。 

3. 对象必须被正确的创建，比如：对象引用在对象创建过程中不能泄露(leak)。 

4. 对象应该是final的，以此来限制子类继承父类，以避免子类改变了父类的immutable特性。 

5. 如果类中包含mutable类对象，那么返回给客户端的时候，返回该对象的一个拷贝，而不是该对象本身（该条可以归为第一条中的一个特例）

使用Immutable类的好处： 

1. Immutable对象是线程安全的，可以不用被synchronize就在并发环境中共享 
2. Immutable对象简化了程序开发，因为它无需使用额外的锁机制就可以在线程间共享
3. Immutable对象提高了程序的性能，因为它减少了synchroinzed的使用 
4. Immutable对象是可以被重复使用的，你可以将它们缓存起来重复使用，就像字符串字面量和整型数字一样。你可以使用静态工厂方法来提供类似于valueOf（）这样的方法，它可以从缓存中返回一个已经存在的Immutable对象，而不是重新创建一个。

2020-06-02

007

**什么是线程局部变量？**

线程局部变量是局限于线程内部的变量，属于线程自身所有，不在多个线程间共

享。Java 提供 ThreadLocal 类来支持线程局部变量，是一种实现线程安全的方

式。但是在管理环境下（如 web 服务器）使用线程局部变量的时候要特别小心，

在这种情况下，工作线程的生命周期比任何应用变量的生命周期都要长。任何线

程局部变量一旦在工作完成后没有释放，Java 应用就存在内存泄露的风险。

2020-06-03

008

**Cookie与Session**

1，session 在服务器端，cookie 在客户端（浏览器）
2，session 默认被存在在服务器的一个文件里（不是内存）
3，session 的运行依赖 session id，而 session id 是存在 cookie 中的，也就是说，如果浏览器禁用了 cookie ，同时 session 也会失效（但是可以通过其它方式实现，比如在 url 中传递 session_id）
4，session 可以放在 文件、数据库、或内存中都可以。
5，用户验证这种场合一般会用 session



2020-06-04

009

**ArrayList 和 LinkedList 有什么区别。**
ArrayList和LinkedList都实现了List接口，有以下的不同点：
1、ArrayList是基于索引的数据接口，它的底层是数组。它可以以O(1)时间复杂度对元素进行随机访问。与此对应，LinkedList是以元素列表的形式存储它的数据，每一个元素都和它的前一个和后一个元素链接在一起，在这种情况下，查找某个元素的时间复杂度是O(n)。
2、相对于ArrayList，LinkedList的插入，添加，删除操作速度更快，因为当元素被添加到集合任意位置的时候，不需要像数组那样重新计算大小或者是更新索引。
3、LinkedList比ArrayList更占内存，因为LinkedList为每一个节点存储了两个引用，一个指向前一个元素，一个指向下一个元素。



2020-06-05

010

**讲讲你理解的 nio和 bio 的区别是啥，谈谈 reactor 模型。**
IO(BIO)是面向流的，NIO是面向缓冲区的
BIO：Block IO 同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低。
NIO：New IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
AIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。

2020-06-06

011

**countdowlatch 和 cyclicbarrier 的内部原理和用法，以及相互之间的差别。**
CountDownLatch是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它运行一个或者多个线程一直处于等待状态。
CyclicBarrier要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
CyclicBarrier初始化的时候，设置一个屏障数。线程调用await()方法的时候，这个线程就会被阻塞，当调用await()的线程数量到达屏障数的时候，主线程就会取消所有被阻塞线程的状态。
前者是递减，不可循环，后者是递加，可循环用
countdowlatch 基于abq cb基于ReentrantLock Condition



2020-06-07

012

Java List的remove()

1、用for循环遍历List删除元素时，需要注意索引会左移的问题。

2、List删除元素时，为避免陷阱，建议使用迭代器iterator的remove方式。
3、List删除元素时，默认按索引删除，而不是对象删除。



2020-06-08

013

讲讲 Spring 事务的传播属性。
七种传播属性。
事务传播行为
所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：
①TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
②TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
③TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
④TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
⑤TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
⑥TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
⑦TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。





2020-06-01心科面试

1、&与&&

参考答案：&运算符有两种用法：(1)按位与；(2)逻辑与。&&运算符是短路与运算。逻辑与跟短路与的差别是非常巨大的，虽然二者都要求运算符左右两端的布尔值都是true整个表达式的值才是true。&&之所以称为短路运算是因为，如果&&左边的表达式的值是false，右边的表达式会被直接短路掉，不会进行运算。很多时候我们可能都需要用&&而不是&，例如在验证用户登录时判定用户名不是null而且不是空字符串，应当写为：username != null &&!username.equals(“”)，二者的顺序不能交换，更不能用&运算符，因为第一个条件如果不成立，根本不能进行字符串的equals比较，否则会产生NullPointerException异常。注意：逻辑或运算符（|）和短路或运算符（||）的差别也是如此。

2、sql每门大于80学生姓名

https://www.cnblogs.com/hongyan5682/p/4816444.html

3、finally里有return

参考答案：

执行顺序：

​    1、执行：expression，计算该表达式，结果保存在操作数栈顶；
​    2、执行：操作数栈顶值（expression的结果）复制到局部变量区作为返回值；
​    3、执行：finally语句块中的代码；
​    4、执行：将第2步复制到局部变量区的返回值又复制回操作数栈顶；
​    5、执行：return指令，返回操作数栈顶的值；

4、Innodb和Myisam

参考答案：

1、MyISAM是非事务安全的，而InnoDB是事务安全的

2、MyISAM锁的粒度是表级的，而InnoDB支持行级锁

3、MyISAM支持全文类型索引，而InnoDB不支持全文索引

4、MyISAM相对简单，效率上要优于InnoDB，小型应用可以考虑使用MyISAM

5、MyISAM表保存成文件形式，跨平台使用更加方便

5、final、finally、finalize

参考答案：

简单区别：

- final用于声明属性，方法和类，分别表示属性不可交变，方法不可覆盖，类不可继承。
- finally是异常处理语句结构的一部分，表示总是执行。
- finalize是Object类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法，供垃圾收集时的其他资源回收，例如关闭文件等。

中等区别：

final：java中的关键字，修饰符。
A).如果一个类被声明为final，就意味着它不能再派生出新的子类，不能作为父类被继承。因此，一个类不能同时被声明为abstract抽象类的和final的类。
B).如果将变量或者方法声明为final，可以保证它们在使用中不被改变.
　　1)被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改。
　　2)被声明final的方法只能使用，不能重载。
finally：java的一种异常处理机制。

finally是对Java异常处理模型的最佳补充。finally结构使代码总会执行，而不管无异常发生。使用finally可以维护对象的内部状态，并可以清理非内存资源。特别是在关闭数据库连接这方面，如果程序员把数据库连接的close()方法放到finally中，就会大大降低程序出错的几率。

finalize：Java中的一个方法名。
Java技术使用finalize()方法在垃圾收集器将对象从内存中清除出去前，做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没被引用时对这个对象调用的。它是在Object类中定义的，因此所的类都继承了它。子类覆盖finalize()方法以整理系统资源或者执行其他清理工作。finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。



6、LinkedList、ArrayList、Vector

1.从存储数据结构分析

ArrayList：数组

Vector：数组

LinkedList：双向链表

数组：可以根据下标快速查找，所以大部分情况下，查询快。但是如果要进行增删操作的时候，会需要移动修改元素后面的所有元素，所以增删的开销比较大，数组的对增删操作的执行效率低。而采用数组作为数据存储结构的ArrayList、Vector也存在这些特性，查询速度快（可以根据下标直接取，比迭代查找更快），增删慢。

链表：增加和删除元素方便，增加或删除一个元素，仅需处理结点间的引用即可。就像人手拉手连成一排，要增加或删除某个人只要附近的两个人换一个人牵手，对已经牵好手的人没影响。无论在哪里换人耗费的资源和时间都是一样的。但是查询不方便，需要一个个对比，无法根据下标直接查找。而采用链表结构存储的LinkedList也有这些特性，增删方便，查询慢(指的是随机查询，不是顺序查询)。

2.从继承上分析

 都实现了List接口，也就是说都实现了get(int location)、remove(int location)等“根据索引值来获取、删除节点的函数”。数组结构根据下标取值很容易，LinkedList双向列表的实现也比较简单，通过计数索引值实现，从链表长度的1/2开始查找，下标大了就从表头开始找，小了就从表尾开始找。

3.从并发安全上分析

Vector：线程安全

ArrayList：非线程安全

LinkedList:非线程安全

4.数据增长分析

Vector：缺省的情况下，增长为原数组长度的一倍。说到缺省，说明他其实是可以自主设置初始化大小的。

ArrayList：自动增长原数组的50%。
原文链接：https://blog.csdn.net/sinat_36265222/java/article/details/86481715

7、跳出多重循环

1、标识变量

2、try...catch

3、break标签变量

https://jingyan.baidu.com/article/86112f13be25ea2737978796.html

8、线程执行过程。

线程的五大状态分别为：创建状态（New）、就绪状态（Runnable）、运行状态（Running）、阻塞状态（Blocked）、死亡状态（Dead）。

https://blog.csdn.net/tongxuexie/article/details/80145663

9、去List重

1、使用LinkedHashSet删除ArrayList中的重复数据（有序）

2、使用HashSet去重（无序）

3、使用java8新特性stream进行List去重

List<String> words= Arrays.asList("a","b","b","c","c","d"); words.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);

4、使用List的contains()去重

https://www.cnblogs.com/lxy0/p/12483028.html

10、split

1、

2、

3、

1、将逗号分隔的字符串转换为List

```
String str = ``"a,b,c"``; 
` `List result = Arrays.asList(str.split(``","``));
```

　　

2、将List转换为逗号分隔的字符串

（1） 利用Guava的Joiner

```
List list = ``new` `ArrayList();
``list.add(``"a"``);
``list.add(``"b"``); 
``list.add(``"c"``); 
` `String str = Joiner.on(``","``).join(list); 
```

　　

（2）利用Apache Commons的StringUtils

```
List list = ``new` `ArrayList(); 
``list.add(``"a"``);
``list.add(``"b"``);
``list.add(``"c"``); 
` `String str = StringUtils.join(list.toArray(), ``","``); 
```

https://www.cnblogs.com/itzyz/p/10844004.html

①Spring Cloud组件②JWT/Auth 2 ③Cookie、Session④Filter、Inceptor⑤Spring Boot常用启动器 ⑥Spring Data JPA

一、Spring Cloud组件

Eureka：各个服务启动时，Eureka Client都会将服务注册到Eureka Server，并且Eureka Client还可以反过来从Eureka Server拉取注册表，从而知道其他服务在哪里
Ribbon：服务间发起请求的时候，基于Ribbon做负载均衡，从一个服务的多台机器中选择一台
Feign：基于Feign的动态代理机制，根据注解和选择的机器，拼接请求URL地址，发起请求
Hystrix：发起请求是通过Hystrix的线程池来走的，不同的服务走不同的线程池，实现了不同服务调用的隔离，避免了服务雪崩的问题
Zuul：如果前端、移动端要调用后端系统，统一从Zuul网关进入，由Zuul网关转发请求给对应的服务

https://blog.csdn.net/xunjiushi9717/article/details/91988479

二、JWT/Auth 2

三、Cookie、Session
链接：https://www.zhihu.com/question/19786827/answer/28752144

1. 由于HTTP协议是无状态的协议，所以服务端需要记录用户的状态时，就需要用某种机制来识具体的用户，这个机制就是Session.典型的场景比如购物车，当你点击下单按钮时，由于HTTP协议无状态，所以并不知道是哪个用户操作的，所以服务端要为特定的用户创建了特定的Session，用用于标识这个用户，并且跟踪用户，这样才知道购物车里面有几本书。这个Session是保存在服务端的，有一个唯一标识。在服务端保存Session的方法很多，内存、数据库、文件都有。集群的时候也要考虑Session的转移，在大型的网站，一般会有专门的Session服务器集群，用来保存用户会话，这个时候 Session 信息都是放在内存的，使用一些缓存服务比如Memcached之类的来放 Session。
2. 思考一下服务端如何识别特定的客户？这个时候Cookie就登场了。每次HTTP请求的时候，客户端都会发送相应的Cookie信息到服务端。实际上大多数的应用都是用 Cookie 来实现Session跟踪的，第一次创建Session的时候，服务端会在HTTP协议中告诉客户端，需要在 Cookie 里面记录一个Session ID，以后每次请求把这个会话ID发送到服务器，我就知道你是谁了。有人问，如果客户端的浏览器禁用了 Cookie 怎么办？一般这种情况下，会使用一种叫做URL重写的技术来进行会话跟踪，即每次HTTP交互，URL后面都会被附加上一个诸如 sid=xxxxx 这样的参数，服务端据此来识别用户。
   \3. Cookie其实还可以用在一些方便用户的场景下，设想你某次登陆过一个网站，下次登录的时候不想再次输入账号了，怎么办？这个信息可以写到Cookie里面，访问网站的时候，网站页面的脚本可以读取这个信息，就自动帮你把用户名给填了，能够方便一下用户。这也是Cookie名称的由来，给用户的一点甜头。
   所以，总结一下：
   Session是在服务端保存的一个数据结构，用来跟踪用户的状态，这个数据可以保存在集群、数据库、文件中；
   Cookie是客户端保存用户信息的一种机制，用来记录用户的一些信息，也是实现Session的一种方式。

https://www.zhihu.com/question/19786827

四、Filter、Inceptor

### Filter和Interceptor的区别

- Filter是基于函数回调的，而Interceptor则是基于Java反射的。
- Filter依赖于Servlet容器，而Interceptor不依赖于Servlet容器。
- Filter对几乎所有的请求起作用，而Interceptor只能对action请求起作用。
- Interceptor可以访问Action的上下文，值栈里的对象，而Filter不能。
- 在action的生命周期里，Interceptor可以被多次调用，而Filter只能在容器初始化时调用一次。

https://blog.csdn.net/testcs_dn/article/details/80279578

五、Spring Boot常用启动器

https://blog.csdn.net/qq_41566980/article/details/84869235

六、Spring Data JPA

https://www.jianshu.com/p/c23c82a8fcfc

**20200601面试总结**：其实每个问题都答出一部分来了，不够细致，不够扎实。可能给offer可能性 80%



2020-06-03

在上海面试，给我感觉安全很重要，JWT/Auth 2/Serculty/加解密等等