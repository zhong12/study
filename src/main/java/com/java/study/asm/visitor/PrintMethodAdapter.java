package com.java.study.asm.visitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by zhongjing on 2017/11/24.
 */
public class PrintMethodAdapter extends MethodVisitor {
    
    private String className;
    
    public PrintMethodAdapter(MethodVisitor methodVisitor, String className) {
        super(Opcodes.ASM4,methodVisitor);
        this.className = className;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitInsn(int i) {
        if ((i >= Opcodes.IRETURN && i <= Opcodes.RETURN) || i == Opcodes.ATHROW) {
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("hello world!");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        mv.visitInsn(i);
    }

    @Override
    public void visitMaxs(int i, int i1) {
        super.visitMaxs(i, i1);
    }
}
