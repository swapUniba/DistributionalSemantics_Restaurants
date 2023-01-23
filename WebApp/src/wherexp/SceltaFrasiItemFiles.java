package wherexp;

import java.util.*;

public class SceltaFrasiItemFiles {


    // Mappa contesto -> similarità ordinate
    public static TreeMap<Integer, TreeSet<SimilaritaCoseno>> ordinamentiSingoli = new TreeMap<>();

    // Mappa combinazione contesti -> similarità ordinate
    public static HashMap<HashSet<Integer>, TreeSet<SimilaritaCoseno>> ordinamentiCombinazioni = new HashMap<>();


    public static void scegliFraseSingoloContesto(int contesto) throws Exception {

        TreeSet<SimilaritaCoseno> valoriSimilarita = new TreeSet<>();

        for (int idFrase : FileTestoItems.frasiLemmi.keySet()) {

            String testoLemmiFrase = FileTestoItems.frasiLemmi.get(idFrase);

            int lunghezzaFrase = 0;

            String[] lemmiFrase = testoLemmiFrase.split(", ");

            if (Configurazione.TipoLemmi.equals("unibigrammi")) {

                for (String lemma : lemmiFrase) {

                    if (!lemma.contains(" ")) {

                        lunghezzaFrase++;

                    } else {

                        break;

                    }
                }

            } else {

                lunghezzaFrase = lemmiFrase.length;
            }

            if (lunghezzaFrase >= 3 && lunghezzaFrase <= 15) {

                // Creazione vettore del testo della frase
                VettoreFrase frase = new VettoreFrase(testoLemmiFrase);

                // Calcolo punteggio
                HashSet<Integer> c = new HashSet<>();
                c.add(contesto);
                SimilaritaCoseno sc = new SimilaritaCoseno(frase.getVettoreFrase(), c);
                sc.setIdFrase(idFrase);
                sc.setTestoFrase(FileTestoItems.frasiIntere.get(idFrase));

                // Aggiunta all'insieme
                valoriSimilarita.add(sc);

            }

        }

        ordinamentiSingoli.put(contesto, valoriSimilarita);


    }

    public static int idFraseSingoloContesto(int contesto) throws Exception {

        int idFrase = 0;

        if (ordinamentiSingoli.get(contesto).size() != 0){

            idFrase = ordinamentiSingoli.get(contesto).first().getIdFrase();
        }

        return idFrase;

    }


    public static void scegliFrasiCombinazioneContesti(HashSet<Integer> contesti) throws Exception {

        // Insieme valori di similarità
        TreeSet<SimilaritaCoseno> valoriSimilarita = new TreeSet<>();

        for (int idFrase : FileTestoItems.frasiLemmi.keySet()){

            String testoLemmiFrase = FileTestoItems.frasiLemmi.get(idFrase);

            int lunghezzaFrase = 0;

            String[] lemmiFrase = testoLemmiFrase.split(", ");

            if (Configurazione.TipoLemmi.equals("unibigrammi")){

                for (String lemma : lemmiFrase){

                    if (!lemma.contains(" ")){

                        lunghezzaFrase++;

                    } else {

                        break;

                    }
                }

            } else {

                lunghezzaFrase = lemmiFrase.length;
            }

            if (lunghezzaFrase >= 3 && lunghezzaFrase <= 15) {

                // Creazione vettore del testo della frase
                VettoreFrase frase = new VettoreFrase(testoLemmiFrase);

                // Calcolo punteggio
                SimilaritaCoseno sc = new SimilaritaCoseno(frase.getVettoreFrase(), contesti);
                sc.setIdFrase(idFrase);
                sc.setTestoFrase(FileTestoItems.frasiIntere.get(idFrase));

                // Aggiunta all'insieme
                valoriSimilarita.add(sc);

            }
        }

        ordinamentiCombinazioni.put(contesti, valoriSimilarita);

    }




    public static ArrayList<Integer> idFrasiCombinazione(HashSet<Integer> contesti) throws Exception {

        ArrayList<Integer> idFrasi = new ArrayList<>();


        int c=0;
        double score1 = 0, score2=0, score3=0;

        for (SimilaritaCoseno sc : ordinamentiCombinazioni.get(contesti)) {

            c++;

            if (c == 1){

                idFrasi.add(sc.getIdFrase());
                score1 = sc.getScoreSimilarita();

            } else if (c == 2){

                score2 = sc.getScoreSimilarita();

                if (score2 >= (score1 * 0.5)){

                    idFrasi.add(sc.getIdFrase());

                } else {
                    break;

                }


            } else if (c == 3){

                score3 = sc.getScoreSimilarita();

                if (score3 >= (score2 * 0.67) ){

                    idFrasi.add(sc.getIdFrase());


                }


            } else {

                break;
            }

        }

        return idFrasi;

    }


}
