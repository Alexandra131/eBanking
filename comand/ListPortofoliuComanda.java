package org.poo.cb.comand;
import org.poo.cb.Cont;
import org.poo.cb.Actiuni;
public class ListPortofoliuComanda implements Comanda {
    private Cont cont;
    private Actiuni actiuni;
    public ListPortofoliuComanda(Actiuni actiuni, Cont cont) {
        this.actiuni =  actiuni;
        this.cont = cont;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.listeazaPortofoliu(argument, actiuni);
    }
}
