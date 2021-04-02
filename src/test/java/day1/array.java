package day1;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class array {
    //write a method that accepts an array and sorts it and returns the smallest value.
    // And write a method accepting 2 int arrays and joins them in a new string and returns ascending order.
    public static void main(String[] args) {
        int[] nums = {5,8,3,1,5};
        int[] num = {5,8,3,1,5};

       smallest(nums);
       arr(nums, num);
        System.out.println("arr7(nums, num) = " + arr7(nums, num));
    }

    public static void smallest(int[] nums){

        ArrayList<Integer> list = new ArrayList<>();
        for(int each: nums){
            list.add(each);
        }
        Collections.sort(list);
        System.out.println(list);
        System.out.println(list.get(0));

    }

    public static void arr(int[] nums,int[] num ) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int each : nums) {
            list.add(each);
        }
        for (int each2 : num) {
            list.add(each2);
        }
        Collections.sort(list);
        String result = "";

        for (int each : list) {
            result += each;
        }
        System.out.println(result);
    }

    public static String arr7(int[] nums,int[] num ){
      String result ="";

        for (int each : nums) {
            result+=each;
        }
        for (int each2 : num) {
            result+=each2;
        }


        char [] output = result.toCharArray(); // converting input string to char array

        Arrays.sort(output);

        return new String(output);

    }


}
