package nl.amnesty.ldap.person.entity;

public class LDAPorganizationalPerson extends LDAPPerson {

    // Constants
    public static final String DESTINATIONINDICATOR = "destinationIndicator";
    public static final String FACSIMILETELEPHONENUMBER = "facsimileTelephoneNumber";
    public static final String INTERNATIONALISDNNUMBER = "internationaliSDNNumber";
    public static final String L = "L";
    public static final String OU = "ou";
    public static final String PHYSICALDELIVERYOFFICENAME = "physicalDeliveryOfficeName";
    public static final String POSTALADDRESS = "postalAddress";
    public static final String POSTALCODE = "postalCode";
    public static final String POSTOFFICEBOX = "postOfficeBox";
    public static final String PREFEREDDELIVERYMETHOD = "preferedDeliveryMethod";
    public static final String REGISTEREDADDRESS = "registeredAddress";
    public static final String ST = "st";
    public static final String STREET = "street";
    public static final String TELETEXTERMINALIDENTIFIER = "teletexTerminalIdentifier";
    public static final String TELEXNUMBER = "telexNumber";
    public static final String TITLE = "title";
    public static final String X121ADDRESS = "x121Address";
    public static final String UID = "uid";
    // Attributes
    private String destinationindicator;
    private String facsimiletelephonenumber;
    private String internationalisdnnumber;
    private String l;
    private String ou;
    private String physicaldeliveryofficename;
    private String postaladdress;
    private String postalcode;
    private String postofficebox;
    private String prefereddeliverymethod;
    private String registeredaddress;
    private String st;
    private String street;
    private String teletexterminalidentifier;
    private String telexnumber;
    private String title;
    private String x121address;

    public String getDestinationindicator() {
        return destinationindicator;
    }

    public void setDestinationindicator(String destinationindicator) {
        this.destinationindicator = destinationindicator;
    }

    public String getFacsimiletelephonenumber() {
        return facsimiletelephonenumber;
    }

    public void setFacsimiletelephonenumber(String facsimiletelephonenumber) {
        this.facsimiletelephonenumber = facsimiletelephonenumber;
    }

    public String getInternationalisdnnumber() {
        return internationalisdnnumber;
    }

    public void setInternationalisdnnumber(String internationalisdnnumber) {
        this.internationalisdnnumber = internationalisdnnumber;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    public String getPhysicaldeliveryofficename() {
        return physicaldeliveryofficename;
    }

    public void setPhysicaldeliveryofficename(String physicaldeliveryofficename) {
        this.physicaldeliveryofficename = physicaldeliveryofficename;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPostofficebox() {
        return postofficebox;
    }

    public void setPostofficebox(String postofficebox) {
        this.postofficebox = postofficebox;
    }

    public String getPrefereddeliverymethod() {
        return prefereddeliverymethod;
    }

    public void setPrefereddeliverymethod(String prefereddeliverymethod) {
        this.prefereddeliverymethod = prefereddeliverymethod;
    }

    public String getRegisteredaddress() {
        return registeredaddress;
    }

    public void setRegisteredaddress(String registeredaddress) {
        this.registeredaddress = registeredaddress;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTeletexterminalidentifier() {
        return teletexterminalidentifier;
    }

    public void setTeletexterminalidentifier(String teletexterminalidentifier) {
        this.teletexterminalidentifier = teletexterminalidentifier;
    }

    public String getTelexnumber() {
        return telexnumber;
    }

    public void setTelexnumber(String telexnumber) {
        this.telexnumber = telexnumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getX121address() {
        return x121address;
    }

    public void setX121address(String x121address) {
        this.x121address = x121address;
    }

}
