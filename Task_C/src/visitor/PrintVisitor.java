package visitor;

public class PrintVisitor extends Visitor {

	private StringBuilder expressionString = new StringBuilder();
	private boolean isSubExpression;
	
	public PrintVisitor() {
		this.isSubExpression = false;
	}
	
	public PrintVisitor(boolean isSubExpression) {
		this.isSubExpression = isSubExpression;
	}
	
	@Override
	public String toString() {
		return expressionString.toString();
	}
	
	@Override
	public void visit(Number exp) {
//		System.out.print(MessageFormat.format("{0} ", exp.getValue()));
		expressionString.append(exp.getValue());
	}

	@Override
	public void visit(Variable exp) {
//		System.out.print(MessageFormat.format("{0} ", exp.getName()));
		expressionString.append(exp.getName());
	}

	@Override
	public void visit(Sum exp) {
//		System.out.print("+ ");
		visit(exp, "+");
	}

	@Override
	public void visit(Minus exp) {
//		System.out.print("- ");
		visit(exp, "-");
	}
	
	private void visit(CompoundExpression exp, String op) {
	
		// add parenthesis if this is a compound expression within another compound expression
		if (isSubExpression)
			expressionString.append("(");
		
		PrintVisitor pv1 = new PrintVisitor(true);
		PrintVisitor pv2 = new PrintVisitor(true);
		
		// accept calls visit() with PrintVisitor on leftOperand, putting the leftOperand in that PrintVisitors expressionString
		exp.leftOperand.accept(pv1);
		// get the string representation of leftOperand from PrintVisitor and append it to this' expressionString
		expressionString.append(pv1.toString());
		
		// add spaces and the operator
		expressionString.append(" ");
		expressionString.append(op);
		expressionString.append(" ");
		
		// do the same for rightOperand
		exp.rightOperand.accept(pv2);
		expressionString.append(pv2.toString());
		
		// add parenthesis if this is a compound expression within another compound expression
		if (isSubExpression)
			expressionString.append(")");
	}
}
