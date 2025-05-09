class first1 extends Thread{
    public void run(){ // must be we have a run method with thread extended class
        for(int  i=0; i<10;i++){
            System.out.println("hi");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
    }
}

class second extends Thread{
    public void run(){
        for(int  i=0; i<10;i++){
            System.out.println("hollo");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
    
    }
}

public class j_86_ThreadsPrioritySleep {
    public static void main(String[] args) {
        first1 obj1 = new first1();
        second obj2 =new second();
        obj1.start();
        // System.out.println(obj1.getPriority());
        // obj1.setPriority(0);
        // obj1.setPriority(Thread.MAX_PRIORITY);
        // obj1.setPriority(Thread.MIN_PRIORITY);
        // obj1.setPriority(Thread.NORM_PRIORITY);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        obj2.start();
    }
}
