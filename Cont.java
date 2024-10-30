package org.poo.cb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.text.DecimalFormat;
import org.poo.cb.Bani.*;

public class Cont {
    Map<String, ArrayList<String>> contBancar = new LinkedHashMap<>();
    ArrayList<String> contPremium= new ArrayList<>();
    ArrayList<Bani> vectorBaniFactory= new ArrayList<>();

    public BaniFactory baniFactory = BaniFactory.Instanta();


    public void adaugareCont(String linie) {
        int indexSpatiu = linie.indexOf(" ");
        String email = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;


        linie = linie.replace(email, "").trim();
        indexSpatiu = linie.indexOf(" ");
        String cont = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        if(contBancar.containsKey(email )) {
            ArrayList<String> vectorConturi  = contBancar.get(email );
            if (!vectorConturi.contains(cont)) {
                // Adăugăm valoarea doar dacă nu există deja în listă de conturi respective
                contBancar.get(email ).add(cont);
            }
            else {
                System.out.println("e de mofificat la adaugar cont dublu");
                return;
            }

        } else {
            //daca nu aveam acea cheie
            contBancar.put(email, new ArrayList<>(Arrays.asList(cont)));
        }
    }



    public void adaugaBaniCont(String linie) {
        int indexSpatiu = linie.indexOf(" ");
        String email = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(email, "").trim();
        indexSpatiu = linie.indexOf(" ");
        String contSiNRBani = linie;
        String cont = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        String bani = linie.replace(cont,"").trim();
        float floatBani = Float.parseFloat(bani);

        if(contBancar.containsKey(email )) {
            ArrayList<String> vectorBanca = contBancar.get(email);
            int indexElementDorit = -1;
            for (int i = 0; i < vectorBanca.size(); i++) {
                if (vectorBanca.get(i).contains(cont)) {
                    indexElementDorit = i;
                    break;
                }
            }
            if (indexElementDorit == -1) {
                System.out.println("nu are cont pe moneda respectiva");
            }
            if(vectorBanca.get(indexElementDorit).equals(cont)) {
                vectorBanca.set(indexElementDorit, contSiNRBani);
                return;
            }
            if(vectorBanca.get(indexElementDorit).contains(cont)) {
                String nrBani = vectorBanca.get(indexElementDorit).replace(cont, "").trim();
                float floatBaniExistDeja = Float.parseFloat(nrBani);
                float suma = floatBaniExistDeja + floatBani;
                String adaugat = cont + " " +  suma;
                vectorBanca.set(indexElementDorit, adaugat);


            }
        }else {
            System.out.println("e de mofificat nu are cont la nici-o banca");
            return;
        }


    }
    public void listeazaPortofoliu(String email, Actiuni actiuni) {

        System.out.print("{\"stocks\":[");

        //vedem daca a facut investiti
        if(actiuni.actiuniUtilizatori.containsKey(email)) {
            ArrayList<String> vectorBanca = actiuni.actiuniUtilizatori.get(email);
            for(int i = 0; i < vectorBanca.size(); i++) {
                String linie = vectorBanca.get(i);
                int indexSpatiu = linie.indexOf(" ");
                String firma =  indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
                String investitie = linie.replace(firma,"").trim();
                if(i == vectorBanca.size()-1) {
                    System.out.print("{\"stockname\":\"" + firma + "\",\"amount\":" + investitie + "}");
                } else {
                    System.out.print("{\"stockname\":\"" + firma + "\",\"amount\":" + investitie + "},");
                }
            }
        }


        System.out.print("],\"accounts\":[");
        if(contBancar.containsKey(email )) {
            ArrayList<String> vectorBanca = contBancar.get(email);
            for (int i = 0; i < vectorBanca.size(); i++) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String cont = vectorBanca.get(i);
                if(cont.length() == 3) {
                    System.out.print("{\"currencyName\":\"" + cont + "\",\"amount\":\"0.00\"}");

                } else {
                    int indexSpatiu = cont.indexOf(" ");
                    String numeCont = indexSpatiu != -1 ? cont.substring(0, indexSpatiu) : cont;
                    cont = cont.replace(numeCont, "").trim();
                    float sumaCont = Float.parseFloat(cont);


                    System.out.print("{\"currencyName\":\"" + numeCont + "\",\"amount\":\"" + String.format("%.2f", sumaCont) + "\"}");
                }
                if(i != vectorBanca.size() - 1) {
                    System.out.print(",");
                }

            }
            System.out.println("]}");

        } else {
            System.out.println("nu stiu");
        }
    }
    public void citesteBani(String fisier) {
        String delimitator = ",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fisier))) {
            String linie;

            // Citeste prima linie, care contine numele valutelor
            linie = bufferedReader.readLine();
            String[] valute = linie.split(delimitator);
            int index = 1;

            while ((linie = bufferedReader.readLine()) != null) {
                String[] date = linie.split(delimitator);
                float[] valori = new float[date.length];

                // Converteste șirurile în valori de tip float și le adaugă în vector
                for (int i = 1; i < date.length; i++) {
                    valori[i] = Float.parseFloat(date[i]);
                }

                vectorBaniFactory.add(baniFactory.puneBani(valute[index], valori));

                index++;
           }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare");
        }
    }

    public void schimbaBani(String linie, String fisier) {
        int indexSpatiu = linie.indexOf(" ");
        String email = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(email, "").trim();
        indexSpatiu = linie.indexOf(" ");
        String contDinCareExtrag = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(contDinCareExtrag,"").trim();
        indexSpatiu = linie.indexOf(" ");
        String contInCareAdaug = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(contInCareAdaug,"").trim();
        float baniDoritSchimbati = Float.parseFloat(linie);


        citesteBani(fisier);


        if(contBancar.containsKey(email )) {
            ArrayList<String> vectorConturi  = contBancar.get(email );
            float baniSursa = 0;
            int indexElementDorit = -1;
            for (int i = 0; i < vectorConturi.size(); i++) {
                if (vectorConturi.get(i).contains(contDinCareExtrag)) {
                    indexElementDorit = i;
                    break;
                }
            }
            if(vectorConturi.get(indexElementDorit).equals(contDinCareExtrag)) {
                System.out.println("Cont gol, nu putem extrage");
                return;
            }
            if(vectorConturi.get(indexElementDorit).contains(contDinCareExtrag)) {
                indexSpatiu = vectorConturi.get(indexElementDorit).indexOf(" ");
                String moneda = indexSpatiu != -1 ? vectorConturi.get(indexElementDorit).substring(0, indexSpatiu) : vectorConturi.get(indexElementDorit);
                String baniPeCard = vectorConturi.get(indexElementDorit).replace(moneda,"").trim();
                baniSursa = Float.parseFloat(baniPeCard);
            }

            //trebuie sa verific daca am bani indeajunsi pentru ai transfera
            float baniSchimbati = 0;
            for(Bani bani : vectorBaniFactory) {
               String moneda = bani.toString();
               if(moneda.equals(contInCareAdaug)) {
                   switch (contDinCareExtrag) {
                       case "EUR":
                           baniSchimbati = baniDoritSchimbati * bani.euro;
                       case "GBP":
                           baniSchimbati = baniDoritSchimbati * bani.gbp;
                       case "JPY":
                           baniSchimbati = baniDoritSchimbati * bani.jpy ;;
                       case "CAD":
                           baniSchimbati = baniDoritSchimbati * bani.cad;
                       case "USD":
                           baniSchimbati = baniDoritSchimbati * bani.usd;
                       default:
                           break;
                   }
               }
            }
            if(baniSchimbati > baniSursa) {
                System.out.println("insufficient amount in account usd for exchange");
                return;
            }
            if(!contPremium.contains(email))
            if(baniSchimbati > baniSursa/2) {
                baniSchimbati = baniSchimbati*1/100 + baniSchimbati;

            }


            baniSursa = baniSursa - baniSchimbati;

            vectorConturi.set(indexElementDorit, contDinCareExtrag + " " + baniSursa);

            for (int i = 0; i < vectorConturi.size(); i++) {
                if (vectorConturi.get(i).contains(contInCareAdaug)) {
                    if(vectorConturi.get(i).equals(contInCareAdaug)) {
                        vectorConturi.set(i, contInCareAdaug + " " + baniDoritSchimbati);
                        break;
                    } else {
                        indexSpatiu = vectorConturi.get(i).indexOf(" ");
                        String moneda = indexSpatiu != -1 ? vectorConturi.get(i).substring(0, indexSpatiu) : vectorConturi.get(i);
                        moneda = vectorConturi.get(i).replace(moneda,"").trim();
                        float pretMoneda = Float.parseFloat(moneda);
                        baniDoritSchimbati = baniDoritSchimbati + pretMoneda;
                        vectorConturi.set(i, contInCareAdaug + " " + baniDoritSchimbati);
                        break;
                    }

                }
            }
        }

    }
    public void transferBani (String linie, Utilizator utilizator) {
        int indexSpatiu = linie.indexOf(" ");
        String emailTrimite = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(emailTrimite, "").trim();
        indexSpatiu = linie.indexOf(" ");
        String emailPrimeste = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        linie = linie.replace(emailPrimeste,"").trim();
        indexSpatiu = linie.indexOf(" ");
        String moneda = indexSpatiu != -1 ? linie.substring(0, indexSpatiu) : linie;
        String bani = linie.replace(moneda,"").trim();
        float baniTrTrimisi = Float.parseFloat(bani);



        //vreau sa verific daca sunt priteni
        if(utilizator.lisaPrieteniUtilizatori.containsKey(emailTrimite)) {
            ArrayList<String> utliDejaUrm  = utilizator.lisaPrieteniUtilizatori.get(emailTrimite);
            if (utliDejaUrm.contains(emailPrimeste)) {
                //sunt prieteni si trebuie sa verific daca au amandoi cont in moneda respectiva
                //pentru cel care trimite
                float baniUtiliTrimite = 0;
                int indexElementDorit = -1;
                ArrayList<String> vectorBancaUnu = new ArrayList<>();
                if(contBancar.containsKey(emailTrimite)) {
                   vectorBancaUnu = contBancar.get(emailTrimite);
                    for (int i = 0; i < vectorBancaUnu.size(); i++) {
                        if (vectorBancaUnu.get(i).contains(moneda)) {
                            indexElementDorit = i;
                            break;
                        }
                    }
                    if (indexElementDorit == -1) {
                        System.out.println("nu are cont pe moneda respectiva si eu sunt la trnafer bani");
                        return;
                    }
                    if(vectorBancaUnu.get(indexElementDorit).equals(moneda)) {
                        System.out.println("Nu are bani in cont pentru a trimite");
                        return;
                    }
                    indexSpatiu = vectorBancaUnu.get(indexElementDorit).indexOf(" ");
                    String baniTotalUtilTrimite =  indexSpatiu != -1 ? vectorBancaUnu.get(indexElementDorit).substring(0, indexSpatiu) : vectorBancaUnu.get(indexElementDorit);
                    baniTotalUtilTrimite = vectorBancaUnu.get(indexElementDorit).replace(baniTotalUtilTrimite, "").trim();
                    baniUtiliTrimite = Float.parseFloat(baniTotalUtilTrimite);
                    if(baniUtiliTrimite < baniTrTrimisi) {
                        System.out.println("insufficient amount in account eur for transfer");
                        return;
                    }
                }

                //daca al doilea are cont la banca respectiva
                if(contBancar.containsKey(emailPrimeste)) {
                    ArrayList<String> vectorBanca = contBancar.get(emailPrimeste);
                    int indexPrimeste = -1;
                    for (int i = 0; i < vectorBanca.size(); i++) {
                        if (vectorBanca.get(i).contains(moneda)) {
                            indexPrimeste = i;
                            break;
                        }
                    }
                    if (indexPrimeste == -1) {
                        System.out.println("nu are cont pe moneda respectiva si nu poate primii bani");
                        return;
                    }
                    if(vectorBanca.get(indexPrimeste).equals(moneda)) {
                        vectorBanca.set(indexPrimeste, moneda + " " + baniTrTrimisi);
                        baniUtiliTrimite = baniUtiliTrimite - baniTrTrimisi;
                        vectorBancaUnu.set(indexElementDorit, moneda + " " + baniUtiliTrimite);
                        return;
                    }

                    indexSpatiu = vectorBanca.get(indexPrimeste).indexOf(" ");
                    String baniTotalUtilPrimeste =  indexSpatiu != -1 ? vectorBanca.get(indexPrimeste).substring(0, indexSpatiu) : vectorBanca.get(indexPrimeste);
                    baniTotalUtilPrimeste = vectorBanca.get(indexPrimeste).replace(baniTotalUtilPrimeste,"").trim();
                    float baniUtiliPrimeste = Float.parseFloat(baniTotalUtilPrimeste);
                    baniUtiliPrimeste = baniUtiliPrimeste + baniTrTrimisi;
                    baniUtiliTrimite = baniUtiliTrimite - baniTrTrimisi;
                    vectorBanca.set(indexPrimeste, moneda + " " + baniUtiliPrimeste);
                    vectorBancaUnu.set(indexElementDorit, moneda + " " + baniUtiliTrimite);

                }

            } else {
                System.out.println("nu avem la prieteni nu avem cum sa trimitem");
                return;
            }
        }

    }

    public void contPremium(String email, Utilizator utilizator) {
        String moneda = "USD";
        int valoare = 100;
        if(utilizator.adresaMail.contains(email)) {
            if(contPremium.contains(email)) {
                System.out.println("The user has a premium account");
            } else {
                //verific daca are bani sufiecienti dar stim deja ca exista
                ArrayList<String> vectorBanca = contBancar.get(email);
                int indexPrimeste = -1;
                for (int i = 0; i < vectorBanca.size(); i++) {
                    if (vectorBanca.get(i).contains(moneda)) {
                        indexPrimeste = i;
                        break;
                    }
                }
                int indexSpatiu = vectorBanca.get(indexPrimeste).indexOf(" ");
                String baniTotalUtil =  indexSpatiu != -1 ? vectorBanca.get(indexPrimeste).substring(0, indexSpatiu) : vectorBanca.get(indexPrimeste);
                baniTotalUtil = vectorBanca.get(indexPrimeste).replace(baniTotalUtil,"").trim();
                float baniUtili = Float.parseFloat(baniTotalUtil);
                if(baniUtili < valoare) {
                    System.out.println("Insufficient amount in account for buying premium option");
                    return;
                }
                baniUtili = baniUtili - valoare;
                vectorBanca.set(indexPrimeste,moneda + baniUtili);
                contPremium.add(email);

            }
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
    }
}
