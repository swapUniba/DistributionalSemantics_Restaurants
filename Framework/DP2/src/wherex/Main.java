package wherexp;

public class Main {

    public static void main(String[] args) {


        try {

            double inizio = System.currentTimeMillis();

            for (int i=3; i>=1; i--){

                if (i == 1){
                    Configurazione.TipoLemmi = "unigrammi";
                } else if (i == 2){
                    Configurazione.TipoLemmi = "bigrammi";
                } else if (i == 3){
                    Configurazione.TipoLemmi = "unibigrammi";
                }

                System.out.println(Configurazione.TipoLemmi);

                // Calcolo matrice frase contesto
                MatriceFC.inizializzaMatriceFraseContesto();
                System.out.println("FC inizializzata.");

                // Lettura strutture utili all'esecuzione
                MatriceLF.generaLemmi();
                MatriceLF.generaIDF();
                System.out.println("Lemmi e IDF calcolati.");

                MatriceLF.generaMatriceLemmaFrase();
                System.out.println("LF inizializzata.");

                // Lettura matrice generata
                MatriceLC.generaMatriceLemmaContesto();
                System.out.println("LC inizializzata.");

                //Lettura vettori contesto
                VettoriContesto.generaVettoriContesto();
                VettoriContesto.scriviVettoriContesto();
                System.out.println("Vettori contesto inizializzati");


                MatriceLocaleContesto.calcolaMatrice();
                MatriceLocaleContesto.stampaFileMatriceLocaleContesto();
                MatriceLocaleContesto.generaMatriciBariTorino();
                MatriceLocaleContesto.leggiMatriceLocaleContestoBari();
                MatriceLocaleContesto.leggiMatriceLocaleContestoTorino();
                System.out.println("Matrici locale contesto generate per Bari e Torino");

                System.out.println("locale contesto bari: " + MatriceLocaleContesto.matriceLocaleContestoBari.keySet().size());
                System.out.println("locale contesto torino: " + MatriceLocaleContesto.matriceLocaleContestoTorino.keySet().size());


                MatriceLocaliContestiFrasi.inizializzaMatriceBari();
                MatriceLocaliContestiFrasi.scriviMatriceBari();
                //System.out.println("locali-contesti-frasi bari: " + MatriceLocaliContestiFrasi.localiContestiFrasiBari.size());
                MatriceLocaliContestiFrasi.inizializzaMatriceTorino();
                MatriceLocaliContestiFrasi.scriviMatriceTorino();
                //System.out.println("locali-contesti-frasi torino: " + MatriceLocaliContestiFrasi.localiContestiFrasiTorino.size());
                //System.out.println("Matrici locali-contesti-frasi generate.");



                Top10LocaliPerCombinazione.generaTop10();
                System.out.println("Top5 locali trovati per Bari e Torino");

                MatriceContestiItemFrasi.inizializzaMatriceBari();
                MatriceContestiItemFrasi.scriviMatriceBari();
                MatriceContestiItemFrasi.inizializzaMatriceTorino();
                MatriceContestiItemFrasi.scriviMatriceTorino();
                System.out.println("Matrici contesti-item-frasi generate per Bari e Torino.");


            }

            double durata = System.currentTimeMillis() - inizio;
            System.out.println(durata);


        } catch (Exception e){

            e.printStackTrace();
        }


    }
}
