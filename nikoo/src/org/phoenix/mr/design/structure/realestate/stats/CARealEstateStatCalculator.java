package org.phoenix.mr.design.structure.realestate.stats;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * The MapReduce job processes an input data set of historical price of 1BHK, 2BHK, and 3BHK
 * apartment in CA and output as annual lowest, highest and average price of the apartment 
 * for last 18 years.
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class CARealEstateStatCalculator extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length !=2) {
			System.err.println("Usage: CARealEstateStatCalculator <input path> <outputpath>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(CARealEstateStatCalculator.class);
		job.setJobName("Real Estate Stats Calculator");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.setMapperClass(CARealEstateStatisticsMapper.class);
		job.setReducerClass(CARealEstateStatisticsReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(CARealEstateStatisticsTuple.class);
		
		System.exit(job.waitForCompletion(true) ? 0:1); 
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CARealEstateStatCalculator driver = new CARealEstateStatCalculator();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

}
