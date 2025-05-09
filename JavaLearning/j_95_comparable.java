import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student implements Comparable<Student>{
    int age;
    String name;
    public Student(String name,int age) {
        this.age = age;
        this.name = name;
    }
    
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

    public int compareTo(Student that) {
        if(this.age>that.age)
            return 1;
        else
            return -1;
    }
}

public class j_95_comparable {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Jaydeep",21));
        students.add(new Student("Rahul",20));
        students.add(new Student("kishor",23));
        students.add(new Student("aashish",19));
        students.add(new Student("ramesh",22));

    
        // Collections.sort(numbers, Comparator.reverseOrder());
    
        Collections.sort(students);
        System.out.println(students);
    }
}


