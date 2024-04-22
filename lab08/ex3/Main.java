package ex3;

public class Main {
    public static void main(String[] args) {
        Box principal = new Box("Principal", 4);
        Box top = new Box("Topo", 2);
        Box bot = new Box("Especialidades", 2);
        top.add(new Beverage("Vinho Reserva UA 2017", 6));
        top.add(new Beverage("Vinho Reserva UA 2018", 6));
        principal.add(top);
        principal.add(bot);
        bot.add(new PreservedFood("Atum à Algarvia", 3));
        bot.add(new Sweet("Morango", 2));
        top.add(new Box("Interior", 1));
        top.add(new PreservedFood("Sardinhas em Azeite", 5));
        principal.draw();
    }
}
