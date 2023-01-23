package wherex;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class VettoreFrase {


    private TreeMap<String, Double> vettoreFrase = new TreeMap<>();


    public static TreeMap<String, Double> lemmiFraseIDF = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public VettoreFrase(String frase){

        StringTokenizer st = new StringTokenizer(frase, ",");

        while(st.hasMoreTokens()){

            String parola = st.nextToken();

            // Se nella matrice dei lemmi del contesto c'è il lemma della parola,
            // allora prendo l'IDF di quel lemma calcolato su tutto il dataset
            if (MatriceLF.lemmiIDF.containsKey(parola)){

                //vettoreFrase.put(parola, lemmiFraseIDF.get(parola));
                vettoreFrase.put(parola, MatriceLF.lemmiIDF.get(parola));


            }

        }

        for (String lemma : MatriceLF.lemmiIDF.keySet()){

            if (!vettoreFrase.containsKey(lemma)){

                vettoreFrase.put(lemma, 0.0);

            }

        }


    }

    public TreeMap<String, Double> getVettoreFrase(){

        return this.vettoreFrase;
    }


    public static void inizializzaLemmiFraseIDF() throws Exception {

        Scanner in = new  Scanner(new File("filesWherEXP\\" + configurazioneLemmi +"\\lemmi frase\\IDF lemmi.txt"));

        while(in.hasNextLine()){

            String riga = in.nextLine();

            String[] vettoreRiga = riga.split(";");

            String lemma = vettoreRiga[0];
            Double idf = Double.parseDouble(vettoreRiga[2].replace(',', '.'));


            lemmiFraseIDF.put(lemma, idf);

        }


        in.close();

    }

    public static void stampaLemmiFraseIDF(){

        for (String lemma : lemmiFraseIDF.keySet()){

            System.out.println(lemma + " -> " + lemmiFraseIDF.get(lemma));
        }

    }


}
