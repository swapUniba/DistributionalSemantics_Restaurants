package wherex;

import java.io.File;
import java.util.*;

public class SceltaFrasiItem {


    // Mappa contesto -> similarità ordinate
    public static TreeMap<Integer, TreeSet<SimilaritaCoseno>> ordinamentiSingoli = new TreeMap<>();

    // Mappa combinazione contesti -> similarità ordinate
    public static HashMap<HashSet<Integer>, TreeSet<SimilaritaCoseno>> ordinamentiCombinazioni = new HashMap<>();

    // Mappa contesto -> similarità per contesti multipli a frase singola
    public static TreeMap<Integer, SimilaritaCoseno> similaritaFraseSingolaCombinazioneContesti = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void scegliFrasiOgniContesto() throws Exception {

        for (int i=1; i<=13; i++){

            // File delle frasi dell'item
            Scanner frasiDaValutare = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\output\\idFraseLemmi.txt"));

            // Insieme valori di similarità
            TreeSet<SimilaritaCoseno> valoriSimilarita = new TreeSet<>();

            // Scorrimento file
            while (frasiDaValutare.hasNextLine()) {

                // Lettura riga e divisione in id e lemmi
                String riga = frasiDaValutare.nextLine();
                StringTokenizer st = new StringTokenizer(riga, ";");
                st.nextToken();

                int idFrase = Integer.parseInt(st.nextToken());
                String testoFrase = st.nextToken();

                int lunghezzaFrase = 0;

                String[] lemmiFrase = testoFrase.split(", ");

                for (String lemma : lemmiFrase){

                    if (!lemma.contains(" ")){

                        lunghezzaFrase++;

                    } else {

                        break;

                    }


                }
                // Conteggio numero di n-grammi della frase.
                // Se sono meno di 3 o più di 15, la frase viene scartata.

                if (lunghezzaFrase >= 3 && lunghezzaFrase <= 15) {

                    // Creazione vettore del testo della frase
                    VettoreFrase frase = new VettoreFrase(testoFrase);

                    // Calcolo punteggio
                    SimilaritaCoseno sc = new SimilaritaCoseno(frase.getVettoreFrase(), VettoriContesto.vettoriContesto.get(i));
                    sc.setIdContesto(i);
                    sc.setIdFrase(idFrase);
                    sc.setTestoFrase(testoFrase);

                    // Aggiunta all'insieme
                    valoriSimilarita.add(sc);

                }

            }

            frasiDaValutare.close();

            ordinamentiSingoli.put(i, valoriSimilarita);


        }
    }

    public static void scegliFrasiCombinazioneContesti(HashSet<Integer> contesti) throws Exception {

        // File delle frasi dell'item
        Scanner frasiDaValutare = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseLemmi.txt"));

        // Insieme valori di similarità
        TreeSet<SimilaritaCoseno> valoriSimilarita = new TreeSet<>();

        // Scorrimento file
        while (frasiDaValutare.hasNextLine()) {

            // Lettura riga e divisione in id e lemmi
            String riga = frasiDaValutare.nextLine();
            StringTokenizer st = new StringTokenizer(riga, ";");

            //nome locale
            st.nextToken();

            int idFrase = Integer.parseInt(st.nextToken());
            String testoFrase = st.nextToken();

            //int lunghezzaFrase = testoFrase.split(" ").length;

            int lunghezzaFrase = 0;

            String[] lemmiFrase = testoFrase.split(", ");

            for (String lemma : lemmiFrase){

                if (!lemma.contains(" ")){

                    lunghezzaFrase++;

                } else {

                    break;

                }


            }
            // Conteggio numero di n-grammi della frase.
            // Se sono meno di 3 o più di 15, la frase viene scartata.


            if (lunghezzaFrase >= 3 && lunghezzaFrase <= 15) {

                // Creazione vettore del testo della frase
                VettoreFrase frase = new VettoreFrase(testoFrase);

                // Calcolo punteggio
                SimilaritaCoseno sc = new SimilaritaCoseno(frase.getVettoreFrase(), contesti);
                sc.setIdFrase(idFrase);
                sc.setTestoFrase(testoFrase);

                // Aggiunta all'insieme
                valoriSimilarita.add(sc);

            }


        }

        frasiDaValutare.close();

        ordinamentiCombinazioni.put(contesti, valoriSimilarita);



    }

    public static void scegliFrasiSingoleCombinazioniContesti(int numeroContesti) throws Exception {

        // File delle frasi dell'item
        Scanner frasiDaValutare = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\output\\idFraseLemmi.txt"));

        // Elaborazioni contesti casuali
        HashSet<Integer> contesti = elaborazioneContesti(numeroContesti);

        // Mappa contesto -> similarità relativa


        // Scorrimento file
        while (frasiDaValutare.hasNextLine()){

            // Lettura riga e divisione in id e lemmi
            String riga = frasiDaValutare.nextLine();
            StringTokenizer st = new StringTokenizer(riga, ";");
            st.nextToken();

            int idFrase = Integer.parseInt(st.nextToken());
            String testoFrase = st.nextToken();

            //int lunghezzaFrase = testoFrase.split(" ").length;

            int lunghezzaFrase = 0;

            String[] lemmiFrase = testoFrase.split(", ");

            for (String lemma : lemmiFrase){

                if (!lemma.contains(" ")){

                    lunghezzaFrase++;

                } else {

                    break;

                }


            }
            // Conteggio numero di n-grammi della frase.
            // Se sono meno di 3 o più di 15, la frase viene scartata.

            if (lunghezzaFrase >= 3 && lunghezzaFrase <= 15){

                // Creazione vettore del testo della frase
                VettoreFrase frase = new VettoreFrase(testoFrase);

                // Selezione frase per ogni contesto
                for (int contesto : contesti){

                    SimilaritaCoseno sc = new SimilaritaCoseno(frase.getVettoreFrase(), VettoriContesto.vettoriContesto.get(contesto));
                    sc.setIdFrase(idFrase);

                    if (similaritaFraseSingolaCombinazioneContesti.containsKey(contesto)){


                        if (similaritaFraseSingolaCombinazioneContesti.get(contesto).getScoreSimilarita() < sc.getScoreSimilarita()){

                            // Reperimento testo frase intera
                            Scanner frasiIntere = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseFrasiIntere.txt"));

                            while (frasiIntere.hasNextLine()){

                                String rigaIntere = frasiIntere.nextLine();
                                StringTokenizer stIntere = new StringTokenizer(rigaIntere, ";");
                                stIntere.nextToken();

                                int idFraseIntere = Integer.parseInt(stIntere.nextToken());

                                if (idFraseIntere == sc.getIdFrase()){

                                    sc.setTestoFrase(stIntere.nextToken());
                                    break;

                                }
                            }

                            frasiIntere.close();

                            similaritaFraseSingolaCombinazioneContesti.remove(contesto);
                            similaritaFraseSingolaCombinazioneContesti.put(contesto, sc);
                            //similaritaFraseSingolaCombinazioneContesti.replace(contesto, sc);
                        }

                    } else {

                        // Aggiunta all'insieme
                        similaritaFraseSingolaCombinazioneContesti.put(contesto, sc);

                    }



                }




            }


        }

        frasiDaValutare.close();


    }

    public static void stampaOrdinamentiSingoli() throws Exception {

        // Lettura mappa ed esiti
        for (int i : ordinamentiSingoli.keySet()) {

            System.out.println("CONTESTO: " + VettoriContesto.contesti.get(i));

            int c = 0;

            for (SimilaritaCoseno sc : ordinamentiSingoli.get(i)) {

                c++;

                // Reperimento testo frase intera
                Scanner frasiIntere = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseFrasiIntere.txt"));

                while (frasiIntere.hasNextLine()){

                    String riga = frasiIntere.nextLine();
                    StringTokenizer st = new StringTokenizer(riga, ";");
                    st.nextToken();

                    int idFrase = Integer.parseInt(st.nextToken());

                    if (idFrase == sc.getIdFrase()){

                        sc.setTestoFrase(st.nextToken());
                        break;

                    }
                }

                frasiIntere.close();

                System.out.println("\t> " + c + "° Frase " + sc.getIdFrase() + ": " + sc.getTestoFrase() + "\n\t> Punteggio: " + sc.getScoreSimilarita());
                System.out.println();

                if (c == 5) break;
            }
        }

    }

    public static void stampaOrdinamentiCombinazioni() throws Exception {


        for (HashSet<Integer> contesti : ordinamentiCombinazioni.keySet()){

            System.out.print("Contesti: ");
            for (int c : contesti){
                System.out.print(VettoriContesto.contesti.get(c) + " - ");
            }
            System.out.println();

            int c = 0;

            for (SimilaritaCoseno sc : ordinamentiCombinazioni.get(contesti)){

                c++;

                // Reperimento testo frase intera
                Scanner frasiIntere = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseFrasiIntere.txt"));

                while (frasiIntere.hasNextLine()){

                    String riga = frasiIntere.nextLine();
                    StringTokenizer st = new StringTokenizer(riga, ";");
                    st.nextToken();

                    int idFrase = Integer.parseInt(st.nextToken());

                    if (idFrase == sc.getIdFrase()){

                        sc.setTestoFrase(st.nextToken());
                        break;

                    }
                }

                frasiIntere.close();

                System.out.println("\t> " + c + "° Frase " + sc.getIdFrase() + ": " + sc.getTestoFrase() + "\n\t> Punteggio: " + sc.getScoreSimilarita());
                System.out.println();

                if (c == 5) break;



            }

        }


    }

    public static String spiegazioneSimilaritaFraseSingolaCombinazioniContesti() throws Exception {

        String spiegazione = "Per i contesti che hai selezionato, ti consiglio questo locale perche' le persone che ci sono state hanno detto che: ";

        for (int contesto : similaritaFraseSingolaCombinazioneContesti.keySet()){

            spiegazione += "\n\tContesto \"" + VettoriContesto.contesti.get(contesto) + "\": " +
                    similaritaFraseSingolaCombinazioneContesti.get(contesto).getTestoFrase() + ";";

        }

        return spiegazione;



    }




    public static HashSet<Integer> elaborazioneContesti(int numeroContesti) {

        // Scelta dei 3 ambiti
        ArrayList<Integer> ambiti = new ArrayList<>();
        ambiti.add(1);
        ambiti.add(2);
        ambiti.add(3);
        ambiti.add(4);
        ambiti.add(5);

        Random n = new Random();

        for (int c=0; c<5-numeroContesti; c++){

            ambiti.remove(n.nextInt(ambiti.size()));

        }

        HashSet<Integer> contesti = new HashSet<>();

        for (int a : ambiti) {

            if (a == 1) {

                contesti.add(1 + n.nextInt(3)); // 1, 2 o 3

            } else if (a == 2) {

                contesti.add(4 + n.nextInt(3)); // 4, 5 o 6

            } else if (a == 3) {

                contesti.add(7 + n.nextInt(2)); // 7 o 8

            } else if (a == 4) {

                contesti.add(9 + n.nextInt(2)); // 9 o 10

            } else if (a == 5) {

                contesti.add(11 + n.nextInt(3)); // 11, 12 o 13

            }
        }

        return contesti;
    }

    public static void azzeraOrdinamentiCombinazioni(){

        ordinamentiCombinazioni = new HashMap<>();


    }

    public static String spiegazione(int idContesto) throws Exception {

        String spiegazione = "\nTi consiglio, per il contesto \"" +
                VettoriContesto.contesti.get(idContesto) +
                "\", questo locale perche' le persone che ci sono state hanno detto che \n\t\"";

        int c=0;
        double c2=0, c3=0;

        for (SimilaritaCoseno sc : ordinamentiSingoli.get(idContesto)) {


            c++;

            Scanner frasiIntere = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseFrasiIntere.txt"));

            while (frasiIntere.hasNextLine()) {

                String riga = frasiIntere.nextLine();
                StringTokenizer st = new StringTokenizer(riga, ";");
                st.nextToken();

                int idFrase = Integer.parseInt(st.nextToken());

                if (idFrase == sc.getIdFrase()) {

                    sc.setTestoFrase(st.nextToken());
                    break;

                }
            }

            frasiIntere.close();



            if (c == 1){

                spiegazione += sc.getTestoFrase() + "\"";

            } else if (c == 2){

                spiegazione += "\ne che\n\t\"" + sc.getTestoFrase() + "\"";
                c2 = sc.getScoreSimilarita();

            } else if (c == 3){

                c3 = sc.getScoreSimilarita();

                if (c3 >= (c2 * 0.67) ){

                    spiegazione += ";\ninfine, hanno detto che\n\t\"" + sc.getTestoFrase() + "\".";

                } else {

                    spiegazione += ".\n";
                }

            } else {

                break;
            }

        }


        return spiegazione;

    }

    public static String spiegazioneCombinazione(HashSet<Integer> contesti) throws Exception {

        String stringaContesti = "";

        for (int c : contesti){

            stringaContesti += "\"" + VettoriContesto.contesti.get(c) + "\", ";

        }

        /*
        for (int i=0; i<listaContesti.size()-1; i++){

            stringaContesti += "\"" + VettoriContesto.contesti.get(listaContesti.get(i)) + "\", ";

        }

        stringaContesti += "\"" + VettoriContesto.contesti.get(listaContesti.get(listaContesti.size()-1)) +"\"";


        */

        String spiegazione = "\nTi consiglio, per i contesti " +
                stringaContesti +
                "questo locale perche' le persone che ci sono state hanno detto che \n\t\"";

        int c=0;
        double score1 = 0, score2=0, score3=0;

        for (SimilaritaCoseno sc : ordinamentiCombinazioni.get(contesti)) {


            c++;

            Scanner frasiIntere = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\items\\idFraseFrasiIntere.txt"), "UTF-8");

            while (frasiIntere.hasNextLine()) {

                String riga = frasiIntere.nextLine();
                StringTokenizer st = new StringTokenizer(riga, ";");
                st.nextToken();

                int idFrase = Integer.parseInt(st.nextToken());

                System.out.println(sc.getIdFrase() + " - " + idFrase);

                if (idFrase == sc.getIdFrase()) {

                    System.out.println("Trovato.");

                    String testo = st.nextToken();
                    sc.setTestoFrase(testo);

                    break;

                }
            }

            frasiIntere.close();

            if (c == 1){

                spiegazione += sc.getTestoFrase() + "\"";
                score1 = sc.getScoreSimilarita();

            } else if (c == 2){

                score2 = sc.getScoreSimilarita();

                if (score2 >= (score1 * 0.5)){

                    spiegazione += "\ne che\n\t\"" + sc.getTestoFrase() + "\"";

                } else {

                    spiegazione += ".\n";
                    break;

                }


            } else if (c == 3){

                score3 = sc.getScoreSimilarita();

                if (score3 >= (score2 * 0.67) ){

                    spiegazione += ";\ninfine, hanno detto che\n\t\"" + sc.getTestoFrase() + "\".";

                } else {

                    spiegazione += ".\n";
                }


            } else {

                break;
            }

        }

        return spiegazione;

    }


}
