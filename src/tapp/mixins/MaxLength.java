package tapp.mixins;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;

/**
 * Enforces maxlength on a textfield or a textarea
 * @source http://tinybits.blogspot.com/2009/04/enforce-max-length-on-textareas-and_15.html
 * @author Inge
 * 
 */
@Import(library = "maxLength.js")
public class MaxLength {

	@InjectContainer
	private ClientElement container;

	@Parameter(required = true)
	private int max;

	@Parameter("true")
	private boolean displayCounter;

	@Environmental
	private RenderSupport renderSupport;

	void afterRender(MarkupWriter writer) {
		String id = container.getClientId();
		String counterId = id + "-counter";
		writer.element("div", "id", counterId + "-container");
		if (!displayCounter) {
			writer.attributes("style", "display: none");
		}
		writer.element("input", "type", "text", "id", counterId, "disabled",
				"disabled", "size", "3");
		writer.end();
		writer.end();
		renderSupport.addScript("new MaxLength('%s', '%s', %s)", id, counterId,
				max);
	}
}