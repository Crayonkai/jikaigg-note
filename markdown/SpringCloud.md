# SpringCloud

## Eureka

>  Eureka是[Netflix](https://baike.baidu.com/item/Netflix/662557)开发的服务发现框架，本身是一个基于[REST](https://baike.baidu.com/item/REST/6330506)的服务，主要用于定位运行在AWS域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。
>
>  SpringCloud将它集成在其子项目spring-cloud-netflix中，以实现SpringCloud的服务发现功能
>
>  Eureka包含两个组件：Eureka Server和Eureka Client。
>
>  Eureka Server提供服务注册服务，各个节点启动后，会在Eureka Server中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。
>
>  Eureka Client是一个[java](https://baike.baidu.com/item/java/85979)客户端，用于简化与Eureka Server的交互，客户端同时也就是一个内置的、使用轮询(round-robin)负载算法的[负载均衡器](https://baike.baidu.com/item/负载均衡器/8852239)。
>
>  在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。
>
>  Eureka Server之间通过复制的方式完成数据的同步，Eureka还提供了客户端缓存机制，即使所有的Eureka Server都挂掉，客户端依然可以利用缓存中的信息消费其他服务的API。综上，Eureka通过心跳检查、[客户端缓存](https://baike.baidu.com/item/客户端缓存/10237000)等机制，确保了系统的高可用性、灵活性和可伸缩性。

依赖包

```java
<!-- eureka服务端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<!-- eureka客户端 -->  
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

#### eureka配置

```yaml
# 服务端配置
server:
  port: 10086  #配置eureka服务的端口
spring:
  application:
    name: eurekaserver #配置服务名称
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:10086/eureka  # 注册到eureka注册中心（服务端也作为一个服务注册进去）
      
# 客户端配置
eureka:
  client:
    service-url:
      defaultZone: http://127.0.0.1:10086/eureka   # 注册到eureka注册中心
```





## Ribbon

> Ribbon 是 NetFlix 公司推出的开源软件，是基于 HTTP 和 TCP 协议的，其主要功能是实现客户端的负载均衡算法。
> Spring Cloud 中Ribbon是基于NetFlix公司的 Ribbon实现的。它不需要单独部署，但是却存在于整个微服务中。OpenFegin 也是基础Ribbon实现的。

``` java
   @Bean
    @LoadBalanced/*public class LoadBalancerInterceptor implements ClientHttpRequestInterceptor */
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
```

LoadBalancerInterceptor: 负载均衡拦截器，拦截http请求，通过拉去的注册服务列表根据负载均衡的规则进行服务的调用。

![image-20220731212445447](./img/Irule.png)

## Nacos

> Nacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。

#### 安装与启动

**windows安装与启动**

下载nacos压缩包，解压到没有中文、空格的一个目录

```shell
# 进入到bin目录打开cmd，输入启动命令
startup.cmd -m standalone
# 浏览器打开http://192.168.110.216:8848/nacos 用户名和密码都是nacos
```

**linux安装与启动**

解压到目录中

```shell
## 启动命令
sh startup.sh -m standalone
# 浏览器打开http://192.168.110.216:8848/nacos 用户名和密码都是nacos
```

#### pom文件配置nacos依赖包：

```java
<!--父工程-->
<!--SpringCloud-阿里巴巴-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>

<!--子工程nacos依赖包-->
<!-- nacos客户端依赖包 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

#### nacos服务分级存储模型：

1. 一级是服务：例如userservice
2. 二级是集群：例如上海集群（SH）、杭州集群（HZ）
3. 三级是实例：例如杭州机房的某台服务器部署了userservice服务

#### 环境隔离：

1. namespace用来做环境隔离
2. 每个namespace都有唯一id
3. 不同namespace的服务实例不能进行访问。

#### yml文件配置nacos服务地址：

```yaml
spring:
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848 #nacos服务地址
      discovery:
        cluster-name: SH  #集群名称，这里SH代指上海
        namespace:  # 环境隔离，命名空间，填写命名空间的id
userservice:
  ribbon:
    NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule  # 配置有限访问本地集群（都是SH的），本地集群无健康集群，进行跨集群访问（日志会有警告信息）。负载均衡规则是随机的
```

#### 配置中心

```yaml
#添加依赖
#nacos配置中信依赖
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>

#spring项目启动顺序为：项目启动->读取本地配置文件appication.yml->创建spring容器->加载bean
#增加nacos配置中新后：项目启动->读取nacos配置文件->读取本地配置文件appication.yml->创建spring容器->加载bean
#bootstrap.yml配置文件在项目一启动最先加载，所以需要把一些最基本信息添加进去
#bootStrap.yml  
spring:
  application:
    name: userservice    #服务名称
  profiles:
    active: dev   # 当前环境
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848 #nacos服务地址
      config:
        file-extension: yaml    #配置文件后缀名
```

![image-20220804203528599](./img/nacos-config1.png)

#### 配置热更新

**方式一：**

在@Value注入的变量所在的类上添加注解@RefreshScope

**方式二：**

```java
// 添加配置类
@Data
@ConfigurationProperties(prefix = "partten")
@Component
public class ParttenConfig {
    private String dateformat;
}
// 使用注入
	@Autowired
    private ParttenConfig parttenConfig;

    @GetMapping("now")
    private String getNow(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(parttenConfig.getDateformat()));
    }
```

#### 多环境配置共享

![image-20220804233519973](./img/nacos-config2.png)

**配置文件优先级**

环境配置文件属性>共享配置文件属性>本地配置文件属性

## OpenFeign

#### 依赖包

```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

#### 常用注解

```java
// 定义在启动类上，开启Feign，basePackages扫描feign服务定义包路径
//@EnableFeignClients(basePackages = "com.jikaigg.note.feign")
// 定义在启动类上，开启Feign，clients定义加载到项目中要调用的feign定义的具体服务（推荐使用，用什么服务加载什么服务）
@EnableFeignClients(clients = UserClient.class)
// 定义提供访问的服务接口
@FeignClient(value = "userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    User queryById(@PathVariable("id") Long id);
}
```

#### 日志配置

```java
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}

NONE：默认，不显示任何日志；
BASIC: 仅记录请求方法、URL、响应状态码及执行时间；
HEADERS：除了BASIC中定义的信息之外，还有请求头和响应头信息；
FULL：除了HEADERS中定义的信息之外，还有请求的正文和响应数据。
```



## SpringCloud-gateway

#### 依赖包

```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

#### Predicate断言工厂

读取配置的断言规则，判断是否允许请求

![image-20220808231027058](./img/Predicate.png)

#### 路由过滤器 GatewayFilter

队进入网关的请求和微服务返回的响应做处理

#### 全局过滤器

实现GlobalFilter接口，添加@Order注解

```java
//@Order(-1)  //定义过滤器的执行顺序，数越小执行越早
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        // 2.获取参数中的AuthorizeFile
        MultiValueMap<String, String> params = request.getQueryParams();
        // 3.判断参数之是否等于admin
        String first = params.getFirst("authorization");
        // 4.是，放行
        if ("admin".equals(first)) {
            return chain.filter(exchange);
        } else {
            // 5.1 设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // 5.否，拦截
            return exchange.getResponse().setComplete();
        }
    }
    //事项Ordered接口重写方法，和@Order注解效果一样
    @Override
    public int getOrder() {
        return -1;
    }
}
```

![image-20220808233840441](./img/GatewayFilter.png)

**过滤器执行顺序：路由过滤器和detaultFilter有spring管理，按照顺序由1递增；defaultFilter>路由过滤器>GloblaFilter**

#### 跨域问题

> 域名不一致就是跨域
>
> * 域名不同：www.taobao.com和www.taobao.org ; www.jd.com和www.miaosha.jd.com
>
> * 域名相同端口不通:：localhost:8080和localhost:8081
>
>   浏览器禁止请求的发起者与服务端发生跨域ajax请求，请求被浏览器拦截的问题
>
> 解决方案：CORS

#### 配置文件

```java
server:
  port: 8085
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: 192.168.56.104:8848 #nacos服务地址
      #      discovery:
      #        cluster-name: HZ   # 集群名称，SH代指上海
      config:
        file-extension: yaml
    gateway:
      routes: #网关路由配置
        - id: userservice #路由id，自定义，只要唯一即可
          #          uri: http://192.168.56.104:8848/userservice #路由的目标地址http就是固定地址
          uri: lb://userservice #路由的目标地址是lb就是负载均衡，后面跟服务名称
          predicates: #路由断言，也就是判断请求是否符合路由规则的条件
            - Path=/user/** #这个是按照路径匹配，只要是/user/开头就符合要求
#          filters:
#            - AddRequestHeader=yao,jikai
        - id: orderservice
          uri: lb://orderservice
          predicates:
            - Path=/order/**
#            - After=2031-04-13T11:11:11.010+08:00[Asia/Shanghai]  #这个时间点之后允许访问
            - Before=2031-04-13T11:11:11.010+08:00[Asia/Shanghai]  #这个时间点之前允许访问
      default-filters: #默认过滤器，全局的
        - AddRequestHeader=yao,jikai111
      globalcors: # 全局的跨域处理
        add-to-simple-url-handler-mapping: true # 解决options请求被拦截问题
        corsConfigurations:
          '[/**]':
            allowedOrigins: # 允许哪些网站的跨域请求
              - "http://localhost:8090"
              - "http://www.leyou.com"
            allowedMethods: # 允许的跨域ajax的请求方式
              - "GET"
              - "POST"
              - "DELETE"
              - "PUT"
              - "OPTIONS"
            allowedHeaders: "*" # 允许在请求中携带的头信息
            allowCredentials: true # 是否允许携带cookie
            maxAge: 360000 # 这次跨域检测的有效期
```

## Docker

![image-20220809001950576](./img/docker-vm.png)



























## 设计模式

>设计模式六大原则：
>
>1. 开闭原则（Open Closed Principle，OCP）对扩展开放，对修改关闭
>2. 单一职责原则（Single Responsibility Principle, SRP）一个类只负责一个功能领域中的相应职责
>3. 里氏代换原则（Liskov Substitution Principle，LSP）所有引用基类的地方必须能透明地使用其子类的对象
>4. 依赖倒转原则（Dependency Inversion Principle，DIP）依赖于抽象，不能依赖于具体实现
>5. 接口隔离原则（Interface Segregation Principle，ISP）类之间的依赖关系应该建立在最小的接口上
>6. 合成/聚合复用原则（Composite/Aggregate Reuse Principle，CARP）尽量使用合成/聚合，而不是通过继承达到复用的目的
>7. 最少知识原则（Least Knowledge Principle，LKP）或者迪米特法则（Law of  Demeter，LOD）一个软件实体应当尽可能少的与其他实体发生相互作用

#### 适配器模式



#### 门面模式

要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。

优点：高内聚，松耦合。安全，不通过门面上提供的方法，休想访问模块内部。

门面模式是个很好的模式，很符合面向接口编程，遵守了依赖倒置原则、迪米特法则等，当然，有些书说违背了开-闭原则，我个人认为，门面模式并不妨碍拓展，只要把基类抽取好，新功能只需要继承或依赖与基类即可。



## Other

#### Linux写自定义脚本命令启动nacos

/usr/bin目录下新建nacos-start

```shell
1. 新建文件 sudo vim nacos-start
2. 编辑文件输入脚本内容，如下
#!/bin/bash
sudo /bin/bash /usr/local/nacos/bin/shutdown.sh
sudo /bin/bash /usr/local/nacos/bin/startup.sh -m standalone
3. 保存退出修改权限
chmod 775 nacos-start
```

#### 工作中的环境问题

DEV Development 研发环境
SIT System Integrate Test 系统集成测试环境（内测）
UAT User Acceptance Test 用户验收测试环境
PET Performance Evaluation Test 性能评估测试环境（压测）
SIM Simulation 高仿真环境
PRD/PROD Production 正式/生产环境

小米：dev->staging->prod

















































