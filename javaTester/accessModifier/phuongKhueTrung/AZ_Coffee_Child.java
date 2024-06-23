package accessModifier.phuongKhueTrung;

import accessModifier.phuongHoaXuan.AZ_Coffee;

public class AZ_Coffee_Child extends AZ_Coffee {

    public static void main(String[] args) {
        AZ_Coffee_Child azChild = new AZ_Coffee_Child();
        azChild.buildRoom();
        System.out.println(azChild.espresso);
        azChild.shipEspresso();
    }

    // 3 - Khác package nhưng là class con
    public void buildRoom(){
        // public
        System.out.println(espresso);
        shipEspresso();

        // protected
        System.out.println(capuchino);
        shipCapuchino();

        // default: : Không truy cập được
            // az.shipHxCoffee();
            // System.out.println(az.hxCoffee);

        // private: Không truy cập được
            // System.out.println(azCoffee);
    }
}
