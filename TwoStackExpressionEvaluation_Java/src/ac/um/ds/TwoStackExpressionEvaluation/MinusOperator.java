package ac.um.ds.TwoStackExpressionEvaluation;

public class MinusOperator extends Operator
{
	public MinusOperator()
	{
		super("-",1, Associativity.ASSOC_LEFT, false);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return v1 - v2;
	}
}