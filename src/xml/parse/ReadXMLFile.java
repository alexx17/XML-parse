
package xml.parse;

 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadXMLFile {
    
    static NodeList nList;
    static String parametr_find;
    static Document doc;
     
    public static String PrintX(int i){
        
    
            Node nNode = nList.item(i);
 
		
 
			Element eElement = (Element) nNode;
                       
			
			return ("Name : " + eElement.getElementsByTagName("Name").item(0).getTextContent())+
			("Category : " + eElement.getElementsByTagName("Category").item(0).getTextContent())+
			("Price : " + eElement.getElementsByTagName("Price").item(0).getTextContent());
 
		         
    }
    
    private static ArrayList edit(String name,String value){
    
    name=name.replaceAll("^\\s",""); value=value.replaceAll("^\\s","");
    String[] variables=value.split("=");
   
    NodeList nodes = doc.getElementsByTagName("Dish").item(0).getChildNodes();
  
	            for (int i = 0; i < nodes.getLength(); i++) {
	  
                      Node nNode = nList.item(i);
 
		
 
	              Element eElement = (Element) nNode;
	              Node element = nodes.item(i);
                     
                         
                        
	                if ( (name.equals(eElement.getElementsByTagName("Name").item(0).getTextContent().replaceAll("^\\s","")))&&(variables[0].equals(element.getNodeName())) ) {
                                   element.setTextContent(variables[1]);   }
	                
                    }

	            
     return find(parametr_find);  }             

   
 
  
    
    
    private static ArrayList find(String param){
    ArrayList search=new ArrayList();
   
    for (int temp = 0; temp < nList.getLength(); temp++){
        
    Node nNode = nList.item(temp);
    Element eElement = (Element) nNode;
    String check = eElement.getElementsByTagName("Name").item(0).getTextContent().replaceAll("^\\s","");

    if (param.equals(null)||param.equals("*"))  search.add( PrintX(temp));
    if (check.startsWith(param) && (!param.equals("/*")) ) search.add( PrintX(temp));
    }
    
   return search;
    }
 
    public static void main(String argv[]) {
    try {
        
	File fXmlFile = new File("D:\\java\\XML\\Restaurant.txt");
    
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	doc = dBuilder.parse(fXmlFile);
        nList = doc.getElementsByTagName("Dish");
        
	for (int temp = 0; temp < nList.getLength(); temp++){
        System.out.println(PrintX(temp));}

        System.out.println("Write find <what to find> to search");
        System.out.println("Write edit <Name> <Tag>=<New value>");
        System.out.println("Write Exit to exit");
        
            while(true){
        Scanner scan=new Scanner(System.in);
        String action=scan.next();
        
        if (action.equals("find")){
        parametr_find=scan.next();
        System.out.println(find(parametr_find));
        }
         
        
          if (action.equals("edit")){
        String parametr_name=scan.next(); String parametr_value=scan.next();
        edit(parametr_name,parametr_value);
        }  
       
        if (action.equals("Exit")) break;
            }
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
 
}