package com.roi.galegot.sequal.formatter;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;

import com.roi.galegot.sequal.common.Sequence;

/**
 * The Interface Formatter.
 */
public interface Formatter extends Serializable {

	/**
	 * Format.
	 *
	 * @param sequences the sequences
	 * @return the java RDD
	 */
	public JavaRDD<Sequence> format(JavaRDD<Sequence> sequences);

}