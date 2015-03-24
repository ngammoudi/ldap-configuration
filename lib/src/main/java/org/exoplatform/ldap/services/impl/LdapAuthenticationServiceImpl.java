package org.exoplatform.ldap.services.impl;

import org.exoplatform.ldap.services.LdapAuthenticationService;
import org.exoplatform.ldap.utils.LDAPAuthenticationException;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.jar.Attributes;

/**
 * Created by nagui on 09/03/15.
 */
public class LdapAuthenticationServiceImpl implements LdapAuthenticationService {
    private static final Log LOG = ExoLogger.getLogger(LdapAuthenticationServiceImpl.class);
    private String ldapContext="com.sun.jndi.ldap.LdapCtxFactory";
    private boolean connected;
    private InitialDirContext connection;
    Hashtable<String, String> env = new Hashtable<String, String>();
    @Override
    public void checkLdapAuthentication(String ldapUrl,String ldapAdmin,String ldapPassword,String authType)throws LDAPAuthenticationException {



        env.put(Context.INITIAL_CONTEXT_FACTORY, ldapContext);
        if(ldapUrl!=null){
            env.put(Context.PROVIDER_URL, ldapUrl);
        }
        if(authType!=null){
            env.put(Context.SECURITY_AUTHENTICATION, authType);
        }
        if(ldapAdmin!=null){
            env.put(Context.SECURITY_PRINCIPAL, ldapAdmin);
        }
        if(ldapPassword!=null){
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        }


        try {
            connection = new InitialDirContext(env);
            connected = true;
            //connection.close();
            LOG.info("Connection Success");

        } catch (Exception namEx) {
            throw new LDAPAuthenticationException ();
        }

    }

    @Override
    public ArrayList<SearchResult> getResultByCustomFilter(String ldapUBaseDN,String searchFilter,String searchScope) throws NamingException {
        SearchControls searchControls = new SearchControls();
        if(searchScope.equals("subTree")){
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        }
        else if(searchScope.equals("oneLevel")){
            searchControls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

        }
        else
            searchControls.setSearchScope(SearchControls.OBJECT_SCOPE);

        connection = new InitialDirContext(env);
        NamingEnumeration<SearchResult> results = connection.search(ldapUBaseDN, searchFilter, searchControls);

        if (results.hasMoreElements()) {
            ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
            while (results.hasMore()) {
                searchResults.add(results.next());
            }
            return searchResults;


        }
        return null;
    }

    /**
     * Return true if connected
     *
     * @return true if connected
     */
    public boolean isConnected() {
        return connected;
    }

}
