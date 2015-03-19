package org.exoplatform.ldap.services.impl;

import org.exoplatform.ldap.services.LdapAuthenticationService;
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
import java.util.Hashtable;

/**
 * Created by nagui on 09/03/15.
 */
public class LdapAuthenticationServiceImpl implements LdapAuthenticationService {
    private static final Log LOG = ExoLogger.getLogger(LdapAuthenticationServiceImpl.class);
    private String ldapContext="com.sun.jndi.ldap.LdapCtxFactory";
    private boolean connected;
    private InitialDirContext connection;
    @Override
    public void checkLdapAuthentication(String ldapUrl,String ldapAdmin,String ldapPassword,String authType)throws Exception {

        Hashtable<String, String> env = new Hashtable<String, String>();

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
            connection.close();
            LOG.info("Connection Success");

        } catch (Exception namEx) {
            LOG.info("Connection failure",namEx);
            connected=false;
        }

    }

    @Override
    public ArrayList<SearchResult> getResultByCustomFilter(String ldapAdmin,String searchFilter) throws NamingException {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = connection.search(ldapAdmin, searchFilter, searchControls);

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
