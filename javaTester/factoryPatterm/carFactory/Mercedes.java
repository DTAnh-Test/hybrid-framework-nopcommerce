package factoryPatterm.carFactory;

public class Mercedes implements CarInterface{

    @Override
    public void viewCar() {
        System.out.println("Viewing Mercedes Car");
    }

    @Override
    public void driveCar() {
        System.out.println("Driving Mercedes Car");
    }
}
