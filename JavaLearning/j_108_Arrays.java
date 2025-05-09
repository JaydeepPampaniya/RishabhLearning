import java.lang.reflect.Array;
import java.util.Arrays;

public class j_108_Arrays {
    public static void main(String[] args) {
        int[] number = {1,5,2,8,5,3,9,0};
        Arrays.sort(number);
        for(int i:number)
            System.out.println(i);
        int index = Arrays.binarySearch(number, 5);

        System.out.println(Arrays.toString(number));

        System.out.println("5 number's index number is : "+ index);

        Integer[] numbers = {1,5,2,8,5,3,9,0};
        Arrays.sort(numbers);
        
        Arrays.fill(numbers, 12);
    }
}
