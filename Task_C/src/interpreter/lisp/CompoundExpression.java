package interpreter.lisp;

import java.util.ArrayList;
import java.util.List;

public class CompoundExpression implements Expr {

	private List<Expr> elements = new ArrayList<Expr>();
	
	public void add(Expr node) {
		elements.add(node);
	}

	public List<Expr> getElements() {
		return elements;
	}

	public void initialize(List<Expr> params) {
	
	}
	
	public Expr evaluate(Context context) {
		if (elements.isEmpty()) {
			return Constants.EMPTY_LIST;
		}
		if (elements.get(0) instanceof Symbol) {
			Symbol keyword = (Symbol) elements.get(0);
			
			CompoundExpression op = (CompoundExpression) OperationsMap.get(keyword);
			
			if (op != null) {
				op.initialize(elements.subList(1,elements.size()));
				return op.evaluate(context);
			}
		}
		return new FunctionCall((Symbol) elements.get(0),elements.subList(1,elements.size())).evaluate(context);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Expr> e = elements;
		sb.append("[");
		for (Expr node : e) {
			sb.append(node);
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}


}
