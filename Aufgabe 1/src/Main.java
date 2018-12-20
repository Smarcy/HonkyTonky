import java.util.ArrayList;
import java.util.List;

public class Main
{

    //static private ArrayList<Integer> list = new ArrayList<Integer>();

    static private int[] list = new int[] {5, 3, 101, 100};

    public static void main(String[] args)
    {
        System.out.println(maximum(list));
    }

    static int maximum(final int[] list)
    {
        int max = list[0];
        for(int i = list.length-1; i >= 0; i--)
        {
            if(list[i]  > max)
            {
                max = list[i];
            }
        }
        return max;
    }
}