package org.phoenix.mr.design.structure.productreview.xml;

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
public class ProductBuildingMapper  extends Mapper<Object, Text, Text, Writable>{
	
	private Text outkey = new Text();
	private Text outvalue = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
				

		String line = value.toString();
				
		String[] product = line.split("\t");
		outvalue.set("P" + product[0].toString());
		outkey.set(product[1]);
		
		
		
		context.write(outkey, outvalue);
	}
}
