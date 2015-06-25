package web.gae.spring;

/**
 * Source: http://code.google.com/p/googleappengine/issues/detail?id=1381
 * 
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class GaePersistenceManagerFactoryHelper {

	private static PersistenceManagerFactory pmf;
	private static PersistenceManagerFactory proxy;

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		if (proxy != null)
			return proxy;
		pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return method.invoke(pmf, args);
			}
		};
		proxy = (PersistenceManagerFactory) Proxy.newProxyInstance(pmf
				.getClass().getClassLoader(),
				new Class[] { PersistenceManagerFactory.class }, handler);
		return proxy;
	}
}