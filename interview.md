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

  		IS		IX		S		X

IS				 	 				 no

IX							no	  no

S				    no               no

X		no       no     no     no

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