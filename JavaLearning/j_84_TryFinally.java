import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class j_84_TryFinally {
    public static void main(String[] args) throws IOException {
    
        int num=0;
        // BufferedReader bf = null;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))){ //at this line advantage is their automaticly close the bf resource
            // InputStreamReader in = new InputStreamReader(System.in);
            // BufferedReader bf = new BufferedReader(in);
            // bf = new BufferedReader(new InputStreamReader(System.in));
             num =Integer.parseInt(bf.readLine());
            System.out.println(num);

        }finally{
            // bf.close();
        }
        
    }
}
