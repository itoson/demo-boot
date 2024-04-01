package org.example.abc.pack_collection;


import org.example.entity.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Demo02 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "A", 25));
        list.add(new Person(2, "B", 29));
        list.add(new Person(3, "C", 28));
        list.add(new Person(4, "D", 24));
        list.add(new Person(5, "E", 20));

        //删除大于25岁的人
        //1.for循环，i--
        //2.增强for，内部迭代器，一定会造成并发修改异常！
        //3.迭代器
        Iterator<Person> itr = list.iterator();
        while (itr.hasNext()) {
            Person p = itr.next();
            if (p.getAge() > 25) {
                itr.remove();  //--使用迭代器的remove方法
                //list.remove(p);  //--不能用List的remove方法，会造成并发修改异常
            }
        }
        //3.stream流
        list = list.stream().filter(p -> p.getAge() <= 25).collect(Collectors.toList());
        list.forEach(System.out::println);

        //单字段投影，只保留name
        List<String> nameList = list.stream().map(p -> p.getName()).collect(Collectors.toList());
        nameList.forEach(System.out::println);
    }


    /**
     * 线程安全的集合
     * -List型的线程安全容器 : Vector,CopyOnWriteArrayList
     * -Map型的线程安全容器 : HashTable,ConcurrentHashmap
     */
}
