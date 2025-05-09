import java.util.Objects;

class Mobile4 {
    String model;
    int price;

    public String toString() {
        return model + ' ' + price;
    }

    public boolean equals(Mobile4 obj) {
        if (this.model.equals(obj.model) && this.price == obj.price) {
            return true;
        } else {
            return false;
        }
    }
}
class Mobile22{
    String model;
    int price;
    @Override
    public String toString() {
        return "Mobile22 [model=" + model + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mobile22 other = (Mobile22) obj;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (price != other.price)
            return false;
        return true;
    }
    
}

 //case 2: class can override object class hashCode(), toString(), equals()

public class j_58_objectTostringEquals {
    public static void main(String[] args) {
        Mobile22 mb1=new Mobile22();
        mb1.model="Apple";
        mb1.price=100000;

        Mobile22 mb2=new Mobile22();
        mb2.model="Apple";
        mb2.price=100000;

        //use of toString() method,  overrides method
        System.out.println(mb1); //Internally mb1.toString() is called and print Model: Apple and price: 100000
        System.out.println(mb2); // Internally mb2.toString() is called and print Model: Apple and price: 100000

        //use of equals() method to compare two object, overrides method
        boolean result =mb1.equals(mb2); //right now it give true result because we override equals() method
        System.out.println(result); //true

        //use of hashCode()
        System.out.println(mb1.hashCode()); //1967873639 due to overrides hashcode method
        System.out.println(mb2.hashCode());  //1967873639

        System.out.println(mb1==mb2);
    }
}


