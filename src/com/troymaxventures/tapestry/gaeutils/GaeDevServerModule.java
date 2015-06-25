package com.troymaxventures.tapestry.gaeutils;

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;

/**
 *
 * @author polrtex
 */
@SubModule(com.plannow.tapestry5.ckeditor.services.CkEditorModule.class)
public class GaeDevServerModule {

    @Contribute(RequestHandler.class)
    public static void addRedirectCommitedFixFilter(OrderedConfiguration<RequestFilter> config) {
        config.addInstance("RedirectCommitedFixFilter", RedirectFixRequestFilter.class);
    }
}