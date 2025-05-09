class Aws {
    public void show() throws ClassNotFoundException{
        Class.forName("j_82_Throws");
    }
}

public class j_82_Throws {
    static{
        int num =5;
        System.out.println("class executed");
    }
    public static void main(String[] args) {  // we can throws the exception at this line but its not recommonded to throws exception in this class  
        Aws aa = new Aws();
        try{
            aa.show();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
