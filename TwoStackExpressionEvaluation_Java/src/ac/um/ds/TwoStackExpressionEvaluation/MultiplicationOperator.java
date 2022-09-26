package ac.um.ds.TwoStackExpressionEvaluation;

public class MultiplicationOperator extends Operator
{
	public MultiplicationOperator()
	{
		super("*", 2, Associativity.ASSOC_LEFT, false);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return v1 * v2;
	}
}