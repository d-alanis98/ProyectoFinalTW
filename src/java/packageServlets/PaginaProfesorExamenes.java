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


public class PaginaProfesorExamenes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        List<Element> examenes;
        Examenes getter = new Examenes(getServletContext().getRealPath("/")+"/XML/Examenesxml/" + userName + ".xml");
        examenes = getter.getExamenes(userName);
	PrintWriter out=response.getWriter();
        session.setAttribute("usr", userName);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Examenes</title>");
            out.println("<link rel=\"stylesheet\" href=\"App.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">"
                    );

            out.println("</head>");
            out.println("<body>");
            out.println("<script type=\"text/javascript\" >" + 
                "function eliminar(){ \n" + 
                    "var opcion = confirm(\"Desea eliminar pregunta?\");\n" +
                    "if (opcion == true) {\nconsole.log('Pregunta eliminada');" +
                        "document.forms[1].action='ServletEliminarExamen';" + 
                        "document.forms[1].submit();\n" +
                    "} else {" + 
                        "document.formulario.action='';" +
                        "alert(\"No se elimino pregunta\");\n" +
                    "}\n" +
                "}"+ 
            "</script>");
            out.println("<form name ='form'><input type = 'hidden' name='n' value='" + examenes.size() + "'/></form><div id='NavigationExam'></div>");
            out.println("<div class=\"App\">");
            //out.println("tam "+examenes.size());
            out.println("<div class='card bg-transparent border-0'>");
            out.println("<h2 class='card-header bg-secondary text-light rounded-top ml-5 mr-5 mt-5'>Examenes de "+userName+"!</h2>");
            out.println("<div class='card-body bg-light ml-5 mr-5 rounded-bottom'>");
            out.println("<a href='EligeExamen' class='btn btn-primary btn-lg'>Crear nuevo examen</a>");
            out.println("<br/><br/><Strong>Listado</Strong>");
            out.println("<br/><br/><table class= 'table table-striped table-bordered  table-hover'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Nombre</td>");
            out.println("<td>Opciones</td>");
            out.println("</tr>");
            out.println("</thead>");
            try {
                if(examenes.size() == 0){
                    out.println("<tr>");
                    out.println("<td colspan=\"3\" class='align-middle'><h5>No hay examenes que mostrar</h5></td>");
                    out.println("</tr>");
                }
                else for (int i = 0; i < examenes.size(); i++) {
                out.println("<tr>");
                out.println("<td class='align-middle'><h5>"+examenes.get(i).getChildText("nombre")+"</h5></td>");
                
                out.println("<td>" +
                        "<form name='formulario' method='get'>" +
                        "<input type='hidden' name='exam' value='" + examenes.get(i).getChildText("nombre") + "'/>" +
                        "<input type ='submit' value='Modificar' formaction='ServletModificarExamen' onClick='metodo' class='btn btn-info'/><br><br>" +
                        "<input type ='submit' value='Eliminar' formaction='javascript:eliminar()' class='btn btn-danger'/><br><br>" +
                        "<input type ='submit' value='Visualizar' formaction='ServletVisualizarExamen' class='btn btn-success'/>");
                        session.setAttribute("tituloExamen", examenes.get(i).getChildText("nombre"));
                out.println("</form></td>");
                out.println("</tr>");
                }
            } catch (Exception e) {
                out.println("<tr>");
                out.println("<td>No se encontro ningun examen</td>");
                out.println("</tr>");
            }
        out.println("</table>");
            out.println("<break><break>");  
            out.println("</div>");
            out.println("<break><br><br>");
            
            out.println("<a href='ServletOpciones'><img src='arrow-back-icon.png' height='50' width='50'/></a><br><br><br>");
            out.println("<a href='index.html'><img src='logout.png' height='50' width='50'/></a><br><br>");
            out.println("<break><br>");
            out.println("</div>");
            out.println("</div>");
                        out.println("        <script type=\"text/babel\" src=\"NavigationExam.js\"></script>\n <script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n");
            out.println("</body>");
            out.println("</html>");
    }
}