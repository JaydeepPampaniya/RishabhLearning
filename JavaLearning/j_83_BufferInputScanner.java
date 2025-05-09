import java.io.*;
import java.util.Scanner;
// import java.io.IOException;

public class j_83_BufferInputScanner {
    public static void main(String[] args) throws IOException {
        System.out.println("enter the age");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(in); //alse bufferReader read the file resource and input data
        int res = Integer.parseInt(bf.readLine());
        System.out.println("enter name");
        String res1 = bf.readLine();
        System.out.println("your namse is "+res1+ " your age is "+ res);
        bf.close(); // its your resposibility to close resource, your resposibility to close connection, your resposibility to close buffer reader so that reson 
        // i closed the bufferreader resource

        Scanner sc = new Scanner(System.in);
        int a1 = sc.nextInt();
        String name = sc.nextLine();
        boolean elligibleForVote = sc.nextBoolean();
    }
}

