package org.phoenix.mr.design.structure.productreview.json;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.phoenix.mr.design.structure.productreview.json.ProductBuildingMapper;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class ProductReviewBuildingDriver  extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		if(args.length !=3) {
			System.err.println("Usage: ProductReviewBuildingDriver <input path> <input path> <outputpath>");
			System.exit(-1);
		}
		
		
		Job job = new Job();
		job.setJarByClass(ProductReviewBuildingDriver.class);
		job.setJobName("Product Review Building Driver");
		
		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, ProductBuildingMapper.class);
		
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, UserReviewBuildingMapper.class);
		
		job.setReducerClass(ProductReviewBuildingReducer.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setOutputFormatClass(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(job, new Path(args[2]));
		
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
		ProductReviewBuildingDriver driver = new ProductReviewBuildingDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

}
