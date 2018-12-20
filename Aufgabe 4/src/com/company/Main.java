package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        int[] array = new int[] {5, 5, 4, 6, 1, 7, 5, 3, 4, 1, 6};
        int result = search(array, 2);
        System.out.println(result);
    }

     private static int search(int[] array, int value)
     {
         for(int i = 0; i < array.length; i++)
         {
             if(array[i] == value)
             {
                 return i;
             }
         }
         return -1;
     }
}
