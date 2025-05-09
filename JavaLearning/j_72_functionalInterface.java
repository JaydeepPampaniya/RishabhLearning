
@FunctionalInterface //only allows one method
interface cc{
    void show();
}
public class j_72_functionalInterface {
    public static void main(String[] args) {
        cc a = new cc() {
            public void show(){
                System.out.println("in Show");
            }
        };
        a.show();
    }
}
