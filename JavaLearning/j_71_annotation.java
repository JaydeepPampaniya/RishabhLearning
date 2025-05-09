class aj {
    public void showTheDataWhichBelongsToThis(){
        System.out.println("class aj");
    }
}
class dd extends aj{
    @Override // helps you to override the method and same name
    //annotation has runtime and compiletime both are usefull
    public void showTheDataWhichBelongsToThis(){
        System.out.println("class dd");
    }
}
public class j_71_annotation{
    public static void main(String[] args) {
        dd a = new dd();
        a.showTheDataWhichBelongsToThis();
    }
}