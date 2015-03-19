@Application
@Portlet

@Bindings({@Binding(LdapConfigService.class),
        @Binding(LdapAuthenticationService.class)

}
)
@Assets(
        location = AssetLocation.SERVER,
        scripts = {
                @Script(id = "jquery", src = "js/jquery-1.11.0.min.js"),
                @Script(id = "core", src = "js/jquery.ui.core.min.js"),
                @Script(id = "juzu", src = "js/juzuFunctions.js"),
                @Script(id = "iphone-style", src = "js/iphone-style-checkboxes.js"),
                @Script(id = "jquery-ui", src = "js/jquery-ui.js"),
                @Script(id = "ldap", src = "js/ldap.js")
        },
        stylesheets = {
                @Stylesheet(src = "skin/css/jquery-ui.css",location = AssetLocation.SERVER, id = "jquery")
        }
)

package org.exoplatform.juzu.ldapConfig;

import juzu.Application;
import juzu.asset.AssetLocation;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.binding.Binding;
import juzu.plugin.less.Less;
import juzu.plugin.portlet.Portlet;
import juzu.plugin.binding.Bindings;
import org.exoplatform.ldap.services.LdapAuthenticationService;
import org.exoplatform.ldap.services.LdapConfigService;

