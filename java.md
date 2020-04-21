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

###### Spring AOP

定义

面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

优点

利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

原理

aop底层将采用代理机制进行实现。

接口 + 实现类：spring 默认采用 jdk 的动态代理 Proxy。

实现类：spring 默认采用 cglib 字节码增强。



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

Redis是一款基于键值对的NoSQL数据库，它的值支持多种数据结构：

字符串（strings)、哈希（hashes）、列表（lists）、集合（sets）、有序集合（sorted sets）等。

Redis将所有的数据都存放在内在中，所以它的读写性能十分惊人。

同时，Redis还可以将内在中的数据以快照或日志的形式保存到硬盘上，以保证数据的安全性。

Redis典型的应用场景包括：缓存、排行榜、计数器、社交网络、消息队列等。

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