package net.egork.collections.sequence;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Array<T> extends AbstractList<T> implements RandomAccess {
	public static<T> List<T> wrap(T...array) {
		return new ReferenceArray<T>(array);
	}

	public static List<Integer> wrap(int...array) {
		return new IntArray(array);
	}

	public static List<Long> wrap(long...array) {
		return new LongArray(array);
	}

	public static List<Character> wrap(char...array) {
		return new CharArray(array);
	}

	public static List<Double> wrap(double...array) {
		return new DoubleArray(array);
	}

	public static List<Boolean> wrap(boolean...array) {
		return new BooleanArray(array);
	}

	public static List<Short> wrap(short...array) {
		return new ShortArray(array);
	}

	public static List<Byte> wrap(byte...array) {
		return new ByteArray(array);
	}

	protected static class ReferenceArray<T> extends Array<T> {
		protected final T[] array;

		protected ReferenceArray(T[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public T get(int index) {
			return array[index];
		}

		public T set(int index, T value) {
			T result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class IntArray extends Array<Integer> {
		protected final int[] array;

		protected IntArray(int[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Integer get(int index) {
			return array[index];
		}

		public Integer set(int index, Integer value) {
			int result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class LongArray extends Array<Long> {
		protected final long[] array;

		protected LongArray(long[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Long get(int index) {
			return array[index];
		}

		public Long set(int index, Long value) {
			long result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class CharArray extends Array<Character> {
		protected final char[] array;

		protected CharArray(char[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Character get(int index) {
			return array[index];
		}

		public Character set(int index, Character value) {
			char result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class DoubleArray extends Array<Double> {
		protected final double[] array;

		protected DoubleArray(double[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Double get(int index) {
			return array[index];
		}

		public Double set(int index, Double value) {
			double result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class BooleanArray extends Array<Boolean> {
		protected final boolean[] array;

		protected BooleanArray(boolean[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Boolean get(int index) {
			return array[index];
		}

		public Boolean set(int index, Boolean value) {
			boolean result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class ShortArray extends Array<Short> {
		protected final short[] array;

		protected ShortArray(short[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Short get(int index) {
			return array[index];
		}

		public Short set(int index, Short value) {
			short result = array[index];
			array[index] = value;
			return result;
		}
	}

	protected static class ByteArray extends Array<Byte> {
		protected final byte[] array;

		protected ByteArray(byte[] array) {
			this.array = array;
		}

		public int size() {
			return array.length;
		}

		public Byte get(int index) {
			return array[index];
		}

		public Byte set(int index, Byte value) {
			byte result = array[index];
			array[index] = value;
			return result;
		}
	}
}
