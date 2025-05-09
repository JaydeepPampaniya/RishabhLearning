class privacy1{
    private String email;
    private String pass;


    public String getEmail(){
        return email;
    }
    public String getPass(){
        return pass;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setPass(String Pass){
        pass = Pass;
    }

}

public class j_40_encapsulation {
    public static void main(String[] args) {
        privacy1 p1 = new privacy1();
        p1.setEmail("jaydeeppampaniya77@gmail.com");
        p1.setPass("Jaydeep@77");
        System.out.println(p1.getEmail());
        System.out.println(p1.getPass());
        
    }
}
