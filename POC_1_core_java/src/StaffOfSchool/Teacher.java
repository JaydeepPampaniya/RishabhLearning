package StaffOfSchool;

import general.Person;

import java.util.Objects;

public class Teacher extends Person {
    public Teacher(int teacherId, String name, String department) {
        super(teacherId,name,department);
    }

    @Override
    public String toString() {
        return "Teacher ID: " + getId() + ", Name: " + getName() + ", Department: " + getDepartment();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return getId() == teacher.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
