package org.poo.cb.comand;

import org.poo.cb.*;

public class CumpActiuniComanda implements Comanda{
    private Cont cont;
    private Actiuni actiuni;
    private String fisier;
    public CumpActiuniComanda(Cont cont, Actiuni actiuni, String fisier) {
        this.cont = cont;
        this.actiuni = actiuni;
        this.fisier = fisier;
    }

    @Override
    public void operatieExecutata(String argument) {
        actiuni.cumparaActiuni(fisier, argument, cont);
    }
}
