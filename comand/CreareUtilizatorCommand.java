package org.poo.cb.comand;

import org.poo.cb.Utilizator;

public class CreareUtilizatorCommand implements Comanda {
    private Utilizator utilizator;

   public CreareUtilizatorCommand(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    @Override
    public void operatieExecutata(String argument) {
        utilizator.adaugaUtilizator(argument);
    }
}
