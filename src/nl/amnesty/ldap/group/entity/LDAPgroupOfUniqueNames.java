/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.group.entity;

import java.util.List;
import nl.amnesty.ldap.person.entity.LDAPinetOrgPerson;

/**
 *
 * @author ed
 */
public class LDAPgroupOfUniqueNames {
    // Constants

    public static final String CN = "cn";
    public static final String UNIQUEMEMBER = "uniqueMember";
    // Attributes
    private String cn;
    private String description;
    private List<LDAPinetOrgPerson> ldapinetorgpersonlist;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LDAPinetOrgPerson> getLdapinetorgpersonlist() {
        return ldapinetorgpersonlist;
    }

    public void setLdapinetorgpersonlist(List<LDAPinetOrgPerson> ldapinetorgpersonlist) {
        this.ldapinetorgpersonlist = ldapinetorgpersonlist;
    }

    public boolean isUidPartOf(String uid) {
        for (LDAPinetOrgPerson ldapinetorgperson : this.ldapinetorgpersonlist) {
            if (ldapinetorgperson.getUid().equals(uid)) {
                return true;
            }
        }
        return false;
    }
}
