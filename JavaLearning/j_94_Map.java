import java.util.*;

public class j_94_Map {
    public static void main(String[] args) {
        Map<String, Integer> student = new HashMap<String, Integer>(); 
        // Map<String, Integer> student = new Hashtable<String, Integer>(); 

        student.put("J9aydeep",5);
        student.put("Dhruvik",94);
        student.put("leenav",96);
        student.put("aashish",99);
        student.put("chirag",97);

        System.out.println(student.keySet());

        for(String key:student.keySet()){
            System.out.println(key+" : "+ student.get(key));
        }
    }
}
