package com.company;

import java.util.*;

public class Person
{
    String name;

    Person() {}

    Person(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }


}
