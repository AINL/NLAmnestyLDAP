/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.person.controller;

import nl.amnesty.ldap.entity.LDAPConnection;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;

/**
 *
 * @author ed
 */
public class LDAPPersonController {

    public static void create(LDAPConnection ldapgenericconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            LDAPPersonOpenDS.create(ldapgenericconnection.getLdapconnectionopends(), basedn, ldapinetorgperson);
            // Set the password to the postalcode initially
            //ldappersonopends.setPassword(ldapgenericconnection.getLdapconnectionopends(), basedn, ldapinetorgperson, ldapinetorgperson.getPostalcode());
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            LDAPPersonSunONE.create(ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapinetorgperson);
            // Set the password to the postalcode initially
            //ldappersonsunone.setPassword(ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapinetorgperson, ldapinetorgperson.getPostalcode());
        }
    }

    public static LDAPinetOrgPerson read(LDAPConnection ldapgenericconnection, String basedn, String uid) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPPersonOpenDS.read(ldapgenericconnection.getLdapconnectionopends(), basedn, uid);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPPersonSunONE.read(ldapgenericconnection.getLdapconnectionsunone(), basedn, uid);
        }
        return null;
    }

    public static void update(LDAPConnection ldapgenericconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            LDAPPersonOpenDS.update(ldapgenericconnection.getLdapconnectionopends(), basedn, ldapinetorgperson);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            LDAPPersonSunONE.update(ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapinetorgperson);
        }
    }

    public static void delete(LDAPConnection ldapgenericconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            LDAPPersonOpenDS.delete(ldapgenericconnection.getLdapconnectionopends(), basedn, ldapinetorgperson);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            LDAPPersonSunONE.delete(ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapinetorgperson);
        }
    }

    public static void setPassword(LDAPConnection ldapgenericconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson, String password) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            LDAPPersonOpenDS.setPassword(ldapgenericconnection.getLdapconnectionopends(), basedn, ldapinetorgperson, password);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            LDAPPersonSunONE.setPassword(ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapinetorgperson, password);
        }
    }

    public static LDAPinetOrgPerson findViaMail(LDAPConnection ldapgenericconnection, String basedn, String mail) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPPersonOpenDS.findViaMail(ldapgenericconnection.getLdapconnectionopends(), basedn, mail);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPPersonSunONE.findViaMail(ldapgenericconnection.getLdapconnectionsunone(), basedn, mail);
        }
        return null;
    }

    public static LDAPinetOrgPerson findViaEmployeenumber(LDAPConnection ldapgenericconnection, String basedn, String employeenumber) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPPersonOpenDS.findViaEmployeenumber(ldapgenericconnection.getLdapconnectionopends(), basedn, employeenumber);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPPersonSunONE.findViaEmployeenumber(ldapgenericconnection.getLdapconnectionsunone(), basedn, employeenumber);
        }
        return null;
    }

}
