/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.person.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchResults;
import netscape.ldap.LDAPv2;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;

/**
 *
 * @author evelzen
 */
public class LDAPPersonSunONE {

    public static final String LDAPPERSON_ID = "uid";
    private static final String LDAPPERSON_GIVENNAME = "givenName";
    private static final String LDAPPERSON_SN = "sn";
    private static final String LDAPPERSON_TELEPHONENUMBER = "telephoneNumber";
    private static final String LDAPPERSON_MAIL = "mail";
    private static final String LDAPPERSON_FACSIMILETELEPHONENUMBER = "facsimileTelephoneNumber";
    private static final String LDAPPERSON_CN = "cn";
    private static final String LDAPPERSON_USERPASSWORD = "userpassword";
    //
    public static final String LDAPPERSON_OU = "ou";
    public static final String LDAPSCOPE_PERSON = "ou=People";
    //
    private static final String OBJECTCLASS_VALUES[] = {"top", "person", "organizationalPerson", "inetOrgPerson"};
    // 

    public static void create(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        try {
            String uid = ldapinetorgperson.getUid();
            String dn = "uid=".concat(uid).concat(",").concat("ou=People,").concat(basedn);

            // DEBUG
            //System.out.println("LDAPPersonSunONE: create(): uid: " + uid);
            //System.out.println("LDAPPersonSunONE: create(): dn: " + dn);

            // Create a new attribute set for the entry
            final LDAPAttributeSet attrs = new LDAPAttributeSet();

            // Create and add attributes to the attribute set
            final LDAPAttribute attr = new LDAPAttribute("objectclass", OBJECTCLASS_VALUES);
            attrs.add(attr);

            String cn = ldapinetorgperson.getCn().trim();
            String displayname = ldapinetorgperson.getDisplayname().trim();
            String employeenumber = ldapinetorgperson.getEmployeenumber().trim();
            String givenname = ldapinetorgperson.getGivenname().trim();
            String initials = ldapinetorgperson.getInitials().trim();
            String mail = ldapinetorgperson.getMail().trim();
            String mobile = ldapinetorgperson.getMobile().trim();
            String postaladdress = ldapinetorgperson.getPostaladdress().trim();
            String postalcode = ldapinetorgperson.getPostalcode().trim();
            String sn = ldapinetorgperson.getSn().trim();
            String st = ldapinetorgperson.getSt().trim();
            String telephonenumber = ldapinetorgperson.getTelephonenumber().trim();
            String title = ldapinetorgperson.getTitle().trim();
            String userpassword = ldapinetorgperson.getUserpassword().trim();

            if (cn.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.CN, cn));
            }
            if (displayname.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.DISPLAYNAME, displayname));
            }
            if (employeenumber.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.EMPLOYEENUMBER, employeenumber));
            }
            if (givenname.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.GIVENNAME, givenname));
            }
            if (initials.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.INITIALS, initials));
            }
            if (mail.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.MAIL, mail));
            }
            if (mobile.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.MOBILE, mobile));
            }
            if (postaladdress.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.POSTALADDRESS, postaladdress));
            }
            if (postalcode.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.POSTALCODE, postalcode));
            }
            if (sn.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.SN, sn));
            }
            if (st.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.ST, st));
            }
            if (telephonenumber.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.TELEPHONENUMBER, telephonenumber));
            }
            if (title.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.TITLE, title));
            }
            if (uid.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.UID, uid));
            }
            if (userpassword.length() != 0) {
                attrs.add(new LDAPAttribute(LDAPinetOrgPerson.USERPASSWORD, userpassword));
            }

            // Create an entry with this DN and these attributes
            final LDAPEntry ldapentry = new LDAPEntry(dn, attrs);

            // Add the entry to the directory
            ldapconnection.add(ldapentry);
        } catch (LDAPException ldape) {
            Logger.getLogger(LDAPPersonSunONE.class.getName()).log(Level.SEVERE, null, ldape);
        } catch (Exception e) {
            Logger.getLogger(LDAPPersonSunONE.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static LDAPinetOrgPerson read(LDAPConnection ldapconnection, String basedn, String uid) {
        String idvalue = "";
        String givenname = "";
        String sn = "";
        String telephonenumber = "";
        String mail = "";
        String facsimiletelephonenumber = "";
        String cn = "";

        try {
            // The old LOF LDAP server places people entries in the root rather than the People group.
            final LDAPSearchResults results = ldapconnection.search(LDAPSCOPE_PERSON.concat(",").concat(basedn), LDAPv2.SCOPE_SUB, "(uid=" + uid + ")", null, false);

            if (results.getCount() > 0) {
                LDAPEntry ldapentry = results.next();
                LDAPAttribute ldapattribute = null;

                LDAPinetOrgPerson ldapinetorgperson = new LDAPinetOrgPerson();

                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_ID)) != null) {
                    idvalue = ldapattribute.getStringValueArray()[0];
                } else {
                    idvalue = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_GIVENNAME)) != null) {
                    givenname = ldapattribute.getStringValueArray()[0];
                } else {
                    givenname = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_SN)) != null) {
                    sn = ldapattribute.getStringValueArray()[0];
                } else {
                    sn = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_TELEPHONENUMBER)) != null) {
                    telephonenumber = ldapattribute.getStringValueArray()[0];
                } else {
                    telephonenumber = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_MAIL)) != null) {
                    mail = ldapattribute.getStringValueArray()[0];
                } else {
                    mail = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_FACSIMILETELEPHONENUMBER)) != null) {
                    facsimiletelephonenumber = ldapattribute.getStringValueArray()[0];
                } else {
                    facsimiletelephonenumber = "";
                }
                if ((ldapattribute = ldapentry.getAttribute(LDAPPERSON_CN)) != null) {
                    cn = ldapattribute.getStringValueArray()[0];
                } else {
                    cn = "";
                }

                ldapinetorgperson.setUid(idvalue);
                ldapinetorgperson.setGivenname(givenname);
                ldapinetorgperson.setSn(sn);
                ldapinetorgperson.setTelephonenumber(telephonenumber);
                ldapinetorgperson.setMail(mail);
                ldapinetorgperson.setFacsimiletelephonenumber(facsimiletelephonenumber);
                ldapinetorgperson.setCn(cn);

                return ldapinetorgperson;
            } else {
                return null;
            }

        } catch (final Exception e) {
            return null;
        }
    }

    public static void update(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void delete(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        String uid = ldapinetorgperson.getUid();
        String dn = "uid=".concat(uid).concat(",").concat("ou=People,").concat(basedn);
        deleteEntry(ldapconnection, dn);
    }

    public static LDAPinetOrgPerson findViaMail(LDAPConnection ldapconnection, String basedn, String mail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static LDAPinetOrgPerson findViaEmployeenumber(LDAPConnection ldapconnection, String basedn, String employeenumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void setPassword(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String setGeneratedPassword(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void deleteEntry(LDAPConnection ldapconnection, String dn) {
        try {
            ldapconnection.delete(dn);
        } catch (LDAPException ex) {
        }
    }
}
