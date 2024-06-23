package factoryPatterm.carFactory;

public class User {
    public static void main(String[] args){
        // Apply Factory Patterm
        getCar("Honda").viewCar();
        getCar("Honda").driveCar();
    }

    public static CarAbstractClass getCar(String carName){
        CarAbstractClass car = null;
        if (carName.equals("Honda")){
            car = new Honda();
        } else if (carName.equals("Huyndai")) {
            car = new Huyndai();
        } else if (carName.equals("Toyota")){
            car = new Toyota();
        } else {
            throw new RuntimeException("Car name is not valid");
        }
        return car;
    }
}
