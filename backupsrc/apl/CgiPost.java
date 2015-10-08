package edu.jhu.apl;

import java.io.*;

// This appears in Core Web Programming from
// Prentice Hall Publishers, and may be freely used
// or adapted. 1997 Marty Hall, hall@apl.jhu.edu.

public class CgiPost extends CgiGet {
  public static void main(String[] args) {
    try {
      DataInputStream in
        = new DataInputStream(System.in);
      String[] data = { in.readLine() };
      CgiPost app = new CgiPost("CgiPost", data, "POST");
      app.printFile();
    } catch(IOException ioe) {
      System.out.println
        ("IOException reading POST data: " + ioe);
    }
  }

  public CgiPost(String name, String[] args,
		 String type) {
    super(name, args, type);
  }
}
