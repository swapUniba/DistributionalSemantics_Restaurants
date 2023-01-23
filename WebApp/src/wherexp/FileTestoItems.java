package wherexp;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class FileTestoItems {

    public static TreeMap<Integer, String> frasiLemmi = new TreeMap<>();
    public static TreeMap<Integer, String> frasiIntere = new TreeMap<>();
    public static TreeMap<Integer, String> locali = new TreeMap<>();

    public static String configurazioneLemmi = Configurazione.TipoLemmi;

    public static void leggiFrasiLocale(int locale) throws Exception {

        Scanner inIntere = new Scanner(new File("filesWherEXP\\frasi singoli items\\intere\\"+locale+".txt"), "UTF-8");
        Scanner inLemmi = new Scanner(new File("filesWherEXP\\frasi singoli items\\" + configurazioneLemmi + "\\" + locale + ".txt"), "UTF-8");

        while(inIntere.hasNextLine()){


            String[] riga = inIntere.nextLine().split(";");

            String loc = riga[0];
            int idFrase = Integer.parseInt(riga[1]);
            String lemmi = riga[2];

            //locali.put(idFrase, loc);
            frasiIntere.put(idFrase, lemmi);


        }

        while (inLemmi.hasNextLine()){


            String[] riga = inLemmi.nextLine().split(";");

            int idFrase = Integer.parseInt(riga[1]);
            String lemmi = riga[2];

            //locali.put(idFrase, locale+"");
            frasiLemmi.put(idFrase, lemmi);

        }

        inIntere.close();
        inLemmi.close();


    }

}
