package org.exoplatform.ldap.services;

import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.ldap.utils.LDAPAuthenticationException;

import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;

/**
 * Created by nagui on 09/03/15.
 */
public interface LdapAuthenticationService {
    public void checkLdapAuthentication(String ldapUrl,String ldapAdmin,String ldapPassword,String authType)throws LDAPAuthenticationException;
    public ArrayList<SearchResult> getResultByCustomFilter(String ldapAdmin,String searchFilter,String searchScope) throws NamingException;
    public boolean isConnected();
}
