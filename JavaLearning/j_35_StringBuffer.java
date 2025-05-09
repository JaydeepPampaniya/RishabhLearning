public class j_35_StringBuffer {
    public static void main(String[] args) {
        String name = "jaydeep";
        StringBuffer s1 = new StringBuffer(name);
        s1.append(" Pampaniya");
        s1.deleteCharAt(2);
        
        s1.insert(0, "Java ");
        s1.setLength(30);
        System.out.println(s1.capacity());
        System.out.println(s1);
    }
}
