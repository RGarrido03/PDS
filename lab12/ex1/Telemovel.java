package ex1;

public class Telemovel{

    protected static String processador;
    protected static int preco;
    protected static int memoria;
    protected static String camara;
    protected static String description;

    public Telemovel(String processador, int preco, int memoria, String camara) {
        Telemovel.processador = processador;
        Telemovel.preco = preco;
        Telemovel.memoria = memoria;
        Telemovel.camara = camara;
    }

    public int getMemory()
    {
        return memoria;
    }

    public double getPrice() {
        return preco;
    }

    public String getDescription() {
        return description;
    }

    public String getProcessador(){return processador;}

    public String toString(){
        return "Telemovel: " +
                "processador: " + processador +
                "Preço:" + preco +
                "Descrição: " + description;
    }
}
