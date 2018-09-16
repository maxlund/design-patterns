package visitor;

public class EvaluationVisitor extends Visitor {

	private int result;

	public int getResult() {
		return result;
	}

	@Override
	public void visit(Number exp) {
		result = exp.getValue();
	}

	@Override
	public void visit(Variable exp) {
		result = exp.getValue();
	}

	@Override
	public void visit(Sum exp) {
		EvaluationVisitor ev1 = new EvaluationVisitor();
		EvaluationVisitor ev2 = new EvaluationVisitor();
		
		// if we just call exp.leftOperand.getValue(), we lose the ability to sum two AbstractExpressions which are not SimpleExpressions,
		// (e.g. a sum of another sum and a subtraction). Instead we create two new EvaluationVisitors which can accept the expressions,
		// and we can then get the result from those.
		exp.leftOperand.accept(ev1);
		exp.rightOperand.accept(ev2);
		
		result = ev1.getResult() + ev2.getResult();
	}

	@Override
	public void visit(Minus exp) {
		// same logic as for sum
		EvaluationVisitor ev1 = new EvaluationVisitor();
		EvaluationVisitor ev2 = new EvaluationVisitor();
		
		exp.leftOperand.accept(ev1);
		exp.rightOperand.accept(ev2);
		
		result = ev1.getResult() - ev2.getResult();
	}


}
