<%@ page import="wherexp.VettoriContesto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.ObjectInputStream" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="wherexp.Locale" %>
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


<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto" align="center">
                <%
                    Scanner in = new Scanner(new File("filesWherEXP/temp/spiegazioni"+tempo+".txt"), "UTF-8");

                    String spiegazione1 = in.nextLine();
                    String spiegazione2 = in.nextLine();

                    in.close();

                %>

                <h3>Spiegazione 1</h3>
                <% out.println(spiegazione1); %>
                <br><br>
                <h3>Spiegazione 2</h3>
                <% out.println(spiegazione2); %>
                <br><br>

                <form action="../salvaValutzione3">

                    <table width="400" align="center">

                        <tr>
                            <td align="center">
                                <h4><h4>Ricordando che l'obiettivo del sistema è di produrre una spiegazione adeguata ai diversi
                                    contesti di utilizzo, quale spiegazione preferisci?</h4></h4>
                                <div class="inputGroup">
                                    <input id="pref0_0" name="pref0" value="0" type="radio" checked />
                                    <label for="pref0_0">Indifferente</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref0_1" name="pref0" value="1" type="radio"  />
                                    <label for="pref0_1">Spiegazione 1</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref0_2" name="pref0" value="2" type="radio" />
                                    <label for="pref0_2">Spiegazione 2</label>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <br>
                                <h4><h4>Ricordando che l'obiettivo del sistema è di produrre una spiegazione adeguata ai diversi
                                    contesti di utilizzo, indica quale delle due spiegazioni ritieni sia più appropiata alle frasi proposte.</h4></h4>
                                <br>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <h5>Ho compreso perchè il locale mi è stato suggerito.</h5>
                                <div class="inputGroup">
                                    <input id="pref1_0" name="pref1" value="0" type="radio" checked />
                                    <label for="pref1_0">Indifferente</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref1_1" name="pref1" value="1" type="radio"  />
                                    <label for="pref1_1">Spiegazione 1</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref1_2" name="pref1" value="2" type="radio" />
                                    <label for="pref1_2">Spiegazione 2</label>
                                </div>
                                <br><br>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <h5>La spiegazione ha reso il suggerimento più convincente.</h5>
                                <div class="inputGroup">
                                    <input id="pref2_0" name="pref2" value="0" type="radio" checked />
                                    <label for="pref2_0">Indifferente</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref2_1" name="pref2" value="1" type="radio"  />
                                    <label for="pref2_1">Spiegazione 1</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref2_2" name="pref2" value="2" type="radio" />
                                    <label for="pref2_2">Spiegazione 2</label>
                                </div>
                                <br><br>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <h5>La spiegazione mi ha permesso di scoprire nuove informazioni circa il locale suggerito. </h5>
                                <div class="inputGroup">
                                    <input id="pref3_0" name="pref3" value="0" type="radio" checked />
                                    <label for="pref3_0">Indifferente</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref3_1" name="pref3" value="1" type="radio"  />
                                    <label for="pref3_1">Spiegazione 1</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref3_2" name="pref3" value="2" type="radio" />
                                    <label for="pref3_2">Spiegazione 2</label>
                                </div>
                                <br><br>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <h5>La spiegazione ha incrementato il mio livello di fiducia nei recommender system.</h5>
                                <div class="inputGroup">
                                    <input id="pref4_0" name="pref4" value="0" type="radio" checked />
                                    <label for="pref4_0">Indifferente</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref4_1" name="pref4" value="1" type="radio"  />
                                    <label for="pref4_1">Spiegazione 1</label>
                                </div>
                                <div class="inputGroup">
                                    <input id="pref4_2" name="pref4" value="2" type="radio" />
                                    <label for="pref4_2">Spiegazione 2</label>
                                </div>
                                <br><br>
                            </td>

                        </tr>

                        <tr>
                            <td align="center">
                                <input type="submit" id="valutazione3" value="Invia valutazione"
                                       class="myButton" style="background: #5562eb">
                            </td>
                        </tr>

                    </table>

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
