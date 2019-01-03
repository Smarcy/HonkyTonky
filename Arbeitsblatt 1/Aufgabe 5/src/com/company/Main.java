package com.company;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args)
    {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        a.add(5);
        a.add(2);
        a.add(199);

        b.add(198);
        b.add(3);
        b.add(1000);
        b.add(5);

        List<Integer> result = intersect(a, b);
        //System.out.println(intersect(a, b));

        for(Integer i : result)
        {
            System.out.println(i);
        }
    }

    static List<Integer> intersect(List<Integer> a, List<Integer> b)
    {
        List<Integer> schnitt = new ArrayList<Integer>();

        for(Integer i : b)
        {
            if ( a.contains(i))
            {
                schnitt.add(i);
            }
        }

        return schnitt;
    }
}
