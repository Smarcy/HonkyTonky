package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        System.out.println(crossSum(12345));
        System.out.println(crossSum(1337));
        System.out.println(crossSum(55555555));

    }

    static int crossSum(int number)
    {
        int result = 0;

        while(number != 0)
        {
            result += (number % 10);

            number = (number / 10);


        }
        return result;
    }
}
