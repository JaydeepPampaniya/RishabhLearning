import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class j_103_priorityQueue{
    public static void main(String[] args) {
        Queue<Integer> student = new PriorityQueue<>(); //Min heap:min values has highest priority
        // Queue<Integer> student = new PriorityQueue<>(Comparator.reverseOrder()); //Max heap: this will max priority for max value

        student.offer(5);
        student.offer(45);
        student.offer(34);
        student.offer(69);
        student.offer(11);
        
        System.out.println(student);
        student.poll();
        System.out.println(student);
        System.out.println(student.peek());
    }
}