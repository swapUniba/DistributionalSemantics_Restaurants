<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script type="text/javascript" src="../js/script.js"></script>


</head>

<body id="page-top" onload="controlloGenerale()">

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
        <h1>Where&Why</h1>
    </div>
</header>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2 align="center">Innanzitutto, abbiamo bisogno di alcune informazioni su di te.</h2>
                <p class="lead"></p>

                <form action="../infoUtente" method="get">

                    <table cellpadding="15" width="500" align="center">

                        <tr>

                            <td>

                                <h3 style="color: #008cff;">Età</h3>
                                <div class="inputGroup">
                                    <input id="1" name="eta" value="1" type="radio" checked />
                                    <label for="1"><18</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="2" name="eta" value="2" type="radio" />
                                    <label for="2">18 - 25</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="3" name="eta" value="3" type="radio" />
                                    <label for="3">26 - 35</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="4" name="eta" value="4" type="radio" />
                                    <label for="4">36 - 50</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="5" name="eta" value="5" type="radio" />
                                    <label for="5">>50</label>
                                </div>

                                <h3 style="color: #008cff;">Genere</h3>
                                <div class="inputGroup">
                                    <input id="uomo" name="genere" value="uomo" type="radio" checked />
                                    <label for="uomo">Uomo</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="donna" name="genere" value="donna" type="radio" />
                                    <label for="donna">Donna</label>
                                </div>

                                <h3 style="color: #008cff;">Titolo di studio</h3>
                                <div class="inputGroup">
                                    <input id="6" name="titoloStudio" value="6" type="radio" checked />
                                    <label for="6">Diploma di Scuola superiore</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="7" name="titoloStudio" value="7" type="radio" />
                                    <label for="7">Laurea Triennale</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="8" name="titoloStudio" value="8" type="radio" />
                                    <label for="8">Laurea Magistrale</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="9" name="titoloStudio" value="9" type="radio" />
                                    <label for="9">Dottorato di Ricerca</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="10" name="titoloStudio" value="10" type="radio" />
                                    <label for="10">Altro</label>
                                </div>

                                <h3 style="color: #008cff;">Quando volte mangi fuori casa in settimana?</h3>
                                <div class="inputGroup">
                                    <input id="11" name="frequenza" value="11" type="radio" checked />
                                    <label for="11">Nessuna o 1 volta a settimana</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="12" name="frequenza" value="12" type="radio" />
                                    <label for="12">Da 2 a 4 volte a settimana</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="13" name="frequenza" value="13" type="radio" />
                                    <label for="13">Da 5 a 7 volte a settimana</label>
                                </div>

                                <h3 style="color: #008cff;">Hai mai usato recommender system (come Amazon, YouTube, Netflix)?</h3>
                                <div class="inputGroup">
                                    <input id="14" name="recSys" value="si" type="radio" checked />
                                    <label for="14">Sì</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="15" name="recSys" value="no" type="radio" />
                                    <label for="15">No</label>
                                </div>

                                <div  align="center">
                                    <input type="submit" id="infoUtente" value="Prosegui"
                                           class="myButton" style="background: #5562eb">
                                </div>

                            </td>
                        </tr>

                    </table>





                </form>


            </div>
        </div>
    </div>
</section>


<!-- Footer -->
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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="js/scrolling-nav.js"></script>

</body>

</html>
