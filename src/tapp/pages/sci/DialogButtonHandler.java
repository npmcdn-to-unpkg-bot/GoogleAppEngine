package tapp.pages.sci;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * http://stackoverflow.com/questions/9640913/tapestry-5-jquery-ui-dialog-and-close-action
 *
 */
public class DialogButtonHandler {
	@Parameter(value = "dlgId", defaultPrefix = BindingConstants.LITERAL)
	private String dlgId;

	@Inject
	private JavaScriptSupport javaScriptSupport;

	@InjectContainer
	private ClientElement element;

	@AfterRender
	public void afterRender() {
		javaScriptSupport.addScript(
				"$('#%s').click(function(){$('#%s').dialog('close');});",
				element.getClientId(), dlgId);
	}
}