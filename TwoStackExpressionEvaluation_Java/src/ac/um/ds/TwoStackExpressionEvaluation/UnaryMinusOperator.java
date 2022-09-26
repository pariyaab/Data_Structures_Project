package ac.um.ds.TwoStackExpressionEvaluation;

public class UnaryMinusOperator extends Operator
{
	public UnaryMinusOperator()
	{
		super("_",4, Associativity.ASSOC_LEFT, true);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return -v1;
	}
}