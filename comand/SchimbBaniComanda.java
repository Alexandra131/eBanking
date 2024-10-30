package org.poo.cb.comand;
import org.poo.cb.*;
public class SchimbBaniComanda implements Comanda{
    private Cont cont;
    private String fisier;
    public SchimbBaniComanda(Cont cont, String fisier) {
        this.cont = cont;
        this.fisier = fisier;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.schimbaBani(argument, fisier);
    }
}
