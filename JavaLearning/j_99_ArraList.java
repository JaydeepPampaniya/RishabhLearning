import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class j_99_ArraList {
    public static void main(String[] args) {
        ArrayList<String> student = new ArrayList<String>();
        student.add("aashish");
        student.add("Jaydeep");
        student.add("Rahul");
        student.add("Kapil");
        student.add("Ramesh");
        student.add("Suresh");
        // student.add(null); we can add null values in list

        for(String stdName:student)
            System.out.println(stdName);
        
        student.add(0,"Axay");
        student.set(0,"Nisha");
        for(int i=0; i<student.size(); i++)
            System.out.println(student.get(i));
        
        System.out.println(student.contains("Jaydeep"));
        System.out.println(student.containsAll(student));

        System.out.println(student.get(0));
		System.out.println("class " + student.getClass());
        student.addFirst("govi");
		student.addLast("Vidhi");
		student.addLast("Sidhdhu");
		student.addAll(2,student);
		student.remove(String.valueOf("jaydeep"));
		student.getFirst();
		student.getLast();
		student.hashCode();
		System.out.println(student.indexOf("jaydeep"));
		student.isEmpty();


        System.out.println(student.lastIndexOf("apple"));
		System.out.println(student.listIterator(5));
		student.remove(1);
		student.removeLast();
		student.removeFirst();
		// student.removeIf(null);
        // student.removeAll(student);

        student.sort(Comparator.reverseOrder());
		List<String> student1= student.subList(0,4);
		System.out.println(student1);


        ListIterator<String> it = student.listIterator();
        while(it.hasNext())
            System.out.println(it.next());

        while(it.hasPrevious())
            System.out.println(it.previous());


        ArrayList<String> student4 = (ArrayList<String>)student.clone(); // shallowcopy
        System.out.println(student4.remove(3));
        System.out.println(student);
        System.out.println("================================");
        System.out.println(student4);
    }
}
