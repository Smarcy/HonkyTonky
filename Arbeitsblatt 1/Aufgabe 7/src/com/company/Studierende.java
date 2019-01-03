package com.company;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.*;

public class Studierende extends Person
{
    Studierende(String name)
    {
        super(name);
        System.out.println(toString());
    }

    @Override
    public String toString()
    {
        return "Studierende: " + super.toString();
    }
}
