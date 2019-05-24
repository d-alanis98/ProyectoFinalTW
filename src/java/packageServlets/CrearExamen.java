package packageServlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;
import java.io.FileWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;

public class CrearExamen extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try {
            HttpSession session=request.getSession();
            String userName=(String)session.getAttribute("userName");
            String p1=request.getParameter("NombreExamen");
            String[] preguntas;
            preguntas= request.getParameterValues("NombrePregunta");
            
            SAXBuilder builder = new SAXBuilder();
            ServletContext application=this.getServletContext();
            File xmlFile = new File(application.getRealPath("/")+"/XML/Examenesxml/" + userName + ".xml");

            Document doc = (Document)builder.build(xmlFile);
            Element rootNode = doc.getRootElement();
            
            Element raiz=new Element("examen");
            raiz.setAttribute("tipo","PartialCredit");
            raiz.setAttribute("user",userName);
            
            Element hoja0=new Element("nombre");
            hoja0.setText(p1);
            raiz.addContent(hoja0);
            
            Element hoja2=new Element("preguntas");
            
           for(int j=0;j< preguntas.length ;j++){
               Element hoja1=new Element("pregunta");
               hoja1.setText(preguntas[j]);
               hoja2.addContent(hoja1);
           }
            raiz.addContent(hoja2);
            rootNode.addContent(raiz);
            
            XMLOutputter fmt=new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer=new FileWriter(application.getRealPath("/")+"/XML/Examenesxml/"+ userName + ".xml");
            fmt.output(doc,writer);
            writer.flush();
            writer.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
       response.sendRedirect("PaginaProfesorExamenes");
    }
}
