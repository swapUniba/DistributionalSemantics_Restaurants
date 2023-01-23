package wherexp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

@WebServlet("/salvaValutzione3")
public class ServletSalvataggioValutazione3 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        String tempo = request.getParameter("tempo").trim();

        Scanner report = new Scanner(new File("filesWherEXP/temp/report"+tempo+".txt"), "UTF-8");
        String reportValutazione = report.nextLine();
        report.close();

        String pref0 = request.getParameter("pref0");
        String pref1 = request.getParameter("pref1");
        String pref2 = request.getParameter("pref2");
        String pref3 = request.getParameter("pref3");
        String pref4 = request.getParameter("pref4");

        String output = "\n"+reportValutazione+";"+pref0+";"+pref1+";"+pref2+";"+pref3+";"+pref4;

        String url = "pagine/results4.jsp?tempo="+tempo+"&pref="+pref0;

        Files.write(Paths.get("filesWherEXP/valutazione3.txt"), output.getBytes(), StandardOpenOption.APPEND);

        System.out.println(output);
        System.out.println("Salvati dati valutazione 3");

        response.sendRedirect(url);
    }
}
