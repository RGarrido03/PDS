package ex1;

import java.util.ArrayList;
import java.util.List;

public class Registos {

    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }
    public void insere(Empregado emp) { // Code to insert employee
        empregados.add(emp);
    }

    public void remove(int codigo) { // Code to remove employee
        empregados.remove((Integer) codigo);
    }
    public boolean isEmpregado(int codigo) { // Code to find employee
        return empregados.stream().map(Empregado::codigo).anyMatch(n -> n == codigo);
    }

    public List<Empregado> listaDeEmpregados() { // Code to retrieve collection
        return empregados;
    }

}
