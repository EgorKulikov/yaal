package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class AdditionChains {
	int resultSize;
	int[] resultFirst = new int[1000];
	int[] resultSecond = new int[1000];
	int candidateSize;
	int[] candidateFirst = new int[1000];
	int[] candidateSecond = new int[1000];
	int[] indexByRemainder = new int[64];
	int[] mod = new int[1000];
	int[] remainder = new int[1000];
	int[] indices = new int[100];
	int[] oldValues = new int[100];
	SmallValues smallValues = new SmallValues();
	static final int threshold = 512;
	int id;
	int[] mark = new int[threshold];
	int[] indexByValue = new int[threshold];
	int[] tempValue = new int[threshold];
	static final int thresholdLog = 9;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		init();
        BigInteger target = in.readBigInteger();
        special(target);
        for (int i = 3; i <= 63; i += 2) {
            solution(target, i);
			applyCandidate();
		}
		for (int i = 3; i <= 5; i++) {
			mixed(target, i);
			applyCandidate();
		}
		int bitLength = (int) (target.bitLength() / 5.2);
		bitLength -= bitLength % 8;
		byte[] mag = new byte[bitLength / 8];
		if (bitLength >= 3) {
			for (int i = 1; i < bitLength; i++) {
				for (int j = i + 1; j < bitLength; j++) {
					for (int k = Math.min(j + 1, bitLength); k <= bitLength; k++) {
						mag[i >> 3] += 1 << (7 - (i & 7));
						if (j != bitLength)
							mag[j >> 3] += 1 << (7 - (j & 7));
						if (k != bitLength)
							mag[k >> 3] += 1 << (7 - (k & 7));
						BigInteger divider = new BigInteger(mag);
						BigInteger[] divAndRemainder = target.divideAndRemainder(divider);
						for (int shift = 5; shift <= 5; shift++) {
							int guessedLength = estimate(divAndRemainder[0], shift) + estimate(divAndRemainder[1], shift) + (j == bitLength ? 0 : 1) + (k == bitLength ? 0 : 1) + (1 << (shift - 1)) - 1;
							if (guessedLength - 3 >= resultSize)
								continue;
							dividerAlgo(target, divider, shift, divAndRemainder);
							applyCandidate();
						}
						mag[i >> 3] = 0;
						if (j != bitLength)
							mag[j >> 3] = 0;
						if (k != bitLength)
							mag[k >> 3] = 0;
					}
				}
			}
		}
        out.printLine(resultSize);
        for (int i = 0; i < resultSize; i++)
            out.printLine(resultFirst[i], resultSecond[i]);
	}

	private void init() {
		Arrays.fill(indexByRemainder, -1);
		indexByRemainder[1] = 0;
		indexByRemainder[2] = 1;
		for (int i = 3; i < 64; i += 2)
			indexByRemainder[i] = i / 2 + 1;
	}

	private void applyCandidate() {
		if (resultSize > candidateSize) {
			resultSize = candidateSize;
			System.arraycopy(candidateFirst, 0, resultFirst, 0, resultSize);
			System.arraycopy(candidateSecond, 0, resultSecond, 0, resultSize);
		}
	}


	private void solution(BigInteger target, int max) {
		id++;
		candidateSize = 0;
        int bitLength = target.bitLength();
		add(0, 0);
		if (target.equals(BigInteger.ONE.add(BigInteger.ONE)))
			return;
        for (int i = 3; i <= max; i += 2) {
			add(indexByRemainder[i - 2], 1);
            if (BigInteger.valueOf(i).add(BigInteger.ONE).compareTo(target) >= 0) {
				add(candidateSize, 0);
				return;
			}
        }
//		int shift = Integer.bitCount(Integer.highestOneBit(max) - 1) + 1;
        int start = bitLength - 2;
        int index = 0;
        int curValue = 1;
		boolean more = false;
        while (start != -1) {
            if (!target.testBit(start)) {
				int oldValue = curValue;
				if (!more)
					curValue += curValue;
                if (curValue >= threshold) {
					if (!more) {
						more = true;
						initialize(oldValue, max);
						index = candidateSize;
					}
					add(index, index);
					index = candidateSize;
                }
                start--;
            } else {
                int curShift = Math.min(thresholdLog, start + 1);
                int remainder = 0;
                for (int i = 0; i < curShift; i++) {
                    if (target.testBit(start - i))
                        remainder += 1 << (curShift - i - 1);
                }
				if (remainder == 0)
					throw new RuntimeException();
				while (remainder > max && (remainder >= threshold || mark[remainder] != id)) {
					remainder >>= 1;
					curShift--;
				}
				if (mark[remainder] != id) {
					while ((remainder & 1) == 0) {
						remainder >>= 1;
						curShift--;
					}
				}
				for (int i = 0; i < curShift; i++) {
					int oldValue = curValue;
					if (!more)
						curValue += curValue;
					if (curValue >= threshold) {
						if (!more) {
							more = true;
							initialize(oldValue, max);
							index = candidateSize;
						}
						add(index, index);
						index = candidateSize;
					}
				}
				int oldValue = curValue;
				if (!more)
					curValue += remainder;
                if (curValue >= threshold) {
					if (!more) {
						more = true;
						initialize(oldValue, max);
						index = candidateSize;
					}
					if (mark[remainder] == id)
						add(index, indexByValue[remainder]);
					else
						add(index, indexByRemainder[remainder]);
					index = candidateSize;
                }
                start -= curShift;
            }
        }
		if (!more)
			initialize(curValue, max);
    }

	private void initialize(int value, int max) {
		int length = smallValues.bestFirst[max][value].length;
		System.arraycopy(smallValues.bestFirst[max][value], 0, candidateFirst, candidateSize, length);
		System.arraycopy(smallValues.bestSecond[max][value], 0, candidateSecond, candidateSize, length);
		candidateSize += length;
		tempValue[0] = 1;
		for (int i = 0; i < candidateSize; i++) {
			tempValue[i + 1] = tempValue[candidateFirst[i]] + tempValue[candidateSecond[i]];
			mark[tempValue[i + 1]] = id;
			indexByValue[tempValue[i + 1]] = i + 1;
		}
	}

	private void add(int first, int second) {
		candidateFirst[candidateSize] = first;
		candidateSecond[candidateSize++] = second;
	}

	private void dividerAlgo(BigInteger target, BigInteger divider, int shift, BigInteger[] divAndRemainder) {
		solution(divAndRemainder[0], (1 << shift) - 1);
		target = divAndRemainder[1];
		int bitLength = divider.bitLength();
		int index = candidateSize;
		int start = index;
		if (target.testBit(bitLength - 1)) {
			add(index, 0);
			index = candidateSize;
		}
		for (int i = bitLength - 1; i >= 0; i--) {
			if (!target.testBit(i)) {
				if (i != bitLength - 1) {
					add(index, index);
					index = candidateSize;
					if (divider.testBit(i)) {
						add(index, start);
						index = candidateSize;
					}
				}
				continue;
			}
			int curShift = Math.min(shift, i + 1);
			int remainder = 0;
			for (int j = 0; j < curShift; j++) {
				remainder <<= 1;
				if (target.testBit(i - j))
					remainder++;
			}
			while ((remainder & 1) == 0) {
				remainder >>= 1;
				curShift--;
			}
			for (int j = 0; j < curShift; j++) {
				if (i - j != bitLength - 1) {
					add(index, index);
					index = candidateSize;
					if (divider.testBit(i - j)) {
						add(index, start);
						index = candidateSize;
					}
				}
			}
			add(index, indexByRemainder[remainder]);
			index = candidateSize;
			i -= curShift - 1;
		}
	}

	private int estimate(BigInteger target, int shift) {
		int bitCount = target.bitLength();
		int result = 0;
		for (int i = bitCount - 2; i >= 0; i--) {
			if (target.testBit(i)) {
				result += Math.min(i + 1, shift) + 1;
				i -= shift - 1;
				continue;
			}
			result++;
		}
		return result;
	}

	private void special(BigInteger target) {
		resultSize = 0;
		int index = 0;
		int bitLength = target.bitLength();
		for (int i = bitLength - 2; i >= 0; i--) {
			resultFirst[resultSize] = resultSecond[resultSize] = index;
			index = ++resultSize;
			if (target.testBit(i)) {
				resultFirst[resultSize] = index;
				resultSecond[resultSize] = 0;
				index = ++resultSize;
			}
		}
	}

	private int remainderCost(int mod, int remainder) {
		return Integer.bitCount(Integer.highestOneBit(mod) - 1) + Integer.bitCount(mod) - 1 + (remainder != 0 ? (remainder > 2 && remainder % 2 == 0 ? 2 : 1) : 0);
	}


	private void mixed(BigInteger target, int shift) {
		candidateSize = 0;
		int size = 0;
		while (!target.equals(BigInteger.ONE)) {
			if (!target.testBit(0)) {
				mod[size] = 2;
				remainder[size++] = 0;
				target = target.shiftRight(1);
				continue;
			}
			int curMod = BigInteger.valueOf(1 << shift).compareTo(target) <= 0 ? (1 << shift) : target.intValue();
			BigInteger[] divisorAndRemainder = target.divideAndRemainder(BigInteger.valueOf(curMod));
			int curRemainder = divisorAndRemainder[1].intValue();
			int curEstimate = estimate(divisorAndRemainder[0], shift) + remainderCost(curMod, curRemainder);
			BigInteger nextTarget = divisorAndRemainder[0];
			for (int i = curMod - 1; i >= 2; i--) {
				divisorAndRemainder = target.divideAndRemainder(BigInteger.valueOf(i));
				int candidateRemainder = divisorAndRemainder[1].intValue();
				int candidateEstimate = estimate(divisorAndRemainder[0], shift) + remainderCost(i, candidateRemainder);
				if (candidateEstimate < curEstimate) {
					curEstimate = candidateEstimate;
					curMod = i;
					curRemainder = candidateRemainder;
					nextTarget = divisorAndRemainder[0];
				}
			}
			mod[size] = curMod;
			remainder[size++] = curRemainder;
			target = nextTarget;
		}
		int maxRemainder = 1;
		for (int i = 0; i < size; i++)
			maxRemainder = Math.max(maxRemainder, remainder[i]);
		if (maxRemainder >= 2)
			add(0, 0);
		for (int i = 3; i <= maxRemainder; i += 2)
			add(indexByRemainder[i - 2], indexByRemainder[2]);
		if (maxRemainder > 2 && maxRemainder % 2 == 0)
			maxRemainder--;
		int index = 0;
		int curValue = 1;
		boolean more = false;
		for (int i = size - 1; i >= 0; i--) {
			int curMod = mod[i];
			int curRemainder = remainder[i];
			for (int j = 0; (1 << (j + 1)) <= curMod; j++) {
				indices[j] = index;
				int oldValue = curValue;
				if (!more) {
					oldValues[j] = curValue;
					curValue += curValue;
				} else
					oldValues[j] = Integer.MAX_VALUE;
				if (curValue >= threshold) {
					if (!more) {
						more = true;
						initialize(oldValue, maxRemainder);
						index = candidateSize;
					}
					add(index, index);
					index = candidateSize;
				}
			}
			for (int j = 0; (1 << (j + 1)) <= curMod; j++) {
				if ((curMod >> j & 1) != 0) {
					int oldValue = curValue;
					if (!more)
						curValue += oldValues[j];
					if (curValue >= threshold) {
						if (!more) {
							more = true;
							initialize(oldValue, maxRemainder);
							index = candidateSize;
						}
						if (oldValues[j] > maxRemainder) {
							add(index, indices[j]);
							index = candidateSize;
						} else {
							int curTarget = oldValues[j];
							if (curTarget % 2 == 1 || curTarget == 2)
								add(index, indexByRemainder[curTarget]);
							else {
								add(index, indexByRemainder[curTarget - 1]);
								index = candidateSize;
								add(index, 0);
							}
							index = candidateSize;
						}
					}
				}
			}
			if (curRemainder != 0) {
				int oldValue = curValue;
				if (!more)
					curValue += curRemainder;
				if (curValue >= threshold) {
					if (!more) {
						more = true;
						initialize(oldValue, maxRemainder);
						index = candidateSize;
					}
					if (curRemainder % 2 == 1 || curRemainder == 2)
						add(index, indexByRemainder[curRemainder]);
					else {
						add(index, indexByRemainder[curRemainder - 1]);
						index = candidateSize;
						add(index, 0);
					}
					index = candidateSize;
				}
			}
		}
		if (!more)
			initialize(curValue, maxRemainder);
	}

	static class SmallValues {
		int[] value;
		int[] first;
		int[] second;
		int[][][] bestFirst;
		int[][][] bestSecond;

		{
			value = new int[100];
			first = new int[100];
			second = new int[100];
			bestFirst = new int[64][threshold][];
			bestSecond = new int[64][threshold][];
			value[0] = 1;
			go(1, 1, 0, 1);
			value[1] = 2;
			go(2, 2, 0, 2);
			for (int i = 3; i < 64; i += 2) {
				value[i / 2 + 1] = i;
				go(i, i / 2 + 2, 0, i);
			}
		}

		private void go(int index, int valueIndex, int moveIndex, int curValue) {
			if (curValue >= threshold)
				return;
			if (bestFirst[index][curValue] != null && bestFirst[index][curValue].length <= moveIndex)
				return;
			bestFirst[index][curValue] = Arrays.copyOf(first, moveIndex);
			bestSecond[index][curValue] = Arrays.copyOf(second, moveIndex);
			for (int i = valueIndex - 3; i >= 0; i--) {
				value[valueIndex] = value[valueIndex - 1] + value[i];
				first[moveIndex] = valueIndex - 1;
				second[moveIndex] = i;
				go(index, valueIndex + 1, moveIndex + 1, value[valueIndex]);
			}
			for (int i = valueIndex - 1; i >= 0 && i >= valueIndex - 2; i--) {
				value[valueIndex] = value[valueIndex - 1] + value[i];
				first[moveIndex] = valueIndex - 1;
				second[moveIndex] = i;
				go(index, valueIndex + 1, moveIndex + 1, value[valueIndex]);
			}
		}
	}
}
