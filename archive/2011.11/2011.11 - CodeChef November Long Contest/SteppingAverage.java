package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SteppingAverage {
	private int size = 12;
	private int bitSize = (1 << (size - 1));
	private int partSize = size / 2;
	private int leftPartSize = (size + 1) / 2;

	private int[][] count = new int[leftPartSize + 1][bitSize + 1];
	private int[][][] lastLeft = new int[leftPartSize + 1][bitSize + 1][];
	private int[][][] lastRight = new int[partSize + 1][bitSize + 1][];
	private double[][][] valueLeft = new double[leftPartSize + 1][bitSize + 1][];
	private double[][][] valueRight = new double[partSize + 1][bitSize + 1][];

	{
		count[0][0] = 1;
		for (int i = 1; i <= leftPartSize; i++) {
			for (int j = 0; j <= bitSize; j++) {
				for (int k = 1; k <= j; k *= 2)
					count[i][j] += count[i - 1][j - k];
			}
		}
		Arrays.fill(valueLeft[0], new double[0]);
		Arrays.fill(valueRight[0], new double[0]);
		valueLeft[0][0] = new double[1];
		valueRight[0][0] = new double[1];
		for (int i = 1; i <= partSize; i++) {
			for (int j = 0; j <= bitSize; j++) {
				lastLeft[i][j] = new int[count[i][j]];
				lastRight[i][j] = new int[count[i][j]];
				valueLeft[i][j] = new double[count[i][j]];
				valueRight[i][j] = new double[count[i][j]];
			}
		}
		for (int i = partSize + 1; i <= leftPartSize; i++) {
			for (int j = 0; j <= bitSize; j++) {
				lastLeft[i][j] = new int[count[i][j]];
				valueLeft[i][j] = new double[count[i][j]];
			}
		}
	}

//	Random rand = new Random(42);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		int target = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++)
			values[i] -= target;
		double sum = 0;
		for (int value : values)
			sum += value;
		final double[] numbers = new double[2 * count - 1];
		for (int i = 0; i < count; i++)
			numbers[i] = values[i];
		@SuppressWarnings({"unchecked"})
		Pair<Integer, Integer>[] result = new Pair[count - 1];
		int size = 1;
		int[] order = new int[count];
		for (int i = 0; i < count; i++)
			order[i] = i;
		Collections.sort(Array.wrap(order), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(numbers[o1], numbers[o2]);
			}
		});
//		if (testNumber == 3 || testNumber == 6)// || testNumber == 9)
//			this.size = 11;
//		else
		this.size = 12;
		bitSize = (1 << (this.size - 1));
		partSize = this.size / 2;
		leftPartSize = (this.size + 1) / 2;
		double min = Double.POSITIVE_INFINITY;
		int[] stepEliminated = new int[2 * count - 1];
		Arrays.fill(stepEliminated, Integer.MAX_VALUE);
		for (int i = 0; i < count - 1; i++) {
			int remaining = count - i;
			double current = Math.abs(sum / remaining);
			if (Integer.bitCount(remaining) == 1) {
				if (current < min) {
					min = current;
					size = remaining;
				}
			}
			int index = count - i;
			int first = -1;
			int second = -1;
			if (remaining == 34) {// || remaining == 66) {
				sum = try2(count, sum, numbers, result, order, stepEliminated, i, index, first, second);
				i++;
				continue;
			}
//			if (remaining == 19) {
//				sum = try3(count, sum, numbers, result, order, stepEliminated, i, index, first, second);
//				i += 2;
//				continue;
//			}
			if (remaining == 12 && min > 0.075) {
				this.size = 12;
				bitSize = (1 << (this.size - 1));
				partSize = this.size / 2;
				leftPartSize = (this.size + 1) / 2;
			} else {
				this.size = 11;
				bitSize = (1 << (this.size - 1));
				partSize = this.size / 2;
				leftPartSize = (this.size + 1) / 2;
			}
			if (remaining == this.size) {
				sum -= meetInTheMiddle(i, stepEliminated, result, numbers);
				break;
			}
			if (remaining == 8) {
				sum = try4(count, sum, numbers, result, order, stepEliminated, i, index, first, second);
				i += 3;
				continue;
			}
			if (remaining - Integer.highestOneBit(remaining) >= 5 && Math.abs(current) < 100000) {
				for (int j = 0; j < i + count; j++) {
					if (stepEliminated[j] == Integer.MAX_VALUE) {
						if (first == -1)
							first = j;
						else {
							second = j;
							break;
						}
					}
				}
			} else {
				double delta = sum;
				delta *= 2;
				double bestValue = Double.POSITIVE_INFINITY;
				int curSecond = index - 1;
				for (int j = 0; j < index; j++) {
					int firstIndex = order[j];
					while (true) {
						int secondIndex = order[curSecond];
						double curValue = numbers[firstIndex] + numbers[secondIndex] - delta;
						if (Math.abs(curValue) < bestValue && firstIndex != secondIndex) {
							bestValue = Math.abs(curValue);
							first = firstIndex;
							second = secondIndex;
						}
						if (curSecond == 0 || curValue < 0)
							break;
						curSecond--;
					}
					if (curSecond <= j)
						break;
				}
			}
			sum = save(count, sum, numbers, result, stepEliminated, i, first, second, order);
		}
		if (Math.abs(sum) > min) {
			int[] notTaken = new int[size];
			int index = 0;
			for (int i = 0; i < 2 * count - size; i++) {
				if (stepEliminated[i] >= count - size)
					notTaken[index++] = i + 1;
			}
			size /= 2;
			for (int i = 0; i < size; i++)
				result[count - 2 * size + i] = Pair.makePair(notTaken[2 * i], notTaken[2 * i + 1]);
			size /= 2;
			while (size != 0) {
				for (int i = 0; i < size; i++) {
					result[count - 2 * size + i] = Pair.makePair(2 * count - 4 * size + 2 * i + 1,
						2 * count - 4 * size + 2 * i + 2);
				}
				size /= 2;
			}
		}
		for (Pair<Integer, Integer> pair : result) {
			out.printLine(pair.first, pair.second);
		}
	}

	private double save(int count, double sum, double[] numbers, Pair<Integer, Integer>[] result,
		int[] stepEliminated, int i, int first, int second, int[] order)
	{
		stepEliminated[first] = i;
		stepEliminated[second] = i;
		numbers[i + count] = (numbers[first] + numbers[second]) / 2;
		result[i] = Pair.makePair(first + 1, second + 1);
		sum -= numbers[count + i];
		int firstPosition = -1;
		int secondPosition = -1;
		for (int j = 0; j < count - i; j++) {
			if (order[j] == first) {
				firstPosition = j;
				break;
			}
		}
		if (firstPosition != count - i - 1) {
			order[firstPosition] = order[count - i - 1];
			adjust(order, numbers, firstPosition, count - i - 1);
		}
		for (int j = 0; j < count - i - 1; j++) {
			if (order[j] == second) {
				secondPosition = j;
				break;
			}
		}
		order[secondPosition] = count + i;
		adjust(order, numbers, secondPosition, count - i - 1);
		return sum;
	}

	private void adjust(int[] order, double[] numbers, int position, int count) {
		int value = order[position];
		while (position > 0 && numbers[order[position - 1]] > numbers[value]) {
			order[position] = order[position - 1];
			position--;
		}
		while (position < count - 1 && numbers[order[position + 1]] < numbers[value]) {
			order[position] = order[position + 1];
			position++;
		}
		order[position] = value;
	}

	private double try4(int count, double sum, double[] numbers, Pair<Integer, Integer>[] result,
		int[] order, int[] stepEliminated, int i, int index, int first, int second) {
		double best = Double.POSITIVE_INFINITY;
		int third = -1;
		int fourth = -1;
		int fifth = -1;
		int sixth = -1;
		int seventh = -1;
		int eighth = -1;
		for (int jj = 0; jj < index; jj++) {
			int j = order[jj];
			for (int kk = jj + 1; kk < index; kk++) {
				int k = order[kk];
				numbers[count + i] = (numbers[j] + numbers[k]) / 2;
				order[index] = count + i;
				for (int ll = 0; ll < index; ll++) {
					if (jj == ll || kk == ll)
						continue;
					int l = order[ll];
					for (int mm = ll + 1; mm <= index; mm++) {
						if (mm == jj || mm == kk)
							continue;
						int m = order[mm];
						numbers[count + i + 1] = (numbers[l] + numbers[m]) / 2;
						order[index + 1] = count + i + 1;
						for (int nn = 0; nn <= index; nn++) {
							if (nn == jj || nn == kk || nn == ll || nn == mm)
								continue;
							int n = order[nn];
							for (int oo = nn + 1; oo <= index + 1; oo++) {
								if (oo == jj || oo == kk || oo == ll || oo == mm)
									continue;
								int o = order[oo];
								numbers[count + i + 2] = (numbers[n] + numbers[o]) / 2;
								order[index + 2] = count + i + 2;
								for (int pp = 0; pp <= index + 1; pp++) {
									if (pp == jj || pp == kk || pp == ll || pp == mm || pp == nn || pp == oo)
										continue;
									int p = order[pp];
									for (int qq = pp + 1; qq <= index + 2; qq++) {
										if (qq == jj || qq == kk || qq == ll || qq == mm || qq == nn || qq == oo)
											continue;
										int q = order[qq];
										double curValue = Math.abs(sum - numbers[count + i] - numbers[count + i + 1] - numbers[count + i + 2] - (numbers[p] + numbers[q]) / 2);
										if (curValue < best) {
											best = curValue;
											first = j;
											second = k;
											third = l;
											fourth = m;
											fifth = n;
											sixth = o;
											seventh = p;
											eighth = q;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		sum = save(count, sum, numbers, result, stepEliminated, i, first, second, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 1, third, fourth, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 2, fifth, sixth, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 3, seventh, eighth, order);
		return sum;
	}

	private double try3(int count, double sum, double[] numbers, Pair<Integer, Integer>[] result,
		int[] order, int[] stepEliminated, int i, int index, int first, int second) {
		double best = Double.POSITIVE_INFINITY;
		int third = -1;
		int fourth = -1;
		int fifth = -1;
		int sixth = -1;
		for (int jj = 0; jj < index; jj++) {
			int j = order[jj];
			for (int kk = jj + 1; kk < index; kk++) {
				int k = order[kk];
				numbers[count + i] = (numbers[j] + numbers[k]) / 2;
				order[index] = count + i;
				for (int ll = 0; ll < index; ll++) {
					if (jj == ll || kk == ll)
						continue;
					int l = order[ll];
					for (int mm = ll + 1; mm <= index; mm++) {
						if (mm == jj || mm == kk)
							continue;
						if (mm != index && ll < jj)
							continue;
						int m = order[mm];
						numbers[count + i + 1] = (numbers[l] + numbers[m]) / 2;
						order[index + 1] = count + i + 1;
						for (int nn = 0; nn <= index; nn++) {
							if (nn == jj || nn == kk || nn == ll || nn == mm)
								continue;
							int n = order[nn];
							int start = index;
							if (nn > jj && nn > ll)
								start = nn + 1;
							for (int oo = start; oo <= index + 1; oo++) {
								if (oo == jj || oo == kk || oo == ll || oo == mm || oo == nn)
									continue;
								int o = order[oo];
								double curValue = Math.abs(sum - numbers[count + i] - numbers[count + i + 1] - (numbers[n] + numbers[o]) / 2);
								if (curValue < best) {
									best = curValue;
									first = j;
									second = k;
									third = l;
									fourth = m;
									fifth = n;
									sixth = o;
								}
							}
						}
					}
				}
			}
		}
		sum = save(count, sum, numbers, result, stepEliminated, i, first, second, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 1, third, fourth, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 2, fifth, sixth, order);
		return sum;
	}

	private double try2(int count, double sum, double[] numbers, Pair<Integer, Integer>[] result,
		int[] order, int[] stepEliminated, int i, int index, int first, int second) {
		double best = Double.POSITIVE_INFINITY;
		int third = -1;
		int fourth = -1;
		for (int jj = 0; jj < index; jj++) {
			int j = order[jj];
			for (int kk = jj + 1; kk < index; kk++) {
				int k = order[kk];
				numbers[count + i] = (numbers[j] + numbers[k]) / 2;
				order[index] = count + i;
				for (int ll = 0; ll < index; ll++) {
					if (jj == ll || kk == ll)
						continue;
					int l = order[ll];
					int from = index;
					if (ll > jj)
						from = ll + 1;
					for (int mm = from; mm <= index; mm++) {
						if (mm == jj || mm == kk)
							continue;
						int m = order[mm];
						double curValue = Math.abs(sum - numbers[count + i] - (numbers[l] + numbers[m]) / 2);
						if (curValue < best) {
							best = curValue;
							first = j;
							second = k;
							third = l;
							fourth = m;
						}
					}
				}
			}
		}
		sum = save(count, sum, numbers, result, stepEliminated, i, first, second, order);
		sum = save(count, sum, numbers, result, stepEliminated, i + 1, third, fourth, order);
		return sum;
	}

	private double meetInTheMiddle(int index, int[] stepEliminated, Pair<Integer, Integer>[] result, final double[] numbers) {
		int[] remaining = new int[size];
		int remainingIndex = 0;
		for (int i = 0; i <= index + result.length; i++) {
			if (stepEliminated[i] == Integer.MAX_VALUE)
				remaining[remainingIndex++] = i;
		}
		if (remainingIndex != size)
			throw new RuntimeException("remainingIndex");
		Collections.sort(Array.wrap(remaining), new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(numbers[o1], numbers[o2]);
			}
		});
		generate(numbers, Arrays.copyOfRange(remaining, 0, leftPartSize), valueLeft, lastLeft);
		generate(numbers, Arrays.copyOfRange(remaining, leftPartSize, size), valueRight, lastRight);
		double best = Double.POSITIVE_INFINITY;
		int leftRow = -1;
		int leftColumn = -1;
		int rightRow = -1;
		int rightColumn = -1;
		for (int i = 0; i <= bitSize; i++) {
			double[] left = valueLeft[leftPartSize][i];
			int j = bitSize - i;
			double[] right = valueRight[partSize][j];
			if (left.length == 0 || right.length == 0)
				continue;
//			Arrays.sort(orderLeft[i], leftComparator[i]);
//			Arrays.sort(orderRight[j], rightComparator[j]);
			int leftIndex = 0;
			int rightIndex = right.length - 1;
			while (leftIndex != count[leftPartSize][i] && rightIndex != -1) {
				double value = left[leftIndex] + right[rightIndex];
				if (Math.abs(value) < best) {
					best = Math.abs(value);
					leftRow = i;
					leftColumn = leftIndex;
					rightRow = j;
					rightColumn = rightIndex;
				}
				if (value < 0)
					leftIndex++;
				else
					rightIndex--;
			}
		}
		if (leftRow == -1)
			throw new RuntimeException("leftRow");
		int[] weights = new int[size];
		for (int i = leftPartSize; i > 0; i--) {
			int lastValue = lastLeft[i][leftRow][leftColumn];
			int nextRow = lastValue >> 20;
			weights[i - 1] = leftRow - nextRow;
			leftRow = nextRow;
			leftColumn = lastValue & ((1 << 20) - 1);
		}
		for (int i = partSize; i > 0; i--) {
			int lastValue = lastRight[i][rightRow][rightColumn];
			int nextRow = lastValue >> 20;
			weights[i + leftPartSize - 1] = rightRow - nextRow;
			rightRow = nextRow;
			rightColumn = lastValue & ((1 << 20) - 1);
		}
		double delta = 0;
		int next = result.length + index + 1;
		for (int i = 0; i < size - 1; i++) {
			boolean found = false;
			for (int j = 0; j < size && !found; j++) {
				if (weights[j] == -1)
					continue;
				for (int k = j + 1; k < size && !found; k++) {
					if (weights[j] == weights[k]) {
						result[index + i] = Pair.makePair(remaining[j] + 1, remaining[k] + 1);
						delta += numbers[next] = (numbers[remaining[j]] + numbers[remaining[k]]) / 2;
						stepEliminated[remaining[j]] = index + i;
						stepEliminated[remaining[k]] = index + i;
						remaining[j] = next++;
						remaining[k] = -1;
						weights[j] *= 2;
						weights[k] = -1;
						found = true;
					}
				}
			}
			if (!found)
				throw new RuntimeException("Not found");
		}
		return delta;
	}

	private int[] next = new int[size];
	private double[] curValues = new double[size];

	private void generate(double[] numbers, int[] indices, double[][][] value, int[][][] last) {
		for (int i = 1; i <= indices.length; i++) {
			double currentNumber = numbers[indices[i - 1]];
			for (int j = 0; j <= bitSize; j++) {
				if (i == indices.length && valueLeft[size - indices.length][bitSize - j].length == 0)
					continue;
				Arrays.fill(next, 0);
				int upTo = 0;
				for (int k = 0; (1 << k) <= j; k++) {
					if (value[i - 1][j - (1 << k)].length == 0)
						curValues[k] = Double.POSITIVE_INFINITY;
					else
						curValues[k] = value[i - 1][j - (1 << k)][0] + (1 << k) * currentNumber;
					upTo++;
				}
				double[] writeTo = value[i][j];
				int[] curLast = last[i][j];
				int length = value[i][j].length;
				int start = 0;
				for (int k = 0; k < length; k++) {
					double best = curValues[start];
					int index = start;
					for (int l = start + 1; l < upTo; l++) {
						if (curValues[l] < best) {
							best = curValues[l];
							index = l;
						}
					}
					writeTo[k] = best;
					curLast[k] = ((j - (1 << index)) << 20) + (next[index]++);
					double[] doubles = value[i - 1][j - (1 << index)];
					if (next[index] == doubles.length) {
						curValues[index] = Double.POSITIVE_INFINITY;
						if (index == start) {
							while (start < upTo && next[start] == value[i - 1][j - (1 << start)].length)
								start++;
						}
					} else
						curValues[index] = doubles[next[index]] + (1 << index) * currentNumber;
				}
			}
		}
	}
}
