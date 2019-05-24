package packageServlets;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import org.jdom2.*;
import java.util.List;
import org.jdom2.input.SAXBuilder;

class Preguntas 
{
    private List<Element> pregunta;
    public Preguntas(String rute)
    {
        pregunta = null;
        try
        {
            File inputFile = new File(rute);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            pregunta = classElement.getChildren();
        } 
	catch (Exception e) 
	{
            e.printStackTrace();
	}
    }
    public List<Element> getPreguntas()
    {
        return pregunta;
    }
    public List<Element> getPreguntas(String userName)
    {
        List<Element> res = new ArrayList<>();
        Element preg;
        try
        {   
            for (int i = 0; i < pregunta.size(); i++) {
                preg = pregunta.get(i);
                if(preg.getAttribute("user").getValue().equals(userName)){
                    res.add(preg);
                }
            }
        } 
	catch (Exception e) 
	{
            e.printStackTrace();
	}
        return res;
    }

}
