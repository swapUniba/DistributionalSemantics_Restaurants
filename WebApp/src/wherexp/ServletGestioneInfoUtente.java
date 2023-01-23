package wherexp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@WebServlet("/infoUtente")
public class ServletGestioneInfoUtente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        File f;
        Long tempo;

        do{

            tempo = System.currentTimeMillis();
            f = new File("filesWherEXP/temp/utente"+tempo+".txt");

        } while (f.exists());

        PrintWriter utente = new PrintWriter(f, "UTF-8");
        utente.flush();
        utente.close();

        String stringaTempo = tempo+"";

        String eta = req.getParameter("eta");
        String genere = req.getParameter("genere");
        String titolo = req.getParameter("titoloStudio");
        String frequenza = req.getParameter("frequenza");
        String recSys = req.getParameter("recSys");

        String output = "\n"+tempo+";"+eta+";"+genere+";"+titolo+";"+frequenza+";"+recSys;
        System.out.println("Info utente:\n"+output);

        Files.write(Paths.get("filesWherEXP/utenti.txt"), output.getBytes(), StandardOpenOption.APPEND);

        System.out.println("Salvati dati utente");

        String url = "pagine/sceltaContesti.jsp?&tempo="+stringaTempo;
        resp.sendRedirect(url);

    }
}
