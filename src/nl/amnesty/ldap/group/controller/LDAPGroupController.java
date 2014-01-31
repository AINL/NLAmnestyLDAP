/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.group.controller;

import nl.amnesty.ldap.entity.LDAPConnection;
import nl.amnesty.ldap.group.entity.LDAPgroupOfUniqueNames;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;
import org.apache.log4j.Logger;

/**
 *
 * @author ed
 */
public class LDAPGroupController {

    public static void create(Logger logger, LDAPConnection ldapgenericconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            LDAPGroupOpenDS.create(logger, ldapgenericconnection.getLdapconnectionopends(), ldapgroupofuniquenames, basedn);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            LDAPGroupSunONE.create(logger, ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapgroupofuniquenames);
        }
    }

    public static LDAPgroupOfUniqueNames read(Logger logger, LDAPConnection ldapgenericconnection, String basedn, String cn) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPGroupOpenDS.read(logger, ldapgenericconnection.getLdapconnectionopends(), basedn, cn);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPGroupSunONE.read(logger, ldapgenericconnection.getLdapconnectionsunone(), basedn, cn);
        }
        return null;
    }

    public static boolean removeMember(Logger logger, LDAPConnection ldapgenericconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPGroupOpenDS.removeMember(logger, ldapgenericconnection.getLdapconnectionopends(), ldapgroupofuniquenames, ldapinetorgperson, basedn);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPGroupSunONE.removeMember(logger, ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapgroupofuniquenames, ldapinetorgperson);
        }
        return false;
    }

    public static boolean addMember(Logger logger, LDAPConnection ldapgenericconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson) {
        if (ldapgenericconnection.getLdapconnectionopends() != null) {
            return LDAPGroupOpenDS.addMember(logger, ldapgenericconnection.getLdapconnectionopends(), ldapgroupofuniquenames, ldapinetorgperson, basedn);
        }
        if (ldapgenericconnection.getLdapconnectionsunone() != null) {
            return LDAPGroupSunONE.addMember(logger, ldapgenericconnection.getLdapconnectionsunone(), basedn, ldapgroupofuniquenames, ldapinetorgperson);
        }
        return false;
    }
}
