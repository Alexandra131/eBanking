package org.poo.cb.comand;
import org.poo.cb.Cont;
public class AdaugaBaniComanda implements Comanda{
    private Cont cont;
    public AdaugaBaniComanda(Cont cont) {
        this.cont = cont;
    }

    @Override
    public void operatieExecutata(String argument) {
        cont.adaugaBaniCont(argument);
    }
}
