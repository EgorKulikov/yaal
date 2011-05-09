package net.egork.collections.sequence;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class ArrayWrapper<T> extends AbstractWritableSequence<T> {
	public static<T> ArrayWrapper<T> wrap(T...array) {
		return new ReferenceArrayWrapper<T>(array);
	}

	public static WritableSequence<Integer> wrap(int...array) {
		return new IntArrayWrapper(array);
	}

	public static WritableSequence<Long> wrap(long...array) {
		return new LongArrayWrapper(array);
	}

	public static WritableSequence<Character> wrap(char...array) {
		return new CharArrayWrapper(array);
	}

	public static WritableSequence<Double> wrap(double...array) {
		return new DoubleArrayWrapper(array);
	}

	public static WritableSequence<Boolean> wrap(boolean...array) {
		return new BooleanArrayWrapper(array);
	}

	public static WritableSequence<Short> wrap(short...array) {
		return new ShortArrayWrapper(array);
	}

	public static WritableSequence<Byte> wrap(byte...array) {
		return new ByteArrayWrapper(array);
	}

	protected static class ReferenceArrayWrapper<T> extends ArrayWrapper<T> {
		protected final T[] array;

		protected ReferenceArrayWrapper(T[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public T get(int index) {
			return array[index];
		}

		public void set(int index, T value) {
			array[index] = value;
		}
	}

	protected static class IntArrayWrapper extends ArrayWrapper<Integer> {
		protected final int[] array;

		protected IntArrayWrapper(int[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Integer get(int index) {
			return array[index];
		}

		public void set(int index, Integer value) {
			array[index] = value;
		}
	}

	protected static class LongArrayWrapper extends ArrayWrapper<Long> {
		protected final long[] array;

		protected LongArrayWrapper(long[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Long get(int index) {
			return array[index];
		}

		public void set(int index, Long value) {
			array[index] = value;
		}
	}

	protected static class CharArrayWrapper extends ArrayWrapper<Character> {
		protected final char[] array;

		protected CharArrayWrapper(char[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Character get(int index) {
			return array[index];
		}

		public void set(int index, Character value) {
			array[index] = value;
		}
	}

	protected static class DoubleArrayWrapper extends ArrayWrapper<Double> {
		protected final double[] array;

		protected DoubleArrayWrapper(double[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Double get(int index) {
			return array[index];
		}

		public void set(int index, Double value) {
			array[index] = value;
		}
	}

	protected static class BooleanArrayWrapper extends ArrayWrapper<Boolean> {
		protected final boolean[] array;

		protected BooleanArrayWrapper(boolean[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Boolean get(int index) {
			return array[index];
		}

		public void set(int index, Boolean value) {
			array[index] = value;
		}
	}

	protected static class ShortArrayWrapper extends ArrayWrapper<Short> {
		protected final short[] array;

		protected ShortArrayWrapper(short[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Short get(int index) {
			return array[index];
		}

		public void set(int index, Short value) {
			array[index] = value;
		}
	}

	protected static class ByteArrayWrapper extends ArrayWrapper<Byte> {
		protected final byte[] array;

		protected ByteArrayWrapper(byte[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Byte get(int index) {
			return array[index];
		}

		public void set(int index, Byte value) {
			array[index] = value;
		}
	}
}
