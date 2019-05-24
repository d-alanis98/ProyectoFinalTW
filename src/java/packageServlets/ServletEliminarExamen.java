/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageServlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Damian Alanis
 */
public class ServletEliminarExamen extends HttpServlet {

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out=response.getWriter(); 
        ServletContext application=this.getServletContext();
        HttpSession session=request.getSession();
        String agregarPreguntas = (String)session.getAttribute("agregarPreguntas");
        String titulo=(String)request.getParameter("exam"); //Recuperamos que titulo de la rpegunta era desde el formulario
        if(titulo == null) titulo = (String)session.getAttribute("exam");
        String usuario = (String)session.getAttribute("usr");
        String ruta = application.getRealPath("/")+"/XML/Examenesxml/" + usuario + ".xml";
        File writer=new File(ruta);        
            try{
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(writer);
            Element raiz = doc.getRootElement();
            List preguntas = raiz.getChildren("examen");
            
            for ( int i = 0; i < preguntas.size(); i++ ){
                Element e = (Element) preguntas.get(i);
                if(e.getChildText("nombre").equals(titulo)){
                    //e.getParentElement().removeChild("Pregunta");
                    e.detach();
                    break;   
                }
            }
                
             try (FileOutputStream fos = new FileOutputStream(getServletContext().getRealPath("/")+"/XML/Examenesxml/" + usuario + ".xml")) {
                XMLOutputter xmlout = new XMLOutputter(Format.getPrettyFormat());
                xmlout.output(doc, fos);
            }
            
            } catch ( IOException io ) {
                System.out.println( io.getMessage() );
            } catch ( JDOMException jdomex ) {
                System.out.println( jdomex.getMessage() ); 
            }     
            if(agregarPreguntas != null){
                if(agregarPreguntas == "true"){
                    response.sendRedirect("EligeExamen");
                    session.setAttribute("tituloModify", titulo);
                    session.setAttribute("agregarPreguntas", null);
                }
                else{
                    response.sendRedirect("PaginaProfesorExamenes");
                    session.setAttribute("tituloModify", null);
                }
            }
            
            else{
                session.setAttribute("tituloModify", null);
                response.sendRedirect("PaginaProfesorExamenes");
            }
        
}

}
