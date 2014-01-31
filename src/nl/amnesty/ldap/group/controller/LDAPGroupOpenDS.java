/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.group.controller;

import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.DeleteRequest;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.Modification;
import com.unboundid.ldap.sdk.ModificationType;
import com.unboundid.ldap.sdk.ModifyRequest;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nl.amnesty.ldap.group.entity.LDAPgroupOfUniqueNames;
import nl.amnesty.ldap.person.controller.LDAPPersonOpenDS;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author ed
 */
public class LDAPGroupOpenDS {

    public static boolean create(Logger logger, LDAPConnection ldapconnection, LDAPgroupOfUniqueNames ldapgroupofuniquenames, String basedn) {
        try {
            String cn = ldapgroupofuniquenames.getCn();
            String dn = "cn=".concat(cn).concat(",").concat("ou=Groups,").concat(basedn);
            Collection collection = new ArrayList<Attribute>();
            collection.add(new Attribute("objectClass", "groupOfUniqueNames"));
            if (cn.length() != 0) {
                collection.add(new Attribute(LDAPgroupOfUniqueNames.CN, cn));
                return addEntry(logger, ldapconnection, dn, collection);
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.fatal(e.getMessage(), e);
            return false;
        }
    }

    public static LDAPgroupOfUniqueNames read(Logger logger, LDAPConnection ldapconnection, String basedn, String cn) {
        Filter filter = Filter.createEqualityFilter("cn", cn);
        SearchRequest searchrequest = new SearchRequest("ou=Groups,".concat(basedn), SearchScope.ONE, filter, SearchRequest.ALL_USER_ATTRIBUTES);
        LDAPgroupOfUniqueNames ldapgroupofuniquenames = new LDAPgroupOfUniqueNames();
        try {

            // DEBUG
            //System.out.println("LDAPGroupOpenDS read() searchrequest: " + searchrequest);

            SearchResult searchresult = ldapconnection.search(searchrequest);

            // DEBUG
            //System.out.println("LDAPGroupOpenDS read() entrycount: " + searchresult.getEntryCount());

            if (searchresult.getEntryCount() == 1) {
                SearchResultEntry searchresultentry = searchresult.getSearchEntries().get(0);
                ldapgroupofuniquenames = setGroup(logger, ldapconnection, basedn, searchresultentry);
                return ldapgroupofuniquenames;
            } else {
                return null;
            }
        } catch (LDAPSearchException lse) {
            return null;
        } catch (LDAPException le) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean addMember(Logger logger, LDAPConnection ldapconnection, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson, String basedn) {
        LDAPResult ldapresult;
        ResultCode resultcode;
        try {
            Modification modification = new Modification(ModificationType.ADD, "uniqueMember", "uid=".concat(ldapinetorgperson.getUid()).concat(",ou=People,").concat(basedn));
            String dn = "cn=".concat(ldapgroupofuniquenames.getCn()).concat(",ou=Groups,").concat(basedn);
            ModifyRequest modifyrequest = new ModifyRequest(dn, modification);
            ldapresult = ldapconnection.modify(modifyrequest);
            resultcode = ldapresult.getResultCode();
            if (resultcode == ResultCode.SUCCESS) {
                return true;
            } else {
                logger.warn(resultcode.toString());
                return false;
            }
        } catch (LDAPException ex) {
            logger.fatal(ex.getMessage(), ex);
            return false;
        }
    }

    public static boolean removeMember(Logger logger, LDAPConnection ldapconnection, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson, String basedn) {
        LDAPResult ldapresult;
        ResultCode resultcode;
        try {
            Modification modification = new Modification(ModificationType.DELETE, "uniqueMember", "uid=".concat(ldapinetorgperson.getUid()).concat(",ou=People,").concat(basedn));
            String dn = "cn=".concat(ldapgroupofuniquenames.getCn()).concat(",ou=Groups,").concat(basedn);
            ModifyRequest modifyrequest = new ModifyRequest(dn, modification);
            ldapresult = ldapconnection.modify(modifyrequest);
            resultcode = ldapresult.getResultCode();
            if (resultcode == ResultCode.SUCCESS) {
                return true;
            } else {
                logger.warn(resultcode.toString());
                return false;
            }
        } catch (LDAPException ex) {
            logger.fatal(ex.getMessage(), ex);
            return false;
        }
    }

    private static boolean addEntry(Logger logger, LDAPConnection ldapconnection, String dn, Collection<Attribute> attributecollection) {
        AddRequest addrequest;
        LDAPResult ldapresult;
        try {
            addrequest = new AddRequest(dn, attributecollection);
            ldapresult = ldapconnection.add(addrequest);
            ResultCode resultcode = ldapresult.getResultCode();
            if (resultcode == ResultCode.SUCCESS) {
                return true;
            } else {
                logger.warn(resultcode.toString());
                return false;
            }
        } catch (LDAPException ex) {
            logger.fatal(ex.getMessage(), ex);
            return false;
        }
    }

    private static boolean deleteEntry(Logger logger, LDAPConnection ldapconnection, String dn) {
        DeleteRequest deleterequest;
        LDAPResult ldapresult;
        try {
            deleterequest = new DeleteRequest(dn);
            ldapresult = ldapconnection.delete(deleterequest);
            ResultCode resultcode = ldapresult.getResultCode();
            if (resultcode == ResultCode.SUCCESS) {
                return true;
            } else {
                logger.warn(resultcode.toString());
                return false;
            }
        } catch (LDAPException ex) {
            logger.fatal(ex.getMessage(), ex);
            return false;
        }
    }

    private static LDAPgroupOfUniqueNames setGroup(Logger logger, LDAPConnection ldapconnection, String basedn, SearchResultEntry searchresultentry) {
        LDAPgroupOfUniqueNames ldapgroupofuniquenames = new LDAPgroupOfUniqueNames();
        List<LDAPinetOrgPerson> ldapinetorgpersonlist = new ArrayList();
        String uid = "";
        String cn = "";
        String[] uniquememberarray = {};
        try {
            cn = searchresultentry.getAttributeValue(LDAPgroupOfUniqueNames.CN);
            ldapgroupofuniquenames.setCn(cn);
            ldapgroupofuniquenames.setDescription(cn);
            Attribute attribute = searchresultentry.getAttribute(LDAPgroupOfUniqueNames.UNIQUEMEMBER);
            if (attribute != null) {
                uniquememberarray = attribute.getValues();
            }

            // DEBUG
            //System.out.println("size LDAPgroupOfUniqueNames.UNIQUEMEMBER: " + uniquememberarray.length);

            for (String uniquemember : uniquememberarray) {
                if (uniquemember.startsWith("uid=")) {
                    uid = uniquemember.substring("uid=".length(), uniquemember.indexOf(","));
                    LDAPinetOrgPerson ldapinetorgperson = LDAPPersonOpenDS.read(ldapconnection, basedn, uid);
                    if (ldapinetorgperson != null) {
                        ldapinetorgpersonlist.add(ldapinetorgperson);
                    } else {
                        // Unable to retrieve LDAPinetOrgPerson based on the uid found in the LDAP group, create dummy person
                        ldapinetorgperson = new LDAPinetOrgPerson();
                        ldapinetorgperson.setUid(uid);
                        // This indicates that LDAP person is no longer listed, update the LDAP group accordingly
                        removeMember(logger, ldapconnection, ldapgroupofuniquenames, ldapinetorgperson, basedn);
                    }
                }
            }
            ldapgroupofuniquenames.setLdapinetorgpersonlist(ldapinetorgpersonlist);

            return ldapgroupofuniquenames;
        } catch (Exception e) {
            logger.fatal(e.getMessage(), e);
            return null;
        }
    }
}
