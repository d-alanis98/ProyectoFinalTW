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


public class PaginaProfesor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        List<Element> preguntas;
        String nombrePregunta = "";
        Preguntas getter = new Preguntas(getServletContext().getRealPath("/")+"/XML/Preguntasxml/" + userName + ".xml");
        preguntas = getter.getPreguntas(userName);
	PrintWriter out=response.getWriter();
        session.setAttribute("usr", userName);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Preguntas</title>");
            out.println("<link rel=\"stylesheet\" href=\"App.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">"
                    );

            out.println("</head>");
            out.println("<body>");
            out.println("<script type=\"text/javascript\" >" + 
                "function eliminar(){ alert(" + nombrePregunta + ");\n" + 
                    "var loc = window.location.href;" + 
                    "var head = loc.split('/', 3);" +
                    "var opcion = confirm(\"Desea eliminar pregunta? \");\n" +
                    "if (opcion == true) {\nconsole.log('Pregunta eliminada');" +
                        "window.location.replace('http://' + head[2] + '/TWProyectoFinal/ServletEliminar?item=" + nombrePregunta + "');" +
                    "} else {" + 
                        "alert(\"No se elimino pregunta\");\n" +
                    "}\n" +
                "}"+ 
            "</script>");
            out.println("<form name ='form'><input type = 'hidden' name='n' value='" + preguntas.size() + "'/></form><div id='Navigation'></div>");
            out.println("<div class=\"App\">");
            out.println("<div class='card bg-transparent border-0'>");
            out.println("<h2 class='card-header bg-secondary text-light rounded-top ml-5 mr-5 mt-5'>Preguntas de "+userName+"!</h2>");
            out.println("<div class='card-body bg-light ml-5 mr-5 rounded-bottom'>");
            out.println("<a href='EligePregunta' class='btn btn-primary btn-lg'>Crear nueva pregunta</a>");
            out.println("<br/><br/><Strong>Listado</Strong>");
            out.println("<br/><br/><table class= 'table table-striped table-bordered  table-hover'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Nombre</td>");
            out.println("<td>Texto</td>");
            out.println("<td>Opciones</td>");
            out.println("</tr>");
            out.println("</thead>");
            try {
                if(preguntas.size() == 0){
                    out.println("<tr>");
                    out.println("<td colspan=\"3\" class='align-middle'><h5>No hay preguntas que mostrar</h5></td>");
                    out.println("</tr>");
                }
                else for (int i = 0; i < preguntas.size(); i++) {
                out.println("<tr>");
                out.println("<td class='align-middle'><h5>"+preguntas.get(i).getChildText("nombre")+"</h5></td>");
                out.println("<td class='align-middle'><h5>"+preguntas.get(i).getChildText("texto")+"</h5></td>");
                nombrePregunta = preguntas.get(i).getChildText("nombre");
                
                out.println("<td>" +
                        "<form name='formulario' method='get'>" +
                        "<input type='hidden' name='item' value='" + preguntas.get(i).getChildText("nombre") + "'/>" +
                        "<input type ='submit' value='Modificar' formaction='ServletModificar' onClick='metodo' class='btn btn-info'/><br><br>" +
                        "<input type ='submit' value='Eliminar' formaction='javascript:eliminar()' data-toggle='modal' class='btn btn-danger'/><br><br>" +
                        "<input type ='submit' value='Visualizar' formaction='ServletVisualizar' class='btn btn-success'/>");
                        session.setAttribute("titulo", preguntas.get(i).getChildText("nombre"));
                out.println("</form></td>");
                out.println("</tr>");
                }
            } catch (Exception e) {
                out.println("<tr>");
                out.println("<td>No se encontro ninguna pregunta</td>");
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
                        out.println("        <script type=\"text/babel\" src=\"Navigation.js\"></script>\n <script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n");
            out.println("</body>");
            out.println("</html>");
    }
}