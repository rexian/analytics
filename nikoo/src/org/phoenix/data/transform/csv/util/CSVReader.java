/**
 * 
 * Copyright 2016 Surajit Paul
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.phoenix.data.transform.csv.util;

import java.io.IOException;
import java.io.Reader;

import com.opencsv.CSVReader;

public class CSVReader extends CSVReader {


	/**
	 * 
	 * @param reader
	 * @param separator
	 * @param quotechar
	 * @param escape
	 * @param line
	 */
	public CSVReader(Reader reader, char separator, char quotechar,
			char escape, int line) {
		super(reader, separator, quotechar, escape, line);		
	}

	/**
	 * Trim each value in the CSV row
	 */
	public String[] readNext() throws IOException {
		String[] result = super.readNext();
		for (int i=0; result != null && i<result.length; i++){
			if(result[i] == null){
				continue;
			}
			result[i] = result[i].trim();
		}
		return result;
	}
}
