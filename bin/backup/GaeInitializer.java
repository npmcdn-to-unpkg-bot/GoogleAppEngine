package wicket;

import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.pageStore.memory.IDataStoreEvictionStrategy;
import org.apache.wicket.pageStore.memory.PageNumberEvictionStrategy;
import org.apache.wicket.util.lang.WicketObjects;

/**
 * A Wicket initializer that configures the application
 * that way so it is possible to run it in Google AppEngine
 */
public class GaeInitializer implements IInitializer {

	public void init(Application application) {
		
		// disable ModificationWatcher
		application.getResourceSettings().setResourcePollFrequency(null);
		
		// use plain JDK Object(Input|Output)Stream
//		WicketObjects.setObjectStreamFactory(new GaeObjectStreamFactory());
		
		// save older version of pages in the HttpSession
		final IDataStoreEvictionStrategy evictionStrategy;
		if (application instanceof GaeApplication) {
			evictionStrategy = ((GaeApplication)application).getEvictionStrategy();
		} else {
			evictionStrategy = new PageNumberEvictionStrategy(10);
		}
		
		application.setPageManagerProvider(new GaePageManagerProvider(application, evictionStrategy));
		
		// disable file cleaning because it starts a new thread
		application.getResourceSettings().setFileCleaner(null);
		System.out.println("GaeInitializer initialized.");
	}

	@Override
	public void destroy(Application arg0) {
		// TODO Auto-generated method stub
		System.out.println("GaeInitializer destroyed.");
	}

}