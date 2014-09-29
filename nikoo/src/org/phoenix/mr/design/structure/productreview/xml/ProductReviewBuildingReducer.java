package org.phoenix.mr.design.structure.productreview.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.w3c.dom.Document;

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
		
		String userReview = null;
		int index = 0;
		
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
				String productWithReview = nestElementTree(productId, reviews);	
				context.write(new Text(productWithReview), NullWritable.get());
			}
		}			
	}
	
	/**
	 * 
	 * @param productId
	 * @param reviews
	 * @return
	 */
	private String nestElementTree(String productId, ArrayList<UserReview> reviews){
		ProductReview prodReview = new ProductReview();
		prodReview.setProductId(productId);
		prodReview.setReviews(reviews);
		System.out.println("Product Review: "+prodReview.toString());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);        
		
		JAXBContext jaxbContext;
		String xmldata = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.newDocument();
			jaxbContext = JAXBContext.newInstance(ProductReview.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);			
			jaxbMarshaller.marshal(prodReview, doc);	
			
			System.out.println("Document: "+doc.toString());
			xmldata = transformDocumentToString(doc);			
			
		} catch (JAXBException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
 
		return xmldata;
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	private String transformDocumentToString(Document doc) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(
					writer));
			return writer.getBuffer().toString().replaceAll("\n|\r", "");
		} catch (Exception e) {
			return null;
		}
	}
}
