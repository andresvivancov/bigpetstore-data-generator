package com.github.rnowling.bps.datagenerator.generators.transaction;

import com.github.rnowling.bps.datagenerator.framework.pdfs.ConditionalProbabilityDensityFunction;
import com.github.rnowling.bps.datagenerator.framework.pdfs.ProbabilityDensityFunction;

public class TransactionTimePDF implements ConditionalProbabilityDensityFunction<Double, Double>
{	
	public double probability(Double proposedTime, Double lastTransactionTime)
	{
		return fixConditional(lastTransactionTime).probability(proposedTime);
	}
	
	public ProbabilityDensityFunction<Double> fixConditional(final Double lastTransactionTime)
	{
		return new ProbabilityDensityFunction<Double>()
			{
				public double probability(Double proposedTransactionTime)
				{
					if(proposedTransactionTime >= lastTransactionTime)
					{
						return 1.0;
					}
					else
					{
						return 0.0;
					}
				}
			};
	}
}
