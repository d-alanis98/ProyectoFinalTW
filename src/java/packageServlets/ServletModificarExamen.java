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
public class ServletModificarExamen extends HttpServlet {

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out=response.getWriter(); 
        ServletContext application=this.getServletContext();
        HttpSession session=request.getSession();
        String titulo=(String)request.getParameter("exam"); //Recuperamos que titulo del examen desde el formulario
        String usuario = (String)session.getAttribute("usr");
        String ruta = application.getRealPath("/")+"/XML/Examenesxml/" + usuario + ".xml";
        session.setAttribute("userName", usuario);
        session.setAttribute("usr", usuario);
        session.setAttribute("exam", titulo);
        session.setAttribute("agregarPreguntas", "true");
        List<String> nombres = new ArrayList<String>();
        List<Element> optionList = new ArrayList<Element>();
         int n = 0;
         //int prueba=0;
         //List prue= new ArrayList<Element>();;
         //Element a;
       // List<Element> optionList = new ArrayList<Element>();
        //Logica para obtener los examenes del xml
        File writer=new File(ruta); 
        Element opcionaux;
        
        try{
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(writer);
            Element raiz = doc.getRootElement();
            List examenes = raiz.getChildren("examen");
            try{
            if(!examenes.isEmpty()){
            for(int i=0;i< examenes.size();i++){
                Element e = (Element) examenes.get(i);
                if(e.getChildText("nombre").equals(titulo)){
                    opcionaux = (Element)e.getChild("preguntas");
                    optionList = opcionaux.getChildren("pregunta");
                    for(int j = 0; j < optionList.size(); j++){
                        Element option = (Element)optionList.get(j);
                        nombres.add(j,option.getText());
                    }
                }
                //nombres.add(i, e.getChild("nombre").getText());
            }
            }
        }catch(Exception e){
                System.out.println(e);
            }
        }catch ( IOException io ) {
            System.out.println( io.getMessage() );
        }catch ( JDOMException jdomex ) {
                System.out.println( jdomex.getMessage() ); 
        }   
        //a =(Element)prue.get(1);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"App.css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" crossorigin=\"anonymous\">"
                    );
        out.println("</head>");
        out.println("<body>");
        
            out.println("<div id='NavigationExam'></div><br/><div class='App'>");
            //out.println("tam "+nombres.size()+"titulo "+titulo+"prueba "+prueba+"Elemento "+a.getChildText("nombre")+"listaop ");
            out.println("<form name='eliminarExamen' method='get'>");
            for(int i=0;i<nombres.size();i++){
                out.println("<input type='hidden' name='NombreExamen"+(i+1)+"' value='"+nombres.get(i)+"'/>");
                n = i + 1;
            }
            out.println("<input type='hidden' name='title' value='"+titulo+"'>"
                    + "<input type='hidden' name='exam' value='"+titulo+"'>");
            out.println("<input type='hidden' name='n' value='" + n + "'/>");

            out.println("</form>");
            out.println("<div id='ExamenEliminar'> </div>");
            out.println("<form name='modifyExam' method='get'>"
                    + "<input type='hidden' name='agregarPreguntas' value='true'/>"
                    + "<input type='submit' value='Agregar preguntas' class='btn btn-info' formaction='ServletEliminarExamen'/><br/><br/>"
                    + "</form>");
            //out.println("<div id='ComponentExamn'> </div>");
            out.println("<a href='PaginaProfesorExamenes'><img src='arrow-back-icon.png' height='50' width='50'/></a><br><br><br>");
            out.println("<a href='index.html'><img src='logout.png' height='50' width='50'/></a><br><br>");
            out.println("</div>");
            out.println("<script type='text/babel' src='NavigationExam.js'></script>");
            
            out.println("<script type='text/babel' src='ExamenEliminar.js'></script>");
            out.println("<script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
"        <script src=\"https://unpkg.com/babel-standalone@6/babel.min.js\"></script>\n");
        out.println("</body>");
        out.println("</html>");
    }
}