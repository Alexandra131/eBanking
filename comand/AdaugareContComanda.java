package org.poo.cb.comand;

import org.poo.cb.Cont;

public class AdaugareContComanda implements Comanda{
    private Cont cont;
    public AdaugareContComanda(Cont cont) {
        this.cont = cont;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.adaugareCont(argument);
    }
}
