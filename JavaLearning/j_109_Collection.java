import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class j_109_Collection {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<>();
		list.add(34);
		list.add(4);
		list.add(43);
		list.add(41);
		list.add(40);
		list.add(25);
		list.add(45);
		list.add(87);
		System.out.println("min element"+ Collections.min(list));
		System.out.println("max element"+ Collections.max(list));
		System.out.println("9 repeate element"+ Collections.frequency(list,9));
		Collections.sort(list);
		Collections.sort(list,Comparator.reverseOrder());
	}
}
