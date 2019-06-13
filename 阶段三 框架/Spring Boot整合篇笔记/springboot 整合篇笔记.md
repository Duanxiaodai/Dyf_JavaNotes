## 1.缓存  Redis



```java
/**
 * 一、搭建基本环境
 * 1、导入数据库文件 创建出department和employee表
 * 2、创建javaBean封装数据
 * 3、整合MyBatis操作数据库
 *        1.配置数据源信息
 *        2.使用注解版的MyBatis；
 *           1）、@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *        步骤：
 *           1、开启基于注解的缓存 @EnableCaching
 *           2、标注缓存注解即可
 *              @Cacheable
 *              @CacheEvict
 *              @CachePut
 * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在  ConcurrentMap<Object, Object>中
 * 开发中使用缓存中间件；redis、memcached、ehcache；
 * 三、整合redis作为缓存
 * Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 *     1、安装redis：使用docker；
 *     2、引入redis的starter
 *     3、配置redis
 *     4、测试缓存
 *        原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 *    1）、引入redis的starter，容器中保存的是 RedisCacheManager；
 *    2）、RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache通过操作redis缓存数据的
 *    3）、默认保存数据 k-v 都是Object；利用序列化保存；如何保存为json
 *           1、引入了redis的starter，cacheManager变为 RedisCacheManager；
 *           2、默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object, Object>
 *           3、RedisTemplate<Object, Object> 是 默认使用jdk的序列化机制
 *      4）、自定义CacheManager；
 *
 */
```



#### 1.1示例与说明

```java
import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames="emp"/*,cacheManager = "employeeCacheManager"*/) //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用调用方法；
     * CacheManager管理多个Cache组件的，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；
     *

     *
     * 原理：
     *   1、自动配置类；CacheAutoConfiguration
     *   2、缓存的配置类
     *   org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *   org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *   3、哪个配置类默认生效：SimpleCacheConfiguration；
     *
     *   4、给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *   5、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；
     *
     *   运行流程：
     *   @Cacheable：
     *   1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     *      （CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
     *   2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     *      key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     *          SimpleKeyGenerator生成key的默认策略；
     *                  如果没有参数；key=new SimpleKey()；
     *                  如果有一个参数：key=参数的值
     *                  如果有多个参数：key=new SimpleKey(params)；
     *   3、没有查到缓存就调用目标方法；
     *   4、将目标方法返回的结果，放进缓存中
     *
     *   @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *   如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *
     *   核心：
     *      1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     *
     *   几个属性：
     *      cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     *
     *      key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
     *              编写SpEL； #i d;参数id的值   #a0  #p0  #root.args[0]
     *              getEmp[2]
     *
     *      keyGenerator：key的生成器；可以自己指定key的生成器的组件id
     *              key/keyGenerator：二选一使用;
     *
     *
     *      cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器
     *
     *      condition：指定符合条件的情况下才缓存；
     *              ,condition = "#id>0"
     *          condition = "#a0>1"：第一个参数的值》1的时候才进行缓存
     *
     *      unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *              unless = "#result == null"
     *              unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
     *      sync：是否使用异步模式
     * @param id
     * @return
     *
     */
    @Cacheable(value = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1号员工；查到的结果会放在缓存中；
     *          key：1  value：lastName：张三
     *  2、以后查询还是之前的结果
     *  3、更新1号员工；【lastName:zhangsan；gender:0】
     *          将方法的返回值也放进缓存了；
     *          key：传入的employee对象  值：返回的employee对象；
     *  4、查询1号员工？
     *      应该是更新后的员工；
     *          key = "#employee.id":使用传入的参数的员工id；
     *          key = "#result.id"：使用返回后的id
     *             @Cacheable的key是不能用#result
     *      为什么是没更新前的？【1号员工没有在缓存中更新】
     *
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除
     *  key：指定要清除的数据
     *  allEntries = true：指定清除这个缓存中所有的数据
     *  beforeInvocation = false：缓存的清除是否在方法之前执行
     *      默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     *
     *  beforeInvocation = true：
     *      代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     *
     *
     */
    @CacheEvict(value="emp",beforeInvocation = true/*key = "#id",*/)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
        //employeeMapper.deleteEmpById(id);
        int i = 10/0;
    }

    // @Caching 定义复杂的缓存规则
    @Caching(
         cacheable = {
             @Cacheable(/*value="emp",*/key = "#lastName")
         },
         put = {
             @CachePut(/*value="emp",*/key = "#result.id"),
             @CachePut(/*value="emp",*/key = "#result.email")
         }
    )
    public Employee getEmpByLastName(String lastName){

        return employeeMapper.getEmpByLastName(lastName);
    }
}
```





切换cachemanager 

```java
import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;


@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

//    @Qualifier("deptCacheManager")
//    @Autowired
//    RedisCacheManager deptCacheManager;


    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        System.out.println(department+"....");
        return department;
    }

    // 使用缓存管理器得到缓存，进行api调用
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门"+id);
//        Department department = departmentMapper.getDeptById(id);
//
//        //获取某个缓存
//        Cache dept = deptCacheManager.getCache("dept");
//        dept.put("dept:1",department);
//
//        return department;
//    }


}
```







#### 1.2Redis的配置与使用

```java
   /**
    * Redis常见的五大数据类型
    *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
    *  stringRedisTemplate.opsForValue()[String（字符串）]
    *  stringRedisTemplate.opsForList()[List（列表）]
    *  stringRedisTemplate.opsForSet()[Set（集合）]
    *  stringRedisTemplate.opsForHash()[Hash（散列）]
    *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
    */
   @Test
   public void test01(){
      //给redis中保存数据
       //stringRedisTemplate.opsForValue().append("msg","hello");
//    String msg = stringRedisTemplate.opsForValue().get("msg");
//    System.out.println(msg);

//    stringRedisTemplate.opsForList().leftPush("mylist","1");
//    stringRedisTemplate.opsForList().leftPush("mylist","2");
   }

   //测试保存对象
   @Test
   public void test02(){
      Employee empById = employeeMapper.getEmpById(1);
      //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
      //redisTemplate.opsForValue().set("emp-01",empById);
      //1、将数据以json的方式保存
       //(1)自己将对象转为json
       //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
      empRedisTemplate.opsForValue().set("emp-01",empById);
   }



   @Test
   public void contextLoads() {

      Employee empById = employeeMapper.getEmpById(1);
      System.out.println(empById);

   }
```



Redis的自定义配置，RedisTemplate，RedisCacheManager

注意：如果是缺省的序列化，需要实体类实现序列化接口Serializable

```java
import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }
    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> ser = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(ser);
        return template;
    }
    //CacheManagerCustomizers可以来定制缓存的一些规则
    @Primary  //将某个缓存管理器作为默认的
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
        //key多了一个前缀
        //使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
        //key多了一个前缀
        //使用前缀，默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
```

#### 1.3CURD标准的项目结构

![1560223661954](images\1560223661954.png)

## 2.消息 RabbitMQ

```java
/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂ConnectionFactory；
 *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
 *     AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 *
 */
```

```properties
spring.rabbitmq.host=10.112.163.217
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```



测试

```java

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

   @Autowired
   RabbitTemplate rabbitTemplate;

   @Autowired
   AmqpAdmin amqpAdmin;

   @Test
   public void createExchange(){

//    amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//    System.out.println("创建完成");

//    amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
      //创建绑定规则

//    amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));

      //amqpAdmin.de
   }

   /**
    * 1、单播（点对点）
    */
   @Test
   public void contextLoads() {
      //Message需要自己构造一个;定义消息体内容和消息头
      //rabbitTemplate.send(exchage,routeKey,message);

      //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
      //rabbitTemplate.convertAndSend(exchage,routeKey,object);
      Map<String,Object> map = new HashMap<>();
      map.put("msg","这是第一个消息");
      map.put("data", Arrays.asList("helloworld",123,true));
      //对象被默认序列化以后发送出去
      rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));

   }

   //接受数据,如何将数据自动的转为json发送出去
   @Test
   public void receive(){
      Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
      System.out.println(o.getClass());
      System.out.println(o);
   }

   /**
    * 广播
    */
   @Test
   public void sendMsg(){
      rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
   }

}
```

## 3.检索 ElasticSearch 

```java
/**
 * SpringBoot默认支持两种技术来和ES交互；
 * 1、Jest（默认不生效）
 *     需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch【ES版本有可能不合适】
 *        版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *    如果版本不适配：2.4.6
 *       1）、升级SpringBoot版本
 *       2）、安装对应版本的ES
 *
 *        1）、Client 节点信息clusterNodes；clusterName
 *        2）、ElasticsearchTemplate 操作es
 *    3）、编写一个 ElasticsearchRepository 的子接口来操作ES；
 * 两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 * 1）、编写一个 ElasticsearchRepository
 */
```

```properties
spring.elasticsearch.jest.uris=http://118.24.44.169:9200

spring.data.elasticsearch.cluster-name=elasticsearch
spring.data.elasticsearch.cluster-nodes=118.24.44.169:9301
```



类似与jpa的方式进行crud操作

```java
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {

    //参照官方文档   -->有方法命名与 对应的查询条件的关系
    // https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
   public List<Book> findByBookNameLike(String bookName);

}
```



## 4.任务

#### 4.1异步任务

在Java应用中，绝大多数情况下都是通过同步的方式来实现交互处理的；但是在处理与第三方系统交互的时候，容易造成响应迟缓的情况，之前大部分都是使用多线程来完成此类任务，其实，在Spring 3.x之后，就已经内置了@Async来完美解决这个问题。

两个注解：

**@EnableAysnc、@Aysnc**

```java
@Async//  开启一个线程池去调用该方法，变为异步
public void hello(){
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("处理数据中...");
}

@EnableAsync  //开启异步注解功能
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
public class Springboot04TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04TaskApplication.class, args);
	}
}
```



#### 4.2定时任务 



项目开发中经常需要执行一些定时任务，比如需要在每天凌晨时候，分析一次前一天的日志信息。Spring为我们提供了异步执行任务调度的方式，提供TaskExecutor 、TaskScheduler 接口。

**两个注解：@EnableScheduling、@Scheduled**

**cron表达式：**

```java
 /**
  * second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
  * 0 * * * * MON-FRI
  *  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
  *  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
  *  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
  *  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
  *  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
  */
// @Scheduled(cron = "0 * * * * MON-SAT")
 //@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
// @Scheduled(cron = "0-4 * * * * MON-SAT")
 @Scheduled(cron = "0/4 * * * * MON-SAT")  //每4秒执行一次
 public void hello(){
     System.out.println("hello ... ");
 }
```

![1560303556379](images\1560303556379.png)



#### 4.3 邮件任务

•邮件发送需要引入spring-boot-starter-mail

•Spring Boot 自动配置MailSenderAutoConfiguration

•定义MailProperties内容，配置在application.yml中

•自动装配JavaMailSender

•测试邮件发送

```properties
spring.mail.username=805236403@qq.com
spring.mail.password=gtstkoszjelabijb
spring.mail.host=smtp.qq.com
spring.mail.properties.mail.smtp.ssl.enable=true
```

```java
@Test
public void contextLoads() {
   SimpleMailMessage message = new SimpleMailMessage();
   //邮件设置
   message.setSubject("通知-今晚开会");
   message.setText("今晚7:30开会");

   message.setTo("17512080612@163.com");
   message.setFrom("534096094@qq.com");

   mailSender.send(message);
}
```

## 5.安全

Spring Security是针对Spring项目的安全框架，也是Spring Boot底层安全模块默认的技术选型。他可以实现强大的web安全控制。对于安全控制，我们仅需引入spring-boot-starter-security模块，进行少量的配置，即可实现强大的安全管理。
 几个类：

WebSecurityConfigurerAdapter：自定义Security策略

AuthenticationManagerBuilder：自定义认证策略

@EnableWebSecurity：开启WebSecurity模式



**•应用程序的两个主要区域是“认证”和“授权”**（或者访问控制）。这两个主要区域是Spring Security 的两个目标。

•“认证”（Authentication），是建立一个他声明的主体的过程（一个“主体”一般是指用户，设备或一些可以在你的应用程序中执行动作的其他系统）。

•“授权”（Authorization）指确定一个主体是否允许在你的应用程序执行一个动作的过程。为了抵达需要授权的店，主体的身份已经有认证过程建立。

•这个概念是通用的而不只在Spring Security中。

```java
/**
 * 1、引入SpringSecurity；
 * 2、编写SpringSecurity的配置类；
 *        @EnableWebSecurity   extends WebSecurityConfigurerAdapter
 * 3、控制请求的访问权限：
 *        configure(HttpSecurity http) {
 *           http.authorizeRequests().antMatchers("/").permitAll()
 *              .antMatchers("/level1/**").hasRole("VIP1")
 *        }
 * 4、定义认证规则：
 *        configure(AuthenticationManagerBuilder auth){
 *           auth.inMemoryAuthentication()
 *              .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
 *        }
 * 5、开启自动配置的登陆功能：
 *        configure(HttpSecurity http){
 *           http.formLogin();
 *        }
 * 6、注销：http.logout();
 * 7、记住我：Remeberme()；
 */
```

```java
@EnableWebSecurity
{
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @Documented
    @Import({WebSecurityConfiguration.class, SpringWebMvcImportSelector.class})
    @EnableGlobalAuthentication
    @Configuration
    public @interface EnableWebSecurity {
        boolean debug() default false;
    }
}
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆


        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1","VIP3");

    }
}
```

## 6.分布式

### 6.1dubbo zookeeper

RPC 调用

步骤1  服务提供者

```java
/**
 * 1、将服务提供者注册到注册中心
 *         1、引入dubbo和zkclient相关依赖
 *         2、配置dubbo的扫描包和注册中心地址
 *         3、使用@Service发布服务
 */
```

```properties
dubbo.application.name=provider-ticket

dubbo.registry.address=zookeeper://10.112.163.217:2181

dubbo.scan.base-packages=com.atguigu.ticket.service
```

```java
provider-ticket 项目
package com.atguigu.ticket.service;
import com.alibaba.dubbo.config.annotation.Service; //这里是dubbo的注解 发布服务
import org.springframework.stereotype.Component;
@Component
@Service //将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
```

步骤2 服务调用者

```java
/**
 * 1、引入依赖‘
 * 2、配置dubbo的注册中心地址
 * 3、引用服务
 */
```

```properties
dubbo.application.name=consumer-user

dubbo.registry.address=zookeeper://10.112.163.217:2181
```

```java
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.ticket.service.TicketService;  //需要复制一份全类名完全相同的接口
import org.springframework.stereotype.Service;  //这里是spring 的注解
@Service
public class UserService{
    @Reference//远程引用服务   
    TicketService ticketService;//按照全类名进行匹配服务
    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了："+ticket);
    }
}
```

### 6.2Spring cloud

**springcloud 在整合微服务的时候是通过轻量级 http 进行通信的**

服务1 Eureka服务中心

```java
/**
 * 注册中心
 * 1、配置Eureka信息
 * 2、@EnableEurekaServer
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(EurekaServerApplication.class, args);
   }
}
```

```properties
server:
  port: 8761
eureka:
  instance:
    hostname: eureka-server  # eureka实例的主机名
  client:
    register-with-eureka: false #不把自己注册到eureka上
    fetch-registry: false #不从eureka上来获取服务的注册信息
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

服务提供者配置

```properties
server:
  port: 8001
spring:
  application:
    name: provider-ticket


eureka:
  instance:
    prefer-ip-address: true # 注册服务的时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

服务调用者配置

```properties
spring:
  application:
    name: consumer-user
server:
  port: 8200
eureka:
  instance:
    prefer-ip-address: true # 注册服务的时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

```java
@EnableDiscoveryClient //开启发现服务功能
@SpringBootApplication
public class ConsumerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerUserApplication.class, args);
	}

	@LoadBalanced //使用负载均衡机制
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
```

```java
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;//http远程调用工具

    @GetMapping("/buy")
    public String buyTicket(String name){
        //远程调用
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name+"购买了"+s;
    }
}
```

## 7.SpringBoot开发热部署

ctrl+f9 重新构建

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-devtools</artifactId>
   <optional>true</optional>
</dependency>
```
## 8.监管

通过引入spring-boot-starter-actuator，可以使用Spring Boot为我们提供的准生产环境下的应用监控和管理功能。我们可以通过HTTP，JMX，SSH协议来进行操作，自动得到审计、健康及指标信息等

•步骤：

–引入spring-boot-starter-actuator

–通过http方式访问监控端点

–可进行shutdown（POST 提交，此端点默认关闭）



•监控和管理端点  

| 端点名      | 描述                        |
| ----------- | --------------------------- |
| autoconfig  | 所有自动配置信息            |
| auditevents | 审计事件                    |
| beans       | 所有Bean的信息              |
| configprops | 所有配置属性                |
| dump        | 线程状态信息                |
| env         | 当前环境信息                |
| health      | 应用健康状况                |
| info        | 当前应用信息                |
| metrics     | 应用的各项指标              |
| mappings    | 应用@RequestMapping映射路径 |
| shutdown    | 关闭当前应用（默认关闭）    |
| trace       | 追踪信息（最新的http请求）  |

**定制端点信息**

–定制端点一般通过endpoints+端点名+属性名来设置。

–修改端点id（endpoints.beans.id=mybeans）

–开启远程应用关闭功能（endpoints.shutdown.enabled=true）

–关闭端点（endpoints.beans.enabled=false）

–开启所需端点

•endpoints.enabled=false

•endpoints.beans.enabled=true

–定制端点访问根路径

•management.context-path=/manage

–关闭http端点

•management.port=-1



**给自己的服务定义健康指示器**

```java
/**
 * 自定义健康状态指示器
 * 1、编写一个指示器 实现 HealthIndicator 接口
 * 2、指示器的名字 xxxxHealthIndicator
 * 3、加入容器中
 */
```

```java
@Component
public class MyAppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        //自定义的检查方法
        //Health.up().build()代表健康
        return Health.down().withDetail("msg","服务异常").build();
    }
}
```