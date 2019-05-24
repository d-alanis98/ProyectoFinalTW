package packageServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Element;


public class ServletOpciones extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        PrintWriter out=response.getWriter();
        session.setAttribute("usr", userName);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");
            out.println("<link rel=\"stylesheet\" href=\"App.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='Navigation'> </div><div class='App'>");
                out.println("<div class='card bg-transparent border-0'>");
                    out.println("<h2 class='card-header bg-secondary text-light rounded-top ml-5 mr-5 mt-5'>Bienvenido "+userName+"!</h2>");
                    out.println("<br><br><div class='card-body bg-white ml-5 mr-5 rounded-bottom'>");
                        out.println("<a href='PaginaProfesor' class='btn btn-primary btn-lg btn-block'>Ver preguntas</a>");
                        out.println("<br><a href='PaginaProfesorExamenes' class='btn btn-info btn-lg btn-block'>Ver examenes</a>");
                    //out.println("</div>");
                out.println("</div>");
            out.println("</div>");
            out.println("<script type='text/babel' src='Navigation.js'></script>");
                       out.println("<script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n");
            out.println("</body>");
    }
}