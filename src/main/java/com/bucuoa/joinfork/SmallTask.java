package com.bucuoa.joinfork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class SmallTask extends RecursiveTask<Long> {  
    private long[] nums;  
  
    public SmallTask(long[] nums) {  
        this.nums = nums;  
    }  
  
    @Override  
    protected Long compute() {  
        Long n = 0L;  
        for (long i : nums)  
            n += i;  
        return n;  
    }  
}  
  
class BigTask extends RecursiveTask<Long> {  
    private long[] nums;  
  
    public BigTask(long[] nums) {  
        this.nums = nums;  
    }  
  
    @Override  
    protected Long compute() {  
        List<RecursiveTask<Long>> list = new ArrayList<RecursiveTask<Long>>();  
        if (nums.length <= 2) {  
            SmallTask task = new SmallTask(nums);  
            list.add(task);  
            task.fork();  
        } else {  
            long[] nums1 = Arrays.copyOfRange(nums, 0, nums.length / 2);  
            long[] nums2 = Arrays.copyOfRange(nums, nums.length / 2, nums.length);  
            BigTask task1 = new BigTask(nums1);  
            BigTask task2 = new BigTask(nums2);  
            list.add(task1);  
            list.add(task2);  
            task1.fork();  
            task2.fork();  
        }  
  
        long n = 0L;  
        for (RecursiveTask<Long> r : list) {  
            n += r.join();  
        }  
        return n;  
    }  
}  
  

