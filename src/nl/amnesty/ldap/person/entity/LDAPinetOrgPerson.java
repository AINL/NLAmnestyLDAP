/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.person.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.ldap.entity.LDAP;
import nl.amnesty.ldap.entity.LDAPConnection;
import nl.amnesty.ldap.person.controller.LDAPPersonController;
import nl.amnesty.ldap.person.controller.LDAPPersonOpenDS;
import nl.amnesty.ldap.person.controller.LDAPPersonSunONE;

/**
 *
 * @author evelzen
 */
public class LDAPinetOrgPerson extends LDAPorganizationalPerson {

    // Constants
    public static final String AUDIO = "audio";
    public static final String BUSINESSCATEGORY = "businessCategory";
    public static final String CARLICENSE = "carLicense";
    public static final String DEPARTMENTNUMBER = "departmentNumber";
    public static final String DISPLAYNAME = "displayName";
    public static final String EMPLOYEENUMBER = "employeeNumber";
    public static final String EMPLOYEETYPE = "employeeType";
    public static final String GIVENNAME = "givenName";
    public static final String HOMEPHONE = "homePhone";
    public static final String HOMEPOSTALADDRESS = "homePostalAddress";
    public static final String INITIALS = "initials";
    public static final String JPEGPHOTO = "jpegPhoto";
    public static final String LABELEDURI = "labeledURI";
    public static final String MAIL = "mail";
    public static final String MANAGER = "manager";
    public static final String MOBILE = "mobile";
    public static final String O = "o";
    public static final String PAGER = "pager";
    public static final String PHOTO = "photo";
    public static final String PREFERREDLANGUAGE = "preferredLanguage";
    public static final String ROOMNUMBER = "roomNumber";
    public static final String SECRETARY = "secretary";
    public static final String USERCERTIFICATE = "userCertificate";
    public static final String X500UNIQUEIDENTIFIER = "x500UniqueIdentifier";
    //
    public static final String LDAPADMIN_ID = "secretariaat";
    public static final String LDAPADMIN_EMPLOYEENUMBER = "666";
    public static final String LDAPADMIN_GIVENNAME = "Het";
    public static final String LDAPADMIN_SN = "Secretariaat";
    public static final String LDAPADMIN_TELEPHONENUMBER = "0206264436";
    public static final String LDAPADMIN_MAIL = "amnesty@amnesty.nl";
    //public static final String LDAPADMIN_FACSIMILETELEPHONENUMBER = "0206240889";
    public static final String LDAPADMIN_CN = "Het Secretariaat";
    public static final String LDAPADMIN_USERPASSWORD = "Verb@@sje";
    public static final String LDAPADMIN_POSTALADDRESS = "Keizersgracht 177";
    public static final String LDAPADMIN_POSTALCODE = "1016DR";
    // Attributes
    private String audio;
    private String businesscategory;
    private String carlicense;
    private String departmentnumber;
    private String displayname;
    private String employeenumber;
    private String employeetype;
    private String givenname;
    private String homephone;
    private String homepostaladdress;
    private String initials;
    private String jpegphoto;
    private String labeleduri;
    private String mail;
    private String manager;
    private String mobile;
    private String o;
    private String pager;
    private String photo;
    private String preferredlanguage;
    private String roomnumber;
    private String secretary;
    private String uid;
    private String usercertificate;
    private String x500uniqueidentifier;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getBusinesscategory() {
        return businesscategory;
    }

    public void setBusinesscategory(String businesscategory) {
        this.businesscategory = businesscategory;
    }

    public String getCarlicense() {
        return carlicense;
    }

    public void setCarlicense(String carlicense) {
        this.carlicense = carlicense;
    }

    public String getDepartmentnumber() {
        return departmentnumber;
    }

    public void setDepartmentnumber(String departmentnumber) {
        this.departmentnumber = departmentnumber;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getHomepostaladdress() {
        return homepostaladdress;
    }

    public void setHomepostaladdress(String homepostaladdress) {
        this.homepostaladdress = homepostaladdress;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getJpegphoto() {
        return jpegphoto;
    }

    public void setJpegphoto(String jpegphoto) {
        this.jpegphoto = jpegphoto;
    }

    public String getLabeleduri() {
        return labeleduri;
    }

    public void setLabeleduri(String labeleduri) {
        this.labeleduri = labeleduri;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPreferredlanguage() {
        return preferredlanguage;
    }

    public void setPreferredlanguage(String preferredlanguage) {
        this.preferredlanguage = preferredlanguage;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsercertificate() {
        return usercertificate;
    }

    public void setUsercertificate(String usercertificate) {
        this.usercertificate = usercertificate;
    }

    public String getX500uniqueidentifier() {
        return x500uniqueidentifier;
    }

    public void setX500uniqueidentifier(String x500uniqueidentifier) {
        this.x500uniqueidentifier = x500uniqueidentifier;
    }

    public boolean equals(LDAPinetOrgPerson ldapinetorgperson) {
        if (equalsAttribute(this.getCn(), ldapinetorgperson.getCn())
                && equalsAttribute(this.getDisplayname(), ldapinetorgperson.getDisplayname())
                && equalsAttribute(this.getEmployeenumber(), ldapinetorgperson.getEmployeenumber())
                && equalsAttribute(this.getGivenname(), ldapinetorgperson.getGivenname())
                && equalsAttribute(this.getInitials(), ldapinetorgperson.getInitials())
                && equalsAttribute(this.getMail(), ldapinetorgperson.getMail())
                && equalsAttribute(this.getMobile(), ldapinetorgperson.getMobile())
                && equalsAttribute(this.getPostaladdress(), ldapinetorgperson.getPostaladdress())
                && equalsAttribute(this.getPostalcode(), ldapinetorgperson.getPostalcode())
                && equalsAttribute(this.getSn(), ldapinetorgperson.getSn())
                && equalsAttribute(this.getSt(), ldapinetorgperson.getSt())
                && equalsAttribute(this.getStreet(), ldapinetorgperson.getStreet())
                && equalsAttribute(this.getTelephonenumber(), ldapinetorgperson.getTelephonenumber())
                && equalsAttribute(this.getTitle(), ldapinetorgperson.getTitle())
                && equalsAttribute(this.getUid(), ldapinetorgperson.getUid())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLDAPAdmin(LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapinetorgperson == null) {
            return false;
        }
        String uid = ldapinetorgperson.getUid();
        if (uid == null) {
            return false;
        }
        if (uid.equals(LDAPADMIN_MAIL)) {
            return true;
        }
        if (uid.equals(LDAPADMIN_EMPLOYEENUMBER)) {
            return true;
        }
        return false;
    }

    private boolean equalsAttribute(String attributeobject, String attributesubject) {
        if (attributeobject == null) {
            if (attributesubject == null) {
                // Object and subject both null
                return true;
            } else if (attributesubject.isEmpty()) {
                // Object is null and subject zero length is considered equal
                return true;
            } else {
                // Object is null while subject has non-zero length
                return false;
            }
        } else if (attributeobject.isEmpty()) {
            if (attributesubject == null) {
                // Object zero length and subject null is considered equal
                return true;
            } else if (attributesubject.isEmpty()) {
                // Both object and subject have zero length
                return true;
            } else {
                // Object has zero length but subject has not
                return false;
            }
        } else {
            if (attributesubject == null) {
                // Object has length other than zero while subject is null
                return false;
            } else if (attributesubject.isEmpty()) {
                // Object has length other than zero, subject has zero length
                return false;
            } else {
                // Both object and subject have non-zero length
                if (attributeobject.equals(attributesubject)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /*
    public List<String> equalsMessageList(LDAPinetOrgPerson ldapinetorgperson) {
    String message = "";
    List<String> messagelist = new ArrayList();
    if (!(message = equalsAttributeMessage(this.getCn(), ldapinetorgperson.getCn())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.CN.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getDisplayname(), ldapinetorgperson.getDisplayname())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.DISPLAYNAME.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getEmployeenumber(), ldapinetorgperson.getEmployeenumber())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.EMPLOYEENUMBER.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getGivenname(), ldapinetorgperson.getGivenname())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.GIVENNAME.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getInitials(), ldapinetorgperson.getInitials())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.INITIALS.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getMail(), ldapinetorgperson.getMail())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.MAIL.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getMobile(), ldapinetorgperson.getMobile())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.MOBILE.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getPostaladdress(), ldapinetorgperson.getPostaladdress())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.POSTALADDRESS.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getPostalcode(), ldapinetorgperson.getPostalcode())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.POSTALCODE.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getSn(), ldapinetorgperson.getSn())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.SN.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getSt(), ldapinetorgperson.getSt())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.ST.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getStreet(), ldapinetorgperson.getStreet())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.STREET.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getTelephonenumber(), ldapinetorgperson.getTelephonenumber())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.TELEPHONENUMBER.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getTitle(), ldapinetorgperson.getTitle())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.TITLE.concat(message));
    }
    if (!(message = equalsAttributeMessage(this.getUid(), ldapinetorgperson.getUid())).isEmpty()) {
    messagelist.add(LDAPinetOrgPerson.UID.concat(message));
    }
    return messagelist;
    }
     *
     */

    /*
    private String equalsAttributeMessage(String attributeobject, String attributesubject) {
    final String NULL = "null";
    final String EMPTY = "empty";
    final String TRANSITION = " --> ";

    if (attributeobject == null) {
    if (attributesubject == null) {
    // Object and subject both null
    return "";
    } else if (attributesubject.isEmpty()) {
    // Object is null and subject zero length is considered equal
    return "";
    } else {
    // Object is null while subject has non-zero length
    return NULL.concat(TRANSITION).concat(attributesubject);
    }
    } else if (attributeobject.isEmpty()) {
    if (attributesubject == null) {
    // Object zero length and subject null is considered equal
    return "";
    } else if (attributesubject.isEmpty()) {
    // Both object and subject have zero length
    return "";
    } else {
    // Object has zero length but subject has not
    return EMPTY.concat(TRANSITION).concat(attributesubject);
    }
    } else {
    if (attributesubject == null) {
    // Object has length other than zero while subject is null
    return attributeobject.concat(TRANSITION).concat(NULL);
    } else if (attributesubject.isEmpty()) {
    // Object has length other than zero, subject has zero length
    return attributeobject.concat(TRANSITION).concat(EMPTY);
    } else {
    // Both object and subject have non-zero length
    if (attributeobject.equals(attributesubject)) {
    return "";
    } else {
    return attributeobject.concat(TRANSITION).concat(attributesubject);
    }
    }
    }
    }
     *
     */
}
