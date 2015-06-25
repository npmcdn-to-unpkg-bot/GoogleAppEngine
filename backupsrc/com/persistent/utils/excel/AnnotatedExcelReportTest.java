package com.persistent.utils.excel;

import static org.junit.Assert.fail;

import java.util.List;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class AnnotatedExcelReportTest {
//	static Logger logger = Logger.getLogger(AnnotatedExcelReportTest.class.getName());
	static {
//	BasicConfigurator.configure();
	}
	List<TestClass> testClasses = null;

	@Before
	public void setUp() throws Exception {
		setTestClasses(TestClass.getTestData());
	}

	//@Test
	public void testReadData() {
		AnnotatedExcelReport ExcelReport = new AnnotatedExcelReport();
		try {
			List<TestClass> results = ExcelReport
					.readData(TestClass.class.getName());
			for (TestClass testClass : results) {
				System.out.println("Read from Excel: "+testClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWriteReportToExcel() {
		setTestClasses(TestClass.getTestData());
		AnnotatedExcelReport excelReport = new AnnotatedExcelReport();
		try {
			//logger.info("The number of data elements is: "+getTestClasses().size());
			System.out.println("The number of data elements is: "+getTestClasses().size());
			excelReport.writeReportToExcel(getTestClasses());
			excelReport.closeWorksheet();
		} catch (Exception e) {
			fail();
		}
	}

	public List<TestClass> getTestClasses() {
		return testClasses;
	}

	public void setTestClasses(List<TestClass> testClasses) {
		this.testClasses = testClasses;
	}

}
