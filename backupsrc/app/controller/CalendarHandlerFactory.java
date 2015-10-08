package app.controller;



/**
 * The class <code>CalendarHandlerFactory</code> implements static methods that return instances of the class <code>{@link CalendarHandler}</code>.
 *
 * @generatedBy CodePro at 8/31/13 8:12 AM
 * @author macbook
 * @version $Revision: 1.0 $
 */
public class CalendarHandlerFactory
 {
	/**
	 * Prevent creation of instances of this class.
	 *
	 * @generatedBy CodePro at 8/31/13 8:12 AM
	 */
	private CalendarHandlerFactory() {
	}


	/**
	 * Create an instance of the class <code>{@link CalendarHandler}</code>.
	 *
	 * @generatedBy CodePro at 8/31/13 8:12 AM
	 */
	public static CalendarHandler createCalendarHandler() {
		return new CalendarHandler();
	}
}