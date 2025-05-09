import java.util.ArrayList;

class Student {
    String name;
    
    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class J_102_shallowCopy {
    public static void main(String[] args) {
        ArrayList<Student> student = new ArrayList<>();
        student.add(new Student("Vidhi"));
        student.add(new Student("Aashish"));
        student.add(new Student("Jaydeep"));
        student.add(new Student("Kapil"));

        ArrayList<Student> student4 = (ArrayList<Student>) student.clone();

        student4.get(0).name = "Updated Alice";

        System.out.println("Original student list: " + student);
        System.out.println("Modified student4 list: " + student4);
    }
}
