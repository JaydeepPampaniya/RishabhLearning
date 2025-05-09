package Management;

import StaffOfSchool.Student;
import StaffOfSchool.Teacher;
import custom_exception.SchoolException;
import java.util.HashSet;
import java.util.Set;

public class SchoolManagement {

    private final Set<Teacher> teachers = new HashSet<>();
    public void addTeacher(int id, String name,String department) throws SchoolException {
        if (teachers.contains(new Teacher(id,name,department))) {
            throw new SchoolException("you cannot add repetitive value");
        }else {
            teachers.add(new Teacher(id,name, department));
        }
    }

    private final Set<Student> students = new HashSet<>();
    public void addStudent(int rollNo,String name,String department) throws SchoolException {
        if (students.contains(new Student(rollNo,name,department))) {
            throw new SchoolException("you cannot add repetitive value");
        }else{
            students.add(new Student(rollNo,name, department));
        }
    }
    public void removeStudent(int rollNo){
        boolean removed = false;
        for(Student s: students){
            if(s.getRollNo()== rollNo){
                students.remove(s);
                removed = true;
                System.out.println("student with rollNo " + rollNo + " is removed successfully");
                break; //because prevent the cunccurentModifectationException thats why ihave a use break
            }
        }
        if(!removed)
            System.out.println("Student with rollNo " + rollNo + " not found.");
    }
    public void removeTeacher(int teacherId){
        boolean removed = false;
        for(Teacher t: teachers){
            if(t.getId()== teacherId) {
                teachers.remove(t);
                removed = true;
                System.out.println("teacher with teacher id " + teacherId + " is removed successfully");
                break;
            }
        }
        if(!removed)
            System.out.println("teacher with teacher id " + teacherId + " not found.");
    }
    public void getStudentDetails(){
        for(Student s: students)
            System.out.println(s);
    }
    public void getTeacherDetails(){
        for(Teacher s: teachers)
            System.out.println(s);
    }

}
