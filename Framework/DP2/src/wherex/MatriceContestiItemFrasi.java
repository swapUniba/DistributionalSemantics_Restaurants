package wherex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MatriceContestiItemFrasi {

    // recommender system + centroide
    // contesti -> { locale -> frasi }
    public static HashMap<HashSet<Integer>, HashMap<Integer, ArrayList<Integer>>> matriceContestiItemFrasiBari = new HashMap<>();
    public static HashMap<HashSet<Integer>, HashMap<Integer, ArrayList<Integer>>> matriceContestiItemFrasiTorino = new HashMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static HashSet<HashSet<Integer>> combinazioni = new HashSet<>();


    public static void inizializzaMatriceBari() throws Exception {

        PrintWriter out = new PrintWriter(new File("filesWherEXP\\" + configurazioneLemmi + "\\contesti-item-frasi bari2.txt"));

        HashSet<HashSet<Integer>> combinazioniContesti = new HashSet<>();

        for (int a = 0; a <= 3; a++) {
            for (int b = 3; b <= 6; b++) {
                if (b == 3) b = 0;
                for (int c = 6; c <= 8; c++) {
                    if (c == 6) c = 0;
                    for (int d = 8; d <= 10; d++) {
                        if (d == 8) d = 0;
                        for (int e = 10; e <= 13; e++) {
                            if (e == 10) e = 0;

                            HashSet<Integer> ins = new HashSet<Integer>();

                            ins.add(a);
                            ins.add(b);
                            ins.add(c);
                            ins.add(d);
                            ins.add(e);



                            if (ins.contains(0)) ins.remove(0);

                            if (!combinazioniContesti.contains(ins) && ins.size() != 0){

                                // Mappa locale -> frasi
                                HashMap<Integer, ArrayList<Integer>> localeFrasi = new HashMap<>();

                                // Ritrovamento dei top 5 locali adatti
                                for (int locale : Top5LocaliPerCombinazione.contestiItemBari.get(ins)) {

                                    // Lettura frasi item
                                    FileTestoItems.leggiFrasiLocale(locale);

                                    // Scelta frasi adatte al locale
                                    SceltaFrasiItemFiles.scegliFrasiCombinazioneContesti(ins);
                                    ArrayList<Integer> idFrasi = SceltaFrasiItemFiles.idFrasiCombinazione(ins);

                                    // Inserimento nella mappa locale -> frasi
                                    localeFrasi.put(locale, idFrasi);

                                    SceltaFrasiItemFiles.ordinamentiCombinazioni.clear();
                                    FileTestoItems.frasiLemmi.clear();
                                    FileTestoItems.frasiIntere.clear();

                                }

                                System.out.println("Contesti: " + ins + " --> Locale -> Frasi: " + localeFrasi);
                                out.println("Contesti: " +ins + " --> " + " --> Locale -> Frasi: " + localeFrasi);

                                // Inserimento nella matrice contesti -> locale -> frasi
                                matriceContestiItemFrasiBari.put(ins, localeFrasi);

                            }

                            if (e == 0) e = 10;
                        }
                        if (d == 0) d = 8;
                    }
                    if (c == 0) c = 6;
                }
                if (b == 0) b = 3;
            }
        }

        out.flush();
        out.close();



    }

    public static void inizializzaMatriceTorino() throws Exception {

        PrintWriter out = new PrintWriter(new File("filesWherEXP\\" + configurazioneLemmi + "\\contesti-item-frasi torino2.txt"));

        HashSet<HashSet<Integer>> combinazioniContesti = new HashSet<>();

        for (int a = 0; a <= 3; a++) {
            for (int b = 3; b <= 6; b++) {
                if (b == 3) b = 0;
                for (int c = 6; c <= 8; c++) {
                    if (c == 6) c = 0;
                    for (int d = 8; d <= 10; d++) {
                        if (d == 8) d = 0;
                        for (int e = 10; e <= 13; e++) {
                            if (e == 10) e = 0;

                            HashSet<Integer> ins = new HashSet<Integer>();

                            ins.add(a);
                            ins.add(b);
                            ins.add(c);
                            ins.add(d);
                            ins.add(e);



                            if (ins.contains(0)) ins.remove(0);

                            if (!combinazioniContesti.contains(ins) && ins.size() != 0){

                                // Mappa locale -> frasi
                                HashMap<Integer, ArrayList<Integer>> localeFrasi = new HashMap<>();

                                for (int locale : Top5LocaliPerCombinazione.contestiItemTorino.get(ins)) {

                                    // Lettura frasi item
                                    FileTestoItems.leggiFrasiLocale(locale);

                                    // Scelta frasi adatte al locale
                                    SceltaFrasiItemFiles.scegliFrasiCombinazioneContesti(ins);
                                    ArrayList<Integer> idFrasi = SceltaFrasiItemFiles.idFrasiCombinazione(ins);


                                    // Inserimento nella mappa locale -> frasi
                                    localeFrasi.put(locale, idFrasi);

                                    SceltaFrasiItemFiles.ordinamentiCombinazioni.clear();
                                    FileTestoItems.frasiLemmi.clear();
                                    FileTestoItems.frasiIntere.clear();

                                }


                                System.out.println(ins + " --> " + localeFrasi);
                                out.println(ins + " --> " + localeFrasi);

                                // Inserimento nella matrice contesti -> locale -> frasi
                                matriceContestiItemFrasiTorino.put(ins, localeFrasi);

                            }

                            if (e == 0) e = 10;
                        }
                        if (d == 0) d = 8;
                    }
                    if (c == 0) c = 6;
                }
                if (b == 0) b = 3;
            }
        }

        out.flush();
        out.close();


    }

    public static void scriviMatriceBari() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File("filesWherEXP\\"+configurazioneLemmi+"\\contesti-item-frasi bari.dat"))));

        oos.writeObject(matriceContestiItemFrasiBari);

        oos.flush();
        oos.close();


    }

    public static void scriviMatriceTorino() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((new File("filesWherEXP\\"+configurazioneLemmi+"\\contesti-item-frasi torino.dat"))));

        oos.writeObject(matriceContestiItemFrasiTorino);

        oos.flush();
        oos.close();


    }

}
