package ex1;

public class VehicleFactory {
    public static Vehicle createMotocycle(String licensePlate, String maker, String model, int power, String type) {
        return new Motocycle(licensePlate, maker, model, power,
                             type.equals("desportivo") ? MotocycleType.SPORT : MotocycleType.ROAD);
    }

    public static Vehicle createAutomovelLigeiro(String licensePlate, String maker, String model, int power, String vin, int trunkCapacity) {
        return new Car(licensePlate, maker, model, power, vin, trunkCapacity);
    }

    public static Vehicle createTaxi(String licensePlate, String brand, String model, int power, String vin, int trunkCapacity, String licenseNumber) {
        return new Taxi(licensePlate, brand, model, power, vin, trunkCapacity, licenseNumber);
    }

    public static Vehicle createBus(String licensePlate, String brand, String model, int power, int weight, String vin, int maxPassengers) {
        return new Bus(licensePlate, brand, model, power, weight, vin, maxPassengers);
    }

    public static Vehicle createElectricCar(String licensePlate, String brand, String model, int power, String vin, int trunkCapacity, int autonomy, int year) {
        return new ElectricCar(licensePlate, brand, model, power, vin, trunkCapacity, autonomy, year);
    }

    public static Vehicle createTruck(String licensePlate, String brand, String model, int power, String vin, int weight, int maxLoad) {
        return new Truck(licensePlate, brand, model, power, vin, weight, maxLoad);
    }

    public static Vehicle createElectricBus(String licensePlate, String brand, String model, int power, int weight, String vin, int maxPassengers, int autonomy, int year) {
        return new ElectricBus(licensePlate, brand, model, power, weight, vin, maxPassengers, autonomy, year);
    }
}
