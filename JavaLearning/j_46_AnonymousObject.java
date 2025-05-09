class a{
    a(){
        System.out.println("object created");

    }
    public void show(){
        System.out.println("method created");
    }
}


public class j_46_AnonymousObject {
    public static void main(String[] args) {
        new a(); // this is anonymnous object
        new a().show();
    }
}
