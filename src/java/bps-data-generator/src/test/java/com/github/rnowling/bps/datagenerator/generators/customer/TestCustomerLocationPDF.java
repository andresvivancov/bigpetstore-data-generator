package com.github.rnowling.bps.datagenerator.generators.customer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.rnowling.bps.datagenerator.Constants;
import com.github.rnowling.bps.datagenerator.algorithms.SeedFactory;
import com.github.rnowling.bps.datagenerator.algorithms.samplers.Sampler;
import com.github.rnowling.bps.datagenerator.datamodels.inputs.ZipcodeRecord;
import com.github.rnowling.bps.datagenerator.datamodels.outputs.Store;
import com.github.rnowling.bps.datagenerator.datareaders.ZipcodeReader;
import com.github.rnowling.bps.datagenerator.generators.store.StoreSamplerBuilder;

public class TestCustomerLocationPDF
{

	@Test
	public void testProbability() throws Exception
	{
		ZipcodeReader zipcodeReader = new ZipcodeReader();
		zipcodeReader.setCoordinatesFile(Constants.COORDINATES_FILE);
		zipcodeReader.setIncomesFile(Constants.INCOMES_FILE);
		zipcodeReader.setPopulationFile(Constants.POPULATION_FILE);
		List<ZipcodeRecord> zipcodes = zipcodeReader.readData();
		
		SeedFactory factory = new SeedFactory(1234);
		
		StoreSamplerBuilder storeSamplerBuilder = new StoreSamplerBuilder(zipcodes, factory);
		Sampler<Store> storeSampler = storeSamplerBuilder.build();
		
		List<Store> stores = new ArrayList<Store>();
		for(int i = 0; i < 10; i++)
		{
			Store store = storeSampler.sample();
			stores.add(store);
		}
		
		CustomerLocationPDF customerLocationPDF = new CustomerLocationPDF(zipcodes, stores, 
					Constants.AVERAGE_CUSTOMER_STORE_DISTANCE);
		
		double prob = customerLocationPDF.probability(zipcodes.get(0));
		
		assertTrue(prob > 0.0);
	}

}
