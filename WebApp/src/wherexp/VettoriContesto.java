package wherexp;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class VettoriContesto {


    public static TreeMap<Integer, TreeMap<String, Double>> vettoriContesto = new TreeMap<>();
    public static HashMap<Integer, String> contesti = new HashMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    static {

        contesti.put(1, "Colazione");
        contesti.put(2, "Pranzo");
        contesti.put(3, "Cena");
        contesti.put(4, "Amici");
        contesti.put(5, "Coppia");
        contesti.put(6, "Famiglia");
        contesti.put(7, "Feriale");
        contesti.put(8, "Festivo");
        contesti.put(9, "Buon umore");
        contesti.put(10, "Cattivo umore");
        contesti.put(11, "Cibo salutare");
        contesti.put(12, "Cibo non salutare");
        contesti.put(13, "Esigenze alimentari");

    }

    public static void scriviVettoriContesto() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\vettori contesto.dat")));
        oos.writeObject(vettoriContesto);
        oos.flush();
        oos.close();

    }

    public static void leggiVettoriContesto() throws Exception {

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\vettori contesto.dat")));
        vettoriContesto = (TreeMap<Integer, TreeMap<String, Double>>) oin.readObject();
        oin.close();

    }

    public static void generaVettoriContesto() throws Exception {

        for (int idContesto = 1; idContesto <= 13; idContesto++) {


            TreeMap<String, Double> contestoCorrente = new TreeMap<>();

            for (String lemma : MatriceLC.matriceLemmaContesto.keySet()) {

                contestoCorrente.put(lemma, MatriceLC.matriceLemmaContesto.get(lemma).get(idContesto));

            }

            vettoriContesto.put(idContesto, contestoCorrente);

        }
    }


    public static void stampaSimilaritaContesti() throws Exception {

        HashMap<HashSet<Integer>, SimilaritaCoseno> similarita = new HashMap<>();

        for (int contesto1 : vettoriContesto.keySet()){

            for (int contesto2 : vettoriContesto.keySet()){

                HashSet<Integer> listaContesti = new HashSet<>();
                listaContesti.add(contesto1);
                listaContesti.add(contesto2);

                if (!similarita.containsKey(listaContesti)){

                    SimilaritaCoseno sc = new SimilaritaCoseno(vettoriContesto.get(contesto1), vettoriContesto.get(contesto2));
                    similarita.put(listaContesti, sc);

                    if  (contesto1 == contesto2) System.out.println(sc.getScoreSimilarita());

                }


            }

        }

        PrintWriter out = new PrintWriter(new File("matrice similarita contesti.txt"));

        out.println("\t1\t\t2\t\t3\t\t5\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\t\t13");

        for (int i=1; i<=13; i++){

            out.print(i+"\t");

            for (int j=1; j<=13; j++){

                HashSet<Integer> listaContesti = new HashSet<>();
                listaContesti.add(i);
                listaContesti.add(j);

                if (similarita.containsKey(listaContesti)) {

                    out.printf("%.2f\t", similarita.get(listaContesti).getScoreSimilarita());

                }



            }

            out.println();
        }

        out.flush();
        out.close();


    }

}
