class Thread1 extends Thread{
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("hi");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Thread2 extends Thread{
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("hello");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

public class j_85_Thread {
    public static void main(String[] args) {
        Thread t1=new Thread1();
        Thread t2=new Thread2();
        
        t1.start();
        t2.start();
    }
}
