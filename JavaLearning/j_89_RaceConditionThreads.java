class Counter {
    int counter;

    // Synchronized method to avoid race condition
    public synchronized void run() {
        counter++;
    }
}

class Increment {
    int counter;

    // Non-synchronized method, can cause race condition
    public int run() {
        return counter++;
    }
}

public class j_89_RaceConditionThreads {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        
        // Runnable for Thread 1
        Runnable obj1 = () -> {
            for (int i = 1; i <= 1000; i++) {
                c.run();
            }
        };

        // Runnable for Thread 2
        Runnable obj2 = () -> {
            for (int i = 1; i <= 1000; i++) {
                c.run();
            }
        };

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        // Start the threads first
        t1.start();
        t2.start();

        // Then join them
        t1.join();
        t2.join();

        // Print the result from synchronized counter
        System.out.println(c.counter);

        Increment p1 = new Increment();

        // Runnable for Thread 3
        Runnable thread1 = () -> {
            for (int i = 1; i <= 1000; i++) {
                p1.run();
            }
        };

        // Runnable for Thread 4
        Runnable thread2 = () -> {
            for (int i = 1; i <= 1000; i++) {
                p1.run();
            }
        };

        Thread t3 = new Thread(thread1);
        Thread t4 = new Thread(thread2);

        // Start the threads first
        t3.start();
        t4.start();

        // Then join them
        t3.join();
        t4.join();

        // Print the result from non-synchronized counter
        System.out.println(p1.counter);
    }
}
