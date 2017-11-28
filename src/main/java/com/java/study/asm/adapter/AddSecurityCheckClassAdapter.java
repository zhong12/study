package com.java.study.asm.adapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by zhongjing on 2017/11/17.
 */
public class AddSecurityCheckClassAdapter extends ClassVisitor {
    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        //Responsechain 的下一个 ClassVisitor，这里我们将传入 ClassWriter，
        // 负责改写后代码的输出
        super(Opcodes.ASM4,cv);
    }

    // 重写 visitMethod，访问到 "operation" 方法时，
    // 给出自定义 MethodVisitor，实际改写方法内容
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            // 对于 "operation" 方法
            if (name.equals("operation")) {
                // 使用自定义 MethodVisitor，实际改写方法内容
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
