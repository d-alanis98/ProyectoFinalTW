package packageServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EligePregunta extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
	PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Servlet Elige</title>\n" +
"        <link rel=\"stylesheet\" href=\"App.css\">\n" +
"        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">\n" +
"    </head>\n" +
"    <body>\n" + 
                    "        <usr id=\"usuario\" name=\"usuario\" style=\"display: none\">"+userName+"</usr>"+
                            "<div id='Navigation'></div>"+
"        <div class=\"App\">\n" +
                           
"            <header class=\"App-header\">\n" +
"\n" +
"                <div class=\"Delimitador\">\n" +
"                \n" +
"                   <div id=\"PreguntaPartialCredit\"></div>\n" +
"                \n" +
"                </div>\n" +
"\n" +
"              </header>\n" +
"        </div>\n" +
"        \n" +
"        \n" +
"<script type=\"text/babel\" src=\"Navigation.js\"></script>\n" +
                    "<script type=\"text/babel\" src=\"Component.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"Retroalimentacion.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"Opcionespreguntas.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"Pregunta.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"PreguntaPartialCredit.js\"></script>\n" +
"        \n" +
"        <script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n" +
"    </body>\n" +
"</html>\n" +
"");
    }
}
