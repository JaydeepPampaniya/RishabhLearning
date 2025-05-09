import java.util.Objects;

public class j_106_studentForSet {
    private String name;
    private int rollNumber;

    public j_106_studentForSet(String name, int rollNumber){
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "j_106_studentForSet [name=" + name + ", rollNumber=" + rollNumber + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber,name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        j_106_studentForSet other = (j_106_studentForSet) obj;
        if (rollNumber != other.rollNumber)
            return false;
        return true;
    }

    
}
