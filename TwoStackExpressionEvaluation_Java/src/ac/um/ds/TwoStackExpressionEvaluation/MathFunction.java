package ac.um.ds.TwoStackExpressionEvaluation;

// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2014 (1393 Hijri Shamsi)
//
// Author: Kamaledin Ghiasi-Shirazi


public abstract class MathFunction extends Operator
{
	public MathFunction(String name)
	{
		super(name, 5, Associativity.ASSOC_LEFT, true, Token.Type.FUNCTION);
	}
}