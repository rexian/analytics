package nikoo.org.bbq.udf.hive;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
/**
 * Hive UDF to eliminate back slash from text
 * @author t_pauls
 *@version 1.0
 */
public class CleanEscape extends UDF {
	
	public Text evaluate(Text input) {
	    if(input == null) return null;
	    String escape = "\\";
	    String field = input.toString();
	    
	    while(escape.equalsIgnoreCase(String.valueOf(field.charAt(field.length() - 1)))){
	    	field = field.substring(0, field.length() - 1);
	    	if(field.equalsIgnoreCase("")) {
	    		field = "NULL";
	    		return new Text(field);
	    	}
		}			    
	    return new Text(field);
	 }
}

