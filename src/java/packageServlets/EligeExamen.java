package packageServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Element;


public class EligeExamen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        String tituloAntiguo = (String)session.getAttribute("tituloModify");
        session.setAttribute("agregarPreguntas", null);
	List<Element> preguntas;
        Preguntas getter = new Preguntas(getServletContext().getRealPath("/")+"/XML/Preguntasxml/"+userName+".xml");
        preguntas = getter.getPreguntas(userName);
        PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Servlet Elige</title>");
                    out.println("<link rel='stylesheet' href='App.css'>");
                    out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' crossorigin='anonymous'>");
                out.println("</head>");
                out.println("<body>");
                    out.println("<form action='CrearExamen' name='form'><input type = 'hidden' name='n' value='" + preguntas.size() + "'/><div id='Navigation'></div>");
                    out.println("<div class='App'>");
                       out.println("<div class='card bg-transparent border-0'>");
                        out.println("<h2 class='card-header bg-secondary text-light rounded-top ml-5 mr-5 mt-5'>Bienvenido "+userName+"!</h2>");
                        out.println("<div>");
                        out.println("<div className='card-header bg-secondary text-white'>");
                            out.println("<h4><strong>Nombre del examen</strong></h4>");
                            out.println("<br></br>");
                            out.println("<input type='text' className='form-control bg-light border-0 rounded' name='NombreExamen' onChange={this.HandleOnChange}");
                            if(tituloAntiguo != null){
                                out.println(" value= '" + tituloAntiguo + "' ");
                                session.setAttribute("tituloModify", null);
                            }
                            out.println("/>");
                        out.println("</div>");
                        out.println("<div class='card-body bg-light ml-5 mr-5 rounded-bottom'>");
                            out.println("<br/><br/><Strong>Listado</Strong>");
                            out.println("<br/><br/><table class= 'table table-striped table-bordered  table-hover'>");
                            out.println("<thead>");
                                out.println("<tr>");
                                    out.println("<td>Seleccion</td>");
                                    out.println("<td>Nombre</td>");
                                    out.println("<td>Texto</td>");
                                out.println("</tr>");
                            out.println("</thead>");
                        out.println("</div>");
                    out.println("</div>");
                    try {
                        for(int i=0; i<preguntas.size(); i++) {
                            out.println("<tr>");
                            out.println("<td><div className='card-footer bg-light'> "+
                                            " <input type='checkbox' name='NombrePregunta' value='"+preguntas.get(i).getChildText("nombre")+"'  />"+
                                            " <span class='glyphicon glyphicon-ok'></span>"+
                                    "</div>"+
                            "</td>");
                            out.println("<td>"+preguntas.get(i).getChildText("nombre")+"</td>");
                            out.println("<td>"+preguntas.get(i).getChildText("texto")+"</td>");

                            out.println("</tr>");
                        }
                    } 
                    catch (Exception e) {
                        out.println("<tr>");
                        out.println("<td>No ha creado ninguna pregunta.</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>"); 
                    out.println("<input class='btn btn-success mb-2 btn-block' type='submit' value='Crear examen'/></form>");
                    out.println("</br><a href='PaginaProfesor' class='btn btn-danger mb-2 btn-block'>Cancelar</a>");   
                    out.println("</div>");
                    out.println("</header>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                    
                    
                    
    }
}