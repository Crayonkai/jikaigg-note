# Transactional注解

表中数据情况：

| id   | name       | age  |
| ---- | ---------- | ---- |
| 1    | yaojikai   | 18   |
| 2    | zhangdapao | 20   |
| 3    | lisi       | 15   |

### 情况一:

```java
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
	public String A() {
    	userMapper.deleteById(1L);
        int i = 1 / 0;
        return "success";
    }
}
```

分析：

* 没有添加Transactional注解，id为1得数据会被删除

### 情况二：

```java
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Transactional
	public String A() {
    	userMapper.deleteById(1L);
        int i = 1 / 0;
        return "success";
    }
}
```

分析：

* 添加Transactional注解，1 / 0发生“/ by zero”异常，事务进行回滚，id为1得数据不会被删除

### 情况三：

```java
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
	@Transactional
    public String A() {
        userMapper.deleteById(2L);
        B();
        return "success";

    }

    @Transactional
    public String B(){
        userMapper.deleteById(3L);
        int i = 1 / 0;
        return "success";
    }
}
```

分析：

* A方法无异常，B方法有异常，A方法中的id为2得数据也不会删除成功，默认Transactional事物的传播等级为TRANSACTIONAL_REQUIRED，A方法和B方法会共用一个事务。

### 情况四：

```java
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
	@Transactional
    // @Transactional(rollbackFor = Exception.class)
    public String A() throws Exception {
        userMapper.deleteById(2L);
        try {
            int i = 1 / 0;
        }catch (ArithmeticException a){
            throw new Exception("aaa");
        }
        return "success";
    }
}
```

分析：

* A方法有加Transactional注解，但是方法1/0抛出的ArithmeticException是继承RuntimeException，但在方法中对异常进行了捕获。并抛出了Exception得异常，Transactional默认捕获的是RuntimeException。如果想要回滚，需要改成注释的样式。

### 情况五：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况：

```java
```

### 情况二:

```java
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
	public String A() {
    	this.B();
        return "success";
    }

	@Transactional
	public String B(){
	   userMapper.deleteById(1L);
 	   int i = 1 / 0;
  	  return "success";
	}
}
```

分析：

* A调用B方法，B加了注解，出现了在同一个类中自己调用自己的方法的情况，此时B方法加不加Transactional注解都不会被事务管理。事务会失效。

### 总结：

1. PROPAGATION_REQUIRED ，默认的spring事务传播级别，使用该级别的特点是，如果上下文中已经存在事务，那么就加入到事务中执行，如果当前上下文中不存在事务，则新建事务执行。所以这个级别通常能满足处理大多数的业务场景。
2. PROPAGATION_SUPPORTS ，从字面意思就知道，supports，支持，该传播级别的特点是，如果上下文存在事务，则支持事务加入事务，如果没有事务，则使用非事务的方式执行。所以说，并非所有的包在transactionTemplate.execute中的代码都会有事务支持。这个通常是用来处理那些并非原子性的非核心业务逻辑操作。应用场景较少。    
3. PROPAGATION_MANDATORY ， 该级别的事务要求上下文中必须要存在事务，否则就会抛出异常！配置该方式的传播级别是有效的控制上下文调用代码遗漏添加事务控制的保证手段。比如一段代码不能单独被调用执行，但是一旦被调用，就必须有事务包含的情况，就可以使用这个传播级别。
4. PROPAGATION_REQUIRES_NEW ，从字面即可知道，new，每次都要一个新事务，该传播级别的特点是，每次都会新建一个事务，并且同时将上下文中的事务挂起，执行当前新建事务完成以后，上下文事务恢复再执行。
5. PROPAGATION_NOT_SUPPORTED ，这个也可以从字面得知，not supported ，不支持，当前级别的特点就是上下文中存在事务，则挂起事务，执行当前逻辑，结束后恢复上下文的事务。
6. PROPAGATION_NEVER ，该事务更严格，上面一个事务传播级别只是不支持而已，有事务就挂起，而PROPAGATION_NEVER传播级别要求上下文中不能存在事务，一旦有事务，就抛出runtime异常，强制停止执行！这个级别上辈子跟事务有仇。
7. PROPAGATION_NESTED ，字面也可知道，nested，嵌套级别事务。该传播级别特征是，如果上下文中存在事务，则嵌套事务执行，如果不存在事务，则新建事务