<%@ page import="wherexp.VettoriContesto" %>
<%@ page import="wherexp.Locale" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.security.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Where&Why</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/scrolling-nav.css" rel="stylesheet">
    <link href="../css/radio.css" rel="stylesheet">
    <link href="../css/button_style.css" rel="stylesheet">
    <link href="../css/slider.css" rel="stylesheet">


    <script type="text/javascript" src="../js/script.js"></script>

    <script>
        function coloreRange(id) {

            var fine = document.getElementById(id).value;
            p = "valore"+id;

            document.getElementById(p).innerHTML = fine;

        }
    </script>


</head>

<body id="page-top">
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Where&Why</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>


<%




    // Strutture parametri
    ArrayList<Integer> frasiCentroide = new ArrayList<Integer>();
    HashMap<Integer, Integer> frasiSingole = new HashMap<Integer, Integer>();
    int locale = 0;
    String stringaLocale = "";
    String configurazione = "";
    String citta=request.getParameter("citta");

    String spiegazioneCentroide = "";
    String spiegazioneSingole = "";
    String spiegazioneBaseline = "";

    int numeroContesti = 0;
    String listaContesti = "";


    HashMap<String, ArrayList<Integer>> fraseContesti = new HashMap<String, ArrayList<Integer>>();


    try{

        // Parametri metodo get
        Map<String, String[]> mappaParametri = request.getParameterMap();

        for (String parametro : mappaParametri.keySet()) {

            if (parametro.equals("centroide")){

                for (String frase : mappaParametri.get(parametro)){

                    frasiCentroide.add(Integer.parseInt(frase));

                }

            } else if (parametro.equals("frasiSingole")){

                for (String contestoFrase : mappaParametri.get(parametro)){

                    int contesto = Integer.parseInt(contestoFrase.split(":")[0]);
                    int frase = Integer.parseInt(contestoFrase.split(":")[1]);

                    frasiSingole.put(contesto, frase);

                }

            } else if (parametro.equals("locale")){

                locale = Integer.parseInt(mappaParametri.get(parametro)[0]);
                stringaLocale = mappaParametri.get(parametro)[0];

            } else if (parametro.equals("configurazione")){

                configurazione = mappaParametri.get(parametro)[0];

            }
        }

        numeroContesti = frasiSingole.size();

        boolean pC = true;
        for (int c : frasiSingole.keySet()){
            if(pC){
                pC=false;
            } else {
                listaContesti += ",";
            }
            listaContesti += c+"";
        }


        if (numeroContesti == 1){
            spiegazioneCentroide += "Ti consiglio questo locale, per il contesto che hai selezionato, ";
        } else {
            spiegazioneCentroide += "Ti consiglio questo locale, per i contesti che hai selezionato, ";
        }

        spiegazioneCentroide += "perchè le persone che ci sono state hanno detto che: <b>\"";
        spiegazioneSingole = "Ti consiglio questo locale perchè le persone che ci sono state hanno detto che";


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("filesWherEXP/frasi singoli items/intere dat/"+locale+".dat")));
        HashMap<Integer, String> mappaFrasi = (HashMap<Integer, String>) ois.readObject();
        ois.close();




        for (int frase : frasiCentroide){

            String f = mappaFrasi.get(frase).trim();

            if (f.charAt(f.length()-1) != '.' &&
                    f.charAt(f.length()-1) != '?' &&
                    f.charAt(f.length()-1) != '!'){
                f += ".";
            }

            spiegazioneCentroide += f + " ";


        }

        spiegazioneCentroide += "\"</b>.";

        boolean primo = true;
        int cont = 0;

        for (int c : frasiSingole.keySet()){

            if (fraseContesti.containsKey(mappaFrasi.get(frasiSingole.get(c)))){

                fraseContesti.get(mappaFrasi.get(frasiSingole.get(c))).add(c);

            } else {

                ArrayList<Integer> temp = new ArrayList<Integer>();
                fraseContesti.put(mappaFrasi.get(frasiSingole.get(c)), temp);
                fraseContesti.get(mappaFrasi.get(frasiSingole.get(c))).add(c);

            }

        }


        for (String f : fraseContesti.keySet()){

            cont++;

            if (cont == 2 || cont == 4){
                spiegazioneSingole += ", e che";
            } else if (cont == 3){
                spiegazioneSingole += "; inoltre, ";
            } else if (cont == 5){
                spiegazioneSingole += "; infine, hanno detto che ";
            }

            if (fraseContesti.get(f).size() != 1){

                String parteFraseContesti = "";

                for (int i=0; i<fraseContesti.get(f).size(); i++){

                    if (i > 0) cont++;

                    int c = fraseContesti.get(f).get(i);

                    switch (c){

                        case 1:
                            parteFraseContesti += " è un locale adatto alla colazione perchè: ";
                            break;
                        case 2:
                            parteFraseContesti += " è un locale adatto al pranzo perchè: ";
                            break;
                        case 3:
                            parteFraseContesti += " è un locale adatto alla cena perchè: ";
                            break;
                        case 4:
                            parteFraseContesti += " è un buon locale per trascorrere una giornata in compagnia di amici perchè: ";
                            break;
                        case 5:
                            parteFraseContesti += " è un buon locale per tascorrere una giornata in compagnia del proprio partner perchè: ";
                            break;
                        case 6:
                            parteFraseContesti += " è un buon locale per trascorrere una giornata in famiglia perchè: ";
                            break;
                        case 7:
                            parteFraseContesti += " è un ottimo locale da frequentare nei giorni feriali perchè: ";
                            break;
                        case 8:
                            parteFraseContesti += " è un ottimo locale da frequentare nei giorni festivi perchè: ";
                            break;
                        case 9:
                            parteFraseContesti += " è un locale adeguato a quando sei di buon umore perchè: ";
                            break;
                        case 10:
                            parteFraseContesti += " è un locale adeguato a quando non sei di buon umore perchè: ";
                            break;
                        case 11:
                            parteFraseContesti += " è un locale in cui puoi mangiare del cibo salutare perchè: ";
                            break;
                        case 12:
                            parteFraseContesti += " è un locale in cui puoi mangiare del cibo non salutare perchè: ";
                            break;
                        case 13:
                            parteFraseContesti += " è un locale idoneo a determinate esigenze alimentari perchè: ";
                            break;
                    }

                    //toglire perchè a tutti quelli che non sono ultimi
                    if (i <= fraseContesti.get(f).size()-1){

                        parteFraseContesti = parteFraseContesti.split("perchè: ")[0];


                    }

                    if (i != fraseContesti.get(f).size()-1){
                        if (i < fraseContesti.get(f).size()-2){
                            parteFraseContesti += ", ";
                        } else {
                            parteFraseContesti += " ed ";
                        }
                    } else {
                        parteFraseContesti += " perchè: ";
                    }


                }

                spiegazioneSingole += parteFraseContesti + "<b>\"" + f +"</b>\"";

            } else {

                int c = fraseContesti.get(f).get(0);

                switch (c){

                    case 1:
                        spiegazioneSingole += " è un locale adatto alla colazione perchè: ";
                        break;
                    case 2:
                        spiegazioneSingole += " è un locale adatto al pranzo perchè: ";
                        break;
                    case 3:
                        spiegazioneSingole += " è un locale adatto alla cena perchè: ";
                        break;
                    case 4:
                        spiegazioneSingole += " è un buon locale per trascorrere una giornata in compagnia di amici perchè: ";
                        break;
                    case 5:
                        spiegazioneSingole += " è un buon locale per tascorrere una giornata in compagnia del proprio partner perchè: ";
                        break;
                    case 6:
                        spiegazioneSingole += " è un buon locale per trascorrere una giornata in famiglia perchè: ";
                        break;
                    case 7:
                        spiegazioneSingole += " è un ottimo locale da frequentare nei giorni feriali perchè: ";
                        break;
                    case 8:
                        spiegazioneSingole += " è un ottimo locale da frequentare nei giorni festivi perchè: ";
                        break;
                    case 9:
                        spiegazioneSingole += " è un locale adeguato a quando sei di buon umore perchè: ";
                        break;
                    case 10:
                        spiegazioneSingole += " è un locale adeguato a quando non sei di buon umore perchè: ";
                        break;
                    case 11:
                        spiegazioneSingole += " è un locale in cui puoi mangiare del cibo salutare perchè: ";
                        break;
                    case 12:
                        spiegazioneSingole += " è un locale in cui puoi mangiare del cibo non salutare perchè: ";
                        break;
                    case 13:
                        spiegazioneSingole += " è un locale idoneo a determinate esigenze alimentari perchè: ";
                        break;
                }


                spiegazioneSingole += "<b>\"" + mappaFrasi.get(frasiSingole.get(c)) +"</b>\"";



            }

        }

        spiegazioneSingole += ".";


    } catch (Exception e){

        e.printStackTrace();

    }


%>


<header class="bg-primary text-white">
    <div class="container text-center">
        <h1>Ecco il locale che ti suggeriamo</h1>
        <p class="lead">
            <%

                try {


                    Scanner infoLocale = new Scanner(new File("filesWherEXP/info utili/" + locale + ".txt"));
                    Locale l = new Locale();

                    l.setId(Integer.parseInt(infoLocale.nextLine()));
                    l.setUrl(infoLocale.nextLine());
                    l.setNome(infoLocale.nextLine().replaceAll("'", ""));
                    l.setIndirizzo(infoLocale.nextLine().replaceAll("'", ""));
                    l.setTelefono(infoLocale.nextLine().replaceAll("'", ""));
                    l.setCategoria(infoLocale.nextLine().replaceAll("'", ""));

                    out.println("<h4>" + l.getNome() + "</h4><br>" + l.getIndirizzo() +
                            "<br>" + l.getTelefono() + "<br>Categoria: " + l.getCategoria() +
                            "<br><img width=\"300\" height=\"300\" src=\""+l.getUrl()+"\"");



                    boolean trovato = false;
                    Scanner baseline = new Scanner(new File("filesWherEXP/baseline.txt"), "UTF-8");
                    spiegazioneBaseline = "Le persone che ci sono state hanno detto che:";

                    while (baseline.hasNextLine()){

                        String rigaBaseline = baseline.nextLine();
                        int localeRigaBaseline = Integer.parseInt(rigaBaseline.split(";")[0]);

                        if (locale == localeRigaBaseline){

                            int contestoBaseline = Integer.parseInt(rigaBaseline.split(";")[1]);

                            for (int contesto : frasiSingole.keySet()){

                                if (contesto == contestoBaseline){

                                    trovato = true;
                                    spiegazioneBaseline += "<br>" + VettoriContesto.contesti.get(contesto) + ": " + "<b>" + rigaBaseline.split(";")[2] + "</b>";

                                }
                            }

                        }

                    }

                    baseline.close();

                    if (!trovato){
                        spiegazioneBaseline = "Il sistema non ha potuto generare alcuna spiegazione.";
                    }


                } catch (Exception e){
                    e.printStackTrace();
                }

            %>
        </p>
    </div>
</header>


<section id="about">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 mx-auto">
        <h2></h2>
        <p class="lead">
            <%
                out.println(spiegazioneCentroide);

            %>
        </p>
      </div>
    </div>
  </div>
</section>


<section id="services" class="bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2 align="center">Ricordando che l'obiettivo del sistema è di produrre una spiegazione adeguata ai diversi
                contesti di utilizzo,</h2>
                <p class="lead" align="center">rispondi a queste semplici domande, dove:<br>
                    - 1 indica che sei in totale disaccordo<br>
                    - 5 indica che sei in totale accordo</p>


                <form action="../salvaValutzione1" method="post">

                    <%


                        String tempo = request.getParameter("tempo").trim();

                        PrintWriter spiegazioni = new PrintWriter("filesWherEXP/temp/spiegazioni"+tempo+".txt","UTF-8");
                        spiegazioni.println(spiegazioneCentroide);
                        spiegazioni.println(spiegazioneSingole);
                        spiegazioni.println(spiegazioneBaseline);
                        spiegazioni.flush();
                        spiegazioni.close();

                        PrintWriter report = new PrintWriter("filesWherEXP/temp/report"+tempo+".txt","UTF-8");
                        report.println(tempo+";"+configurazione+";"+citta+";"+locale+";"+numeroContesti+";"+listaContesti);
                        report.flush();
                        report.close();


                    %>

                    <div class="slidecontainer">
                        <div>Ho compreso perchè l'attività mi è stata suggerita:</div><div id="valorepref1">3</div><br>
                        <input type="range" id="pref1" name="pref1" min="1" max="5" value="3" step="1" class="slider" onfocus="coloreRange(this.id)" onclick="coloreRange(this.id)" onchange="coloreRange(this.id)">
                    </div>

                    <br><br>

                    <div class="slidecontainer">
                        <div>La spiegazione ha reso il suggerimento più convincente:</div><div id="valorepref2">3</div><br>
                        <input type="range" id="pref2" name="pref2" min="1" max="5" value="3" step="1"class="slider" onfocus="coloreRange(this.id)" onclick="coloreRange(this.id)" onchange="coloreRange(this.id)">
                    </div>

                    <br><br>

                    <div class="slidecontainer">
                        <div>La spiegazione mi ha permesso di scoprire nuove informazioni relative all'attività:</div><div id="valorepref3">3</div><br>
                        <input type="range" id="pref3" name="pref3" min="1" max="5" value="3" step="1" class="slider" onfocus="coloreRange(this.id)" onclick="coloreRange(this.id)" onchange="coloreRange(this.id)">
                    </div>

                    <br><br>

                    <div class="slidecontainer">
                        <div>La spiegazione ha incrementato il mio livello di fiducia nel recommender system:</div><div id="valorepref4">3</div><br>
                        <input type="range" id="pref4" name="pref4" min="1" max="5" value="3" step="1" class="slider" onfocus="coloreRange(this.id)" onclick="coloreRange(this.id)" onchange="coloreRange(this.id)">
                    </div>

                    <div  align="center">
                        <input type="submit" id="valutazione1" value="Invia valutazione"
                               class="myButton" style="background: #5562eb">
                    </div>

                    <input type="hidden" id="tempo" name="tempo" value="<%out.println(tempo);%>">

                </form>
            </div>
        </div>
    </div>
</section>

<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Progetto di Tesi di Laurea in Informatica e TPS<br>
            Laureando: Giuseppe Spillo<br>
            Relatore: dott. Cataldo Musto</p>
        <div style="padding-left:250px; padding-right:230px;">
            <a href="https://www.uniba.it/"><img align="right" src="../Logo_Uniba.png"></a>
            <a href="http://www.di.uniba.it/~swap/"><img height="60" align="center" src="../swap.PNG"></a>
        </div>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="../js/scrolling-nav.js"></script>

</body>

</html>
