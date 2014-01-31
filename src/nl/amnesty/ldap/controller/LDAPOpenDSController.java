/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.amnesty.ldap.controller;

import nl.amnesty.ldap.entity.LDAP;
import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.ldap.entity.LDAPResultcode;

/**
 *
 * @author ed
 */
public class LDAPOpenDSController {
    /*
   private final static String ATTRIBUTES[] = {
        LDAPinetOrgPerson.CN,
        LDAPinetOrgPerson.DISPLAYNAME,
        LDAPinetOrgPerson.GIVENNAME,
        LDAPinetOrgPerson.INITIALS,
        LDAPinetOrgPerson.MAIL,
        LDAPinetOrgPerson.MOBILE,
        LDAPinetOrgPerson.POSTALADDRESS,
        LDAPinetOrgPerson.POSTALCODE,
        LDAPinetOrgPerson.SN,
        LDAPinetOrgPerson.TELEPHONENUMBER,
        LDAPinetOrgPerson.UID
    };
     *
     */
    public LDAPConnection open(LDAP ldap) {
        LDAPConnection ldapconnection = new LDAPConnection();
        try {
            ldapconnection.connect(ldap.getHostname(), ldap.getPortnumber());
            BindResult bindresult = ldapconnection.bind(ldap.getBinddn(), ldap.getBindpassword());
            ResultCode resultcode = bindresult.getResultCode();

            LDAPResultcode ldapgenericresultcode = new LDAPResultcode();
            ldapgenericresultcode.setLdapresultcodeopends(resultcode);
            ldap.setLdapgenericresultcode(ldapgenericresultcode);

            //DEBUG
            /*
            if (ldapconnection.isConnected()) {
                //System.out.println("Connected");
            } else {
                //System.out.println("Not connected");
            }
             * 
             */

            /*
            if (resultcode == ResultCode.SUCCESS) {
                ldap.setLdapconnection(ldapconnection);
            } else {
                ldap.setLdapconnection(null);
            }
             *
             */
        } catch (LDAPException ex) {
            LDAPResultcode ldapgenericresultcode = new LDAPResultcode();
            ldapgenericresultcode.setLdapresultcodeopends(ResultCode.SERVER_DOWN);
            ldap.setLdapgenericresultcode(ldapgenericresultcode);
            Logger.getLogger(LDAPOpenDSController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            return ldapconnection;
        }
    }

    public void close(LDAPConnection ldapconnection) {
        if (ldapconnection != null) {
            ldapconnection.close();
            /*
            ldap.setLdapconnection(null);
             *
             */
        } else {
            /*
            ldap.setLdapconnection(null);
             *
             */
        }
        //return ldap;
    }
}
