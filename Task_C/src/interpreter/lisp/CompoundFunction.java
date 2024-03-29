package interpreter.lisp;

import java.util.ArrayList;
import java.util.List;

public class CompoundFunction extends CompoundExpression implements Fun {

	private List<Symbol> parameters;
	private Symbol name;
	private Expr body;


	@Override
	public void initialize(List<Expr> params) {
		this.name = (Symbol) params.get(0);
		List<Expr> elts = ((CompoundExpression) params.get(1)).getElements();
		List<Symbol> paramNames = new ArrayList<Symbol>();
		for (Expr node : elts) {
			paramNames.add((Symbol) node);
		}

		this.parameters = paramNames;
		this.body = params.get(2);

	}
	
	/* (non-Javadoc)
	 * @see interpreter.lisp.Fun#getParameters()
	 */
	@Override
	public List<Symbol> getParameters() {
		return parameters;
	}

	public Symbol getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see interpreter.lisp.Fun#getBody()
	 */
	@Override
	public Expr getBody() {
		return body;
	}

	@Override
	public Expr evaluate(Context context) {
		context.put(getName(),this);
		return this;
	}
	
	@Override
	public String toString() {
		return getBody().toString();
	}

}
