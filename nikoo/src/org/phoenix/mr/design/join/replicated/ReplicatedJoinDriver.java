package org.phoenix.mr.design.join.replicated;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.phoenix.mr.book.ch5.ReplicatedJoinDriver.ReplicatedJoinMapper;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
@SuppressWarnings("deprecation")
public class ReplicatedJoinDriver extends Configured implements Tool {

	public static void main(String[] args)  throws Exception {
		
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 4) {
			System.err
					.println("Usage: ReplicatedJoin <user data> <comment data> <out> [inner|leftouter]");
			System.exit(1);
		}

		String joinType = otherArgs[3];
		if (!(joinType.equalsIgnoreCase("inner") || joinType
				.equalsIgnoreCase("leftouter"))) {
			System.err.println("Join type not set to inner or leftouter");
			System.exit(2);
		}

		// Configure the join type
		Job job = new Job(conf, "Replicated Join");
		job.getConfiguration().set("join.type", joinType);
		job.setJarByClass(ReplicatedJoinDriver.class);

		job.setMapperClass(ReplicatedJoinMapper.class);
		job.setNumReduceTasks(0);

		TextInputFormat.setInputPaths(job, new Path(otherArgs[1]));
		TextOutputFormat.setOutputPath(job, new Path(otherArgs[2]));

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// Configure the DistributedCache
		DistributedCache.addCacheFile(new Path(otherArgs[0]).toUri(),
				job.getConfiguration());

		DistributedCache.setLocalFiles(job.getConfiguration(), otherArgs[0]);

		System.exit(job.waitForCompletion(true) ? 0 : 3);
	}

	@Override
	public int run(String[] args) throws Exception {
		if(args.length !=3) {
			System.err.println("Usage: ReplicatedJoinDriver <input path> <input path> <outputpath>");
			System.exit(-1);
		}
				
		Job job = new Job();
		job.setJarByClass(ReplicatedJoinDriver.class);
		job.setJobName("Replicated Join Driver");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));	
				
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path(args[2]));
		
		System.exit(job.waitForCompletion(true) ? 0:1); 
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

}
