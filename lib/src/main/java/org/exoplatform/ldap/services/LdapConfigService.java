package org.exoplatform.ldap.services;

import org.exoplatform.ldap.model.LdapConfigBean;
import javax.inject.Named;
import javax.jcr.Node;

/**
 * Created by nagui on 29/01/15.
 */
@Named("LdapConfigService")
public interface LdapConfigService {
    public void createLdapConfig(LdapConfigBean ldapConfigBean) throws Exception;


}
