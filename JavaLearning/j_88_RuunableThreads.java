
// class first2 implements Runnable{
//     public void run(){ // must be we have a run method with thread extended class
//         for(int  i=0; i<10;i++){
//             System.out.println("hi");
//             try {
//                 Thread.sleep(10);
//             } catch (InterruptedException e) {
                
//                 e.printStackTrace();
//             }
//         }
//     }
// }

class second2 implements Runnable{
    public void run(){
        for(int i=0; i<10;i++){
            System.out.println("hollo");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }
    }
}
public class j_88_RuunableThreads {
    public static void main(String[] args) {
        // Runnable obj1 = new Runnable(){
        //     public void run(){
        //         for(int  i=0; i<10;i++){
        //             System.out.println("hi");
        //             try {
        //                 Thread.sleep(10);
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //             }
        //         }
        //     }
        // };

        Runnable obj1 = ()->{
            {
                for(int  i=0; i<10;i++){
                    System.out.println("hi");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable obj2 =new second2();
        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);//thread construcytors support runnable class  so that reason i give a refference of runnable class
        t1.start();
        t2.start();
    }
}

