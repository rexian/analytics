package org.phoenix.mr.design.join.replicated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper component of the map-reduce job, performing Map side replicate join
 * @author Surajit Paul
 * @version 1.0
 *
 */
@SuppressWarnings("deprecation")
public class ReplicatedJoinMapper extends Mapper<Object, Text, Text, Text> {

	private Map<String, String> userInfo = new HashMap<String, String>();
	private Map<String, String> productInfo = new HashMap<String, String>();

	private Text outvalue = new Text();
	private String joinType = null;

	public void setup(Context context) throws IOException, InterruptedException {
		try {
			Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());

			if (files == null || files.length == 0) {
				throw new RuntimeException(
						"User information is not set in DistributedCache");
			}

			// Read all files in the DistributedCache
			for (Path p : files) {
				BufferedReader rdr = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(
										new File(p.toString()))));

				String line;				
				while ((line = rdr.readLine()) != null) {


					String[] product = line.split("\t");
					String userId = product[1];

					if (userId != null) {
						productInfo.put(userId, product[0]);
					}
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
		joinType = context.getConfiguration().get("join.type");
	}

	/**
	 * @param Object
	 * @param Text
	 * @param Context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] user = line.split("\t");
		String userId = user[0];

		if (userId == null) {
			return;
		}

		String userInformation = userInfo.get(userId);

		// If the user information is not null, then output
		if (userInformation != null) {
			outvalue.set(userInformation);
			context.write(value, outvalue);
		} else if (joinType.equalsIgnoreCase("leftouter")) {			
			context.write(value, new Text(""));
		}
	}
}