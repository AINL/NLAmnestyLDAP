/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.entity;

import com.unboundid.ldap.sdk.ResultCode;
import netscape.ldap.LDAPResponse;

/**
 *
 * @author ed
 */
public class LDAPResultcode {

    private com.unboundid.ldap.sdk.ResultCode ldapresultcodeopends;
    //private netscape.ldap.LDAPResponse ldapresultcodesunone;
    private boolean ldapresultcodesunone;

    public ResultCode getLdapresultcodeopends() {
        return ldapresultcodeopends;
    }

    public void setLdapresultcodeopends(ResultCode ldapresultcodeopends) {
        this.ldapresultcodeopends = ldapresultcodeopends;
    }

    public boolean isLdapresultcodesunone() {
        return ldapresultcodesunone;
    }

    public void setLdapresultcodesunone(boolean ldapresultcodesunone) {
        this.ldapresultcodesunone = ldapresultcodesunone;
    }

}
