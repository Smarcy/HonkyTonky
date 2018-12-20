import java.lang.reflect.Array;
import java.util.*;

public class Main
{

    static private ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args)
    {
        System.out.println(maximum(list));
    }

    static int maximum(final ArrayList<Integer> list)
    {
        list.add(3);
        list.add(101);
        list.add(5);
        list.add(100);
        list.add(99);

        Iterator<Integer> it = list.iterator();
        int max = it.next();
        while(it.hasNext())
        {
            int i = it.next();
            if(i > max)
            {
                max = i;
            }
        }
        return max;
    }
}