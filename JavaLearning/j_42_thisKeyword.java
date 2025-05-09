class privacy{
    private String email;
    private String pass;
    private int age;


   
    public void setEmail(String email){
        
        this.email = email;
    }
    public void setPass(String pass){
        privacy password = new privacy();
        password.pass = pass;
    }
    public void setAge(int age,privacy h1){
        h1.age = age;
    }
    public String getEmail(){
        return email;
    }
    public String getPass(){
        return pass;
    } 
    public int getAge(){
        return age;
    }

}

public class j_42_thisKeyword {
    public static void main(String[] args) {
        privacy p1 = new privacy();
        p1.setEmail("jaydeeppampaniya77@gmail.com");
        p1.setPass("Jaydeep@77");
        p1.setAge(25,p1);
        System.out.println(p1.getEmail());
        System.out.println(p1.getPass());
        System.out.println(p1.getAge());
        int $jaydeep = 5;
        int _jaydeep = 5;
    }
}
