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
//
//import com.newatlanta.commons.vfs.provider.gae.GaeVFS;

/**
 * Source: 
 * http://sdoulger.blogspot.com/2010/12/call-pmd-from-your-code-with-you-custom.html
 * http://pmd.sourceforge.net/howtowritearule.html
 * http://pmd.sourceforge.net/rules
 * 
 */
public class PMDUtil {
    
    public static InputStream fromString(String str)
    {
		byte[] bytes = str.getBytes();
		return new ByteArrayInputStream(bytes);
    }
    /**
     * Runs PMD on the specific file with the specifc rule.
     * @param file target file
     * @param  YOUR custom PMD rule that you have created with java (and must extend
	 * AbstractJavaRule)
     * @return  List with errors. If emtpy then no error
     */
    public static List<String> runPMD(String content, AbstractJavaRule rule) {
        List<String> ruleViolationMsgs = new ArrayList<String>();
        try {
          InputStream fis = fromString(content);
        	
            //prepare PMD
            final PMD pmd = new PMD();
           //Set the javaVersion you are using. (*1)
            pmd.setJavaVersion(SourceType.JAVA_16);
           //Get a context and initialize it with The Report taht PMD will return
            final RuleContext ctx = new RuleContext();
            ctx.setReport(new Report());
            //add you rule to the ruleset
            RuleSet ruleSet = new RuleSet();
            Rule r = null;
            r = new TooManyFields();r.setName("TooManyFields");
            ruleSet.addRule(r);
            r = new UselessStringValueOf();r.setName("UselessStringValueOf");
            ruleSet.addRule(r);
            r = new StringInstantiation();r.setName("StringInstantiation");
            ruleSet.addRule(r);
            r = new AvoidDeeplyNestedIfStmtsRule();r.setName("AvoidDeeplyNestedIfStmtsRule");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("SwitchDensityRule");
            ruleSet.addRule(r);

            r = new SwitchDensityRule();r.setName("LocalVariableCouldBeFinal");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("MethodArgumentCouldBeFinal");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("AvoidInstantiatingObjectsInLoops");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("UseArrayListInsteadOfVector");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("SimplifyStartsWith");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("UseStringBufferForStringAppends");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("UseArraysAsList");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("AvoidArrayLoops");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("UnnecessaryWrapperObjectCreation");
            ruleSet.addRule(r);
            r = new SwitchDensityRule();r.setName("AddEmptyString");
            ruleSet.addRule(r);
            
            
            //ruleSet.addRule(rule);
            Rule r1 = new XPathRule();
            r1.setName("EmptyCatchBlock");
            r1.setPriority(3);
            r1.setMessage("Avoid empty catch blocks");
            r1.addProperty("xpath", "//CatchStatement[count(Block/BlockStatement) = 0 and ($allowCommentedBlocks != 'true' or Block/@containsComment = 'false')][FormalParameter/Type/ReferenceType/ClassOrInterfaceType[@Image != 'InterruptedException' and @Image != 'CloneNotSupportedException']]");
            r1.addProperty("allowCommentedBlocks", "false");
            ruleSet.addRule(r1);
            Rule r2 = new XPathRule();
            r2.setName("EmptyIfStatement");
            r2.setPriority(3);
            r2.setMessage("Avoid empty if statements");
            r2.addProperty("xpath", "//IfStatement/Statement [EmptyStatement or Block[count(*) = 0]]");
            ruleSet.addRule(r2);
            Rule r3 = new XPathRule();
            r3.setName("SwitchStmtsShouldHaveDefault");
            r3.setPriority(3);
            r3.setMessage("Switch statements should have a default label");
            r3.addProperty("xpath", "//SwitchStatement[not(SwitchLabel[@Default='true'])]");
            ruleSet.addRule(r3);
            
            //target filename
            ctx.setSourceCodeFilename("temp.java");
            
            //Call
            pmd.processFile(fis, ruleSet, ctx);

            //write results to
            if (!ctx.getReport().isEmpty()) {
                for (Iterator i = ctx.getReport().iterator(); i.hasNext(); ) {
                    final RuleViolation viol = (RuleViolation)i.next();
                    //Use viol to retrieve the details of the errror
                  //System.out.println("Rule Violation: " + "@ line: " + viol.getBeginLine() + " and @ column: " +    viol.getBeginColumn() + ".");
                  ruleViolationMsgs.add("[" + viol.getRule().getName() + "] Violation @line: " + viol.getBeginLine() + " and column: " +    viol.getBeginColumn() + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ruleViolationMsgs;
    }
    
    public static void main(String[] args) throws IOException {
    	File f = new File("C:\\GoogleAppEngine\\src\\com\\appspot\\cloudserviceapi\\test\\BadCodeMockup.java");
//	    GaeVFS.setRootPath("/");
	    String resp = null; //FileUtils.readFileToString(f);
    	List l = null;
    	try {
	    	l = PMDUtil.runPMD(resp, null);
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