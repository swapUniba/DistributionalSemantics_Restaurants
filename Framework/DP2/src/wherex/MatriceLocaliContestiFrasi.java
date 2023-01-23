package wherex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MatriceLocaliContestiFrasi {

    // Per le frasi singole
    // locale -> { contesto -> frase }
    public static HashMap<Integer, HashMap<Integer, Integer>> localiContestiFrasiBari = new HashMap<>();
    public static HashMap<Integer, HashMap<Integer, Integer>> localiContestiFrasiTorino = new HashMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void inizializzaMatriceBari() throws Exception {

        PrintWriter out = new PrintWriter(new File("filesWherEXP\\" + configurazioneLemmi + "\\locale-contesto-frase bari.txt"));


        // Per ogni locale di Bari
        for (int locale : MatriceLocaleContesto.matriceLocaleContestoBari.keySet()){

            // Mappa contesto -> frase
            HashMap<Integer, Integer> contestoFrase = new HashMap<>();

            // Per ogni contesto singolo
            for (int contesto=1; contesto <= 13; contesto++){

                // Lettura frasi item
                FileTestoItems.leggiFrasiLocale(locale);

                // Scelta frasi adatte al locale
                SceltaFrasiItemFiles.scegliFraseSingoloContesto(contesto);
                int idFrase = SceltaFrasiItemFiles.idFraseSingoloContesto(contesto);

                // Inserimento nella mappa contesto -> frase
                contestoFrase.put(contesto, idFrase);
                System.out.println("\t" + contesto + " --> " + idFrase);

                SceltaFrasiItemFiles.ordinamentiSingoli.clear();
                FileTestoItems.frasiLemmi.clear();
                FileTestoItems.frasiIntere.clear();

            }


            System.out.println(locale + " --> " + contestoFrase);
            out.println(locale + " --> " + contestoFrase);

            // Inserimento nella matrice locale -> contesto -> frase
            localiContestiFrasiBari.put(locale, contestoFrase);


        }

        System.out.println("Dimensione LCF Bari: " + localiContestiFrasiBari.size());

        out.flush();
        out.close();

    }


    public static void inizializzaMatriceTorino() throws Exception {

        PrintWriter out = new PrintWriter(new File("filesWherEXP\\" + configurazioneLemmi + "\\locale-contesto-frase torino.txt"));


        // Per ogni locale di Torino
        for (int locale : MatriceLocaleContesto.matriceLocaleContestoTorino.keySet()){

            // Mappa contesto -> frase
            HashMap<Integer, Integer> contestoFrase = new HashMap<>();

            // Per ogni contesto singolo
            for (int contesto=1; contesto <= 13; contesto++){

                // Lettura frasi item
                FileTestoItems.leggiFrasiLocale(locale);


                // Scelta frasi adatte al locale
                SceltaFrasiItemFiles.scegliFraseSingoloContesto(contesto);
                int idFrase = SceltaFrasiItemFiles.idFraseSingoloContesto(contesto);

                // Inserimento nella mappa contesto -> frase
                contestoFrase.put(contesto, idFrase);
                System.out.println("\t" + contesto + " --> " + idFrase);

                SceltaFrasiItemFiles.ordinamentiSingoli.clear();
                FileTestoItems.frasiLemmi.clear();
                FileTestoItems.frasiIntere.clear();

            }

            System.out.println(locale + " --> " + contestoFrase);
            out.println(locale + " --> " + contestoFrase);

            // Inserimento nella matrice locale -> contesto -> frase
            localiContestiFrasiTorino.put(locale, contestoFrase);


        }

        out.flush();
        out.close();

    }


    public static void scriviMatriceBari() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-frase bari.dat")));

        oos.writeObject(localiContestiFrasiBari);

        oos.flush();
        oos.close();


    }

    public static void leggiMatriceBari() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-frase bari.dat")));
        localiContestiFrasiBari = (HashMap<Integer, HashMap<Integer, Integer>>) ois.readObject();
        ois.close();


    }


    public static void scriviMatriceTorino() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-frase torino.dat")));

        oos.writeObject(localiContestiFrasiTorino);

        oos.flush();
        oos.close();


    }

    public static void leggiMatriceTorino() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-frase torino.dat")));
        localiContestiFrasiTorino = (HashMap<Integer, HashMap<Integer, Integer>>) ois.readObject();
        ois.close();


    }


}
