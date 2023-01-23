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

@WebServlet("/salvaValutzione4")
public class ServletSalvataggioValutazione4 extends HttpServlet {

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
        String prec = request.getParameter("precedente").trim();

        String output = "\n"+reportValutazione+";"+pref0+";"+pref1+";"+pref2+";"+pref3+";"+pref4+";"+prec;

        String url = "end.jsp";

        Files.write(Paths.get("filesWherEXP/valutazione4.txt"), output.getBytes(), StandardOpenOption.APPEND);

        System.out.println(output);
        System.out.println("Salvati dati valutazione 4");

        response.sendRedirect(url);
    }
}
