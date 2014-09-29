package org.phoenix.mr.design.structure.realestate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 08.08.2014
 * @see CARealEstateStatisticsTuple.java
 */
public class CARealEstateStatMapper extends Mapper<Object, Text, Text, Writable> {
	
	DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	CARealEstateStatTuple realEstateTuple = new CARealEstateStatTuple();
	private Text aptKey = new Text();
	private String aptType = null;

	/**
	 * 
	 */
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		int year = 0;
		float cost = 0f;
		
		StringTokenizer itr = new StringTokenizer(line, ",");
		while (itr.hasMoreTokens()) {
			aptType = itr.nextToken();			
			year = LocalDate.parse(itr.nextToken(), dtfmt).getYear();
			aptKey.set(aptType + "," + year);
			cost = Float.parseFloat(itr.nextToken());			
		}
		
		realEstateTuple.setCount(1);		
		realEstateTuple.setMinCost(cost);
		realEstateTuple.setMaxCost(cost);
		realEstateTuple.setAvgCost(cost);
		context.write(aptKey, realEstateTuple);
	}

}
