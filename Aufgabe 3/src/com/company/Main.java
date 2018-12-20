package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        System.out.println(reverse(7331));
        System.out.println(reverse(555));
        System.out.println(reverse(1234567890));
    }

    private static int reverse(int number)
    {
        String stringResult = "";
        int result = 0;
        int calc = number;

        for(int i = 0; i < String.valueOf(number).length(); i++)
        {
            if(calc >= 10)
            {
                result = (calc % 10);
            }else{
                result = calc;
            }
            stringResult += String.valueOf(result);

            calc = calc / 10;
        }

        return Integer.valueOf(stringResult);
    }
}
