package app.controller;

/**
 * Test cases for doMigratePurgeItem
 */
public class MovieHandlerTest31 extends edu.gatech.cc.junit.FilteringTestCase {
  
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
      java.lang.String s2 = "";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test1() throws Throwable {
    try{
      java.lang.String s1 = "";
      java.lang.String s2 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test2() throws Throwable {
    try{
      java.lang.String s1 = "";
      java.lang.String s2 = (java.lang.String)null;
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test3() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      java.lang.String s2 = "";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test4() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      java.lang.String s2 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test5() throws Throwable {
    try{
      java.lang.String s1 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      java.lang.String s2 = (java.lang.String)null;
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test6() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      java.lang.String s2 = "";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test7() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      java.lang.String s2 = "\"\n\\.`'@#$%^&/({<[|\\n:.,;";
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }

  /**
   * JCrasher-generated test case.
   */
  public void test8() throws Throwable {
    try{
      java.lang.String s1 = (java.lang.String)null;
      java.lang.String s2 = (java.lang.String)null;
      MovieHandler m3 = new MovieHandler();
      m3.doMigratePurgeItem(s1, s2);
    }
    catch (Throwable throwable) {throwIf(throwable);}
  }
  
  
  protected String getNameOfTestedMeth() {
    return "app.controller.MovieHandler.doMigratePurgeItem";
  }
  
  /**
   * Constructor
   */
  public MovieHandlerTest31(String pName) {
    super(pName);
  }
  
  /**
   * Easy access for aggregating test suite.
   */
  public static junit.framework.Test suite() {
    return new junit.framework.TestSuite(MovieHandlerTest31.class);
  }
  
  /**
   * Main
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MovieHandlerTest31.class);
  }
}