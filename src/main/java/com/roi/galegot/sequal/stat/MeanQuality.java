package com.roi.galegot.sequal.stat;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;

import com.roi.galegot.sequal.common.Sequence;

/**
 * The Class MeanQuality.
 */
public class MeanQuality implements Stat {

	private static final long serialVersionUID = 8125961407288475771L;

	/**
	 * Measure.
	 *
	 * @param sequences the sequences
	 * @return the double
	 */
	@Override
	public Double measure(JavaRDD<Sequence> sequences) {

		BigDecimal mean;
		DummyCount initialDummy = new DummyCount();
		DummyCount resultDummy;
		Function2<DummyCount, Sequence, DummyCount> addAndCount;
		Function2<DummyCount, DummyCount, DummyCount> combine;

		if (sequences.isEmpty() || !sequences.first().getHasQuality()) {
			return Double.valueOf(0);
		}

		addAndCount = new Function2<DummyCount, Sequence, DummyCount>() {

			private static final long serialVersionUID = 1L;

			@Override
			public DummyCount call(DummyCount v1, Sequence v2) throws Exception {
				v1.countQuality = v1.countQuality.add(BigDecimal.valueOf(v2.getQuality()));
				v1.countNumber += (long) 1;
				return v1;
			}

		};

		combine = new Function2<DummyCount, DummyCount, DummyCount>() {

			private static final long serialVersionUID = 1L;

			@Override
			public DummyCount call(DummyCount v1, DummyCount v2) throws Exception {
				return MeanQuality.this.combineFunction(v1, v2);
			}

		};

		resultDummy = sequences.aggregate(initialDummy, addAndCount, combine);
		mean = resultDummy.getMean();

		return mean.doubleValue();
	}

	/**
	 * Measure pair.
	 *
	 * @param sequences the sequences
	 * @return the double
	 */
	@Override
	public Double measurePair(JavaRDD<Sequence> sequences) {
		BigDecimal mean;
		DummyCount initialDummy = new DummyCount();
		DummyCount resultDummy;
		Function2<DummyCount, Sequence, DummyCount> addAndCount;
		Function2<DummyCount, DummyCount, DummyCount> combine;

		addAndCount = new Function2<DummyCount, Sequence, DummyCount>() {

			private static final long serialVersionUID = 1L;

			@Override
			public DummyCount call(DummyCount v1, Sequence v2) throws Exception {
				v1.countQuality = v1.countQuality.add(BigDecimal.valueOf(v2.getQualityPair()));
				v1.countNumber += (long) 1;
				return v1;
			}

		};

		combine = new Function2<DummyCount, DummyCount, DummyCount>() {

			private static final long serialVersionUID = 1L;

			@Override
			public DummyCount call(DummyCount v1, DummyCount v2) throws Exception {
				return MeanQuality.this.combineFunction(v1, v2);
			}

		};

		resultDummy = sequences.aggregate(initialDummy, addAndCount, combine);
		mean = resultDummy.getMean();

		return mean.doubleValue();
	}

	/**
	 * Combine function.
	 *
	 * @param v1 the v 1
	 * @param v2 the v 2
	 * @return the dummy count
	 * @throws Exception the exception
	 */
	private DummyCount combineFunction(DummyCount v1, DummyCount v2) throws Exception {
		v1.countQuality = v1.countQuality.add(v2.countQuality);
		v1.countNumber += v2.countNumber;
		return v1;
	}

	/**
	 * The Class DummyCount.
	 */
	public class DummyCount implements Serializable {

		private static final long serialVersionUID = -7774902974273299201L;
		private BigDecimal countQuality = BigDecimal.ZERO;
		private Long countNumber = Long.valueOf(0);

		/**
		 * Gets the mean.
		 *
		 * @return the mean
		 */
		public BigDecimal getMean() {
			if (this.countNumber <= 0) {
				return BigDecimal.ZERO;
			}

			return this.countQuality.divide(BigDecimal.valueOf(this.countNumber), 2, BigDecimal.ROUND_UP);
		}

	}

}