package interpreter.lisp;

import java.util.List;

public class Conditional extends CompoundExpression {

	private Expr condition;
	private Expr consequent;
	private Expr antecedent;


	@Override
	public void initialize(List<Expr> params) {
		this.condition = params.get(0);
		this.consequent = params.get(1);
		this.antecedent = params.get(2);
	}
	
	public Expr getCondition() {
		return condition;
	}

	public Expr getConsequent() {
		return consequent;
	}

	public Expr getAntecedent() {
		return antecedent;
	}

	@Override
	public Expr evaluate(Context context) {
		return condition.evaluate(context) == Constants.TRUE ? consequent
				.evaluate(context) : antecedent.evaluate(context);
	}

}
