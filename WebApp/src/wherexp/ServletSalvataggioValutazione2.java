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

@WebServlet("/salvaValutzione2")
public class ServletSalvataggioValutazione2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {

        String tempo = request.getParameter("tempo").trim();

        Scanner report = new Scanner(new File("filesWherEXP/temp/report"+tempo+".txt"), "UTF-8");
        String reportValutazione = report.nextLine();
        report.close();

        String pref1 = request.getParameter("pref1");
        String pref2 = request.getParameter("pref2");
        String pref3 = request.getParameter("pref3");
        String pref4 = request.getParameter("pref4");

        String output = "\n"+reportValutazione+";"+pref1+";"+pref2+";"+pref3+";"+pref4;

        String url = "pagine/results3.jsp?tempo="+tempo;

        Files.write(Paths.get("filesWherEXP/valutazione2.txt"), output.getBytes(), StandardOpenOption.APPEND);

        System.out.println(output);
        System.out.println("Salvati dati valutazione 2");

        response.sendRedirect(url);
    }
}
