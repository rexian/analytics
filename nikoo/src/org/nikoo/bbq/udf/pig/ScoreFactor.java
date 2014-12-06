package org.nikoo.bbq.udf.pig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

/**
 * Pig UDF used to return score value for each input score code
 * @author Surajit Paul
 * @version 1.1
 * @since 08.04.2014
 *
 */
public class ScoreFactor extends EvalFunc<Tuple> {
 
	private static String accessKey = "GYHJBFKJSNI^*&SBKB";
	private static String secretKey = "gvdjbk8345034jhbVJHGIUFH873839u03hkzx";
	private static String bucketName = "s3_bucket_name";
	private static String key = "udf/resources/parameters.csv";
	
	@Override
	/**
	 * @input Tuple takes 3 input parameters - customer size cd, sales region cd and geo cd
	 * @return final score value as string
	 */
	public Tuple exec(Tuple tuple) throws IOException {
		String customerSizeCD = null;
		String salesRegionNM = null;
		String geoCD = null;				
		
		/* Checks if Tuple is empty */
		if(tuple == null || tuple.size() == 0){
			return null;
		}
		try{
			customerSizeCD =  (String) tuple.get(0);
			salesRegionNM = (String) tuple.get(1);
			geoCD = (String) tuple.get(2);
		}catch(Exception e){
			      System.err.println(e.getMessage());
		 }		
		return fetchAgreementScore(customerSizeCD, geoCD, salesRegionNM);
	}

	/**
	 * Returns score value corresponding to each key/score code
	 * @param scoreCode
	 * @return
	 */
	private Tuple fetchAgreementScore(String customerSizeCD, String geoCD, String salesRegionNM){
		System.out.println("CUSTOMER SIZE: "+customerSizeCD+ "-- GEO CD:"+geoCD+"-- SALES REGION CD: "+salesRegionNM);
		if(customerSizeCD == null || "NULL".equalsIgnoreCase(customerSizeCD) || geoCD == null || "NULL".equalsIgnoreCase(geoCD) || salesRegionNM == null || "NULL".equalsIgnoreCase(salesRegionNM)){
			return null;
		}	
				
		String factorVal = null;
		double interceptf = 0.0;
		double customerSizeCDf = 0.0;
		double geoCDf = 0.0;
		double salesRegionNMf = 0.0;
		double score = 0.0;
		TupleFactory tupleFactory = TupleFactory.getInstance();
		Map<String, String> estimateMap = new HashMap<String, String>();
		List<String> output = new LinkedList<String>();
		estimateMap = this. getScoreMap();
		
		factorVal = estimateMap.get("Intercept");
		if(factorVal != null){
			interceptf = Double.parseDouble(factorVal);
		}
		factorVal = estimateMap.get(geoCD);
		output.add(factorVal);
		if(factorVal != null){
			geoCDf = Double.parseDouble(factorVal);
		}
		factorVal = estimateMap.get(geoCD+"-"+customerSizeCD);
		output.add(factorVal);
		if(factorVal != null){
			customerSizeCDf = Double.parseDouble(factorVal);
		}
		factorVal = estimateMap.get(salesRegionNM);
		output.add(factorVal);
		if(factorVal != null){
			salesRegionNMf = Double.parseDouble(factorVal);
		}
		double tScore = interceptf + geoCDf + customerSizeCDf + salesRegionNMf;
		System.out.println("tScore: " + tScore);
		score = 1 / (1 + Math.exp(- tScore));
		output.add(String.valueOf(score));		
		System.out.println("Output: " + output.toString());
		return tupleFactory.newTupleNoCopy(output);				 
	}
	
	/**
	 * 
	 * @return a map object containing score estimates
	 */
	private Map<String, String> getScoreMap(){
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3-us-west-1.amazonaws.com");
		Map<String, String> estimateMap = new HashMap<String, String>();

		S3Object s3object = conn.getObject(new GetObjectRequest(bucketName, key));
		System.out.println(s3object.getKey());
		S3ObjectInputStream strm = s3object.getObjectContent();
		BufferedReader br = null;
		String line = null;
		try{
			br = new BufferedReader(new InputStreamReader(strm));
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				estimateMap.put(fields[0], fields[1]);
			}
			System.out.println(estimateMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return estimateMap;
	}
}
