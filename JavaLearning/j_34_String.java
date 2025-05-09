public class j_34_String {
    public static void main(String[] args) {
        String name = new String("jaydeep");
        System.out.println("hello "+name);
        System.out.println(name.hashCode());
        System.out.println(name.charAt(1));
        System.out.println(name.compareToIgnoreCase("jAYDEP"));
        System.out.println(name.compareTo(name));
        System.out.println(name.equals(name));
        System.out.println(name.concat(" pampaniya"));
        System.out.println(name);
        System.out.println(1);

        //strings are immutable
        String s2="jaydeep";
        String s3="jaydeep";
        //here s2 and s3 both reference varible stored in same heap memory location because string is class and s2 and s3 is object 
        //so sobject are stored in same healp location
    }
}
