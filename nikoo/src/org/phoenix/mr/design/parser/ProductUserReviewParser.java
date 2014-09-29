package org.phoenix.mr.design.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 
 * @author Surajit Paul
 *
 */
public class ProductUserReviewParser {

	public static void main(String[] args) {		
//		File file = new File("src/org/phoenix/mr/design/parser/holycross.txt");
		File file = new File(args[0]);
		File product = new File("src/org/phoenix/mr/design/parser/product.txt");
		File userFile = new File("src/org/phoenix/mr/design/parser/user.txt");
		InputStreamReader isr = null;
		PrintWriter prod = null;
		PrintWriter user = null;
		BufferedReader br = null;
		String currentLine = null;
		try {
			isr = new InputStreamReader(new FileInputStream(file));
			prod = new PrintWriter(new BufferedWriter(new FileWriter(product)));
			user = new PrintWriter(new BufferedWriter(new FileWriter(userFile)));
			br = new BufferedReader(isr);
			while ((currentLine = br.readLine()) != null) {
	            System.out.println(currentLine);
//	            StringTokenizer st = new StringTokenizer(currentLine, ":");
	            String[] fields = currentLine.split(":");
	            for(int k = 0; k < fields.length - 1; k++){
	            	String key = fields[0];
	            	String val = fields[1];
	            	if(key.equalsIgnoreCase("product/productId")){
	            		prod.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/userId")){
	            		prod.write(val+"\n");
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/profileName")){
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/helpfulness")){
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/score")){
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/time")){
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/summary")){
	            		user.write(val+"\t");
	            	}
	            	if(key.equalsIgnoreCase("review/text")){
	            		user.write(val+"\n");
	            	}
	            }
	         }
			prod.flush();
			user.flush();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception: "+e.getMessage());
		}
		finally{			
			try {
				if(isr != null){
					isr.close();
				}
				if(prod != null){
					prod.close();
				}
				if(user != null){
					user.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}			
		}
	}
}
