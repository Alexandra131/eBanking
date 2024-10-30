package org.poo.cb.comand;
import org.poo.cb.Utilizator;
public class ListUtilizatoriComanda implements Comanda{
    private Utilizator utilizator;
    public  ListUtilizatoriComanda (Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    @Override
    public void operatieExecutata(String argument) {
        utilizator.listeazaUtlizator(argument);
    }
}
