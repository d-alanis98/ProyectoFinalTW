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
import java.util.ArrayList;
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
public class ServletPreguntaExamen extends HttpServlet {

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out=response.getWriter(); 
        ServletContext application=this.getServletContext();
        HttpSession session=request.getSession();
        String titulo=(String)request.getParameter("item"); //nombre de la pregunta
        String nombreP=(String)request.getParameter("exam"); //nombre del examen
        String usuario = (String)session.getAttribute("usr");
        String ruta = application.getRealPath("/")+"/XML/Examenesxml/" + usuario + ".xml";
        File writer=new File(ruta);
        
        
        Element opcionaux;
        List<Element> optionList = new ArrayList<Element>();
        
            try{
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(writer);
            Element raiz = doc.getRootElement();
            List examenes = raiz.getChildren("examen");
            
           for ( int i = 0; i < examenes.size(); i++ ){
                Element e = (Element) examenes.get(i);
                if(e.getChildText("nombre").equals(nombreP)){
                   opcionaux = (Element)e.getChild("preguntas");
                   optionList = opcionaux.getChildren("pregunta");
                   for(int j = 0; j < optionList.size(); j++){
                        Element option = (Element)optionList.get(j);
                        if(option.getValue().equals(titulo)){
                            option.detach();
                            break;
                        }
                    }
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
             
            response.sendRedirect("PaginaProfesorExamenes");
        
}

}
