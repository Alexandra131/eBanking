package org.poo.cb.comand;
import org.poo.cb.Utilizator;
public class AdaugaPrieteniComanda implements Comanda {
    private Utilizator utilizator;

    public AdaugaPrieteniComanda(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    @Override
    public void operatieExecutata(String argument) {
        utilizator.adaugaPriteni(argument);
    }
}
