package com.java.study.asm.adapter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM4,mv);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker",
                "checkSecurity", "()V");
    }
}
