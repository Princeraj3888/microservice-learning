package com.explorer.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseAnArrayExample {

    public static void main(String[] args){
        int[] arr = {5, 7, 8, 3, 1, 8, 3};

        System.out.println("original array: "+Arrays.toString(arr));

        reverseArrayUsingTempVariable(arr);
        //reverseArrayUsingStreams(arr);

    }

    private static void reverseArrayUsingStreams(int[] arr) {
        List<Integer> reverseList = IntStream.rangeClosed(0, arr.length-1)
                .mapToObj(i -> arr[arr.length-1-i])
                .collect(Collectors.toList());

        System.out.println("reverseList: "+reverseList);
    }

    private static void reverseArrayUsingTempVariable(int[] arr) {
        int start = 0;
        int end = arr.length -1;

        while(start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }

        System.out.println(Arrays.toString(arr));
    }
}
