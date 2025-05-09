class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class j_80_ThrowInException {
    public static void main(String[] args) {
        int i=20;
        int j=0;
        try{
            j=18/i;
            if(j==0)
                throw new MyException("you can't divide by zero");

        }catch(Exception e){
            j=18/1;
            System.out.println("thats it default output" + e);
        }
        System.out.println(j);
    }
}