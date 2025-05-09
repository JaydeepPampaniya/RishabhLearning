import java.util.Set;
import java.util.TreeSet;

public class j_105_treeSet {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>(); // return value in sorted way
        
        set.add(32);
        set.add(45);
        set.add(32);
        set.add(45);
        set.add(12);
        set.add(11);
        set.add(92);
        set.add(65);
        // set.add(null);// null value is not allowed
        System.out.println(set);
        set.remove(45);

        System.out.println(set);
        System.out.println(set.contains(21));
        set.remove(65);
        System.out.println(set.isEmpty());
        
    }
}


