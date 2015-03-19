package org.exoplatform.juzu.ldapConfig;

import juzu.*;
import juzu.io.Stream;
import juzu.plugin.ajax.Ajax;
import juzu.request.RenderContext;
import juzu.template.Template;
import org.apache.log4j.Logger;
import org.exoplatform.container.RootContainer;
import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.ldap.services.LdapAuthenticationService;
import org.exoplatform.ldap.services.LdapConfigService;
import org.exoplatform.ldap.utils.CoreUtils;
import org.exoplatform.ldap.utils.LdapConfigConverter;
import org.exoplatform.ldap.utils.LdapParameters;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.naming.NamingException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by nagui on 27/06/14.
 */
public class LdapController {
    private static final Logger LOG = Logger.getLogger(LdapController.class);
    private String ldapUrl;
    private String ldapType;
    private String ldapAdminDN;
    private String ldapAdminPwd;
    private String ldapAuthType;
    private String ldapUBaseDN;
    private String ldapGBaseDN;
    private String usersId;
    private String usersFilter;
    private String usersAttr;
    private String usersClasses;
    private String usersSearch;
    private String message;

    @Inject
    LdapConfigService ldapConfigService ;
    @Inject
    LdapParameters ldapParameters;
    @Inject
    LdapAuthenticationService ldapAuthenticationService;
    @Inject
    @Path("index.gtmpl")
    org.exoplatform.juzu.ldapConfig.templates.index index;

    @Inject
    @Path("ldap.gtmpl")
    org.exoplatform.juzu.ldapConfig.templates.ldap ldap;


    @View
    public Response.Content index(RenderContext renderContext) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        ResourceBundle rs = renderContext.getApplicationContext().resolveBundle(renderContext.getUserContext().getLocale());

        // load default values from configuration.properties.
        ldapUrl = System.getProperty(ldapParameters.LDAP_CONF_URL);
        ldapType = System.getProperty(ldapParameters.LDAP_CONF_TYPE);
        ldapAdminDN = System.getProperty(ldapParameters.LDAP_CONF_ADMIN_DN);
        ldapAdminPwd = System.getProperty(ldapParameters.LDAP_CONF_ADMIN_DN_PWD);
        ldapAuthType = System.getProperty(ldapParameters.LDAP_CONF_AUTH_TYPE);
        ldapUBaseDN = System.getProperty(ldapParameters.LDAP_CONF_USERS_BASE_DN);
        ldapGBaseDN = System.getProperty(ldapParameters.LDAP_CONF_GROUPS_BASE_DN);
        usersId = System.getProperty(ldapParameters.LDAP_CONF_USERS_ID);
        usersFilter = System.getProperty(ldapParameters.LDAP_CONF_USERS_FILTER);
        usersAttr = System.getProperty(ldapParameters.LDAP_CONF_USERS_MAPPING);
        usersClasses = System.getProperty(ldapParameters.LDAP_CONF_USERS_CLASSES);
        usersSearch = System.getProperty(ldapParameters.LDAP_CONF_USERS_SEARCH_SCOPE);

        parameters.put("ldapTypeLabel", rs.getString("ldap.type.label"));
        parameters.put("ldapUrlLabel", rs.getString("ldap.url.label"));
        parameters.put("ldapAdminDNLabel", rs.getString("ldap.admin.dn.label"));
        parameters.put("ldapPwdLabel", rs.getString("ldap.password.label"));
        parameters.put("ldapAuthLabel", rs.getString("ldap.authentication.label"));
        parameters.put("ldapGBaseLabel", rs.getString("ldap.groups.base.label"));
        parameters.put("resetLabel", rs.getString("ldap.reset.label"));
        parameters.put("testLabel", rs.getString("ldap.test.label"));
        parameters.put("saveLabel", rs.getString("ldap.save.label"));
        parameters.put("ldapUBaseLabel", rs.getString("ldap.users.base.label"));
        parameters.put("ldapUsersLabel", rs.getString("ldap.users.id.label"));
        parameters.put("ldapUsersFilterLabel", rs.getString("ldap.users.filter.label"));
        parameters.put("ldapUsersAttrLabel", rs.getString("ldap.users.attribute.label"));
        parameters.put("ldapUserClsLabel", rs.getString("ldap.users.classes.label"));
        parameters.put("ldapUsersSearchLabel", rs.getString("ldap.users.search.label"));
        parameters.put("ldapDescriptionLabel", rs.getString("ldap.description.label"));
        parameters.put("ldapAuthenticationLabel", rs.getString("ldap.authentications.label"));

        parameters.put("ldapUrl", ldapUrl);
        parameters.put("ldapType", ldapType);
        parameters.put("ldapAdminDN", ldapAdminDN);
        parameters.put("ldapAdminPwd", ldapAdminPwd);
        parameters.put("ldapAuthType", ldapAuthType);
        parameters.put("ldapUBaseDN", ldapUBaseDN);
        parameters.put("ldapGBaseDN", ldapGBaseDN);
        parameters.put("usersId", usersId);
        parameters.put("usersFilter", usersFilter);
        parameters.put("usersAttr", usersAttr);
        parameters.put("usersClasses", usersClasses);
        parameters.put("usersSearch", usersSearch);
        return index.with(parameters).ok();

    }

    @View
    @Route("/ldap/{ldapType}")
    public Response.Content ldap()
    {

        return ldap.ok();

    }
    @Action
    @Route("/createLdapConfig")
    public Response.View createLdapConfig(String ldapType,String ldapUrl,String ldapAdminDn,String ldapAuthentication,String ldapUBaseDn) throws Exception {
            LdapConfigBean ldapConfigBean = new LdapConfigBean(ldapType, ldapUrl, ldapAdminDn, ldapAuthentication, ldapUBaseDn);
            ldapConfigService.createLdapConfig(ldapConfigBean);
            return  LdapController_.ldap();

    }
    @Ajax
    @Resource
    public Response.Content<Stream.Char> checkConnection(String ldapUrl,String ldapAdminDN,String ldapAdminPwd,String ldapAuthType) throws Exception {
        Map<String, String> paramaters = new HashMap<String, String>();
        paramaters.put("ldapUrl",ldapUrl);
        paramaters.put("ldapAdminDN",ldapAdminDN);
        paramaters.put("ldapAdminPwd",ldapAdminPwd);
        paramaters.put("ldapAuthType",ldapAuthType);
            ldapAuthenticationService.checkLdapAuthentication(ldapUrl,ldapAdminDN,ldapAdminPwd,ldapAuthType);
            if(ldapAuthenticationService.isConnected()){
                paramaters.put("message","ok");
            }
        else{
                paramaters.put("message","ko");
            }

        return createJSON(paramaters);



    }
    @Ajax
    @Resource
    public Response.Content<Stream.Char> resetSettings() {
        Map<String, String> paramaters = new HashMap<String, String>();
        ldapUrl=System.getProperty(ldapParameters.LDAP_CONF_URL) ;
        ldapType=System.getProperty(ldapParameters.LDAP_CONF_TYPE) ;
        ldapAdminDN=System.getProperty(ldapParameters.LDAP_CONF_ADMIN_DN);
        ldapAdminPwd=System.getProperty(ldapParameters.LDAP_CONF_ADMIN_DN_PWD) ;
        ldapAuthType=System.getProperty(ldapParameters.LDAP_CONF_AUTH_TYPE) ;
        ldapUBaseDN=System.getProperty(ldapParameters.LDAP_CONF_USERS_BASE_DN) ;
        ldapGBaseDN=System.getProperty(ldapParameters.LDAP_CONF_GROUPS_BASE_DN) ;
        paramaters.put("ldapUrl",ldapUrl);
        paramaters.put("ldapType",ldapType);
        paramaters.put("ldapAdminDN",ldapAdminDN);
        paramaters.put("ldapAdminPwd",ldapAdminPwd);
        paramaters.put("ldapAuthType",ldapAuthType);
        paramaters.put("ldapUBaseDN",ldapUBaseDN);
        paramaters.put("ldapGBaseDN",ldapGBaseDN);
        return createJSON(paramaters);
    }

    /**
     * create a object JSON from the map.
     *
     * @param Map<String, String> data
     * @return Response.Content
     */
    private Response.Content<Stream.Char> createJSON(final Map<String, String> data) {
        Response.Content<Stream.Char> json = new Response.Content<Stream.Char>(200, Stream.Char.class) {
            @Override
            public String getMimeType() {
                return "application/json";
            }

            @Override
            public void send(Stream.Char stream) throws IOException {
                stream.append("{");
                Iterator<Map.Entry<String, String>> i = data.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry<String, String> entry = i.next();
                    stream.append("\"" + entry.getKey() + "\"");
                    stream.append(":");
                    stream.append("\"" + entry.getValue() + "\"");
                    if (i.hasNext()) {
                        stream.append(",");
                    }
                }
                stream.append("}");
            }
        };
        return json;
    }
}
