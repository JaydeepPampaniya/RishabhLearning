public class J_7_WhileDoWhileLoops {
    public static void main(String[] args) {
        int i=1;
        // while(true){
        //     System.out.println("hi my name is jaydeeP "+i);
        //     i++;
        // }
        while(i<=4){
            System.out.println("hi my name is jaydeeP "+i);
            int j=1;
            while(j<=3){
                System.out.println("hello jaydeep");
                j++;
            }
            i++;
        }
        do{
            System.out.println("jaydeep");
            i++;
        }while(i<10);
    }
}
