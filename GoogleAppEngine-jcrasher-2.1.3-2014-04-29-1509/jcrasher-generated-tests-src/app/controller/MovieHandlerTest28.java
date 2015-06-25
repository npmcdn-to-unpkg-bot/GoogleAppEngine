package app.controller;

/**
 * Test cases for contextInitialized
 */
public class MovieHandlerTest28 extends edu.gatech.cc.junit.FilteringTestCase {
  
  /**
   * Executed before each testXXX().
   */
  protected void setUp() {
    /* Re-initialize static fields of loaded classes. */
    edu.gatech.cc.junit.reinit.ClassRegistry.resetClasses();
    //TODO: my setup code goes here.
  }
  
  /**
   * Executed after each testXXX().
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    //TODO: my tear down code goes here.
  }

  /**
   * JCrasher-generated test case.
   */
  public void test0() throws Throwable {
    try{
      javax.servlet.ServletContextEvent s1 = (javax.servlet.ServletContextEvent)null;
      MovieHandler m2 = new MovieHandler();
      m2.contextInitialized(s1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.contextInitialized";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest28(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest28.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest28.class);
  }
}