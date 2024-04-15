package ex1;

public class Adapter extends Employee{

    public Adapter(Empregado empregado){
        super(empregado.nome() + ' ' + empregado.apelido(), empregado.codigo(), empregado.salario());
    }


}
