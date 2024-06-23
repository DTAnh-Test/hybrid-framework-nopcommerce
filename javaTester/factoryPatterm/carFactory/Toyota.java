package factoryPatterm.carFactory;

public class Toyota extends CarAbstractClass{
    @Override
    public void viewCar() {
        System.out.println("Viewing Toyota Car");
    }

    @Override
    public void driveCar() {
        System.out.println("Driving Toyota Car");
    }
}
