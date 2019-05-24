package packageServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class fail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
	PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");   
            out.println("<link rel=\"stylesheet\" href=\"App.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='App'>");

            out.println("<header class='App-header'>");
            out.println("<div class='Delimitador'>");
            out.println("<strong>");
            out.println("<div class='text-white'>");
            out.println("Usuario [" + userName + "] no registrado en el XML");
            out.println("<a href='index.html' ><br><br><br><img src='arrow-back-icon.png' height = '50' width = '50'/></a>");  
            
            out.println("</strong>");
            out.println("</div>");
            out.println("</div>");
            out.println("</header>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }
}
