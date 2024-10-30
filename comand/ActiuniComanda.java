package org.poo.cb.comand;

import org.poo.cb.Actiuni;

public class ActiuniComanda implements Comanda{
    private Actiuni actiuni;

    public ActiuniComanda(Actiuni actiuni) {
        this.actiuni = actiuni;

    }

    @Override
    public void operatieExecutata(String argument) {
        actiuni.recomandStoc(argument);
    }
}
