package org.exoplatform.ldap.services;

import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.ldap.utils.LDAPAuthenticationException;

import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagui on 09/03/15.
 */
public interface LdapAuthenticationService {
    public void checkLdapAuthentication(String ldapUrl,String ldapAdmin,String ldapPassword,String authType)throws LDAPAuthenticationException;
    public List<String> searchUsers(String ldapAdmin, String searchFilter, String searchScope, String usersId) throws NamingException;
    public ArrayList<SearchResult> searchGroups(String groupBaseDN,String groupsFilter,String searchScope,String groupsId) throws NamingException;
    public boolean isConnected();
}
