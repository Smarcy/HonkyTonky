import java.util.ArrayList;
import java.util.List;

public class Main
{

    static private ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args)
    {
        list.add(5);
        list.add(3);
        list.add(101);
        list.add(100);

        System.out.println(maximum(list));
    }

    static int maximum(final List<Integer> list)
    {
        int max = list.get(0);
        for(int i = list.size()-1; i >= 0; i--)
        {
            if(list.get(i)  > max)
            {
                max = list.get(i);
            }
        }
        return max;
    }
}