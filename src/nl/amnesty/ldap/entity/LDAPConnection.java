package nl.amnesty.ldap.entity;

/**
 *
 * @author ed
 */
public class LDAPConnection {
    //
    private com.unboundid.ldap.sdk.LDAPConnection ldapconnectionopends;
    private netscape.ldap.LDAPConnection ldapconnectionsunone;

    public com.unboundid.ldap.sdk.LDAPConnection getLdapconnectionopends() {
        return ldapconnectionopends;
    }

    public void setLdapconnectionopends(com.unboundid.ldap.sdk.LDAPConnection ldapconnectionopends) {
        this.ldapconnectionopends = ldapconnectionopends;
    }

    public netscape.ldap.LDAPConnection getLdapconnectionsunone() {
        return ldapconnectionsunone;
    }

    public void setLdapconnectionsunone(netscape.ldap.LDAPConnection ldapconnectionsunone) {
        this.ldapconnectionsunone = ldapconnectionsunone;
    }

}
