package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Actiuni {
    Map<String, ArrayList<Float>> actiuniFirme = new LinkedHashMap<>();
    Map<String, ArrayList<String>> actiuniUtilizatori = new LinkedHashMap<>();
    ArrayList<String> firmeRecomandate = new ArrayList<>();

    public float sumaTLung = 0;
    public float medieTLung = 0;
    public float medieTScurt = 0;
    public float sumaTScurt = 0;
    public String moneda = "USD";

    public void citireStoc(String fisier) {
        String delimitator = ",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fisier))) {
            String linie;

            // Citeste prima linie, care contine numele valutelor
            linie = bufferedReader.readLine();
            String[] valute = linie.split(delimitator);
            int index = 1;

            while ((linie = bufferedReader.readLine()) != null) {
                String[] date = linie.split(delimitator);
                float valori = 0;
                ArrayList<Float> valoriActiuni = new ArrayList<>();
                // Converteste șirurile în valori de tip float și le adaugă în vector
                for (int i = 1; i < date.length; i++) {
                    valori = Float.parseFloat(date[i]);
                    valoriActiuni.add(valori);
                }

                actiuniFirme.put(date[0], valoriActiuni);


            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare");
        }

    }


    public void verificareFirma(String fisier) {
        citireStoc(fisier);
        //parcurg fisecare cheie
        for (String firma : actiuniFirme.keySet()) {
            //parcurg fiecare valoare asociata firemi
            ArrayList<Float> valori = actiuniFirme.get(firma);
             sumaTLung = 0;
             medieTLung = 0;
             medieTScurt = 0;
             sumaTScurt = 0;
            for (int i = 0; i < valori.size(); i++) {
                //System.out.print("Valoare: " + valori.get(i));
                if(i > 4) {
                    sumaTScurt = sumaTScurt + valori.get(i);
                }
                sumaTLung = sumaTLung + valori.get(i);
            }
            medieTLung = sumaTLung / valori.size();
            medieTScurt = sumaTScurt / 5;
            if(medieTScurt > medieTLung) {
             //   System.out.print("\"" + firma +"\",");
                firmeRecomandate.add(firma);
            }


        }
    }
    public void recomandStoc(String fisier) {
        verificareFirma(fisier);

        System.out.print("{\"stocksToBuy\":[");
        Collections.sort(firmeRecomandate, Collections.reverseOrder());
        for (int i = 0; i < firmeRecomandate.size(); i++) {
            if(i != firmeRecomandate.size() -1 ) {
                System.out.print("\"" + firmeRecomandate.get(i) + "\",");
            } else {
                System.out.println("\"" + firmeRecomandate.get(i) + "\"]}");
            }
        }
    }

    public void cumparaActiuni(String fisier, String linie, Cont cont) {
        citireStoc(fisier);
        int indexSpatiu = linie.indexOf(" ");
        String email = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(email, "").trim();
        String cumparare = linie;
        indexSpatiu = linie.indexOf(" ");
        String domeniuCumparare = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(domeniuCumparare,"").trim();
        int valoare = Integer.parseInt(linie);

        //verificam daca exista email
        if(cont.contBancar.containsKey(email)) {
            ArrayList<String> vectorBanca = cont.contBancar.get(email);

            int indexPrimeste = -1;
            for (int i = 0; i < vectorBanca.size(); i++) {
                if (vectorBanca.get(i).contains(moneda)) {
                    indexPrimeste = i;
                    break;
                }
            }
            if (indexPrimeste == -1) {
                System.out.println("nu are cont pe moneda respectiva si nu poate cumpara actiuni");
                return;
            }
            if(vectorBanca.get(indexPrimeste).equals(moneda)) {
                System.out.println("Nu are bani pentru a cumpara actiuni");
                return;
            }

            indexSpatiu = vectorBanca.get(indexPrimeste).indexOf(" ");
            String baniEmail =  indexSpatiu != -1 ? vectorBanca.get(indexPrimeste).substring(0, indexSpatiu) : vectorBanca.get(indexPrimeste);
            baniEmail = vectorBanca.get(indexPrimeste).replace(baniEmail,"").trim();
            float baniUtili = Float.parseFloat(baniEmail);
            float mediaBaniDati = 0;

            //caut sa vad care actiune este a mea
            if(actiuniFirme.containsKey(domeniuCumparare)) {

                ArrayList<Float> valoriCredit = actiuniFirme.get(domeniuCumparare);
                if(cont.contPremium.contains(email)) {
                    //se face o reducere de 5%;
                    Float reducere = 5 / 100 * valoriCredit.get(valoriCredit.size() - 1);
                    mediaBaniDati = valoriCredit.get(valoriCredit.size() - 1) - reducere;
                    mediaBaniDati = mediaBaniDati *valoare;
                } else {
                    mediaBaniDati = valoriCredit.get(valoriCredit.size() - 1) * valoare;
                }

            }
            //System.out.println(mediaBaniDati);
            if(baniUtili < mediaBaniDati) {
                System.out.println("insufficient amount in account for buying stock");
                return;
            }
            baniUtili = baniUtili - mediaBaniDati;
            vectorBanca.set(indexPrimeste, moneda + " " + baniUtili);
            if(actiuniUtilizatori.containsKey(email)) {
                //daca contine
                ArrayList<String> utliDejaUrm  = actiuniUtilizatori.get(email);
                actiuniUtilizatori.get(email).add(cumparare);
            }else {
                //daca nu contine
                actiuniUtilizatori.put(email, new ArrayList<>(Arrays.asList(cumparare)));
            }

        } else {
            System.out.println("Nu putem cumpara actiuni, nu exista utilizator");
        }
    }
}
