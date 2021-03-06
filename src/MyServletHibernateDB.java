import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Game;
import util.UtilDB;

@WebServlet("/MyServletHibernateDB")
public class MyServletHibernateDB extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<Game> listGames = UtilDB.listGames();
      for (Game game : listGames) {
         System.out.println("[DBG] " + game.getId() + ": " //List games to the console.
                 + game.getName() + ", " //
                 + game.getReleasedate() + ", "
                 + game.getGamesystem() + ", "
                 + game.getPublisher() + ", "
                 + "$" + game.getPrice() + ", "
                 + game.getRegion());

         out.println("<li>" + game.getId() + ", " //List games to the web-page.
                 + game.getName() + ", " //
                 + game.getReleasedate() + ", "
                 + game.getGamesystem() + ", "
                 + game.getPublisher() + ", "
                 + "$" + game.getPrice() + ", "
                 + game.getRegion() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
