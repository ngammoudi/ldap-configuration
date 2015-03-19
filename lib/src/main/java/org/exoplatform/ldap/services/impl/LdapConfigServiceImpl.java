package org.exoplatform.ldap.services.impl;

import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.ldap.services.LdapConfigService;
import org.exoplatform.ldap.utils.CoreUtils;
import org.exoplatform.ldap.utils.LdapConfigConverter;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Created by nagui on 29/01/15.
 */
public class LdapConfigServiceImpl implements LdapConfigService{
    private static final Log LOG = ExoLogger.getLogger(LdapConfigService.class);


    @Override
    public void createLdapConfig(LdapConfigBean ldapConfigBean) throws Exception {
         Node ldapConfigNode;
        try
        {
            Node ldapConfigRootNode= CoreUtils.getLdapRootNode() ;
            if (ldapConfigRootNode.hasNodes()){
                ldapConfigNode=ldapConfigRootNode.getNode(LdapConfigConverter.EXO_LDAP_CONFIG)   ;
            }
            else{
                ldapConfigNode=ldapConfigRootNode.addNode(LdapConfigConverter.EXO_LDAP_CONFIG);
            }

            LdapConfigConverter.beanToNode(ldapConfigBean,ldapConfigNode);
            ldapConfigRootNode.save();
            ldapConfigRootNode.getSession().logout();
        }
        catch (RepositoryException e)
        {
            LOG.error("Error while saving ldapConfigNode: ", e);
        }
    }
}
