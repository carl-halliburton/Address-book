//-----------------------------------------------------------------------------
/*
 * Manages the exporting of xml documents and importing from xml
 * NOTE: there is not validation of the XML document before importation takes place
 */
package addressbook;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Element;
import org.xml.sax.SAXException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.Namespace;
import org.w3c.dom.Document;

/**
 * @project Address Book Project
 * @projectVersion 1.1
 * @classVersion 1.0
 * @author Carl Halliburton
 */
//-----------------------------------------------------------------------------
public class XmlReadWrite {
 
    public XmlReadWrite() {
    }
    
    public org.jdom2.Document writeToXml(ArrayList<Contact> contactList) {
        int id = 0;
        org.jdom2.Document doc = new org.jdom2.Document();
        doc.setRootElement(new Element("contactList", Namespace.getNamespace("http://www.w3.org/2001/XMLSchema")));
        try { 
            for(Contact cont : contactList){
                Element contact = new Element("contact");
                contact.setAttribute("id","" + id);
                contact.addContent(new Element("firstName").setText
                                                        (cont.getFirstName()));
                contact.addContent(new Element("middleName").setText
                                                       (cont.getMiddleName()));
                contact.addContent(new Element("lastName").setText
                                                         (cont.getLastName()));
                contact.addContent(new Element("email").setText
                                                            (cont.getEmail()));
                contact.addContent(new Element("mobilePhone").setText
                                                      (cont.getMobilePhone()));
                contact.addContent(new Element("homePhone").setText
                                                        (cont.getHomePhone()));
                doc.getRootElement().addContent(contact);
                
                Element address = new Element("address");
                address.addContent(new Element("street").setText
                                                           (cont.getStreet()));
                address.addContent(new Element("suburb").setText
                                                           (cont.getSuburb()));
                address.addContent(new Element("city").setText
                                                             (cont.getCity()));
                address.addContent(new Element("region").setText
                                                           (cont.getRegion()));
                address.addContent(new Element("country").setText
                                                          (cont.getCountry()));
                address.addContent(new Element("postcode").setText
                                                         (cont.getPostCode()));
                doc.getRootElement().addContent(address);
                id++;
            }     
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return doc;
    }
//-----------------------------------------------------------------------------   
    public ArrayList readFromXml(String fileName) {
        ArrayList<Contact> tempList = new ArrayList();
        
        org.jdom2.Document jdomDoc;
        try {
            //we can create JDOM Document from DOM, SAX and
            //STAX Parser Builder classes
            jdomDoc = useDOMParser(fileName);
            Element root = jdomDoc.getRootElement();
            List<Element> contListElements = root.getChildren("contact"); 
            List<Element> addListElements = root.getChildren("address"); 
            for (Element contElement : contListElements) {
                Contact cont = new Contact();
                cont.setFirstName(contElement.getChildText("firstName"));
                cont.setMiddleName(contElement.getChildText("middleName"));
                cont.setLastName(contElement.getChildText("lastName"));
                cont.setEmail(contElement.getChildText("email"));
                cont.setMobilePhone(contElement.getChildText("mobilePhone"));
                cont.setHomePhone(contElement.getChildText("homePhone"));
                //get address
                for (Element addElement : addListElements) {
                    cont.setStreet(addElement.getChildText("street"));
                    cont.setSuburb(addElement.getChildText("suburb"));
                    cont.setCity(addElement.getChildText("city"));
                    cont.setRegion(addElement.getChildText("region"));
                    cont.setCountry(addElement.getChildText("country"));
                    cont.setPostCode(addElement.getChildText("postcode"));
                }
                tempList.add(cont);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } 
        return tempList;
    }
    
    //Get JDOM document from DOM Parser
    private static org.jdom2.Document useDOMParser(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        //creating DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);
    }
}
//-----------------------------------------------------------------------------