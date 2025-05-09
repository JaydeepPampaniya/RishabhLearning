import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class j_110_FileHanling {
    public static void main(String[] args) {
        File myFile = new File("j_110_FileHandling.txt");
        
        try {
            myFile.createNewFile();
            System.out.println("File created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating a file
        // FileWriter writer = null;
        // try{
        //     writer = new FileWriter("j_110_FileHandling.txt")
        //     writer.write("CodeWithHarry is one step solution for your all programming problems.\nKeep learning, Keep coding!");
        // }catch(Exception e){
        //     e.printStackTrace();
        // }

        // writing a file
        try(FileWriter writer = new FileWriter("j_110_FileHandling.txt")){
            writer.write("CodeWithHarry is one step solution for your all programming problems.\nKeep learning, Keep coding!");
        }catch(Exception e){
            e.printStackTrace();
        }

        //another way of writing a file

        String names[] = {"John","chrystefor","tiger"};
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter("j_110_anotherFile.txt"));
            bf.write("writing text");
            for(String name:names){
                bf.write("\n"+ name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reading a file
        File readFile = null;
        try{
            readFile = new File("j_110_FileHandling.txt");
            Scanner sc = new Scanner(readFile);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            sc.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error while reading a file");
        }

        //another way to read a file
        try {
            BufferedReader br = new BufferedReader(new FileReader("j_110_anotherFile.txt"));
            System.out.println(br.readLine());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File a1 = null;
        try{
            a1 = new File("j_110_FileHandling.txt");
            if(a1.delete()){
                System.out.println("file deleted successfully : " + a1.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error accuring while deleting a file");
        }
    }
}
