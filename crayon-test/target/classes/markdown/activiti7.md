# Activiti7工作流
[toc]

## 工作流介绍


## 使用步骤
#### 部署activiti
引入jar包
#### 流程定义
定义业务（BPMN文件） ，按照BPMN的规范，使用流程定义工具，用流程符号吧整个流程表述出来
##### 流程符号

1. 事件
2. 活动
3. 网关
4. 流向

#### 流程部署
把画好的流程定义文件，加载到数据库中，生成表的数据

#### 启动流程
流程实例也叫ProcessInstance
使用java代码来操作数据库表中的内容

#### 用户查询待办任务（Task）

#### 用户办理任务

#### 流程结束

## Activiti环境

## Activiti的数据库支持
Activiti在运行时需要数据库的支持，使用25张表，把流程定义节点内容读取到数据库表中，一共后续使用
### 在mysql中生成表
#### 创建数据库
```mysql
CREATE DATABASE activiti DEFAULT CHARACTER SET utf8;
```
#### 使用java代码生成表
```xml
<dependencies>
        <!-- activiti mysql驱动 mybatis log4j -->
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-engine</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-model</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-converter</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-json-converter</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-bpmn-layout</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>org.activiti.cloud.rb</groupId>
            <artifactId>activiti-cloud-services-api</artifactId>
            <version>7.1.0.M6</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.6.6</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.6.6</version>
        </dependency>
    </dependencies>
```
#### activiti配置文件
使用activiti提供的默认方式来创建mysql表
默认方式要求在resources下创建activiti.cfg.xml文件，路径和文件名固定不可变
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans

       http://www.springframework.org/schema/beans/spring-beans.xsd">
<!-- 在默认方式下 beanID为固定的processEngineConfiguration -->
    <bean id="processEngineConfiguration" class="org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration">
        <!-- 配置数据库信息 -->
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/activiti" />
        <property name="jdbcDriver" value="com.mysql.cj.jdbc.Driver" />
        <property name="jdbcUsername" value="root" />
        <property name="jdbcPassword" value="" />
        <!-- 配置模式  true 自动创建和更新表，如果数据库中已经存在相应的表，那么直接使用，如果不存在，则创建 -->
        <property name="databaseSchemaUpdate" value="true" />

    </bean>

</beans>
```
#### java代码生成25张表
```java
    /**
 * 两种方式创建mysql的表
 */
public static void main(String[] args) {
        // 默认方式
        // getDefaultProcessEngine默认从resouces文件夹下读取activiti.cfg.xml文件
        // 创建ProcessEnging，创建MySQL表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 自定义方式
        // createProcessEngineConfigurationFromResource中参数可自定义，不用默认方式中的固定命名
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
        ProcessEngine buildProcessEngine = processEngineConfiguration.buildProcessEngine();

        System.out.println(processEngine);

        }
    
    
```
#### 25张表介绍/作用
表结构操作：
1. 资源库流程规则表repository
1).act_re_deployment  部署信息表
2).act_re_model       流程设计模型部署表
3).act_re_procdef     流程定义数据表
2. 运行时数据库表runtime
1).act_ru_execution   运行时流程执行实例表
2).act_ru_identitylink运行时流程人员表，主要存储任务节点与参与者的相关信息
3).act_ru_task        运行时任务节点表
4).act_ru_variable    运行时流程变量数据表
3. 历史数据库表history
1).act_hi_actinst      历史节点表
2).act_hi_attachment   历史附件表
3).act_hi_comment      历史意见表
4).act_hi_identitylink 历史流程人员表
5).act_hi_detail       历史详情表，提供历史变量的查询
6).act_hi_procinst     历史流程实例表
7).act_hi_taskinst     历史任务实例表
8).act_hi_varinst      历史变量表
4. 组织机构表
1).act_id_group        用户组信息表
2).act_id_info         用户扩展信息表
3).act_id_membership   用户与用户组对应信息表
4).act_id_user         用户信息表
这四张表很常见，基本的组织机构管理，关于用户认证方面建议还是自己开发一套，组件自带的功能太简单，使用中有很多需求难以满足
5. 通用数据表genetal
1).act_ge_bytearray  二进制数据表
2).act_ge_property   属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录，







