import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

// public class j_95_Comparator {
//     public static void main(String[] args) {
//         List<Integer> numbers = new ArrayList<>();
//         numbers.add(88);
//         numbers.add(45);
//         numbers.add(89);
//         numbers.add(56);
//         numbers.add(44);
//         numbers.add(59);

//         // Collections.sort(numbers, Comparator.reverseOrder());

//         Comparator<Integer> com = new Comparator<Integer>() {
//             public int compare(Integer i, Integer j){
//                 if(i%10>j%10)
//                     return 1;
//                 else
//                     return -1;
//             }
//         };
//         Collections.sort(numbers, com);
//         System.out.println(numbers);
//     }
// }

class Student {
    int age;
    String name;
    public Student(String name,int age) {
        this.age = age;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }
}

public class j_95_Comparator {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Jaydeep",21));
        students.add(new Student("Rahul",20));
        students.add(new Student("kishor",23));
        students.add(new Student("aashish",19));
        students.add(new Student("ramesh",22));

        // Collections.sort(numbers, Comparator.reverseOrder());
    
        // Comparator<Student> com = new Comparator<Student>() {
        //     public int compare(Student i, Student j){
        //         if(i.age>j.age)
        //             return 1;
        //         else
        //             return -1;
        //     }
        // };

        Comparator<Student> com = (i, j)->(i.age>j.age)? 1 :-1;
        Collections.sort(students, com);
        System.out.println(students);
    }
}


