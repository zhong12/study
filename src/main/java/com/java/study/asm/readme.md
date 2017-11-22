一、什么是ASM
　　ASM是一个java字节码操纵框架，它能被用来动态生成类或者增强既有类的功能。ASM可以直接产
生二进制class文件，也可以在类被加载入Java虚拟机之前动态改变类行为。Java class被存储在严
格格式定义的 .class文件里，这些类文件拥有足够的元数据来解析类中的所有元素：类名称、方法、
属性以及Java字节码（指令）。ASM从类文件中读入信息后，能够改变类行为，分析类信息，甚至能够
根据用户要求生成新类。asm字节码增强技术主要是用来反射的时候提升性能的，如果单纯用jdk的反
射调用，性能是非常低下的，而使用字节码增强技术后反射调用的时间已经基本可以与直接调用相当了
。

二、如何使用ASM
　　ASM框架中的核心类有以下几个：
   ①ClassReader类：这个类会提供你要转变的类的字节数组，它的accept方法，接受一个具体的
ClassVisitor，并调用实现中具体的visit,visitSource, visitOuterClass, visitAnnotation, 
visitAttribute, visitInnerClass, visitField,visitMethod和 visitEnd方法。

   ②ClassWriter类：这个类是ClassVisitor的一个实现类，这个类中的toByteArray方法会将
最终修改的字节码以byte数组形式返回，在这个类的构造时可以指定让系统自动为我们计算栈和本地
变量的大小(COMPUTE_MAXS)，也可以指定系统自动为我们计算栈帧的大小(COMPUTE_FRAMES)。

   ③ClassAdapter类：这个类也是ClassVisitor的一个实现类，这个类可以看成是一个事件过滤
器，在这个类里，它对ClassVisitor的实现都是委派给一个具体的ClassVisitor实现类，即调用
那个实现类实现的方法。

   ④FieldVisitor接口：这个接口定义了和属性结构相对应的方法，这些方法可以操作属性。
   
   ⑤MethodVisitor接口：这个接口定义了和方法结构相对应的方法，这些方法可以去操作源方法，
具体的可以查看一下源码。



三、 ASM字节码处理框架是用Java开发的而且使用基于访问者模式生成字节码及驱动类到字节码的转
换，通俗的讲，它就是对class文件的CRUD，经过CRUD后的字节码可以转换为类。ASM的解析方式类
似于SAX解析XML文件，它综合运用了访问者模式、职责链模式、桥接模式等多种设计模式，相对于其
他类似工具如BCEL、SERP、Javassist、CGLIB，它的最大的优势就在于其性能更高，其jar包仅
30K。Hibernate和Spring都使用了cglib代理，而cglib本身就是使用的ASM，可见ASM在各种开源
框架都有广泛的应用。

ASM是一个强大的框架，利用它我们可以做到：
   1、获得class文件的详细信息，包括类名、父类名、接口、成员名、方法名、方法参数名、局部变
量名、元数据等
   2、对class文件进行动态修改，如增加、删除、修改类方法、在某个方法中添加指令等CGLIB（动
态代理）是对ASM的封装，简化了ASM的操作，降低了ASM的使用门槛，
  其中，hibernate的懒加载使用到了asm，spring的AOP也使用到了。你建立一个hibernate映射对
象并使用懒加载配置的时候，在内存中生成的对象使用的不再是你实现的那个类了，而是hibernate根
据字节码技术已你的类为模板构造的一个新类，证明就是当你获得那个对象输出类名是，不是你自己生
成的类名了。spring可能是proxy$xxx，hibernate可能是<你的类名>$xxx$xxx之类的名字。

A、bytecode中type表示如下：类似jni接口
Java type      Type descriptor
boolean        Z
char           C
byte           B
short          S
int            I
float          F
long           J
double         D
Object         Ljava/lang/Object;
int[]          [I
Object[][]     [[Ljava/lang/Object;


B、方法描述也类似jni
Method declaration in source ﬁle Method descriptor
void m(int i, float f)     (IF)V
int m(Object o)            (Ljava/lang/Object;)I
int[] m(int i, String s)   (ILjava/lang/String;)[I
Object m(int[] i)          ([I)Ljava/lang/Object;

