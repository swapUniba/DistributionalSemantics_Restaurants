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



<header class="bg-primary text-white">
    <div class="container text-center">
        <h1>WherEXP</h1>
    </div>
</header>

<%

    try {

        File folder = new File("filesWherEXP/info utili/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {

                Scanner infoLocale = new Scanner(file);
                Locale l = new Locale();

                l.setId(Integer.parseInt(infoLocale.nextLine()));
                l.setUrl(infoLocale.nextLine());
                l.setNome(infoLocale.nextLine().replaceAll("'", ""));
                l.setIndirizzo(infoLocale.nextLine().replaceAll("'", ""));
                l.setTelefono(infoLocale.nextLine().replaceAll("'", ""));
                l.setCategoria(infoLocale.nextLine().replaceAll("'", ""));

                out.println("<h4>" + l.getId() + " - " + l.getNome() + "</h4><br>" + l.getIndirizzo() +
                        "<br>" + l.getTelefono() + "<br>Categoria: " + l.getCategoria() +
                        "<br>");

            }
        }

    } catch (Exception e){
        out.println(e.getMessage());
    }



%>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2></h2>
                <p class="lead">
                <h3>Spiegazione</h3>
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
                    > 1 indica che sei in totale disaccordo<br>
                    > 5 indica che sei in totale accordo</p>



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
