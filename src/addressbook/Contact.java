//-----------------------------------------------------------------------------
/*
 * An individual contact object containing all the contact information, which
 * then stored and sorted alphabetically in an arraylist.
 */
//-----------------------------------------------------------------------------
package addressbook;

import java.io.Serializable;

/**
 * @project Address Book Project
 * @projectVersion 1.1
 * @classVersion 1.0
 * @author Carl Halliburton
 */
//-----------------------------------------------------------------------------
public class Contact implements Comparable<Contact>, Serializable {
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String street;
    private String suburb;
    private String city;
    private String region;
    private String country;
    private String postCode;
    
    public Contact(){
    }
    
    public Contact(String newFname, String newMname, String newLname, 
                   String newEmail, String newMobilePhone, String newHomePhone, 
                   String newStreet, String newSuburb, String newCity,
                   String newRegion, String newCountry, String newPostCode) {
        firstName = newFname;
        middleName = newMname;
        lastName = newLname;
        email = newEmail;
        mobilePhone = newMobilePhone;
        homePhone = newHomePhone;
        street = newStreet;
        suburb = newSuburb;
        city = newCity;
        region = newRegion;
        country = newCountry;
        postCode = newPostCode;
    }
    
    @Override
    public int compareTo(Contact nextContact) {
        return lastName.compareTo(nextContact.lastName);
    }
  
    public void setFirstName(String newFname) {
        firstName = newFname;
    }
   
    public String getFirstName() {
        return firstName;
    }
    
    public void setMiddleName(String newMname) {
        middleName = newMname;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setLastName(String newLname) {
        lastName = newLname;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setMobilePhone(String newMobilePhone) {
        mobilePhone = newMobilePhone;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    public void setHomePhone(String newHomePhone) {
        homePhone = newHomePhone;
    }
    
    public String getHomePhone() {
        return homePhone;
    }
    
    public void setStreet(String newStreet) {
        street = newStreet;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setSuburb(String newSuburb) {
        suburb = newSuburb;
    }
    
    public String getSuburb() {
        return suburb;
    }
    
    public void setCity(String newCity) {
        city = newCity;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setRegion(String newRegion) {
        region = newRegion;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setCountry(String newCountry) {
        country = newCountry;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setPostCode(String newPostCode) {
        postCode = newPostCode;
    }
    
    public String getPostCode() {
        return postCode;
    }
}
//-----------------------------------------------------------------------------