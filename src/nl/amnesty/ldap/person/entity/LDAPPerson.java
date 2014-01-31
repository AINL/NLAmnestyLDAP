/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.ldap.person.entity;

/**
 *
 * @author evelzen
 */
public class LDAPPerson {

    // Constants
    public static final String CN = "cn";
    public static final String SN = "sn";
    public static final String DESCRIPTION = "description";
    public static final String SEEALSO = "seeAlso";
    public static final String TELEPHONENUMBER = "telephoneNumber";
    public static final String USERPASSWORD = "userPassword";
    // Attributes
    private String cn;
    private String sn;
    private String description;
    private String seealso;
    private String telephonenumber;
    private String userpassword;

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

    public String getSeealso() {
        return seealso;
    }

    public void setSeealso(String seealso) {
        this.seealso = seealso;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

}