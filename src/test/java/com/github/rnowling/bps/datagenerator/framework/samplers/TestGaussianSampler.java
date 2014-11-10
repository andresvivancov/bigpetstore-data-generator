package com.github.rnowling.bps.datagenerator.framework.samplers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.rnowling.bps.datagenerator.framework.SeedFactory;
import com.github.rnowling.bps.datagenerator.framework.samplers.GaussianSampler;
import com.github.rnowling.bps.datagenerator.framework.samplers.Sampler;

public class TestGaussianSampler
{

	@Test
	public void testSample() throws Exception
	{
		double mean = 2.0;
		double var = 1.0;
		
		SeedFactory seedFactory = new SeedFactory(1234);
		
		Sampler<Double> sampler = new GaussianSampler(mean, var, seedFactory);
		
		Double result = sampler.sample();
		
		assertTrue(result >= -10);
		assertTrue(result <= 10);
	}
}