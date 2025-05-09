import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class j_92_ArrayList {
    public static void main(String[] args) {
        // Collection<Integer> num = new ArrayList<Integer>();
        List<Integer> num = new ArrayList<Integer>();

        num.add(5);
        num.add(6);
        num.add(7);
        num.add(8);
        // num.add("5");
        System.out.println(num.get(1));
        System.out.println(num.indexOf(5));
        System.out.println(num);


        // Collection num = new ArrayList();
        // num.add(5);
        // num.add(6);
        // num.add(7);
        // num.add(8);
        // for(Object n:num){
        //     int nums = (Integer)n;
        //     System.out.println(nums*2);
        // }

    }
}
