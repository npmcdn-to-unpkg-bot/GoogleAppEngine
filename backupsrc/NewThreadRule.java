package com.appspot.cloudserviceapi.test;

import net.sourceforge.pmd.AbstractJavaRule;
import net.sourceforge.pmd.ast.ASTAllocationExpression;
import net.sourceforge.pmd.ast.ASTName;

public class NewThreadRule extends AbstractJavaRule {
    public Object visit(ASTAllocationExpression node, Object data){
        if ((node.jjtGetChild(0) instanceof ASTName &&
        ((ASTName)node.jjtGetChild(0)).getImage().equals("Thread"))) {
            // we've found one!  Now we'll record a RuleViolation and move on
            addViolation(data, node);
        }
        return super.visit(node, data);
    }
}