package packageServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class CrearXMLNuevo extends HttpServlet {
    /* Se crean los elementos y atributos de la pregunta en el XML user.xml */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
            int k=0;
            HttpSession session=request.getSession();
            String userName=(String)session.getAttribute("userName");
            String p1=request.getParameter("NombrePregunta");
            String p2=request.getParameter("TextoPregunta");
            for(k=0;k<6;k++){
                if(request.getParameter("Puntos"+(k+1)).equals(""))
                    break;
            }
            String p15=request.getParameter("CantMaxima");
            String p16=request.getParameter("RetroalimentacionCorrecta");
            String p17=request.getParameter("RetroalimentacionIncorrecta");
            String p18=request.getParameter("Tipo");
            String p19=request.getParameter("usr");
            
            SAXBuilder builder = new SAXBuilder();
            ServletContext application=this.getServletContext();
	    File xmlFile = new File(application.getRealPath("/")+"/XML/Preguntasxml/" + userName + ".xml");

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();
            
            Element raiz=new Element("pregunta");
            raiz.setAttribute("tipo",p18);
            raiz.setAttribute("user",p19);
            Element hoja=new Element("nombre"); 
            hoja.setText(p1);
            Element hoja2=new Element("texto");
            hoja2.setText(p2);
            Element hoja3=new Element("respuestas");
            hoja3.setAttribute("cantidadmax",p15);
            Element hoja5=new Element("retroalimentacion");
            Element hoja6=new Element("correcto");
            hoja6.setText(p16);
            Element hoja7=new Element("incorrecto");
            hoja7.setText(p17);
            //Se agregan las respuestas
            for(int j=0;j<k;j++){
               Element hoja4 = new Element("respuesta");
               hoja4.setAttribute("puntos",request.getParameter("Puntos"+(j+1)));
               hoja4.setText(request.getParameter("Respuesta"+(j+1)));
               hoja3.addContent(hoja4);
            } 
            
            raiz.addContent(hoja);
            raiz.addContent(hoja2);
            raiz.addContent(hoja3);
            raiz.addContent(hoja5);
            hoja5.addContent(hoja6);
            hoja5.addContent(hoja7);
            
            rootNode.addContent(raiz);
            
            XMLOutputter fmt=new XMLOutputter(Format.getPrettyFormat());
            FileWriter writer=new FileWriter(application.getRealPath("/")+"/XML/Preguntasxml/" + userName + ".xml");
            fmt.output(doc,writer);
            writer.flush();
            writer.close();
        }
        catch(Exception e)
                {
                    e.printStackTrace();
                }
        response.sendRedirect("PaginaProfesor");
    }

}
