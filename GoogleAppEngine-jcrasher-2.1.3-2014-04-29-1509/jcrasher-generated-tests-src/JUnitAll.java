import junit.framework.*;

/**
 * Collects all test cases generated for all classes.
 */
public class JUnitAll extends TestSuite {

  public JUnitAll(String name) {
    super(name);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(app.controller.MovieHandlerTest1.class);
    suite.addTestSuite(app.controller.MovieHandlerTest2.class);
    suite.addTestSuite(app.controller.MovieHandlerTest3.class);
    suite.addTestSuite(app.controller.MovieHandlerTest4.class);
    suite.addTestSuite(app.controller.MovieHandlerTest5.class);
    suite.addTestSuite(app.controller.MovieHandlerTest6.class);
    suite.addTestSuite(app.controller.MovieHandlerTest7.class);
    suite.addTestSuite(app.controller.MovieHandlerTest8.class);
    suite.addTestSuite(app.controller.MovieHandlerTest9.class);
    suite.addTestSuite(app.controller.MovieHandlerTest10.class);
    suite.addTestSuite(app.controller.MovieHandlerTest11.class);
    suite.addTestSuite(app.controller.MovieHandlerTest12.class);
    suite.addTestSuite(app.controller.MovieHandlerTest13.class);
    suite.addTestSuite(app.controller.MovieHandlerTest14.class);
    suite.addTestSuite(app.controller.MovieHandlerTest15.class);
    suite.addTestSuite(app.controller.MovieHandlerTest16.class);
    suite.addTestSuite(app.controller.MovieHandlerTest17.class);
    suite.addTestSuite(app.controller.MovieHandlerTest18.class);
    suite.addTestSuite(app.controller.MovieHandlerTest19.class);
    suite.addTestSuite(app.controller.MovieHandlerTest20.class);
    suite.addTestSuite(app.controller.MovieHandlerTest21.class);
    suite.addTestSuite(app.controller.MovieHandlerTest22.class);
    suite.addTestSuite(app.controller.MovieHandlerTest23.class);
    suite.addTestSuite(app.controller.MovieHandlerTest24.class);
    suite.addTestSuite(app.controller.MovieHandlerTest25.class);
    suite.addTestSuite(app.controller.MovieHandlerTest26.class);
    suite.addTestSuite(app.controller.MovieHandlerTest27.class);
    suite.addTestSuite(app.controller.MovieHandlerTest28.class);
    suite.addTestSuite(app.controller.MovieHandlerTest29.class);
    suite.addTestSuite(app.controller.MovieHandlerTest30.class);
    suite.addTestSuite(app.controller.MovieHandlerTest31.class);
    suite.addTestSuite(app.controller.MovieHandlerTest32.class);
    suite.addTestSuite(app.controller.MovieHandlerTest33.class);
    suite.addTestSuite(app.controller.MovieHandlerTest34.class);
    return suite;
  }
}
