package org.exoplatform.ldap.model;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.ldap.utils.CoreUtils;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.naming.Context;

/**
 * Created by nagui on 28/01/15.
 */
public class LdapConfigBean {
    private String ldapType ;
    private String ldapUrl;
    private String ldapAdminDn;
    private String ldapAuthentication;
    private String ldapUBaseDn;



    public LdapConfigBean(String ldapType, String ldapUrl, String ldapAdminDn, String ldapAuthentication, String ldapUBaseDn) {
        this.ldapType = ldapType;
        this.ldapUrl = ldapUrl;
        this.ldapAdminDn = ldapAdminDn;
        this.ldapAuthentication = ldapAuthentication;
        this.ldapUBaseDn = ldapUBaseDn;
    }



    public LdapConfigBean() {
    }

    public String getLdapUBaseDn() {
        return ldapUBaseDn;
    }

    public void setLdapUBaseDn(String ldapUBaseDn) {
        this.ldapUBaseDn = ldapUBaseDn;
    }

    public String getLdapAuthentication() {
        return ldapAuthentication;
    }

    public void setLdapAuthentication(String ldapAuthentication) {
        this.ldapAuthentication = ldapAuthentication;
    }

    public String getLdapAdminDn() {
        return ldapAdminDn;
    }

    public void setLdapAdminDn(String ldapAdminDn) {
        this.ldapAdminDn = ldapAdminDn;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getLdapType() {
        return ldapType;
    }

    public void setLdapType(String ldapType) {
        this.ldapType = ldapType;
    }

}
