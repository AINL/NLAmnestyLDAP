/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.ldap.controller.LDAPOpenDSController;
import nl.amnesty.ldap.controller.LDAPSunONEController;

/**
 *
 * @author ed
 */
public class LDAP {

    public final static String HOSTNAME_GARNAAL = "garnaal.groentenman.amnesty.nl";
    public final static int PORTNUMBER_GARNAAL = 389;
    public final static String BASEDN_GARNAAL = "dc=groentenman,dc=amnesty,dc=nl";
    public final static String BINDDN_GARNAAL = "cn=Directory Manager";
    public final static String BINDPASSWORD_GARNAAL = "eendvogel";
    //
    public final static String HOSTNAME_LOF = "lof.groentenman.amnesty.nl";
    public final static int PORTNUMBER_LOF = 389;
    public final static String BASEDN_LOF = "dc=groentenman,dc=amnesty,dc=nl";
    public final static String BINDDN_LOF = "cn=Directory Manager";
    public final static String BINDPASSWORD_LOF = "eendvogel";
    //
    public static final String LDAP_TYPE_OPENDS_NAME = "opends";
    public static final String LDAP_TYPE_SUNONE_NAME = "sunone";
    public static final int LDAP_TYPE_NONE = 0;
    public static final int LDAP_TYPE_OPENDS = 1;
    public static final int LDAP_TYPE_SUNONE = 2;
    private int ldapvendor;
    private String hostname;
    private int portnumber;
    private String basedn;
    private String binddn;
    private String bindpassword;
    private LDAPConnection ldapgenericconnection;
    private LDAPResultcode ldapgenericresultcode;

    public LDAP() {
    }

    public LDAP(int ldapvendor, String hostname, int portnumber, String basedn, String binddn, String bindpassword) {
        this.ldapvendor = ldapvendor;
        this.hostname = hostname;
        this.portnumber = portnumber;
        this.basedn = basedn;
        this.binddn = binddn;
        this.bindpassword = bindpassword;
    }

    public LDAPConnection open() {
        ldapgenericconnection = new LDAPConnection();
        try {
            switch (this.ldapvendor) {
                case LDAP_TYPE_OPENDS:
                    LDAPOpenDSController ldapopends = new LDAPOpenDSController();
                    ldapgenericconnection.setLdapconnectionopends(ldapopends.open(this));
                    ldapgenericconnection.setLdapconnectionsunone(null);
                    break;
                case LDAP_TYPE_SUNONE:
                    LDAPSunONEController ldapsunone = new LDAPSunONEController();
                    ldapgenericconnection.setLdapconnectionopends(null);
                    ldapgenericconnection.setLdapconnectionsunone(ldapsunone.open(this));
                    break;
                default:
                    ldapgenericconnection.setLdapconnectionopends(null);
                    ldapgenericconnection.setLdapconnectionsunone(null);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(LDAP.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            return ldapgenericconnection;
        }
    }

    public void close(LDAPConnection ldapgenericconnection) {
        try {
            switch (this.ldapvendor) {
                case LDAP_TYPE_OPENDS:
                    ldapgenericconnection.getLdapconnectionopends().close();
                    break;
                case LDAP_TYPE_SUNONE:
                    ldapgenericconnection.getLdapconnectionsunone().disconnect();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(LDAP.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getBasedn() {
        return basedn;
    }

    public void setBasedn(String basedn) {
        this.basedn = basedn;
    }

    public String getBinddn() {
        return binddn;
    }

    public void setBinddn(String binddn) {
        this.binddn = binddn;
    }

    public String getBindpassword() {
        return bindpassword;
    }

    public void setBindpassword(String bindpassword) {
        this.bindpassword = bindpassword;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPortnumber() {
        return portnumber;
    }

    public void setPortnumber(int portnumber) {
        this.portnumber = portnumber;
    }

    public int getLdapvendor() {
        return ldapvendor;
    }

    public void setLdapvendor(int ldapvendor) {
        this.ldapvendor = ldapvendor;
    }

    public LDAPConnection getLdapgenericconnection() {
        return ldapgenericconnection;
    }

    public void setLdapgenericconnection(LDAPConnection ldapgenericconnection) {
        this.ldapgenericconnection = ldapgenericconnection;
    }

    public LDAPResultcode getLdapgenericresultcode() {
        return ldapgenericresultcode;
    }

    public void setLdapgenericresultcode(LDAPResultcode ldapgenericresultcode) {
        this.ldapgenericresultcode = ldapgenericresultcode;
    }
}
