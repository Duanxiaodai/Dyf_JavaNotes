spring共四天
第一天：spring框架的概述以及spring中基于XML的IOC配置
第二天：spring中基于注解的IOC和ioc的案例
第三天：spring中的aop和基于XML以及注解的AOP配置
第四天：spring中的JdbcTemlate以及Spring事务控制
-----------------------------------------------------
1、spring的概述
	spring是什么
	spring的两大核心
	spring的发展历程和优势
	spring体系结构
2、程序的耦合及解耦
	曾经案例中问题
	工厂模式解耦
3、IOC概念和spring中的IOC
	spring中基于XML的IOC环境搭建
4、依赖注入（Dependency Injection）
5、作业：


第一天：spring框架的概述以及spring中基于XML的IOC配置
/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean >  实体类
 *
 *   它就是创建我们的service和dao对象的。
 *
 *   第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   我的配置文件可以是xml也可以是properties
 */
 
 /**
 * 获取spring的Ioc核心容器，并根据id获取对象
 *
 * ApplicationContext的三个常用实现类：
 *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。(更常用)
 *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）
 *
 *      AnnotationConfigApplicationContext：它是用于读取注解创建容器的，是明天的内容。
 *
 * 核心容器的两个接口引发出的问题：
 *  ApplicationContext:     单例对象适用              采用此接口
 *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
 *
 *  BeanFactory:            多例对象使用
 *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
 * @param args
 */
 
     <!--把对象的创建交给spring来管理-->
    <!--spring对bean的管理细节
        1.创建bean的三种方式
        2.bean对象的作用范围
        3.bean对象的生命周期
    -->

    <!--创建Bean的三种方式 -->
    <!-- 第一种方式：使用默认构造函数创建。
            在spring的配置文件中使用bean标签，配以id和class属性之后，且没有其他属性和标签时。
            采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数，则对象无法创建。

    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
    -->

    <!-- 第二种方式： 使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器）
    <bean id="instanceFactory" class="com.itheima.factory.InstanceFactory"></bean>
    <bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"></bean>
    -->

    <!-- 第三种方式：使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器)
    <bean id="accountService" class="com.itheima.factory.StaticFactory" factory-method="getAccountService"></bean>
    -->

    <!-- bean的作用范围调整
        bean标签的scope属性：
            作用：用于指定bean的作用范围
            取值： 常用的就是单例的和多例的
                singleton：单例的（默认值）
                prototype：多例的
                request：作用于web应用的请求范围
                session：作用于web应用的会话范围
                global-session：作用于集群环境的会话范围（全局会话范围），当不是集群环境时，它就是session

    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl" scope="prototype"></bean>
    -->

    <!-- bean对象的生命周期
            单例对象
                出生：当容器创建时对象出生
                活着：只要容器还在，对象一直活着
                死亡：容器销毁，对象消亡
                总结：单例对象的生命周期和容器相同
            多例对象
                出生：当我们使用对象时spring框架为我们创建
                活着：对象只要是在使用过程中就一直活着。
                死亡：当对象长时间不用，且没有别的对象引用时，由Java的垃圾回收器回收
     -->
	 
	 
	 
	 
<!-- spring中的依赖注入
	依赖注入：
		Dependency Injection
	IOC的作用：
		降低程序间的耦合（依赖关系）
	依赖关系的管理：
		以后都交给spring来维护
	在当前类需要用到其他类的对象，由spring为我们提供，我们只需要在配置文件中说明
	依赖关系的维护：
		就称之为依赖注入。
	 依赖注入：
		能注入的数据：有三类
			基本类型和String
			其他bean类型（在配置文件中或者注解配置过的bean）
			复杂类型/集合类型
		 注入的方式：有三种
			第一种：使用构造函数提供
			第二种：使用set方法提供
			第三种：使用注解提供（明天的内容）
 -->


<!--构造函数注入：
	使用的标签:constructor-arg
	标签出现的位置：bean标签的内部
	标签中的属性
		type：用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型
		index：用于指定要注入的数据给构造函数中指定索引位置的参数赋值。索引的位置是从0开始
		name：用于指定给构造函数中指定名称的参数赋值                                        常用的
		=============以上三个用于指定给构造函数中哪个参数赋值===============================
		value：用于提供基本类型和String类型的数据
		ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象

	优势：
		在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。
	弊端：
		改变了bean对象的实例化方式，使我们在创建对象时，如果用不到这些数据，也必须提供。
-->
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
	<constructor-arg name="name" value="泰斯特"></constructor-arg>
	<constructor-arg name="age" value="18"></constructor-arg>
	<constructor-arg name="birthday" ref="now"></constructor-arg>
</bean>

<!-- 配置一个日期对象 -->
<bean id="now" class="java.util.Date"></bean>



<!-- set方法注入                更常用的方式
	涉及的标签：property
	出现的位置：bean标签的内部
	标签的属性
		name：用于指定注入时所调用的set方法名称
		value：用于提供基本类型和String类型的数据
		ref：用于指定其他的bean类型数据。它指的就是在spring的Ioc核心容器中出现过的bean对象
	优势：
		创建对象时没有明确的限制，可以直接使用默认构造函数
	弊端：
		如果有某个成员必须有值，则获取对象是有可能set方法没有执行。
-->
<bean id="accountService2" class="com.itheima.service.impl.AccountServiceImpl2">
	<property name="name" value="TEST" ></property>
	<property name="age" value="21"></property>
	<property name="birthday" ref="now"></property>
</bean>


<!-- 复杂类型的注入/集合类型的注入
	用于给List结构集合注入的标签：
		list array set
	用于个Map结构集合注入的标签:
		map  props
	结构相同，标签可以互换
-->
<bean id="accountService3" class="com.itheima.service.impl.AccountServiceImpl3">
	<property name="myStrs">
		<set>
			<value>AAA</value>
			<value>BBB</value>
			<value>CCC</value>
		</set>
	</property>

	<property name="myList">
		<array>
			<value>AAA</value>
			<value>BBB</value>
			<value>CCC</value>
		</array>
	</property>

	<property name="mySet">
		<list>
			<value>AAA</value>
			<value>BBB</value>
			<value>CCC</value>
		</list>
	</property>

	<property name="myMap">
		<props>
			<prop key="testC">ccc</prop>
			<prop key="testD">ddd</prop>
		</props>
	</property>

	<property name="myProps">
		<map>
			<entry key="testA" value="aaa"></entry>
			<entry key="testB">
				<value>BBB</value>
			</entry>
		</map>
	</property>
</bean>

spring第二天：spring基于注解的IOC以及IoC的案例
1、spring中ioc的常用注解
2、案例使用xml方式和注解方式实现单表的CRUD操作
	持久层技术选择：dbutils
3、改造基于注解的ioc案例，使用纯注解的方式实现
	spring的一些新注解使用
4、spring和Junit整合
/**
 * 账户的业务层实现类
 *
 * 曾经XML的配置：
 *  <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *        scope=""  init-method="" destroy-method="">
 *      <property name=""  value="" | ref=""></property>
 *  </bean>
 *
 * 用于创建对象的
 *      他们的作用就和在XML配置文件中编写一个<bean>标签实现的功能是一样的
 *      Component:
 *          作用：用于把当前类对象存入spring容器中
 *          属性：
 *              value：用于指定bean的id。当我们不写时，它的默认值是当前类名，且首字母改小写。
 *      Controller：一般用在表现层
 *      Service：一般用在业务层
 *      Repository：一般用在持久层
 *      以上三个注解他们的作用和属性与Component是一模一样。
 *      他们三个是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰
 *
 *
 * 用于注入数据的
 *      他们的作用就和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
 *      Autowired:
 *          作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                如果ioc容器中没有任何bean的类型和要注入的变量类型匹配，则报错。
 *                如果Ioc容器中有多个类型匹配时： 匹配成员变量名称与id ？
 *          出现位置：
 *              可以是变量上，也可以是方法上
 *          细节：
 *              在使用注解注入时，set方法就不是必须的了。
 *      Qualifier:
 *          作用：在按照类中注入的基础之上再按照名称注入。它在给类成员注入时不能单独使用。但是在给方法参数注入时可以（稍后我们讲）
 *          属性：
 *              value：用于指定注入bean的id。
 *      Resource
 *          作用：直接按照bean的id注入。它可以独立使用
 *          属性：
 *              name：用于指定bean的id。
 *      以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *      另外，集合类型的注入只能通过XML来实现。
 *
 *      Value
 *          作用：用于注入基本类型和String类型的数据
 *          属性：
 *              value：用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式）
 *                      SpEL的写法：${表达式}
 *
 * 用于改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值。常用取值：singleton prototype
 *
 * 和生命周期相关 了解
 *      他们的作用就和在bean标签中使用init-method和destroy-methode的作用是一样的
 *      PreDestroy
 *          作用：用于指定销毁方法
 *      PostConstruct
 *          作用：用于指定初始化方法
 */
 //注解与xml配置混合
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 告知spring在创建容器时要扫描的包 -->
    <context:component-scan base-package="com.itheima"></context:component-scan>
    <!--配置QueryRunner-->
    <bean id="runner" class="org.apache.commons.dbutils.QueryRunner" scope="prototype">
        <!--注入数据源-->
        <constructor-arg name="ds" ref="dataSource"></constructor-arg>
    </bean>

    <!-- 配置数据源 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!--连接数据库的必备信息-->
        <property name="driverClass" value="com.mysql.cj.jdbc.Driver"></property>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/eesy?serverTimezone=UTC"></property>
        <property name="user" value="root"></property>
        <property name="password" value="duanyuefeng"></property>
    </bean>
</beans>


	
去掉xml配置文件,纯注解（并没有省事）可以在自己开发的包中用注解，jar外部包的依赖用xml文件来配置
/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * spring中的新注解
 * Configuration
 *     作用：指定当前类是一个配置类
 *     细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写。
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包。
 *                 我们使用此注解就等同于在xml中配置了:
 *                      <context:component-scan base-package="com.itheima"></context:component-scan>
 *  Bean
 *      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性:
 *          name:用于指定bean的id。当不写时，默认值是当前方法的名称
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象。
 *          查找的方式和Autowired注解的作用是一样的
 *  Import
 *      作用：用于导入其他的配置类
 *      属性：
 *          value：用于指定其他配置类的字节码。
 *                  当我们使用Import的注解之后，有Import注解的类就父配置类，而导入的都是子配置类
 *  PropertySource
 *      作用：用于指定properties文件的位置
 *      属性：
 *          value：指定文件的名称和路径。
 *                  关键字：classpath，表示类路径下
 */
//@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {


}


Spring 整合 junit
1、应用程序的入口
	main方法
2、junit单元测试中，没有main方法也能执行
	junit集成了一个main方法
	该方法就会判断当前测试类中哪些方法有 @Test注解
	junit就让有Test注解的方法执行
3、junit不会管我们是否采用spring框架
	在执行测试方法时，junit根本不知道我们是不是使用了spring框架
	所以也就不会为我们读取配置文件/配置类创建spring核心容器
4、由以上三点可知
	当测试方法执行时，没有Ioc容器，就算写了Autowired注解，也无法实现注入
	
/**
 * 使用Junit单元测试：测试我们的配置
 * Spring整合junit的配置
 *      1、导入spring整合junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *             @Runwith
 *      3、告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *                  locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在地位置
 *
 *   当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上
 */


Spring 第三天 
1、完善我们的account案例
2、分析案例中问题
3、回顾之前讲过的一个技术：## 动态代理
4、动态代理另一种实现方式
5、解决案例中的问题
6、AOP的概念
7、spring中的AOP相关术语
8、spring中基于XML和注解的AOP配置


/**
 * 动态代理：
 *  特点：字节码随用随创建，随用随加载
 *  作用：不修改源码的基础上对方法增强
 *  分类：
 *      基于接口的动态代理
 *      基于子类的动态代理
 *  基于接口的动态代理：
 *      涉及的类：Proxy
 *      提供者：JDK官方
 *  如何创建代理对象：
 *      使用Proxy类中的newProxyInstance方法
 *  创建代理对象的要求：
 *      被代理类最少实现一个接口，如果没有则不能使用
 *  newProxyInstance方法的参数：
 *      ClassLoader：类加载器
 *          它是用于加载代理对象字节码的。和被代理对象使用相同的类加载器。固定写法。
 *      Class[]：字节码数组
 *          它是用于让代理对象和被代理对象有相同方法。固定写法。
 *      InvocationHandler：用于提供增强的代码
 *          它是让我们写如何代理。我们一般都是写一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
 *          此接口的实现类都是谁用谁写。
 */
 
 /**
 * 动态代理2：
 *  特点：字节码随用随创建，随用随加载
 *  作用：不修改源码的基础上对方法增强
 *  分类：
 *      基于接口的动态代理
 *      基于子类的动态代理
 *  基于子类的动态代理：
 *      涉及的类：Enhancer
 *      提供者：第三方cglib库
 *  如何创建代理对象：
 *      使用Enhancer类中的create方法
 *  创建代理对象的要求：
 *      被代理类不能是最终类
 *  create方法的参数：
 *      Class：字节码
 *          它是用于指定被代理对象的字节码。
 *
 *      Callback：用于提供增强的代码
 *          它是让我们写如何代理。我们一般都是些一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。
 *          此接口的实现类都是谁用谁写。
 *          我们一般写的都是该接口的子接口实现类：MethodInterceptor
 */


 
 
 
 
 AOP xml配置
 <?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置srping的Ioc,把service对象配置进来-->
    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>

    <!--spring中基于XML的AOP配置步骤
        1、把通知Bean也交给spring来管理
        2、使用aop:config标签表明开始AOP的配置
        3、使用aop:aspect标签表明配置切面
                id属性：是给切面提供一个唯一标识
                ref属性：是指定通知类bean的Id。
        4、在aop:aspect标签的内部使用对应标签来配置通知的类型
               我们现在示例是让printLog方法在切入点方法执行之前之前：所以是前置通知
               aop:before：表示配置前置通知
                    method属性：用于指定Logger类中哪个方法是前置通知
                    pointcut属性：用于指定切入点表达式，该表达式的含义指的是对业务层中哪些方法增强

            切入点表达式的写法：
                关键字：execution(表达式)
                表达式：
                    访问修饰符  返回值  包名.包名.包名...类名.方法名(参数列表)
                标准的表达式写法：
                    public void com.itheima.service.impl.AccountServiceImpl.saveAccount()
                访问修饰符可以省略
                    void com.itheima.service.impl.AccountServiceImpl.saveAccount()
                返回值可以使用通配符，表示任意返回值
                    * com.itheima.service.impl.AccountServiceImpl.saveAccount()
                包名可以使用通配符，表示任意包。但是有几级包，就需要写几个*.
                    * *.*.*.*.AccountServiceImpl.saveAccount())
                包名可以使用..表示当前包及其子包
                    * *..AccountServiceImpl.saveAccount()
                类名和方法名都可以使用*来实现通配
                    * *..*.*()
                参数列表：
                    可以直接写数据类型：
                        基本类型直接写名称           int
                        引用类型写包名.类名的方式   java.lang.String
                    可以使用通配符表示任意类型，但是必须有参数
                    可以使用..表示有无参数均可，有参数可以是任意类型
                全通配写法：
                    * *..*.*(..)

                实际开发中切入点表达式的通常写法：
                    切到业务层实现类下的所有方法
                        * com.itheima.service.impl.*.*(..)
    -->

    <!-- 配置Logger类 -->
    <bean id="logger" class="com.itheima.utils.Logger"></bean>

    <!--配置AOP-->
    <aop:config>
        <!--配置切面 -->
        <aop:aspect id="logAdvice" ref="logger">
            <!-- 配置通知的类型，并且建立通知方法和切入点方法的关联-->
            <aop:before method="printLog" pointcut="execution(* com.itheima.service.impl.*.*(..))"></aop:before>
        </aop:aspect>
    </aop:config>

</beans>



四个通知类型 以及 环绕通知
<!--配置AOP-->
<aop:config>
	<!-- 配置切入点表达式 id属性用于指定表达式的唯一标识。expression属性用于指定表达式内容
		  此标签写在aop:aspect标签内部只能当前切面使用。
		  它还可以写在aop:aspect外面，此时就变成了所有切面可用
	  -->
	<aop:pointcut id="pt1" expression="execution(* com.itheima.service.impl.*.*(..))"></aop:pointcut>
	<!--配置切面 -->
	<aop:aspect id="logAdvice" ref="logger">
		<!-- 配置前置通知：在切入点方法执行之前执行
		<aop:before method="beforePrintLog" pointcut-ref="pt1" ></aop:before>-->

		<!-- 配置后置通知：在切入点方法正常执行之后值。它和异常通知永远只能执行一个
		<aop:after-returning method="afterReturningPrintLog" pointcut-ref="pt1"></aop:after-returning>-->

		<!-- 配置异常通知：在切入点方法执行产生异常之后执行。它和后置通知永远只能执行一个
		<aop:after-throwing method="afterThrowingPrintLog" pointcut-ref="pt1"></aop:after-throwing>-->

		<!-- 配置最终通知：无论切入点方法是否正常执行它都会在其后面执行
		<aop:after method="afterPrintLog" pointcut-ref="pt1"></aop:after>-->

		<!-- 配置环绕通知 详细的注释请看Logger类中-->
		<aop:around method="aroundPringLog" pointcut-ref="pt1"></aop:around>
	</aop:aspect>
</aop:config>


/**
 * 环绕通知
 * 问题：
 *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
 * 分析：
 *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
 * 解决：
 *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
 *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
 *
 * spring中的环绕通知：
 *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
 */
public Object aroundPringLog(ProceedingJoinPoint pjp){
	Object rtValue = null;
	try{
		Object[] args = pjp.getArgs();//得到方法执行所需的参数

		System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");

		rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）

		System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");

		return rtValue;
	}catch (Throwable t){
		System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
		throw new RuntimeException(t);
	}finally {
		System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
	}
}


AOP 注解配置   （Spring 中的注解配置 后置 与 最终 通知  会有bug?? 顺序不同    建议使用环绕通知）
xml 文件中内容的替换
<!-- 配置spring创建容器时要扫描的包-->
<context:component-scan base-package="com.itheima"></context:component-scan>

<!-- 配置spring开启注解AOP的支持 -->
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>


/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 前置通知
     */
//    @Before("pt1()")
    public  void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }

    /**
     * 后置通知
     */
//    @AfterReturning("pt1()")
    public  void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }
    /**
     * 异常通知
     */
//    @AfterThrowing("pt1()")
    public  void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }

    /**
     * 最终通知
     */
//    @After("pt1()")
    public  void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    @Around("pt1()")
    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            Object[] args = pjp.getArgs();//得到方法执行所需的参数

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");

            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");

            return rtValue;
        }catch (Throwable t){
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
            throw new RuntimeException(t);
        }finally {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
        }
    }
}

第四天 
1、spring中的JdbcTemplate
	JdbcTemplate的作用：
		它就是用于和数据库交互的，实现对表的CRUD操作
	如何创建该对象：
	对象中的常用方法：
2、作业：
	spring基于AOP的事务控制
3、spring中的事务控制
	基于XML的
	基于注解的
	
	
	
spring 事务控制xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置业务层-->
    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!-- 配置账户的持久层-->
    <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl">
        <property name="dataSource" ref="dataSource"></property>
    </bean>


    <!-- 配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/eesy"></property>
        <property name="username" value="root"></property>
        <property name="password" value="1234"></property>
    </bean>

    <!-- spring中基于XML的声明式事务控制配置步骤
        1、配置事务管理器
        2、配置事务的通知
                此时我们需要导入事务的约束 tx名称空间和约束，同时也需要aop的
                使用tx:advice标签配置事务通知
                    属性：
                        id：给事务通知起一个唯一标识
                        transaction-manager：给事务通知提供一个事务管理器引用
        3、配置AOP中的通用切入点表达式
        4、建立事务通知和切入点表达式的对应关系
        5、配置事务的属性
               是在事务的通知tx:advice标签的内部

     -->
    <!-- 配置事务管理器 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!-- 配置事务的通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!-- 配置事务的属性
                isolation：用于指定事务的隔离级别。默认值是DEFAULT，表示使用数据库的默认隔离级别。
                propagation：用于指定事务的传播行为。默认值是REQUIRED，表示一定会有事务，增删改的选择。查询方法可以选择SUPPORTS。
                read-only：用于指定事务是否只读。只有查询方法才能设置为true。默认值是false，表示读写。
                timeout：用于指定事务的超时时间，默认值是-1，表示永不超时。如果指定了数值，以秒为单位。
                rollback-for：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时，事务不回滚。没有默认值。表示任何异常都回滚。
                no-rollback-for：用于指定一个异常，当产生该异常时，事务不回滚，产生其他异常时事务回滚。没有默认值。表示任何异常都回滚。
        -->
        <tx:attributes>
            <tx:method name="*" propagation="REQUIRED" read-only="false"/>
            <tx:method name="find*" propagation="SUPPORTS" read-only="true"></tx:method>
        </tx:attributes>
    </tx:advice>

    <!-- 配置aop-->
    <aop:config>
        <!-- 配置切入点表达式-->
        <aop:pointcut id="pt1" expression="execution(* com.itheima.service.impl.*.*(..))"></aop:pointcut>
        <!--建立切入点表达式和事务通知的对应关系 -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pt1"></aop:advisor>
    </aop:config>
</beans>


spring 事务控制 anno
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置spring创建容器时要扫描的包-->
    <context:component-scan base-package="com.itheima"></context:component-scan>

    <!-- 配置JdbcTemplate-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>



    <!-- 配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/eesy"></property>
        <property name="username" value="root"></property>
        <property name="password" value="1234"></property>
    </bean>

    <!-- spring中基于注解 的声明式事务控制配置步骤
        1、配置事务管理器
        2、开启spring对注解事务的支持
        3、在需要事务支持的地方使用@Transactional注解


     -->
    <!-- 配置事务管理器 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>



    <!-- 开启spring对注解事务的支持-->
    <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>

</beans>


纯注解配置






最终的问题   ----   spring 自己的数据源    声明世事务控制，（内部怎么做到的connextion保持一直）   也就是可能是自己实现的与当前线程绑定？？？

既然jdbcTemplate每次执行一个操作的时候都是从连接池中获取connection，那么spring事务管理是怎么实现事务一致性的呢？

问题解决 
DataSourceTransactionManager 内部是实现 很可能也是DataSourceUtils来做的
Spring提供一个能从当前事务上下文中获取绑定的数据连接的工具类，即DataSourceUtils。Spring强调必须使用DataSourceUtils获取数据库连接，Spring的JdbcTemplate内部也是通过DataSourceUtils来获取连接的。DataSourceUtils提供了若干获取和释放数据连接的静态方法，

所以就说的通了

