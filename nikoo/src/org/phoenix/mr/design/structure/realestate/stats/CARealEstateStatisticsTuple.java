package org.phoenix.mr.design.structure.realestate.stats;

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
public class CARealEstateStatisticsTuple implements Writable {

	private float meanCost = 0.0f;
	private float medianCost = 0.0f;
	private float stdDev = 0.0f;
	private float range = 0.0f;
	private int count = 0;
	
	
	public float getMeanCost() {
		return meanCost;
	}

	public void setMeanCost(float meanCost) {
		this.meanCost = meanCost;
	}

	public float getMedianCost() {
		return medianCost;
	}

	public void setMedianCost(float medianCost) {
		this.medianCost = medianCost;
	}
	
	public float getStdDev() {
		return stdDev;
	}

	public void setStdDev(float stdDev) {
		this.stdDev = stdDev;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
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
		
		meanCost = in.readFloat();
		medianCost = in.readFloat();
		stdDev = in.readFloat();
		range = in.readFloat();
		count = in.readInt();
	}

	/**
	 * 
	 */
	public void write(DataOutput out) throws IOException {
		
		out.writeFloat(meanCost);
		out.writeFloat(medianCost);
		out.writeFloat(stdDev);
		out.writeFloat(range);
		out.writeInt(count);
	}

	@Override
	public String toString() {
		return "[meanCost=" + meanCost
				+ ", medianCost=" + medianCost + ", stdDev=" + stdDev
				+ ", range=" + range + ", count=" + count + "]";
	}	
}
