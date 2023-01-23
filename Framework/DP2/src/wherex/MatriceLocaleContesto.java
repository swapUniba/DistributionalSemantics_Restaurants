package wherex;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

public class MatriceLocaleContesto {

    // id locale -> (id contesto -> punteggio contesto)
    public static TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContesto = new TreeMap<>();
    public static TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContestoBari = new TreeMap<>();
    public static TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContestoTorino = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void calcolaMatrice() throws Exception {

        // Per ogni locale
        for (int idLocale = 1; idLocale <= 791; idLocale++){

            TreeMap<Integer, Double> contestoScore = new TreeMap<>();

            // Per ogni contesto
            for (int contesto = 1; contesto <= 13; contesto++){

                // Per ogni frase
                Scanner in = new Scanner(new File("filesWherEXP\\frasi singoli items\\" + configurazioneLemmi + "\\" + idLocale+".txt"), "UTF-8");

                int contatoreFrasi = 0;
                double sommatoriaSimilarita = 0;

                //Per ogni frase
                while(in.hasNextLine()){

                    // Acquisizione frase, calolo vettore frase e similarità
                    String frase = in.nextLine().split(";")[2];
                    VettoreFrase vettoreFrase = new VettoreFrase(frase);

                    //System.out.println("Lunghezza vettore frase: " + vettoreFrase.getVettoreFrase().size());
                    //System.out.println("Lunghezza vettore contesto: " + VettoriContesto.vettoriContesto.get(contesto).size());

                    SimilaritaCoseno sc = new SimilaritaCoseno(vettoreFrase.getVettoreFrase(), VettoriContesto.vettoriContesto.get(contesto));

                    sommatoriaSimilarita += sc.getScoreSimilarita();
                    contatoreFrasi++;

                }

                System.out.println("Locale " + idLocale + ", contesto " + contesto + " --> " + sommatoriaSimilarita + "/" + contatoreFrasi);

                double similarita = sommatoriaSimilarita;
                //double similarita = sommatoriaSimilarita / contatoreFrasi;

                contestoScore.put(contesto, similarita);

                in.close();

                //System.out.println("Locale " + idLocale + ", contesto " + contesto + " --> " + similarita);

            }

            matriceLocaleContesto.put(idLocale, contestoScore);

        }

    }

    public static void stampaFileMatriceLocaleContesto() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto.dat")));

        oos.writeObject(matriceLocaleContesto);

        oos.flush();
        oos.close();


    }

    public static void leggiMatriceLocaleContesto() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto.dat")));

        matriceLocaleContesto = (TreeMap<Integer, TreeMap<Integer, Double>>) ois.readObject();

        ois.close();

    }

    public static void leggiMatriceLocaleContestoBari() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-bari.dat")));

        matriceLocaleContestoBari = (TreeMap<Integer, TreeMap<Integer, Double>>) ois.readObject();

        ois.close();

    }

    public static void leggiMatriceLocaleContestoTorino() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-torino.dat")));

        matriceLocaleContestoTorino = (TreeMap<Integer, TreeMap<Integer, Double>>) ois.readObject();

        ois.close();

    }

    public static void generaMatriciBariTorino() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto.dat")));
        ObjectOutputStream b = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-bari.dat")));
        ObjectOutputStream t = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\"+configurazioneLemmi+"\\locali-contesto-torino.dat")));

        TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContesto = (TreeMap<Integer, TreeMap<Integer, Double>>) ois.readObject();
        TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContestoBari = new TreeMap<>();
        TreeMap<Integer, TreeMap<Integer, Double>> matriceLocaleContestoTorino = new TreeMap<>();

        Scanner inB = new Scanner(new File("filesWherEXP\\localiBari.txt"));
        Scanner inT = new Scanner(new File("filesWherEXP\\localiTorino.txt"));

        while(inB.hasNextLine()){

            int l = Integer.parseInt(inB.nextLine().split(";")[0]);
            matriceLocaleContestoBari.put(l, matriceLocaleContesto.get(l));

        }

        while (inT.hasNextLine()){

            int l = Integer.parseInt(inT.nextLine().split(";")[0]);
            matriceLocaleContestoTorino.put(l, matriceLocaleContesto.get(l));

        }

        inB.close();
        inT.close();

        b.writeObject(matriceLocaleContestoBari);
        t.writeObject(matriceLocaleContestoTorino);

        b.flush();
        b.close();
        t.flush();
        t.close();
        ois.close();




    }

}


