package ex1;

public class Telemovel{

    protected String processador;
    protected int preco;
    protected int memoria;
    protected String description;

    public Telemovel(int preco, int memoria, String description) {
        this.preco = preco;
        this.memoria = memoria;
        this.description = description;
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

    public String toString(){
        return "Telemovel: " +
                " processador: " + processador +
                " Preço: " + preco +
                " Descrição: " + description;
    }
}
