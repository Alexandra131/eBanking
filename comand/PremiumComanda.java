package org.poo.cb.comand;

import org.poo.cb.*;

public class PremiumComanda implements Comanda{
    private Cont cont;
    private Utilizator utilizator;
    public PremiumComanda(Cont cont, Utilizator utilizator) {
        this.cont = cont;
        this.utilizator = utilizator;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.contPremium(argument,utilizator);
    }
}
