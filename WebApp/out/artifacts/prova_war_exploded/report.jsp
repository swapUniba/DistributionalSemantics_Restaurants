<%@ page import="wherexp.Report" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WherEXP</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <link href="css/radio.css" rel="stylesheet">
    <link href="css/button_style.css" rel="stylesheet">

    <script type="text/javascript" src="js/script.js"></script>


</head>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">WherEXP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<header class="bg-primary text-white">
    <div class="container text-center">
        <h1>Report</h1>
    </div>
</header>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">

                <%

                    HashMap<String, Double> a = null;
                    HashMap<String, Double> b = null;
                    HashMap<String, HashMap<String, Double>> c = null;
                    HashMap<String, HashMap<String, Double>> d = null;
                    HashMap<String, HashMap<String, Double>> e = null;
                    HashMap<String, HashMap<String, HashMap<String, Double>>> f = null;
                    HashMap<String, HashMap<String, HashMap<String, Double>>> g = null;
                    HashMap<String, HashMap<String, Integer>> h = null;
                    HashMap<Integer, HashMap<String, Double>> i = null;
                    HashMap<Integer, Integer> l = null;
                    HashMap<Integer, HashMap<String, HashMap<String, Double>>> o = null;
                    HashMap<Integer, HashMap<String, Double>> p = null;

                    HashMap<String, Integer> contatori = null;
                    HashMap<Integer, Integer> contatoriContesti = null;


                    int numeroUtenti = 1;
                    Report r = null;

                    try{

                        r = new Report();

                        a = r.sistemaVSBaseline();
                        b = r.centroideVSSingoleGenerale();
                        c = r.centroideVSSingolePreferenze();
                        d = r.valutazioniAssoluteCentroide();
                        e = r.valutazioniAssoluteSingole();
                        f = r.centroideVSSingoleMetriche();
                        g = r.sistemaVSBaselineMetriche();
                        h = r.infoUtenti();
                        i = r.centroideVSsingoleNumContesti();
                        l = r.contatoriLocaliSuggeriti();
                        o = r.medieMetrichePerContesti();
                        p = r.medieMetrichePerContestiGenerale();

                        contatori = r.contatoriConfigurazioni();
                        contatoriContesti = r.contatoriNumeroContesti();

                        numeroUtenti = r.getUtenti().size();

                    } catch (Exception exception){

                        exception.printStackTrace();

                    }
                %>



                <table border="1" align="center">
                    <tr>
                        <td colspan="5" align="center"><h5>Statistiche utenti</h5></td>
                    </tr>
                    <tr>
                        <td colspan="5" align="center"><b>Età</b></td>
                    </tr>
                    <tr>
                        <td width="80" align="center"> < 18</td>
                        <td width="80" align="center">18 - 25</td>
                        <td width="80" align="center">26 - 35</td>
                        <td width="80" align="center">36 - 50</td>
                        <td width="80" align="center"> > 50</td>
                    </tr>
                    <tr>
                        <td align="center"><%out.println(r.valorePercentuale(h.get("Eta").get("<18"), numeroUtenti));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(h.get("Eta").get("18-25"), numeroUtenti));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(h.get("Eta").get("26-35"), numeroUtenti));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(h.get("Eta").get("36-50"), numeroUtenti));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(h.get("Eta").get(">50"), numeroUtenti));%></td>
                    </tr>

                    <tr>
                        <td colspan="5" align="center"><b>Genere</b></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2">Uomo</td>
                        <td align="center" colspan="3">Donna</td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2"><%out.println(r.valorePercentuale(h.get("Genere").get("uomo"), numeroUtenti));%></td>
                        <td align="center" colspan="3"><%out.println(r.valorePercentuale(h.get("Genere").get("donna"), numeroUtenti));%></td>
                    </tr>
                    <tr>
                        <td colspan="5" align="center"><b>Titolo di studio</b></td>
                    </tr>
                    <tr>
                        <td  align="center">Diploma Scuola Superiore</td>
                        <td align="center">Laurea Triennale</td>
                        <td  align="center">Laurea Magistrale</td>
                        <td  align="center">PhD</td>
                        <td  align="center">Altro</td>
                    </tr>
                    <tr>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Titoli di studio").get("Diploma Scuola superiore"), numeroUtenti));%></td>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Titoli di studio").get("Laurea Triennale"), numeroUtenti));%></td>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Titoli di studio").get("Laurea Magistrale"), numeroUtenti));%></td>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Titoli di studio").get("Dottorato di Ricerca"), numeroUtenti));%></td>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Titoli di studio").get("Altro"), numeroUtenti));%></td>
                    </tr>

                    <tr>
                        <td colspan="5" align="center"><b>Uso Recommender System</b></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="3">Sì</td>
                        <td align="center" colspan="2">No</td>
                    </tr>
                    <tr>
                        <td align="center" colspan="3"><%out.print(r.valorePercentuale(h.get("Uso Rec Sys").get("si"), numeroUtenti));%></td>
                        <td align="center" colspan="2"><%out.println(r.valorePercentuale(h.get("Uso Rec Sys").get("no"), numeroUtenti));%></td>
                    </tr>

                    <tr>
                        <td colspan="5" align="center"><b>Frequenza uscite settimanali</b></td>
                    </tr>
                    <tr>
                        <td align="center">0-1 volta</td>
                        <td align="center" colspan="2">2-4 volte</td>
                        <td align="center" colspan="2">5-7 volte</td>
                    </tr>
                    <tr>
                        <td align="center"><%out.print(r.valorePercentuale(h.get("Frequenze uscite").get("0-1 volta"), numeroUtenti));%></td>
                        <td align="center" colspan="2"><%out.print(r.valorePercentuale(h.get("Frequenze uscite").get("2-4 volte"), numeroUtenti));%></td>
                        <td align="center" colspan="2"><%out.print(r.valorePercentuale(h.get("Frequenze uscite").get("5-7 volte"), numeroUtenti));%></td>
                    </tr>
                </table>

                <br><br>

                <table border="1" align="center">
                    <tr>
                        <td align="center" colspan="3" width="200"><h5>Preferenze generali</h5></td>
                    </tr>
                    <tr>
                        <td rowspan="2" align="center" width="100">Baseline</td>
                        <td colspan="2" align="center">Sistema</td>
                    </tr>
                    <tr>
                        <td align="center" width="100">Frasi singole</td>
                        <td align="center" width="100">Centroide</td>
                    </tr>
                    <tr>
                        <td align="center"><%out.println(r.valorePercentuale(a.get("baseline")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(a.get("singole")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(a.get("centroide")));%></td>
                    </tr>
                </table>

                <br><br>

                <table border="1" align="center">
                    <tr>
                        <td align="center" colspan="3" width="360"><h5>Centroide VS Frasi singole</h5></td>
                    </tr>
                    <tr>
                        <td align="center" width="120">Centroide</td>
                        <td align="center" width="120">Frasi singole</td>
                        <td align="center" width="120">Indifferente</td>
                    </tr>
                    <tr>
                        <td align="center"><%out.println(r.valorePercentuale(b.get("centroide")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(b.get("singole")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(b.get("indifferente")));%></td>
                    </tr>
                </table>

                <br><br>

                <table align="center" border="1">

                    <tr>
                        <td align="center" align="center" colspan="9" width="200"><h5>Centroide VS Frasi singole - Dettaglio</h5></td>
                    </tr>

                    <tr>
                        <td align="center"></td>
                        <td align="center">Centroide</td>
                        <td align="center">Frasi singole</td>
                        <td align="center">Indifferente</td>
                    </tr>

                    <tr>
                        <td align="center">Unigrammi</td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unigrammi").get("centroide")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unigrammi").get("singole")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unigrammi").get("indifferenti")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Bigrammi</td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("bigrammi").get("centroide")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("bigrammi").get("singole")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("bigrammi").get("indifferenti")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Unigrammi + Bigrammi</td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unibigrammi").get("centroide")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unibigrammi").get("singole")));%></td>
                        <td align="center"><%out.println(r.valorePercentuale(c.get("unibigrammi").get("indifferenti")));%></td>
                    </tr>

                </table>


                <br><br>

                <table align="center" border="1">


                    <tr>
                        <td align="center" align="center" colspan="9" width="200"><h5>Valutazioni centroide</h5></td>
                    </tr>

                    <tr>
                        <td align="center"></td>
                        <td align="center">Unigrammi</td>
                        <td align="center">Bigrammi</td>
                        <td align="center">Unigrammi + Bigrammi</td>
                    </tr>

                    <tr>
                        <td align="center">Trasparenza</td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unigrammi").get("Trasparenza")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("bigrammi").get("Trasparenza")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unibigrammi").get("Trasparenza")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Persuasione</td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unigrammi").get("Persuasione")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("bigrammi").get("Persuasione")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unibigrammi").get("Persuasione")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Coinvolgimento</td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unigrammi").get("Coinvolgimento")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("bigrammi").get("Coinvolgimento")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unibigrammi").get("Coinvolgimento")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Fiducia</td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unigrammi").get("Fiducia")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("bigrammi").get("Fiducia")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(d.get("unibigrammi").get("Fiducia")));%></td>
                    </tr>

                </table>

                <br><br>

                <table align="center" border="1">


                    <tr>
                        <td align="center" align="center" colspan="9" width="200"><h5>Valutazioni frasi singole</h5></td>
                    </tr>

                    <tr>
                        <td align="center"></td>
                        <td align="center">Unigrammi</td>
                        <td align="center">Bigrammi</td>
                        <td align="center">Unigrammi + Bigrammi</td>
                    </tr>

                    <tr>
                        <td align="center">Trasparenza</td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unigrammi").get("Trasparenza")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("bigrammi").get("Trasparenza")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unibigrammi").get("Trasparenza")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Persuasione</td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unigrammi").get("Persuasione")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("bigrammi").get("Persuasione")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unibigrammi").get("Persuasione")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Coinvolgimento</td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unigrammi").get("Coinvolgimento")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("bigrammi").get("Coinvolgimento")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unibigrammi").get("Coinvolgimento")));%></td>
                    </tr>
                    <tr>
                        <td align="center">Fiducia</td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unigrammi").get("Fiducia")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("bigrammi").get("Fiducia")));%></td>
                        <td align="center"><%out.println(r.valoreDecimale(e.get("unibigrammi").get("Fiducia")));%></td>
                    </tr>

                </table>

                <br><br>

                <table align="center" border="1">

                    <tr>
                        <td align="center" colspan="9" width="200"><h5>Metriche frasi singole VS centroide</h5></td>
                    </tr>

                    <tr>
                        <td align="center" width="120"></td>
                        <td align="center" width="120" colspan="2">Unigrammi</td>
                        <td align="center" width="120" colspan="2">Bigrammi</td>
                        <td align="center" width="120" colspan="2">Unigrammi + Bigrammi</td>
                    </tr>

                    <tr>
                        <td></td>
                        <td align="center">Centroide</td>
                        <td align="center">Frasi singole</td>
                        <td align="center">Centroide</td>
                        <td align="center">Frasi singole</td>
                        <td align="center">Centroide</td>
                        <td align="center">Frasi singole</td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Trasparenza</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("centroide").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("singole").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("centroide").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("singole").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("centroide").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("singole").get("Trasparenza")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Persuasione</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("centroide").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("singole").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("centroide").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("singole").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("centroide").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("singole").get("Persuasione")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Coinvolgimento</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("centroide").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("singole").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("centroide").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("singole").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("centroide").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("singole").get("Coinvolgimento")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Fiducia</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("centroide").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unigrammi").get("singole").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("centroide").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("bigrammi").get("singole").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("centroide").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(f.get("unibigrammi").get("singole").get("Fiducia")));%></td>
                    </tr>




                </table>

                <br><br>

                <table align="center" border="1">

                    <tr>
                        <td align="center" colspan="9" width="200"><h5>Metriche sistema VS baseline</h5></td>
                    </tr>

                    <tr>
                        <td align="center" width="120"></td>
                        <td align="center" width="120" colspan="2">Unigrammi</td>
                        <td align="center" width="120" colspan="2">Bigrammi</td>
                        <td align="center" width="120" colspan="2">Unigrammi + Bigrammi</td>
                    </tr>

                    <tr>
                        <td></td>
                        <td align="center">Sistema</td>
                        <td align="center">Baseline</td>
                        <td align="center">Sistema</td>
                        <td align="center">Baseline</td>
                        <td align="center">Sistema</td>
                        <td align="center">Baseline</td>
                    </tr>


                    <tr>
                        <td align="center" width="120">Trasparenza</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("sistema").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("baseline").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("sistema").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("baseline").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("sistema").get("Trasparenza")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("baseline").get("Trasparenza")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Persuasione</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("sistema").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("baseline").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("sistema").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("baseline").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("sistema").get("Persuasione")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("baseline").get("Persuasione")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Coinvolgimento</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("sistema").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("baseline").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("sistema").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("baseline").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("sistema").get("Coinvolgimento")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("baseline").get("Coinvolgimento")));%></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Fiducia</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("sistema").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unigrammi").get("baseline").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("sistema").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("bigrammi").get("baseline").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("sistema").get("Fiducia")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(g.get("unibigrammi").get("baseline").get("Fiducia")));%></td>
                    </tr>

                </table>

                <br><br>

                <table align="center" border=1>

                    <tr>
                        <td align="center" colspan="9" width="200"><h5>Centroide VS Frasi Singole - Numero contesti</h5></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">Num. contesti</td>
                        <td align="center" width="120">Centroide</td>
                        <td align="center" width="120">Singole</td>
                        <td align="center" width="120">Indifferente</td>
                    </tr>

                    <tr>
                        <td align="center" width="120">1</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(1).get("Centroide")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(1).get("Singole")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(1).get("Indifferente")));%></td>
                    </tr>
                    <tr>
                        <td align="center" width="120">2</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(2).get("Centroide")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(2).get("Singole")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(2).get("Indifferente")));%></td>
                    </tr>
                    <tr>
                        <td align="center" width="120">3</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(3).get("Centroide")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(3).get("Singole")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(3).get("Indifferente")));%></td>
                    </tr>
                    </tr>
                    <tr>
                        <td align="center" width="120">4</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(4).get("Centroide")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(4).get("Singole")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(4).get("Indifferente")));%></td>
                    </tr>
                    <tr>
                        <td align="center" width="120">5</td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(5).get("Centroide")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(5).get("Singole")));%></td>
                        <td align="center" width="120"><%out.println(r.valorePercentuale(i.get(5).get("Indifferente")));%></td>
                    </tr>

                </table>

                <br><br>



                <br><br>

                <table align="center" border=1>

                <tr>
                    <td align="center" colspan="3" width="200"><h5>Contatori configurazioni</h5></td>
                </tr>

                <tr>
                    <td align="center" width="120">
                        Numero unigrammi
                    </td>
                    <td align="center" width="120">
                        Numero bigrammi
                    </td>
                    <td align="center" width="120">
                        Numero unibigrammi
                    </td >
                </tr>
                <tr>
                    <td align="center" width="120">
                        <%out.println(contatori.get("unigrammi"));%>
                    </td>
                    <td align="center" width="120">
                        <%out.println(contatori.get("bigrammi"));%>
                    </td>
                    <td align="center" width="120">
                        <%out.println(contatori.get("unibigrammi"));%>
                    </td >
                </tr>

            </table>

                <br><br>

                <table align="center" border=1>

                    <tr>
                        <td align="center" colspan="5" width="200"><h5>Contatori configurazioni</h5></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">
                            1 contesto
                        </td>
                        <td align="center" width="120">
                            2 contesti
                        </td>
                        <td align="center" width="120">
                            3 contesti
                        </td>
                        <td align="center" width="120">
                            4 contesti
                        </td>
                        <td align="center" width="120">
                            5 contesti
                        </td>
                    </tr>
                    <tr>
                        <td align="center" width="120">
                            <%out.println(contatoriContesti.get(1));%>
                        </td>
                        <td align="center" width="120">
                            <%out.println(contatoriContesti.get(2));%>
                        </td>
                        <td align="center" width="120">
                            <%out.println(contatoriContesti.get(3));%>
                        </td >
                        <td align="center" width="120">
                            <%out.println(contatoriContesti.get(4));%>
                        </td>
                        <td align="center" width="120">
                            <%out.println(contatoriContesti.get(5));%>
                        </td>
                    </tr>

                </table>

                <br><br>



                <table align="center" border=1>

                    <tr>
                        <td align="center" colspan="3" width="200"><h5>Contatori locali suggeriti</h5></td>
                    </tr>

                    <tr>
                        <td align="center" width="120">
                            Locale
                        </td>
                        <td align="center" width="120">
                            Numero di suggerimenti
                        </td>
                    </tr>

                    <%
                        for (int locale : l.keySet()){

                            out.println("<tr>" +
                                    "<td align=\"center\" width=\"120\">" +
                                    locale +
                                    "</td>"+
                                    "<td align=\"center\" width=\"120\">" +
                                    l.get(locale) +
                                    "</td>"+
                                    "</tr>");

                        }
                    %>


                    <br><br><br>

                    <table align="center" border=1>

                        <tr>
                            <td align="center" colspan="9" width="200"><h5>Preferenze metriche per contesto</h5></td>
                        </tr>

                        <tr>

                            <td align="center">
                                Metriche
                            </td>

                            <td colspan="2" align="center">
                                Trasparenza
                            </td>

                            <td colspan="2" align="center">
                                Coinvolgimento
                            </td>

                            <td colspan="2" align="center">
                                Persuasione
                            </td>

                            <td colspan="2" align="center">
                                Fiducia
                            </td>

                        </tr>

                        <tr>

                            <td align="center" width="100">
                                Contesti
                            </td>

                            <td align="center" width="100">
                                Centroide
                            </td>
                            <td align="center" width="100">
                                Singole
                            </td>

                            <td align="center" width="100">
                                Centroide
                            </td>
                            <td align="center" width="100">
                                Singole
                            </td>

                            <td align="center" width="100">
                                Centroide
                            </td>
                            <td align="center" width="100">
                                Singole
                            </td>

                            <td align="center" width="100">
                                Centroide
                            </td>
                            <td align="center" width="100">
                                Singole
                            </td>

                        </tr>

                        <%

                            try{

                                HashMap<Integer, String> contesti = new HashMap<Integer, String>();

                                contesti.put(1, "Colazione");
                                contesti.put(2, "Pranzo");
                                contesti.put(3, "Cena");
                                contesti.put(4, "Amici");
                                contesti.put(5, "Coppia");
                                contesti.put(6, "Famiglia");
                                contesti.put(7, "Feriale");
                                contesti.put(8, "Festivo");
                                contesti.put(9, "Buon umore");
                                contesti.put(10, "Cattivo umore");
                                contesti.put(11, "Cibo salutare");
                                contesti.put(12, "Cibo non salutare");
                                contesti.put(13, "Esigenze alimentari");

                                for (int cont=1; cont<=13; cont++){

                                    out.println("<tr><td align=\"center\">" +
                                            contesti.get(cont) +
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("centroide").get("trasparenza"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("singole").get("trasparenza"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("centroide").get("persuasione"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("singole").get("persuasione"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("centroide").get("coinvolgimento"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("singole").get("coinvolgimento"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("centroide").get("fiducia"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(o.get(cont).get("singole").get("fiducia"))+
                                            "</td> </tr>");
                                }


                            } catch (Exception ecc){
                                ecc.printStackTrace();
                            }

                        %>



                    </table>

                    <br><br>

                    <br><br><br>

                    <table align="center" border=1>

                        <tr>
                            <td align="center" colspan="9" width="200"><h5>Preferenze metriche per contesto</h5></td>
                        </tr>

                        <tr>

                            <td align="center">
                                Metriche
                            </td>

                            <td align="center">
                                Trasparenza
                            </td>

                            <td align="center">
                                Coinvolgimento
                            </td>

                            <td align="center">
                                Persuasione
                            </td>

                            <td align="center">
                                Fiducia
                            </td>

                        </tr>



                        <%

                            try{

                                HashMap<Integer, String> contesti = new HashMap<Integer, String>();

                                contesti.put(1, "Colazione");
                                contesti.put(2, "Pranzo");
                                contesti.put(3, "Cena");
                                contesti.put(4, "Amici");
                                contesti.put(5, "Coppia");
                                contesti.put(6, "Famiglia");
                                contesti.put(7, "Feriale");
                                contesti.put(8, "Festivo");
                                contesti.put(9, "Buon umore");
                                contesti.put(10, "Cattivo umore");
                                contesti.put(11, "Cibo salutare");
                                contesti.put(12, "Cibo non salutare");
                                contesti.put(13, "Esigenze alimentari");

                                for (int cont=1; cont<=13; cont++){

                                    out.println("<tr><td align=\"center\">" +
                                            contesti.get(cont) +
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(p.get(cont).get("trasparenza"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(p.get(cont).get("persuasione"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(p.get(cont).get("coinvolgimento"))+
                                            "</td> <td align=\"center\">" +
                                            r.valoreDecimale(p.get(cont).get("fiducia"))+
                                            "</td> </tr>");
                                }


                            } catch (Exception ecc){
                                ecc.printStackTrace();
                            }

                        %>



                    </table>

                    <br><br><br>


                </table>

                <%

                    out.println("Utenti: " + r.getUtenti().size());
                    out.println("<br>");

                    out.println("Valutazioni 1: " + r.getValutazioni1().size());
                    out.println("<br>");

                    out.println("Valutazioni 2: " + r.getValutazioni2().size());
                    out.println("<br>");

                    out.println("Valurazioni 3: " + r.getValutazioni3().size());
                    out.println("<br>");

                    out.println("Valutazioni 4: " + r.getValutazioni4().size());
                    out.println("<br>");

                    for (String s : contatori.keySet()) {
                        out.println(s + " -> " + contatori.get(s) + "<br>");
                    }

                %>
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
            <a href="https://www.uniba.it/"><img align="right" src="Logo_Uniba.png"></a>
            <a href="http://www.di.uniba.it/~swap/"><img height="60" align="center" src="swap.PNG"></a>
        </div>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="js/scrolling-nav.js"></script>

</body>

</html>
