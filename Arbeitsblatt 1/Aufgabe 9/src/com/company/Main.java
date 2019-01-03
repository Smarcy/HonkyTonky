package com.company;

import java.util.*;

public class Main
{

  public static void main(String[] args)
  {


    if (isHappy(7))
    {
      System.out.println("7 ist glücklich!");
    } else if (!isHappy(7))
    {
      System.out.println("7 ist traurig!");
    } else
    {
      System.out.println("Kein Ergebnis");
    }


  }

  static int sumOfSquaredDigits(int number)
  {
    int result = 0;
    String stringNum = String.valueOf(number);

    for (int i = 0; i < stringNum.length(); i++)
    {
      result = result + ((number % 10) * (number % 10));
      number = number / 10;

    }

    return result;
  }

  static boolean isHappy(int number)
  {

    if(isHappy(number))
    {
      return true;
    }
    else if(!isHappy(number))
    {
      return false;
    }else{
      number = sumOfSquaredDigits(number);
      isHappy(number);
    }

    return false;
  }

}