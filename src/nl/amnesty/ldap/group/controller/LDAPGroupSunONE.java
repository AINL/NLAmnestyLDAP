/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.group.controller;

import nl.amnesty.ldap.person.controller.LDAPPersonSunONE;
import java.util.ArrayList;
import java.util.List;
import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPModification;
import netscape.ldap.LDAPSearchResults;
import netscape.ldap.LDAPv2;
import nl.amnesty.ldap.group.entity.LDAPgroupOfUniqueNames;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;
import org.apache.log4j.Logger;

/**
 *
 * @author evelzen
 */
public class LDAPGroupSunONE {

    private static final String LDAPGROUP_ID = "uid";
    private static final String LDAPGROUP_CN = "cn";
    private static final String LDAPGROUP_OU = "ou";
    private static final String LDAPGROUP_UNIQUEMEMBER = "uniqueMember";
    private static final String LDAPSCOPE_GROUP = "ou=Groups";
    //
    private static final String OBJECTCLASS_VALUES[] = {"top", "groupofuniquenames"};

    public static void create(Logger logger, LDAPConnection ldapconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames) {
        try {
            String cn = ldapgroupofuniquenames.getCn();
            final String dn = "cn=" + cn + "," + "ou=Groups," + basedn;
            final LDAPAttributeSet attrs = new LDAPAttributeSet();
            final LDAPAttribute attr = new LDAPAttribute("objectclass", OBJECTCLASS_VALUES);
            attrs.add(attr);

            attrs.add(new LDAPAttribute("cn", ldapgroupofuniquenames.getCn()));
            //TODO: Not sure if description is the proper name of this attribute?
            attrs.add(new LDAPAttribute("description", "Digital Action File Country ISO group"));
            final LDAPEntry ldapentry = new LDAPEntry(dn, attrs);

            ldapconnection.add(ldapentry);
        } catch (LDAPException ldape) {
            if (ldape.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT) {
                logger.error("Error modLdapAttribute: No such entry.");
            } else if (ldape.getLDAPResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
                logger.error("Error modLdapAttribute: Insufficient rights.");
            } else if (ldape.getLDAPResultCode() == LDAPException.ATTRIBUTE_OR_VALUE_EXISTS) {
                logger.error("Error modLdapAttribute: Attribute or value existst.");
            } else {
                logger.error("Error modLdapAttribute: " + ldape.toString());
            }
            ldape.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LDAPgroupOfUniqueNames read(Logger logger, LDAPConnection ldapconnection, String basedn, String cn) {
        String cnvalue = "";
        try {
            final LDAPSearchResults results = ldapconnection.search(LDAPSCOPE_GROUP.concat(",").concat(basedn), LDAPv2.SCOPE_SUB, "(cn=" + cn + ")", null, false);

            if (results.getCount() > 0) {
                LDAPEntry ldapentry = results.next();
                LDAPAttribute ldapattribute = null;

                LDAPgroupOfUniqueNames ldapgroupofuniquenames = new LDAPgroupOfUniqueNames();
                if ((ldapattribute = ldapentry.getAttribute(LDAPGROUP_CN)) != null) {
                    cnvalue = ldapattribute.getStringValueArray()[0];
                } else {
                    cnvalue = "";
                }
                List<LDAPinetOrgPerson> ldapinetorgpersonlist = new ArrayList();
                if ((ldapattribute = ldapentry.getAttribute(LDAPGROUP_UNIQUEMEMBER)) != null) {
                    String[] uniquememberlist = ldapattribute.getStringValueArray();
                    for (int i = 0; i < uniquememberlist.length; i++) {
                        int beginindex = uniquememberlist[i].indexOf(LDAPPersonSunONE.LDAPPERSON_ID) + LDAPPersonSunONE.LDAPPERSON_ID.length() + 1;
                        int endindex = uniquememberlist[i].indexOf(LDAPPersonSunONE.LDAPPERSON_OU) - 1;
                        String uid = uniquememberlist[i].substring(beginindex, endindex);

                        // DEBUG
                        //System.out.println("LDAPgroupOfUniqueNames read(): uid: [" + uid + "]");

                        LDAPinetOrgPerson ldapinetorgperson = LDAPPersonSunONE.read(ldapconnection, basedn, uid);
                        if (ldapinetorgperson != null) {
                            ldapinetorgpersonlist.add(ldapinetorgperson);
                        } else {
                            // DEBUG
                            //System.out.println("LDAPgroupOfUniqueNames read(): null for uid: [" + uid + "]");
                        }
                    }
                }
                ldapgroupofuniquenames.setCn(cnvalue);
                //ldapgroupofuniquenames.setDescription("Digital Action File Country ISO group");
                ldapgroupofuniquenames.setDescription("LDAP group");
                ldapgroupofuniquenames.setLdapinetorgpersonlist(ldapinetorgpersonlist);
                return ldapgroupofuniquenames;
            } else {
                return null;
            }
        } catch (LDAPException ldape) {
            if (ldape.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT) {
                logger.error("Error modLdapAttribute: No such entry.");
            } else if (ldape.getLDAPResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
                logger.error("Error modLdapAttribute: Insufficient rights.");
            } else if (ldape.getLDAPResultCode() == LDAPException.ATTRIBUTE_OR_VALUE_EXISTS) {
                logger.error("Error modLdapAttribute: Attribute or value existst.");
            } else {
                logger.error("Error modLdapAttribute: " + ldape.toString());
            }
            ldape.printStackTrace();
            return null;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean removeMember(Logger logger, LDAPConnection ldapconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson) {
        try {
            String cn = ldapgroupofuniquenames.getCn();
            String uid = ldapinetorgperson.getUid();
            Boolean found = false;
            // This may be a newly created LDAP group without persons at this point.
            if (ldapgroupofuniquenames.getLdapinetorgpersonlist() != null) {
                for (LDAPinetOrgPerson groupldapinetorgperson : ldapgroupofuniquenames.getLdapinetorgpersonlist()) {
                    if (groupldapinetorgperson.getUid().equals(ldapinetorgperson.getUid())) {
                        found = true;
                    }
                }
            }
            // Remove only of LDAP person only makes sense if person is found within LDAP group
            if (found) {
                final String dn = "uid=" + uid + "," + "ou=People," + basedn;
                String entrydn = "cn=" + cn + ",ou=Groups," + basedn;
                // Prepare the modification
                final LDAPAttribute attrMod = new LDAPAttribute("uniqueMember", dn);
                final LDAPModification mods = new LDAPModification(LDAPModification.DELETE, attrMod);
                // Modify the entry
                ldapconnection.modify(entrydn, mods);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addMember(Logger logger, LDAPConnection ldapconnection, String basedn, LDAPgroupOfUniqueNames ldapgroupofuniquenames, LDAPinetOrgPerson ldapinetorgperson) {
        try {
            String cn = ldapgroupofuniquenames.getCn();
            String uid = ldapinetorgperson.getUid();
            // Check if this person is already part of the group
            Boolean found = false;
            // This may be a newly created LDAP group without persons at this point.
            if (ldapgroupofuniquenames.getLdapinetorgpersonlist() != null) {
                for (LDAPinetOrgPerson groupldapinetorgperson : ldapgroupofuniquenames.getLdapinetorgpersonlist()) {
                    
                    // DEBUG
                    //System.out.println("groupldapinetorgperson.getUid(): [" + groupldapinetorgperson.getUid() + "]");
                    //System.out.println("ldapinetorgperson.getUid(): <" + ldapinetorgperson.getUid() + ">");

                    if (groupldapinetorgperson.getUid().equals(ldapinetorgperson.getUid())) {
                        found = true;
                    }
                }
            }

            if (!found) {
                final String dn = "uid=" + uid + "," + "ou=People," + basedn;
                String entrydn = "cn=" + cn + ",ou=Groups," + basedn;

                // DEBUG
                //System.out.println("dn: " + dn);
                //System.out.println("entrydn: " + entrydn);

                // Prepare the modification
                final LDAPAttribute attrMod = new LDAPAttribute("uniqueMember", dn);
                final LDAPModification mods = new LDAPModification(LDAPModification.ADD, attrMod);
                // Modify the entry
                ldapconnection.modify(entrydn, mods);

                return true;
            } else {
                return false;
            }
        } catch (final LDAPException ldape) {
            if (ldape.getLDAPResultCode() == LDAPException.NO_SUCH_OBJECT) {
                logger.error("Error modLdapAttribute: No such entry.");
            } else if (ldape.getLDAPResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
                logger.error("Error modLdapAttribute: Insufficient rights.");
            } else if (ldape.getLDAPResultCode() == LDAPException.ATTRIBUTE_OR_VALUE_EXISTS) {
                logger.error("Error modLdapAttribute: Attribute or value existst.");
            } else {
                logger.error("Error modLdapAttribute: " + ldape.toString());
            }
            ldape.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
