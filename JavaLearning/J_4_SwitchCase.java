public class J_4_SwitchCase {
    public class Switch {
        public static void main(String[] args) {
            int n=5;
            switch(n){
                case 1:
                    System.out.println("monday");
                    break;
                case 2:
                    System.out.println("monday");
                    break;
                case 3:
                    System.out.println("monday");
                    break;
                case 4:
                    System.out.println("monday");
                    break;
                case 5:
                    System.out.println("monday");
                    break;
                case 6:
                    System.out.println("monday");
                    break;
                case 7:
                    System.out.println("sunday");
                    break;
                default:
                    System.out.println("enter the valid week day");
            }


            switch(n){
                case 1 -> System.out.println("monday");

                case 2-> System.out.println("monday");
                case 3->System.out.println("monday");

                case 4->System.out.println("monday");

                case 5->System.out.println("monday");

                case 6->System.out.println("monday");

                case 7->System.out.println("sunday");

                default->System.out.println("enter the valid week day");
            }

            String day="sunday";

            String result ="";
            switch(day){
                case "monday" ->result="7.40am";

                case "saturday","sunday"-> result="10am";

                default->System.out.println("enter the valid week day");


            }
            System.out.println(result);

            String result1 = switch(day){
                case "monday" ->"7.40am";

                case "saturday","sunday"-> "10am"; // it returns the value

                default->"enter the valid week day";
            };
            System.out.println(result1);

            String result2 = switch(day){
                case "monday" :yield "7.40am";

                case "saturday","sunday":yield "10am"; // it returns the value

                default: yield "enter the valid week day";
            };
            System.out.println(result2);
        }
    }
}
