package org.nikoo.bbq.udf.pig;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

/**
 * @author Surajit Paul
 * @version 1.0 
 */
public class DatasetMerger extends EvalFunc<Tuple> {

	/**
	 * Exec method in UDF takes in a Tuple and returns a 
	 * Tuple after filtration of the input data set
	 * @param Tuple
	 * @return Tuple
	 */
	public Tuple exec(Tuple tuple) throws IOException {
		TupleFactory tupleFactory = TupleFactory.getInstance();
		List<String> output = new ArrayList<String>();		
		String regexp = "([0-9]{4})-([01]{0,1}[0-9])-([0-2]{0,1}[0-9]|30|31) ([01]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]).([0-9])";
		
		/* Checks if Tuple is empty */
		if(tuple == null || tuple.size() == 0){
			return null;
		}
		
		try{	
			List<Object> tupleList = (List<Object>) tuple.getAll();
			List<Timestamp> dates = new ArrayList<Timestamp>();
			int tupleSeparationIndex = 0;
			String key = tupleList.get(0).toString();
			for(int i = 0; i < tupleList.size(); i++){
				if(key.equals(tupleList.get(i).toString())){
					tupleSeparationIndex = i;
				}				
			}
			
			/* Picks up the dates from the input data sets using regular expression */
			Pattern pat = Pattern.compile(regexp); // date format - yyyy-MM-dd hh:mm:ss.s
			Matcher match;
			Iterator<Object> li = tupleList.iterator();
			while(li.hasNext()){
				String date = li.next().toString();
				match = pat.matcher(date);
				if(match.matches()){
					dates.add(Timestamp.valueOf(date));
				}
			}			
			
			/* Loads the data set in the Tuple for the latest record */
			if(dates.size() == 4){			
				if(dates.get(0).before(dates.get(2))){
					for(int i = tupleSeparationIndex; i < tupleList.size(); i++){
						output.add(tupleList.get(i).toString());
					}
				}
				else{
					for(int i = 0; i < tupleSeparationIndex; i++){			
						output.add(tupleList.get(i).toString());
					}			
				}					
			}			
		}		
		catch(Exception e){
			System.err.println("Error : "+e.getMessage());
		}		
		return tupleFactory.newTupleNoCopy(output);
	}
}
