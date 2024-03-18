package ex1;

public class Main {
    public static void main(String[] args) {
        Rental r = new Rental("Rental", "1234-567", "a@ua.pt");
        r.addVehicle(VehicleFactory.createMotocycle("00-AB-12", "Honda", "CBR 600", 100, "desportivo"));
        r.addVehicle(VehicleFactory.createAutomovelLigeiro("22-CD-34", "Volkswagen", "Golf", 110, "ABC123456789", 350));
        r.addVehicle(
                VehicleFactory.createTaxi("44-EF-56", "Mercedes-Benz", "E-Class", 150, "GHI123456789", 400, "TAXI123"));
        r.addVehicle(
                VehicleFactory.createElectricBus("77-HI-89", "Tesla", "Model X", 500, 2000, "PPE123456789", 50, 100,
                                                 200));
        r.addVehicle(
                VehicleFactory.createElectricCar("88-IJ-90", "Tesla", "Model 3", 500, "ALE123456789", 500, 1000, 2000));
        r.addVehicle(
                VehicleFactory.createTruck("66-GH-78", "Volvo", "FH", 500, "MNO123456789", 20000, 40000));
        r.addVehicle(VehicleFactory.createBus("99-JK-00", "Volvo", "FH", 500, 2000, "PPE123456789", 50));
        for (Vehicle v : r.getStock()) {
            System.out.println(v);
        }
    }
}
