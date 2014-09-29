package org.phoenix.mr.design.structure.productreview.xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 *
 */
@XmlRootElement
public class ProductReview {
	
	
	private String productId = null;
	
	private ArrayList<UserReview> reviews = null;
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public ArrayList<UserReview> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<UserReview> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "ProductReview [productId=" + productId + ", reviews=" + reviews.toString()
				+ "]";
	}
	
	
}
