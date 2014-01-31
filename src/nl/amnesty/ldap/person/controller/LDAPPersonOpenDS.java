/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.person.controller;

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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;

/**
 *
 * @author root
 */
public class LDAPPersonOpenDS {

    public static void create(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        String uid = ldapinetorgperson.getUid();
        //String basedn = "ou=People,dc=groentenman,dc=amnesty,dc=nl";
        String dn = "uid=".concat(uid).concat(",").concat("ou=People,").concat(basedn);

        Collection collection = new ArrayList<Attribute>();

        // All entries must contain a structural objectclass
        collection.add(new Attribute("objectClass", "inetOrgPerson"));

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
        //String userpassword = ldapinetorgperson.getUserpassword();

        if (cn.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.CN, cn));
        } else {
            collection.add(new Attribute(LDAPinetOrgPerson.CN, "null"));
        }
        if (displayname.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.DISPLAYNAME, displayname));
        }
        if (employeenumber.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.EMPLOYEENUMBER, employeenumber));
        }
        if (givenname.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.GIVENNAME, givenname));
        }
        if (initials.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.INITIALS, initials));
        }
        if (mail.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.MAIL, mail));
        }
        if (mobile.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.MOBILE, mobile));
        }
        if (postaladdress.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.POSTALADDRESS, postaladdress));
        }
        if (postalcode.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.POSTALCODE, postalcode));
        }
        if (sn.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.SN, sn));
        } else {
            // sn is a mandatory field which is required by objectclass person
            collection.add(new Attribute(LDAPinetOrgPerson.SN, "null"));
        }
        if (st.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.ST, st));
        }
        if (telephonenumber.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.TELEPHONENUMBER, telephonenumber));
        }
        if (title.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.TITLE, title));
        }
        if (uid.length() != 0) {
            collection.add(new Attribute(LDAPinetOrgPerson.UID, uid));
        }
        //if (userpassword.length() != 0) {
        //    collection.add(new Attribute(LDAPinetOrgPerson.USERPASSWORD, userpassword));
        //}

        addEntry(ldapconnection, dn, collection);
    }

    public static void update(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        String uid = ldapinetorgperson.getUid();
        //String basedn = "ou=People,dc=groentenman,dc=amnesty,dc=nl";
        //String dn = "uid=".concat(uid).concat(",").concat(basedn);
        String dn = "uid=".concat(uid).concat(",").concat("ou=People,").concat(basedn);

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

        if (cn.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.CN, cn);
        } else {
            // cn is a mandatory field which is required by objectclass person
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.CN, "null");
        }
        if (displayname.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.DISPLAYNAME, displayname);
        }
        if (employeenumber.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.EMPLOYEENUMBER, employeenumber);
        }
        if (givenname.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.GIVENNAME, givenname);
        }
        if (initials.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.INITIALS, initials);
        }
        if (mail.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.MAIL, mail);
        }
        if (mobile.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.MOBILE, mobile);
        }
        if (postaladdress.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.POSTALADDRESS, postaladdress);
        }
        if (postalcode.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.POSTALCODE, postalcode);
        }
        if (sn.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.SN, sn);
        } else {
            // sn is a mandatory field which is required by objectclass person
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.SN, "null");
        }
        if (st.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.ST, st);
        }
        if (telephonenumber.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.TELEPHONENUMBER, ldapinetorgperson.getTelephonenumber());
        }
        if (title.length() != 0) {
            modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.TITLE, title);
        }
    }

    public static void delete(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        String uid = ldapinetorgperson.getUid();
        String dn = "uid=".concat(uid).concat(",").concat("ou=People,").concat(basedn);
        deleteEntry(ldapconnection, dn);
    }

    public static LDAPinetOrgPerson read(LDAPConnection ldapconnection, String basedn, String uid) {
        Filter filter = Filter.createEqualityFilter("uid", uid);
        SearchRequest searchrequest = new SearchRequest("ou=People,".concat(basedn), SearchScope.ONE, filter, SearchRequest.ALL_USER_ATTRIBUTES);
        LDAPinetOrgPerson ldapinetorgperson = new LDAPinetOrgPerson();
        try {
            SearchResult searchresult = ldapconnection.search(searchrequest);
            if (searchresult.getEntryCount() == 1) {
                SearchResultEntry searchresultentry = searchresult.getSearchEntries().get(0);
                ldapinetorgperson = setPerson(searchresultentry);
                return ldapinetorgperson;
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

    public static LDAPinetOrgPerson findViaMail(LDAPConnection ldapconnection, String basedn, String mail) {
        Filter filter = Filter.createEqualityFilter(LDAPinetOrgPerson.MAIL, mail);
        SearchRequest searchrequest = new SearchRequest(basedn, SearchScope.ONE, filter, SearchRequest.ALL_USER_ATTRIBUTES);
        LDAPinetOrgPerson ldapinetorgperson = new LDAPinetOrgPerson();
        try {
            SearchResult searchresult = ldapconnection.search(searchrequest);
            if (searchresult.getEntryCount() == 1) {
                SearchResultEntry searchresultentry = searchresult.getSearchEntries().get(0);
                ldapinetorgperson = setPerson(searchresultentry);
                return ldapinetorgperson;
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

    public static LDAPinetOrgPerson findViaEmployeenumber(LDAPConnection ldapconnection, String basedn, String employeenumber) {
        Filter filter = Filter.createEqualityFilter(LDAPinetOrgPerson.EMPLOYEENUMBER, employeenumber);
        SearchRequest searchrequest = new SearchRequest(basedn, SearchScope.ONE, filter, SearchRequest.ALL_USER_ATTRIBUTES);
        LDAPinetOrgPerson ldapinetorgperson = new LDAPinetOrgPerson();
        try {
            SearchResult searchresult = ldapconnection.search(searchrequest);
            if (searchresult.getEntryCount() == 1) {
                SearchResultEntry searchresultentry = searchresult.getSearchEntries().get(0);
                ldapinetorgperson = setPerson(searchresultentry);
                return ldapinetorgperson;
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

    public static void setPassword(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson, String password) {
        String uid = ldapinetorgperson.getUid();
        String dn = "uid=".concat(uid).concat(",ou=People,").concat(basedn);
        modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.USERPASSWORD, password);
    }

    public static String setGeneratedPassword(LDAPConnection ldapconnection, String basedn, LDAPinetOrgPerson ldapinetorgperson) {
        String uid = ldapinetorgperson.getUid();
        String dn = "uid=".concat(uid).concat(",").concat(basedn);
        String password = generatePassword();
        modifyEntry(ldapconnection, dn, LDAPinetOrgPerson.USERPASSWORD, password);
        return password;
    }

    private static void modifyEntry(LDAPConnection ldapconnection, String dn, String attributename, String attributevalue) {
        if (attributename != null && attributename.length() != 0) {
            if (attributevalue == null) {
                attributevalue = "";
            }
            Modification modification = new Modification(ModificationType.REPLACE, attributename, attributevalue);
            ModifyRequest modifyrequest = new ModifyRequest(dn, modification);
            LDAPResult ldapresult;
            try {
                ldapresult = ldapconnection.modify(modifyrequest);
                ResultCode resultcode = ldapresult.getResultCode();

                if (resultcode == ResultCode.SUCCESS) {
                    //DEBUG
                    //System.out.println("LDAPSDK Success");
                } else {
                    System.out.println("LDAPSDK Error: attribute " + attributename + " value " + attributevalue + " resultcode " + resultcode.getName());
                }
            } catch (LDAPException ex) {
                Logger.getLogger(LDAPPersonOpenDS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void addEntry(LDAPConnection ldapconnection, String dn, Collection<Attribute> attributecollection) {
        AddRequest addrequest = new AddRequest(dn, attributecollection);
        LDAPResult ldapresult;
        try {
            ldapresult = ldapconnection.add(addrequest);
            ResultCode resultcode = ldapresult.getResultCode();

            if (resultcode == ResultCode.SUCCESS) {
                //DEBUG
                //System.out.println("Success");
            } else {
                System.out.println(resultcode.getName());
            }
        } catch (LDAPException ex) {
            Logger.getLogger(LDAPPersonOpenDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void deleteEntry(LDAPConnection ldapconnection, String dn) {
        DeleteRequest deleterequest = new DeleteRequest(dn);
        LDAPResult ldapresult = null;
        try {
            ldapresult = ldapconnection.delete(deleterequest);
            if (ldapresult != null) {
                ResultCode resultcode = ldapresult.getResultCode();
                if (resultcode != null) {
                    if (resultcode != ResultCode.SUCCESS) {
                        Logger.getLogger(LDAPPersonOpenDS.class.getName()).log(Level.SEVERE, String.valueOf(resultcode));
                    }
                }
            }
        } catch (LDAPException ex) {
            ResultCode resultcode = ex.getResultCode();
            if (resultcode != ResultCode.NO_SUCH_OBJECT) {
                Logger.getLogger(LDAPPersonOpenDS.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    private static LDAPinetOrgPerson setPerson(SearchResultEntry searchresultentry) {
        LDAPinetOrgPerson ldapinetorgperson = new LDAPinetOrgPerson();

        ldapinetorgperson.setCn(searchresultentry.getAttributeValue(LDAPinetOrgPerson.CN));
        ldapinetorgperson.setDisplayname(searchresultentry.getAttributeValue(LDAPinetOrgPerson.DISPLAYNAME));
        ldapinetorgperson.setEmployeenumber(searchresultentry.getAttributeValue(LDAPinetOrgPerson.EMPLOYEENUMBER));
        ldapinetorgperson.setGivenname(searchresultentry.getAttributeValue(LDAPinetOrgPerson.GIVENNAME));
        ldapinetorgperson.setInitials(searchresultentry.getAttributeValue(LDAPinetOrgPerson.INITIALS));
        ldapinetorgperson.setMail(searchresultentry.getAttributeValue(LDAPinetOrgPerson.MAIL));
        ldapinetorgperson.setMobile(searchresultentry.getAttributeValue(LDAPinetOrgPerson.MOBILE));
        ldapinetorgperson.setPostaladdress(searchresultentry.getAttributeValue(LDAPinetOrgPerson.POSTALADDRESS));
        ldapinetorgperson.setPostalcode(searchresultentry.getAttributeValue(LDAPinetOrgPerson.POSTALCODE));
        ldapinetorgperson.setSn(searchresultentry.getAttributeValue(LDAPinetOrgPerson.SN));
        ldapinetorgperson.setTelephonenumber(searchresultentry.getAttributeValue(LDAPinetOrgPerson.TELEPHONENUMBER));
        ldapinetorgperson.setTitle(searchresultentry.getAttributeValue(LDAPinetOrgPerson.TITLE));
        ldapinetorgperson.setUid(searchresultentry.getAttributeValue(LDAPinetOrgPerson.UID));

        return ldapinetorgperson;
    }

    private static void debugPerson(LDAPinetOrgPerson ldapinetorgperson) {
        System.out.println("CN: " + ldapinetorgperson.getCn());
        System.out.println("DISPLAYNAME: " + ldapinetorgperson.getDisplayname());
        System.out.println("GIVENNAME: " + ldapinetorgperson.getGivenname());
        System.out.println("INITIALS: " + ldapinetorgperson.getInitials());
        System.out.println("MAIL: " + ldapinetorgperson.getMail());
        System.out.println("MOBILE: " + ldapinetorgperson.getMobile());
        System.out.println("POSTALADDRESS: " + ldapinetorgperson.getPostaladdress());
        System.out.println("POSTALCODE: " + ldapinetorgperson.getPostalcode());
        System.out.println("SN: " + ldapinetorgperson.getSn());
        System.out.println("TELEPHONENUMBER: " + ldapinetorgperson.getTelephonenumber());
        System.out.println("UID: " + ldapinetorgperson.getUid());
    }

    private static String generatePassword() {
        int i;
        int index;
        String password = "";
        String array = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+";
        Random random = new Random();
        for (i = 0; i < 8; i++) {
            index = random.nextInt(array.length());
            password = password.concat(String.valueOf(array.charAt(index)));
        }
        return password;
    }
}
