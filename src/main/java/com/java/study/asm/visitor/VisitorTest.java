package com.java.study.asm.visitor;

import com.java.study.asm.MonitorConfig;
import com.java.study.asm.MonitorConfigImpl;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjing on 2017/11/24.
 */
public class VisitorTest {

    final static Map<String,List<String>> classMethodMap = new HashMap<>();
    static {
        MonitorConfig config;
        try {
            //读取配置文件  
            config = new MonitorConfigImpl();
            String methodStr = config.getStringValue("methodList", null);
            String[] classMethods = methodStr.split(",");
            //将读取的配置文件加入要检测的方法列表  
            if (null != classMethods && classMethods.length > 0) {
                for (int i = 0; i < classMethods.length; i++) {
                    String classMethod = classMethods[i];
                    String className = classMethod.substring(0, classMethod.lastIndexOf("."));
                    String methodName = classMethod.substring(classMethod.lastIndexOf(".") + 1);
                    List<String> list = classMethodMap.get(className);
                    if (list == null) {
                        list = new ArrayList<>();
                        classMethodMap.put(className, list);
                    }
                    list.add(methodName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        String className = "com.zb.javaagent.SayServiceImpl";
        if (classMethodMap.containsKey(className)) {
            List<String> methods = classMethodMap.get(className);
            if (methods != null && methods.size() > 0) {

                try {
                    //读取类的字节码流
                    ClassReader reader = new ClassReader(className);
                    //创建操作字节流值对象，ClassWriter.COMPUTE_MAXS:表示自动计算栈大小
                    ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
                    for (String method : methods) {
                        ClassVisitor classVisitor = new PrintClassAdapter(writer, method);
                        //使给定的访问者访问Java类的ClassReader
                        reader.accept(classVisitor, ClassReader.SKIP_DEBUG);
                    }
                    //返回修改后的字节码流
                    byte[] data = writer.toByteArray();
                    File file = new File("D://" + className + ".class");
                    System.out.println("filePath:" + file.getPath());
                    FileOutputStream fout = new FileOutputStream(file);
                    fout.write(data);
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
