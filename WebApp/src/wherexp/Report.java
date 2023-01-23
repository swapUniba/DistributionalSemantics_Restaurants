package wherexp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Report {

    private HashSet<Utente> utenti;
    private HashSet<ValutazioneTipo1_2> valutazioni1, valutazioni2;
    private HashSet<ValutazioneTipo3> valutazioni3;
    private HashSet<ValutazioneTipo4> valutazioni4;


    // singole vs centroide - solo metrica di preferenza - senza distinzioni di uni/bi/unibi
    public HashMap<String, Double> centroideVSSingoleGenerale(){

        int centroide = 0, singole = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();

        for (ValutazioneTipo3 v3 : valutazioni3){

            totale++;

            if (v3.getPreferenza0() == 0){
                indifferente++;
            } else if (v3.getPreferenza0() == 1){
                centroide++;
            } else if (v3.getPreferenza0() == 2){
                singole++;
            }

        }

        if (totale != 0){
            esiti.put("centroide", (double)centroide/totale);
            esiti.put("singole", (double)singole/totale);
            esiti.put("indifferente", (double)indifferente/totale);
        } else {
            esiti.put("centroide", 0.0);
            esiti.put("singole", 0.0);
            esiti.put("indifferente", 0.0);
        }

        return esiti;

    }

    // sistema vs baseline - solo metrica di preferenza - senza distinzioni di uni/bi/unibi
    public HashMap<String, Double> sistemaVSBaseline(){

        int centroide = 0, singole = 0, sistema = 0, baseline = 0, indifferente = 0, totale = 0;
        HashMap<String, Double> esiti = new HashMap<>();

        for (ValutazioneTipo4 v4 : valutazioni4){

            totale++;

            if (v4.getPreferenzaPrecedente() == 1){

                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } else if (v4.getPreferenza0() == 1){
                    centroide++;
                    sistema++;
                } else {
                    baseline++;
                }

            } else {

                if (v4.getPreferenza0() == 0){
                    indifferente++;
                } else if (v4.getPreferenza0() == 1){
                    singole++;
                    sistema++;
                } else {
                    baseline++;
                }

            }

        }

        if (totale != 0){
            esiti.put("centroide", (double)centroide/totale);
            esiti.put("singole", (double)singole/totale);
            esiti.put("sistema", (double)sistema/totale);
            esiti.put("baseline", (double)baseline/totale);
        } else {
            esiti.put("centroide", 0.0);
            esiti.put("singole", 0.0);
            esiti.put("sistema", 0.0);
            esiti.put("baseline", 0.0);
        }

        return esiti;
    }

    // singole vs centroide - solo metrica di preferenza - con distinzioni di uni/bi/unibi
    public HashMap<String, HashMap<String, Double>> centroideVSSingolePreferenze(){

        HashMap<String, HashMap<String, Double>> esiti = new HashMap<>();

        int  singoleUni = 0, singoleBi = 0, singoleUniBi = 0,
                centroideUni = 0, centroideBi = 0, centroideUniBi = 0,
                indiffUni = 0, indiffBi = 0, indiffUniBi = 0,
                totaleUni = 0, totaleBi = 0, totaleUniBi = 0;

        for (ValutazioneTipo3 v3 : valutazioni3){

            if (v3.getConfigurazione().equals("unigrammi")){

                totaleUni++;

                if (v3.getPreferenza0() == 1){
                    centroideUni++;
                } else if (v3.getPreferenza0() == 2){
                    singoleUni++;
                } else {
                    indiffUni++;
                }

            } else if (v3.getConfigurazione().equals("bigrammi")){

                totaleBi++;

                if (v3.getPreferenza0() == 1){
                    centroideBi++;
                } else if (v3.getPreferenza0() == 2){
                    singoleBi++;
                } else {
                    indiffBi++;
                }

            } else if (v3.getConfigurazione().equals("unibigrammi")){

                totaleUniBi++;

                if (v3.getPreferenza0() == 1){
                    centroideUniBi++;
                } else if (v3.getPreferenza0() == 2){
                    singoleUniBi++;
                } else {
                    indiffUniBi++;
                }
            }

        }

        HashMap<String, Double> uni = new HashMap<>();
        HashMap<String, Double> bi = new HashMap<>();
        HashMap<String, Double> unibi = new HashMap<>();

        if (totaleUni != 0){

            uni.put("centroide", (double)centroideUni/totaleUni);
            uni.put("singole", (double)singoleUni/totaleUni);
            uni.put("indifferenti", (double)indiffUni/totaleUni);

        } else {

            uni.put("centroide", 0.0);
            uni.put("singole", 0.0);
            uni.put("indifferenti", 0.0);

        }

        if (totaleBi != 0){

            bi.put("centroide", (double)centroideBi/totaleBi);
            bi.put("singole", (double)singoleBi/totaleBi);
            bi.put("indifferenti", (double)indiffBi/totaleBi);

        } else {

            bi.put("centroide", 0.0);
            bi.put("singole", 0.0);
            bi.put("indifferenti", 0.0);

        }

        if (totaleUniBi != 0){

            unibi.put("centroide", (double)centroideUniBi/totaleUniBi);
            unibi.put("singole", (double)singoleUniBi/totaleUniBi);
            unibi.put("indifferenti", (double)indiffUniBi/totaleUniBi);

        } else {

            unibi.put("centroide", 0.0);
            unibi.put("singole", 0.0);
            unibi.put("indifferenti", 0.0);

        }

        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;

    }

    // valutazione sul centroide assolute
    public HashMap<String, HashMap<String, Double>> valutazioniAssoluteCentroide(){

        HashMap<String, HashMap<String, Double>> esiti = new HashMap<>();

        HashMap<String, Double> uni = new HashMap<>();
        HashMap<String, Double> bi = new HashMap<>();
        HashMap<String, Double> unibi = new HashMap<>();

        int valore1uni = 0, valore2uni = 0, valore3uni = 0, valore4uni = 0, totaleuni = 0,
                valore1bi = 0, valore2bi = 0, valore3bi = 0, valore4bi = 0, totalebi = 0,
                valore1unibi = 0, valore2unibi = 0, valore3unibi = 0, valore4unibi = 0, totaleunibi = 0;

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            if (v1.getConfigurazione().equals("unigrammi")){

                valore1uni += v1.getTrasparenza();
                valore2uni += v1.getPersuasione();
                valore3uni += v1.getCoinvolgimento();
                valore4uni += v1.getFiducia();

                totaleuni++;

            } else if (v1.getConfigurazione().equals("bigrammi")){

                valore1bi += v1.getTrasparenza();
                valore2bi += v1.getPersuasione();
                valore3bi += v1.getCoinvolgimento();
                valore4bi += v1.getFiducia();

                totalebi++;

            } else if (v1.getConfigurazione().equals("unibigrammi")){

                valore1unibi += v1.getTrasparenza();
                valore2unibi += v1.getPersuasione();
                valore3unibi += v1.getCoinvolgimento();
                valore4unibi += v1.getFiducia();

                totaleunibi++;

            }

        }

        if (totaleuni != 0){

            uni.put("Trasparenza", (double)valore1uni/totaleuni);
            uni.put("Persuasione", (double)valore2uni/totaleuni);
            uni.put("Coinvolgimento", (double)valore3uni/totaleuni);
            uni.put("Fiducia", (double)valore4uni/totaleuni);

        } else {

            uni.put("Trasparenza", 0.0);
            uni.put("Persuasione", 0.0);
            uni.put("Coinvolgimento", 0.0);
            uni.put("Fiducia", 0.0);

        }

        if (totalebi != 0){

            bi.put("Trasparenza", (double)valore1bi/totalebi);
            bi.put("Persuasione", (double)valore2bi/totalebi);
            bi.put("Coinvolgimento", (double)valore3bi/totalebi);
            bi.put("Fiducia", (double)valore4bi/totalebi);

        } else {

            bi.put("Trasparenza", 0.0);
            bi.put("Persuasione", 0.0);
            bi.put("Coinvolgimento", 0.0);
            bi.put("Fiducia", 0.0);

        }

        if (totaleunibi != 0){

            unibi.put("Trasparenza", (double)valore1unibi/totaleunibi);
            unibi.put("Persuasione", (double)valore2unibi/totaleunibi);
            unibi.put("Coinvolgimento", (double)valore3unibi/totaleunibi);
            unibi.put("Fiducia", (double)valore4unibi/totaleunibi);

        } else {

            unibi.put("Trasparenza", 0.0);
            unibi.put("Persuasione", 0.0);
            unibi.put("Coinvolgimento", 0.0);
            unibi.put("Fiducia", 0.0);

        }


        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;

    }

    // valutazioni sulle frasi singole assolute
    public HashMap<String, HashMap<String, Double>> valutazioniAssoluteSingole(){

        HashMap<String, HashMap<String, Double>> esiti = new HashMap<>();

        HashMap<String, Double> uni = new HashMap<>();
        HashMap<String, Double> bi = new HashMap<>();
        HashMap<String, Double> unibi = new HashMap<>();

        int valore1uni = 0, valore2uni = 0, valore3uni = 0, valore4uni = 0, totaleuni = 0,
                valore1bi = 0, valore2bi = 0, valore3bi = 0, valore4bi = 0, totalebi = 0,
                valore1unibi = 0, valore2unibi = 0, valore3unibi = 0, valore4unibi = 0, totaleunibi = 0;

        for (ValutazioneTipo1_2 v2 : valutazioni2){

            if (v2.getConfigurazione().equals("unigrammi")){

                valore1uni += v2.getTrasparenza();
                valore2uni += v2.getPersuasione();
                valore3uni += v2.getCoinvolgimento();
                valore4uni += v2.getFiducia();

                totaleuni++;

            } else if (v2.getConfigurazione().equals("bigrammi")){

                valore1bi += v2.getTrasparenza();
                valore2bi += v2.getPersuasione();
                valore3bi += v2.getCoinvolgimento();
                valore4bi += v2.getFiducia();

                totalebi++;

            } else if (v2.getConfigurazione().equals("unibigrammi")){

                valore1unibi += v2.getTrasparenza();
                valore2unibi += v2.getPersuasione();
                valore3unibi += v2.getCoinvolgimento();
                valore4unibi += v2.getFiducia();

                totaleunibi++;

            }

        }

        if (totaleuni != 0){

            uni.put("Trasparenza", (double)valore1uni/totaleuni);
            uni.put("Persuasione", (double)valore2uni/totaleuni);
            uni.put("Coinvolgimento", (double)valore3uni/totaleuni);
            uni.put("Fiducia", (double)valore4uni/totaleuni);

        } else {

            uni.put("Trasparenza", 0.0);
            uni.put("Persuasione", 0.0);
            uni.put("Coinvolgimento", 0.0);
            uni.put("Fiducia", 0.0);

        }

        if (totalebi != 0){

            bi.put("Trasparenza", (double)valore1bi/totalebi);
            bi.put("Persuasione", (double)valore2bi/totalebi);
            bi.put("Coinvolgimento", (double)valore3bi/totalebi);
            bi.put("Fiducia", (double)valore4bi/totalebi);

        } else {

            bi.put("Trasparenza", 0.0);
            bi.put("Persuasione", 0.0);
            bi.put("Coinvolgimento", 0.0);
            bi.put("Fiducia", 0.0);

        }

        if (totaleunibi != 0){

            unibi.put("Trasparenza", (double)valore1unibi/totaleunibi);
            unibi.put("Persuasione", (double)valore2unibi/totaleunibi);
            unibi.put("Coinvolgimento", (double)valore3unibi/totaleunibi);
            unibi.put("Fiducia", (double)valore4unibi/totaleunibi);

        } else {

            unibi.put("Trasparenza", 0.0);
            unibi.put("Persuasione", 0.0);
            unibi.put("Coinvolgimento", 0.0);
            unibi.put("Fiducia", 0.0);

        }


        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;

    }

    // singole vs centroide - metriche - distinzione di uni/bi/unibi
    public HashMap<String, HashMap<String, HashMap<String, Double>>> centroideVSSingoleMetriche(){

        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> centroideUni = new HashMap<>();
        HashMap<String, Double> centroideBi = new HashMap<>();
        HashMap<String, Double> centroideUniBi = new HashMap<>();

        HashMap<String, Double> singoleUni = new HashMap<>();
        HashMap<String, Double> singoleBi = new HashMap<>();
        HashMap<String, Double> singoleUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();

        singoleUni.put("Trasparenza", 0.0);
        singoleUni.put("Persuasione", 0.0);
        singoleUni.put("Coinvolgimento", 0.0);
        singoleUni.put("Fiducia", 0.0);
        singoleBi.put("Trasparenza", 0.0);
        singoleBi.put("Persuasione", 0.0);
        singoleBi.put("Coinvolgimento", 0.0);
        singoleBi.put("Fiducia", 0.0);
        singoleUniBi.put("Trasparenza", 0.0);
        singoleUniBi.put("Persuasione", 0.0);
        singoleUniBi.put("Coinvolgimento", 0.0);
        singoleUniBi.put("Fiducia", 0.0);

        centroideUni.put("Trasparenza", 0.0);
        centroideUni.put("Persuasione", 0.0);
        centroideUni.put("Coinvolgimento", 0.0);
        centroideUni.put("Fiducia", 0.0);
        centroideBi.put("Trasparenza", 0.0);
        centroideBi.put("Persuasione", 0.0);
        centroideBi.put("Coinvolgimento", 0.0);
        centroideBi.put("Fiducia", 0.0);
        centroideUniBi.put("Trasparenza", 0.0);
        centroideUniBi.put("Persuasione", 0.0);
        centroideUniBi.put("Coinvolgimento", 0.0);
        centroideUniBi.put("Fiducia", 0.0);

        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;


        for (ValutazioneTipo3 v3 : valutazioni3){

            if (v3.getConfigurazione().equals("unigrammi")){

                totaliUni++;

                if (v3.getTrasparenza() == 1){

                    centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")+1);

                } else if (v3.getTrasparenza() == 2){

                    singoleUni.put("Trasparenza", singoleUni.get("Trasparenza")+1);

                } else {

                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){

                    centroideUni.put("Persuasione", centroideUni.get("Persuasione")+1);

                } else if (v3.getPersuasione() == 2){

                    singoleUni.put("Persuasione", singoleUni.get("Persuasione")+1);

                } else {

                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);

                }

                if (v3.getCoinvolgimento() == 1){

                    centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")+1);

                } else if (v3.getCoinvolgimento() == 2){

                    singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")+1);

                } else {

                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);

                }

                if (v3.getFiducia() == 1){

                    centroideUni.put("Fiducia", centroideUni.get("Fiducia")+1);

                } else if (v3.getFiducia() == 2){

                    singoleUni.put("Fiducia", singoleUni.get("Fiducia")+1);

                } else {

                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);

                }


            } else if (v3.getConfigurazione().equals("bigrammi")){

                totaliBi++;

                if (v3.getTrasparenza() == 1){

                    centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")+1);

                } else if (v3.getTrasparenza() == 2){

                    singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")+1);

                } else {

                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){

                    centroideBi.put("Persuasione", centroideBi.get("Persuasione")+1);

                } else if (v3.getPersuasione() == 2){

                    singoleBi.put("Persuasione", singoleBi.get("Persuasione")+1);

                } else {

                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);

                }

                if (v3.getCoinvolgimento() == 1){

                    centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")+1);

                } else if (v3.getCoinvolgimento() == 2){

                    singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")+1);

                } else {

                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);

                }

                if (v3.getFiducia() == 1){

                    centroideBi.put("Fiducia", centroideBi.get("Fiducia")+1);

                } else if (v3.getFiducia() == 2){

                    singoleBi.put("Fiducia", singoleBi.get("Fiducia")+1);

                } else {

                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);

                }

            } else if (v3.getConfigurazione().equals("unibigrammi")){

                totaliUniBi++;

                if (v3.getTrasparenza() == 1){

                    centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")+1);

                } else if (v3.getTrasparenza() == 2){

                    singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")+1);

                } else {

                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v3.getPersuasione() == 1){

                    centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")+1);

                } else if (v3.getPersuasione() == 2){

                    singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")+1);

                } else {

                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);

                }

                if (v3.getCoinvolgimento() == 1){

                    centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")+1);

                } else if (v3.getCoinvolgimento() == 2){

                    singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")+1);

                } else {

                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);

                }

                if (v3.getFiducia() == 1){

                    centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")+1);

                } else if (v3.getFiducia() == 2){

                    singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")+1);

                } else {

                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);

                }
            }
        }

        if (totaliUni != 0){
            centroideUni.put("Trasparenza", centroideUni.get("Trasparenza")/totaliUni);
            centroideUni.put("Persuasione", centroideUni.get("Persuasione")/totaliUni);
            centroideUni.put("Coinvolgimento", centroideUni.get("Coinvolgimento")/totaliUni);
            centroideUni.put("Fiducia", centroideUni.get("Fiducia")/totaliUni);
            singoleUni.put("Trasparenza", singoleUni.get("Trasparenza")/totaliUni);
            singoleUni.put("Persuasione", singoleUni.get("Persuasione")/totaliUni);
            singoleUni.put("Coinvolgimento", singoleUni.get("Coinvolgimento")/totaliUni);
            singoleUni.put("Fiducia", singoleUni.get("Fiducia")/totaliUni);
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }

        if (totaliBi != 0){
            centroideBi.put("Trasparenza", centroideBi.get("Trasparenza")/totaliBi);
            centroideBi.put("Persuasione", centroideBi.get("Persuasione")/totaliBi);
            centroideBi.put("Coinvolgimento", centroideBi.get("Coinvolgimento")/totaliBi);
            centroideBi.put("Fiducia", centroideBi.get("Fiducia")/totaliBi);
            singoleBi.put("Trasparenza", singoleBi.get("Trasparenza")/totaliBi);
            singoleBi.put("Persuasione", singoleBi.get("Persuasione")/totaliBi);
            singoleBi.put("Coinvolgimento", singoleBi.get("Coinvolgimento")/totaliBi);
            singoleBi.put("Fiducia", singoleBi.get("Fiducia")/totaliBi);
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }

        if (totaliUniBi != 0){
            centroideUniBi.put("Trasparenza", centroideUniBi.get("Trasparenza")/totaliUniBi);
            centroideUniBi.put("Persuasione", centroideUniBi.get("Persuasione")/totaliUniBi);
            centroideUniBi.put("Coinvolgimento", centroideUniBi.get("Coinvolgimento")/totaliUniBi);
            centroideUniBi.put("Fiducia", centroideUniBi.get("Fiducia")/totaliUniBi);
            singoleUniBi.put("Trasparenza", singoleUniBi.get("Trasparenza")/totaliUniBi);
            singoleUniBi.put("Persuasione", singoleUniBi.get("Persuasione")/totaliUniBi);
            singoleUniBi.put("Coinvolgimento", singoleUniBi.get("Coinvolgimento")/totaliUniBi);
            singoleUniBi.put("Fiducia", singoleUniBi.get("Fiducia")/totaliUniBi);
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }


        uni.put("centroide", centroideUni);
        uni.put("singole", singoleUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("centroide", centroideBi);
        bi.put("singole", singoleBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("centroide", centroideUniBi);
        unibi.put("singole", singoleUniBi);
        unibi.put("indifferenti", indifferentiUniBi);

        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;
    }

    // sistema vs baseline - metriche - distinzione di uni/bi/unibi
    public HashMap<String, HashMap<String, HashMap<String, Double>>> sistemaVSBaselineMetriche(){

        HashMap<String, HashMap<String, HashMap<String, Double>>> esiti = new HashMap<>();

        HashMap<String, HashMap<String, Double>> uni = new HashMap<>();
        HashMap<String, HashMap<String, Double>> bi = new HashMap<>();
        HashMap<String, HashMap<String, Double>> unibi = new HashMap<>();

        HashMap<String, Double> sistemaUni = new HashMap<>();
        HashMap<String, Double> sistemaBi = new HashMap<>();
        HashMap<String, Double> sistemaUniBi = new HashMap<>();

        HashMap<String, Double> baselineUni = new HashMap<>();
        HashMap<String, Double> baselineBi = new HashMap<>();
        HashMap<String, Double> baselineUniBi = new HashMap<>();

        HashMap<String, Double> indifferentiUni = new HashMap<>();
        HashMap<String, Double> indifferentiBi = new HashMap<>();
        HashMap<String, Double> indifferentiUniBi = new HashMap<>();

        baselineUni.put("Trasparenza", 0.0);
        baselineUni.put("Persuasione", 0.0);
        baselineUni.put("Coinvolgimento", 0.0);
        baselineUni.put("Fiducia", 0.0);
        baselineBi.put("Trasparenza", 0.0);
        baselineBi.put("Persuasione", 0.0);
        baselineBi.put("Coinvolgimento", 0.0);
        baselineBi.put("Fiducia", 0.0);
        baselineUniBi.put("Trasparenza", 0.0);
        baselineUniBi.put("Persuasione", 0.0);
        baselineUniBi.put("Coinvolgimento", 0.0);
        baselineUniBi.put("Fiducia", 0.0);

        sistemaUni.put("Trasparenza", 0.0);
        sistemaUni.put("Persuasione", 0.0);
        sistemaUni.put("Coinvolgimento", 0.0);
        sistemaUni.put("Fiducia", 0.0);
        sistemaBi.put("Trasparenza", 0.0);
        sistemaBi.put("Persuasione", 0.0);
        sistemaBi.put("Coinvolgimento", 0.0);
        sistemaBi.put("Fiducia", 0.0);
        sistemaUniBi.put("Trasparenza", 0.0);
        sistemaUniBi.put("Persuasione", 0.0);
        sistemaUniBi.put("Coinvolgimento", 0.0);
        sistemaUniBi.put("Fiducia", 0.0);

        indifferentiUni.put("Trasparenza", 0.0);
        indifferentiUni.put("Persuasione", 0.0);
        indifferentiUni.put("Coinvolgimento", 0.0);
        indifferentiUni.put("Fiducia", 0.0);
        indifferentiBi.put("Trasparenza", 0.0);
        indifferentiBi.put("Persuasione", 0.0);
        indifferentiBi.put("Coinvolgimento", 0.0);
        indifferentiBi.put("Fiducia", 0.0);
        indifferentiUniBi.put("Trasparenza", 0.0);
        indifferentiUniBi.put("Persuasione", 0.0);
        indifferentiUniBi.put("Coinvolgimento", 0.0);
        indifferentiUniBi.put("Fiducia", 0.0);

        int totaliUni = 0, totaliBi = 0, totaliUniBi = 0;


        for (ValutazioneTipo4 v4 : valutazioni4){

            if (v4.getConfigurazione().equals("unigrammi")){

                totaliUni++;

                if (v4.getTrasparenza() == 1){

                    sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")+1);

                } else if (v4.getTrasparenza() == 2){

                    baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")+1);

                } else {

                    indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){

                    sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")+1);

                } else if (v4.getPersuasione() == 2){

                    baselineUni.put("Persuasione", baselineUni.get("Persuasione")+1);

                } else {

                    indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){

                    sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")+1);

                } else if (v4.getCoinvolgimento() == 2){

                    baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")+1);

                } else {

                    indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")+1);

                }

                if (v4.getFiducia() == 1){

                    sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")+1);

                } else if (v4.getFiducia() == 2){

                    baselineUni.put("Fiducia", baselineUni.get("Fiducia")+1);

                } else {

                    indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")+1);

                }


            } else if (v4.getConfigurazione().equals("bigrammi")){

                totaliBi++;

                if (v4.getTrasparenza() == 1){

                    sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")+1);

                } else if (v4.getTrasparenza() == 2){

                    baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")+1);

                } else {

                    indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){

                    sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")+1);

                } else if (v4.getPersuasione() == 2){

                    baselineBi.put("Persuasione", baselineBi.get("Persuasione")+1);

                } else {

                    indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){

                    sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")+1);

                } else if (v4.getCoinvolgimento() == 2){

                    baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")+1);

                } else {

                    indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")+1);

                }

                if (v4.getFiducia() == 1){

                    sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")+1);

                } else if (v4.getFiducia() == 2){

                    baselineBi.put("Fiducia", baselineBi.get("Fiducia")+1);

                } else {

                    indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")+1);

                }

            } else if (v4.getConfigurazione().equals("unibigrammi")){

                totaliUniBi++;

                if (v4.getTrasparenza() == 1){

                    sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")+1);

                } else if (v4.getTrasparenza() == 2){

                    baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")+1);

                } else {

                    indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")+1);
                }

                if (v4.getPersuasione() == 1){

                    sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")+1);

                } else if (v4.getPersuasione() == 2){

                    baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")+1);

                } else {

                    indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")+1);

                }

                if (v4.getCoinvolgimento() == 1){

                    sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")+1);

                } else if (v4.getCoinvolgimento() == 2){

                    baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")+1);

                } else {

                    indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")+1);

                }

                if (v4.getFiducia() == 1){

                    sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")+1);

                } else if (v4.getFiducia() == 2){

                    baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")+1);

                } else {

                    indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")+1);

                }
            }
        }

        if (totaliUni != 0){
            sistemaUni.put("Trasparenza", sistemaUni.get("Trasparenza")/totaliUni);
            sistemaUni.put("Persuasione", sistemaUni.get("Persuasione")/totaliUni);
            sistemaUni.put("Coinvolgimento", sistemaUni.get("Coinvolgimento")/totaliUni);
            sistemaUni.put("Fiducia", sistemaUni.get("Fiducia")/totaliUni);
            baselineUni.put("Trasparenza", baselineUni.get("Trasparenza")/totaliUni);
            baselineUni.put("Persuasione", baselineUni.get("Persuasione")/totaliUni);
            baselineUni.put("Coinvolgimento", baselineUni.get("Coinvolgimento")/totaliUni);
            baselineUni.put("Fiducia", baselineUni.get("Fiducia")/totaliUni);
            indifferentiUni.put("Trasparenza", indifferentiUni.get("Trasparenza")/totaliUni);
            indifferentiUni.put("Persuasione", indifferentiUni.get("Persuasione")/totaliUni);
            indifferentiUni.put("Coinvolgimento", indifferentiUni.get("Coinvolgimento")/totaliUni);
            indifferentiUni.put("Fiducia", indifferentiUni.get("Fiducia")/totaliUni);
        }

        if (totaliBi != 0){
            sistemaBi.put("Trasparenza", sistemaBi.get("Trasparenza")/totaliBi);
            sistemaBi.put("Persuasione", sistemaBi.get("Persuasione")/totaliBi);
            sistemaBi.put("Coinvolgimento", sistemaBi.get("Coinvolgimento")/totaliBi);
            sistemaBi.put("Fiducia", sistemaBi.get("Fiducia")/totaliBi);
            baselineBi.put("Trasparenza", baselineBi.get("Trasparenza")/totaliBi);
            baselineBi.put("Persuasione", baselineBi.get("Persuasione")/totaliBi);
            baselineBi.put("Coinvolgimento", baselineBi.get("Coinvolgimento")/totaliBi);
            baselineBi.put("Fiducia", baselineBi.get("Fiducia")/totaliBi);
            indifferentiBi.put("Trasparenza", indifferentiBi.get("Trasparenza")/totaliBi);
            indifferentiBi.put("Persuasione", indifferentiBi.get("Persuasione")/totaliBi);
            indifferentiBi.put("Coinvolgimento", indifferentiBi.get("Coinvolgimento")/totaliBi);
            indifferentiBi.put("Fiducia", indifferentiBi.get("Fiducia")/totaliBi);
        }

        if (totaliUniBi != 0){
            sistemaUniBi.put("Trasparenza", sistemaUniBi.get("Trasparenza")/totaliUniBi);
            sistemaUniBi.put("Persuasione", sistemaUniBi.get("Persuasione")/totaliUniBi);
            sistemaUniBi.put("Coinvolgimento", sistemaUniBi.get("Coinvolgimento")/totaliUniBi);
            sistemaUniBi.put("Fiducia", sistemaUniBi.get("Fiducia")/totaliUniBi);
            baselineUniBi.put("Trasparenza", baselineUniBi.get("Trasparenza")/totaliUniBi);
            baselineUniBi.put("Persuasione", baselineUniBi.get("Persuasione")/totaliUniBi);
            baselineUniBi.put("Coinvolgimento", baselineUniBi.get("Coinvolgimento")/totaliUniBi);
            baselineUniBi.put("Fiducia", baselineUniBi.get("Fiducia")/totaliUniBi);
            indifferentiUniBi.put("Trasparenza", indifferentiUniBi.get("Trasparenza")/totaliUniBi);
            indifferentiUniBi.put("Persuasione", indifferentiUniBi.get("Persuasione")/totaliUniBi);
            indifferentiUniBi.put("Coinvolgimento", indifferentiUniBi.get("Coinvolgimento")/totaliUniBi);
            indifferentiUniBi.put("Fiducia", indifferentiUniBi.get("Fiducia")/totaliUniBi);
        }


        uni.put("sistema", sistemaUni);
        uni.put("baseline", baselineUni);
        uni.put("indifferenti", indifferentiUni);

        bi.put("sistema", sistemaBi);
        bi.put("baseline", baselineBi);
        bi.put("indifferenti", indifferentiBi);

        unibi.put("sistema", sistemaUniBi);
        unibi.put("baseline", baselineUniBi);
        unibi.put("indifferenti", indifferentiUniBi);

        esiti.put("unigrammi", uni);
        esiti.put("bigrammi", bi);
        esiti.put("unibigrammi", unibi);

        return esiti;
    }

    // centroide vs singole - preferenza in base al numero di contesti
    public HashMap<Integer, HashMap<String, Double>> centroideVSsingoleNumContesti(){

        HashMap<Integer, HashMap<String, Double>> preferenzaNumeroContesti = new HashMap<Integer, HashMap<String, Double>>();

        int centroide1 = 0, centroide2 = 0, centroide3 = 0, centroide4 = 0, centroide5 = 0,
                singole1 = 0, singole2 = 0, singole3  = 0, singole4 = 0, singole5 = 0,
                indiff1 = 0, indiff2 = 0, indiff3  = 0, indiff4 = 0, indiff5 = 0,
                totale1 = 0, totale2 = 0, totale3 = 0, totale4 = 0, totale5 = 0;

        for (ValutazioneTipo3 v3 : valutazioni3){

            if (v3.getNumeroContesti() == 1){

                totale1++;

                if (v3.getPreferenza0() == 1){
                    centroide1++;
                } else if (v3.getPreferenza0() == 2){
                    singole1++;
                } else {
                    indiff1++;
                }

            } else if (v3.getNumeroContesti() == 2){

                totale2++;

                if (v3.getPreferenza0() == 1){
                    centroide2++;
                } else if (v3.getPreferenza0() == 2){
                    singole2++;
                } else {
                    indiff2++;
                }

            } else if (v3.getNumeroContesti() == 3) {

                totale3++;

                if (v3.getPreferenza0() == 1) {
                    centroide3++;
                } else if (v3.getPreferenza0() == 2) {
                    singole3++;
                } else {
                    indiff3++;
                }
            } else if (v3.getNumeroContesti() == 4) {

                totale4++;

                if (v3.getPreferenza0() == 1) {
                    centroide4++;
                } else if (v3.getPreferenza0() == 2) {
                    singole4++;
                } else {
                    indiff4++;
                }
            } else if (v3.getNumeroContesti() == 5) {

                totale5++;

                if (v3.getPreferenza0() == 1) {
                    centroide5++;
                } else if (v3.getPreferenza0() == 2) {
                    singole5++;
                } else {
                    indiff5++;
                }
            }

        }

        HashMap<String, Double> contesto1 = new HashMap<String, Double>();
        HashMap<String, Double> contesto2 = new HashMap<String, Double>();
        HashMap<String, Double> contesto3 = new HashMap<String, Double>();
        HashMap<String, Double> contesto4 = new HashMap<String, Double>();
        HashMap<String, Double> contesto5 = new HashMap<String, Double>();

        contesto1.put("Centroide", totale1 != 0 ? (double)centroide1/totale1 : 0);
        contesto1.put("Singole", totale1 != 0 ? (double)singole1/totale1 : 0);
        contesto1.put("Indifferente", totale1 != 0 ? (double)indiff1/totale1 : 0);

        contesto2.put("Centroide", totale2 != 0 ? (double)centroide2/totale2 : 0);
        contesto2.put("Singole", totale2 != 0 ? (double)singole2/totale2 : 0);
        contesto2.put("Indifferente", totale2 != 0 ? (double)indiff2/totale2 : 0);

        contesto3.put("Centroide", totale3 != 0 ? (double)centroide3/totale3 : 0);
        contesto3.put("Singole", totale3 != 0 ? (double)singole3/totale3 : 0);
        contesto3.put("Indifferente", totale3 != 0 ? (double)indiff3/totale3 : 0);

        contesto4.put("Centroide", totale4 != 0 ? (double)centroide4/totale4 : 0);
        contesto4.put("Singole", totale4 != 0 ? (double)singole4/totale4 : 0);
        contesto4.put("Indifferente", totale4 != 0 ? (double)indiff4/totale4 : 0);

        contesto5.put("Centroide", totale5 != 0 ? (double)centroide5/totale5 : 0);
        contesto5.put("Singole", totale5 != 0 ? (double)singole5/totale5 : 0);
        contesto5.put("Indifferente", totale5 != 0 ? (double)indiff5/totale5 : 0);

        preferenzaNumeroContesti.put(1, contesto1);
        preferenzaNumeroContesti.put(2, contesto2);
        preferenzaNumeroContesti.put(3, contesto3);
        preferenzaNumeroContesti.put(4, contesto4);
        preferenzaNumeroContesti.put(5, contesto5);

        return preferenzaNumeroContesti;

    }

    // info generali sugli utenti
    public HashMap<String, HashMap<String, Integer>> infoUtenti(){

        HashMap<String, HashMap<String, Integer>> infoUtenti = new HashMap<>();

        HashMap<String, Integer> eta = new HashMap<>();
        HashMap<String, Integer> genere = new HashMap<>();
        HashMap<String, Integer> titolo = new HashMap<>();
        HashMap<String, Integer> frequenza = new HashMap<>();
        HashMap<String, Integer> recsys = new HashMap<>();

        eta.put("<18", 0);
        eta.put("18-25", 0);
        eta.put("26-35", 0);
        eta.put("36-50", 0);
        eta.put(">50", 0);

        genere.put("uomo", 0);
        genere.put("donna", 0);

        titolo.put("Diploma Scuola superiore",0);
        titolo.put("Laurea Triennale",0);
        titolo.put("Laurea Magistrale",0);
        titolo.put("Dottorato di Ricerca",0);
        titolo.put("Altro",0);

        frequenza.put("0-1 volta", 0);
        frequenza.put("2-4 volte", 0);
        frequenza.put("5-7 volte", 0);

        recsys.put("si", 0);
        recsys.put("no", 0);

        for (Utente u : utenti){

            switch (u.getEta()){
                case 1:
                    eta.put("<18", eta.get("<18")+1);
                    break;
                case 2:
                    eta.put("18-25", eta.get("18-25")+1);
                    break;
                case 3:
                    eta.put("26-35", eta.get("26-35")+1);
                    break;
                case 4:
                    eta.put("36-50", eta.get("36-50")+1);
                    break;
                case 5:
                    eta.put(">50", eta.get(">50")+1);
                    break;
            }

            if (u.getGenere().equals("uomo")){
                genere.put("uomo", genere.get("uomo")+1);
            } else {
                genere.put("donna", genere.get("donna")+1);
            }

            switch (u.getTitoloStudio()){
                case 6:
                    titolo.put("Diploma Scuola superiore",titolo.get("Diploma Scuola superiore")+1);
                    break;
                case 7:
                    titolo.put("Laurea Triennale",titolo.get("Laurea Triennale")+1);
                    break;
                case 8:
                    titolo.put("Laurea Magistrale",titolo.get("Laurea Magistrale")+1);
                    break;
                case 9:
                    titolo.put("Dottorato di Ricerca",titolo.get("Dottorato di Ricerca")+1);
                    break;
                case 10:
                    titolo.put("Altro",titolo.get("Altro")+1);
                    break;
            }

            switch (u.getFrequenzaUscite()){
                case 11:
                    frequenza.put("0-1 volta", frequenza.get("0-1 volta")+1);
                    break;
                case 12:
                    frequenza.put("2-4 volte", frequenza.get("2-4 volte")+1);
                    break;
                case 13:
                    frequenza.put("5-7 volte", frequenza.get("5-7 volte")+1);
                    break;
            }

            if (u.getUsoRecSys().equals("si")){
                recsys.put("si", recsys.get("si")+1);
            } else {
                recsys.put("no", recsys.get("no")+1);
            }
        }

        infoUtenti.put("Eta", eta);
        infoUtenti.put("Genere", genere);
        infoUtenti.put("Titoli di studio", titolo);
        infoUtenti.put("Frequenze uscite", frequenza);
        infoUtenti.put("Uso Rec Sys", recsys);

        return infoUtenti;

    }

    // contatori unigrammi - bigrammi - unibigrammi
    public HashMap<String, Integer> contatoriConfigurazioni(){

        HashMap<String, Integer> contatori = new HashMap<String, Integer>();

        contatori.put("unigrammi",0);
        contatori.put("bigrammi", 0);
        contatori.put("unibigrammi",0);

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            contatori.put(v1.configurazione, contatori.get(v1.configurazione)+1);

        }

        return contatori;

    }

    public HashMap<Integer, Integer> contatoriNumeroContesti(){

        HashMap<Integer, Integer> contatori = new HashMap<Integer, Integer>();

        contatori.put(1,0);
        contatori.put(2, 0);
        contatori.put(3,0);
        contatori.put(4,0);
        contatori.put(5,0);

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            contatori.put(v1.getNumeroContesti(), contatori.get(v1.getNumeroContesti())+1);

        }

        return contatori;

    }

    // info preferenza metriche contesti singoli
    public HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenzeMetrichePerContesti(){

        HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenze = new HashMap<Integer, HashMap<String, HashMap<String, Double>>>();

        HashMap<Integer, Integer> contatori = new HashMap<>();

        for (int i=1; i<=13; i++){

            contatori.put(i, 0);

            HashMap<String, Double> trasp = new HashMap<String, Double>();

            trasp.put("centroide", 0.0);
            trasp.put("singole", 0.0);
            trasp.put("indifferente", 0.0);

            HashMap<String, Double> coinv = new HashMap<String, Double>();

            coinv.put("centroide", 0.0);
            coinv.put("singole", 0.0);
            coinv.put("indifferente", 0.0);

            HashMap<String, Double> pers = new HashMap<String, Double>();

            pers.put("centroide", 0.0);
            pers.put("singole", 0.0);
            pers.put("indifferente", 0.0);

            HashMap<String, Double> fid = new HashMap<String, Double>();

            fid.put("centroide", 0.0);
            fid.put("singole", 0.0);
            fid.put("indifferente", 0.0);

            HashMap<String, HashMap<String, Double>> b = new HashMap<String, HashMap<String, Double>>();

            b.put("trasparenza", trasp);
            b.put("coinvolgimento", coinv);
            b.put("persuasione", pers);
            b.put("fiducia", fid);

            preferenze.put(i, b);

        }

        for (ValutazioneTipo3 v3 : valutazioni3){

            for (int c : v3.getListaContesti()){

                contatori.put(c, contatori.get(c)+1);

                if (v3.getTrasparenza() == 0){

                    preferenze.get(c).get("trasparenza").put("indifferente", preferenze.get(c).get("trasparenza").get("indifferente")+1 );

                } else if (v3.getTrasparenza() == 1) {

                    preferenze.get(c).get("trasparenza").put("centroide", preferenze.get(c).get("trasparenza").get("centroide")+1 );

                } else {

                    preferenze.get(c).get("trasparenza").put("singole", preferenze.get(c).get("trasparenza").get("singole")+1 );

                }

                if (v3.getCoinvolgimento() == 0){

                    preferenze.get(c).get("coinvolgimento").put("indifferente", preferenze.get(c).get("coinvolgimento").get("indifferente")+1 );

                } else if (v3.getCoinvolgimento() == 1) {

                    preferenze.get(c).get("coinvolgimento").put("centroide", preferenze.get(c).get("coinvolgimento").get("centroide")+1 );

                } else {

                    preferenze.get(c).get("coinvolgimento").put("singole", preferenze.get(c).get("coinvolgimento").get("singole")+1 );

                }


                if (v3.getPersuasione() == 0){

                    preferenze.get(c).get("persuasione").put("indifferente", preferenze.get(c).get("persuasione").get("indifferente")+1 );

                } else if (v3.getPersuasione() == 1) {

                    preferenze.get(c).get("persuasione").put("centroide", preferenze.get(c).get("persuasione").get("centroide")+1 );

                } else {

                    preferenze.get(c).get("persuasione").put("singole", preferenze.get(c).get("persuasione").get("singole")+1 );

                }

                if (v3.getFiducia() == 0){

                    preferenze.get(c).get("fiducia").put("indifferente", preferenze.get(c).get("fiducia").get("indifferente")+1 );

                } else if (v3.getFiducia() == 1) {

                    preferenze.get(c).get("fiducia").put("centroide", preferenze.get(c).get("fiducia").get("centroide")+1 );

                } else {

                    preferenze.get(c).get("fiducia").put("singole", preferenze.get(c).get("fiducia").get("singole")+1 );

                }

            }



        }

        for (int c=1; c<=13; c++){

            preferenze.get(c).get("trasparenza").put("indifferente", preferenze.get(c).get("trasparenza").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("trasparenza").put("centroide", preferenze.get(c).get("trasparenza").get("centroide") / contatori.get(c));
            preferenze.get(c).get("trasparenza").put("singole", preferenze.get(c).get("trasparenza").get("singole") / contatori.get(c));

            preferenze.get(c).get("coinvolgimento").put("indifferente", preferenze.get(c).get("coinvolgimento").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("coinvolgimento").put("centroide", preferenze.get(c).get("coinvolgimento").get("centroide") / contatori.get(c));
            preferenze.get(c).get("coinvolgimento").put("singole", preferenze.get(c).get("coinvolgimento").get("singole") / contatori.get(c));

            preferenze.get(c).get("persuasione").put("indifferente", preferenze.get(c).get("persuasione").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("persuasione").put("centroide", preferenze.get(c).get("persuasione").get("centroide") / contatori.get(c));
            preferenze.get(c).get("persuasione").put("singole", preferenze.get(c).get("persuasione").get("singole") / contatori.get(c));

            preferenze.get(c).get("fiducia").put("indifferente", preferenze.get(c).get("fiducia").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("fiducia").put("centroide", preferenze.get(c).get("fiducia").get("centroide") / contatori.get(c));
            preferenze.get(c).get("fiducia").put("singole", preferenze.get(c).get("fiducia").get("singole") / contatori.get(c));

        }


        return preferenze;


    }

    // info preferenza metriche contesti singoli, sistema VS baseline
    public HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenzeMetrichePerContestiSistemaVSBaseline(){

        HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenze = new HashMap<Integer, HashMap<String, HashMap<String, Double>>>();

        HashMap<Integer, Integer> contatori = new HashMap<>();

        for (int i=1; i<=13; i++){

            contatori.put(i, 0);

            HashMap<String, Double> trasp = new HashMap<String, Double>();

            trasp.put("sistema", 0.0);
            trasp.put("baseline", 0.0);
            trasp.put("indifferente", 0.0);

            HashMap<String, Double> coinv = new HashMap<String, Double>();

            coinv.put("sistema", 0.0);
            coinv.put("baseline", 0.0);
            coinv.put("indifferente", 0.0);

            HashMap<String, Double> pers = new HashMap<String, Double>();

            pers.put("sistema", 0.0);
            pers.put("baseline", 0.0);
            pers.put("indifferente", 0.0);

            HashMap<String, Double> fid = new HashMap<String, Double>();

            fid.put("sistema", 0.0);
            fid.put("baseline", 0.0);
            fid.put("indifferente", 0.0);

            HashMap<String, HashMap<String, Double>> b = new HashMap<String, HashMap<String, Double>>();

            b.put("trasparenza", trasp);
            b.put("coinvolgimento", coinv);
            b.put("persuasione", pers);
            b.put("fiducia", fid);

            preferenze.put(i, b);

        }

        for (ValutazioneTipo4 v4 : valutazioni4){

            for (int c : v4.getListaContesti()){

                contatori.put(c, contatori.get(c)+1);

                if (v4.getTrasparenza() == 0){

                    preferenze.get(c).get("trasparenza").put("indifferente", preferenze.get(c).get("trasparenza").get("indifferente")+1 );

                } else if (v4.getTrasparenza() == 1) {

                    preferenze.get(c).get("trasparenza").put("sistema", preferenze.get(c).get("trasparenza").get("sistema")+1 );

                } else {

                    preferenze.get(c).get("trasparenza").put("baseline", preferenze.get(c).get("trasparenza").get("baseline")+1 );

                }

                if (v4.getCoinvolgimento() == 0){

                    preferenze.get(c).get("coinvolgimento").put("indifferente", preferenze.get(c).get("coinvolgimento").get("indifferente")+1 );

                } else if (v4.getCoinvolgimento() == 1) {

                    preferenze.get(c).get("coinvolgimento").put("sistema", preferenze.get(c).get("coinvolgimento").get("sistema")+1 );

                } else {

                    preferenze.get(c).get("coinvolgimento").put("baseline", preferenze.get(c).get("coinvolgimento").get("baseline")+1 );

                }


                if (v4.getPersuasione() == 0){

                    preferenze.get(c).get("persuasione").put("indifferente", preferenze.get(c).get("persuasione").get("indifferente")+1 );

                } else if (v4.getPersuasione() == 1) {

                    preferenze.get(c).get("persuasione").put("sistema", preferenze.get(c).get("persuasione").get("sistema")+1 );

                } else {

                    preferenze.get(c).get("persuasione").put("baseline", preferenze.get(c).get("persuasione").get("baseline")+1 );

                }

                if (v4.getFiducia() == 0){

                    preferenze.get(c).get("fiducia").put("indifferente", preferenze.get(c).get("fiducia").get("indifferente")+1 );

                } else if (v4.getFiducia() == 1) {

                    preferenze.get(c).get("fiducia").put("sistema", preferenze.get(c).get("fiducia").get("sistema")+1 );

                } else {

                    preferenze.get(c).get("fiducia").put("baseline", preferenze.get(c).get("fiducia").get("baseline")+1 );

                }

            }



        }

        for (int c=1; c<=13; c++){

            preferenze.get(c).get("trasparenza").put("indifferente", preferenze.get(c).get("trasparenza").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("trasparenza").put("sistema", preferenze.get(c).get("trasparenza").get("sistema") / contatori.get(c));
            preferenze.get(c).get("trasparenza").put("baseline", preferenze.get(c).get("trasparenza").get("baseline") / contatori.get(c));

            preferenze.get(c).get("coinvolgimento").put("indifferente", preferenze.get(c).get("coinvolgimento").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("coinvolgimento").put("sistema", preferenze.get(c).get("coinvolgimento").get("sistema") / contatori.get(c));
            preferenze.get(c).get("coinvolgimento").put("baseline", preferenze.get(c).get("coinvolgimento").get("baseline") / contatori.get(c));

            preferenze.get(c).get("persuasione").put("indifferente", preferenze.get(c).get("persuasione").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("persuasione").put("sistema", preferenze.get(c).get("persuasione").get("sistema") / contatori.get(c));
            preferenze.get(c).get("persuasione").put("baseline", preferenze.get(c).get("persuasione").get("baseline") / contatori.get(c));

            preferenze.get(c).get("fiducia").put("indifferente", preferenze.get(c).get("fiducia").get("indifferente") / contatori.get(c)) ;
            preferenze.get(c).get("fiducia").put("sistema", preferenze.get(c).get("fiducia").get("sistema") / contatori.get(c));
            preferenze.get(c).get("fiducia").put("baseline", preferenze.get(c).get("fiducia").get("baseline") / contatori.get(c));

        }


        return preferenze;


    }

    // medie metriche per contesti - centroide vs singole
    public HashMap<Integer, HashMap<String, HashMap<String, Double>>> medieMetrichePerContesti(){

        HashMap<Integer, HashMap<String, HashMap<String, Double>>> preferenze = new HashMap<>();

        HashMap<Integer, Integer> contatori = new HashMap<>();

        for (int i=1; i<=13; i++){

            contatori.put(i, 0);

        }

        for (int i=1; i<=13; i++){

            HashMap<String, HashMap<String, Double>> t = new HashMap<>();

            HashMap<String, Double> c = new HashMap<>();
            c.put("trasparenza", 0.0);
            c.put("persuasione", 0.0);
            c.put("coinvolgimento", 0.0);
            c.put("fiducia", 0.0);

            t.put("centroide", c);

            HashMap<String, Double> d = new HashMap<>();
            d.put("trasparenza", 0.0);
            d.put("persuasione", 0.0);
            d.put("coinvolgimento", 0.0);
            d.put("fiducia", 0.0);

            t.put("singole", d);

            preferenze.put(i, t);

        }

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            //centroide

            for (int contesto : v1.listaContesti){

                preferenze.get(contesto).get("centroide").put("trasparenza", preferenze.get(contesto).get("centroide").get("trasparenza") + (double)v1.getTrasparenza());
                preferenze.get(contesto).get("centroide").put("persuasione", preferenze.get(contesto).get("centroide").get("persuasione") + (double)v1.getPersuasione());
                preferenze.get(contesto).get("centroide").put("coinvolgimento", preferenze.get(contesto).get("centroide").get("coinvolgimento") + (double)v1.getCoinvolgimento());
                preferenze.get(contesto).get("centroide").put("fiducia", preferenze.get(contesto).get("centroide").get("fiducia") + (double)v1.getFiducia());

                contatori.put(contesto, contatori.get(contesto)+1);

            }

        }

        for (ValutazioneTipo1_2 v2 : valutazioni2){

            //singole

            for (int contesto : v2.listaContesti){

                preferenze.get(contesto).get("singole").put("trasparenza", preferenze.get(contesto).get("singole").get("trasparenza") + (double)v2.getTrasparenza());
                preferenze.get(contesto).get("singole").put("persuasione", preferenze.get(contesto).get("singole").get("persuasione") + (double)v2.getPersuasione());
                preferenze.get(contesto).get("singole").put("coinvolgimento", preferenze.get(contesto).get("singole").get("coinvolgimento") + (double)v2.getCoinvolgimento());
                preferenze.get(contesto).get("singole").put("fiducia", preferenze.get(contesto).get("singole").get("fiducia") + (double)v2.getFiducia());


            }

        }

        for (int contesto=1; contesto<=13; contesto++){

            //centroide

            preferenze.get(contesto).get("centroide").put("trasparenza", preferenze.get(contesto).get("centroide").get("trasparenza") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("persuasione", preferenze.get(contesto).get("centroide").get("persuasione") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("coinvolgimento", preferenze.get(contesto).get("centroide").get("coinvolgimento") / contatori.get(contesto));
            preferenze.get(contesto).get("centroide").put("fiducia", preferenze.get(contesto).get("centroide").get("fiducia") / contatori.get(contesto));

            //singole

            preferenze.get(contesto).get("singole").put("trasparenza", preferenze.get(contesto).get("singole").get("trasparenza") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("persuasione", preferenze.get(contesto).get("singole").get("persuasione") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("coinvolgimento", preferenze.get(contesto).get("singole").get("coinvolgimento") / contatori.get(contesto));
            preferenze.get(contesto).get("singole").put("fiducia", preferenze.get(contesto).get("singole").get("fiducia") / contatori.get(contesto));


        }

        return preferenze;


    }

    // media metriche per contesti - generale
    public HashMap<Integer, HashMap<String, Double>> medieMetrichePerContestiGenerale(){

        HashMap<Integer, HashMap<String, Double>> preferenze = new HashMap<>();
        HashMap<Integer, Integer> contatori = new HashMap<>();

        for (int i=1; i<=13; i++){

            contatori.put(i, 0);

        }

        for (int i=1; i<=13; i++){

            HashMap<String, Double> c = new HashMap<>();
            c.put("trasparenza", 0.0);
            c.put("persuasione", 0.0);
            c.put("coinvolgimento", 0.0);
            c.put("fiducia", 0.0);

            preferenze.put(i, c);

        }

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            //centroide

            for (int contesto : v1.listaContesti){

                preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") + (double)v1.getTrasparenza());
                preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") + (double)v1.getPersuasione());
                preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") + (double)v1.getCoinvolgimento());
                preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") + (double)v1.getFiducia());

                contatori.put(contesto, contatori.get(contesto)+1);

            }

        }

        for (ValutazioneTipo1_2 v2 : valutazioni2){

            //singole

            for (int contesto : v2.listaContesti){

                preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") + (double)v2.getTrasparenza());
                preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") + (double)v2.getPersuasione());
                preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") + (double)v2.getCoinvolgimento());
                preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") + (double)v2.getFiducia());


            }

        }

        for (int contesto=1; contesto<=13; contesto++){

            //centroide

            preferenze.get(contesto).put("trasparenza", preferenze.get(contesto).get("trasparenza") / contatori.get(contesto) / 2);
            preferenze.get(contesto).put("persuasione", preferenze.get(contesto).get("persuasione") / contatori.get(contesto) / 2);
            preferenze.get(contesto).put("coinvolgimento", preferenze.get(contesto).get("coinvolgimento") / contatori.get(contesto) / 2);
            preferenze.get(contesto).put("fiducia", preferenze.get(contesto).get("fiducia") / contatori.get(contesto) / 2);



        }



        return preferenze;

    }

    // contatori locali suggeriti
    public HashMap<Integer, Integer> contatoriLocaliSuggeriti(){

        HashMap<Integer, Integer> contatoriLocali = new HashMap<Integer, Integer>();

        for (ValutazioneTipo1_2 v1 : valutazioni1){

            Integer locale = v1.getLocale();

            if (contatoriLocali.containsKey(locale)){

                // aggiornamento occorrenze
                contatoriLocali.put(locale, contatoriLocali.get(locale)+1);

            } else {

                // prima occorrenza
                contatoriLocali.put(locale, 1);
            }

        }

        return contatoriLocali;


    }

    public Report() throws Exception {

        utenti = new HashSet<>();
        valutazioni1 = new HashSet<>();
        valutazioni2 = new HashSet<>();
        valutazioni3 = new HashSet<>();
        valutazioni4 = new HashSet<>();

        //Scanner users = new Scanner(new File("filesWherEXP\\utenti.txt"), "UTF-8");
        Scanner users = new Scanner(new File("filesWherEXP/utenti.txt"), "UTF-8");
        users.nextLine();

        while(users.hasNextLine()){

            String[] riga = users.nextLine().split(";");

            Utente u = new Utente();

            u.setId(riga[0]);
            u.setEta(Integer.parseInt(riga[1]));
            u.setGenere(riga[2]);
            u.setTitoloStudio(Integer.parseInt(riga[3]));
            u.setFrequenzaUscite(Integer.parseInt(riga[4]));
            u.setUsoRecSys(riga[5]);

            utenti.add(u);
        }

        users.close();

        //Scanner val1 = new Scanner(new File("filesWherEXP\\valutazione1.txt"), "UTF-8");
        Scanner val1 = new Scanner(new File("filesWherEXP/valutazione1.txt"), "UTF-8");
        val1.nextLine();

        while(val1.hasNextLine()){

            String[] riga = val1.nextLine().split(";");

            ValutazioneTipo1_2 v1 = new ValutazioneTipo1_2();

            v1.setId(riga[0]);
            v1.setConfigurazione(riga[1]);
            v1.setCitta(riga[2]);
            v1.setLocale(Integer.parseInt(riga[3]));
            v1.setNumeroContesti(Integer.parseInt(riga[4]));
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[5].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v1.setListaContesti(lc);
            v1.setTrasparenza(Integer.parseInt(riga[6]));
            v1.setPersuasione(Integer.parseInt(riga[7]));
            v1.setCoinvolgimento(Integer.parseInt(riga[8]));
            v1.setFiducia(Integer.parseInt(riga[9]));

            HashSet<ValutazioneTipo1_2> rim = new HashSet<>();
            for (ValutazioneTipo1_2 v : valutazioni1){
                if (v.getId().equals(v1.getId())){
                    rim.add(v);
                }
            }
            valutazioni1.removeAll(rim);

            valutazioni1.add(v1);
        }

        val1.close();

        //Scanner val2 = new Scanner(new File("filesWherEXP\\valutazione2.txt"), "UTF-8");
        Scanner val2 = new Scanner(new File("filesWherEXP/valutazione2.txt"), "UTF-8");
        val2.nextLine();

        while(val2.hasNextLine()){

            String[] riga = val2.nextLine().split(";");

            ValutazioneTipo1_2 v2 = new ValutazioneTipo1_2();

            v2.setId(riga[0]);
            v2.setConfigurazione(riga[1]);
            v2.setCitta(riga[2]);
            v2.setLocale(Integer.parseInt(riga[3]));
            v2.setNumeroContesti(Integer.parseInt(riga[4]));
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[5].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v2.setListaContesti(lc);
            v2.setTrasparenza(Integer.parseInt(riga[6]));
            v2.setPersuasione(Integer.parseInt(riga[7]));
            v2.setCoinvolgimento(Integer.parseInt(riga[8]));
            v2.setFiducia(Integer.parseInt(riga[9]));

            HashSet<ValutazioneTipo1_2> rim = new HashSet<>();
            for (ValutazioneTipo1_2 v : valutazioni2){
                if (v.getId().equals(v2.getId())){
                    rim.add(v);
                }
            }
            valutazioni2.removeAll(rim);

            valutazioni2.add(v2);
        }

        val2.close();

        //Scanner val3 = new Scanner(new File("filesWherEXP\\valutazione3.txt"), "UTF-8");
        Scanner val3 = new Scanner(new File("filesWherEXP/valutazione3.txt"), "UTF-8");
        val3.nextLine();

        while(val3.hasNextLine()){

            String[] riga = val3.nextLine().split(";");

            ValutazioneTipo3 v3 = new ValutazioneTipo3();

            v3.setId(riga[0]);
            v3.setConfigurazione(riga[1]);
            v3.setCitta(riga[2]);
            v3.setLocale(Integer.parseInt(riga[3]));
            v3.setNumeroContesti(Integer.parseInt(riga[4]));
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[5].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v3.setListaContesti(lc);
            v3.setPreferenza0(Integer.parseInt(riga[6]));
            v3.setTrasparenza(Integer.parseInt(riga[7]));
            v3.setPersuasione(Integer.parseInt(riga[8]));
            v3.setCoinvolgimento(Integer.parseInt(riga[9]));
            v3.setFiducia(Integer.parseInt(riga[10]));

            HashSet<ValutazioneTipo3> rim = new HashSet<>();
            for (ValutazioneTipo3 v : valutazioni3){
                if (v.getId().equals(v3.getId())){
                    rim.add(v);
                }
            }
            valutazioni3.removeAll(rim);

            valutazioni3.add(v3);
        }

        val3.close();

        //Scanner val4 = new Scanner(new File("filesWherEXP\\valutazione4.txt"), "UTF-8");
        Scanner val4 = new Scanner(new File("filesWherEXP/valutazione4.txt"), "UTF-8");
        val4.nextLine();

        while(val4.hasNextLine()){

            String[] riga = val4.nextLine().split(";");

            ValutazioneTipo4 v4 = new ValutazioneTipo4();

            v4.setId(riga[0]);
            v4.setConfigurazione(riga[1]);
            v4.setCitta(riga[2]);
            v4.setLocale(Integer.parseInt(riga[3]));
            v4.setNumeroContesti(Integer.parseInt(riga[4]));
            ArrayList<Integer> lc = new ArrayList<>();
            String[] lista = riga[5].split(",");
            for (String s : lista){
                lc.add(Integer.parseInt(s));
            }
            v4.setListaContesti(lc);
            v4.setPreferenza0(Integer.parseInt(riga[6]));
            v4.setTrasparenza(Integer.parseInt(riga[7]));
            v4.setPersuasione(Integer.parseInt(riga[8]));
            v4.setCoinvolgimento(Integer.parseInt(riga[9]));
            v4.setFiducia(Integer.parseInt(riga[10]));
            v4.setPreferenzaPrecedente(Integer.parseInt(riga[11]));

            HashSet<ValutazioneTipo4> rim = new HashSet<>();
            for (ValutazioneTipo4 v : valutazioni4){
                if (v.getId().equals(v4.getId())){
                    rim.add(v);
                }
            }
            valutazioni4.removeAll(rim);

            valutazioni4.add(v4);
        }

        val4.close();

        this.controllo();
    }

    private void controllo(){

        HashSet<String> rimozione = new HashSet<>();

        ArrayList<HashSet<String>> listaId = new ArrayList<HashSet<String>>();

        HashSet<String> id0 = new HashSet<>();
        for (Utente u : utenti){
            String id = u.getId();
            id0.add(id);
        }
        listaId.add(id0);

        HashSet<String> id1 = new HashSet<>();
        for (ValutazioneTipo1_2 v1 : valutazioni1){
            String id = v1.getId();
            id1.add(id);
        }
        listaId.add(id1);

        HashSet<String> id2 = new HashSet<>();
        for (ValutazioneTipo1_2 v2 : valutazioni2){
            String id = v2.getId();
            id2.add(id);
        }
        listaId.add(id2);

        HashSet<String> id3 = new HashSet<>();
        for (ValutazioneTipo3 v3 : valutazioni3){
            String id = v3.getId();
            id3.add(id);
        }
        listaId.add(id3);

        HashSet<String> id4 = new HashSet<>();
        for (ValutazioneTipo4 v4 : valutazioni4){
            String id = v4.getId();
            id4.add(id);
        }
        listaId.add(id4);

        listaId.get(0).retainAll(listaId.get(1));
        listaId.get(0).retainAll(listaId.get(2));
        listaId.get(0).retainAll(listaId.get(3));
        listaId.get(0).retainAll(listaId.get(4));

        HashSet<String> mantenere = listaId.get(0);

        HashSet<Utente> rimUt = new HashSet<>();
        for (Utente u : utenti){
            if (!mantenere.contains(u.getId())){
                rimUt.add(u);
            }
        }
        utenti.removeAll(rimUt);

        HashSet<ValutazioneTipo1_2> rimV1 = new HashSet<>();
        for (ValutazioneTipo1_2 v1 : valutazioni1){
            if (!mantenere.contains(v1.getId())){
                rimV1.add(v1);
            }
        }
        valutazioni1.removeAll(rimV1);

        HashSet<ValutazioneTipo1_2> rimV2 = new HashSet<>();
        for (ValutazioneTipo1_2 v2 : valutazioni2){
            if (!mantenere.contains(v2.getId())){
                rimV2.add(v2);
            }
        }
        valutazioni2.removeAll(rimV2);

        HashSet<ValutazioneTipo3> rimV3 = new HashSet<>();
        for (ValutazioneTipo3 v3 : valutazioni3){
            if (!mantenere.contains(v3.getId())){
                rimV3.add(v3);
            }
        }
        valutazioni3.removeAll(rimV3);

        HashSet<ValutazioneTipo4> rimV4 = new HashSet<>();
        for (ValutazioneTipo4 v4 : valutazioni4){
            if (!mantenere.contains(v4.getId())){
                rimV4.add(v4);
            }
        }
        valutazioni4.removeAll(rimV4);


        /*

        for (Utente u : utenti){

            String id = u.getId();

            boolean presente1 = false, presente2 = false, presente3 = false, presente4 = false;

            for (ValutazioneTipo1_2 v1 : valutazioni1){

                if (v1.getId().equals(id)){
                    presente1 = true;
                }
            }

            for (ValutazioneTipo1_2 v2 : valutazioni2){

                if (v2.getId().equals(id)){
                    presente2 = true;
                }
            }

            for (ValutazioneTipo3 v3 : valutazioni3){

                if (v3.getId().equals(id)){
                    presente3 = true;
                }
            }

            for (ValutazioneTipo4 v4 : valutazioni4){

                if (v4.getId().equals(id)){
                    presente4 = true;
                }
            }

            boolean presente = presente1 && presente2 && presente3 && presente4;

            if (!presente){
                rimozione.add(u);
            }
        }

        */

        /*

        for (Utente u : rimozione){

            String id = u.getId();

            HashSet<ValutazioneTipo1_2> rim1 = new HashSet<>();
            for (ValutazioneTipo1_2 v1 : valutazioni1){
                if (v1.getId().equals(id)) rim1.add(v1);
            }
            valutazioni1.removeAll(rim1);

            HashSet<ValutazioneTipo1_2> rim2 = new HashSet<>();
            for (ValutazioneTipo1_2 v2 : valutazioni2){
                if (v2.getId().equals(id)) rim2.add(v2);
            }
            valutazioni2.removeAll(rim2);

            HashSet<ValutazioneTipo3> rim3 = new HashSet<>();
            for (ValutazioneTipo3 v3 : valutazioni3){
                if (v3.getId().equals(id)) rim3.add(v3);
            }
            valutazioni3.removeAll(rim3);

            HashSet<ValutazioneTipo4> rim4 = new HashSet<>();
            for (ValutazioneTipo4 v4 : valutazioni4){
                if (v4.getId().equals(id)) rim4.add(v4);
            }
            valutazioni4.removeAll(rim4);

        }

        utenti.removeAll(rimozione);

        */

    }

    public HashSet<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(HashSet<Utente> utenti) {
        this.utenti = utenti;
    }

    public HashSet<ValutazioneTipo1_2> getValutazioni1() {
        return valutazioni1;
    }

    public void setValutazioni1(HashSet<ValutazioneTipo1_2> valutazioni1) {
        this.valutazioni1 = valutazioni1;
    }

    public HashSet<ValutazioneTipo1_2> getValutazioni2() {
        return valutazioni2;
    }

    public void setValutazioni2(HashSet<ValutazioneTipo1_2> valutazioni2) {
        this.valutazioni2 = valutazioni2;
    }

    public HashSet<ValutazioneTipo3> getValutazioni3() {
        return valutazioni3;
    }

    public void setValutazioni3(HashSet<ValutazioneTipo3> valutazioni3) {
        this.valutazioni3 = valutazioni3;
    }

    public HashSet<ValutazioneTipo4> getValutazioni4() {
        return valutazioni4;
    }

    public void setValutazioni4(HashSet<ValutazioneTipo4> valutazioni4) {
        this.valutazioni4 = valutazioni4;
    }

    public class Utente{

        private String id, genere, usoRecSys;
        private int eta, titoloStudio, frequenzaUscite;

        public Utente(String id, String genere, String usoRecSys, int eta, int titoloStudio, int frequenzaUscite) {
            this.id = id;
            this.genere = genere;
            this.usoRecSys = usoRecSys;
            this.eta = eta;
            this.titoloStudio = titoloStudio;
            this.frequenzaUscite = frequenzaUscite;
        }

        public Utente(){

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGenere() {
            return genere;
        }

        public void setGenere(String genere) {
            this.genere = genere;
        }

        public String getUsoRecSys() {
            return usoRecSys;
        }

        public void setUsoRecSys(String usoRecSys) {
            this.usoRecSys = usoRecSys;
        }

        public int getEta() {
            return eta;
        }

        public void setEta(int eta) {
            this.eta = eta;
        }

        public int getTitoloStudio() {
            return titoloStudio;
        }

        public void setTitoloStudio(int titoloStudio) {
            this.titoloStudio = titoloStudio;
        }

        public int getFrequenzaUscite() {
            return frequenzaUscite;
        }

        public void setFrequenzaUscite(int frequenzaUscite) {
            this.frequenzaUscite = frequenzaUscite;
        }
    }

    public class ValutazioneTipo1_2 {

        protected String id, configurazione, citta;
        protected int locale, numeroContesti, trasparenza, persuasione, coinvolgimento, fiducia;
        protected ArrayList<Integer> listaContesti;

        public ValutazioneTipo1_2(String id, String configurazione, String citta, int locale, int numeroContesti, int trasparenza, int persuasione, int coinvolgimento, int fiducia, ArrayList<Integer> listaContesti) {
            this.id = id;
            this.configurazione = configurazione;
            this.citta = citta;
            this.locale = locale;
            this.numeroContesti = numeroContesti;
            this.trasparenza = trasparenza;
            this.persuasione = persuasione;
            this.coinvolgimento = coinvolgimento;
            this.fiducia = fiducia;
            this.listaContesti = listaContesti;
        }

        public ValutazioneTipo1_2() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConfigurazione() {
            return configurazione;
        }

        public void setConfigurazione(String configurazione) {
            this.configurazione = configurazione;
        }

        public String getCitta() {
            return citta;
        }

        public void setCitta(String citta) {
            this.citta = citta;
        }

        public int getLocale() {
            return locale;
        }

        public void setLocale(int locale) {
            this.locale = locale;
        }

        public int getNumeroContesti() {
            return numeroContesti;
        }

        public void setNumeroContesti(int numeroContesti) {
            this.numeroContesti = numeroContesti;
        }

        public int getTrasparenza() {
            return trasparenza;
        }

        public void setTrasparenza(int trasparenza) {
            this.trasparenza = trasparenza;
        }

        public int getPersuasione() {
            return persuasione;
        }

        public void setPersuasione(int persuasione) {
            this.persuasione = persuasione;
        }

        public int getCoinvolgimento() {
            return coinvolgimento;
        }

        public void setCoinvolgimento(int coinvolgimento) {
            this.coinvolgimento = coinvolgimento;
        }

        public int getFiducia() {
            return fiducia;
        }

        public void setFiducia(int fiducia) {
            this.fiducia = fiducia;
        }

        public ArrayList<Integer> getListaContesti() {
            return listaContesti;
        }

        public void setListaContesti(ArrayList<Integer> listaContesti) {
            this.listaContesti = listaContesti;
        }

    } public class ValutazioneTipo3 extends ValutazioneTipo1_2 {

        protected int preferenza0;

        public ValutazioneTipo3(String id, String configurazione, String citta, int locale, int numeroContesti, int trasparenza, int persuasione, int coivolgimento, int fiducia, ArrayList<Integer> listaContesti, int preferenza0) {
            super(id, configurazione, citta, locale, numeroContesti, trasparenza, persuasione, coivolgimento, fiducia, listaContesti);
            this.preferenza0 = preferenza0;
        }

        public ValutazioneTipo3(){

        }

        public int getPreferenza0() {
            return preferenza0;
        }

        public void setPreferenza0(int preferenza0) {
            this.preferenza0 = preferenza0;
        }
    }

    public class ValutazioneTipo4 extends ValutazioneTipo3 {

        private int preferenzaPrecedente;

        public ValutazioneTipo4(String id, String configurazione, String citta, int locale, int numeroContesti, int trasparenza, int persuasione, int coivolgimento, int fiducia, ArrayList<Integer> listaContesti, int preferenza0, int preferenzaPrecedente) {
            super(id, configurazione, citta, locale, numeroContesti, trasparenza, persuasione, coivolgimento, fiducia, listaContesti, preferenza0);
            this.preferenzaPrecedente = preferenzaPrecedente;
        }

        public ValutazioneTipo4(){

        }

        public int getPreferenzaPrecedente() {
            return preferenzaPrecedente;
        }

        public void setPreferenzaPrecedente(int preferenzaPrecedente) {
            this.preferenzaPrecedente = preferenzaPrecedente;
        }
    }

    public String valorePercentuale(int valore, int totale){

        String percentuale = "";

        float v = (float)valore/totale*100;
        percentuale = String.format("%.2f", v) + "%";

        return percentuale;

    }

    public String valorePercentuale(double valore){

        String percentuale = String.format("%.2f", valore*100) + "%";
        return percentuale;

    }

    public String valoreDecimale(double valore){

        String decimale = String.format("%.2f", valore);
        return decimale;
    }
}
