# Java多线程编程核心技术

## 第一章  Java多线程技能

**进程**：是操作系统结构的基础;是一次程序的执行;是一个程序及其数据在处理机上顺序执行时所发生的活动;是程序在一个数据集合上运行的过程，它是系统进行资源分配和调度的一个独立单位。

**线程**：可理解成是在进程中独立运行的子任务。

**多线程是异步的。**非顺序执行，线程调用的时机是随机的。

实现多线程主要两种：继承Thread类/实现Runable

**实例变量与线程安全**

实例变量 

①不共享数据的情况

线程内变量各线程自己使用

②共享数据的情况

一个公用线程做为参数实例多个线程

**留意i--与System.out.pringln()的异常**（两个原子操作合起来都不是原子操作，更何况i--不是）

currentThread()方法 可返回代码段被哪个线程调用的信息

isAlive()方法  判断当前线程是否处于活动状态

sleep()方法  指定毫秒数让“当前正执行线程”休眠 **this.currentThread()**返回的线程

getId()方法  作用是取得线程的唯一标识。（如两相同名的线程）

**停止线程**

①interrupt()并非真的停止线程，只是给当前线程作了停止的标记。

②this.interrupted() 当前线程是否已中断 执行后具有将状态标志置清除为载false的功能。（第一次中断后，第二次中断j时调用检验完中断状态前不一定是false）

this.isInterrupted() 线程是否已中断

③异常  InterruptedException  中断后下面有代码会继续执行

④先sleep再停止，sleep前后代码都执行再进入异常捕获 清除停止状态值 为false

先停止再sleep,sleep后代码不执行进入异常捕获

⑤stop()暴力停止

⑥stop()与java.lang.ThreadDeath异常  stop()无须显式捕获该异常   stop()可能使一些清理性的工作得不到完成  还会解锁锁定对象，导致不同步

⑦stop()释放锁造成数据不一致

⑧推荐用抛异常方式停止，能向上传播。 interrupt()与return结束也能实现 线程停止。



第二章  对象及变量的并发访问

第三章  线程间通信

第四章  Lock的使用

第五章  定时器Timer

第六章  单例模式与多线程

第七章  拾遗增补



Spring Cloud Alibaba 微服务原理与实战

第一章 微服务的发展史

1、单体到分布式过程

①单体－集群及垂直化（增加服务器及拆分子系统）－SOA－微服务

SOA（Service-Oriented Architecture）：1、信息孤岛（信息互通） 2、共享业务的重用（防冗余，服务重用）

微服务：1、解耦（业务解耦）  2、容器化技术结合 3、持续交付

优点：1、细粒度 复杂度可控

​			2、技术灵活，微服务可由不同团队结合业务特性自由选择

​			3、可扩展性更强，增加单服务集群或配置

​			4、独立部署

​			5、容错性 重试or降级等实现应用层面的容错

个人理解作者意思是 SOA是多个系统用相同的服务，服务共用；微服务是单个系统业务解耦；

2、微服务挑战

​	1、故障排查（定位哪个服务出错！体积变大，链路变长）

​	2、服务监控（对每个微服务监控！）

​	3、分布式复杂性（远程通信，网络时延及故障无法避免！）

​	4、服务依赖（各服务之间的依赖关系需定位好！）

​	5、运维成本（管理 例：单服务流量激增如何快速扩容；拆分后故障点多如何处理；如何快速部署和统一管理多个微服务）

3、如何实现微服务架构

服务注册发现/配置中心/网关/负载均衡/授权认证/容错限流   调用链监控/日志监控/健康检查or告警

各技术选型与实践

第二章 微服务解决方案之Spring Cloud

1、Is what?

让开发者快速构建微服务应用的**工具**；比如配置管理、服务发现、熔断、智能路由等；

致力于解决的问题是：

① Distributed/versioned configuration, 分布式版本化配置。

② Service registration and discovery, 服务注册与发现。

③ Routing, 服务路由。

④ Service-to-service calls, 服务调用。

⑤ Load balancing, 负载均衡。

⑥ Circuit Breakers, 断路器。

⑦ Global locks, 全局锁。

⑧  Leadership election and cluster state, Leader选举及集群状态。

⑨ Distribute messaging, 分布式消息。

2、Version introduction

3、Spring Cloud规范下的实现

4、Spring Cloud Netflix

​	  Eureka,服务注册与发现。

​	   Zuul,服务网关。

​	   Ribbon, 负载均衡。

​       Feign, 远程服务的端代理。

​		Hystrix,断路器，提供服务熔断和限流功能。

​		Hystrix Dashboard,监控面板。

​		Trubine, 将各个服务实例上的Hystrix监控信息进行统一聚合。有组件停更

5、Spring Cloud Alibaba

​		Sentinel 流量控制和服务降级

​		Nacos 服务注册与发现

​		Nacos 分布式配置中心

​		RocketMQ  消息驱动

​		Seate  分布式事务

​		Dubbo RPC通信

​		OSS 阿里云对象存储（收费的云服务）。

5.1 Advantages of Spring Cloud Alibaba

​	1、未织入Cloud生态前已经广泛使用，Dubbo支持多协议

​    2、经过了双11考验0.0

5.2 Spring Cloud Alibaba Version

​		



第三章 Spring Cloud的核心之Spring Boot

​	1、重识Spring Boot

​	2、构建Spring Boot应用

​	3、Spring Boot自动装配的原理

​	4、手写实现一个Starter

​	5、小结

第四章 微服务架构下的服务治理

第五章 服务注册与发现

第六章 Nacos实现统一配置管理

第七章 基于Sentinel的微服务限流及熔断

第八章 分布式事务

第九章 RocketMQ分布式消息通信

第十章 微服务网关之Spring Cloud Gateway















# Java与模式	-阎宏

## 第一部分  UML和模式介绍

#### 第一章  模式的简史和形而上学

模式：问题及解决方案重复出现，不同面孔有**共同的本质**就是模式。

质：QWAN    无名质The Quality Without a Name ;

包括   整体性 wholeness 完备性completeness 舒适性comfort  和谐性harmony 可居住性 habitability

持久性durability 开放性openness 可变性variability 可塑性 adaptability等 。

QWAN是建筑设计依据的原始根据，定义的“质”是一切工程设计的开始。

门：

道：The Timeless Way 永恒之道

java源码/气/门  道/定义/组合  系统/体/质

用道由气变质  气变‘形’  形即模式



模式的**要素**：

名字 Name

问题 Problem

环境或初始环境 Context / Initial Context 模式问题及解答的前提条件

力 Forces  力给出与模式相关的力和约束

解答  Solution

举例 Examples

末态环境 Resulting Context 应用模式后的结果

推理 Rationale 解释模式步骤、规则

其他有关模式 Related Patterns 与其他模式的静态和动态的关系

已知的应用 Know Uses



一个模式**应当包含**所有要素。



讲解模式**格式**

介绍/结构/长短处/什么情况使用/实现的讨论/举例/相关模式



#### 第二章 统一建模语言UML简介 

统一建模语言 Unified Modeling Language

对软件系统来说 UML功能

可视化功能 Visualizing

说明功能     Specifying

建造功能     Constructing

建文档功能  Documenting



UML包括以下的图：

使用案例图 Use case diagrams

类图             Class diagrams

序列图          Sequence diagrams

合作图          Collaboration diagrams

状态图           Statechart diagrams

活动图			Activity diagrams

构件图			 Component diagrams

部署图             Deployment diagrams



## 第二部分  面向对象的设计原则 

#### 第三章 软件的可维护性与可复用性 

支持可维护性(Maintainability)同时，提高系统可复用性(Reuseability)是一个核心问题。

导致可维护性较低的真正原因：过于僵硬(Rigidity)、过于脆弱(Fragility)、复用率低(Immobility)、黏性过高(Viscosity)

设计的目标：可扩展性(Extensibility)、灵活性(Flexibility)、可插入性(Pluggability)

设计原则：

“开－闭”原则（Open-Closed Principle,OCP）

里氏代换原则（Liskov Substitution Principle,LSP）

依赖倒转原则（Dependency Inversion Principle,DSP）

接口隔离原则（Interface Segregation Principle,ISP）

组合/聚合利用原则（Composition/Aggregation Principle,CARP）

迪米特法则（Law of Demeter,LoD）



**开闭原则：**

在设计模块时，可不修改前提下被扩展，或不修改源代码下改变模块的行为。

1. 提供新行为，满足新需求，使变化的系统有一定的适应性和灵活性。
2. 已有模块，特别重要的抽象层模块不能再修改，使变化的系统有一定的稳定性和延续性。

抽象化是关键：抽象可变实现，满足1,抽象不变，满足2。

对可变性的封装原则，开闭的另一角度（Principle of Encapsulation of Variation，常常略写为EVP）

1. 一种可变性不应散落在很多角落，可变性表象在同层级的具体子类
2. 一种可变性不应与另一种可变性混合，设计模式的类图一般都不会超过两层。

**里氏代换原则：**

任何基类可出现的地方，子类一定可以出现。是对开闭原则的补充。是对实现抽象化的具体步骤的规范。一般来说，违反里氏，也违背了开闭，反之不一定成立。

**依赖倒转原则：**

依赖抽象，不要依赖于实现。

与开闭原则是目标和手段的关系，开闭原则是目标，手段是依赖倒转原则。

**合成/聚合复用原则：**

尽量使用合成/聚合，而不继承达到复用目的。

优先使用合成/聚合，里氏代换需符合一定条件。与里氏相辅相成实现开闭目标。

遵守合成/聚合是实现开闭的必要条件，违反无法实现目标。

**迪米特法则：**

一个软件实体尽可能少与其他实体相互作用。

即模块修改尽可能不要传递，相对孤立，相对更容易做到对修改的关闭，是通向开闭的道路。

**接口隔离原则：**

尽可能提供小而单独的接口

与迪米特都是对软件实体的通信限制，广义迪米特尽可能限制通信的宽度和深度，接口则是限制通过的宽度，通信尽可能的窄。遵守迪米特和接口隔离，显然的将修改压力更不会传递到其他的实体。



所有设计模式对不同的可变性封装，使系统在不同角度上满足开闭原则的要求。







# Java程序员修炼之道 －Ben Evans

## 第一部分  用Java 7做开发

#### 第一章  初识java7

![image-20210317154405378](D:\Git-Center\Learning-Notes\source\static\images\java7change.png)

![image-20210317154019691](D:\Git-Center\Learning-Notes\source\static\images\classProcess.png)

**变化点：**

1. swtich增加对String的支持。

2. 更强的数值文本表示法

   1.数字常量可用二进制文本表示

   ![image-20210317161938972](D:\Git-Center\Learning-Notes\source\static\images\数可二进制表示.png)

   2.可用下划线分隔

   ![image-20210317162416418](D:\Git-Center\Learning-Notes\source\static\images\数可加下划线.png)

3. 改善后的异常处理

   multicatch和final重抛

   ![image-20210317163305485](D:\Git-Center\Learning-Notes\source\static\images\multicatch.png)

   1. 

   ![image-20210317163434630](D:\Git-Center\Learning-Notes\source\static\images\multicatchPro.png)

   ![image-20210317163612008](D:\Git-Center\Learning-Notes\source\static\images\finalCatch.png)

4. try-with-resources(TWR)

![](D:\Git-Center\Learning-Notes\source\static\images\try-with-resources.png)

![image-20210317171033762](D:\Git-Center\Learning-Notes\source\static\images\TWR.png)

5.钻石语法

创建泛型定义和实例太过繁琐，可在声明时new省咯，编译器会用前面推断后面

![image-20210317172459779](D:\Git-Center\Learning-Notes\source\static\images\dimondGrammer.png)

6.简化变参方法调用

去掉方法签名中**同时出现**泛型和变参的警告

![image-20210317192434275](D:\Git-Center\Learning-Notes\source\static\images\SafeVarargs.png)



*JSR*: *JSR*是Java Specification Requests的缩写,意思是“Java 规范提案”。

#### 第二章  NIO





#### 第三单 依赖注入





## 第二部分  关键技术

#### 第四章  现代并发

![image-20210318193451752](D:\Git-Center\Learning-Notes\source\static\images\concurrent.png)



![image-20210318193746994](D:\Git-Center\Learning-Notes\source\static\images\concurrent_warn.png)



**系统开销来源**

锁与监测/环境切换/线程个数/调度/内存的局部性/算法设计

#### 第五章 类文件与字节码



#### 第六章 理解性能调优



1. 性能术语

   等待时间  Latency

   吞吐量      Throughput

   利用率	   Utilization

   效率           Efiiciency

   容量            Capacity

   扩展性         Scalability

   退化             Degradation

2. 短时间用nanoTime(),长时间要用currentTimeMills()校准。







