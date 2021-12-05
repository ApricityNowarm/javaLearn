# Spring

## IOC

#### 创建Bean对象

+ 1.方式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--    设置注解支持-->
    <context:annotation-config/>

    <!--    用于团队开发，导入他人写好的xml文件-->
    <import resource="beans.xml"/>


    <!--    id：相当于对象名-->
    <!--    class：是bean对象对应的全限定类名 包名 + 类型-->
    <!--    name：别名 可以取多个别名 用空格或逗号隔开-->
    <!--    scope默认singleton单例模式，只会创建一个bean，还有prototype-->
    <bean id="mysqlImpl" class="com.mt.dao.MysqlImpl" name="mysqlImpl2,impl1 impl2" scope="singleton"/>


    <!--    利用set动态注入属性，要求类必须有相应的set方法,set注入默认使用无参构造方法-->
    <bean id="UserServiceImpl" class="com.mt.Service.UserServiceImpl">
        <!--        ref：引用spring创建好的对象-->
        <!--        value：具体的值，基本数据类型-->
        <property name="userDao" ref="mysqlImpl"/>
    </bean>


    <bean id="user1" class="com.mt.pojo.User">
        <property name="name" value="张三"/>
    </bean>


    <!--    使用有参构造方法创建-->
    <bean id="user2" class="com.mt.pojo.User">
        <!--        index指构造方法参数的索引依次排开从0开始-->
        <constructor-arg index="0" value="张三"/>
    </bean>


    <bean id="user3" class="com.mt.pojo.User">
        <!--type指参数类型-->
        <constructor-arg type="com.lang.String" value="张三"/>
    </bean>


    <bean id="user4" class="com.mt.pojo.User">
        <!--name指参数名-->
        <constructor-arg name="name" value="张三"/>
    </bean>

    <!--    起别名-->
    <alias name="user1" alias="userT"/>


    <bean id="student" class="com.mt.pojo.Student">
        <!--        普通值注入-->
        <property name="name" value="张三"/>
        <!--        ref引用bean对象注入-->
        <property name="adress" ref="adress"/>
        <!--        数组-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>三国演义</value>
            </array>
        </property>
        <!--        List-->
        <property name="hobbys">
            <list>
                <value>爬山</value>
                <value>看电影</value>
                <value>读书</value>
            </list>
        </property>
        <!--        map映射-->
        <property name="card">
            <map>
                <entry key="1" value="一"/>
                <entry key="2" value="二"/>
                <entry key="3" value="三"/>
            </map>
        </property>
        <!--        set集合-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>DNF</value>
            </set>
        </property>
        <!--        null值-->
        <property name="wife">
            <null/>
        </property>
        <!--        properties-->
        <property name="info">
            <props>
                <prop key="学号">1841306018</prop>
                <prop key="姓名">张三</prop>
                <prop key="性别">男</prop>
            </props>
        </property>
    </bean>

    <!--    p命名空间，需要引入命名约束,p命名属性注入，也是使用set方法-->
    <bean id="user5" class="com.mt.pojo.User" p:name="张三" p:age="18"/>
    <!--    c命名空间，构造器注入-->
    <bean id="user6" class="com.mt.pojo.User" c:name="张三" c:age="18"/>
    <!--    _0和_1指构造器参数位置索引-->
    <bean id="user6" class="com.mt.pojo.User" c:_0="张三" c:_1="18"/>


    <!--    测试自动装配-->
    <bean id="cat" class="com.mt.pojo.Cat"/>
    <bean id="dog" class="com.mt.pojo.Dog"/>
    <bean id="person" class="com.mt.pojo.Person" autowire="byName">
        <property name="name" value="张三"/>
        <property name="cat" ref="cat"/>
        <property name="dog" ref="dog"/>
    </bean>
    
    <!--    byname通过名字自动注入属性-->
    <!--    会通过set方法名后面的值对应bean的id-->
    <!--    比如setCat则再同一个xml文件(上下文)中查找id=cat的bean赋值-->
    <bean id="person" class="com.mt.pojo.Person" autowire="byName">
        <property name="name" value="张三"/>
    </bean>
  
    <!--    bytype通过查找和set方法参数相同类型的bean对象-->
    <!--    但是同一个xml文件中(上下文)中只能有一个相对应类型的bean对象-->
    <!--    本例中只能有一个cat和dog-->
    <bean id="person" class="com.mt.pojo.Person" autowire="byType">
        <property name="name" value="张三"/>
    </bean>
  
</beans>
```

```java
import com.sun.istack.internal.Nullable;

import javax.annotation.Resource;

//注解自动装配
public class People {
//    注解注入首先根据类型查找，如果有多个相同类型bean再根据name(属性名)查找
//    此例中如果查找名字则查找bean的id为cat的bean
//    Quanlifier指定唯一bean
    @Autowired
    @Qualifier(value = "cat1")
    private Cat cat;
//    required标记false则说明dog允许为空
//    @Autowired(required = false)
//    Resource是java原生注解先根据bytype如果多个再根据byname,也可以指定name
    @Resource(name = "cat1")
    private Dog dog;

    private String name;

    //    nullable注解则说明允许cat传入参数为空
    @Autowired
    public void setCat(@Nullable Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "people{ " +
                "name = " + name +
                ", cat = " + cat +
                ", dog = " + dog + "},";
    }
}
```

+ 2.注解配置

```java

@Configuration
@ComponentScan(basePackages = "com.mt.pojo")
public class AppConfig {
    // ...
}
```

等同于以下配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.mt.pojo"/>

</beans>
```

然后可以使用@Component创建bean

```java
//此User类放在pojo包下被扫描，getBean("user")，实用类的小写名
//Scope作用域
@Component
@Scope("Singleton")
public class User {
    private String name1 = "张三";

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName1() {
        return name1;
    }

    //    属性注入
    @Value("张三")
    private String name2 = "张三";

    //    放在set方法上
    @Value("张三")
    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getname2() {
        return name2;
    }
}
```

+ @Component 衍生注解：创建bean
    + Dao : @Repository
    + Service：@Service
    + Controller：@Controller


+ 3.使用纯Java的@Configuration注解配置文件

```java

@Configuration
public class MtConfig {
//    通过getBean("myService")获取bean，默认bean名称与方法名相同
//    通过name指定名称
    @Bean(name = "myservice")
    public MyService myService() {
        return new MyServiceImpl();
    }

    //    起多个别名
    @Bean(name = {"user1", "user2", "user3"})
    public User getUser() {
        return new User();
    }

}
```

等同于以下配置文件

```xml

<beans>
    <bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>
```

## Aop

本质就是动态代理，Spring会帮我们创建代理类我们只需要控制某个对象方法执行前应该做什么执行后应该做什么

#### 实现方式

+ 1.方式
    + 1.1使用Spring的API接口

```java
public class Log implements MethodBeforeAdvice {
//    method:要执行的方法
//    args:参数
//    target:目标对象
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "被执行了");
    }
}

//======================================================================================
public class AfterLog implements AfterReturningAdvice {
    //    returnValue：返回值
//    method:要执行的方法
//    args:参数
//    target:目标对象
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "方法，返回值：" + returnValue);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--    注册bean-->
    <bean id="userService" class="com.mt.service.UserServiceImpl"/>
    <bean id="log" class="com.mt.log.Log"/>
    <bean id="afterLog" class="com.mt.log.AfterLog"/>


    <!--  方式一：使用Spring的API接口-->
    <!--  配置aop切入-->
    <aop:config>
        <!--    execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)-->
        <!--    切入点:expression:表达式. execution(修饰词， 返回值， 类名， 方法名， 参数)-->
        <!--    下面例子中：-->
        <!--    第一个*代表返回值类型为任意返回值-->
        <!--    第二个*代表类中任意方法-->
        <!--    ..代表方法中任意参数-->
        <aop:pointcut id="pointcut" expression="execution(* com.mt.service.UserServiceImpl.*(..))"/>
        <!--    执行环绕增加！-->
        <!--    advice-ref引用bean-->
        <!--    pointcut-ref引用切入点-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>

    <!--  方式二：使用自定义类-->
    <bean id="diy" class="com.mt.diy.DiyPointcut"/>

    <aop:config>
        <!--    自定义切面（切面即我们要把什么切入面对象增加进去）ref是要引用的类-->
        <aop:aspect ref="diy">
            <!--      切入点-->
            <!--      before和after指定切入点之前之后执行的切入面中的方法-->
            <aop:pointcut id="point" expression="execution(* com.mt.Service.UserServiceImpl.*(..))"/>
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>


    <!--  方式三：使用自定义类注解-->
    <bean id="annatationPointcut" class="com.mt.diy.AnnotationPointcut"/>
    <!--  开启注解支持-->
    <aop:aspectj-autoproxy/>

</beans>
```

+ 1.2使用Spring的API接口

```java
public class DiyPointcut {
    public void before() {
        System.out.println("之前执行了");
    }

    public void after() {
        System.out.println("之后执行了");
    }
}
```

+ 1.3使用注解实现AOP

```java

@Aspect
public class AnnotationPointcut {

    @Before("execution(* com.mt.Service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("方法执行前======");
    }

    @After("execution(* com.mt.Service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("方法执行后======");
    }

    //    给定一个参数传入，代表获取处理切入的点
    @Around("execution(* com.mt.Service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) {
        System.out.println("环绕前");

        Object o = jp.proceed();

        System.out.println("环绕后");
    }
}
```

## 整合MyBatis

+ 方式一

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--    注册配置bean-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverMannagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url"
                  value="jdbc:mysql://127.0.0.1:3306/smbms?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>


    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="com/mt/mapper/*.xml"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userMapperImpl" class="com.mt.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>
```

还需要配合UserMapperImpl

```java
public class UserMapperImpl implements UserMapper {

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUser();
    }
}
```

+ 方式二
    + UserMapperImpl继承SqlSessionDaoSupport调用getSession获取sqlSession

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <import resource="spring-dao.xml"/>

    <bean id="userMapperImpl" class="com.mt.mapper.UserMapperImpl">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

</beans>
```

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    public List<User> selectUser() {
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
```

+ 事务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--    注册配置bean-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverMannagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url"
                  value="jdbc:mysql://127.0.0.1:3306/smbms?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>


    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="com/mt/mapper/*.xml"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
    <!--    创建事务类支持-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <constructor-arg ref="dataSource"/>
    </bean>
    <!--  配合AOP植入事务处理-->
    <!--  配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--      给那些方法配置事务-->
        <!--      配置事务传播特性 new propagation:有7种-->
        <!--      add*则为名字为add开头的方法配置-->
        <tx:attributes>
            <tx:method name="add*" propagation="REQUIRED"/>
            <tx:method name="delete"/>
            <tx:method name="update"/>
            <tx:method name="query" read-only="true"/>
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <!--  配置事务切入-->
    <aop:config>
        <aop:poincut id="txPointcut" expression="execution(* com.mt.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
    </aop:config>

</beans>
```



















