# Practices
学习java的练习及总结

##三大特性
###封装： 
利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体。数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，只保留一些对外接口使之与外部发生联系。用户无需知道对象内部的细节，但可以通过对象对外提供的接口来访问该对象。
优点:
+     减少耦合: 可以独立地开发、测试、优化、使用、理解和修改
+     减轻维护的负担: 可以更容易被程序员理解，并且在调试的时候可以不影响其他模块
+     有效地调节性能: 可以通过剖析确定哪些模块影响了系统的性能提高软件的可重用性
+     降低了构建大型系统的风险: 即使整个系统不可用，但是这些独立的模块却有可能是可用的
###继承
继承实现了 IS-A 关系，例如 Cat 和 Animal 就是一种 IS-A 关系，因此 Cat 可以继承自 Animal，从而获得 Animal 非 private 的属性和方法。
继承应该遵循里氏替换原则，子类对象必须能够替换掉所有父类对象。
Cat 可以当做 Animal 来使用，也就是说可以使用 Animal 引用 Cat 对象。父类引用指向子类对象称为 向上转型 。
***

    Java只能单继承，不能多继承，但接口可以多继承。
    1、子类继承父类所有的属性（除了private）
    2、子类继承父类（除private）所有的方法
    3.final方法不可以被继承，static方法不可以被继承，随着类的加载而加载。权限允许，子类可以使用。
    3、子类可以通过super，表示父类的引用，调用父类的属性或者方法。
    （构造函数和代码块是无法被继承）
*** 
    default (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。
    private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
    public : 对所有类可见。使用对象：类、接口、变量、方法
    protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）。
###多态
多态分为编译时多态和运行时多态:
编译时多态主要指方法的重载
运行时多态指程序中定义的对象引用所指向的具体类型在运行期间才确定
######运行时多态有三个条件:
+ 继承
+ 覆盖(重写)
+ 向上转型
  + 1.要有继承关系
  + 2.子类需重写父类的方法
  + 3.父类引用指向子类
  + 4.成员方法：编译在左，运行看右。
  + 5.成员变量：编译在左，运行看左。
###缓存池
+ new Integer(123) 与 Integer.valueOf(123) 的区别在于:
+ new Integer(123) 每次都会新建一个对象
+ Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
+ valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。
+ 在 Java 8 中，Integer 缓存池的大小默认为 -128~127。
+ 编译器会在缓冲池范围内的基本类型自动装箱过程调用 valueOf() 方法，因此多个 Integer 实例使用自动装箱来创建并且值相同，那么就会引用相同的对象。
######基本类型对应的缓冲池如下:
+ boolean values true and false
+ all byte values
+ short values between -128 and 127
+ int values between -128 and 127
+ char in the range \u0000 to \u007F
在使用这些基本类型对应的包装类型时，就可以直接使用缓冲池中的对象。
## 抽象类
+ 抽象类不能被实例化
+ 含有抽象方法必须是抽象类
+ 抽象类不一定必须含有抽象方法
+ 抽象类的抽象方法必须在子类实现
+ 如若子类没有实现父类的抽象方法那么子类也必须抽象
+ 不能有抽象构造方法和抽象静态方法。
## 接口
+    Java7：
+    1.常量
+    2.抽象方法
+    J-8：
+    3.默认方法
+    4.静态方法
+    J-9：
+    5.私有方法
###方法重载：
    同一个类，多个方法有相同的名字、不同的参数便产生了重载。
    1.不能有两个方法名称和参数类型相同，但是返回不同类型值的方法。
##方法重写：
    子类对父类的允许访问的方法实现过程进行重新定义
        方法名相同，参数类型相同	(方法签名相同)
            1.子类返回类型小于等于父类方法返回类型(指的是继承的关系)
        	2.子类方法抛出的异常小于等于父类方法
        	3.子类方法访问权限大于等于父类方法
        	口诀：两同两小一大
##Object通用方法
###equals()
+ 1.等价关系
  + 自反性：
    ```java
    x.equals(x); //true
  + 对称性：
    ```java
    x.equals(y) == y.equals(x); //true
  + 传递性：
    ```java
    if(x.equals(y) && y.equals(z))
        x.equals(z); //true
  + 一致性 : 多次调用equals方法结果不变
    ```java
    x.equals(y) == x.equals(y); //ture
  + 与null比较
    ```java
    x.equals(null); //false
+ equals()与==
  + 对于基本类型，==判断两个值是否相等，基本类型没有equals()方法
  + 对于应用类型，==判断两个变量是否引用同一个对象，而equals()方法判断引用对象是否等价
###hashCode()方法
+ hashCode() 返回散列值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。 
+ 在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。
###toString()方法
+ 默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示
###clone()方法
+ 1.cloneable
  
        clone() 是 Object 的 protected 方法，它不是 public，一个类不显式去重写 clone()，其它类就不能直接去调用该类实例的 clone() 方法。
+ 2.浅拷贝
  
        拷贝对象和原始对象的引用类型引用同一个对象。
+ 3.深拷贝

        拷贝对象和原始对象的引用类型引用不同对象。
+ 4.clone()的替代方案

        使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。
##关键字
+ final
    + 数据：数据为常量，不可改变
    + 方法：此方法不可被子类重写
    + 类：此类不可被继承
+ static
    + 静态变量：变量属于类，在内存只存在一份
    + 方法：类加载方法就已存在，不依赖实例，不可被抽象
    + 静态语句块：只在类初始化时运行一次
    + 静态内部类：
        + 非静态内部类需要依赖外部类实例，静态内部类不需要。
        + 静态内部类不能访问外部类非静态变量和方法
    + 静态导包：在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。
        + import static com.xxx.ClassName.*
##反射
+ 每个类都有一个 Class 对象，包含了与类有关的信息。当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。
+ 类加载相当于 Class 对象的加载。类在第一次使用时才动态加载到 JVM 中，可以使用 Class.forName("com.mysql.jdbc.Driver") 这种方式来控制类的加载，该方法会返回一个 Class 对象。
+ 反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。
+ Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类:
    + Field : 可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
    + Method : 可以使用 invoke() 方法调用与 Method 对象关联的方法；
    + Constructor : 可以用 Constructor 创建新的对象。
##异常
Throwable 可以用来表示任何可以作为异常抛出的类，分为两种: Error 和 Exception。

+ 其中 Error 用来表示 JVM 无法处理的错误，Exception 分为两种:
    + 受检异常 : 需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
    + 非受检异常 : 是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复。
## 规范代码
    规范化代码更容易让人理解
***
    命名规范：
        1.项目：全部小写，多个单词用中划线分隔'-'     spring-cloud
        2.包命名：全部小写      com.alibaba.fastjson
        3.类：单词首字母大写     FieldDeserializer
        4.变量：首字母小写，其他单词首字母大写    userName
        5.常量：全部大写，多个单词下划线分隔     USER_ID
        6.方法：首字母小写，其他单词首字母大写    readUserName()
## 八大基本类型
    short,int,long,float,double,Char,Boolean,Byte
    局部变量无默认初始值，成员变量数值类型默认0，引用类型默认NULL
## 栈内存、堆内存、方法区
    堆：存储对象
    栈：存储八大基本类型、对象引用
    方法区：存放类信息、常量、静态变量、即时编译器编译后的代码
## 方法：
    访问修饰符：public、protected、default、private
    方法名与参数列表合称为方法签名。
***
    关键字：主要有abstract、static两个关键字
    abstract关键字：
        抽象方法：
                1.抽象方法没有方法体。
                2.抽象方法必须被实现，如果一个类含有抽象方法，那么它一定是抽象类。
                3.如果一个子类没有实现父类中的抽象方法，则子类也成为了一个抽象类！
                4.抽象方法必须被重写，从没有方法体变为有具体的方法体。
                5.抽象类不能被实例化，不能new操作。
                6.抽象方法只需声明。
    static关键字：
        静态方法：
                1.和静态变量一样不属于对象，属于类。
                2.静态方法不能直接访问所属类非静态成员变量和非静态方法。
                3.不使用this和super。
***
    构造方法：
        1.无返回值。
        2.名称必须与类名称相同。
        3.不能递归。
        4.默认会提供无参构造方法
            如果类中有显示构造方法，默认构造方法失效
            需要无参构造方法时则需自己加上
##枚举
详解[深入枚举类型，引自CSDN博主](https://blog.csdn.net/javazejian/article/details/71333103)
## 内部类
### 静态内部类和非静态内部类的区别：
    1.静态内部类可以有静态成员，而非静态内部类则不能有静态成员。
    2.静态内部类可以访问外部类的静态变量，而不可访问外部类的非静态变量；
    3.非静态内部类的非静态成员可以访问外部类的非静态变量。
    4.静态内部类的创建不依赖于外部类，而非静态内部类必须依赖于外部类的创建而创建。
### 局部内部类：
    1.局部内类不允许使用访问权限修饰符 public private protected 均不允许。
    2.局部内部类对外完全隐藏，除了创建这个类的方法可以访问它其他的地方是不允许访问的。
    3.局部内部类与成员内部类不同之处是他可以引用成员变量，但该成员必须声明为 final，并内部不允许修改该变量的值。（这句话并不准确，因为如果不是基本数据类型的时候，只是不允许修改引用指向的对象，而对象本身是可以被就修改的）
### 匿名内部类:
    1.匿名内部类不含访问修饰符的。
    2.匿名内部类必须继承一个抽象类或者实现一个接口
    3.匿名内部类中不能存在任何静态成员或方法。
    4.匿名内部类是没有构造方法的，因为它没有类名。
    5.与局部内部相同匿名内部类也可以引用局部变量。此变量也必须声明为 final。 
### 可变参数
    使用前提：当方法的参数列表数据类型已经确定，但是参数个数不确定，就可以使用
    使用格式：定义方法时使用
           修饰符 返回值类型 方法名(数据类型...变量名){ }
    可变参数原理：
        底层是一个数组，根据传递参数个数不同，会创建不同长度的数组，来存储这些参数
        传递参数的个数，可以是0个（不传递），1,2...多个
    注意事项：
        1.一个方法的参数列表，只能有一个可变参数
        2.如果方法的参数有多个，那么可变参数必须写在参数列表末尾
## 常用API类
### Random
    创建 Random r = new Random();
    常用：获取一个随机的int数字，(范围是int所有范围，有正负两种)：int num = r.nextInt();
    int num = r.nextInt(3); //参数代表左闭右开[0,3)
### Arraylist<E>
    数组的长度不可以发生改变 
    底层是数组队列，是动态数组，容量可以动态增长
    实现了Cloneable接口，能被克隆
    实现了java.io.Serializable 接口，支持序列化，能通过序列化去传输
    ArrayList 中的操作不是线程安全的
    <E>指泛型
###### 常用方法：
    public boolean add(E e),向集合中添加元素
    public E get(int index),从集合中获取元素，参数是索引编号，返回值就是对应位置的元素
    public E remove(int index),从集合中删除元素，参数是索引编号，返回值就是删掉的元素
    public int size(),获取集合的长度，返回值包含集合个数
### String
       * 字符串的内容永不变
        字符串可以共享使用
        字符串效果上相当于char[]字符数组，java9以后:底层是byte[]字节数组，在这之前是char[]数组
        浪费空间
###### 常用构造方法：
    public String(),创建一个空白字符串，不含有任何内容
    public String(char[] array),根据字符数组内容创建
    public String(byte[] array),根据字节数组内容创建
###### 常用方法：
    public int length(),获取字符串当中含有的字符个数，拿到字符串长度
    public String concat(String str),将当前字符串和参数字符串拼接成为返回值新的字符串
    public char charAt(int index),获取指定索引位置单个字符
    public int indexOf(String str),查找参数字符串在本字符串当中首次出现的索引位置，没有返回-1
###### 字符串截取：
    public String substring(int index),截取从参数位置一直到字符串末尾，返回新字符串
    public String substring(int begin,int end),截取从begin开始到end结束，中间的字符串，左开右闭
###### 字符串转换:
    public char[] toCharArray(),将当前字符串拆分成为字符数组作为返回值
    public byte[] getBytes(),获得当前字符串底层数组
    public String replace(CharSequence oldString,CharSequence newString),
    将所有出现的老字符串替换成为新的字符串，返回替换之后的新字符串
###### 字符串分割：
    public String[] split(String regex),按照参数的规则，将字符串切分成为若干部分
### Date
###### 构造方法
    public Date(),获取当前系统的日期和时间
    public Date(long date),传递毫秒值，把毫秒值转换为Date日期
###### 常用方法
    public long getTime(),把日期转换为毫秒值
#### DateFormat
    日期/时间格式化子类的抽象类，用于日期和文本之间的转换，即Date和String转换
    格式化：按照指定格式，从Date对象转换成String
    解析：按照指定格式，从String对象转换成Date
    此类抽象类，一般使用子类java.text.SimpleDateFormat
###### 成员方法
    String format(Date date),按照指定格式，把Date日期，格式化为符合模式的字符串
    Date parse(String source),把符合模式的字符串解析为Date日期
#### SimpleDateFormat
###### 构造方法
    public SimpleDateFormat(String pattern),用给定的模式和默认语言环境的日期格式符号构造
#### Calendar
    日历类，是一个抽象类，提供了很多操作日历的字段(YEAR、MONTH、DAT_OF_MONTH、HOUR)
    Clendar类无法直接创建对象使用，里边有一个静态方法叫getInstance()，该方法返回了Calendar类的子类对象
    static Calendar getInstance()使用默认时区和语言环境获得一个日历
###### 常用方法
    public int get(int field),返回给定日历字段的值
    public void set(int field,int value),将给定日历字段设置为给定值
    public abstract void add(int field,int amout),依据日历的规则，为给定的日历字段添加或减去指定的时间量
    public Date getTime(),返回一个表示此Calendar时间值(从历元到现在的毫秒偏移量)的Date对象
###### 成员方法的参数：
    int field:日历类的字段，可以使用Calendar类的静态成员变量获取
        public static final int YEAR = 1;           年
        public static final int MONTH = 2;          月
        public static final int DATE= 5;            月中的某一天
        public static final int DAY_OF_MONTH = 5;   月中的某一天
        public static final int HOUR = 10;          时
        public static final int MINUTE = 12;        分
        public static final int SECOND = 13;        秒
#### StringBuilder
    字符串缓冲区，可以提高字符串的操作效率(看成一个长度可以变化的字符串)
    底层也是一个数组，但是没有被final修饰，可以改变长度
    效率高，线程不安全
###### 常用方法
    public StringBuilder append(...),可以添加任意数据的字符串形式，并返回当前对象自身
    public String toString(),将当前StringBuilder转换成String对象
## 异常
    Throwable：
        Error：错误
        Exception：编译期异常
            RuntimeException：运行期异常
    子父类异常：
                - 如果父类抛出多个异常，子类重写父类方法时，抛出和父类相同的异常或者是父类异常的子类或者不抛出异常
                - 父类方法没有抛出异常，子类重写父类该方法也不可抛出异常。此时子类产生异常，只能捕获处理，不能声明抛出
                注意：
                    父类异常什么样，子类异常就什么样
###### throw关键字
        作用：可以使用throw关键字在指定方法中抛出指定的异常
        格式：throw new xxxException("异常产生原因");
        注意：
            1.throw关键字必须写在方法内部
            2.throw关键字后边new的对象必须是Exception或者Exception子类对象
            3.throw关键字抛出指定的异常对象，我们就必须处理这个异常对象
                throw关键字后边创建的是RuntimeException或者是RuntimeException的子类对象，我们可以不处理，默认交给JVM处理（打印异常对象，中断程序）
                throw关键字后边创建的是编译异常（写代码的时候报错），我们就必须处理这个异常，要么throws，要么try...catch
###### throws关键字
      异常处理的第一种方式，交给别人处理
      作用：
            当方法内部抛出异常对象处理的时候，那么我们必须处理这个对象
            可以使用throws关键字处理这个异常对象，会把对象声明抛出给方法调用者处理（自己不处理，交给别人处理），最终交给JVM处理--->中断处理
      使用格式：在方法声明时使用
        修饰符 返回类型    方法名(参数列表) throws xxxException,yyyException...{ 
                throw new xxxException("产生原因");
                throw new yyyException("产生原因");
                ...
           }
      注意：
            1.throws关键字必须写在方法声明处
            2.throws关键字后边声明的异常必须是Exception或者是Exception的子类
            3.方法内部如果抛出了多个异常对象，那么throws后边也必须声明多个异常
                如果抛出的多个异常对象有子父类关系，那么直接声明父类即可
            4.调用了一个声明抛出异常的方法，我们就必须处理声明的异常
                要么继续使用throws声明抛出，交给方法调用者处理，最终交给JVM
                要么try...catch自己处理异常
###### try...catch
    异常出来的第二种方式，自己处理异常
    格式：
        try{
            可能产生异常的代码
        }catch(定义一个异常变量，用来接收try中抛出的异常对象){
            异常的处理逻辑，产生异常对象之后，怎么处理异常对象
            一般在工作中，会把异常的信息记录到日志
        }
        ...
        catch(异常类名 变量名){
        }finally{
            无论是否出现异常都会执行
        }
    注意：
        1.try中可能会抛出多个异常对象，那么就可以使用多个catch来处理这些异常对象
        2.如果try中产生了异常，那么就会执行catch中的异常处理逻辑，执行完毕catch中的处理逻辑，继续执行try...catch之后的代码
          如果try中没有产生异常，那么就不会执行catch中的异常处理逻辑，执行完毕try中的代码，继续执行try...catch之后的代码
###### finally
    注意：
        1.finally不能单独使用，必须和try一起使用
        2.finally一般用于资源释放（资源回收），无论程序是否出现异常，最后都要资源释放（IO）
        3.如果finally有return语句，永远返回finally中的结果，避免该情况（finally里return语句会覆盖）
###### 自定义异常
    java提供自定义异常，不够我们使用时，需要自己定义一些异常
    格式：
        public class xxxException extends Exception | RuntimeException{
            添加一个空参数的构造方法
            添加一个带异常信息的构造方法
        }
    注意：
        1.自定义异常类一般都是以Exception结尾，说明该类是一个异常类
        2.自定义异常类，必须的继承Exception或RuntimeException
            继承Exception：那么自定义的异常类就是一个编译期异常，如果方法内部抛出了一个编译期异常，那么就必须处理这个异常，要么throws，要么try...catch
            继承RuntimeException：那么自定义异常就是一个运行期异常，无需处理，交给虚拟机处理（中断处理）

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    