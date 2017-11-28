package com.java.study.asm.adapter;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class AdapterTest {


//        ASM 能够通过改造既有类，直接生成需要的代码。增强的代码是硬编码在新生成的类文件内部的，没有反射带来性能上的付出。同时，ASM与Proxy 编程不同，不需要为增强代码而新定义一个接口，生
//    成的代码可以覆盖原来的类，或者是原始类的子类。它是一个普通的Java类而不是proxy类，甚至可以在应用程序的类框架中拥有自己的位置，派生自己的子类。
//        相比于其他流行的Java字节码操纵工具，ASM 更小更快。ASM 具有类似于BCEL或者SERP的功能，而只有33k大小，而后者分别有350k和150k。同时，同样类转换的负载，如果ASM是60%的话，BCEL需
//    要700%，而SERP需要1100% 或者更多。
//        ASM已经被广泛应用于一系列Java项目：AspectWerkz、AspectJ、BEA WebLogic、IBM AUS、OracleBerkleyDB、Oracle TopLink、Terracotta、RIFE、EclipseME、Proactive、Speedo、
//    Fractal、EasyBeans、BeanShell、Groovy、Jamaica、CGLIB、dynaop、Cobertura、JDBCPersistence、JiP、SonarJ、Substance L&F、Retrotranslator等。Hibernate和Spring也通过
//    cglib，另一个更高层一些的自动代码生成工具使用了ASM。
    
    public static void main(String[] args) throws Exception {
        addByte();
//        createInterface();
//        createClass();
        
    }


    /**
     * 在有class文件的基础上添加字节
     *
     * ClassWriter:
     *          构造器的另外一种参数。如果我们使用了ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);那么
     *          COMPUTE_MAXS就是替我们计算局部变量表和操作数栈的大小，这时候我们还是需要调用? mv.visitMaxs(2, 2);方法，
     *          但是可以传递任意参数，因为COMPUTE_MAXS的方式会帮助我们重新计算局部变量和操作数的size。还需要注意的是
     *          COMPUTE_MAXS不会为我们计算StackMapFrame，而COMPUTE_FRAMES既会计算栈size,也会计算StackMapFrame，当然
     *          使用COMPUTE_MAXS会让ClassWriter慢10%，而使用COMPUTE_FRAMES会慢20%（数据来自ASM官方说明文档，这里就不
     *          再测试）
     */
    public static void addByte() throws Exception{
        ClassReader cr = new ClassReader("com.java.study.asm.proxy.AccountImpl");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("D:\\AccountImpl123.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    /**
     * 创建一个Interface 的 class文件
     * @throws Exception
     */
    public static void createInterface() throws Exception{
        //生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(0);
        /**
         * 通过visit方法确定类的头部信息
         *
         * Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE 不写就是生成类
         * 
         * new String[]{"com/asm3/Mesurable","java/lang/Object"} 继承多个类
         * 
         * 
         *   version：JVM版本，这里是Opcodes.V1_5，即jdk5；
             access：类或者接口访问标志modifier，这里是 public abstract interface；
             name：类或者接口的全限定名，这里是asm/demo/AddOper；
             signature：类或者接口泛型参数，这里没有使用泛型，所以为null；
             superName：父类java/lang/Object；
             interfaces：父接口数组 new String[]{"asm/demo/Oper"}。
         */
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "com/study/People", null, "java/lang/Object", new String[]{});
        /**
         * 定义类的属性
         * 
         *    Opcodes.ACC_PUBLIC    属性作用域
         *    Opcodes.ACC_FINAL     是否FINAL
         *    Opcodes.ACC_STATIC    是否static
         *    LESS                  属性名
         *    new Integer(-1)  属性int类型并赋值 -1
         *    
         *    eg: public static final int LESS = -1
         *    
         *    
         *     第一个参数指定的是这个属性的操作权限，
         *     第二个参数是属性名，
         *     第三个参数是类型描述，
         *     第四个参数是泛型类型，
         *     第五个参数是初始化的值，
         *     这里需要注意一下的是第五个参数，这个参数只有属性为static时才有效，也就是数只有为static时，
         *     这个值才真正会赋值给我们添加的属性上，对于非static属性，它将被忽略。
         */
        //创建int类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "i", "I", null, new Integer(0)).visitEnd();
        //创建Integer类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "I", Type.getDescriptor(Integer.class), null, new Integer(0)).visitEnd();

        //创建float类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "f", "F", null, new Float(1)).visitEnd();
        //创建Float类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "F", Type.getDescriptor(Float.class), null, new Float(0)).visitEnd();

        //创建double类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "d", "D", null, new Double(0)).visitEnd();
        //创建Double类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "D", Type.getDescriptor(Double.class), null, new Double(0)).visitEnd();
        
        //创建boolean类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "Z", "F", null, new Boolean("true")).visitEnd();
        //创建Boolean类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "B", Type.getDescriptor(Boolean.class), null, new Boolean("false")).visitEnd();
        
        //创建Date类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "date", Type.getDescriptor(Date.class), null, "2017-17-10").visitEnd();
        //创建String类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "str", Type.getDescriptor(String.class), null, "我是String类型").visitEnd();
        //创建list类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "list", "Ljava/util/List;", "Ljava/util/List<Ljava/lang/String;>;", null).visitEnd();
        //创建map类型
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "map", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null).visitEnd();
        /**
         * 定义类的方法
         * 
         *     s1  参数类型
         *     (Ljava/lang/Object;Ljava/lang/String;)I   多个参数 
         *              java/lang/Object  参数类型
         *              I 方法返回类型
         *     new String[]{"java/lang/Exception"}   抛异常的类
         */
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;Ljava/lang/String;)I", "jjjj", new String[]{"java/lang/Exception"}).visitEnd();
        
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareToo",
                "(Ljava/lang/Object;)S", null, null).visitEnd();
        
        cw.visitEnd(); //使cw类已经完成
        //将cw转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File("D://People.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }

    /**
     * 创建一个 class文件
     * @throws Exception
     */
    public static void createClass() throws Exception{
        //生成一个类只需要ClassWriter组件即可
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC,
                "com/asm3/Man", null, "java/lang/Object", new String[]{"com/study/People"});
        //创建构造函数(无参)
        MethodVisitor mv1 = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(Opcodes.ALOAD, 0);
        mv1.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V");
        mv1.visitInsn(Opcodes.RETURN);
        mv1.visitMaxs(1, 1);
        mv1.visitEnd();

//        //创建构造函数(有参)
//        MethodVisitor mv2 = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(Ljava/lang/Object;Ljava/lang/String;)I", null, null);
//        mv2.visitCode();
//        mv2.visitVarInsn(Opcodes.ALOAD, 0);
//        mv2.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V");
//        mv2.visitAnnotation("LNotNull;", true).visit("value","abc");
//        mv2.visitInsn(Opcodes.RETURN);
//        mv2.visitMaxs(1, 1);
//        mv2.visitEnd();
        
        //创建属性
        cw.visitField(Opcodes.ACC_PUBLIC,"age", Type.getDescriptor(Integer.class),null,null).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC,"birthDay", Type.getDescriptor(Date.class),null,null).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC,"name", Type.getDescriptor(String.class),null,null).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC,"arr", "[I",null,null).visitEnd();
        
        // 定义code方法
        MethodVisitor mv3 = cw.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V",
                null, null);
        mv3.visitCode();
        mv3.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        mv3.visitLdcInsn("Hello World!");
        mv3.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V");
        mv3.visitInsn(Opcodes.RETURN);
        mv3.visitMaxs(0, 0);
        mv3.visitEnd();
        
        MethodVisitor mv2 = cw.visitMethod(ACC_PUBLIC, "getList", "(Ljava/util/Map;)Ljava/util/List;", 
                "(Ljava/util/Map<Ljava/lang/String;Ljava/lang/Integer;>;)Ljava/util/List<Ljava/lang/String;>;", null);
        mv2.visitInsn(Opcodes.RETURN);
        mv2.visitMaxs(0, 0);
        mv2.visitEnd();
        
        cw.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = cw.toByteArray();
        File file = new File("D://Man.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
