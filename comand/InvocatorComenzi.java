package org.poo.cb.comand;

public class InvocatorComenzi {
    private Comanda command;

   public void setCommand(Comanda command) {
        this.command = command;
    }

    public void executareComenzi(String argument) {
        command.operatieExecutata(argument);
    }
}
