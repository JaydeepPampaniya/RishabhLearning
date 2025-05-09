import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class j_104_hashSetAndLinkedHashSet {
    public static void main(String[] args) {
        // Set<Integer> set = new HashSet<>();
        Set<Integer> set = new LinkedHashSet<>(); //maintain the insertio order
        
        
        set.add(32);
        set.add(45);
        set.add(32);
        set.add(45);
        set.add(12);
        set.add(11);
        set.add(92);
        set.add(65);
        set.add(null);
        set.add(null); // only one null values allowed
        System.out.println(set);
        set.remove(45);
        
        System.out.println(set);
        System.out.println(set.contains(21));
        set.remove(65);
        System.out.println(set.isEmpty());
        
    }
}
