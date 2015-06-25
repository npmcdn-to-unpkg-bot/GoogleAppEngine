package app.controller;

/**
 * Test cases for purgeScheduledMovies
 */
public class MovieHandlerTest33 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      java.util.List l2 = new java.util.Vector();
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test1() throws Throwable {
    try{
      java.lang.String s1 = "";
      java.util.List l2 = (java.util.List)null;
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test2() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      java.util.List l2 = new java.util.Vector();
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test3() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      java.util.List l2 = (java.util.List)null;
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test4() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      java.util.List l2 = new java.util.Vector();
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test5() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      java.util.List l2 = (java.util.List)null;
      MovieHandler.purgeScheduledMovies(s1, l2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.purgeScheduledMovies";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest33(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest33.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest33.class);
  }
}