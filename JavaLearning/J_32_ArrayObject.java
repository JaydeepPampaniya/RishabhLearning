class Student {
    int rollNumber;
    String name;
    int marks;

}
public class J_32_ArrayObject {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.rollNumber = 1;
        s1.name = "jaydeep";
        s1.marks = 99;

        Student s2 = new Student();
        s2.rollNumber = 2;
        s2.name = "vidhi";
        s2.marks = 34;

        Student s3 = new Student();
        s3.rollNumber = 3;
        s3.name = "dhruvik";
        s3.marks = 85;

        Student students[] = new Student[3];
        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        for (int i = 0; i < students.length; i++) {
            System.out.println("student name is : " + students[i].name + " roll number is : " + students[i].rollNumber
                    + "marks is : " + students[i].marks);
        }
        for (Student s : students) {
            System.out.println("student name is : " + s.name + " roll number is : " + s.rollNumber
                    + "marks is : " + s.marks);
        }

    }
}

