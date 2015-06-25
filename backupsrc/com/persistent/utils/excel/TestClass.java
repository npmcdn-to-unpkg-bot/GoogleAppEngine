package com.persistent.utils.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ExcelReport(reportName="test.xls")
public class TestClass {
	private String name;
	private String path;
	private String directory;
	private Integer containedFiles;
	private Double doubleField;
	
	public TestClass() {
	}
	
	
	
	public TestClass(String name, String path, String directory,
			Integer containedFiles, Double doubleField) {
		super();
		this.name = name;
		this.path = path;
		this.directory = directory;
		this.containedFiles = containedFiles;
		this.doubleField = doubleField;
	}

	
	@ExcelColumn(label="Test Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@ExcelColumn(label="Path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	@ExcelColumn(label="Directory")
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	
	@ExcelColumn(label="Number of Files in Directory")
	public Integer getContainedFiles() {
		return containedFiles;
	}

	public void setContainedFiles(Integer containedFiles) {
		this.containedFiles = containedFiles;
	}


	@ExcelColumn(ignore=true)
	public Double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	

	@Override
	public String toString() {
		return "TestClass [containedFiles=" + containedFiles + ", directory="
				+ directory + ", doubleField=" + doubleField + ", name=" + name
				+ ", path=" + path + "]";
	}

	public static List<TestClass> getTestData() {
		List<TestClass> testClasses = new ArrayList<TestClass>();

		String currentDirectory = System.getProperty("user.dir");
		File currentDirectoryFile = new File(currentDirectory);
		File[] files = currentDirectoryFile.listFiles();
		double dbl = 0;
		for (File file : files) {
			TestClass testClass = null;
			if (file.isDirectory()) {
				testClass = new TestClass(file.getName(), file.getPath(),
						"directory", file.list().length,dbl++);
			} else {
				testClass = new TestClass(file.getName(), file.getPath(),
						"file", 0,dbl++);
			}
			testClasses.add(testClass);
		}
		return testClasses;
	}

	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		File currentDirectoryFile = new File(currentDirectory);
		File[] files = currentDirectoryFile.listFiles();

		System.out.println("Is there anything in the list:" + files.length);
		double dbl = 0;
		for (File file : files) {
			System.out.println(file.getName());
			System.out.println(file.getAbsoluteFile());
			System.out.println(file.getPath());
			if (file.isDirectory()) {
				System.out.println("Directory");
				new TestClass(file.getName(), file.getPath(),
						"directory", file.list().length,dbl++);
			} else {
				new TestClass(file.getName(), file.getPath(),
						"file", 0,dbl++);
			}
		}
	}

}
