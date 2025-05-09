abstract class annonymous{
    public abstract void show();
    public abstract void config();
}

public class j_64_annonymousAbstractClass {
    public static void main(String[] args) {
        annonymous s1 = new annonymous() {
            public void show() {
                System.out.println("show");
            }
            public void config() {
                System.out.println("config");
            }
        };
        s1.show();
        s1.config();
    }
}
