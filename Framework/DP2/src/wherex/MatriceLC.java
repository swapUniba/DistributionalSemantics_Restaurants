package wherex;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class MatriceLC {

    public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaContesto = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void generaMatriceLemmaContestoDaFile() throws Exception{

        FileInputStream in = new FileInputStream(new File("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\lemma-contesto.dat"));
        ObjectInputStream oin = new ObjectInputStream(in);

        matriceLemmaContesto = (TreeMap<String, TreeMap<Integer, Double>>) oin.readObject();

        oin.close();

    }

    public static void generaMatriceLemmaContesto(){

        for (String lemma : MatriceLF.matriceLemmaFrase.keySet()){

            TreeMap<Integer, Double> contestoPunteggio = new TreeMap<>();

            for (int idContesto = 1; idContesto <= 13; idContesto++) {

                double somma = 0;

                for (int idFrase : MatriceFC.matriceFraseContesto.keySet()) {

                    double a = MatriceLF.matriceLemmaFrase.get(lemma).get(idFrase);
                    double b = MatriceFC.matriceFraseContesto.get(idFrase).get(idContesto);

                    somma += a * b;

                }

                contestoPunteggio.put(idContesto, somma);

            }

            matriceLemmaContesto.put(lemma, contestoPunteggio);

        }

    }

    public static void annullamentoScoreStopWords() throws Exception {

        Scanner stopLemmi = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\stoplemmi.txt"));

        while(stopLemmi.hasNextLine()){

            String stopLemma = stopLemmi.nextLine();

            for (int contesto : matriceLemmaContesto.get(stopLemma).keySet()){

                //matriceLemmaContesto.get(stopLemma).replace(contesto, 0.0);
                //matriceLemmaContesto.get(stopLemma).remove(contesto);
                matriceLemmaContesto.get(stopLemma).put(contesto, 0.0);
            }
        }

        stopLemmi.close();



    }

    public static void stampaMatriceLemmaContesto(){

        System.out.printf("%-30s\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\n", "Lemma");

        for (String lemma : matriceLemmaContesto.keySet()){

            System.out.printf("%-25s\t", lemma);

            for (int idContesto : matriceLemmaContesto.get(lemma).keySet()){

                System.out.printf("%.2f\t", matriceLemmaContesto.get(lemma).get(idContesto));
            }

            System.out.println();
        }

    }

    public static void stampaMatriceLemmaContestoFile() throws Exception {

        PrintWriter out = new PrintWriter(new File("matrice lemma contesto.txt"));

        out.printf("%-30s\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\n", "Lemma");

        for (String lemma : matriceLemmaContesto.keySet()){

            out.printf("%-25s\t", lemma);

            for (int idContesto : matriceLemmaContesto.get(lemma).keySet()){

                out.printf("%.2f\t", matriceLemmaContesto.get(lemma).get(idContesto));
            }

            out.println();
        }

        out.flush();
        out.close();

    }

}
