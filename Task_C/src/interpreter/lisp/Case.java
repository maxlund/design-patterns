package interpreter.lisp;

import java.util.List;


public class Case extends CompoundExpression {

	private List<Expr> cases;
	private Expr condition;

	@Override
	public void initialize(List<Expr> params) {
		this.condition = params.get(0);
		this.cases = params.subList(1,params.size());
	}

	@Override
	public Expr evaluate(Context context) {		
		for (Expr expr : cases) {
			List<Expr> elem_list = ((CompoundExpression) expr).getElements();

			if (condition.evaluate(context).equals(elem_list.get(0).evaluate(context))) {
				return elem_list.get(1).evaluate(context);
			}
		}
		return Constants.FALSE;
	}

}
