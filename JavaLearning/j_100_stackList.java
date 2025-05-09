import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class j_100_stackList {
    public static void main(String[] args) {
        Stack<String> animal = new Stack<>();
        animal.push("Horse");
        animal.push("Cow");
        animal.push("Pig");
        animal.push("Sheep");
        animal.push("Goat");
        
        System.out.println(animal.peek());
        System.out.println(animal.capacity());
        System.out.println(animal.firstElement());
        System.out.println(animal.get(0));
        System.out.println(animal.pop());
        System.out.println(animal.search("Cow"));
        System.out.println(animal.contains("Cow"));
        System.out.println(animal.getFirst());
        System.out.println(animal.removeFirst());

        System.out.println("======================================");
        Iterator it = animal.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("======================================");
        for(String values:animal)
            System.out.println(values);
    }
}
