package app.controller;

/**
 * Test cases for doUpdateItem
 */
public class MovieHandlerTest19 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      java.lang.Object o1 = new java.lang.Object();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test1() throws Throwable {
    try{
      java.lang.Object o1 = (java.lang.Object)null;
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test2() throws Throwable {
    try{
      java.lang.Object o1 = new app.model.Calendar();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test3() throws Throwable {
    try{
      java.lang.Object o1 = new MovieHandler();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test4() throws Throwable {
    try{
      java.lang.Object o1 = new app.model.User();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test5() throws Throwable {
    try{
      java.lang.Object o1 = new com.google.appengine.api.datastore.Text("");
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test6() throws Throwable {
    try{
      java.lang.Object o1 = new com.google.appengine.api.datastore.Text("\"\n\\.`'@#$%^&/({<[|\\n:.,;");
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test7() throws Throwable {
    try{
      java.lang.Object o1 = new com.google.appengine.api.datastore.Text((java.lang.String)null);
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test8() throws Throwable {
    try{
      java.lang.Object o1 = new CalendarHandler();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test9() throws Throwable {
    try{
      java.lang.Object o1 = new app.model.Movie();
      MovieHandler m2 = new MovieHandler();
      m2.doUpdateItem(o1);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.doUpdateItem";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest19(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest19.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest19.class);
  }
}