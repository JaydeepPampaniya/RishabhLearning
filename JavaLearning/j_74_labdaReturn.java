interface ax{
    int sum(int a,int b);
}

public class j_74_labdaReturn {
    public static void main(String[] args) {
        ax a = (i,j)->
            {
                return i+j;
            };
        ax aj = (i,j)-> i-j;

        
        int result = a.sum(9,5);
        int result2 = aj.sum(9,5);
        System.out.println(result);
        System.out.println(result2);
    }
    
}
