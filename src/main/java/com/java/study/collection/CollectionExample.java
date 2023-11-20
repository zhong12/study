package com.java.study.collection;

import com.java.study.domain.User;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Created by zhongjing on 2015/11/12 0012.
 */
public class CollectionExample {
    public static void main(String[] args){
        String[] arrayA = new String[]{"1", "2", "3", "3", "4", "5"};
        String[] arrayB = new String[]{"3","3", "4", "4", "5", "6", "7"};
        List a = Arrays.asList(arrayA);
        List b = Arrays.asList(arrayB);
        Collection union = CollectionUtils.union(b, a);  //并集
        Collection intersection = CollectionUtils.intersection( b,a); //交集
        Collection disjunction = CollectionUtils.disjunction(b,a); //析取
        Collection subtract = CollectionUtils.subtract(b,a); //差集
        boolean containsAny= CollectionUtils.containsAny(b, a);

        Collections.sort((List<Comparable>) union);
        Collections.sort((List<Comparable>) intersection);
        Collections.sort((List<Comparable>) disjunction);
        Collections.sort((List<Comparable>) subtract);

        System.out.println("A2: " + ArrayUtils.toString(a.toArray()));
        System.out.println("B2: " + ArrayUtils.toString(b.toArray()));
        System.out.println("Union并集: " + ArrayUtils.toString(union.toArray()));
        System.out.println("Intersection交集: " +
                ArrayUtils.toString(intersection.toArray()));
        System.out.println("Disjunction析取: " +
                ArrayUtils.toString(disjunction.toArray()));
        System.out.println("Subtract差集: " + ArrayUtils.toString(subtract.toArray()));
        System.out.println("containsAny: " + containsAny);

        List stants = new ArrayList();
        User st1=new User();
        st1.setGrade("A");
        st1.setAge(5);
        stants.add(st1);
        Predicate isProblem = new MyPredicate("grade", "B");
        Collection select = CollectionUtils.select(stants, isProblem);
        Object[] objects = select.toArray();
        User user = (User) objects[0];
        System.out.println("select.size: " + ArrayUtils.toString(select.size()+"----"+select.toArray().length));
        System.out.println("user.grade: " + user.getGrade());
    }
}
