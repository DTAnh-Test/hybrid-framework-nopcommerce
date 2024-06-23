package accessModifier.phuongKhueTrung;

import accessModifier.phuongHoaXuan.AZ_Coffee;

public class B_House {
    // 4 - Truy cập ngoài package không bởi class con
    public static void main(String[] args){
        AZ_Coffee az = new AZ_Coffee();
        // public
        System.out.println(az.espresso);
        az.shipEspresso();

        // protected: Không truy cập được
            // az.shipCapuchino();
            // System.out.println(az.capuchino);

        // default: Không truy cập được
            // az.shipHxCoffee();
            // System.out.println(az.hxCoffee);

        // private: Không truy cập được
            // System.out.println(az.azCoffee);
    }
}
