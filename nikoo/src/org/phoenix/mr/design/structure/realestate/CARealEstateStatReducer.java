package org.phoenix.mr.design.structure.realestate;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 08.08.2014
 * @see CARealEstateStatisticsTuple.java
 */
public class CARealEstateStatReducer extends Reducer<Text, CARealEstateStatTuple, Text, CARealEstateStatTuple> {
	
	private CARealEstateStatTuple result = new CARealEstateStatTuple();

	/**
	 * Reduce method to calculate minimum, maximum and average cost of the apartment
	 * for each apartment type for last 18 years
	 */
	public void reduce(Text key, Iterable<CARealEstateStatTuple> values,
			Context context) throws IOException, InterruptedException {
		
		result.setAvgCost(0.0f);
		result.setCount(0);
		result.setMaxCost(0.0f);
		result.setMinCost(0.0f);
		int sum = 0;
		int count = 0;
		for (CARealEstateStatTuple val : values) {			
			
			if(result.getMinCost() == 0.0 || result.getMinCost() > val.getMinCost()){
				result.setMinCost(val.getMinCost());
			}
			
			if(result.getMaxCost() == 0.0 || result.getMaxCost() < val.getMaxCost()){
				result.setMaxCost(val.getMaxCost());
			}
			
			sum += val.getAvgCost();
			count += val.getCount();				
								
		}
		if(count != 0){
			result.setAvgCost(sum/count);
		}		
		result.setCount(count);
		context.write(key, result);				
	}
}
