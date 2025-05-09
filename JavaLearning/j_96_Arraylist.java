import java.util.*;
import java.util.function.*;
public class j_96_Arraylist {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(4, 5, 6, 8, 2, 1);
        
        Consumer<Integer> num  = new Consumer<Integer>(){
            public void accept(Integer n){
                System.out.println(n);
            }
        };
        //step2
        // Consumer<Integer> num  = (Integer n)->{
        //         System.out.println(n);
            
        // };
        //step3
            
            

        nums.forEach((n)-> System.out.println(num));

        
        //step4 thats how foreah works
        // nums.forEach(n->System.out.println(n));
    }
}
