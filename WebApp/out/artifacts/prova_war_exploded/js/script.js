function disabilitaCerca() {
    document.getElementById("cerca").disabled = true;
    document.getElementById("cerca").style.background = "grey";
}

function abilitaCerca() {

    document.getElementById("cerca").disabled = false;
    document.getElementById("cerca").style.background = "#5561eb";
}

function controlloGenerale() {

    var i = 0;
    var controllo = false;

    for (i=1; i<=13; i++){

        if (document.getElementById(i+"").checked){
            controllo=true;
            break;
        }
    }

    if (controllo){
        abilitaCerca();
    } else {
        disabilitaCerca();
    }


}

function check(id) {

    if (id == "1"){

        if (document.getElementById("pPasto").innerHTML == "0"){

            document.getElementById("pPasto").innerHTML = "1";

        } else if (document.getElementById("pPasto").innerHTML == "1"){

            document.getElementById("1").checked = false;
            document.getElementById("pPasto").innerHTML = "0";

        } else {

            document.getElementById("pPasto").innerText = "1";

        }


    } else if (id == "2"){

        if (document.getElementById("pPasto").innerHTML == "0"){

            document.getElementById("pPasto").innerHTML = "2";

        } else if (document.getElementById("pPasto").innerHTML == "2"){

            document.getElementById("2").checked = false;
            document.getElementById("pPasto").innerHTML = "0";

        } else {

            document.getElementById("pPasto").innerText = "2";

        }

    } else if (id == "3"){

        if (document.getElementById("pPasto").innerHTML == "0"){

            document.getElementById("pPasto").innerHTML = "3";

        } else if (document.getElementById("pPasto").innerHTML == "3"){

            document.getElementById("3").checked = false;
            document.getElementById("pPasto").innerHTML = "0";

        } else {

            document.getElementById("pPasto").innerText = "3";

        }

    } else if (id == "4"){

        if (document.getElementById("pCompagnia").innerHTML == "0"){

            document.getElementById("pCompagnia").innerHTML = "4";

        } else if (document.getElementById("pCompagnia").innerHTML == "4"){

            document.getElementById("4").checked = false;
            document.getElementById("pCompagnia").innerHTML = "0";

        } else {

            document.getElementById("pCompagnia").innerText = "4";

        }


    } else if (id == "5"){

        if (document.getElementById("pCompagnia").innerHTML == "0"){

            document.getElementById("pCompagnia").innerHTML = "5";

        } else if (document.getElementById("pCompagnia").innerHTML == "5"){

            document.getElementById("5").checked = false;
            document.getElementById("pCompagnia").innerHTML = "0";

        } else {

            document.getElementById("pCompagnia").innerText = "5";

        }


    } else if (id == "6"){

        if (document.getElementById("pCompagnia").innerHTML == "0"){

            document.getElementById("pCompagnia").innerHTML = "6";

        } else if (document.getElementById("pCompagnia").innerHTML == "6"){

            document.getElementById("6").checked = false;
            document.getElementById("pCompagnia").innerHTML = "0";

        } else {

            document.getElementById("pCompagnia").innerText = "6";

        }


    } else if (id == "7"){

        if (document.getElementById("pGiorno").innerHTML == "0"){

            document.getElementById("pGiorno").innerHTML = "7";

        } else if (document.getElementById("pGiorno").innerHTML == "7"){

            document.getElementById("7").checked = false;
            document.getElementById("pGiorno").innerHTML = "0";

        } else {

            document.getElementById("pGiorno").innerText = "7";

        }


    } else if (id == "8"){

        if (document.getElementById("pGiorno").innerHTML == "0"){

            document.getElementById("pGiorno").innerHTML = "8";

        } else if (document.getElementById("pGiorno").innerHTML == "8"){

            document.getElementById("8").checked = false;
            document.getElementById("pGiorno").innerHTML = "0";

        } else {

            document.getElementById("pGiorno").innerText = "8";

        }


    } else if (id == "9"){

        if (document.getElementById("pUmore").innerHTML == "0"){

            document.getElementById("pUmore").innerHTML = "9";

        } else if (document.getElementById("pUmore").innerHTML == "9"){

            document.getElementById("9").checked = false;
            document.getElementById("pUmore").innerHTML = "0";

        } else {

            document.getElementById("pUmore").innerText = "9";

        }


    } else if (id == "10"){

        if (document.getElementById("pUmore").innerHTML == "0"){

            document.getElementById("pUmore").innerHTML = "10";

        } else if (document.getElementById("pUmore").innerHTML == "10"){

            document.getElementById("10").checked = false;
            document.getElementById("pUmore").innerHTML = "0";

        } else {

            document.getElementById("pUmore").innerText = "10";

        }


    } else if (id == "11"){

        if (document.getElementById("pCibo").innerHTML == "0"){

            document.getElementById("pCibo").innerHTML = "11";

        } else if (document.getElementById("pCibo").innerHTML == "11"){

            document.getElementById("11").checked = false;
            document.getElementById("pCibo").innerHTML = "0";

        } else {

            document.getElementById("pCibo").innerText = "11";

        }


    } else if (id == "12"){

        if (document.getElementById("pCibo").innerHTML == "0"){

            document.getElementById("pCibo").innerHTML = "12";

        } else if (document.getElementById("pCibo").innerHTML == "12"){

            document.getElementById("12").checked = false;
            document.getElementById("pCibo").innerHTML = "0";

        } else {

            document.getElementById("pCibo").innerText = "12";

        }


    } else if (id == "13"){

        if (document.getElementById("pCibo").innerHTML == "0"){

            document.getElementById("pCibo").innerHTML = "13";

        } else if (document.getElementById("pCibo").innerHTML == "13"){

            document.getElementById("13").checked = false;
            document.getElementById("pCibo").innerHTML = "0";

        } else {

            document.getElementById("pCibo").innerText = "13";

        }


    }

    controlloGenerale();

}


