package visitor;

public class CountingVariablesVisitor extends Visitor {

	int count;

	public CountingVariablesVisitor() {
		this.count = 0;
	}
	
	public int getCount() {
		return count;
	}

	@Override
	public void visit(Number exp) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(Variable exp) {
		count++;
	}

	@Override
	public void visit(Sum exp) {
		// create two new CountingVariablesVisitor to count the number of variables in left and right operands
		CountingVariablesVisitor cv1 = new CountingVariablesVisitor();
		CountingVariablesVisitor cv2 = new CountingVariablesVisitor();
		
		// accept (call visit()) with cv1 and cv2
		exp.leftOperand.accept(cv1);
		exp.rightOperand.accept(cv2);
		
		// add the counts
		this.count += cv1.getCount() + cv2.getCount();
	}


	@Override
	public void visit(Minus exp) {
		// same as for Sum
		CountingVariablesVisitor cv1 = new CountingVariablesVisitor();
		CountingVariablesVisitor cv2 = new CountingVariablesVisitor();
		
		exp.leftOperand.accept(cv1);
		exp.rightOperand.accept(cv2);
		
		this.count += cv1.getCount() + cv2.getCount();
		
	}
}
