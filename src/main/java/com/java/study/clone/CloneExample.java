package com.java.study.clone;

import java.io.*;

/**
 * Created by zhongjing on 2015/11/26 0026.
 */
public class CloneExample {
    private CloneExample() {
        throw new AssertionError();
    }

    public static <T> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }


    public static void main(String[] args) {
        try {
//            User user1 = new User("zhangsan", 23, new BigDecimal(300));
//            User user2 = clone(user1);   // 深度克隆
//            user2.setMoney(new BigDecimal(400));
//            // 修改克隆的User对象user2关联的汽车对象的品牌属性
//            // 原来的User对象user1关联的汽车不会受到任何影响
//            // 因为在克隆User对象时其关联的汽车对象也被克隆了
//            System.out.println(user1.toString());
//            System.out.println(user2.toString());
//
//            Scanner scanner = new Scanner(System.in);
//            String a = scanner.next();
//            System.out.println(a);



            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            System.out.println("Enter your value:");
            str = br.readLine();
            System.out.println("your value is :"+str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
