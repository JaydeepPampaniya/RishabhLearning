class JaydeepException extends Exception{ //also we can use RuntimeException
    public JaydeepException(String msg){
        super(msg);
    }
}

public class j_81_userException {
    public static void main(String[] args) {
        int i=32;
        int j=0;
        try{
            j=18/i;
            if(j==0){
                throw new JaydeepException("I dont want to print a zero");
            }
        }catch(JaydeepException e){
            System.out.println("thats a default output "+e);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
