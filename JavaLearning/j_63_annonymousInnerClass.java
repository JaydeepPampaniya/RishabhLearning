class outer123{
    public void show(){
        System.out.println("this method belongs to outerClass");
    }
}
public class j_63_annonymousInnerClass {
    public static void main(String[] args) {
        outer123 a1 = new outer123() // here we can see here is no semicolone in this object creation
        {
            public void show(){
                System.out.println("annonymous inner class");  //that is a anonymous inner class 
                // we dont have an class name so we can say it is a annonymous class
            }
        };
        a1.show();
    }
}
