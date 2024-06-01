package AccessModifier.phuongHoaXuan;

public class AZ_Coffee {
    // Property/ Field/ Variable
    public String espresso = "Espresso Coffee";

    // Function/ Method
    public void shipEspresso(){
        System.out.println("Ship" + espresso);
    }

    protected String capuchino = "Capuchino Coffee";

    protected void shipCapuchino(){
        System.out.println("Ship" + capuchino);
    }

    // default:
    String hxCoffee = "HX Coffee";
    void shipHxCoffee(){
        System.out.println("Ship" + hxCoffee);
    }

    // private:
    private String azCoffee = "AZ Coffee";

    // 1 - Sử dụng trong cùng Class
    public static void main(String[] args){
        AZ_Coffee az = new AZ_Coffee();
        // public
        System.out.println(az.espresso);
        az.shipEspresso();

        // protected
        System.out.println(az.capuchino);
        az.shipCapuchino();

        // default
        System.out.println(az.hxCoffee);
        az.shipHxCoffee();

        // private:
        System.out.println(az.azCoffee);
    }
}
