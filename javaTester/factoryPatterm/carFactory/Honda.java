package factoryPatterm.carFactory;

public class Honda extends CarAbstractClass {

    @Override
    public void viewCar() {
        System.out.println("Viewing Honda Cả");
    }

    @Override
    public void driveCar() {
        System.out.println("Driving Honda Car");
    }
}
