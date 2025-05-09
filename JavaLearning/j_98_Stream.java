import java.util.*;
import java.util.stream.Stream;

public class j_98_Stream {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(5);
        nums.add(11);
        nums.add(8);
        nums.add(3);
        nums.add(12);
        Stream<Integer> s1 = nums.stream();
        Stream<Integer> s2 = s1.filter(n->n%2==0);
        Stream<Integer> s3 = s2.map(n->n*2);
        int s4 = s3.reduce(0,(c,e)->c+e);

        // s3.forEach(n->System.out.println(n)); // stream only use one time
        System.out.println(s4);

        int res = nums.stream()
                            .filter(n->n%2==0)
                            .map(n->n*2)
                            .reduce(0,(c,e)->c+e);
        System.out.println(res);


        Stream<Integer> s5 = nums.stream() //nums.parallelStream() usefull for multi threading for filter the data synchronously
                                .filter(n->n%2==0)
                                .sorted();
                                s5.forEach(n->System.out.println(n));
    }
}
