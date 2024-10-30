package org.poo.cb;
import org.poo.cb.comand.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        int index = args[1].indexOf("/");
        String numeTest = index != -1 ? args[1].substring(0, index) : args[1];

        String fiser = "src/main/resources/" + numeTest + "/commands.txt";
        String fisier2 = "src/main/resources/common/exchangeRates.csv";
        String fisier3 = "src/main/resources/" + numeTest + "/stockValues.csv";
        Utilizator utilizator = new Utilizator();
        Cont cont = new Cont();
        Actiuni actiuni = new Actiuni();
        InvocatorComenzi comenzi = new InvocatorComenzi();

        try (BufferedReader readerNoteUnu = new BufferedReader(new FileReader(fiser))) {
            String linie;
            while ((linie = readerNoteUnu.readLine()) != null) {
                if(linie.contains("CREATE USER")) {
                    linie = linie.replace("CREATE USER", "").trim();
                    comenzi.setCommand(new CreareUtilizatorCommand(utilizator));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("ADD FRIEND")) {
                    linie = linie.replace("ADD FRIEND", "").trim();
                    comenzi.setCommand(new AdaugaPrieteniComanda(utilizator));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("LIST USER")) {
                    linie = linie.replace("LIST USER","").trim();
                    comenzi.setCommand(new ListUtilizatoriComanda(utilizator));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("ADD ACCOUNT")) {
                    linie = linie.replace("ADD ACCOUNT","").trim();
                    comenzi.setCommand(new AdaugareContComanda(cont));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("ADD MONEY")) {
                    linie = linie.replace("ADD MONEY","").trim();
                    comenzi.setCommand(new AdaugaBaniComanda(cont));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("LIST PORTFOLIO")) {
                    linie = linie.replace("LIST PORTFOLIO","").trim();
                    comenzi.setCommand(new ListPortofoliuComanda(actiuni, cont));
                    comenzi.executareComenzi(linie);
                }
                if(linie.contains("EXCHANGE MONEY")) {
                    linie = linie.replace("EXCHANGE MONEY","").trim();
                    comenzi.setCommand(new SchimbBaniComanda(cont,fisier2));
                    comenzi.executareComenzi(linie);
                }
                if (linie.contains("TRANSFER MONEY")) {
                    linie = linie.replace("TRANSFER MONEY","").trim();
                    comenzi.setCommand(new TransferBaniComanda(cont,utilizator));
                    comenzi.executareComenzi(linie);
                }
                if (linie.contains("RECOMMEND STOCKS")) {
                    comenzi.setCommand(new ActiuniComanda(actiuni));
                    comenzi.executareComenzi(fisier3);
                }
                if (linie.contains("BUY STOCKS")) {
                    linie = linie.replace("BUY STOCKS","").trim();
                    comenzi.setCommand(new CumpActiuniComanda(cont,actiuni,fisier3));
                    comenzi.executareComenzi(linie);
                }
                if (linie.contains("BUY PREMIUM")) {
                    linie = linie.replace("BUY PREMIUM","").trim();
                    comenzi.setCommand(new PremiumComanda(cont, utilizator));
                    comenzi.executareComenzi(linie);
                }
            }
        } catch (Exception e) {
            System.out.println("Eroare la citire din fisier");
        }
    }
}