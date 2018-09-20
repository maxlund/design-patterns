package interpreter.lisp;

public class InterpreterTest {

	public static void main(String[] args) {
		Expr expr = Reader
				.read("(def fac (x) (if (= x 0) 1 (* x (fac (- x 1)))))");
		/*
		 * The expression above is a function definition of the function 'fac'
		 * with parameter 'x', that calculates the factorial of x.
		 * 
		 * All expressions are delimited by parentheses, and all 'significant
		 * names' are listed directly after the opening parenthesis, in prefix
		 * form (operator first, operands later).
		 * 
		 * In another programming language (Python), the code above would read
		 * 
		 * def fac(x):
		 *    if(x == 0):
		 *       1
		 *    else:
		 *       x * fac(x-1)
		 * 
		 * 
		 */

		Expr result = expr.evaluate(Context.getTopLevelContext());
		System.out.println(result);
		
		expr = Reader
				.read("(case (* 1 1) ((* 2 4) 0) (3 1) (1 (fac 3)))");
//		System.out.println(expr);
//		
		result = expr.evaluate(Context.getTopLevelContext());
		System.out.println(result);

		Expr expr2 = Reader.read("(fac 5)"); // fac(5) in Python
		result = expr2.evaluate(Context.getTopLevelContext());
		System.out.println(result);

	}

}
