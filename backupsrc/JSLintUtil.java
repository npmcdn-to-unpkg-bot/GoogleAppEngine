package com.appspot.cloudserviceapi.common;

import java.util.*;
import java.io.*;

import net.sourceforge.pmd.*;
import net.sourceforge.pmd.rules.AvoidDeeplyNestedIfStmtsRule;
import net.sourceforge.pmd.rules.CyclomaticComplexity;
import net.sourceforge.pmd.rules.XPathRule;
import net.sourceforge.pmd.rules.design.SwitchDensityRule;
import net.sourceforge.pmd.rules.design.TooManyFields;
import net.sourceforge.pmd.rules.strings.AvoidDuplicateLiteralsRule;
import net.sourceforge.pmd.rules.strings.StringInstantiation;
import net.sourceforge.pmd.rules.strings.UselessStringValueOf;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.vfs.FileObject;
//import org.apache.commons.vfs.FileSystemException;
//import org.apache.commons.vfs.FileSystemManager;
//import org.apache.commons.vfs.FileUtil;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ScriptableObject;

import com.googlecode.jslint4java.Issue;
import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.googlecode.jslint4java.JSLintResult;
//import com.newatlanta.commons.vfs.provider.gae.GaeVFS;

/**
 * Source: 
 * http://code.google.com/p/jslint4java/source/browse/docs/1.4.2/cli.html?r=246
 * 
 */
public class JSLintUtil  {
    
    public static List<String> run(String content) {
        List<String> ruleViolationMsgs = new ArrayList<String>();
        try {
        	JSLint jsl = new JSLintBuilder().fromDefault();
        	JSLintResult r = jsl.lint("jsLintOutput.txt", content);
        	List issues = r.getIssues();
            //write results to
        	String msg = null;
            if (issues.size() > 0) {
                for (Iterator i = issues.iterator(); i.hasNext(); ) {
                    final Issue viol = (Issue)i.next();
                    //Use viol to retrieve the details of the errror
					//System.out.println("Rule Violation: " + "@ line: " + viol.getBeginLine() + " and @ column: " +    viol.getBeginColumn() + ".");
                    msg = "[" + viol.getReason() + "] Violation @line: " + viol.getLine() + " and column: " +    viol.getCharacter() + "";
                    msg = filterViolationMessage(msg);
					ruleViolationMsgs.add(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ruleViolationMsgs;
    }
    
    private static String filterViolationMessage(String message) {
    	if(message != null) {
    		message = message.replace("[Script URL.]", "[Use onclick in anchors instead of javascript: Pseudo-Protocol http://goo.gl/gGnWv]");
    		message = message.replace("[type is unnecessary.]", "[HTML5 \"type\" property is optional http://goo.gl/xTxzm]");
    		message = message.replace("[Missing ';'.]", "[Missing a Semicolon http://goo.gl/h9c1i]");
        	message = message.replace("[== and !=.]", "[Avoid type coersion http://goo.gl/3ROwU]");
        	message = message.replace("[Avoid HTML event handlers.]", "[Avoid javascript event handlers in HTML tag http://goo.gl/oVGcQ]");
        		
    	}
    	
    	return message;
    }
    
    public static void main(String[] args) throws IOException {
    	File f = new File("C:\\GoogleAppEngine\\src\\com\\appspot\\cloudserviceapi\\test\\BadCodeMockup.htm");
//	    GaeVFS.setRootPath("/");
	    String resp = null; //FileUtils.readFileToString(f);
    	List l = null;
    	try {
//		    FileSystemManager fsManager = GaeVFS.getManager();
//		    FileObject tempFile = GaeVFS.resolveFile( "gae://WEB-INF/tmp" + "uri" + ".tmp" );
//		    GaeVFS.setBlockSize( tempFile, 8 ); // set block size for tempFile to 8KB
//		    tempFile.createFile();
//		    RandomAccessContent content = tempFile.getContent().getRandomAccessContent(RandomAccessMode.READWRITE);

	    	l = JSLintUtil.run(resp);
		    String advice = String.valueOf(l);
			resp = advice + resp;
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
//	        GaeVFS.clearFilesCache(); // this is important!
	    }
    	System.out.println("status = " + l);
	}
}