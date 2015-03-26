package org.exoplatform.ldap.utils;

import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by nagui on 27/02/15.
 */
public class LdapConfigConverter {
    private static final Log LOG = ExoLogger.getLogger(LdapConfigConverter.class);
    public static final String EXO_LDAP_CONFIG = "exo:ldapConfig";
    public static final String EXO_LDAP_TYPE = "exo:ldapType";
    public static final String EXO_LDAP_URL = "exo:ldapUrl";
    public static final String EXO_LDAP_ADMIN_DN = "exo:ldapAdminDN";
    public static final String EXO_LDAP_ADMIN_PWD = "exo:ldapAuthentication";
    public static final String EXO_LDAP_ADMIN_U_BASE = "exo:ldapUBaseDN";
    public static final String EXO_LDAP_ADMIN_G_BASE = "exo:ldapGBaseDN";
    public static final String EXO_LDAP_POOL="exo:ldapPool";
    public static final String EXO_LDAP_POOL_MAX="exo:ldapPoolMax";
    public static final String EXO_LDAP_POOL_TIMEOUT="exo:ldapPoolTimeout";
    public static final String EXO_LDAP_POOL_PROTOCOL="exo:ldapPoolProtocol";
    public static final String EXO_LDAP_READ_ONLY="exo:ldapRO";
    public static final String EXO_LDAP_SEARCH_LIMIT="exo:ldapSearchLimit";
    public static final String EXO_LDAP_INSENSITIVE="exo:ldapInsensitive";
    public static final String EXO_LDAP_USERS_ID="exo:ldapUsersId";
    public static final String EXO_LDAP_USERS_FILTER="exo:ldapUsersFilter";
    public static final String EXO_LDAP_USERS_MAPPING="exo:ldapUsersMapping";
    public static final String EXO_LDAP_USERS_CLASSES="exo:ldapUsersClasses";
    public static final String EXO_LDAP_USERS_SEARCH_SCOPE="exo:usersSearchScope";
    public static final String EXO_LDAP_GROUPS_ID="exo:ldapGroupsId";
    public static final String EXO_LDAP_GROUPS_FILTER="exo:ldapGroupsFilter";
    public static final String EXO_LDAP_GROUPS_MAPPING="exo:ldapGroupsMapping";
    public static final String EXO_LDAP_GROUPS_CLASSES="exo:ldapGroupsClasses";
    public static final String EXO_LDAP_GROUPS_SEARCH_SCOPE="exo:groupsSearchScope";
    public static final String EXO_LDAP_CONF_FILE="exo:ldapFileConfig";

  public static Node beanToNode(LdapConfigBean ldapConfigBean, Node ldapConfig){
      try {
          ldapConfig.setProperty(EXO_LDAP_TYPE, ldapConfigBean.getLdapType());
          ldapConfig.setProperty(EXO_LDAP_URL, ldapConfigBean.getLdapUrl());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_DN, ldapConfigBean.getLdapAdminDN());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_PWD, ldapConfigBean.getLdapAuthentication());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_U_BASE, ldapConfigBean.getLdapUBaseDN());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_G_BASE, ldapConfigBean.getLdapGBaseDN());
          ldapConfig.setProperty(EXO_LDAP_POOL, ldapConfigBean.getConnPool());
          ldapConfig.setProperty(EXO_LDAP_POOL_TIMEOUT, ldapConfigBean.getConnPoolTimeout());
          ldapConfig.setProperty(EXO_LDAP_POOL_MAX, ldapConfigBean.getConnPoolMax());
          ldapConfig.setProperty(EXO_LDAP_POOL_PROTOCOL, ldapConfigBean.getConnPoolProtocol());
          ldapConfig.setProperty(EXO_LDAP_READ_ONLY, ldapConfigBean.getLdapReadOnly());
          ldapConfig.setProperty(EXO_LDAP_SEARCH_LIMIT, ldapConfigBean.getSearchLimit());
          ldapConfig.setProperty(EXO_LDAP_INSENSITIVE, ldapConfigBean.getLdapInsensitive());
          ldapConfig.setProperty(EXO_LDAP_USERS_ID, ldapConfigBean.getUsersId());
          ldapConfig.setProperty(EXO_LDAP_USERS_FILTER, ldapConfigBean.getUsersFilter());
          ldapConfig.setProperty(EXO_LDAP_USERS_MAPPING, ldapConfigBean.getUsersMapping());
          ldapConfig.setProperty(EXO_LDAP_USERS_CLASSES, ldapConfigBean.getUsersClasses());
          ldapConfig.setProperty(EXO_LDAP_USERS_SEARCH_SCOPE, ldapConfigBean.getUsersSearchScope());
          ldapConfig.setProperty(EXO_LDAP_GROUPS_ID, ldapConfigBean.getGroupsId());
          ldapConfig.setProperty(EXO_LDAP_GROUPS_FILTER, ldapConfigBean.getGroupsFilter());
          ldapConfig.setProperty(EXO_LDAP_GROUPS_MAPPING, ldapConfigBean.getGroupsMapping());
          ldapConfig.setProperty(EXO_LDAP_GROUPS_CLASSES, ldapConfigBean.getGroupsClasses());
          ldapConfig.setProperty(EXO_LDAP_GROUPS_SEARCH_SCOPE, ldapConfigBean.getGroupsSearchScope());
          ldapConfig.setProperty(EXO_LDAP_CONF_FILE, ldapConfigBean.getLdapFileConfig());


      }
      catch (RepositoryException e)
      {
          LOG.error("An error occurred while mapping LdapConfig Bean to JCR Node : ", e);
      }
      return ldapConfig;
  }
}
