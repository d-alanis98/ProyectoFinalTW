package packageServlets;

import java.io.File;
import org.jdom2.*;
import java.util.List;
import org.jdom2.input.SAXBuilder;

class LoginBean 
{
    public LoginBean()
    {
        
    }
    public boolean validateUser(String userName, String password, String rute)
    {
        try
        {
            File inputFile = new File(rute);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element classElement = document.getRootElement();
            List<Element> userList = classElement.getChildren();
            
            for (int i = 0; i < userList.size(); i++) {
                Element usuario = userList.get(i);
                if(usuario.getChild("usr").getText().equals(userName) && usuario.getChild("psw").getText().equals(password)){
                    return true;
                }
            }
        } 
	catch (Exception e) 
	{
            e.printStackTrace();
	}
        return false;
    }

}
