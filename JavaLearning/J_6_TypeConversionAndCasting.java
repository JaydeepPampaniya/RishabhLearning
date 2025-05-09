public class J_6_TypeConversionAndCasting {
    public static void main(String[] args) {
        int a=45;
        byte b=1;
        //  b=a; cnannot convert int to byte but byte to int conversion is posiible
        a=b;
        // we can also cnvert a into byte using casting for example
        b=(byte)a; // casting

        float c = 5.8f;
        int x= (int)c;
        //conversion is automatic convert and casting means explicitly convert

        int d = 5;
        boolean z = c>d;
        System.out.println(z);

        if(c>d){
            System.out.println(d);
        }else
            System.out.println("c"+c);
    }
}
