/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.controller;

import nl.amnesty.ldap.entity.LDAP;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPv2;
import nl.amnesty.ldap.entity.LDAPResultcode;

/**
 *
 * @author ed
 */
public class LDAPSunONEController {
    //private String basedn;
    //private String binddn;
    //private String bindpassword;
    //
    //private LDAPConnection ldapconnection;

    public LDAPConnection open(LDAP ldap) {
        LDAPConnection ldapconnection = new LDAPConnection();
        //LDAPConnection ldapgenericconnection = new LDAPConnection();
        LDAPResultcode ldapgenericresultcode = new LDAPResultcode();
        try {
            ldapconnection.connect(ldap.getHostname(), ldap.getPortnumber());
            ldapconnection.setOption(LDAPv2.TIMELIMIT, new Integer(5000));
            // Authenticate to the server as the directory manager
            ldapconnection.authenticate("cn=".concat(ldap.getBinddn()), ldap.getBindpassword());

            // Set connection
            //ldapgenericconnection.setLdapconnectionsunone(ldapconnection);
            //ldap.setLdapgenericconnection(ldapgenericconnection);
            // Set resultcode
            ldapgenericresultcode.setLdapresultcodesunone(ldapconnection.isConnected());
            ldap.setLdapgenericresultcode(ldapgenericresultcode);

            return ldapconnection;
        } catch (final LDAPException ldape) {
            ldape.printStackTrace();
            ldapgenericresultcode.setLdapresultcodesunone(ldapconnection.isConnected());
            ldap.setLdapgenericresultcode(ldapgenericresultcode);
            return null;
        } catch (final Exception e) {
            e.printStackTrace();
            ldapgenericresultcode.setLdapresultcodesunone(ldapconnection.isConnected());
            ldap.setLdapgenericresultcode(ldapgenericresultcode);
            return null;
        } finally {
            return ldapconnection;
        }
    }

    public void close(LDAPConnection ldapconnection) {
        /* Disconnect from the server when done */
        if ((ldapconnection != null) && ldapconnection.isConnected()) {
            try {
                ldapconnection.disconnect();
            } catch (final LDAPException ldape) {
                ldape.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
