// we cannot create a multiple function in lamda expression usase
@FunctionalInterface
interface ann{
    void show(int i,int j);
}

public class j_73_LamdaExpression {
    public static void main(String[] args) {
        ann obj = (i,j)-> System.out.println("in show, the sum of two value "+ (i+j));
        ann obj2 = (i,j)-> System.out.println("in show, the minus of two value "+ (i-j));
        ann obj3 = (i,j)-> System.out.println("jajd"+(i+j));
        obj.show(6,8);
        obj2.show(0, 0);
    }
}
