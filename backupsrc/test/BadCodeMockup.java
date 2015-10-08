package com.appspot.cloudserviceapi.test;

public class BadCodeMockup {
	public int l;
	public int l2;
	public int l3;
	public int l4;
	public int l5;
	public int l6;
	public int l7;
	public int l8;
	public int l9;
	public int l10;
	public int l11;
	public int l12;
	public int l13;
	public int l14;
	public int l15;
	public int l16;

	 public void badDesign2(int x) {
		   switch (x) {
		     case 1: {
		       // lots of statements
		       break;
		     } case 2: {
		       // lots of statements
		       break;
		     }
		   }
	 }
	 
     public void badDesign1(int x, int y, int z) {
		  if (x>y) {
		   if (y>z) {
		    if (z==x) {
		     // whew, too deep
		    }
		   }
		  }
		 }
	
	public void BadCodeMockup() {
		int x = 2;
		switch (x) {
		case 2:
			int j = 8;
		}
	}

	private void bar() {
		buz("Howdy");
		buz("Howdy");
		buz("Howdy");
		buz("Howdy");
	}

	private void buz(String x) {
	}

	public String convert(int i) {
		String s;
		s = "a" + String.valueOf(i); // Bad
		s = "a" + i; // Better

		String temp = null;

		try {
			temp.indexOf("");
		} catch (Exception e) {
		}

		if (temp == null) {
		}

		return s;
	}

	private String bar = new String("bar"); // just do a String bar = "bar";

}
