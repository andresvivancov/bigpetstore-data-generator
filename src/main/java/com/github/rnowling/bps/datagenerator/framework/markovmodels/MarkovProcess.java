package com.github.rnowling.bps.datagenerator.framework.markovmodels;

import java.util.Map;

import com.github.rnowling.bps.datagenerator.framework.SeedFactory;
import com.github.rnowling.bps.datagenerator.framework.samplers.RouletteWheelSampler;
import com.github.rnowling.bps.datagenerator.framework.samplers.Sampler;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;

public class MarkovProcess<T> implements Sampler<T>
{
	final ImmutableMap<T, Sampler<T>> transitionSamplers;
	final Sampler<T> startStateSampler;
	
	T currentState;
	
	
	public MarkovProcess(MarkovModel<T> model, SeedFactory factory)
	{
		Map<T, Map<T, Double>> transitionTable = model.getTransitionWeights();
		
		startStateSampler = RouletteWheelSampler.create(model.getStartWeights(), factory);
		
		ImmutableMap.Builder<T, Sampler<T>> builder = ImmutableMap.builder();
		for(Map.Entry<T, Map<T, Double>> entry : transitionTable.entrySet())
		{
			builder.put(entry.getKey(), RouletteWheelSampler.create(entry.getValue(), factory));
		}
		
		
		this.transitionSamplers = builder.build();
		
		currentState = null;
	}
	
	public static <T> MarkovProcess<T> create(MarkovModel<T> model, SeedFactory factory)
	{
		return new MarkovProcess<T>(model, factory);
	}
	
	public T sample() throws Exception
	{
		if(currentState == null)
		{
			currentState = startStateSampler.sample();
			return currentState;
		}
		
		currentState = transitionSamplers.get(currentState).sample();
		return currentState;
	}
}