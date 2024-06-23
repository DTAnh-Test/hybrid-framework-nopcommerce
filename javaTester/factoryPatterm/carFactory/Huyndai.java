package factoryPatterm.carFactory;

public class Huyndai extends CarAbstractClass{

    @Override
    public void viewCar() {
        System.out.println("Viewing Huyndai Car");
    }

    @Override
    public void driveCar() {
        System.out.println("Driving Huyndai Car");
    }
}
