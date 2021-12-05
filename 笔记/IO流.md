# IO

##### 感谢 [个人学习，引自博客，点击跳转](https://www.cnblogs.com/yichunguo/p/11775270.html)

    绝对路径：是一个完整的路径，以盘符（C:,D:）开始的路径
            C:\\Users\\Rainbow\\Desktop\\test.txt
    相对路径：是一个简化的路径
        相对路径指的是相对当前项的根目录(C:\\Users\\itcast\\IdeaProjects\\shungyuan)
        如果使用当前项目的根目录，路径可以简化书写
        C:\\Users\\itcast\\IdeaProjects\\shungyuan\\test.txt-->简化为：test.txt

### File

     类是文件和目录路径名的抽象表示，主要用于文件和目录的创建、查找和删除等操作

###### 构造方法

    public File(String pathname),通过给定的路径名字符串转换为抽象路径名来创建新的File实例
    public File(String parent,String child),从父路径名字符串和子字符串创建新的File实例
    public File(File parent,String child),从父抽象路径名和子路径字符串创建新的File实例

###### 静态成员变量

    public static String pathSeparator,与系统有关的路径分隔符，为了方便，它被表示为一个字符串
    public static char pathSeparatorChar,与系统路径有关的路径分隔符
    public static String separator,与系统有关的默认名称分隔符，为了方便，它被表示为一个字符串
    public static char separatorChar,与系统有关的默认名称分隔符

###### 常用方法

    public String getAbsolutePath(),返回此File的绝对路径名字符串
    public String getPath(),将此File转换为路径名字符串
    public String getName(),返回由此File表示的文件或目录的名称
    public long length(),返回此File表示的文件的长度
    public boolean exists(),此File表示的文件或目录是否实际存在
    public boolean isDirectory(),此File表示的是否为目录
    public boolean isFile(),此File表示的是否为文件
    public boolean createNewFile(),当且仅当具有该名称的文件尚不存在时，创建一个新的空文件
    public boolean delete(),删除由此File表示的文件或目录
    public boolean mkdir(),创建由此File表示的目录
    public boolean mkdirs(),创建由此File表示的目录，包括任何必须但不存在的父目录

### FileFilter

    java.io.FileFilter接口：用于抽象路径名(File对象)的过滤器
    作用：用于过滤文件（File对象）
    抽象方法：用来过滤文件的方法
        boolean accept(File pathname),测试指定抽象路径名是否应该包含在某个路径名列表中
    参数：
        File pathname：使用listFiles方法遍历目录，得到的每一个文件对象
    File[] listFiles(FilenameFilter filter)
    java.io.FilenameFilter接口：实现此接口的类实例可用于过滤器文件名
        作用：用于过滤文件名称
        抽象方法：用来过滤文件的方法
            boolean accept(File dir,String name) 测试指定文件是否应该包含在某一文件列表中
            参数：
                File dir:构造方法中传递的被遍历的目录
                String name:使用listFiles方法遍历目录，获取每一个文件/文件夹的名称
    两个过滤器接口都是没有实现类，需要自己写实现类，重写过滤的方法accept，在方法中自定义过滤规则

## IO流分类

                    输入流                     输出流
    字节流      字节输入流(InputStream)     字节输出流(OutputStream)
    字符流      字符输入流(Reader)          字符输出流(Writer)

### 字节输出流(OutputStream类)

    定义了字节输出流基本共性功能方法：
        public void close(),关闭此输出流并释放与此流相关联的任何系统资源
        public void flush(),刷新此输出流并强制任何缓冲的输出字节被写出
        public void write(byte[] b),将b.length字节从指定的字节数组写入此输出流
        public void write(byte[] b,int off,int len),将指定的字节数组写入len字节，从偏移量off开始输出到此输出流
        public abstract void write(int b),将指定的字节写入输出流

##### FileOutputStream

    文件字节输出流
    作用：把内存中的数据写入到硬盘文件中

###### 构造方法

    public FileOutputStream(String name),创建一个向具有指定名称的文件中写入数据的输出文件流
    public FileOutputStream(File file),创建一个向指定File对象表示的文件中写入数据的文件输出流
    参数：
        String name：目的地是一个文件路径
        File file：目的地是一个文件
    构造方法的作用：
                    1.创建一个FileoutputStream对象
                    2.会根据构造方法中传递的文件/文件路径，创建一个空的文件
                    3.会把FileOutputStream对象指向创建好的文件
    追加/续写：使用两个参数的构造方法
        public FileOutputStream(String name,boolean append),创建一个向具有指定name的文件中写入数据的输出文件流
        public FileOutputStream(File file,boolean append),创建一个向指定File对象表示的文件中写入数据的文件输出流
    参数：
        String name,File file:写入数据的目的地
        boolean append:追加写开关
                        true:创建对象不会覆盖源文件，继续在文件的末尾追加写数据
                        false:创建一个新文件，覆盖源文件

### 字节输入流(InputStream类)

    定义了字节输入流的基本共性功能方法
        public void close(),关闭此输入流并释放与此流相关联的任何系统资源
        public abstract int read(),从输入流读取数据的下一个字节
        public int read(byte[] b),从输入流中读取一些字节数，并将他们存储到字节数组b中

##### FileInputStream

    文件字节输入流
    作用：把硬盘文件中的数据，读取到内存中使用

###### 构造方法

    public FileInputStream(String name),创建一个从具有指定名称的文件中读取数据的输入文件流
    public FileInputStream(File file),创建一个从指定File对象表示的文件中读取数据的文件输入流
    参数：读取文件的数据源
        String name：文件路径
        File file：文件
    构造方法的作用：
          1.会创建一个FileInputStream对象
          2.会把FileInputStream对象指定构造方法要读取的文件

### 字符输入流(Reader类)

    字符输入流的最顶层父类，是一个抽象类
    共性成员方法：
                public int read(),读取单个字符并返回
                public int read(char[] cbuf),一次读取多个字符，将字符读入数组
                public void close(),关闭该流并释放与之关联的所有资源

##### FileReader

    文件字符输入流
    作用：把硬盘文件中的数据以字符的方式读取到内存中

###### 构造方法

    public FileReader(String fileName),
    public FileReader(File file),
    参数：读取文件的数据源
        String fileName：文件路径
        File file：一个文件
    构造方法作用：
            1.创建一个FileReader对象
            2.会把FileReader对象指向要读取的文件

### 字符输出流(Writer)

    字符输出流最顶层的父类，是一个抽象类
    共性成员方法：
                public void write(int c),写入单个字符
                public void write(char[] cbuf),写入字符数组
                public abstract void write(char[] cbuf,int off,int len),写入字符数组的某一部分，off是数组的开始索引，len是写入字符个数
                public void write(String str),写入字符串
                public void write(String str,int off, int len),写入字符串某一部分，off是字符串的开始索引，len写入字符的个数
                public void flush(),刷新该流的缓冲
                public void close(),关闭此流，但要先刷新它

##### FileWriter

    文件字符输出流

###### 构造方法

    public FileWriter(String fileName),
    public FileWriter(File file),
    参数：读取文件的数据源
        String fileName：文件路径
        File file：一个文件
    构造方法作用：
            1.创建一个FileWriter对象
            2.会根据构造方法中传递的文件/文件路径，创建文件
            3.会把FileWriter对象指向创建好的文件

### 缓冲流

##### BufferedOutputStream

    字节缓冲输出流
    继承父类共性成员方法：
            public void close(),关闭此输出流并释放与此流相关联的任何系统资源
            public void flush(),刷新此输出流并强制任何缓冲的输出字节被写出
            public void write(byte[] b),将b.length字节从指定的字节数组写入此输出流
            public void write(byte[] b,int off,int len),将指定的字节数组写入len字节，从偏移量off开始输出到此输出流
            public abstract void write(int b),将指定的字节写入输出流

###### 构造方法

    public BufferedOutputStream(Outputstream out),创建一个新的缓冲输出流，以将数据写入指定的底层输出流
    public BufferedOutputStream(Outputstream out,int size),创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流
    参数： 
        OutputStream out：字节输出流
                我们可以传递FileOutputStream，缓冲流会给FileOutputStream增加一个缓冲区，提高FileOutputStream的写入效率
        int size：指定缓冲流内部缓冲区的大小，不指定默认大小

##### BufferedInputStream

    字节缓冲输入流 
    继承父类成员方法
            public void close(),关闭此输入流并释放与此流相关联的任何系统资源
            public abstract int read(),从输入流读取数据的下一个字节
            public int read(byte[] b),从输入流中读取一些字节数，并将他们存储到缓冲区数组b中

###### 构造方法

    public BufferedInputStream(InputStream in),创建一个BufferedInputStream并保存其参数，
    public BufferedInputStream(InputStream in,int size),创建具有指定缓冲区大小的BufferedInputStream并保存其参数
    参数： 
        InputStream in：字节输入流
                我们可以传递FileInputStream，缓冲流会给FileInputStream增加一个缓冲区，提高FileInputStream的读取效率
        int size：指定缓冲流内部缓冲区的大小，不指定默认大小   

##### BufferedWriter

    字符缓冲输出流
    继承父类的共性成员方法
                public void write(int c),写入单个字符
                public void write(char[] cbuf),写入字符数组
                public abstract void write(char[] cbuf,int off,int len),写入字符数组的某一部分，off是数组的开始索引，len是写入字符个数
                public void write(String str),写入字符串
                public void write(String str,int off, int len),写入字符串某一部分，off是字符串的开始索引，len写入字符的个数
                public void flush(),刷新该流的缓冲
                public void close(),关闭此流，但要先刷新它

###### 构造方法

    public BufferedIWriter(Writer out),创建一个使用默认大小输出缓冲区的缓冲字符输出流
        public BufferedIWriter(Writer out,int size),创建具有指定缓冲区大小的新缓冲字符输出流
        参数： 
           Writer out：字符输出流
                    我们可以传递FileWriter，缓冲流会给FileWriter增加一个缓冲区，提高FileWriter的写入效率
            int size：指定缓冲流内部缓冲区的大小，不指定默认大小 

###### 特有成员方法

    public void newLine(),写入一个行分隔符，会根据不同操作系统，获取不同分隔符    

##### BufferedReader

    字符缓冲输入流
    继承字符类成员共性方法：    
                public int read(),读取单个字符并返回
                public int read(char[] cbuf),一次读取多个字符，将字符读入数组
                public void close(),关闭该流并释放与之关联的所有资源

###### 构造方法

    public BufferedReader(Reader in),创建一个使用默认大小输出缓冲区的缓冲字符输入流
        public BufferedReader(Reader in,int size),创建具有指定缓冲区大小的新缓冲字符输入流
        参数： 
           Reader in：字符输入流
                    我们可以传递FileReader，缓冲流会给FileReader增加一个缓冲区，提高FileReader的读取效率
            int size：指定缓冲流内部缓冲区的大小，不指定默认大小    

###### 特有成员方法

    public String readLine(),读取一个文本行，读取一行数据             

### 转换流

    字符字节转换

##### OutputStreamWriter

    字符通向字节流的桥梁，可使用指定的charset将要写入流中的字符编码成字节
    继承父类共性成员方法：
                public void write(int c),写入单个字符
                public void write(char[] cbuf),写入字符数组
                public abstract void write(char[] cbuf,int off,int len),写入字符数组的某一部分，off是数组的开始索引，len是写入字符个数
                public void write(String str),写入字符串
                public void write(String str,int off, int len),写入字符串某一部分，off是字符串的开始索引，len写入字符的个数
                public void flush(),刷新该流的缓冲
                public void close(),关闭此流，但要先刷新它    

###### 构造方法

    public OutputStreamWriter(OutputStream out),创建使用默认字符编码的OutputStreamWriter
        public OutputStreamWriter(OutputStream out,String charsetName),创建使用指定字符编码的OutputStreamWriter
        参数： 
           OutputStream out：字节输出流
           String charsetName：指定编码表名称，不区分大小写，不指定默认使用UTF-8  

##### InputStreamReader

    字节通向字符流的桥梁，可使用指定的charset读取字节并将其解码成字符
    继承父类成员方法
            public void close(),关闭此输入流并释放与此流相关联的任何系统资源
            public abstract int read(),从输入流读取数据的下一个字节
            public int read(byte[] b),从输入流中读取一些字节数，并将他们存储到缓冲区数组b中

###### 构造方法

    public InputStreamReader(InputStreamReader in),创建使用默认字符编码的InputStreamReader
        public InputStreamReader(InputStreamReader in,String charsetName),创建使用指定字符编码的InputStreamReader
        参数： 
           InputStreamReader in：字节输入流
           String charsetName：指定编码表名称，不区分大小写，不指定默认使用UTF-8      