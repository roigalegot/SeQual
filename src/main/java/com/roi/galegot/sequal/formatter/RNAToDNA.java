package com.roi.galegot.sequal.formatter;

import org.apache.spark.api.java.JavaRDD;

import com.roi.galegot.sequal.common.Sequence;

/**
 * The Class RNAToDNA.
 */
public class RNAToDNA implements Formatter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3160351422137802821L;

	@Override
	public JavaRDD<Sequence> format(JavaRDD<Sequence> sequences) {
		return sequences.map(sequence -> this.doFormat(sequence));
	}

	/**
	 * Do format.
	 *
	 * @param sequence the sequence
	 * @return the sequence
	 */
	private Sequence doFormat(Sequence sequence) {
		sequence.setSequenceString(sequence.getSequenceString().replace("U", "T"));
		return sequence;
	}
}