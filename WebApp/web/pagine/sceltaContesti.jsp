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
        <p class="lead">Tu dicci come vuoi passare la giornata, noi pensiamo al resto.</p>
    </div>
</header>


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2 align="center">Che tipo di giornata vuoi trascorrere?</h2>
                <h5 align="center">IMPORTANTE: non sei obbligato a selezionare tutti i contesti di utilizzo</h5>
                <p class="lead"></p>

                <form action="../gestioneRichiesta" method="get">

                    <%
                        String tempo = request.getParameter("tempo").trim();
                        System.out.println("tempo: " + tempo);
                    %>

                    <input type="hidden" id="tempo" name="tempo" value="<%out.println(tempo);%>">


                    <table cellpadding="15" width="500" align="center">

                        <tr>

                            <td>

                                <h3 style="color: #008cff;">Città</h3>
                                <div class="inputGroup">
                                    <input id="bari" name="citta" value="bari" type="radio" checked />
                                    <label for="bari">Bari</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="torino" name="citta" value="torino" type="radio" />
                                    <label for="torino">Torino</label>
                                </div>

                                <h3 style="color: #008cff;">Pasto</h3>
                                <p id="pPasto" hidden >0</p>
                                <div class="inputGroup">
                                    <input id="1" name="pasto" value="1" type="radio" onclick="check(this.id)" />
                                    <label for="1">Colazione</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="2" name="pasto" value="2" type="radio" onclick="check(this.id)" />
                                    <label for="2">Pranzo</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="3" name="pasto" value="3" type="radio" onclick="check(this.id)" />
                                    <label for="3">Cena</label>
                                </div>


                                <h3 style="color: #008cff;">Compagnia</h3>
                                <p id="pCompagnia" hidden >0</p>
                                <div class="inputGroup">
                                    <input id="4" name="compagnia" value="4" type="radio" onclick="check(this.id)" />
                                    <label for="4">Amici</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="5" name="compagnia" value="5" type="radio" onclick="check(this.id)" />
                                    <label for="5">Coppia</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="6" name="compagnia" value="6" type="radio" onclick="check(this.id)" />
                                    <label for="6">Famiglia</label>
                                </div>


                                <h3 style="color: #008cff;">Giorno</h3>
                                <p id="pGiorno" hidden >0</p>
                                <div class="inputGroup">
                                    <input id="7" name="giorno" value="7" type="radio" onclick="check(this.id)" />
                                    <label for="7">Feriale</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="8" name="giorno" value="8" type="radio" onclick="check(this.id)" />
                                    <label for="8">Festivo</label>
                                </div>


                                <h3 style="color: #008cff;">Umore</h3>
                                <p id="pUmore" hidden >0</p>
                                <div class="inputGroup">
                                    <input id="9" name="umore" value="9" type="radio" onclick="check(this.id)" />
                                    <label for="9">Buono</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="10" name="umore" value="10" type="radio" onclick="check(this.id)" />
                                    <label for="10">Cattivo</label>
                                </div>


                                <h3 style="color: #008cff;">Cibo</h3>
                                <p id="pCibo" hidden >0</p>
                                <div class="inputGroup">
                                    <input id="11" name="cibo" value="11" type="radio" onclick="check(this.id)" />
                                    <label for="11">Salutare</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="12" name="cibo" value="12" type="radio" onclick="check(this.id)" />
                                    <label for="12">Non salutare</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="13" name="cibo" value="13" type="radio" onclick="check(this.id)" />
                                    <label for="13">Esigenze alimentari</label>
                                </div>
                            </td>
                            <td>
                                <br>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                            </td>
                            <td>
                                <br>
                            </td>
                            <td align="center">
                            </td>
                        </tr>
                        <tr >
                            <td align="center" colspan="3">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div  align="center">
                                    <input type="submit" id="cerca" value="Cerca locale"
                                           class="myButton" disabled style="background: grey">
                                </div>
                            </td>
                        </tr>

                    </table>

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
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom JavaScript for this theme -->
<script src="js/scrolling-nav.js"></script>

</body>

</html>
