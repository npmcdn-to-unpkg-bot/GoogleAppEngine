package app.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.model.Calendar;

/**
 * The class <code>CalendarHandlerTest</code> contains tests for the class <code>{@link CalendarHandler}</code>.
 *
 * @generatedBy CodePro at 8/31/13 8:15 AM
 * @author macbook
 * @version $Revision: 1.0 $
 */
public class CalendarHandlerTest {
	/**
	 * Run the CalendarHandler() constructor test.
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testCalendarHandler_1()
		throws Exception {
		CalendarHandler result = new CalendarHandler();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void contextDestroyed(ServletContextEvent) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testContextDestroyed_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		ServletContextEvent arg0 = new ServletContextEvent(new MockServletContext());

		fixture.contextDestroyed(arg0);

		// add additional test code here
	}

	/**
	 * Run the void contextInitialized(ServletContextEvent) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testContextInitialized_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		ServletContextEvent arg0 = new ServletContextEvent(new MockServletContext());

		fixture.contextInitialized(arg0);

		// add additional test code here
	}

	/**
	 * Run the long deleteItemsByPattern(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDeleteItemsByPattern_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		String pattern = "";

		long result = fixture.deleteItemsByPattern(pattern);

		// add additional test code here
		assertEquals(0L, result);
	}

	/**
	 * Run the Object doCreateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoCreateItem_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = new Calendar();

		Object result = fixture.doCreateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doCreateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoCreateItem_2()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = new Calendar();

		Object result = fixture.doCreateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doCreateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoCreateItem_3()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = new Calendar();

		Object result = fixture.doCreateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doCreateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoCreateItem_4()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = new Calendar();

		Object result = fixture.doCreateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoDeleteItem_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		long itemId = 1L;

		Object result = fixture.doDeleteItem(itemId);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoDeleteItem_2()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		long itemId = 1L;

		Object result = fixture.doDeleteItem(itemId);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.Exception.class)
	public void testDoDeleteItem_3()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		long itemId = 1L;

		Object result = fixture.doDeleteItem(itemId);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoDeleteItem_4()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setTitle("");
		item.setEnd("");
		item.setId(new Long(1L));
		item.setStart("");

		Object result = fixture.doDeleteItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoDeleteItem_5()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setTitle("");
		item.setEnd("");
		item.setId(new Long(1L));
		item.setStart("");

		Object result = fixture.doDeleteItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoDeleteItem_6()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = null;

		Object result = fixture.doDeleteItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.Exception.class)
	public void testDoDeleteItem_7()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setId(new Long(1L));

		Object result = fixture.doDeleteItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doDeleteItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.Exception.class)
	public void testDoDeleteItem_8()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setId(new Long(1L));

		Object result = fixture.doDeleteItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doGetItem(long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoGetItem_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		long itemId = 1L;

		Object result = fixture.doGetItem(itemId);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String doGetItems() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoGetItems_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();

		String result = fixture.doGetItems();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String doGetItems() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoGetItems_2()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();

		String result = fixture.doGetItems();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String doGetItems() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoGetItems_3()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();

		String result = fixture.doGetItems();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String doGetItems() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoGetItems_4()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();

		String result = fixture.doGetItems();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doUpdateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoUpdateItem_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setTitle("");
		item.setEnd("");
		item.setUrl("");
		item.setId(new Long(1L));
		item.setDescription("");
		item.setAllDay(new Boolean(true));
		item.setStart("");

		Object result = fixture.doUpdateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doUpdateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testDoUpdateItem_2()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Calendar item = new Calendar();
		item.setTitle("");
		item.setEnd("");
		item.setUrl("");
		item.setId(new Long(1L));
		item.setDescription("");
		item.setAllDay(new Boolean(true));
		item.setStart("");

		Object result = fixture.doUpdateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object doUpdateItem(Object) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.Exception.class)
	public void testDoUpdateItem_3()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		Object item = new Object();

		Object result = fixture.doUpdateItem(item);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getUid() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testGetUid_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();

		String result = fixture.getUid();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_2()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_3()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_4()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_5()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_6()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_7()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_8()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_9()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_10()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_11()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_12()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_13()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testParseRequest_14()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.NumberFormatException.class)
	public void testParseRequest_15()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Object parseRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test(expected = java.lang.NumberFormatException.class)
	public void testParseRequest_16()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		HttpServletRequest request = new MockHttpServletRequest();

		Object result = fixture.parseRequest(request);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void setUid(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Test
	public void testSetUid_1()
		throws Exception {
		CalendarHandler fixture = CalendarHandlerFactory.createCalendarHandler();
		String uid = "";

		fixture.setUid(uid);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 8/31/13 8:15 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CalendarHandlerTest.class);
	}
}