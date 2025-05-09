import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class j_109_exArrayList {
    public static void main(String[] args) {
        List<String> list6 = new ArrayList<>();
		list6.add("java");
		list6.add("is");
		list6.add("java");
		list6.add("java");
		list6.add("Language");
		list6.add("Python");
		list6.add("is");
		list6.add("Language");
		list6.add("my");
		list6.add("name");
		list6.add("is");
		list6.add("darshan");
		list6.add("my");
		list6.add("favourite");
		list6.add("is");
		list6.add("Language");
		list6.add("is");
		list6.add("java");
		list6.add("sql");

		int max1 = 0, max2 = 0, max3 = 0;
        String w1 = "", w2 = "", w3 = "";

        for (String i : list6) {
            int f = Collections.frequency(list6, i);

            if (f > max1) {
                max3 = max2;
                max2 = max1;
                max1 = f;
                w3 = w2;
                w2 = w1;
                w1 = i;
            } else if (f > max2 && f != max1) {
                max3 = max2;
                max2 = f;
                w3 = w2;
                w2 = i;
            } else if (f > max3 && f != max1 && f != max2) {
                max3 = f;
                w3 = i;
            }
        }

        System.out.println("top 3 repeated values");
        System.out.println(w1 + "=" + max1);
        System.out.println(w2 + "=" + max2);
        System.out.println(w3 + "=" + max3);
    }
}
