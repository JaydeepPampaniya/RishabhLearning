enum Status{ // enum is predefined class and (Running,Failed,Success,Pending) those are predefined objects
    //basically usefull for create user define variable
    Running,Failed,Success,Pending;
}

enum jp{
    
}

public class j_68_enum {
    public static void main(String[] args) {
        Status ss = Status.Running;
        System.out.println(ss);
        System.out.println(ss.ordinal()); //ordinal means position running have 0th position
        System.out.println("hello " + Status.valueOf(Status.Running.name()));
        System.out.println(ss.getClass().getSuperclass());// enum is the extends enum lang class

        Status ss1[] = Status.values();
        System.out.println(ss1[1]);
        for(Status s:ss1){
            System.out.println(s + " : "+ s.ordinal());
        }

        if(ss == Status.Running)
            System.out.println("all good");
        else if(ss == Status.Pending)
            System.out.println("please wait");
        else if(ss == Status.Failed)
            System.out.println("try again");
        else
            System.out.println("done");

            
        switch(ss){
            case Running -> System.out.println("all good"); 
            case Failed -> System.out.println("try again");
            case Pending -> System.out.println("please wait");
            default -> System.out.println("done");
        }

    }
}
