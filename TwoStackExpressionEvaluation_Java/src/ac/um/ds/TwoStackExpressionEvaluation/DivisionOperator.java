package ac.um.ds.TwoStackExpressionEvaluation;

public class DivisionOperator extends Operator
{
	public DivisionOperator()
	{
		super("/", 2,Associativity.ASSOC_LEFT, false);
	}
	@Override
	public double evaluate(double v1, double v2)
	{
		return v1 / v2;
	}
}