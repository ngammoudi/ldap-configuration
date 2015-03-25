package org.exoplatform.juzu.ldapConfig;

import juzu.*;
import juzu.io.Stream;
import juzu.plugin.ajax.Ajax;
import juzu.request.RenderContext;
import org.apache.log4j.Logger;
import org.exoplatform.ldap.model.LdapConfigBean;
import org.exoplatform.ldap.services.LdapAuthenticationService;
import org.exoplatform.ldap.services.LdapConfigService;
import org.exoplatform.ldap.utils.LdapParameters;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.*;

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
    private String usersMapping;
    private String usersClasses;
    private String usersSearchScope;
    private String groupsId;
    private String groupsFilter;
    private String groupsMapping;
    private String groupsClasses;
    private String groupsSearchScope;
    private String connPoolMax;
    private String connPoolTimeout;
    private String searchLimit;
    private String message;
    private String connectionSuccessLabel;
    private String connectionFailureLabel;


    @Inject
    LdapConfigService ldapConfigService ;

    @Inject
    LdapAuthenticationService ldapAuthenticationService;
    @Inject
    @Path("index.gtmpl")
    org.exoplatform.juzu.ldapConfig.templates.index index;



    @View
    public Response.Content index(RenderContext renderContext) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        ResourceBundle rs = renderContext.getApplicationContext().resolveBundle(renderContext.getUserContext().getLocale());
        loadDefaultSettings();
        loadResourceBundle(parameters, rs);
        setDefaultSettings(parameters);
        return index.with(parameters).ok();

    }

    private void setDefaultSettings(Map<String, Object> parameters) {
        parameters.put("ldapUrl", ldapUrl);
        parameters.put("ldapType", ldapType);
        parameters.put("ldapAdminDN", ldapAdminDN);
        parameters.put("ldapAdminPwd", ldapAdminPwd);
        parameters.put("ldapAuthType", ldapAuthType);
        parameters.put("ldapUBaseDN", ldapUBaseDN);
        parameters.put("ldapGBaseDN", ldapGBaseDN);
        parameters.put("usersId", usersId);
        parameters.put("usersFilter", usersFilter);
        parameters.put("usersMapping", usersMapping);
        parameters.put("usersClasses", usersClasses);
        parameters.put("usersSearchScope", usersSearchScope);
        parameters.put("groupsId", groupsId);
        parameters.put("groupsFilter", groupsFilter);
        parameters.put("groupsMapping", groupsMapping);
        parameters.put("groupsClasses", groupsClasses);
        parameters.put("groupsSearchScope", groupsSearchScope);
        parameters.put("connPoolMax", connPoolMax);
        parameters.put("connPoolTimeout", connPoolTimeout);
        parameters.put("searchLimit", searchLimit);
    }

    private void loadResourceBundle(Map<String, Object> parameters, ResourceBundle rs) {
        parameters.put("ldapTypeLabel", rs.getString("ldap.type.label"));
        parameters.put("ldapUrlLabel", rs.getString("ldap.url.label"));
        parameters.put("ldapAdminDNLabel", rs.getString("ldap.admin.dn.label"));
        parameters.put("ldapPwdLabel", rs.getString("ldap.password.label"));
        parameters.put("ldapAuthLabel", rs.getString("ldap.authentication.label"));
        parameters.put("ldapGBaseLabel", rs.getString("ldap.groups.base.label"));
        parameters.put("resetLabel", rs.getString("ldap.reset.label"));
        parameters.put("testLabel", rs.getString("ldap.test.label"));
        parameters.put("saveLabel", rs.getString("ldap.save.label"));
        parameters.put("searchLabel", rs.getString("ldap.search.label"));
        parameters.put("ldapUBaseLabel", rs.getString("ldap.users.base.label"));
        parameters.put("ldapUsersLabel", rs.getString("ldap.users.id.label"));
        parameters.put("ldapUsersFilterLabel", rs.getString("ldap.users.filter.label"));
        parameters.put("ldapUsersAttrLabel", rs.getString("ldap.users.attribute.label"));
        parameters.put("ldapUserClsLabel", rs.getString("ldap.users.classes.label"));
        parameters.put("ldapUsersSearchLabel", rs.getString("ldap.users.search.label"));
        parameters.put("ldapDescriptionLabel", rs.getString("ldap.description.label"));
        parameters.put("ldapAuthenticationLabel", rs.getString("ldap.authentications.label"));
        parameters.put("ldapSettingsLabel", rs.getString("ldap.settings.label"));
        parameters.put("ldapGroupsLabel", rs.getString("ldap.groups.id.label"));
        parameters.put("ldapGroupsFilterLabel", rs.getString("ldap.groups.filter.label"));
        parameters.put("ldapGroupsAttrLabel", rs.getString("ldap.groups.attribute.label"));
        parameters.put("ldapGroupsClsLabel", rs.getString("ldap.groups.classes.label"));
        parameters.put("ldapGroupsSearchLabel", rs.getString("ldap.groups.search.label"));
        parameters.put("connPoolMaxLabel", rs.getString("ldap.pool.max.label"));
        parameters.put("connPoolTimeoutLabel", rs.getString("ldap.pool.timeout.label"));
        parameters.put("connPoolProtocolLabel", rs.getString("ldap.pool.protocol.label"));
        parameters.put("ldapSearchLimitLabel", rs.getString("ldap.search.limit.label"));
        parameters.put("connectionLabel", rs.getString("ldap.connection.label"));
        parameters.put("groupsLabel", rs.getString("ldap.groups.label"));
        parameters.put("usersLabel", rs.getString("ldap.users.label"));
        parameters.put(connectionSuccessLabel, rs.getString("ldap.connection.success.label"));
        parameters.put(connectionFailureLabel, rs.getString("ldap.connection.failure.label"));
    }

    private void loadDefaultSettings() {
        // load default values from configuration.properties.
        ldapUrl = System.getProperty(LdapParameters.LDAP_CONF_URL);
        ldapType = System.getProperty(LdapParameters.LDAP_CONF_TYPE);
        ldapAdminDN = System.getProperty(LdapParameters.LDAP_CONF_ADMIN_DN);
        ldapAdminPwd = System.getProperty(LdapParameters.LDAP_CONF_ADMIN_DN_PWD);
        ldapAuthType = System.getProperty(LdapParameters.LDAP_CONF_AUTH_TYPE);
        ldapUBaseDN = System.getProperty(LdapParameters.LDAP_CONF_USERS_BASE_DN);
        ldapGBaseDN = System.getProperty(LdapParameters.LDAP_CONF_GROUPS_BASE_DN);
        usersId = System.getProperty(LdapParameters.LDAP_CONF_USERS_ID);
        usersFilter = System.getProperty(LdapParameters.LDAP_CONF_USERS_FILTER);
        usersMapping = System.getProperty(LdapParameters.LDAP_CONF_USERS_MAPPING);
        usersClasses = System.getProperty(LdapParameters.LDAP_CONF_USERS_CLASSES);
        usersSearchScope = System.getProperty(LdapParameters.LDAP_CONF_USERS_SEARCH_SCOPE);
        groupsId = System.getProperty(LdapParameters.LDAP_CONF_USERS_GROUPS_ID);
        groupsFilter = System.getProperty(LdapParameters.LDAP_CONF_USERS_GROUPS_FILTER);
        groupsMapping = System.getProperty(LdapParameters.LDAP_CONF_USERS_GROUPS_MAPPING);
        groupsClasses = System.getProperty(LdapParameters.LDAP_CONF_GROUPS_CLASSES);
        groupsSearchScope = System.getProperty(LdapParameters.LDAP_CONF_GROUPS_SEARCH_SCOPE);
        connPoolMax = System.getProperty(LdapParameters.LDAP_CONF_MAX_POOL);
        connPoolTimeout= System.getProperty(LdapParameters.LDAP_CONF_POOL_TIMEOUT);
        searchLimit= System.getProperty(LdapParameters.LDAP_CONF_SEARCH_LIMIT);
    }

    @Ajax
    @Resource
    public Response.Content<Stream.Char> createLdapConfig(String ldapType, String ldapUrl, String ldapAdminDN, String ldapAuthType, String ldapUBaseDN, String ldapGBaseDN, String usersId, String usersFilter, String usersMapping, String usersClasses, String usersSearchScope, String groupsId, String groupsFilter, String groupsMapping, String groupsClasses, String connPool, String connPoolMax, String connPoolTimeout, String connPoolProtocol, String ldapReadOnly, String ldapInsensitive, String searchLimit,String groupsSearchScope ) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        LdapConfigBean ldapConfigBean = prepareLdapConfigBean(ldapType, ldapUrl, ldapAdminDN, ldapAuthType, ldapUBaseDN, ldapGBaseDN, usersId, usersFilter, usersMapping, usersClasses, usersSearchScope, groupsId, groupsFilter, groupsMapping, groupsClasses, connPool, connPoolMax, connPoolTimeout, connPoolProtocol, ldapReadOnly, ldapInsensitive, searchLimit, groupsSearchScope, parameters);

        try{
            parameters.put("message","ok");
            ldapConfigService.createLdapConfig(ldapConfigBean);
        }

        catch (Exception e){
            parameters.put("connectionFailureLabel",connectionFailureLabel);
        }
        return createJSON(parameters);
    }

    private LdapConfigBean prepareLdapConfigBean(String ldapType, String ldapUrl, String ldapAdminDN, String ldapAuthType, String ldapUBaseDN, String ldapGBaseDN, String usersId, String usersFilter, String usersMapping, String usersClasses, String usersSearchScope, String groupsId, String groupsFilter, String groupsMapping, String groupsClasses, String connPool, String connPoolMax, String connPoolTimeout, String connPoolProtocol, String ldapReadOnly, String ldapInsensitive, String searchLimit, String groupsSearchScope, Map<String, String> parameters) {
        LdapConfigBean ldapConfigBean = new LdapConfigBean(ldapType,ldapUrl,ldapAdminDN,ldapAuthType,ldapUBaseDN,ldapGBaseDN,usersId,usersFilter,usersMapping,usersClasses,usersSearchScope,groupsId,groupsFilter,groupsMapping,groupsClasses,connPool,connPoolMax,connPoolTimeout,connPoolProtocol,ldapReadOnly,ldapInsensitive,searchLimit,groupsSearchScope);
        parameters.put("ldapType", ldapType);
        parameters.put("ldapUrl", ldapUrl);
        parameters.put("ldapAdminDN",ldapAdminDN);
        parameters.put("ldapAuthType",ldapAuthType);
        parameters.put("ldapUBaseDN",ldapUBaseDN);
        parameters.put("ldapGBaseDN",ldapGBaseDN);
        parameters.put("usersId",usersId);
        parameters.put("usersFilter",usersFilter);
        parameters.put("usersMapping",usersMapping);
        parameters.put("usersClasses",usersClasses);
        parameters.put("usersSearchScope",usersSearchScope);
        parameters.put("groupsId",groupsId);
        parameters.put("groupsFilter",groupsFilter);
        parameters.put("groupsMapping",groupsMapping);
        parameters.put("groupsClasses",groupsClasses);
        parameters.put("groupsSearchScope",groupsSearchScope);
        parameters.put("connPool",connPool);
        parameters.put("connPoolMax",connPoolMax);
        parameters.put("connPoolTimeout",connPoolTimeout);
        parameters.put("connPoolProtocol",connPoolProtocol);
        parameters.put("ldapReadOnly",ldapReadOnly);
        parameters.put("ldapInsensitive",ldapInsensitive);
        parameters.put("searchLimit",searchLimit);
        return ldapConfigBean;
    }

    @Ajax
    @Resource
    public Response.Content<Stream.Char> checkConnection(String ldapUrl,String ldapAdminDN,String ldapAdminPwd,String ldapAuthType) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("ldapUrl", ldapUrl);
        parameters.put("ldapAdminDN",ldapAdminDN);
        parameters.put("ldapAdminPwd",ldapAdminPwd);
        parameters.put("ldapAuthType",ldapAuthType);

        try{
            ldapAuthenticationService.checkLdapAuthentication(ldapUrl,ldapAdminDN,ldapAdminPwd,ldapAuthType);
            parameters.put("message","ok");
            parameters.put("connectionSuccessLabel",connectionSuccessLabel);
        }
        catch (Exception e){
            parameters.put("connectionFailureLabel",connectionFailureLabel);
        }



        return createJSON(parameters);
    }
    @Ajax
    @Resource
    public Response.Content<Stream.Char> resetSettings() {
        Map<String, String> parameters = new HashMap<String, String>();
        ldapUrl=System.getProperty(LdapParameters.LDAP_CONF_URL) ;
        ldapType=System.getProperty(LdapParameters.LDAP_CONF_TYPE) ;
        ldapAdminDN=System.getProperty(LdapParameters.LDAP_CONF_ADMIN_DN);
        ldapAdminPwd=System.getProperty(LdapParameters.LDAP_CONF_ADMIN_DN_PWD) ;
        ldapAuthType=System.getProperty(LdapParameters.LDAP_CONF_AUTH_TYPE) ;
        ldapUBaseDN=System.getProperty(LdapParameters.LDAP_CONF_USERS_BASE_DN) ;
        ldapGBaseDN=System.getProperty(LdapParameters.LDAP_CONF_GROUPS_BASE_DN) ;
        parameters.put("ldapUrl",ldapUrl);
        parameters.put("ldapType",ldapType);
        parameters.put("ldapAdminDN",ldapAdminDN);
        parameters.put("ldapAdminPwd",ldapAdminPwd);
        parameters.put("ldapAuthType",ldapAuthType);
        parameters.put("ldapUBaseDN",ldapUBaseDN);
        parameters.put("ldapGBaseDN",ldapGBaseDN);
        return createJSON(parameters);
    }
    @Ajax
    @Resource
    public Response.Content<Stream.Char> searchUsers(String ldapUBaseDN, String usersFilter,String usersSearch,String usersId)throws NamingException{
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("ldapUBaseDN",ldapUBaseDN) ;
        parameters.put("usersFilter",usersFilter) ;
        parameters.put("usersSearch",usersSearch) ;
        parameters.put("usersId",usersId) ;
        try{
            List<String> users=ldapAuthenticationService.searchUsers(ldapUBaseDN, usersFilter, usersSearch, usersId);
            int size=users.size();
            parameters.put("searchResults", String.valueOf(size))    ;
            parameters.put("users", users.toString())    ;
            for(String userNames:users){
                parameters.put("message","ok");
                parameters.put("userNames",userNames);
            }
        }
        catch (Exception e){

        }
        return createJSON(parameters);

    }
    @Ajax
    @Resource
    public Response.Content<Stream.Char> searchGroups(String ldapGBaseDN, String groupsFilter,String groupsSearch,String groupsId)throws NamingException{
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("ldapGBaseDN",ldapGBaseDN) ;
        parameters.put("groupsFilter",groupsFilter) ;
        parameters.put("groupsSearch",groupsSearch) ;
        parameters.put("groupsId",groupsId) ;
        try{
            ArrayList<SearchResult> groups=ldapAuthenticationService.searchGroups(ldapGBaseDN, groupsFilter, groupsSearch, groupsId);
            int size=groups.size();
            parameters.put("message","ok");
            parameters.put("searchResults", String.valueOf(size))    ;
            parameters.put("groups", groups.toString())    ;

        }
        catch (Exception e){

        }
        return createJSON(parameters);

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
