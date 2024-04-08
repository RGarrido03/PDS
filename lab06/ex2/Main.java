package ex2;

public class Main {

    public static void main(String[] args) {
        Movie movie =  new Movie.Builder()
                .setTitle("Inception")
                .setDirector(new Person("Bruno Lopes"))
                .setWriter(new Person("Bruno Lopes"))
                .build();

        System.out.println("Movie: " + movie);
    }
}
