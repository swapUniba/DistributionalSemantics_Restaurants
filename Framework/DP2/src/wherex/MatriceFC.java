package wherex;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MatriceFC {

    public static TreeMap<Integer, TreeMap<Integer, Double>> matriceFraseContesto = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void inizializzaMatriceFraseContesto() throws Exception {

        try {

            // File di lettura
            Scanner fileFrasiContesti = new Scanner(new File("filesWherEXP\\" + configurazioneLemmi + "\\idFraseidContesi.txt"), "UTF-8");

            // Lettura file
            while (fileFrasiContesti.hasNextLine()) {


                // Lettura riga file
                String riga = fileFrasiContesti.nextLine();

                // Tokenizzazione riga
                StringTokenizer st = new StringTokenizer(riga, ";");

                // id frase
                int idFrase = Integer.parseInt(st.nextToken());

                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ",");

                // mappa contesto -> punteggio
                TreeMap<Integer, Double> contestoPunteggio = new TreeMap<>();

                while (st2.hasMoreTokens()) {

                    int idContesto = Integer.parseInt(st2.nextToken());

                    // inserimento punteggio (fissato a 1)
                    contestoPunteggio.put(idContesto, 1.0);

                }

                for (int i = 1; i <= 13; i++) {

                    if (!contestoPunteggio.containsKey(i)) {

                        contestoPunteggio.put(i, 0.0);
                    }
                }

                // aggiunta della riga
                matriceFraseContesto.put(idFrase, contestoPunteggio);


            }

            // Chiusura file input
            fileFrasiContesti.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    public static void stampaMatriceFraseContesto() {

        for (int idFrase : matriceFraseContesto.keySet()){

            System.out.printf("%-6d -> {", idFrase);

            for (int idContesto : matriceFraseContesto.get(idFrase).keySet()){

                System.out.print(idContesto + " -> " + matriceFraseContesto.get(idFrase).get(idContesto));

                if (idContesto != 13) {
                    System.out.print(",\t");
                }



            }

            System.out.println(" }");
        }

    }

    public static void stampaMatriceFraseContestoFile() throws Exception {

        PrintWriter out = new PrintWriter(new File("filesWherEXP\\" + configurazioneLemmi + "\\strutture\\matrice frase contesto.txt"));

        for (int idFrase : matriceFraseContesto.keySet()){

            out.printf("%-6d -> {", idFrase);

            for (int idContesto : matriceFraseContesto.get(idFrase).keySet()){

                out.print(idContesto + " -> " + matriceFraseContesto.get(idFrase).get(idContesto));

                if (idContesto != 13) {
                    out.print(",\t");
                }
            }

            out.println(" }");
        }

        out.flush();
        out.close();

    }
}
