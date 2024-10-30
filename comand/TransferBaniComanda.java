package org.poo.cb.comand;
import org.poo.cb.*;
public class TransferBaniComanda implements Comanda{
    private Cont cont;
    private Utilizator utilizator;
    public TransferBaniComanda(Cont cont, Utilizator utilizator) {
        this.cont = cont;
        this.utilizator = utilizator;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.transferBani(argument, utilizator);
    }
}
