package com.github.rnowling.bps.datagenerator.datamodels.inputs;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InputData
{
	List<ZipcodeRecord> zipcodeTable;
	Names names;
	Collection<ProductCategory> productCategories;
	
	public InputData(List<ZipcodeRecord> zipcodeTable,
			Names names,
			Collection<ProductCategory> productCategories)
	{
		this.zipcodeTable = Collections.unmodifiableList(zipcodeTable);
		this.names = names;
		this.productCategories = Collections.unmodifiableCollection(productCategories);
	}
	
	public List<ZipcodeRecord> getZipcodeTable()
	{
		return zipcodeTable;
	}
	
	public Names getNames()
	{
		return names;
	}
	
	public Collection<ProductCategory> getProductCategories()
	{
		return productCategories;
	}
	
	
}
