package factoryPatterm.carWithoutFactory;

public class User {
    public static void main(String[] args){
        // Không apply Factory Patterm

        Honda honda = new Honda();
        honda.viewCar();
        honda.driveVar();

        Huyndai huyndai = new Huyndai();
        huyndai.viewCar();
        huyndai.driveVar();

        Toyota toyota = new Toyota();
        toyota.viewCar();
        toyota.driveVar();

    }
}
