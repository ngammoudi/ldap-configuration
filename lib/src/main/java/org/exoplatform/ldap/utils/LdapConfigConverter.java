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
    public static final String EXO_LDAP_ADMIN_DN = "exo:ldapAdminDn";
    public static final String EXO_LDAP_ADMIN_PWD = "exo:ldapAuthentication";
    public static final String EXO_LDAP_ADMIN_BASE = "exo:ldapUBaseDn";

    public static LdapConfigBean nodeToBean(Node ldapConfig,LdapConfigBean ldapConfigBean){
       try {
           ldapConfigBean.setLdapType(ldapConfig.getProperty(EXO_LDAP_TYPE).toString());
           ldapConfigBean.setLdapUrl(ldapConfig.getProperty(EXO_LDAP_URL).toString());
           ldapConfigBean.setLdapAdminDn(ldapConfig.getProperty(EXO_LDAP_ADMIN_DN).toString());
           ldapConfigBean.setLdapAuthentication(ldapConfig.getProperty(EXO_LDAP_ADMIN_PWD).toString());
           ldapConfigBean.setLdapUBaseDn(ldapConfig.getProperty(EXO_LDAP_ADMIN_BASE).toString());
       }
       catch (RepositoryException e)
       {
           LOG.error("An error occurred while mapping LdapConfig JCR Node to a Bean: ", e);
       }
        return ldapConfigBean;
    }
  public static Node beanToNode(LdapConfigBean ldapConfigBean, Node ldapConfig){
      try {
          ldapConfig.setProperty(EXO_LDAP_TYPE, ldapConfigBean.getLdapType());
          ldapConfig.setProperty(EXO_LDAP_URL, ldapConfigBean.getLdapUrl());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_DN, ldapConfigBean.getLdapAdminDn());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_PWD, ldapConfigBean.getLdapAuthentication());
          ldapConfig.setProperty(EXO_LDAP_ADMIN_BASE, ldapConfigBean.getLdapUBaseDn());

      }
      catch (RepositoryException e)
      {
          LOG.error("An error occurred while mapping LdapConfig Bean to JCR Node : ", e);
      }
      return ldapConfig;
  }
}
