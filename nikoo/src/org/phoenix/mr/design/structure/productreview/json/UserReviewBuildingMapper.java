package org.phoenix.mr.design.structure.productreview.json;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class UserReviewBuildingMapper  extends Mapper<Object, Text, Text, Writable>{

	private Text outkey = new Text();
	private Text outvalue = new Text();
	
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
				
		String[] fields = line.split("\t");
		outkey.set(fields[0]);
		outvalue.set("R" + line.toString());		
				
		context.write(outkey, outvalue);
	}
}
