package org.phoenix.mr.design.structure.wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class WordCountDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length !=2) {
			System.err.println("Usage: WordCountDriver <input path> <outputpath>");
			System.exit(-1);
		}
		
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJarByClass(WordCountDriver.class);
		job.setJobName("Word Count");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
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
		WordCountDriver driver = new WordCountDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

}
