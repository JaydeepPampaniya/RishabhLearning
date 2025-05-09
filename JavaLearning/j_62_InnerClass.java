class outer{
    public void show(){
        System.out.println("show 1");
    }
    class inner{
        public void showInner(){
            System.out.println("showInner");
        }
    }
    static class inner1{ //inner class can abstract,static,final,protected,private,public all are allowed to create.
        public void showInner(){
            System.out.println("static showInner");
        }
    }
}


public class j_62_InnerClass {
    public static void main(String[] args) {
        outer a = new outer();

        outer.inner b = a.new inner();
        b.showInner();
        outer.inner1 b1 = new outer.inner1();
        b1.showInner();
    }
}
