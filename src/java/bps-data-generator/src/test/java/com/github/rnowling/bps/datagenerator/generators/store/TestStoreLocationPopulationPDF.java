package com.github.rnowling.bps.datagenerator.generators.store;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.rnowling.bps.datagenerator.Constants;
import com.github.rnowling.bps.datagenerator.datamodels.inputs.ZipcodeRecord;
import com.github.rnowling.bps.datagenerator.datareaders.ZipcodeReader;

public class TestStoreLocationPopulationPDF
{

	@Test
	public void testProbability() throws Exception
	{
		ZipcodeReader reader = new ZipcodeReader();
		reader.setCoordinatesFile(Constants.COORDINATES_FILE);
		reader.setIncomesFile(Constants.INCOMES_FILE);
		reader.setPopulationFile(Constants.POPULATION_FILE);
		
		List<ZipcodeRecord> zipcodes = reader.readData();
		
		assertTrue(zipcodes.size() > 0);
		
		StoreLocationIncomePDF pdf = new StoreLocationIncomePDF(zipcodes, 100.0);
		
		for(ZipcodeRecord record : zipcodes)
		{
			assertTrue(pdf.probability(record) > 0.0);
		}
		
	}

}
