package general;

public abstract class Person {
    private int id;
    private String name;
    private String department;

    protected Person(int id,String name, String department){
        this.id = id;
        this.name=name;
        this.department=department;
    }
    public abstract String toString();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
