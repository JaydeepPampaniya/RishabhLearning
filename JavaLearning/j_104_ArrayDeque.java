import java.util.ArrayDeque;
import java.util.Deque;


public class j_104_ArrayDeque {
    public static void main(String[] args) {
        Deque<Integer> marks = new ArrayDeque<>();
        marks.offer(24);
        marks.offer(53);
        marks.offer(78);
        marks.offer(25);
        marks.offer(12);
        marks.offerFirst(67);
        marks.offerLast(98);
        
        System.out.println(marks.peek());
        System.out.println(marks.peekFirst());
        System.out.println(marks.peekLast());
        System.out.println(marks.poll());
        System.out.println(marks.pollFirst());
        System.out.println(marks.removeLast());// we can add and remove from both of side in deque

    }
}
