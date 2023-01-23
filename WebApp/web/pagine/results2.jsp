<%@ page import="wherexp.VettoriContesto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.ObjectInputStream" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="wherexp.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

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
    String tempo = request.getParameter("tempo").trim();
%>


<header class="bg-primary text-white">
    <div class="container text-center">
        <h1>Where&Why</h1>
    </div>
</header>

<%

    Scanner in = new Scanner(new File("filesWherEXP/temp/spiegazioni"+tempo+".txt"), "UTF-8");

    in.nextLine();
    String spiegazioneSingole = in.nextLine();

    in.close();


%>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2></h2>
                <p class="lead">
                    <h3>Spiegazione</h3>
                    <% out.println(spiegazioneSingole); %>
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

                <form action="../salvaValutzione2">

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
