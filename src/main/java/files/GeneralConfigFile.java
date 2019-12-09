package files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.ContextOperations;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class GeneralConfigFile {
    private HashMap<String, String> map;
    public GeneralConfigFile (ServletContext context) {
        String pathToConfig = ContextOperations.getPathToRoot(context.getRealPath("")) + "\\src\\main\\webapp\\WEB-INF\\admin\\config.xml";
        this.map = readConfigFile(context, pathToConfig);
    }
    public HashMap<String, String> getMap() {
        return map;
    }
    public HashMap<String, String> readConfigFile(ServletContext context, String pathToConfig){
        HashMap<String, String> configuration = new HashMap<>();
        try {
            File fXmlFile = new File(pathToConfig);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Element root = doc.getDocumentElement();
            NodeList nList = root.getElementsByTagName("singleConf");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    configuration.put(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("value").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            System.out.println("Error while getting data config.xml; GeneralConfigFile.readConfigFile() ---> " + e.getMessage());
        } finally {
            return configuration;
        }
    }
    public void updateConfigFile(ServletContext context, HashMap<String, String> configuration, String pathToConfig){
        try {
            InputStream fXmlFile = context.getResourceAsStream("/WEB-INF/admin/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            Element root = doc.getDocumentElement();
            NodeList nList = root.getElementsByTagName("singleConf");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    eElement.getElementsByTagName("value").item(0).setTextContent(configuration.get(eElement.getElementsByTagName("name").item(0).getTextContent()));
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File file = new File (pathToConfig);
            System.out.println(file.getPath());
            FileOutputStream outStream = new FileOutputStream(file);
            StreamResult result = new StreamResult(outStream);
            transformer.transform(source, result);
        } catch (Exception ex) {
            System.out.println("An error occurred while updating settings.xml file; GeneralConfigFile.updateConfigFile()" + ex);
        }
    }
}