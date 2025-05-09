//we have a some wrapper class like Integer,String,Double 
// which extends object class by default

public class j_60_WrapperClass {
    public static void main(String[] args) {
        
        int num=7;
        Integer num1 = num; //auto boxing
        Integer num5 = new Integer(num);
        int num2 = num1.intValue(); //unboxing
        int num3 = num1; // auto-unboxing
        System.out.println(num3);


        String str = "12";
        int num4 = Integer.parseInt(str);
        System.out.println(num4*3);


    }
    
}
