package com.appspot.cloudserviceapi.sci.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.persistent.utils.excel.AnnotatedExcelReport;

public class RepositoryDataReport {

	public ByteArrayOutputStream getZippedDatabaseReport() {
		int BUFFER = 2048;
		List testClasses = null;
		ByteArrayOutputStream workbook = null;
		ByteArrayOutputStream zip = new ByteArrayOutputStream();
		AnnotatedExcelReport excelReport = null;
		ZipOutputStream out1 = new ZipOutputStream(zip);
		ZipEntry entry = null;

		testClasses = (new RepositoryDataDAO()).getCloneList();
		excelReport = new AnnotatedExcelReport();
		try {
			System.out.println("Data elements in RepositoryData is: "
					+ testClasses.size());
			excelReport.writeReportToExcel(testClasses);
			excelReport.closeWorksheet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		workbook = excelReport.getWorksheetOutputStream();
		// out.setMethod(ZipOutputStream.DEFLATED);
		System.out.println("Adding: RepositoryData");
		entry = new ZipEntry(excelReport.getWorkbookName());
		try {
			out1.putNextEntry(entry);
			out1.write(workbook.toByteArray(), 0, workbook.size());
			out1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		testClasses = (new RepositoryDataDAO()).getCloneList();
//		excelReport = new AnnotatedExcelReport();
//		try {
//			System.out.println("Data elements in Employees is: "
//					+ testClasses.size());
//			excelReport.writeReportToExcel(testClasses);
//			excelReport.closeWorksheet();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		workbook = excelReport.getWorksheetOutputStream();
//		// out.setMethod(ZipOutputStream.DEFLATED);
//		System.out.println("Adding: Employees");
//		entry = new ZipEntry(excelReport.getWorkbookName());
//		try {
//			out1.putNextEntry(entry);
//			out1.write(workbook.toByteArray(), 0, workbook.size());
//			// out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		testClasses = (new RepositoryDataDAO()).getCloneList();
//		excelReport = new AnnotatedExcelReport();
//		try {
//			System.out.println("Data elements in Clients is: "
//					+ testClasses.size());
//			excelReport.writeReportToExcel(testClasses);
//			excelReport.closeWorksheet();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		workbook = excelReport.getWorksheetOutputStream();
//		// out.setMethod(ZipOutputStream.DEFLATED);
//		System.out.println("Adding: Clients");
//		entry = new ZipEntry(excelReport.getWorkbookName());
//		try {
//			out1.putNextEntry(entry);
//			out1.write(workbook.toByteArray(), 0, workbook.size());
//			out1.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return zip;
	}
	
}
