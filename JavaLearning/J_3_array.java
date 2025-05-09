import java.util.List;

class abj {
    public int jp(){
        return 0;
    }
    public int jp(int a){
        return a;
    }
    public double jp(double a,int b){
        return a;
    }
}

public class J_3_array {
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 5;
        int[] k = {5, 6, 7, 8};
        String cars[] = new String[5];
        cars[0]="Bmw";

        System.out.println("length" + k.length);
        int twod[][] = new int[3][4];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                twod[i][j] = (int) (Math.random() * 100);

            }
            System.out.println();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.print(twod[i][j] + " ");
            }
            System.out.println();
        }
        for (int n[] : twod) {
            for (int m : n) {
                System.out.print(m + " ");
            }
            System.out.println();
        }


//jagged array
        int klm[][] = {{}, {}, {}};
        int klmx[] = {1, 23, 3};

        int jagged[][] = new int[3][];
        jagged[0] = new int[2];
        jagged[1] = new int[4];
        jagged[2] = new int[3];
        for (int i = 0; i < jagged.length; i++) {
            for (int j = 0; j < jagged[i].length; j++) {
                jagged[i][j] = (int) (Math.random() * 100);
            }
            System.out.println();
        }
        for (int n[] : jagged) {
            for (int m : n) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }
}
