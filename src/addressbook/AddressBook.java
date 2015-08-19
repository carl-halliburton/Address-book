//-----------------------------------------------------------------------------
/* Address book project for Assigment 2 IT7374 - Programming III
 * Bachelor of Imformation Technology 2015
 * Description:
 * A simple addressbook application that allows a user to save the contact
 * details people, contacts are stored on disk.
 * They can be edited, deleted and exported to XML or imported from XML
 */
package addressbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @project Address Book Project
 * @projectVersion 1.1
 * @classVersion 1.0
 * @author Carl Halliburton
 */
//-----------------------------------------------------------------------------
public class AddressBook {
    
    private final XmlReadWrite xml;
    private ArrayList<Contact> contactList;
    private final String file;
    private boolean importTypeStatus;
    private org.jdom2.Document doc;
   
   
    public AddressBook() {
        xml = new XmlReadWrite();  
        contactList = new ArrayList<>();
        file = "ContactList.dat";
    }
    
    public void setContact(String newFname, String newMname, String newLname, 
                           String newEmail, String newMobile, String newHome, 
                           String newStreet, String newSuburb, String newCity,
                           String newRegion, String newCountry, 
                           String newPostCode) {
        contactList.add(new Contact(newFname, newMname, newLname, 
                           newEmail, newMobile, newHome, newStreet, newSuburb, 
                           newCity, newRegion, newCountry, newPostCode ));        
        
        sortContactList(); //sort arraylist
    }
       
    public Contact getContact(int index) {
        return contactList.get(index);
    }
    
    public int getContactListSize() {
        return contactList.size();
    }
    
    public String updateCbxContactList(int index) {
        return contactList.get(index).getLastName() + 
               ", " + contactList.get(index).getFirstName();
    }
    
    public void deleteContact(int index) {
        contactList.remove(index);
    }
    
    public boolean isListEmpty() {
        return contactList.isEmpty();
    }
    
    public ArrayList getContactList() { 
        return contactList;
    }
    
    public void sortContactList() { 
        Collections.sort(contactList);
    } 
//-----------------------------------------------------------------------------
//Save Addressbook (seriouslize)
    public void saveContactList() {   
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(contactList);
            out.close();
            fileOut.close();
         } catch(Exception e) {
             e.printStackTrace(System.out);
         }  
    }
//-----------------------------------------------------------------------------
//Open ContactList (Deserialization)
    public void openContactList() {
        if (new File(file).exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                contactList = (ArrayList<Contact>) in.readObject();
                in.close();
                fileIn.close();
            }catch(Exception e){
             e.printStackTrace(System.out);  
            }
        }
    }    
    
//Export and Import Xml
//-----------------------------------------------------------------------------   
    public void exportAsXml() {
        doc = xml.writeToXml(contactList);
    }
    
    public org.jdom2.Document getDocument() {
        return doc;
    }
    
    public void importFromXml(String xmlFileName, boolean importOpStat) {
        ArrayList<Contact> tempList = new ArrayList();
        tempList = xml.readFromXml(xmlFileName);
 
        if (importOpStat == true) //append
            appendToContactList(tempList);
        else
            contactList = tempList; //overwrite
    }
 
    public void appendToContactList(ArrayList<Contact> tempList) {
        for (Contact cont : tempList ) {
            contactList.add(cont);
        }
    }
}
//-----------------------------------------------------------------------------