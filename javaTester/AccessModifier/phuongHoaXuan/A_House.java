package AccessModifier.phuongHoaXuan;

public class A_House{

    // 2 - Sử dụng trong cùng Package
    public static void main(String[] args){
        AZ_Coffee az = new AZ_Coffee();
        // public
        System.out.println(az.espresso);
        az.shipEspresso();

        // protected
        az.shipCapuchino();
        System.out.println(az.capuchino);

        // default
        az.shipHxCoffee();
        System.out.println(az.hxCoffee);

        // private: Không truy cập được
            // System.out.println(az.azCoffee);
    }
}
