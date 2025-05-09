import java.util.HashSet;
import java.util.Set;

public class j_106_exampleOfSet {
    public static void main(String[] args) {
        Set<j_106_studentForSet> s2 = new HashSet<>();
        s2.add(new j_106_studentForSet("jaydeep",3323));
        s2.add(new j_106_studentForSet("aashish",3322));
        s2.add(new j_106_studentForSet("vidhi",3321));
        s2.add(new j_106_studentForSet("dhruvik",3320));
        s2.add(new j_106_studentForSet("suresh",3322));
        s2.add(new j_106_studentForSet("prakash",3324));
        s2.add(new j_106_studentForSet("jaydeep",3324));
        
        System.out.println(s2);

    }
}
