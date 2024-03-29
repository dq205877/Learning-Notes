人际交往+技术本质+业务深度+行业应用天花板＝年薪
追踪到C，到数据库，到OS。

架构设计＝操作系统+java core+框架+计算机网络

框架＝注解+反射+设计模式

算法：解决方案：

特性  输入：输出：有穷性、确定性、可行性

基本要求：正确性、可读性、健壮性、时间复杂度、空间复杂度

数据结构：

线性结构：数组、栈、队列、单链表、循环链表、双链表、递归（斐波那契（前两项和是第三个数值）、汉诺塔）、排序算法

数组：①索引 下标从0开始  码成一排存放

 		   有语义更好，快速查询，

​			没语义 如何表示无元素？添加元素？删除元素？

​			tips:熟悉数据结构可以尝试着写一个包装类并实现增删改查。

​			②局限性 必须初始化大小及实例化（含默认实例化）（动态：判断null，用新数组替换原数组（变大或变小））。

栈（mini版数组包装）：撤销操作（压进去，拿出来）先进后出。

​		递归及OS调度（压栈底）

队列：

链表：

递归：

二分搜索树：

前（访问即取），中（顺序，二次取），后（三次）序遍历（深度） 层序遍历（广度）



集合和映射：

优先队列和堆：

线段树：

Trie：

并查集：

AVL：

链表：单链（注意上节点和下节点）、循环链（单向/无根节点，圆）、双向循环链（圆环，双向）

斐波那契

```java
// 1   1   2   3    5   8
public static int fb(int positionNum){
	if(positionNum<1){
		return 0;//入参不合适
	}
    if(positionNum=1 | positionNum=2){
     return 1;
    }else{
		return fb(positionNum-1)+fb(positionNum-2);
    }
}
```

汉诺塔

树结构：二叉树、红黑树、Hash表、B-Tree

二叉树：树深度太长；

红黑树:

Hash表:

B-Tree:

- 叶节点具有相同的深度，叶节点的指针为空

- 所有索引元素不重复

- 节点中的数据索引从左到右递增排列

B+Tree:

- 非叶子节点不存储data，只存储索引（冗余），可以放更多的索引
- 叶子节点包含所有索引字段
- 叶子节点用指针连接，提高区间访问的性能



CPU缓存：缓存一致性

MESI（Modified/Exclusive/Shared/Invalid)修改/独享/共享/无效



|      | M    | E    | S    | I    |
| ---- | ---- | ---- | ---- | ---- |
| M    | 1    | 1    | 1    | 0    |
| E    | 1    | 1    | 1    | 0    |
| S    | 1    | 1    | 0    | 0    |
| I    | 0    | 0    | 0    | 0    |

编译时多态：重载（方法名相同，参数不同）因有时程序不考虑返回值故设计之

运行时多态：重写（子类可加final)抛小修饰大　需向上再向下

JMM Unlock/Lock 主内存  Read Load 工作内存  Use  线程内存 Assign  工作内存 Store Write 主内存

![image-20210530171450920](D:\Git-Center\Learning-Notes\source\static\images\同步八操作.png)

- lock：作用于主内存的变量，把一个变量标识为一条线程独占状态。
- unlock：作用于主内存的变量，释放一个变量的锁定状态。
- read：作用于主内存的变量，把一个变量的值从主内存传输到工作内存中。
- load：作用于工作内存的变量，在read之后执行，把read得到的值放入工作内存的变量副本中。
- use：作用于工作内存的变量，把工作内存中一个变量的值传递给执行引擎。
- assign：作用于工作内存的变量，把一个从执行引擎接收到的值赋给工作内存的变量。
- store：作用于工作内存的变量，把工作内存的一个变量的值传送到主内存中。
- write：作用于主内存的变量，在 store 之后执行，把 store 得到的值放入主内存的变量中。



synchornized JMM规定 解锁前 共享变量最新值刷到主内存  加锁时，清空工作内存中共享变量值，即让共享变量在主内存重新读取最新的值。

volatile 内存屏障和禁止重排序  写 store屏障指令  读 load屏障指令


![JVM memory](D:\Git-Center\Learning-Notes\source\static\images\JAVA-memory.png)

![JVM memory](https://github.com/dq205877/Learning-Notes/blob/master/source/static/images/JAVA-memory.png)

方法区/堆   虚拟机栈/本地方法栈/程序计数器

半初始化

源码：

```java
class T{

int m =8;

}

T t = new T();
```

汇编码：

```
0  new #2<T>

3 dup

4 invokespecial #3<T.<init>>

7 astore_1

8 return
```

申请内存

dup复制一份  因为invokespecial消耗引用

构造方法

指向

返回

0时m=0：半初始化

DCL（double check lock)  

###### volatitle(1、可见性； 2、禁止指令重排序)

上两个一起使用，可避免半初始化。

###### 对象定位（1、句柄（间接） 2、直接指针）

new object()          markword  /class pointer/   instance data/ padding

new int[4]             markword  /class pointer/  length / instance data/ padding

栈上分配（没有逃逸(线程内使用)，没有其他对象和方法引用）对象直接在栈上分配。

线程本地分配-Eden-ServivorFrom-markword(age)-SurvirorTo-Permanant

JVM memory:最大最小内存一致，避免系统资源浪费

-XX:+UseCompaperssedClassPointers 压缩指针

4字节寻址：可能是32G，48G可能不压缩了，直接膨胀了。所以越大不一定更好（没有压缩效果了）



###### 如何确定垃圾可回收

引用计数法 

在 Java 中，引用和对象是有关联的。如果要操作对象则必须用引用进行。因此，很显然一个简单 的办法是通过引用计数来判断一个对象是否可以回收。简单说，即一个对象如果没有任何与之关 联的引用，即他们的引用计数都不为 0，则说明对象不太可能再被用到，那么这个对象就是可回收 对象。 

可达性分析 

为了解决引用计数法的循环引用问题，Java 使用了可达性分析的方法。通过一系列的“GC roots” 对象作为起点搜索。如果在“GC roots”和一个对象之间没有可达路径，则称该对象是不可达的。 13/04/2018 Page 27 of 283 要注意的是，不可达对象不等价于可回收对象，不可达对象变为可回收对象至少要经过两次标记 过程。两次标记后仍然是可回收对象，则将面临回收。



###### JAVA 四种引用类型 

1. 强引用

强引用 在 Java 中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是一个强引 用。当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾回收机制回收的，即 使该对象以后永远都不会被用到 JVM 也不会回收。因此强引用是造成 Java 内存泄漏的主要原因之 一。 

2. 软引用

 软引用需要用 SoftReference 类来实现，对于只有软引用的对象来说，当系统内存足够时它 不会被回收，当系统内存空间不足时它会被回收。软引用通常用在对内存敏感的程序中。

​     3.弱引用 

弱引用需要用 WeakReference 类来实现，它比软引用的生存期更短，对于只有弱引用的对象 来说，只要垃圾回收机制一运行，不管 JVM 的内存空间是否足够，总会回收该对象占用的内存。 

4. 虚引用 

虚引用需要 PhantomReference 类来实现，它不能单独使用，必须和引用队列联合使用。虚 引用的主要作用是跟踪对象被垃圾回收的状态。

类初始化执行顺序：父静变父静块子静变子静块父非静变父非静块父构子非静变子非静块子构

###### 垃圾回收算法

标记清除+复制＝标记整理     碎片化+浪费可用（分两半）＝时间问题？

单线程 多线程

分代  分区          专代专用  非整个堆减小停顿

###### IO

IO 是面向流的，NIO 是面向缓冲区的。



分类:字节流,字符流,

扩展流.





NIO 主要有三大核心部分：Channel(通道)，Buffer(缓冲区), Selector。

NIO 中的 Channel 的主要实现有： 1. FileChannel 2. DatagramChannel 3. SocketChannel 4. ServerSocketChannel



## BIO、NIO 有什么区别？

- 线程模型不同
  - BIO：一个连接一个线程，客户端有连接请求时服务器端就需要启动一个线程进行处理。所以，线程开销大。可改良为用线程池的方式代替新创建线程，被称为伪异步 IO 。
  - NIO：一个请求一个线程，但客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有新的 I/O 请求时，才启动一个线程进行处理。可改良为一个线程处理多个请求，基于 [多 Reactor 模型](http://svip.iocoder.cn/Netty/EventLoop-1-Reactor-Model/)。
- BIO 是面向流( Stream )的，而 NIO 是面向缓冲区( Buffer )的。
- BIO 的各种操作是阻塞的，而 NIO 的各种操作是非阻塞的。
- BIO 的 Socket 是单向的，而 NIO 的 Channel 是双向的。

|           | BIO  | NIO  | AIO  |
| --------- | ---- | ---- | ---- |
| 是否阻塞  | 是   | 否   | 否   |
| 同步/异步 | 同   | 同   | 异   |
| 性能      | 低   | 一般 | 高   |
| 线程比    | 1:1  | N：1 | N：0 |
| 吞吐量    | 低   | 高   | 高   |



###### JVM 类加载机制 

JVM 类加载机制分为五个部分：加载，验证，准备，解析，初始化

某个静态Field使用了final修饰，且编译时能确定值，其他地方使用该静态Field时，实际没有调用静态Field，相当于使用常量，也就是只调用这种静态Field时，不初始化对象

```java
package testStaticBlock;

public class SuperStatic {
	static {
		System.out.println("Super Static block");
	}
	
	public static final String HelloWorld="Hello,World!";
	public static int value=123;
	
}



package testStaticBlock;

public class SubStatic extends SuperStatic{
	
	static {
		System.out.println("Sub Static block");
	}

}


package testStaticBlock;

public class NotInitialization {
	
	public static void main(String[] args) {
		//只有直接定义静态字段的类才会初始化,虚拟机规范未明确规定,具体取决于虚拟机实现
//		System.out.println(SubStatic.value);
		SuperStatic [] objectArr = new SuperStatic[5];//[SuperStatic封装了数组方法
		System.out.println("");
		System.out.println(SuperStatic.HelloWorld);//编译成class已经将helloworld转化成自身的常量,无常量类引用
	}
}


```





###### 集合

集合类存放于 Java.util 包中，主要有 3 种：set(集）、list(列表包含 Queue）和 map(映射)。

1. Collection：Collection 是集合 List、Set、Queue 的最基本的接口。 
2. Iterator：迭代器，可以通过迭代器遍历集合中的数据 
3. Map：是映射表的基础接口



###### JUC

死锁四个必要条件：

1、资源独享

2、占用等待

3、不剥夺

4、循环等待链



乐观锁

悲观锁



公平锁

非公平锁



偏向锁

自旋锁

轻量锁

重量锁

锁是否能带来性能提升：根据业务判断，锁本身是一个浪费资源的表现，因为线程竞争导致出现这些具体实现。

###### Synchronized 

非null对象独占式悲观锁，可重入。

偏向锁默认打开。

###### 无锁－偏向－自旋（E(读取)，E(期望)，A(实际)）【轻量默认10次】－重量－（C++monitor对象）

偏向monitorcenter monitorexit

自旋  LR(Lock Record)引用

C++monitor对象引用

线程

线程相关的基本方法有 wait，notify，notifyAll，sleep，join，yield 等。

![image-20200418001343249](D:\Git-Center\Learning-Notes\source\static\images\Thread.png)

wait  WATING状态  sleep  TIMED-WATING状态 显然，sleep是定时等待，不释放锁。

yield让步  优先级高的可能性大，不绝对，OS不一定对线程优先级敏感。

interrupt中断  给信号，会改变标志位，不改状态。

1. 调用 interrupt()方法并不会中断一个正在运行的线程。也就是说处于 Running 状态的线 程并不会因为被中断而被终止，仅仅改变了内部维护的中断标识位而已。 
2. 若调用 sleep()而使线程处于 TIMED-WATING 状态，这时调用 interrupt()方法，会抛出 InterruptedException,从而使线程提前结束 TIMED-WATING 状态。 
3. 许多声明抛出 InterruptedException 的方法(如 Thread.sleep(long mills 方法))，抛出异 常前，都会**清除**中断标识位，所以抛出异常后，调用 isInterrupted()方法将会返回 **false**。
4. 中断状态是线程固有的一个标识位，可以通过此标识位安全的终止线程。比如,你想终止 一个线程 thread 的时候，可以调用 thread.interrupt()方法，在线程的 run 方法**内部**可以 根据 thread.isInterrupted()的值来**优雅**的终止线程。

join 等待其他线程终止，当前线程调用则阻塞，另一线程结束，当前线程就绪。（应用：主线程要结束，调join(),会等子线程结束后结束。）

notify 唤醒对象监视器中其中一个幸运线程（死锁警告）；notifyAll();所有



其他方法： 1. sleep()：强迫一个线程睡眠Ｎ毫秒。 2. isAlive()： 判断一个线程是否存活。 3. join()： 等待线程终止。 4. activeCount()： 程序中活跃的线程数。 5. enumerate()： 枚举程序中的线程。 6. currentThread()： 得到当前线程。 7. isDaemon()： 一个线程是否为守护线程。 8. setDaemon()： 设置一个线程为守护线程。(用户线程和守护线程的区别在于，是否等待主线 程依赖于主线程结束而结束) 9. setName()： 为线程设置一个名称。 10. wait()： 强迫一个线程等待。 13/04/2018 Page 75 of 283 11. notify()： 通知一个线程继续运行。 12. setPriority()： 设置一个线程的优先级。 13. getPriority():：获得一个线程的优先级。

任务的状态保存**及**再加载, 这段过程就叫做 **上下文切换**。



线程池**主要**特点：**线程复用**；**控制最大并发数；管理线程**

我们可以继承重写 Thread 类，在其 start 方法中添加不断循环调用传递过来的 Runnable 对象。 这就是线程池的**实现原理**。循环方法中不断获取 Runnable 是用 Queue 实现的，在获取下一个 Runnable 之前**可以**是阻塞的。（**线程复用**）

线程池的组成 

一般的线程池主要分为以下 4 个组成部分： 

1. 线程池管理器：用于创建并管理线程池 
2. 工作线程：线程池中的线程 
3.  任务接口：每个任务必须实现的接口，用于工作线程调度其运行 
4. 任务队列：用于存放待处理的任务，提供一种缓冲机制 Java 中的线程池是通过 Executor 框架实现的，该框架中用到了 Executor，Executors， ExecutorService，ThreadPoolExecutor ，Callable 和 Future、FutureTask 这几个类。

ThreadPoolExecutor 的构造方法如下： 

```java
public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue workQueue) { 

this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler); 

}
```

1. corePoolSize：指定了线程池中的线程数量。 
2.  maximumPoolSize：指定了线程池中的最大线程数量。 
3. keepAliveTime：当前线程池数量超过 corePoolSize 时，多余的空闲线程的存活时间，即多 次时间内会被销毁。 
4. unit：keepAliveTime 的单位。 
5. workQueue：任务队列，被提交但尚未被执行的任务。
6. threadFactory：线程工厂，用于创建线程，一般用默认的即可。
7. handler：拒绝策略，当任务太多来不及处理，如何拒绝任务。

四种线程池

Executor 顶级接口 执行工具

ExecutorService  线程池真正接口

newCachedThreadPool

newFixedThreadPool

newScheduledThreadPool

newSingleThreadExecutor



并发三大部分

**Synchronizer（同步器）/同步容器/ThreadPool、executor**



JUC class

LockSupport

public static void park(Object blocker); // 暂停当前线程
public static void parkNanos(Object blocker, long nanos); // 暂停当前线程，不过有超时时间的限制
public static void parkUntil(Object blocker, long deadline); // 暂停当前线程，直到某个时间
public static void park(); // 无期限暂停当前线程
public static void parkNanos(long nanos); // 暂停当前线程，不过有超时时间的限制
public static void parkUntil(long deadline); // 暂停当前线程，直到某个时间
public static void unpark(Thread thread); // 恢复其他线程
public static Object getBlocker(Thread t);

park和unpark可以实现类似wait和notify的功能，但是并不和wait和notify交叉，也就是说unpark不会对wait起作用，notify也不会对park起作用。
park和unpark的使用不会出现死锁的情况(但要合理运用，避免产生逻辑死锁)
blocker的作用是在dump线程的时候看到阻塞对象的信息



```
									Spring
```

###### Spring Ioc

Invension of Control

​	-控制反转，是一种面向对象编程的设计思想。

Dependency Injection

​	-依赖注入，是IoC思想的实现方式。

IoC Container

​	-IoC窗口，是实现依赖注入的关键，本质上是一个工厂。

```
// TransactionDefinition.java

// ========== 支持当前事务的情况 ========== 

/**
 * 如果当前存在事务，则使用该事务。
 * 如果当前没有事务，则创建一个新的事务。
 */
int PROPAGATION_REQUIRED = 0;
/**
 * 如果当前存在事务，则使用该事务。
 * 如果当前没有事务，则以非事务的方式继续运行。
 */
int PROPAGATION_SUPPORTS = 1;
/**
 * 如果当前存在事务，则使用该事务。
 * 如果当前没有事务，则抛出异常。
 */
int PROPAGATION_MANDATORY = 2;

// ========== 不支持当前事务的情况 ========== 

/**
 * 创建一个新的事务。
 * 如果当前存在事务，则把当前事务挂起。
 */
int PROPAGATION_REQUIRES_NEW = 3;
/**
 * 以非事务方式运行。
 * 如果当前存在事务，则把当前事务挂起。
 */
int PROPAGATION_NOT_SUPPORTED = 4;
/**
 * 以非事务方式运行。
 * 如果当前存在事务，则抛出异常。
 */
int PROPAGATION_NEVER = 5;

// ========== 其他情况 ========== 

/**
 * 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行。
 * 如果当前没有事务，则等价于 {@link TransactionDefinition#PROPAGATION_REQUIRED}
 */
int PROPAGATION_NESTED = 6;
```



五大体系

Resource体系

InputStreamSource

ResourceLoader

BeanFactory体系

BeanDefinition体系

BeanDefinitionReader体系

ApplicationContext体系





Spring如何解决循环依赖？

| 缓存                  | 说明                                                         |
| --------------------- | ------------------------------------------------------------ |
| singletonObjects      | 第一级缓存，存放可用的`成品Bean`。                           |
| earlySingletonObjects | 第二级缓存，存放`半成品的Bean`，`半成品的Bean`是已创建对象，但是未注入属性和初始化。用以解决循环依赖。 |
| singletonFactories    | 第三级缓存，存的是`Bean工厂对象`，用来生成`半成品的Bean`并放入到二级缓存中。用以解决循环依赖。 |

三级缓存 `singletonFactories` 

 二级缓存 `earlySingletonObjects`

一级缓存singletonObjects

```java
// DefaultSingletonBeanRegistry.java

protected void addSingleton(String beanName, Object singletonObject) {
	synchronized (this.singletonObjects) {
		this.singletonObjects.put(beanName, singletonObject);
		this.singletonFactories.remove(beanName);//三级移除
		this.earlySingletonObjects.remove(beanName);//二级移除
		this.registeredSingletons.add(beanName);
	}
}


// AbstractAutowireCapableBeanFactory.java

boolean earlySingletonExposure = (mbd.isSingleton() // 单例模式
        && this.allowCircularReferences // 运行循环依赖
        && isSingletonCurrentlyInCreation(beanName)); // 当前单例 bean 是否正在被创建
if (earlySingletonExposure) {
    if (logger.isTraceEnabled()) {
        logger.trace("Eagerly caching bean '" + beanName +
                "' to allow for resolving potential circular references");
    }
    // 提前将创建的 bean 实例加入到 singletonFactories 中
    // <X> 这里是为了后期避免循环依赖
    addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
}
```

**如果要使用`二级缓存`解决`循环依赖`，意味着Bean在`构造`完后就创建`代理对象`，这样违背了`Spring设计原则`。Spring结合AOP跟Bean的生命周期，是在`Bean创建完全`之后通过`AnnotationAwareAspectJAutoProxyCreator`这个后置处理器来完成的，在这个后置处理的`postProcessAfterInitialization`方法中对初始化后的Bean完成AOP代理。如果出现了`循环依赖`，那没有办法，只有给Bean先创建代理，但是没有出现循环依赖的情况下，设计之初就是让Bean在生命周期的最后一步完成代理而不是在实例化后就立马完成代理。**

###### Spring AOP

定义

面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

优点

利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

原理

aop底层将采用代理机制进行实现。
作用
日志：方法执行时间，追踪问题
事务：两个方法不相关但业务需要是在同一事务中，回滚一套业务。比如：下单后商品没有抢到，则需要将商品和下单两块回滚。

接口 + 实现类：spring 默认采用 jdk 的动态代理 Proxy。

实现类：spring 默认采用 cglib 字节码增强。
ConcureentHashMap存储IOC对象，Class>byte Array(not IO a class)


放到IOC的时候已经是代理对象了。即初始化IOC时。


target：目标类，需要被代理的类。例如：UserService

Joinpoint：连接点，是指那些可能被拦截到的方法。例如：所有的方法

PointCut：切入点，已经被增强的连接点。例如：addUser()

advice： 通知/增强，增强代码。例如：after、before

Weaving：织入，是指把 advice 应用到 target 来创建代理对象的过程。

proxy： 代理类

Aspect: 切面，是切入点 pointcut 和通知 advice 的结合
 一个线是一个特殊的面。
 一个切入点和一个通知，组成成一个特殊的面。



org.aopalliance.aop.Advice

根据目标类方法的连接点位置划分：

前置 MethodBeforeAdvice

后置 AfterReturningAdvice

环绕 MethodInterceptor

异常 ThrowsAdvice

引介 IntroductionInterceptor  添加新行为和属性







CGLIB/JDK动态代理





-----------------------------------------------------------------------------------------------------------------------

spring AOP四种实现方式
配置可以通过xml文件来进行，大概有四种方式：

1.        配置ProxyFactoryBean，显式地设置advisors, advice, target等（基于代理的AOP ）

2.        配置AutoProxyCreator，这种方式下，还是如以前一样使用定义的bean，但是从容器中获得的其实已经是代理对象

3.        通过<aop:config>来配置（纯POJO切面）

4.        通过<aop: aspectj-autoproxy>来配置，使用AspectJ的注解来标识通知及切入点
原文链接：https://blog.csdn.net/liuhaiabc/article/details/52597204

--------------------------------------------------------------------------------------------------------------------------------------------





### AspectJ

#### 介绍

- AspectJ 是一个基于 Java 语言的 AOP 框架
- Spring2.0 以后新增了对 AspectJ 切点表达式支持
- `@AspectJ` 是 AspectJ1.5 新增功能，通过 JDK5 注解技术，允许直接在 Bean 类中定义切面
   新版本 Spring 框架，建议使用 AspectJ 方式来开发 AOP
- 主要用途：自定义开发

**before: 前置通知(校验)**
 在方法执行前执行，如果通知抛出异常，阻止方法运行

**afterReturning: 后置通知(数据处理)**
 方法正常返回后执行，如果方法中抛出异常，通知无法执行
 必须在方法执行后才执行，所以可以获得方法的返回值。

**around: 环绕通知(十分强大，可以做任何事情)**
 方法执行前后分别执行，可以阻止方法的执行
 必须手动执行目标方法

**afterThrowing: 抛出异常通知(包装异常信息)**
 方法抛出异常后执行，如果方法没有抛出异常，无法执行

**after: 最终通知(清理现场)**
 方法执行完毕后执行，无论方法中是否出现异常



###### Spring MVC

										Spring Boot

1、微服务：一个项目 可以由多个 小型服务构成（微服务）
2、Spring boot可以快速开发 微服务模块
	a.简化j2ee开发
	b.整个Spring技术栈的整合（整合springmvc spring）
	c.整个j2ee技术的整合（整合mybatis redis）
准备：jdk maven/gradle
spring boot 开发工具：
	Eclipse（STS插件） 或STS
	IntelliJ IDEA
目录结构 resources:
	static:静态资源（js css 图片 音频 视频）
	templates:模板文件（模版引擎freemarker,thymeleaf;默认不支持JSP）
	application.properties:配置文件
Spring boot 四大核心
自动配置、起步依赖、命令行界面、Actuator
@SpringBootApplication等同于下面三个注解组合(Spring Boot 1.2.0)
Spring的@Configuration：
Spring的@ComponentScan：
Spring Boot 的 @EnableAutoConfiguration ：或@Abracadabra


配置文件的位置
	1、项目内部的配置文件：
	properties和yml中的配置，相互补充；如果冲突，则properties优先级高。
	spring boot默认能够读取的application.properties/application.yml，这2个文件 可以存在于以下几下目录
	file:项目根目录/config 
	file:项目根目录
	classpath:项目根目录/config
	classpath:项目根目录
	注意
	a.如果某项配置冲突，则优先级从上往下
	b.如果不冲突，则互补结合使用
	 配置项目名：
	properties文件中
	server.servlet.context-path=/boot  即IP后根路径前追加boot.即项目名
	2、项目外部的配置文件：（补救）
	在项目Run configuration,arguments:
	--spring.config.location=D:/application.properties
	如果 同一个配置 同时存在于 内部配置文件和外部配置文件，则外部的优先级高
	外部配置文件 通过命令行调用 java -jar project.jar --spring.config.location=D:/application.properties
	3、项目运行参数：
	参数补救与2一样，--server.port --...
	命令参数(外部文件>运行参数)大于内部（properties>yml）

静态资源默认识别路径"classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/" 
网址LOG：favicon.ico
欢迎页：index.html	
上面两个放在任意识别路径

springboot jsp整合
要外置tomcat  war包
1、新建boot项目，war
2、mvn level :provide tomcat打包不会打tomcat
3目录
webapps/WEB-INF（需要）
webapps/WEB-INF/web.xml(不需要）
webapps/index.jsp
4、创建tomcat实例，部署项目。
会先启动tomcat再启动springboot







```
                                     Spring Cloud
```

springcloud
1、Eureka(AP) *		注册中心
     Consul(CP)
     Zookeeper(CP)
     Nacos
2、ribbon+RestTemplate	服务调用
     Feign *
     OpenFeign
3、Hystrix *		服务降级
     resilence4j
     sentinel 
4、Zuul *			服务网关
     Zuul2 !
     gateway
5、Config *		服务配置
     Nacos
6、Bus *			服务总线
     Nacos
Nacos：注册中心/服务配置/服务总线
Eureka
1、互相注册，互相守望
ribbon
com.netflix.loadbalancer.RoundRobinRule轮询
com.netfilx.loadbalancer.RandomRule随机
com.netfilx.loadbalancer.RetryRule先按照轮询，在指定时间内重试
WeightedResponseTimeRule扩展轮询，响应速度越快的实例选择权重越大，越容易被选择
BestAvailableRule 先过滤由于多次访问故障而处于断路器跳闸状态的服务，再选并发量最小的服务
AvailabilityFilteringRule先过滤故障实例，再选并发小的服务
ZoneAvoidanceRule默认规则，复合判断服务所在区域的性能和服务的可用选择服务

IRule
ILoadBanlance
默认轮询（服务List.get(次数%服务总可用数),重启归零）

重写：接口要在Scan包外，重写在Scan包内
RestTemplate getObject getEntity(更全，有HEAD，STATUS什么的）
OpenFegin
1、ribbon+RestTemplate
2、客户端(Controller调Service)=服务端Service+@FeginClient，类似@Mapper
3、超时默认1秒，Ribbon>timeout+
Hystrix		服务熔断
服务降级(友好提示,扇形链路)
服务熔断（保险丝 先降级 再熔断，再恢复）
服务限流（确保稳定）
#@EnableCircuitBreaker 断路器
#@EnableHystrixDashboard  服务监控
@HystrixCommand(
fallbackMethod="defineMethod",commandProperties = {
@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="300")
}
)
@DefaultProperties(defaultFallback = "")//放类上面，全局
或在@FeignClient( value="service", fallback = fallback.class)
fallback implements service.

@HystrixCommand(
fallbackMethod="defineMethod",commandProperties = {
@HystrixProperty(name="circuitBreaker.enabled",value="true"),//是否开启断路器
@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="300"),//请求次数
@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="30000"),//时间窗口期
@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//失败率达到跳闸
}
)
默认10秒内20次以上50%会熔断，默认5秒后尝试

要监控要在Breaker启动类添加一个Bean
@Bean
public ServletRegistrationBean getServlet(){
HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
registrationBean.setLoadOnStartup(1);
registrationBean.addUrlMappings("/hystrix.stream");
registrationBean.setName("HystrixMetricxStreamServlet");
return registrationBean;
}

Gateway		服务网关
build on  Spring 5.0+Spring Boot 2.x+Spring WebFlux+Project Reactor.
核心概念：Route(路由),Predicate(断言),Filter(过滤)
自定义：实现GlobalFilter,Ordered

Config		服务配置

Bus		消息总线
全局广播
动态全局刷新
动态定点刷新

Stream		消息驱动
消除两种消息队列的鸿沟

Sleuth收集	分布式链路追踪
zipkin展现 默认端口9411


SpringCloudAlibaba（Sentinel+Nacos+RocketMQ+Dubbo+Seata+Alibaba Cloud OSS+Alibaba Cloud SchedulerX+Alibaba Cloud SMS）
SpringCloudNetflix进入维护  H版（替换）

Nacos  注册中心+配置中心+服务总线

Sentinel		服务熔器

Seata		分布式事务



```
											Redis
```

Windows

redis-server.exe redis.windows.conf

redis-cli -h 127.0.0.1 -p 6379







​    redis-server --service-install redis.windows.conf //安装服务
​    redis-server --service-start //启动服务
​    redis-server --service-stop //停止服务
​    redis-server --service-uninstall //卸载服务





\1. redis-cli.exe
\2. shutdown
\3. exit
\4. redis-server.exe redis.windows.conf



Redis是一款基于键值对的NoSQL数据库，它的值支持多种数据结构：

字符串（strings)、哈希（hashes）、列表（lists）、集合（sets）、有序集合（sorted sets）等。

Redis将所有的数据都存放在内在中，所以它的读写性能十分惊人。

同时，Redis还可以将内在中的数据以快照或日志的形式保存到硬盘上，以保证数据的安全性。

Redis典型的应用场景包括：缓存、排行榜、计数器、社交网络、消息队列等。

常见命令：

key *     查看所有的

exists key 判断是否存在

move key db 当前库没了，被移除

expire key seconds 设置过期时间

ttl key  查看过期时间 －1永不过期 -2已过期

type key 查看类型

string

set/get/del/append/strlen

incr/decr/incrby decrby 加 减法

getrange setrange   拼接替换

setex( set with expire)键秒值/setnx(set if not exist) 

mset/mget/msetnx 如果不存在插入（与）

### 											JSR－107

#### Application

#### CachingProvider 

#### CacheManager

#### Cache

#### Entry<K,V>         Expiry



Cache

缓存接口，定义缓存操作。实现有：RedisCache、EhCacheCache、ConcurrentMapCache等

CacheManager

缓存管理器，管理各种缓存（Cache）组件

@Cacheable

主要针对方法配置，能够根据方法的请求参数对其结果进行缓存

@CacheEvict

清空缓存

@CachePut

保证方法被调用，又希望结果被缓存（更新缓存）

@EnableCaching

开启基于注解的缓存

keyGenerator

缓存数据时key生成策略

serlialize

缓存数据时value序列化策略





网络  七层

应用层/表示层/会话层/网络层/传输层/数据链路层/物理层

TCP/IP 应用层/传输层/网络层/链路层

TCP  三次握手，四次挥手  

喂/我听到了/我也听到了。

喂，我下班了/我等下也下班了/我下班了/好的





POI/EasyExcel

POI

WorkBook>Sheet>Row>Cell

HSSFWorkbook         XSSFWorkbook     SXSSFWorkbook(临时文件关闭)

读取不同类型数据！！！

公式要注意（）

```java
FormulaEvaluator evaluator = workbook.getCreationHelper()
                  .createFormulaEvaluator();

CellValue cellValue = evaluator.evaluate(cell);//我理解为这是正则表达式 matches();
```

| HSSF | Microsoft Excel       | 03  65535 limit |
| ---- | --------------------- | --------------- |
| XSSF | Microsoft Excel OOXML | 07  slow>memory |
| HWPF | Microsoft Word        |                 |
| HSLF | Microsoft PowerPoint  |                 |
| HDGF | Microsoft Visio       |                 |

EasyExcel

https://www.yuque.com/easyexcel/doc/read

https://github.com/alibaba/easyexcel



轻量 Undertow /Jetty/tomcat  重量 JBoss/wildfly:default Undertow



Redis/Boot/Cloud





黄俊

javac Hello.java

--查看字节码

javap -c Hello

javap -verbose Hello   



javap -c Hello 将字节码反编译为字节码指令输出





java常用命令

javac Test.java 编译

java Test 执行

jar xf test.jar 解包

jar cvf package.jar file1 file2 folder1 folder2 压包



HashMap解析：

![preview](https://pic3.zhimg.com/v2-8cf9331314f6e50ea96de543d706c076_r.jpg)





```java
//Rotate Left  treeMap左旋
private void rotateLeft(Entry<K,V> p) {
    if (p != null) {
        Entry<K,V> r = p.right; //10  p是3 r是10
        p.right = r.left;  //10左给3右
        if (r.left != null) //10左不空 10左父为3
            r.left.parent = p;
        r.parent = p.parent;  //3父赋给10父 左旋完成
        if (p.parent == null)  //3父为空则根节点是10
            root = r;
        else if (p.parent.left == p)//3父节点的左节点为本身则其为10左节点
            p.parent.left = r;
        else                        //其他情况10的右节点为3
            p.parent.right = r;
        r.left = p; //10左为3
        p.parent = r;//3父为10
    }
}//总结：左旋是左节点变父节点，左节点（父节点）的右节点变其（原父节点）的左节点
```

**红黑树是一种近似平衡的二叉查找树，它能够确保任何一个节点的左右子树的高度差不会超过二者中较低那个的一陪**。具体来说，红黑树是满足如下条件的二叉查找树（binary search tree）：

1. 每个节点要么是红色，要么是黑色。
2. 根节点必须是黑色
3. 红色节点不能连续（也即是，红色节点的孩子和父亲都不能是红色）。
4. 对于每个节点，从该点至null（树尾端）的任何路径，都含有相同个数的黑色节点。

```java
//红黑树调整函数fixAfterInsertion() 就是通过左右旋来调整使树满足上面四个条件
private void fixAfterInsertion(Entry<K,V> x) {
    x.color = RED;
    while (x != null && x != root && x.parent.color == RED) {
        if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
            Entry<K,V> y = rightOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {//如果y为null，则视为BLACK
                setColor(parentOf(x), BLACK);              // 情况1
                setColor(y, BLACK);                        // 情况1
                setColor(parentOf(parentOf(x)), RED);      // 情况1
                x = parentOf(parentOf(x));                 // 情况1
            } else {
                if (x == rightOf(parentOf(x))) {
                    x = parentOf(x);                       // 情况2
                    rotateLeft(x);                         // 情况2
                }
                setColor(parentOf(x), BLACK);              // 情况3
                setColor(parentOf(parentOf(x)), RED);      // 情况3
                rotateRight(parentOf(parentOf(x)));        // 情况3
            }
        } else {
            Entry<K,V> y = leftOf(parentOf(parentOf(x)));
            if (colorOf(y) == RED) {
                setColor(parentOf(x), BLACK);              // 情况4
                setColor(y, BLACK);                        // 情况4
                setColor(parentOf(parentOf(x)), RED);      // 情况4
                x = parentOf(parentOf(x));                 // 情况4
            } else {
                if (x == leftOf(parentOf(x))) {
                    x = parentOf(x);                       // 情况5
                    rotateRight(x);                        // 情况5
                }
                setColor(parentOf(x), BLACK);              // 情况6
                setColor(parentOf(parentOf(x)), RED);      // 情况6
                rotateLeft(parentOf(parentOf(x)));         // 情况6
            }
        }
    }
    root.color = BLACK;
}
```

hashMap大部分摘自https://zhuanlan.zhihu.com/p/24795143



HashMap的put方法执行过程可以通过下图来理解，自己有兴趣可以去对比源码更清楚地研究学习。

![img](https://pic3.zhimg.com/80/58e67eae921e4b431782c07444af824e_hd.jpg)

①.判断键值对数组table[i]是否为空或为null，否则执行resize()进行扩容；

②.根据键值key计算hash值得到插入的数组索引i，如果table[i]==null，直接新建节点添加，转向⑥，如果table[i]不为空，转向③；

③.判断table[i]的首个元素是否和key一样，如果相同直接覆盖value，否则转向④，这里的相同指的是hashCode以及equals；

④.判断table[i] 是否为treeNode，即table[i] 是否是红黑树，如果是红黑树，则直接在树中插入键值对，否则转向⑤；

⑤.遍历table[i]，判断链表长度是否大于8，大于8的话把链表转换为红黑树，在红黑树中执行插入操作，否则进行链表的插入操作；遍历过程中若发现key已经存在直接覆盖value即可；

⑥.插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容。

JDK1.8HashMap的put方法源码如下:

```java
 1 public V put(K key, V value) {
 2     // 对key的hashCode()做hash
 3     return putVal(hash(key), key, value, false, true);
 4 }
 5 
 6 final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
 7                boolean evict) {
 8     Node<K,V>[] tab; Node<K,V> p; int n, i;
 9     // 步骤①：tab为空则创建
10     if ((tab = table) == null || (n = tab.length) == 0)
11         n = (tab = resize()).length;
12     // 步骤②：计算index，并对null做处理 
13     if ((p = tab[i = (n - 1) & hash]) == null) 
14         tab[i] = newNode(hash, key, value, null);
15     else {
16         Node<K,V> e; K k;
17         // 步骤③：节点key存在，直接覆盖value
18         if (p.hash == hash &&
19             ((k = p.key) == key || (key != null && key.equals(k))))
20             e = p;
21         // 步骤④：判断该链为红黑树
22         else if (p instanceof TreeNode)
23             e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
24         // 步骤⑤：该链为链表
25         else {
26             for (int binCount = 0; ; ++binCount) {
27                 if ((e = p.next) == null) {
28                     p.next = newNode(hash, key,value,null);
                        //链表长度大于8转换为红黑树进行处理
29                     if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st  
30                         treeifyBin(tab, hash);
31                     break;
32                 }
                    // key已经存在直接覆盖value
33                 if (e.hash == hash &&
34                     ((k = e.key) == key || (key != null && key.equals(k))))                                          break;
36                 p = e;
37             }
38         }
39         
40         if (e != null) { // existing mapping for key
41             V oldValue = e.value;
42             if (!onlyIfAbsent || oldValue == null)
43                 e.value = value;
44             afterNodeAccess(e);
45             return oldValue;
46         }
47     }

48     ++modCount;
49     // 步骤⑥：超过最大容量 就扩容
50     if (++size > threshold)
51         resize();
52     afterNodeInsertion(evict);
53     return null;
54 }
```

https://www.baidu.com/link?url=FC7O2yK4kVlZ6L8hkC97rQN0Td2HiRGnhj_vLtkScClL8SQzt9LotBto3ew_N9jsWneEdI67q93Eh0w5nldzWq&wd=&eqid=cc458b020004a14a00000004601a4570



SQL(Structure Query Language)语言是数据库的核心语言。

SQL语言共分为四大类：数据查询语言DQL，数据操纵语言DML，数据定义语言DDL，数据控制语言DCL。

***\*1. 数据查询语言DQL\****
数据查询语言DQL基本结构是由SELECT子句，FROM子句，WHERE
子句组成的查询块：
SELECT <字段名表>
FROM <表或视图名>
WHERE <查询条件>

***\*2 .数据操纵语言DML\****
数据操纵语言DML主要有三种形式：
1) 插入：INSERT
2) 更新：UPDATE
3) 删除：DELETE

***\*3. 数据定义语言DDL\****
数据定义语言DDL用来创建数据库中的各种对象-----表、视图、
索引、同义词、聚簇等如：
CREATE TABLE/VIEW/INDEX/SYN/CLUSTER
| | | | |
表 视图 索引 同义词 簇

DDL操作是隐性提交的！不能rollback 

***\*4. 数据控制语言DCL\****
数据控制语言DCL用来授予或回收访问数据库的某种特权，并控制
数据库操纵事务发生的时间及效果，对数据库实行监视等。如：
1) GRANT：授权。


2) ROLLBACK [WORK] TO [SAVEPOINT]：回退到某一点。
回滚---ROLLBACK
回滚命令使数据库状态回到上次最后提交的状态。其格式为：
SQL>ROLLBACK;


3) COMMIT [WORK]：提交。


  在数据库的插入、删除和修改操作时，只有当事务在提交到数据
库时才算完成。在事务提交前，只有操作数据库的这个人才能有权看
到所做的事情，别人只有在最后提交完成后才可以看到。
提交数据有三种类型：显式提交、隐式提交及自动提交。下面分
别说明这三种类型。


(1) 显式提交
用COMMIT命令直接完成的提交为显式提交。其格式为：
SQL>COMMIT；


(2) 隐式提交
用SQL命令间接完成的提交为隐式提交。这些命令是：
ALTER，AUDIT，COMMENT，CONNECT，CREATE，DISCONNECT，DROP，
EXIT，GRANT，NOAUDIT，QUIT，REVOKE，RENAME。

(3) 自动提交
若把AUTOCOMMIT设置为ON，则在插入、修改、删除语句执行后，
系统将自动进行提交，这就是自动提交。其格式为：
SQL>SET AUTOCOMMIT ON；





PreparedStatement

boolean execute() throws SQLException;

true the first result is a ResultSet;  false  the first result is an update count or there is no result.



ResultSet executeQuery() throws SQLException;

```
* @return either (1) the row count for SQL Data Manipulation Language (DML) statements
*         or (2) 0 for SQL statements that return nothing
```

int executeUpdate() throws SQLException;DML



Mybatis架构设计

配置文件   Mapper仓库 

接口如何实例化为一个bean?

代理模式-JDK动态代理.

Proxy.newProxyInstance();





架构设计

​					Mapper注册中心

配置类		执行器

​					StatementHandler

​					 										字段映射处理

​					结果集Handler

## MyBatis 流程

1. 创建 SqlSessionFactory 对象。
2. SqlSessionFactory 获取 SqlSession 对象。
3. SqlSession 获得 Mapper 代理对象。
4. Mapper 代理对象，执行数据库操作。
5. 执行成功，则使用 SqlSession 提交事务。
6. 执行失败，则使用 SqlSession 回滚事务。
7. 关闭会话。





## Mybatis 都有哪些 Executor 执行器？它们之间的区别是什么？

Mybatis 有四种 Executor 执行器，分别是 SimpleExecutor、ReuseExecutor、BatchExecutor、CachingExecutor 。

- SimpleExecutor ：每执行一次 update 或 select 操作，就创建一个 Statement 对象，用完立刻关闭 Statement 对象。
- ReuseExecutor ：执行 update 或 select 操作，以 SQL 作为key 查找**缓存**的 Statement 对象，存在就使用，不存在就创建；用完后，不关闭 Statement 对象，而是放置于缓存 `Map` 内，供下一次使用。简言之，就是重复使用 Statement 对象。
- BatchExecutor ：执行 update 操作（没有 select 操作，因为 JDBC 批处理不支持 select 操作），将所有 SQL 都添加到批处理中（通过 addBatch 方法），等待统一执行（使用 executeBatch 方法）。它缓存了多个 Statement 对象，每个 Statement 对象都是调用 addBatch 方法完毕后，等待一次执行 executeBatch 批处理。**实际上，整个过程与 JDBC 批处理是相同**。
- CachingExecutor ：在上述的三个执行器之上，增加**二级缓存**的功能。

------

通过设置 `` 的 `"value"` 属性，可传入 SIMPLE、REUSE、BATCH 三个值，分别使用 SimpleExecutor、ReuseExecutor、BatchExecutor 执行器。

通过设置 ` 的 `"value"` 属性为 `true` 时，创建 CachingExecutor 执行器。









tomcat原理

tomcat类加载器,common-catalina-shared  Loader 





Dubbo

![image-20210526204829961](D:\Git-Center\Learning-Notes\source\static\images\Dubbo-mini.png)

- Provider

  - 第 0 步，start 启动服务。
  - 第 1 步，register 注册服务到注册中心。

- Consumer

  - 第 2 步，subscribe 向注册中心订阅服务。
    - 注意，只订阅使用到的服务。
    - 再注意，首次会拉取订阅的服务列表，缓存在本地。
  - 【异步】第 3 步，notify 当服务发生变化时，获取最新的服务列表，更新本地缓存。

- invoke 调用

  - Consumer 直接发起对 Provider 的调用，无需经过注册中心。而对多个 Provider 的负载均衡，Consumer 通过 **cluster** 组件实现。

- count 监控

  - 【异步】Consumer 和 Provider 都异步通知监控中心。

  

  容错模式

  **Failover Cluster**

  失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过 `retries="2"` 来设置重试次数(不含第一次)。

  **Failfast Cluster**

  快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。

  **Failsafe Cluster**

  失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作。

  **Failback Cluster**

  失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。

  **Forking Cluster**

  并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 `forks="2"` 来设置最大并行数。

  **Broadcast Cluster**

  广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。





NETTY

 **Java NIO 的步骤**如下：

1. 创建 ServerSocketChannel 。

   - 绑定监听端口，并配置为非阻塞模式。

2. 创建 Selector，将之前创建的 ServerSocketChannel 注册到 Selector 上，监听 

   ```
   SelectionKey.OP_ACCEPT
   ```

    。

   - 循环执行 `Selector#select()` 方法，轮询就绪的 Channel。

3. 轮询就绪的 Channel 时，如果是处于 

   ```
   OP_ACCEPT
   ```

    状态，说明是新的客户端接入，调用 

   ```
   ServerSocketChannel#accept()
   ```

    方法，接收新的客户端。

   - 设置新接入的 SocketChannel 为非阻塞模式，并注册到 Selector 上，监听 `OP_READ` 。

4. 如果轮询的 Channel 状态是 

   ```
   OP_READ
   ```

    ，说明有新的就绪数据包需要读取，则构造 ByteBuffer 对象，读取数据。

   - 这里，解码数据包的过程，需要我们自己编写。



**Netty 的步骤**如下：

1. 创建 NIO 线程组 EventLoopGroup 和 ServerBootstrap。
   - 设置 ServerBootstrap 的属性：线程组、SO_BACKLOG 选项，设置 NioServerSocketChannel 为 Channel
   - 设置业务处理 Handler 和 编解码器 Codec 。
   - 绑定端口，启动服务器程序。
2. 在业务处理 Handler 中，处理客户端发送的数据，并给出响应。



Kafka

Producer      My Topic       Partion队列   Consumer

持久到硬盘指定时间可控  消费消息由consumer控制,可取读取任意位置的消息



P  KafkaTemplate   C  @KafkaListenner



RabbitMQ(有管理界面)port15672

P-Exchange--type-Queue---C

Exchange RoutingKey  Queue

消息接收有 （Confirm）ack/nack两种发给生产者．

接收方可以指定noAck=false，消费者显式返回ack信息队列才会从内存（或磁盘）中移去消息，但消费者要对消息唯一性校验，没返回ack会再发重复消息．允许消息时间可以很长超时重发，断开链接重发．



@Bean rabbitQueue

P RabbitTemplate     C   @RabbitListener(topics={rabbitQueueName})

