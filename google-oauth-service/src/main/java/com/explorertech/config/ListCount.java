package com.explorertech.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListCount {

    //list1 -> a,b,c
    //list2 -> a,c,c,b,b,b,d
    //restult -> a->1, b->3, c->2, d->0

    public static void main(String[] args){
        List<String> list1 = Arrays.asList("a","b","c");
        List<String> list2 = Arrays.asList("a","c","c","b","b","b","d");

       Map<String, Integer> countMap =  new HashMap<>();
               //list2.stream().filter(list1::contains).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


       for(String st: list2){
           if(list1.contains(st)) {
               countMap.put(st, countMap.getOrDefault(st, 0) + 1);
           }else{
               countMap.put(st, 0);
           }
       }

        System.out.println("countMap: "+countMap);
    }
}
