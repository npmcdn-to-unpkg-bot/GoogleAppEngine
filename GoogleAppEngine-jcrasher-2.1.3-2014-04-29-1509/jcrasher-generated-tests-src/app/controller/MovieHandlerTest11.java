package app.controller;

/**
 * Test cases for setAction
 */
public class MovieHandlerTest11 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      java.lang.String s1 = "";
      MovieHandler m2 = new MovieHandler();
      m2.setAction(s1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test1() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      MovieHandler m2 = new MovieHandler();
      m2.setAction(s1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test2() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      MovieHandler m2 = new MovieHandler();
      m2.setAction(s1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.setAction";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest11(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest11.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest11.class);
  }
}