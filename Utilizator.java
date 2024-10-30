package org.poo.cb;

import java.util.*;

public class Utilizator {
    ArrayList<String> numeUtilizator = new ArrayList<>();
    ArrayList<String> prenumeUtilizator = new ArrayList<>();
    ArrayList<String> adresaMail = new ArrayList<>() ;
    ArrayList<String> adresaLocuinta = new ArrayList<>();
    Map<String, ArrayList<String>> lisaPrieteniUtilizatori = new LinkedHashMap<>();


    public void adaugaUtilizator(String linie) {
        int indexSpatiu = linie.indexOf(" ");
        String email = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        //verific daca exista acest utilizator
        if(adresaMail.contains(email)) {
            System.out.println("user with " + email + " already exists");
            return;
        }
        adresaMail.add(email);
        linie = linie.replace(email, "").trim();


        indexSpatiu = linie.indexOf(" ");
        String numeFamilie = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        numeUtilizator.add(numeFamilie);
        linie = linie.replace(numeFamilie, "").trim();

        indexSpatiu = linie.indexOf(" ");
        String prenume = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        prenumeUtilizator.add(prenume);

        linie = linie.replace(prenume,"").trim();
        adresaLocuinta.add(linie);

    }

    public void adaugaPriteni(String linie) {

        int indexSpatiu = linie.indexOf(" ");
        String primulEmail = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;

        String alDoileaEmail = linie.replace(primulEmail, "").trim();


        if(lisaPrieteniUtilizatori.containsKey(primulEmail)) {
            ArrayList<String> utliDejaUrm  = lisaPrieteniUtilizatori.get(primulEmail);
            if (!utliDejaUrm.contains(alDoileaEmail)) {
                // Adăugăm valoarea doar dacă nu există deja în listă prietenul respectiv
                lisaPrieteniUtilizatori.get(primulEmail).add(alDoileaEmail);
            } else {
                System.out.println("User with " + alDoileaEmail+  " is already a friend");
                return;
            }

        } else {
            //daca nu aveam acea cheie
            lisaPrieteniUtilizatori.put(primulEmail, new ArrayList<>(Arrays.asList(alDoileaEmail)));
        }
        if(lisaPrieteniUtilizatori.containsKey(alDoileaEmail)) {
            lisaPrieteniUtilizatori.get(alDoileaEmail).add(primulEmail);
        } else {
            //daca nu aveam acea cheie
            lisaPrieteniUtilizatori.put(alDoileaEmail, new ArrayList<>(Arrays.asList(primulEmail)));
        }

    }

    public void listeazaUtlizator(String linie) {
        int index = -1;
        for(int i = 0; i < adresaMail.size(); i++) {
            if(adresaMail.get(i).equals(linie)) {
                index = 1;
            }
        }
        if(index == -1) {
            System.out.println("user with "  + linie + " doesn't exist");
            return;
        }

        System.out.print("{\"email\":\"" + linie + "\",\"firstname\":\"");
        int pozitie = adresaMail.indexOf(linie);
        System.out.print(numeUtilizator.get(pozitie) + "\",\"lastname\":\"" + prenumeUtilizator.get(pozitie) + "\",\"address\":\"" + adresaLocuinta.get(pozitie) + "\",\"friends\":[");
        if(lisaPrieteniUtilizatori.containsKey(linie)) {
            ArrayList<String> valori = lisaPrieteniUtilizatori.get(linie);
            for (String valoare : valori) {
                System.out.print("\"" + valoare + "\"");
            }
        }
        System.out.println("]}");
    }
}
