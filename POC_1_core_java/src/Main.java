import Management.SchoolManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            SchoolManagement add = new SchoolManagement();

            while (true) {
                System.out.println(" 1. Add a student \n 2. Remove a student \n 3. Get Student Details \n 4. Add a teacher \n 5. Remove a teacher \n 6. Get Teacher Details");
                System.out.println("Enter Index No. : ");
                int index = sc.nextInt();
                switch (index){
                    case 1:
                        System.out.println("Enter a Student details");
                        System.out.println("Roll No : ");
                        int rollNo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Name : ");
                        String name = sc.nextLine();
                        System.out.println("Department : ");
                        String department = sc.nextLine();
                        add.addStudent(rollNo,name,department);
                        break;
                    case 2:
                        System.out.println("Enter Roll No");
                        int removeRollNo = sc.nextInt();
                        add.removeStudent(removeRollNo);
                        System.out.println(" ");
                        break;
                    case 3:
                        add.getStudentDetails();
                        System.out.println(" ");
                        break;
                    case 4:
                        System.out.println("Enter a Teacher Details");
                        System.out.println("Teacher Id");
                        int techerId = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Name");
                        String teacherName = sc.nextLine();
                        System.out.println("Enter Department");
                        String teacherDepartment = sc.nextLine();
                        add.addTeacher(techerId,teacherName,teacherDepartment);
                        break;

                    case 5:
                        System.out.println("Enter Teacher Id");
                        int removeTeacherId = sc.nextInt();
                        add.removeTeacher(removeTeacherId);
                        System.out.println(" ");
                        break;
                    case 6:
                        add.getTeacherDetails();
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Enter Valid No");
                }
            }
        } catch (Exception e){
            System.out.println("An error occurred: "+ e);
        }
    }
}