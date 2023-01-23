package wherexp;

import java.util.Random;

public class Configurazione {


    //public static String TipoLemmi = "unigrammi";
    //public static String TipoLemmi = "bigrammi";
    public static String TipoLemmi = "unibigrammi";

    public static void tipoRandom(){


        Random n = new Random();
        int p = n.nextInt(3);

        if (p == 0){
            TipoLemmi = "unigrammi";
        } else if (p == 1){
            TipoLemmi = "bigrammi";
        } else {
            TipoLemmi = "unibigrammi";
        }


    }

}
