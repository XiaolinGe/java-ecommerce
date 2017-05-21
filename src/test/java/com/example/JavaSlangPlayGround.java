package com.example;

import com.example.vo.A;
import com.example.vo.B;
import javaslang.API;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.control.Option;
import org.junit.Test;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Created by gezilin on 8/05/17.
 */
public class JavaSlangPlayGround {

    @Test
    public void optionBasic(){
        Option<Integer> some =Option.some(1);
        Option none = Option.none();
        System.out.println(some);
        System.out.println(none);
        System.out.println("some  defined "+some.isDefined());
        System.out.println("none  defined "+none.isDefined());

        System.out.println("some  isEmpty "+some.isEmpty());
        System.out.println("none  isEmpty "+none.isEmpty());

        System.out.println("some  get "+some.get());
        try {
            System.out.println("none  get "+none.get()); // throw Exception
        }catch (NoSuchElementException e){
           // e.printStackTrace();
        }



        System.out.println("some  getOrElse "+some.getOrElse(10));
        System.out.println("none  getOrElse "+none.getOrElse(10));


        System.out.println("some  getOrElse "+some.getOrElse(()->{
            //复杂逻辑
            int i = 1;
            return i++;
        }));
        System.out.println("none  getOrElse "+none.getOrElse(()->{
            int i=1;
            return i++;
        }));


    }

    @Test
    public void optionFP(){
        // option 精髓
        Option none = Option.none();
        System.out.println(none.map(e->e.toString())
                .filter(e->e.hashCode()>10));


        //SOME ->map/filter... -> SOME
        //NONE ->map/... - > NONE


        String s=null;
       // String s2="2";

        List<Integer> result;
        if(s==null){
            result = List.empty();
        }else {
            result = List.of(s).map(e -> Integer.parseInt(e));
        }

        System.out.println(result);


        result = Option.of(s)                     // String -> Option<String>
                .map(e -> List.of(e))           // Option<String> -> Option<List<String>>
                .getOrElse(List.empty())        // Option<List<String>> -> List<String>
                .map(e->Integer.parseInt(e));   // List<String> -> List<Integer>

        System.out.println(result);


    }

    @Test
    public void optionFlatmap(){
        Option o1 = Option.of(1);              // Integer ->option<Integer>
        Option o2 = o1.map(e->Option.of(e));   // Integer ->opton<ption<Integer>>
       // System.out.println(o2);

        Option o3 = o1.flatMap(e->Option.of(e));
        //System.out.println(o3);


        A a = new A();
        a.setName("test");

        B b = new B();
        b.setAge(10);
        //b.setA(a);

        java.util.Map<Integer,B> data = new HashMap<>();
        data.put(10,b);

        int age = 10;

        String name ;

       // name = data.get(age).getA().getName();
      //  System.out.println(name);

        name = Option.of(data.get(age))          // map -> option<A>
                .map(e->Option.of(e.getA()))     //option<a> option<option<a>
                .map(e->e.map(e2->e2.getName())) //option<option<a>   option<option<a>
                .get()
                .getOrElse("");


        name = Option.of(data.get(age))          // map -> option<A>
                .flatMap(e->Option.of(e.getA()))     //option<a> option<option<a>
                .flatMap(e->Option.of(e.getName())) //option<option<a>   option<option<a>
                .getOrElse("not found");

        System.out.println(name);
    }
}


