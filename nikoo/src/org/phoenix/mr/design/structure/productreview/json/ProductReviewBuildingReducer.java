package org.phoenix.mr.design.structure.productreview.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
public class ProductReviewBuildingReducer  extends Reducer<Text, Text, Text, NullWritable> {

	private ArrayList<UserReview> reviews = new ArrayList<UserReview>();
	UserReview review = new UserReview();
	String productId = null;
	
	public void reduce(Text key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		ObjectMapper om = new ObjectMapper();
		
		String userReview = null;
		int index = 0;
		// For each input value
		for (Text t : values) {
			
			if (t.charAt(0) == 'P') {
				productId = t.toString().substring(1, t.toString().length())
						.trim();
			} else {
				
				userReview = t.toString().substring(1, t.toString().length()).trim();
				
				StringTokenizer tokens = new StringTokenizer(userReview, "\t");
				if(tokens.hasMoreTokens()){
					review.setUserId(tokens.nextToken());
				}
				if(tokens.hasMoreTokens()){
					review.setProfileName(tokens.nextToken());
				}
				if(tokens.hasMoreTokens()){
					review.setHelpfulness(tokens.nextToken());
				}
				if(tokens.hasMoreTokens()){
					review.setScore(Double.parseDouble(tokens.nextToken().trim()));
				}
				if(tokens.hasMoreTokens()){
					review.setTime(Long.parseLong(tokens.nextToken().trim()));
				}
				if(tokens.hasMoreTokens()){
					review.setSummary(tokens.nextToken());
				}
				if(tokens.hasMoreTokens()){
					review.setText(tokens.nextToken());
				}
				reviews.add(review);
			}
			
			if(productId != null){
				ProductReview pr = new ProductReview();
				pr.setProductId(productId);
				pr.setReviews(reviews);
				String productWithReview = om.writeValueAsString(pr);	
				context.write(new Text(productWithReview), NullWritable.get());
			}
		}			
	}
}
