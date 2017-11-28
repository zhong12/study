package com.java.study.asm.tree;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhongjing on 2017/11/24.
 */
public class TreeTest {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        int val;
        do{
            System.out.println("请选择   1、第一种方法读取class信息   2、第二种方法读取class信息  3、其他(退出程序)");
            val = s.nextInt();
            if(val == 1){
                readClassInfoFirst();
            }else if(val == 2){
                readClassInfoTwo();
            }else{
                
            }
        }
        while (val == 1 || val == 2);
        System.out.println("程序已退出");
    }

    /**
     * 所有vlaue都是 null。（注：接口说明，发现其是从常量池中读取的值，并且要求filed类型不是private修饰的。
     * 有值的都是final并且是基本数据类型的（String类型的必须是String s = "str"方式声明的，
     * 如果是new String("str")的也读取不到）。
     */
    public static void readClassInfoFirst(){
        ClassReader reader = null;
        try {
            reader = new ClassReader("com.java.study.asm.tree.ForReadClass");
            ClassNode cn = new ClassNode();
            reader.accept(cn, 0);
            System.out.println(cn.name);
            List<FieldNode> fieldList = cn.fields;
            for (FieldNode fieldNode : fieldList) {
                System.out.print("Field name: " + fieldNode.name);
                System.out.print("\t\tField desc: " + fieldNode.desc);
                System.out.print("\t\tFiled value: " + fieldNode.value);
                System.out.print("\t\tFiled access: " + fieldNode.access);
                System.out.println("\n===================================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void readClassInfoTwo(){
        try {
            ClassReader reader = new ClassReader("com.java.study.asm.tree.ForReadClass");
            ClassNode cn = new ClassNode();
            reader.accept(cn, 0);
            List<MethodNode> methodList = cn.methods;
            for (MethodNode md : methodList) {
                System.out.println(md.name);
                System.out.println(md.access);
                System.out.println(md.desc);
                System.out.println(md.signature);
                List<LocalVariableNode> lvNodeList = md.localVariables;
                for (LocalVariableNode lvn : lvNodeList) {
                    System.out.print("Local name: " + lvn.name);
                    System.out.print("\tLocal label: " + lvn.start.getLabel());
                    System.out.print("\tLocal desc: " + lvn.desc);
                    System.out.print("\tLocal signature: " + lvn.signature);
                    System.out.println("\n===================================");
                }
                Iterator<AbstractInsnNode> iterator = md.instructions.iterator();
                while (iterator.hasNext()) {
                    AbstractInsnNode abi = iterator.next();
                    if (abi instanceof LdcInsnNode) {
                        LdcInsnNode ldcI = (LdcInsnNode) abi;
                        System.out.print("\tLDC node value: " + ldcI.cst);
                    }
                    if(abi instanceof MethodInsnNode){
                        MethodInsnNode methodInsnNode = (MethodInsnNode) abi;
                    }
                }
                System.out.println("\n===================================");
            }
//            MethodVisitor mv = cn.visitMethod(Opcodes.AALOAD, "<init>", Type.getType(String.class).toString(), null, null);
//            mv.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(String.class), "str", Type.getType(String.class).toString());
//            System.out.println(cn.name);
//            List<FieldNode> fieldList = cn.fields;
//            for (FieldNode fieldNode : fieldList) {
//                System.out.print("Field name: " + fieldNode.name);
//                System.out.print("\tField desc: " + fieldNode.desc);
//                System.out.print("\tFiled value: " + fieldNode.value);
//                System.out.print("\tFiled access: " + fieldNode.access);
//                if (fieldNode.visibleAnnotations != null) {
//                    for (Object obj : fieldNode.visibleAnnotations) {
//                        if (obj instanceof AnnotationNode){
//                            AnnotationNode node = (AnnotationNode) obj;
//                            System.out.print("\t desc"+node.desc);
//                        }
//                    }
//                }
//                System.out.println("\n===================================");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
