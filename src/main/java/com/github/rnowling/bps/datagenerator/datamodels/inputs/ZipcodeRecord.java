/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.rnowling.bps.datagenerator.datamodels.inputs;

import java.io.Serializable;

import com.github.rnowling.bps.datagenerator.datamodels.Pair;

public class ZipcodeRecord implements Serializable
{
	private static final long serialVersionUID = 1769986686070108470L;
	
	final String zipcode;
	final Pair<Double, Double> coordinates;
	final String city;
	final String state;
	final double medianHouseholdIncome;
	final long population;
	
	public ZipcodeRecord(String zipcode, Pair<Double, Double> coordinates,
			String city, String state, double medianHouseholdIncome, long population)
	{
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.coordinates = coordinates;
		this.medianHouseholdIncome = medianHouseholdIncome;
		this.population = population;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public Pair<Double, Double> getCoordinates()
	{
		return coordinates;
	}
	
	public double getMedianHouseholdIncome()
	{
		return medianHouseholdIncome;
	}
	
	public long getPopulation()
	{
		return population;
	}
	
	public double distance(ZipcodeRecord other)
	{
		if(other.getZipcode().equals(zipcode))
			return 0.0;
		
		Pair<Double, Double> otherCoords = other.getCoordinates();
		
		double dist = Math.sin(Math.toRadians(coordinates.getFirst())) *
				Math.sin(Math.toRadians(otherCoords.getFirst())) +
				Math.cos(Math.toRadians(coordinates.getFirst())) *
				Math.cos(Math.toRadians(otherCoords.getFirst())) *
				Math.cos(Math.toRadians(coordinates.getSecond() - otherCoords.getSecond()));
		dist = Math.toDegrees(Math.acos(dist)) * 69.09;
		
		return dist;		
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
}
