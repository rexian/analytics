package org.phoenix.mr.design.structure.realestate.stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 08.08.2014
 * @see CARealEstateStatisticsTuple.java
 */
public class CARealEstateStatisticsReducer extends Reducer<Text, FloatWritable, Text, CARealEstateStatisticsTuple> {
	
	private CARealEstateStatisticsTuple result = new CARealEstateStatisticsTuple();
	private ArrayList<Float> costList = new ArrayList<Float>();
	
	/**
	 * Reduce method to calculate mean, median, range and stddev of cost of the apartment
	 * for each apartment type for last 18 years
	 */
	public void reduce(Text key, Iterable<FloatWritable> values,
			Context context) throws IOException, InterruptedException {
				
		costList.clear();
		result.setMeanCost(0.0f);
		result.setMedianCost(0.0f);
		result.setStdDev(0.0f);
		result.setRange(0.0f);
		result.setCount(0);		
		int sum = 0;
		int count = 0;
		float range = 0.0f;
		float mean = 0.0f;
		
		for (FloatWritable val : values) {			
			costList.add(val.get());
			sum += val.get();
			++count;								
		}
					
		/* sort costList to calculate median */
		Collections.sort(costList);

		/* if costList is an even value, average middle two elements */
		if (count % 2 == 0) {
			result.setMedianCost((costList.get((int) count / 2 - 1) + costList
					.get((int) count / 2)) / 2.0f);
		} else {
			/* else, set median to middle value */
			result.setMedianCost(costList.get((int) count / 2));
		}

		/* calculate standard deviation */
		mean = sum / count;
		
		/* Difference between the maximum apartment cost and the minimum cost in a year */
		range = Collections.max(costList) - Collections.min(costList);		

		float sumOfSquares = 0.0f;
		for (Float f : costList){
			sumOfSquares += (f - mean) * (f - mean);
		}
		
		result.setMeanCost(mean);
		result.setRange(range);
		result.setStdDev((float) Math.sqrt(sumOfSquares / (count - 1)));		
		result.setCount(count);
		
		context.write(key, result);				
	}
}
