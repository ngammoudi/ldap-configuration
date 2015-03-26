package org.exoplatform.ldap.model;

/**
 * Created by nagui on 28/01/15.
 */
public class LdapConfigBean {
    private String ldapType;
    private String ldapUrl;
    private String ldapAdminDN;
    private String ldapAuthentication;
    private String ldapUBaseDN;
    private String ldapGBaseDN;
    private String usersId;
    private String usersFilter;
    private String usersMapping;
    private String usersClasses;
    private String usersSearchScope;
    private String groupsId;
    private String groupsFilter;
    private String groupsMapping;
    private String groupsClasses;
    private String connPool;
    private String connPoolMax;
    private String connPoolTimeout;
    private String connPoolProtocol;
    private String ldapReadOnly;
    private String ldapInsensitive;
    private String searchLimit;
    private String groupsSearchScope;
    private String ldapFileConfig;


    public LdapConfigBean() {
    }

    public LdapConfigBean(String ldapType, String ldapUrl, String ldapAdminDN, String ldapAuthentication, String ldapUBaseDN, String ldapGBaseDN, String usersId, String usersFilter, String usersMapping, String usersClasses, String usersSearchScope, String groupsId, String groupsFilter, String groupsMapping, String groupsClasses, String connPool, String connPoolMax, String connPoolTimeout, String connPoolProtocol, String ldapReadOnly, String ldapInsensitive, String searchLimit, String groupsSearchScope) {
        this.ldapType = ldapType;
        this.ldapUrl = ldapUrl;
        this.ldapAdminDN = ldapAdminDN;
        this.ldapAuthentication = ldapAuthentication;
        this.ldapUBaseDN = ldapUBaseDN;
        this.ldapGBaseDN = ldapGBaseDN;
        this.usersId = usersId;
        this.usersFilter = usersFilter;
        this.usersMapping = usersMapping;
        this.usersClasses = usersClasses;
        this.usersSearchScope = usersSearchScope;
        this.groupsId = groupsId;
        this.groupsFilter = groupsFilter;
        this.groupsMapping = groupsMapping;
        this.groupsClasses = groupsClasses;
        this.connPool = connPool;
        this.connPoolMax = connPoolMax;
        this.connPoolTimeout = connPoolTimeout;
        this.connPoolProtocol = connPoolProtocol;
        this.ldapReadOnly = ldapReadOnly;
        this.ldapInsensitive = ldapInsensitive;
        this.searchLimit = searchLimit;
        this.groupsSearchScope = groupsSearchScope;
    }

    public String getLdapType() {
        return ldapType;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }

    public String getLdapAdminDN() {
        return ldapAdminDN;
    }

    public String getLdapAuthentication() {
        return ldapAuthentication;
    }

    public String getLdapUBaseDN() {
        return ldapUBaseDN;
    }

    public String getLdapGBaseDN() {
        return ldapGBaseDN;
    }

    public String getUsersId() {
        return usersId;
    }

    public String getUsersFilter() {
        return usersFilter;
    }

    public String getUsersMapping() {
        return usersMapping;
    }

    public String getUsersClasses() {
        return usersClasses;
    }

    public String getUsersSearchScope() {
        return usersSearchScope;
    }

    public String getGroupsId() {
        return groupsId;
    }

    public String getGroupsFilter() {
        return groupsFilter;
    }

    public String getGroupsMapping() {
        return groupsMapping;
    }

    public String getGroupsClasses() {
        return groupsClasses;
    }

    public String getConnPool() {
        return connPool;
    }

    public String getConnPoolMax() {
        return connPoolMax;
    }

    public String getConnPoolTimeout() {
        return connPoolTimeout;
    }

    public String getConnPoolProtocol() {
        return connPoolProtocol;
    }

    public String getLdapReadOnly() {
        return ldapReadOnly;
    }

    public String getLdapInsensitive() {
        return ldapInsensitive;
    }

    public String getSearchLimit() {
        return searchLimit;
    }

    public String getGroupsSearchScope() {
        return groupsSearchScope;
    }

    public String getLdapFileConfig() {
        return ldapFileConfig;
    }
}
