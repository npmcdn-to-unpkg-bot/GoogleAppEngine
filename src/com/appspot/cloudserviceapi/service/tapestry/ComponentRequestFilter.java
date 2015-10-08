package com.appspot.cloudserviceapi.service.tapestry;

import java.io.IOException;

import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.PageRenderRequestParameters;

/**
 * Filter interface for {@link org.apache.tapestry5.services.ComponentRequestHandler}.
 */
public interface ComponentRequestFilter
{
    /**
     * Handler for a component action request which will trigger an event on a component and use the return value to
     * send a response to the client (typically, a redirect to a page render URL).
     *
     * @param parameters defining the request
     * @param handler    next handler in the pipeline
     */
    void handleComponentEvent(ComponentEventRequestParameters parameters, ComponentRequestHandler handler)
            throws IOException;

    /**
     * Invoked to activate and render a page. In certain cases, based on values returned when activating the page, a
     * {@link org.apache.tapestry5.services.ComponentEventResultProcessor} may be used to send an alternate response
     * (typically, a redirect).
     *
     * @param parameters defines the page name and activation context
     * @param handler    next handler in the pipeline
     */
    void handlePageRender(PageRenderRequestParameters parameters, ComponentRequestHandler handler) throws IOException;
}