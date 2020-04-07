​										Spring Boot

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







​										Spring Cloud

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