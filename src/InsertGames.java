import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Game;
import util.Info;
import util.UtilDB;

@WebServlet("/InsertGames")
public class InsertGames extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public InsertGames() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name").trim();
      String releasedate = request.getParameter("releasedate").trim();
      String gamesystem = request.getParameter("gamesystem").trim();
      String publisher = request.getParameter("publisher").trim();
      String price = request.getParameter("price").trim();
      String region = request.getParameter("region").trim();
      
      List<Game> listGames = null;
      listGames = UtilDB.listGames(name, releasedate);
      if (listGames.size()  < 1) //If game entered does not exist currently with the given name and release date.
      {
    	  if (name != "" && releasedate != "" && gamesystem != "" && publisher != "" && price != "" && region != "")//Check if any of the fields are empty.
    	  {
        	  UtilDB.createGame(name, releasedate, gamesystem, publisher, price, region);
              response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              String title = "Database Result";
              String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
              out.println(docType + //
                    "<html>\n" + //
                    "<head><title>" + title + "</title></head>\n" + //
                    "<body bgcolor=\"#f0f0f0\">\n" + //
                    "<h1 align=\"center\">" + title + "</h1>\n");
              out.println("<ul>");
              out.println("<li> Name: " + name);
              out.println("<li> Release Date: " + releasedate);
              out.println("<li> Game System: " + gamesystem);
              out.println("<li> Publisher: " + publisher);
              out.println("<li> Price: $" + price);
              out.println("<li> Region: " + region);
              out.println("</ul>");
              out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
              out.println("</body></html>");
    	  }
    	  else //There are incomplete fields. Prompt the user to fill in all the fields.
    	  {
        	  response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              String title = "Error: Not All Fields Filled!";
              String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
              out.println(docType + //
                    "<html>\n" + //
                    "<head><title>" + title + "</title></head>\n" + //
                    "<body bgcolor=\"#f0f0f0\">\n" + //
                    "<h1 align=\"center\">" + title + "</h1>\n");
              out.println("<ul>");
              out.println("<li> 1 or more fields incomplete or missing. Please fill in everything. The following fields are invalid.");
              out.println("<li> Name: " + name);
              out.println("<li> Release Date: " + releasedate);
              out.println("<li> Game System: " + gamesystem);
              out.println("<li> Publisher: " + publisher);
              out.println("<li> Price: $" + price);
              out.println("<li> Region: " + region);
              out.println("</ul>");
              out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
              out.println("</body></html>");
    	  }
      }
      else //The game was already entered.
      {
    	  response.setContentType("text/html");
          PrintWriter out = response.getWriter();
          String title = "Error: Repeated Game!";
          String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
          out.println(docType + //
                "<html>\n" + //
                "<head><title>" + title + "</title></head>\n" + //
                "<body bgcolor=\"#f0f0f0\">\n" + //
                "<h1 align=\"center\">" + title + "</h1>\n");
          out.println("<ul>");
          out.println("<li> The following game has already been recorded and will not be added to the database.");
          out.println("<li> Name: " + name);
          out.println("<li> Release Date: " + releasedate);
          out.println("<li> Game System: " + gamesystem);
          out.println("<li> Publisher: " + publisher);
          out.println("<li> Price: $" + price);
          out.println("<li> Region: " + region);
          out.println("</ul>");
          out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
          out.println("</body></html>");
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
