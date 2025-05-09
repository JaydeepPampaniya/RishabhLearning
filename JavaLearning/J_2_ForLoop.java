public class J_2_ForLoop {
    public static void main(String[] args) {
        for(int i=1;i<=7;i++){
            System.out.println("day"+i);
            for(int j=1; j<=9;j++){
                // System.out.println(" "+j+8); // must be see there numbers are concatinate into both values
                System.out.println(" "+(j+8));
                // System.out.println(" "+(j+8)+"-"+(j+9));

            }
        }
        int k=1;
        for(;k<=7;k++){
            System.out.println("day"+k);
            for(int j=1; j<=9;j++){
                //System.out.println(" "+j+8); // must be see there numbers are concatinate into both values
                // System.out.println(" "+(j+8));
                // System.out.println(" "+(j+8)+"-"+(j+9));

            }
        }
    }
}
