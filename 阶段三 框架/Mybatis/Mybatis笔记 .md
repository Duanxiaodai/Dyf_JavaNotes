mybatis框架
共四天
第一天：mybatis入门
	mybatis的概述
	mybatis的环境搭建
	mybatis入门案例
	自定义mybatis框架（主要的目的是为了让大家了解mybatis中执行细节）
第二天：mybatis基本使用
	mybatis的单表crud操作
	mybatis的参数和返回值
	mybatis的dao编写
	mybatis配置的细节
		几个标签的使用
第三天：mybatis的深入和多表
	mybatis的连接池
	mybatis的事务控制及设计的方法
	mybatis的多表查询
		一对多（多对一）
		多对多
第四天：mybatis的缓存和注解开发
	mybatis中的加载时机（查询的时机）
	mybatis中的一级缓存和二级缓存
	mybatis的注解开发
		单表CRUD
		多表查询
-----------------------------------------------------------
第一天：mybatis入门  
1、什么是框架？
	它是我们软件开发中的一套解决方案，不同的框架解决的是不同的问题。
	使用框架的好处：
		框架封装了很多的细节，使开发者可以使用极简的方式实现功能。大大提高开发效率。
2、三层架构
	表现层：
		是用于展示数据的
	业务层：
		是处理业务需求
	持久层：
		是和数据库交互的
3、持久层技术解决方案
	JDBC技术：
		Connection
		PreparedStatement
		ResultSet
	Spring的JdbcTemplate：
		Spring中对jdbc的简单封装
	Apache的DBUtils：
		它和Spring的JdbcTemplate很像，也是对Jdbc的简单封装

	以上这些都不是框架
		JDBC是规范
		Spring的JdbcTemplate和Apache的DBUtils都只是工具类

4、mybatis的概述
	mybatis是一个持久层框架，用java编写的。
	它封装了jdbc操作的很多细节，使开发者只需要关注sql语句本身，而无需关注注册驱动，创建连接等繁杂过程
	它使用了ORM思想实现了结果集的封装。

	ORM：
		Object Relational Mappging 对象关系映射
		简单的说：
			就是把数据库表和实体类及实体类的属性对应起来
			让我们可以操作实体类就实现操作数据库表。

			user			User
			id			userId
			user_name		userName
	今天我们需要做到
		实体类中的属性和数据库表的字段名称保持一致。
			user			User
			id			id
			user_name		user_name
5、mybatis的入门
	mybatis的环境搭建
		第一步：创建maven工程并导入坐标
		第二步：创建实体类和dao的接口
		第三步：创建Mybatis的主配置文件
				SqlMapConifg.xml
		第四步：创建映射配置文件
				IUserDao.xml
	环境搭建的注意事项：
		第一个：创建IUserDao.xml 和 IUserDao.java时名称是为了和我们之前的知识保持一致。
			在Mybatis中它把持久层的操作接口名称和映射文件也叫做：Mapper
			所以：IUserDao 和 IUserMapper是一样的
		第二个：在idea中创建目录的时候，它和包是不一样的
			包在创建时：com.itheima.dao它是三级结构
			目录在创建时：com.itheima.dao是一级目录
		第三个：mybatis的映射配置文件位置必须和dao接口的包结构相同
		第四个：映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
		第五个：映射配置文件的操作配置（select），id属性的取值必须是dao接口的方法名

		当我们遵从了第三，四，五点之后，我们在开发中就无须再写dao的实现类。
	mybatis的入门案例
		第一步：读取配置文件
		第二步：创建SqlSessionFactory工厂
		第三步：创建SqlSession
		第四步：创建Dao接口的代理对象
		第五步：执行dao中的方法
		第六步：释放资源

		注意事项：
			不要忘记在映射配置中告知mybatis要封装到哪个实体类中
			配置的方式：指定实体类的全限定类名
		
		mybatis基于注解的入门案例：
			把IUserDao.xml移除，在dao接口的方法上使用@Select注解，并且指定SQL语句
			同时需要在SqlMapConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名。
	明确：
		我们在实际开发中，都是越简便越好，所以都是采用不写dao实现类的方式。
		不管使用XML还是注解配置。
		但是Mybatis它是支持写dao实现类的。
xml文件配置

<!-- mybatis的主配置文件 -->
<configuration>
    <!-- 配置环境 -->
    <environments default="mysql">
        <!-- 配置mysql的环境-->
        <environment id="mysql">
            <!-- 配置事务的类型-->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 配置数据源（连接池） -->
            <dataSource type="POOLED">
                <!-- 配置连接数据库的4个基本信息 -->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/eesy?serverTimezone=Hongkong"/>
                <property name="username" value="root"/>
                <property name="password" value="duanyuefeng"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 指定映射配置文件的位置，映射配置文件指的是每个dao独立的配置文件 -->
    <mappers>
        <mapper resource="com/itheima/dao/IUserDao.xml"/>
    </mappers>
</configuration>
子配置文件
<mapper namespace="com.itheima.dao.IUserDao">
    <!--配置查询所有-->
    <select id="findAll" resultType="com.itheima.domain.User">
        select * from user
    </select>
</mapper>

注解配置 
    <mappers>
        <mapper class="com.itheima.dao.IUserDao"/>
    </mappers>
6、自定义Mybatis的分析：
	mybatis在使用代理dao的方式实现增删改查时做什么事呢？
		只有两件事：
			第一：创建代理对象
			第二：在代理对象中调用selectList
		
	自定义mybatis能通过入门案例看到类
		class Resources
		class SqlSessionFactoryBuilder
		interface SqlSessionFactory
		interface SqlSession
		
		
		
"C:\Program Files\Java\jdk1.8.0_131\bin\java.exe" -Dmaven.multiModuleProjectDirectory=E:\IDEA\myMybatis\asd -DarchetypeCatalog=internal -Dmaven.home=E:\Tools\apache-maven-3.5.2-bin\apache-maven-3.5.2 -Dclassworlds.conf=E:\Tools\apache-maven-3.5.2-bin\apache-maven-3.5.2\bin\m2.conf -Didea.launcher.port=51189 "-Didea.launcher.bin.path=E:\IDEA\IntelliJ IDEA 2018.3.5\bin" -Dfile.encoding=UTF-8 -classpath "E:\Tools\apache-maven-3.5.2-bin\apache-maven-3.5.2\boot\plexus-classworlds-2.5.2.jar;E:\IDEA\IntelliJ IDEA 2018.3.5\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMainV2 org.codehaus.classworlds.Launcher -Didea.version=2018.3.5 -Dexec.workingdir=E:\IDEA\myMybatis\asd "-Dexec.args=-classpath %classpath com.it.test.A" "-Dexec.executable=C:\Program Files\Java\jdk1.8.0_131\bin\java.exe" exec:exec
[INFO] Scanning for projects...


第二天：mybatis基本使用  

1、回顾mybatis自定义和环境搭建+完善自定义Mybatis的注解开发
2、Mybatis基于代理Dao的CRUD操作					重点内容  
	细节：
		对于删除方法，他是一个基本类型或者是基本类型包装类作为参数传递时，占位符可以随意写
	
		<!-- 删除用户 --> 
		<delete id="deleteUser" parameterType="java.lang.Integer"> 
			delete from user where id = #{uid} 
		</delete>  
		模糊查询 两种写法
		保存操作之后，获取id值
		
3、CRUD中可能遇到的问题：参数的传递以及返回值的封装
4、介绍Mybatis基于传统dao方式的使用（自己编写dao的实现类）	了解的内容    

	源码分析，能让自己理解更深刻，和自定义的mybatis逻辑基本一致
	断点调试，跟踪源码
5、mybatis主配置文件中的常用配置
	properties标签
	typeAliases标签				---解释Integer的写法
	mappers标签的子标签：package

<!-- 配置properties
	可以在标签内部配置连接数据库的信息。也可以通过属性引用外部配置文件信息
	resource属性： 常用的
		用于指定配置文件的位置，是按照类路径的写法来写，并且必须存在于类路径下。
	url属性：
		是要求按照Url的写法来写地址
		URL：Uniform Resource Locator 统一资源定位符。它是可以唯一标识一个资源的位置。
		它的写法：
			http://localhost:8080/mybatisserver/demo1Servlet
			协议      主机     端口       URI

		URI:Uniform Resource Identifier 统一资源标识符。它是在应用中可以唯一定位一个资源的。
-->
	
<!--使用typeAliases配置别名，它只能配置domain中类的别名 -->
<typeAliases>
	<!--typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就再区分大小写
	<typeAlias type="com.itheima.domain.User" alias="user"></typeAlias>  -->

	<!-- 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写-->
	<package name="com.itheima.domain"></package>
</typeAliases>

<!-- 配置映射文件的位置 -->
<mappers>
	<!--<mapper resource="com/itheima/dao/IUserDao.xml"></mapper>-->
	<!-- package标签是用于指定dao接口所在的包,当指定了之后就不需要在写mapper以及resource或者class了 -->
	<package name="com.itheima.dao"></package>
</mappers>

	
-----------------------------------------
OGNL表达式：	
	Object Graphic Navigation Language
	对象	图	导航	   语言
	
	它是通过对象的取值方法来获取数据。在写法上把get给省略了。
	比如：我们获取用户的名称
		类中的写法：user.getUsername();
		OGNL表达式写法：user.username     类的成员变量，和类的属性  有可能会不一样
	mybatis中为什么能直接写username,而不用user.呢：
		因为在parameterType中已经提供了属性所属的类，所以此时不需要写对象名
		实际开发中，又多个对象来组合成查询条件，以此来封装（pojo 实体类 == javabean）
		

第三天：mybatis的深入和多表
1、mybatis中的连接池以及事务控制			原理部分了解，应用部分会用
	mybatis中连接池使用及分析
	mybatis事务控制的分析
2、mybatis基于XML配置的动态SQL语句使用		会用即可
	mappers配置文件中的几个标签：
		<if>
		<where>
		<foreach>
		<sql>
3、mybatis中的多表操作				掌握应用
	一对多
	一对一（？）
	多对多

--------------------------------------------------------------------------------
1、连接池：
	我们在实际开发中都会使用连接池。
	因为它可以减少我们获取连接所消耗的时间。
2、mybatis中的连接池
	mybatis连接池提供了3种方式的配置：
		配置的位置：
			主配置文件SqlMapConfig.xml中的dataSource标签，type属性就是表示采用何种连接池方式。
		type属性的取值：
			POOLED	 采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现
			UNPOOLED 采用传统的获取连接的方式，虽然也实现Javax.sql.DataSource接口，但是并没有使用池的思想。
			JNDI	 采用服务器提供的JNDI技术实现，来获取DataSource对象，不同的服务器所能拿到DataSource是不一样。
				 注意：如果不是web或者maven的war工程，是不能使用的。
				 我们课程中使用的是tomcat服务器，采用连接池就是dbcp连接池。
3、mybatis中的事务
	什么是事务
	事务的四大特性ACID
	不考虑隔离性会产生的3个问题
	解决办法：四种隔离级别

	它是通过sqlsession对象的commit方法和rollback方法实现事务的提交和回滚
4、mybatis中的多表查询
	表之间的关系有几种：
		一对多
		多对一
		一对一
		多对多
	举例：
		用户和订单就是一对多
		订单和用户就是多对一
			一个用户可以下多个订单
			多个订单属于同一个用户

		人和身份证号就是一对一
			一个人只能有一个身份证号
			一个身份证号只能属于一个人

		老师和学生之间就是多对多
			一个学生可以被多个老师教过
			一个老师可以交多个学生
	特例：
		如果拿出每一个订单，他都只能属于一个用户。
		所以Mybatis就把多对一看成了一对一。
	
	mybatis中的多表查询：
		示例：用户和账户
			一个用户可以有多个账户
			一个账户只能属于一个用户（多个账户也可以属于同一个用户）
		步骤：
			1、建立两张表：用户表，账户表
				让用户表和账户表之间具备一对多的关系：需要使用外键在账户表中添加
			2、建立两个实体类：用户实体类和账户实体类
				让用户和账户的实体类能体现出来一对多的关系
			3、建立两个配置文件
				用户的配置文件
				账户的配置文件
			4、实现配置：
				当我们查询用户时，可以同时得到用户下所包含的账户信息
				当我们查询账户时，可以同时得到账户的所属用户信息

		示例：用户和角色
			一个用户可以有多个角色
			一个角色可以赋予多个用户
		步骤：
			1、建立两张表：用户表，角色表
				让用户表和角色表具有多对多的关系。需要使用中间表，中间表中包含各自的主键，在中间表中是外键。
			2、建立两个实体类：用户实体类和角色实体类
				让用户和角色的实体类能体现出来多对多的关系
				各自包含对方一个集合引用
			3、建立两个配置文件
				用户的配置文件
				角色的配置文件
			4、实现配置：
				当我们查询用户时，可以同时得到用户所包含的角色信息
				当我们查询角色时，可以同时得到角色的所赋予的用户信息
				
				
第四天：mybatis的缓存和注解开发 
1、Mybatis中的延迟加载
	问题：在一对多中，当我们有一个用户，它有100个账户。
	      在查询用户的时候，要不要把关联的账户查出来？
	      在查询账户的时候，要不要把关联的用户查出来？
		
	      在查询用户时，用户下的账户信息应该是，什么时候使用，什么时候查询的。
	      在查询账户时，账户的所属用户信息应该是随着账户查询时一起查询出来。

	什么是延迟加载
		在真正使用数据时才发起查询，不用的时候不查询。按需加载（懒加载）
	什么是立即加载
		不管用不用，只要一调用方法，马上发起查询。
	
	在对应的四种表关系中：一对多，多对一，一对一，多对多  
		一对多，多对多：通常情况下我们都是采用延迟加载。  
		多对一，一对一：通常情况下我们都是采用立即加载。    
		 
<!-- 定义封装account和user的resultMap -->
    <resultMap id="accountUserMap" type="account">
        <id property="id" column="id"></id>
        <result property="uid" column="uid"></result>
        <result property="money" column="money"></result>
        <!-- 一对一的关系映射：配置封装user的内容
        select属性指定的内容：查询用户的唯一标识：
        column属性指定的内容：用户根据id查询时，所需要的参数的值
        -->
        <association property="user" column="uid" javaType="user" select="com.itheima.dao.IUserDao.findById"></association>
    </resultMap>  
<!--配置参数-->
    <settings>
        <!--开启Mybatis支持延迟加载-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"></setting>
    </settings>
2、Mybatis中的缓存
	什么是缓存
		存在于内存中的临时数据。
	为什么使用缓存
		减少和数据库的交互次数，提高执行效率。
	什么样的数据能使用缓存，什么样的数据不能使用
		适用于缓存：
			经常查询并且不经常改变的。
			数据的正确与否对最终结果影响不大的。
		不适用于缓存：
			经常改变的数据
			数据的正确与否对最终结果影响很大的。
			例如：商品的库存，银行的汇率，股市的牌价。
	Mybatis中的一级缓存和二级缓存
		一级缓存：
			它指的是Mybatis中SqlSession对象的缓存。
			当我们执行查询之后，查询的结果会同时存入到SqlSession为我们提供一块区域中。
			该区域的结构是一个Map。当我们再次查询同样的数据，mybatis会先去sqlsession中
			查询是否有，有的话直接拿出来用。
			当SqlSession对象消失时，mybatis的一级缓存也就消失了。
		
		二级缓存:
			它指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
			二级缓存的使用步骤：
				第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
				第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
				第三步：让当前的操作支持二级缓存（在select标签中配置）
3、Mybatis中的注解开发 (在使用注解开发的时候，不能在同一个dao下在使用xml配置)
	环境搭建
	单表CRUD操作（代理Dao方式）
	多表查询操作
	缓存的配置（一级缓存不用配置，自己就有）
/**

 * 在mybatis中针对,CRUD一共有四个注解
 *  @Select @Insert @Update @Delete
 */