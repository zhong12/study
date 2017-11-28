package com.java.study.asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by zhongjing on 2017/11/24.
 * 
 * 基于事件驱动的接口，类似于xml的SAX接口，
 *    visitor模式，在访问到类定义某个部分的时候进行回调，实现上比tree接口高效，占用内存更小  
 * 
 */
public class PrintClassAdapter extends ClassVisitor {
    private String classMethod;
    private String className;
    
    public PrintClassAdapter(ClassVisitor classVisitor,String classMethod) {
        super(Opcodes.ASM4,classVisitor);
        this.classMethod = classMethod;
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s, s1, s2, strings);
        this.className = s;
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = cv.visitMethod(i, s, s1, s2, strings);
        if (s.equals(classMethod) && mv != null) {
            //为方法添加计时功能
            mv = new PrintMethodAdapter(mv,className);
        }
        return mv;
        
        
        
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
