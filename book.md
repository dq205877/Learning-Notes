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

5、Spring Cloud Alibaba

5.1 Advantages of Spring Cloud Alibaba

5.2 Spring Cloud Alibaba Version



第三章 Spring Cloud的核心之Spring Boot

第四章 微服务架构下的服务治理

第五章 服务注册与发现

第六章 Nacos实现统一配置管理

第七章 基于Sentinel的微服务限流及熔断

第八章 分布式事务

第九章 RocketMQ分布式消息通信

第十章 微服务网关之Spring Cloud Gateway