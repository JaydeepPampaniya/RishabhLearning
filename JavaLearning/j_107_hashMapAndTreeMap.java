import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class j_107_hashMapAndTreeMap {
    public static void main(String[] args) {
        // Map<String, Integer> number = new HashMap<>();
        Map<String, Integer> number = new TreeMap<>();
        number.put("one", 1);
        number.put("two", 2);
        number.put("three", 3);
        number.put("four", 4);
        number.put("five", 5);
        
        if (!number.containsKey("three")) {
            number.put("three", 3);
        }

        number.containsValue(3);
        number.putIfAbsent("two", 23);
        System.out.println(number.containsKey("two") + " " + number.containsValue(2) + " " + number.isEmpty());

        number.remove("eleven");

        for (Map.Entry<String, Integer> e : number.entrySet()) {
            System.out.println(e);

            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }
        for (String e : number.keySet()) {
            System.out.println(e + " = " + number.get(e));
        }

        for (Integer e : number.values()) {
            System.out.println(e);
        }
        number.forEach((key, value) -> System.out.println(key + ": " + value));
    }

}
