package app.controller;

/**
 * Test cases for migrateOwnedMovies
 */
public class MovieHandlerTest32 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      app.model.User u1 = (app.model.User)null;
      java.util.List l2 = new java.util.Vector();
      MovieHandler m3 = new MovieHandler();
      m3.migrateOwnedMovies(u1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test1() throws Throwable {
    try{
      app.model.User u1 = (app.model.User)null;
      java.util.List l2 = (java.util.List)null;
      MovieHandler m3 = new MovieHandler();
      m3.migrateOwnedMovies(u1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test2() throws Throwable {
    try{
      app.model.User u1 = new app.model.User();
      java.util.List l2 = new java.util.Vector();
      MovieHandler m3 = new MovieHandler();
      m3.migrateOwnedMovies(u1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test3() throws Throwable {
    try{
      app.model.User u1 = new app.model.User();
      java.util.List l2 = (java.util.List)null;
      MovieHandler m3 = new MovieHandler();
      m3.migrateOwnedMovies(u1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.migrateOwnedMovies";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest32(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest32.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest32.class);
  }
}