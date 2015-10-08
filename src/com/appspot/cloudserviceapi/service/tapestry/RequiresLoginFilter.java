package com.appspot.cloudserviceapi.service.tapestry;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

public class RequiresLoginFilter implements ComponentRequestFilter {

	private PageRenderLinkSource renderLinkSource;

	private ComponentSource componentSource;

	private Response response;

	private AuthenticationService authService;

	public void PageAccessFilter(PageRenderLinkSource renderLinkSource,
			ComponentSource componentSource, Response response,
			AuthenticationService authService) {
		this.renderLinkSource = renderLinkSource;
		this.componentSource = componentSource;
		this.response = response;
		this.authService = authService;
	}

	public void handleComponentEvent(
			ComponentEventRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getActivePageName())) {
			return;
		}

		handler.handleComponentEvent(parameters);

	}

	public void handlePageRender(PageRenderRequestParameters parameters,
			ComponentRequestHandler handler) throws IOException {

		if (dispatchedToLoginPage(parameters.getLogicalPageName())) {
			return;
		}

		handler.handlePageRender(parameters);
	}

	private boolean dispatchedToLoginPage(String pageName) throws IOException {

		if (authService.isLoggedIn()) {
			return false;
		}

		Component page = (Component) componentSource.getPage(pageName);

		if (!page.getClass().isAnnotationPresent(RequiresLogin.class)) {
			return false;
		}

		Link link = renderLinkSource.createPageRenderLink("Login");

		response.sendRedirect(link);

		return true;
	}
}