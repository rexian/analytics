package org.phoenix.mr.design.structure.realestate;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 
 * @author Surajit Paul
 * @version 1.0
 * @since 08.08.2014
 *
 */
public class CARealEstateStatTuple implements Writable {

	private float minCost = 0f;
	private float maxCost = 0f;
	private float avgCost = 0f;
	private int count = 0;
	
	
	public float getMinCost() {
		return minCost;
	}

	public void setMinCost(float minCost) {
		this.minCost = minCost;
	}

	public float getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(float maxCost) {
		this.maxCost = maxCost;
	}

	public float getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(float avgCost) {
		this.avgCost = avgCost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	/**
	 * 
	 */
	public void readFields(DataInput in) throws IOException, EOFException {
		
		minCost = in.readFloat();
		maxCost = in.readFloat();
		avgCost = in.readFloat();
		count = in.readInt();
	}

	/**
	 * 
	 */
	public void write(DataOutput out) throws IOException {
		
		out.writeFloat(minCost);
		out.writeFloat(maxCost);
		out.writeFloat(avgCost);
		out.writeInt(count);
	}

	/**
	 * 
	 */
	public String toString() {
		return "[minCost=" + minCost
				+ ", maxCost=" + maxCost + ", avgCost=" + avgCost + ", count="
				+ count + "]";
	}
	
}
