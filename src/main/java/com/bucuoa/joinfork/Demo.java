package com.bucuoa.joinfork;

import java.util.concurrent.ForkJoinPool;

public class Demo {  
	  
    public static void main(String[] args) {  
        long[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  
  
        ForkJoinPool pool = new ForkJoinPool(8);  
        long result = pool.invoke(new BigTask(nums));  
  
        System.out.println(result);  
    }  
  
}  