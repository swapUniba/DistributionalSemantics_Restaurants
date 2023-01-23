package wherex;


import java.io.*;
import java.util.*;

public class MatriceLF {

    public static TreeMap<String, TreeMap<Integer, Double>> matriceLemmaFrase = new TreeMap<>();

    public static HashSet<String> insiemeLemmi = new HashSet<>();
    public static HashMap<String, Double> lemmiIDF = new HashMap<>();
    public static HashSet<Integer> idFrasi = new HashSet<>();

    public static int numeroFrasi = 0;

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void scriviIDFeLemmi() throws Exception{

        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\insiemeLemmi.dat"));
        oos1.writeObject(insiemeLemmi);
        oos1.flush();
        oos1.close();

        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\lemmiIDF.dat"));
        oos2.writeObject(lemmiIDF);
        oos2.flush();
        oos2.close();

    }

    public static void leggiIDFeLemmi() throws Exception {

        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\insiemeLemmi.dat"));
        insiemeLemmi = (HashSet<String>) ois1.readObject();
        ois1.close();

        ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\lemmiIDF.dat"));
        lemmiIDF = (HashMap<String, Double>) ois2.readObject();
        ois2.close();

    }

    public static void generaLemmi() throws Exception {

        /*
            Si legge dal file delle frasi ridotte a lemmi, si inseriscono in un set
            (per non creare duplicati).
         */

        /*
        for (int idFrase : FileTestoItems.frasiLemmi.keySet()){

            numeroFrasi++;

            String lemmiFrase = FileTestoItems.frasiLemmi.get(idFrase);

            idFrasi.add(idFrase);

            String[] lemmi = lemmiFrase.split(",");

            for (String l : lemmi){

                insiemeLemmi.add(l.trim());
            }

        }
        */


        // Frase letta dal file
        Scanner frasi = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\idFraseLemmi.txt"));

        while (frasi.hasNextLine()) {

            // Riga del file
            String riga = frasi.nextLine();
            numeroFrasi++;

            // Lettura id e lemmi
            StringTokenizer st = new StringTokenizer(riga, ";");

            int idFrase = Integer.parseInt(st.nextToken());
            idFrasi.add(idFrase);

            // Acquisizione testo
            String testo = st.nextToken();

            // I lemmi vengono inseriti nell'insieme dei lemmi
            StringTokenizer st2 = new StringTokenizer(testo, ",");

            while (st2.hasMoreTokens()) {

                insiemeLemmi.add(st2.nextToken().trim());
            }


        }

    }

    public static void generaIDF() throws Exception {


        // Per ogni lemma delle frasi
        for (String lemma : insiemeLemmi) {

            double idf = 0;

            Scanner frasi = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\idFraseLemmi.txt"));

            int contatoreFrasiContenentiLemma = 0;

            // Lettura frasi
            while (frasi.hasNextLine()) {

                StringTokenizer st = new StringTokenizer(frasi.nextLine(), ";");
                st.nextToken();
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");

                boolean presente = false;

                while (st2.hasMoreTokens()) {

                    if (lemma.equals(st2.nextToken().trim())) {

                        presente = true;
                        break;
                    }
                }

                if (presente) {

                    contatoreFrasiContenentiLemma++;

                }
            }

            frasi.close();

            idf = Math.log((double) numeroFrasi / (double) contatoreFrasiContenentiLemma);
            lemmiIDF.put(lemma, idf);

        }
    }


    public static void generaMatriceLemmaFrase() throws Exception {

        for  (String lemma : insiemeLemmi){


            double tf;
            double idf = lemmiIDF.get(lemma);

            // Apertura file frasi
            Scanner frasi = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\idFraseLemmi.txt"));

            TreeMap<Integer, Double> frasiTFIDF = new TreeMap<>();

            // Scorrimento file
            while(frasi.hasNextLine()){

                // Lettura riga
                String riga = frasi.nextLine();

                StringTokenizer st = new StringTokenizer(riga, ";");

                int idFrase = Integer.parseInt(st.nextToken());

                // Lemmi frase
                String testo = st.nextToken();

                StringTokenizer st2 = new StringTokenizer(testo, ",");

                // Contatore occorrenze lemma nella frase e lemmi della frase
                int contatoreOccorrenze = 0, contatoreLemmi = 0;

                while(st2.hasMoreTokens()){

                    contatoreLemmi++;

                    if (st2.nextToken().trim().equals(lemma)){

                        contatoreOccorrenze++;

                    }

                }

                // Calcolo TF
                tf = (double)contatoreOccorrenze / (double)contatoreLemmi;

                // Calcolo TF-IDF
                double tfidf = tf * idf;

                frasiTFIDF.put(idFrase, tfidf);

            }

            for (int idFrase : idFrasi){

                if (!frasiTFIDF.containsKey(idFrase)){

                    frasiTFIDF.put(idFrase, 0.0);
                }
            }

            matriceLemmaFrase.put(lemma, frasiTFIDF);

            // Chiusura file
            frasi.close();

        }


    }

    public static void stampaMatriceLemmaFrase(){


        for (String lemma : matriceLemmaFrase.keySet()){

            System.out.printf("%-25s -> { ", lemma);

            for (int idFrase : matriceLemmaFrase.get(lemma).keySet()){

                System.out.printf("%d -> %.2f , ", idFrase, matriceLemmaFrase.get(lemma).get(idFrase));
            }

            System.out.println("}");
        }


    }

    public static void stampaMatriceLemmaFraseFile() throws Exception{

        PrintWriter out = new PrintWriter(new File("matrice lemma frase.txt"));

        for (String lemma : matriceLemmaFrase.keySet()){

            out.printf("%-25s -> { ", lemma);

            for (int idFrase : matriceLemmaFrase.get(lemma).keySet()){

                out.printf("%d -> %.2f , ", idFrase, matriceLemmaFrase.get(lemma).get(idFrase));
            }

            out.println("}");
        }

        out.flush();
        out.close();


    }

    public static void stampaIDF(){

        for (String lemma : lemmiIDF.keySet()){

            System.out.printf("%-23s -> %.2f\n", lemma, lemmiIDF.get(lemma));
        }
    }
}
