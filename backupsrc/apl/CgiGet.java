package edu.jhu.apl;

//import com.appspot.cloudserviceapi.common.FileHelper;
import com.appspot.cloudserviceapi.test.BigTimeTest;

// This appears in Core Web Programming from
// Prentice Hall Publishers, and may be freely used
// or adapted. 1997 Marty Hall, hall@apl.jhu.edu.

public class CgiGet extends CgiShow {
  public static void main(String[] args) {
    CgiGet app = new CgiGet("CgiGet", args, "GET");
//    app.printFile();
//	String fd = "02/01/2011";
//	String td = "12/31/2011";
	if(args.length == 2) {
		app.startr(args[0], args[1]);
		app.start(args[0], args[1]);
//		try {
//			FileHelper.executeBatch("php /drupalroot7/bt.php", false);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
	} else {
	    System.out.println("need start date and end date!");
	}
//    System.out.println("" + args[0] + "");
    
  }
      
  public CgiGet(String name, String[] args,
		String type) {
    super(name, args, type);
  }
  
  protected void printHeader() {
    super.printHeader();
    System.out.println
      ("This program illustrates CGI programs\n" +
       "in Java that receive <TT>" + type +
       "</TT> requests.\n" +
       "<P>");
  }

  protected void printBody(String[] data) {
    System.out.println("Data was '" + data[0] + "'.");
  }
}
