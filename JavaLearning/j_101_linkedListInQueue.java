import java.util.LinkedList;
import java.util.Queue;

public class j_101_linkedListInQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");
        queue.offer("G"); //if error are occured while adding the data it will return false and true while add method throws you exception
        System.out.println(queue.element()); //it will return top of the element from queue if queue is empty it will throws exception
        System.out.println(queue.peek()); //it will return top of the element from queue if queue is empty it will return null
        
        System.out.println(queue.remove()); //return and remove top of the element in queue,throws exception if queue is empty
        System.out.println(queue.poll()); //return and remove top of the element in queue,return null if queue is empty

        System.out.println(queue);
    }
}
