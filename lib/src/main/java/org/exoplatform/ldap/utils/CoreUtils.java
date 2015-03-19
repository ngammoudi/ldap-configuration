package org.exoplatform.ldap.utils;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.RootContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.ext.hierarchy.NodeHierarchyCreator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

/**
 * Created by nagui on 28/01/15.
 */
public class CoreUtils {

    private static final Log LOG = ExoLogger.getLogger(CoreUtils.class.getName());
    /**
     * Gets the service.
     *
     * @param clazz the clazz
     *
     * @return the service
     */
    public static <T> T getService(Class<T> clazz) {
        return getService(clazz, null);
    }

    /**
     * Gets the service.
     *
     * @param clazz the class
     * @param containerName the container's name
     *
     * @return the service
     */
    public static <T> T getService(Class<T> clazz, String containerName) {
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        if (containerName != null) {
            container = RootContainer.getInstance().getPortalContainer(containerName);
        }
        if (container.getComponentInstanceOfType(clazz)==null) {
            containerName = PortalContainer.getCurrentPortalContainerName();
            container = RootContainer.getInstance().getPortalContainer(containerName);
        }
        return clazz.cast(container.getComponentInstanceOfType(clazz));
    }

    /**
     * Gets the system session provider.
     *
     * @return the system session provider
     */
    public static SessionProvider getSystemSessionProvider() {
        SessionProviderService sessionProviderService = getService(SessionProviderService.class);
        return sessionProviderService.getSystemSessionProvider(null);
    }

    /**
     * Gets the session provider.
     *
     * @return the session provider
     */
    public static SessionProvider getUserSessionProvider() {
        SessionProviderService sessionProviderService = getService(SessionProviderService.class);
        return sessionProviderService.getSessionProvider(null);
    }

    /**
     * Get the current repository
     *
     * @return the current manageable repository
     */
    public static ManageableRepository getRepository() {
        try {
            RepositoryService repositoryService = getService(RepositoryService.class);
            return repositoryService.getCurrentRepository();
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("getRepository() failed because of ", e);
            }
        }
        return null;
    }

    public static Node getLdapRootNode() throws RepositoryException {
        NodeHierarchyCreator nodeHierarchyCreator = getService(NodeHierarchyCreator.class);
        String path = nodeHierarchyCreator.getJcrPath("ldapConfiguration");
        Session session = getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"), getRepository());
        return (Node) session.getItem(path);
    }

    public static Object getPropertyValue(Property property) throws Exception {
        int propertyType = property.getType() ;
        switch(propertyType) {
            case PropertyType.STRING : return property.getValue().getString() ;
            case PropertyType.BOOLEAN : return property.getValue().getBoolean() ;
            case PropertyType.DATE : return property.getValue().getDate() ;
            case PropertyType.DOUBLE : return property.getValue().getDouble() ;
            case PropertyType.LONG : return property.getValue().getLong() ;
            case PropertyType.NAME : return property.getValue().getString() ;
            case PropertyType.UNDEFINED : return property.getValue() ;
        }
        return null ;
    }

    public static QueryResult buildQuery(String statement) throws Exception {
        Session session = getSystemSessionProvider().getSession(System.getProperty("gatein.jcr.workspace.default"), getRepository());
        return session.getWorkspace().getQueryManager().createQuery(statement, Query.SQL).execute();
    }
}
