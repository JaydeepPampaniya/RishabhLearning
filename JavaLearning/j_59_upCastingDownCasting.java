class AA{
    public void show1(){
        System.out.println("show1 in A");
    }
}
class BB extends AA{
    public void show2(){
        System.out.println("show2 in B");
    }
}

public class j_59_upCastingDownCasting {
    public static void main(String[] args) {
        AA obj =(AA) new BB(); // UPCASTING
        obj.show1();
       // obj.show2(); // we cannot access a bb class object because we are passing a reference of AA class

        AA obj1 = new BB();
        BB obj2 = (BB)obj1; //down casting
        obj2.show2();
        
    }
}
