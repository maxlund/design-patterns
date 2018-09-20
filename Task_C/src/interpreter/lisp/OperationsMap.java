package interpreter.lisp;

import java.util.HashMap;

public class OperationsMap {

	public HashMap<Symbol, Expr> operations = new HashMap<Symbol, Expr>();
	
	private static OperationsMap Map = new OperationsMap();
	
	private OperationsMap() {
		operations.put(new Symbol("def"), new CompoundFunction());
		operations.put(new Symbol("if"), new Conditional());
		operations.put(new Symbol("case"), new Case());
	}
	
	public static Expr get(Symbol var) {
		Expr value = Map.operations.get(var);

		return value;
	}
}
