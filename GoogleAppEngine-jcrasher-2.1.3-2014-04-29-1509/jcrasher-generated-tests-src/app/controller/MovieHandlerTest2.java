package app.controller;

/**
 * Test cases for getType
 */
public class MovieHandlerTest2 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      MovieHandler m1 = new MovieHandler();
      m1.getType();
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.getType";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest2(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest2.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest2.class);
  }
}