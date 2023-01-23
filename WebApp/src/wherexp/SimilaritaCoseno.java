package wherexp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class SimilaritaCoseno implements Comparable<SimilaritaCoseno>{

    // se score ==-1 -> non calcolabile
    // se score >= 0 -> punteggio

    private TreeMap<String, Double> frase, contesto;
    private double scoreSimilarita;
    private int idFrase;
    private String testoFrase;
    private int idContesto;

    // Costruttore per una frase ed un contesto
    public SimilaritaCoseno(TreeMap<String, Double> frase, TreeMap<String, Double> contesto){

        this.frase = frase;
        this.contesto = contesto;

        //if (controlloLemmi()){
        if (true){

            this.scoreSimilarita = calcolaScoreFrase();

        } else {

            this.scoreSimilarita = -1;

        }
    }

    // Costruttore per una frase ed un insieme di contesti
    public SimilaritaCoseno(TreeMap<String, Double> frase, HashSet<Integer> contesti) throws Exception{

        this.frase = frase;
        this.contesto = new TreeMap<>();

        boolean controllo = true;

        if (controllo){

            /* Si calcolano le somme degli score dei lemmi comuni */

            int contatoreContesti = 0;

            // Scorrimento lista contesti scelti
            for (int contesto : contesti){

                contatoreContesti++;

                // Ottenimento vettore contesto corrente
                TreeMap<String, Double> vettoreContesto = VettoriContesto.vettoriContesto.get(contesto);


                //Per ogni lemma del contesto
                for (String lemma : vettoreContesto.keySet()){

                    // Calcolo nuovo valore
                    double nuovoValore;

                    /* Se il valore non è presente, viene impostato quello corrente,
                        altrimenti viene aggiornato il valore che già c'era */
                    if (!this.contesto.containsKey(lemma)){

                        nuovoValore = vettoreContesto.get(lemma);
                        this.contesto.put(lemma, nuovoValore);

                    } else {

                        nuovoValore = vettoreContesto.get(lemma) + this.contesto.get(lemma);

                        this.contesto.remove(lemma);
                        this.contesto.put(lemma, nuovoValore);
                        //this.contesto.replace(lemma, nuovoValore);
                    }


                }
            }


            /* A questo punto si hanno le somme, va calcolata solo la media scorrendo
                il vettore contesto centroide */
            for (String lemma : this.contesto.keySet()){

                double nuovoValore = this.contesto.get(lemma) / contatoreContesti;
                //this.contesto.remove(lemma);
                this.contesto.put(lemma, nuovoValore);

                //this.contesto.replace(lemma, this.contesto.get(lemma) / contatoreContesti);

            }

            // Centroide calcolato. Si calcola ora lo score di similarità
            this.scoreSimilarita = calcolaScoreFrase();




        } else {

            this.scoreSimilarita = -1;
        }


    }

    public double calcolaScoreFrase(){

        double similarita = 0;

        double numeratore = 0, denA = 0, denB = 0, denominatore = 0;

        for (String lemma : frase.keySet()){

            double a = frase.get(lemma);
            double b = contesto.get(lemma);

            numeratore += a*b;

            denA += Math.pow(frase.get(lemma), 2);
            denB += Math.pow(contesto.get(lemma), 2);

        }

        denominatore = Math.sqrt(denA) * Math.sqrt(denB);

        if (denominatore == 0){

            similarita = 0;

        } else {

            similarita = numeratore / denominatore;
        }

        return similarita;
    }

    public boolean controlloLemmi(){

        boolean esito = true;

        HashSet<String> lemmiFrase = new HashSet<>();
        HashSet<String> lemmiContesto = new HashSet<>();

        for (String l : lemmiFrase){

            lemmiFrase.add(l);
        }

        for (String l : lemmiContesto){

            lemmiContesto.add(l);
        }

        if (lemmiContesto.size() != lemmiFrase.size()){

            esito = false;

        } else {

            int dimensioneFrase = lemmiFrase.size(), dimensioneContesto = lemmiContesto.size();

            lemmiFrase.retainAll(lemmiContesto);

            if (lemmiFrase.size() == dimensioneFrase){

                lemmiContesto.retainAll(lemmiFrase);


                if (lemmiContesto.size() == dimensioneContesto){

                    esito = true;

                } else {

                    esito = false;
                }
            }

        }

        return esito;

    }



    public double getScoreSimilarita() {
        return scoreSimilarita;
    }

    public void setScoreSimilarita(double scoreSimilarita) {
        this.scoreSimilarita = scoreSimilarita;
    }

    public int getIdFrase(){
        return idFrase;
    }

    public void setIdFrase(int idFrase){
        this.idFrase = idFrase;
    }

    public String getTestoFrase() {
        return testoFrase;
    }

    public void setTestoFrase(String testoFrase) {
        this.testoFrase = testoFrase;
    }

    public int getIdContesto() {
        return idContesto;
    }

    public void setIdContesto(int idContesto) {
        this.idContesto = idContesto;
    }

    @Override
    public int compareTo(SimilaritaCoseno sc) {

        // Ordine decrescente

        if (this.scoreSimilarita < sc.scoreSimilarita){

            return 1;

        } else if (this.scoreSimilarita > sc.scoreSimilarita){

            return -1;

        } else {

            return 0;
        }
    }
}
