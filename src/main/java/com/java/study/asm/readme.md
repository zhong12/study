一、什么是ASM

    1．什么是ObjectWeb ASM  
        ASM是一个java字节码操纵框架，它能被用来动态生成类或者增强既有类的功能。ASM可以直接产生二进制class文件，也可以在类被加载入Java虚拟机之前动态改变类行为。
    Java class被存储在严格格式定义的 .class文件里，这些类文件拥有足够的元数据来解析类中的所有元素：类名称、方法、属性以及Java字节码（指令）。ASM从类文件中读入
    信息后，能够改变类行为，分析类信息，甚至能够根据用户要求生成新类。asm字节码增强技术主要是用来反射的时候提升性能的，如果单纯用jdk的反射调用，性能是非常低下的，
    而使用字节码增强技术后反射调用的时间已经基本可以与直接调用相当了。   
        ASM 提供了与BCEL和SERP相似的功能，只有22K的大小，比起350K的BCEL和150K的SERP来说，是相当小巧的，并且它有更高的执行效率，是BCEL的7倍，SERP的11倍以上。
        
        ASM字节码处理框架是用Java开发的而且使用基于访问者模式生成字节码及驱动类到字节码的转换，通俗的讲，它就是对class文件的CRUD，经过CRUD后的字节码可以转换为
    类。ASM的解析方式类似于SAX解析XML文件，它综合运用了访问者模式、职责链模式、桥接模式等多种设计模式，相对于其他类似工具如BCEL、SERP、Javassist、CGLIB，它的
    最大的优势就在于其性能更高，其jar包仅30K。Hibernate和Spring都使用了cglib代理，而cglib本身就是使用的ASM，可见ASM在各种开源框架都有广泛的应用。
    
    ASM是一个强大的框架，利用它我们可以做到：
       1、获得class文件的详细信息，包括类名、父类名、接口、成员名、方法名、方法参数名、局部变量名、元数据等
       2、对class文件进行动态修改，如增加、删除、修改类方法、在某个方法中添加指令等CGLIB（动态代理）是对ASM的封装，简化了ASM的操作，降低了ASM的使用门槛，其中，
    hibernate的懒加载使用到了asm，spring的AOP也使用到了。你建立一个hibernate映射对象并使用懒加载配置的时候，在内存中生成的对象使用的不再是你实现的那个类了，而
    是hibernate根据字节码技术已你的类为模板构造的一个新类，证明就是当你获得那个对象输出类名是，不是你自己生成的类名了。spring可能是proxy$xxx，hibernate可能是
    <你的类名>$xxx$xxx之类的名字。    
        
    2. ASM 特点 
        A、小巧、高效  
        B、源代码实现非常简洁而又优雅，简直就是Gof的《设计模式》非常棒的注解  
        C、字节码级的控制，能够更高效地实现字节码的控制  
      
    ObjectWeb ASM 有2组接口：
        * 基于事件驱动的接口，类似于xml的SAX接口，visitor模式，在访问到类定义某个部分的时候进行回调，实现上比tree接口高效，占用内存更小  
        * 基于tree的接口，类似于xml的DOM接口，将类定义解析成tree 


二、如何使用ASM

　　1、ASM框架中的核心类有以下几个：

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

    2、ASM/JAVA 类型和方法对比
         A、类型
             Java 类型      class文件类型(汇编语言类型)
             
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
    
        B、方法
            JAVA方法                    class文件类型(汇编语言类型)
            
            void m(int i, float f)     (IF)V
            int m(Object o)            (Ljava/lang/Object;)I
            int[] m(int i, String s)   (ILjava/lang/String;)[I
            Object m(int[] i)          ([I)Ljava/lang/Object;

