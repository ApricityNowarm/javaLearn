#Spring

##创建Bean对象
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
    <bean id="user5" class="com.mt.pojo.User p:name="张三" p:age="18"/>
<!--    c命名空间，构造器注入-->
    <bean id="user6" class="com.mt.pojo.User" c:name="张三" c:age="18"/>
<!--    _0和_1指构造器参数位置索引-->
    <bean id="user6" class="com.mt.pojo.User" c:_0="张三" c:_1age="18"/>
    
    
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