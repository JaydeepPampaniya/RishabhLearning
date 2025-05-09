import java.util.*;

public class j_93_set {
    public static void main(String[] args) {
        //set doesent have an index value
        Set<Integer> nums = new HashSet<Integer>();
        nums.add(5);
        nums.add(3);
        nums.add(1);
        nums.add(4);
        nums.add(3);
        System.out.println(nums);

        Collection<Integer> nnums = new TreeSet<Integer>();
        nnums.add(5);
        nnums.add(3);
        nnums.add(1);
        nnums.add(4);
        nnums.add(3);
        System.out.println(nnums);

        Iterator<Integer> values = nnums.iterator();
        while(values.hasNext()){
            System.out.println(values.next());
        }
    }
}
