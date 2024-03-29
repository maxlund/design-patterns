package visitor;

import java.text.MessageFormat;


public class VisitorTest {
	public static void main(String[] args) {
		CountingVariablesVisitor v = new CountingVariablesVisitor();
		AbstractExpression expression = new Sum(new Minus(new Number(1),
				new Variable("x", 3)), new Sum(new Number(5), new Variable("y",
				7)));
		
		// (1 - x) + (5 + y) is printed.. this is correct right?
		System.out.println(expression.toString());
		System.out.println(expression);
		
		// and the result becomes 10, as it should be
		EvaluationVisitor ev = new EvaluationVisitor();
		expression.accept(ev);
		System.out.println(ev.getResult());
		
		// how do we implement counting variables though..
		expression.accept(v);
		System.out.println(MessageFormat.format(
				"There were {0} variables in the expression", v.getCount()));
		
		// PrintVisitors toString() prints the expression that PrintVisitor holds after visit() is called through expressions accept()
		PrintVisitor pv = new PrintVisitor();
		expression.accept(pv);
		System.out.println(pv.toString());
	}
}
