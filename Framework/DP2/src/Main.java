import wherex.*;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void lol() throws Exception{

        Scanner s = new Scanner(new File("dataset.txt"), "UTF-8");

        int c = 0;

        while (s.hasNextLine()){

            String recensione = s.nextLine();

            String[] frasi = recensione.split(";")[1].split(".");

            String fr = "";

            for (String f : frasi){

                fr = f.replaceAll("!",".");
                fr = f.replaceAll("/?",".");

            }

            c += fr.split(".").length+1;

            if (c % 1000 == 0) System.out.println(c);

        }

        PrintWriter pw = new PrintWriter(new FileWriter("risposta.txt"));
        pw.println(c);
        pw.flush();
        pw.close();

        s.close();

    }

    public static void main(String[] args) {


        try {

            lol();

            /*

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

                /*

                MatriceLocaleContesto.calcolaMatrice();
                MatriceLocaleContesto.stampaFileMatriceLocaleContesto();
                //MatriceLocaleContesto.leggiMatriceLocaleContesto();
                //MatriceLocaleContesto.stampaFileMatriceLocaleContesto();
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



                Top5LocaliPerCombinazione.generaTop5();
                System.out.println("Top5 locali trovati per Bari e Torino");

                MatriceContestiItemFrasi.inizializzaMatriceBari();
                MatriceContestiItemFrasi.scriviMatriceBari();
                MatriceContestiItemFrasi.inizializzaMatriceTorino();
                MatriceContestiItemFrasi.scriviMatriceTorino();
                System.out.println("Matrici contesti-item-frasi generate per Bari e Torino.");

                */

            /*

            }





            double durata = System.currentTimeMillis() - inizio;
            System.out.println(durata);


            /*

            Top5LocaliPerCombinazione.leggiBari();
            Top5LocaliPerCombinazione.leggiTorino();
            System.out.println("Matrice contesti locali lette.");

            MatriceLocaleContesto.leggiMatriceLocaleContestoBari();
            MatriceLocaleContesto.leggiMatriceLocaleContestoTorino();
            System.out.println("Matrici locali contesti lette.");

            MatriceLocaliContestiFrasi.leggiMatriceBari();
            MatriceLocaliContestiFrasi.leggiMatriceTorino();
            System.out.println("Matrici locali contesti frasi lette.");
            */

            /*
            MatriceContestiItemFrasi.inizializzaMatriceBari();
            MatriceContestiItemFrasi.scriviMatriceBari();
            System.out.println("Matrice contesti-item-frasi Bari scritta.");

            MatriceContestiItemFrasi.matriceContestiItemFrasiBari.clear();
            */

            /*
            MatriceContestiItemFrasi.inizializzaMatriceTorino();
            MatriceContestiItemFrasi.scriviMatriceTorino();
            System.out.println("Matrice contesti-item-frasi Torino scritta.");

            */




            /*
            for (int i=1; i<=791; i++){

                Scanner filein = new Scanner(new File("filesWherEXP\\frasi singoli items\\intere\\"+i+".txt"));
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("filesWherEXP\\frasi singoli items\\intere dat\\"+i+".dat")));

                HashMap<Integer, String> frasiIntere = new HashMap<>();

                while(filein.hasNextLine()){

                    String[] riga = filein.nextLine().split(";");


                    frasiIntere.put(Integer.parseInt(riga[1]), riga[2]);

                }

                oos.writeObject(frasiIntere);

                oos.flush();
                oos.close();
                filein.close();

            }

            */


            /*
            MatriceContestiItemFrasi.inizializzaMatriceTorino();
            MatriceContestiItemFrasi.scriviMatriceTorino();
            System.out.println("Matrice contesti-item-frasi Torino scritta.");


            System.out.println("END");
            */

        } catch (Exception e){

            e.printStackTrace();
        }


    }
}
