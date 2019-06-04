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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Attribute;
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
public class ServletVisualizar extends HttpServlet {

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out=response.getWriter(); 
        ServletContext application=this.getServletContext();
        HttpSession session=request.getSession();
        String titulo=(String)request.getParameter("item"); //Recuperamos que titulo de la rpegunta era desde el formulario
        String usuario = (String)session.getAttribute("usr");
        String ruta = application.getRealPath("/")+"/XML/Preguntasxml/" + usuario + ".xml";
       String texto = "";
       List<String> puntos = new ArrayList<String>();
       List<String> opciones = new ArrayList<String>();
       String retroCorrecta = "";
       String retroIncorrecta = "";
       String maxElements = "";
       String tipo = "";
    List<Element> optionList = new ArrayList<Element>();
    int n = 0;
        File writer=new File(ruta); 
        Element opcionaux;
            try{
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(writer);
            Element raiz = doc.getRootElement();
            List preguntas = raiz.getChildren("pregunta");
            for ( int i = 0; i < preguntas.size(); i++ ){
                Element e = (Element) preguntas.get(i);
                if(e.getChildText("nombre").equals(titulo)){
                    Attribute _tipo = e.getAttribute("tipo");
                    tipo = _tipo.getValue();
                    texto = e.getChild("texto").getText();
                    opcionaux = (Element)e.getChild("respuestas");
                    Attribute max = (Attribute)opcionaux.getAttribute("cantidadmax");
                    maxElements = max.getValue();
                    optionList = opcionaux.getChildren("respuesta");
                    
                    //Ciclo for para obtener las opciones con sus puntos y vaciarlos en sus respectivos Lists
                    for(int j = 0; j < optionList.size(); j++){
                        Element option = (Element)optionList.get(j);
                        Attribute punto = (Attribute)option.getAttribute("puntos");
                        opciones.add(option.getValue());
                        puntos.add(punto.getValue());
                    }
                    Element retroalimentacion = e.getChild("retroalimentacion");
                    retroCorrecta = retroalimentacion.getChild("correcto").getText();
                    retroIncorrecta = retroalimentacion.getChild("incorrecto").getText();
                    break;   
                }
            }     
            } catch ( IOException io ) {
                System.out.println( io.getMessage() );
            } catch ( JDOMException jdomex ) {
                System.out.println( jdomex.getMessage() ); 
            }         
             

        out.println("<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link rel=\"stylesheet\" href=\"App.css\">"
                + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">"
                + "    </head>\n" +
                " <body><div id='Navigation'></div><div class = 'App'>"
                + "<form name='visualizar'>"
                + "<input type=\"hidden\" name=\"NombrePregunta\" value='" + titulo + "'/>\n" 
                + "<input type=\"hidden\" name=\"MaxElements\" value='" + maxElements + "'/>\n"
                + "<input type=\"hidden\" name=\"RetroCorrecta\" value='" + retroCorrecta + "'/>\n"
                + "<input type=\"hidden\" name=\"RetroIncorrecta\" value='" + retroIncorrecta + "'/>\n"
                + "<input type=\"hidden\" name=\"Tipo\" value='" + tipo + "'/>\n"    
                + "<input type=\"hidden\" name=\"TextoPregunta\" value = '" + texto + "'/> ");
        for(int i = 0; i < opciones.size(); i++){//Ciclo for que pone en el formulario las opciones
            out.println("<input type=\"hidden\" name='Puntos" + (i + 1) + "' value='" + puntos.get(i)+ "'/>\n"
                        + "<input type=\"hidden\" name='Respuesta" + (i + 1) + "' value='" + opciones.get(i)+ "'/>\n");
            n = i + 1;
            //out.println("<br/>Opcion [" + (i + 1) + "] = "+ opciones.get(i) + "  Puntos = " + puntos.get(i));
        }
        out.println("<input type='hidden' name='n' value='" + n + "'/>");
        out.println("</form>");
        out.println("<div id='Component'> </div>");
        out.println("</div>");
out.println("        <script type=\"text/babel\" src=\"Navigation.js\"></script>" +
                                "<script type=\"text/babel\" src=\"PreguntaVisualizer.js\"></script>\n" +
                    "<script type=\"text/babel\" src=\"Component.js\"></script>\n" +

"        <script type=\"text/babel\" src=\"Opcionespreguntas.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"Pregunta.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"PreguntaPartialCredit.js\"></script>\n" +
"        <script type=\"text/babel\" src=\"Retroalimentacion.js\"></script>\n" +
"        \n" +
"        <script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n" +
"    </body>\n" +
"</html>\n" +
"");
    }
}
