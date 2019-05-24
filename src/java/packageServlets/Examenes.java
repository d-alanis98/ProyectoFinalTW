package packageServlets;

import java.io.File;
import java.util.AbstractList;
import java.util.ArrayList;
import org.jdom2.*;
import java.util.List;
import org.jdom2.input.SAXBuilder;

class Examenes
{
    private List<Element> examen;
    public Examenes(String rute)
    {
        examen = null;
        try
        {
            File inputFile = new File(rute);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            examen = classElement.getChildren();
        } 
	catch (Exception e) 
	{
            e.printStackTrace();
	}
    }
    public List<Element> getExamenes(){
        return examen;
    }
    public List<Element> getExamenes(String userName)
    {
        List<Element> res = new ArrayList<>();
        Element preg;
        try
        {   
            for (int i = 0; i < examen.size(); i++) {
                preg = examen.get(i);
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
