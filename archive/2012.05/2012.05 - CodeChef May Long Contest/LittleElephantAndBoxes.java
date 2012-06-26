package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;
import java.util.Comparator;

public class LittleElephantAndBoxes {
	long[][] c = IntegerUtils.generateBinomialCoefficients(16);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int boxCount = in.readInt();
		int giftCount = in.readInt();
		int[] money = new int[boxCount];
		int[] probability = new int[boxCount];
		IOUtils.readIntArrays(in, money, probability);
		int[] cost = new int[giftCount];
		int[] diamonds = new int[giftCount];
		IOUtils.readIntArrays(in, cost, diamonds);
		int firstSize = boxCount / 2;
		int firstVariants = 1 << firstSize;
		int secondSize = boxCount - firstSize;
		int secondVariants = 1 << secondSize;
		int[] firstMoney = new int[firstVariants];
		double[] firstProbability = new double[firstVariants];
		int[] secondMoney = new int[secondVariants];
		double[] secondProbability = new double[secondVariants];
		countBoxes(money, probability, 0, firstSize, firstMoney, firstProbability);
		countBoxes(money, probability, firstSize, boxCount, secondMoney, secondProbability);
		int[][] firstGroup = groupByDiamonds(firstSize, firstMoney, 1);
		int[][] secondGroup = groupByDiamonds(secondSize, secondMoney, -1);
		int[][] thresholds = processGifts(giftCount, boxCount, cost, diamonds);
		double answer = 0;
		for (int i = 0; i <= firstSize; i++) {
			for (int j = 0; j <= secondSize; j++) {
				int[] position = new int[giftCount + 1];
				double[] sumProbabilities = new double[giftCount + 1];
				for (int k : firstGroup[i]) {
					for (int l = 1; l <= giftCount; l++) {
						while (position[l] != secondGroup[j].length && firstMoney[k] + secondMoney[secondGroup[j][position[l]]] >= thresholds[i + j][l])
							sumProbabilities[l] += secondProbability[secondGroup[j][position[l]++]];
						answer += sumProbabilities[l] * firstProbability[k];
					}
				}
			}
		}
		out.printFormat("%.4f\n", answer);
	}

	private int[][] processGifts(int giftCount, int boxCount, int[] cost, int[] diamonds) {
		int[][] result = new int[boxCount + 1][giftCount + 1];
		ArrayUtils.fill(result, Integer.MAX_VALUE);
		result[0][0] = 0;
		for (int i = 0; i < giftCount; i++) {
			for (int j = boxCount; j >= 0; j--) {
				for (int k = giftCount; k >= 0; k--) {
					if (result[j][k] == Integer.MAX_VALUE || j + diamonds[i] > boxCount)
						continue;
					result[j + diamonds[i]][k + 1] = Math.min(result[j + diamonds[i]][k + 1], result[j][k] + cost[i]);
				}
			}
		}
		for (int i = 0; i <= giftCount; i++) {
			for (int j = 1; j <= boxCount; j++)
				result[j][i] = Math.min(result[j][i], result[j - 1][i]);
		}
		return result;
	}

	private int[][] groupByDiamonds(int size, final int[] money, final int reverse) {
		int[][] result = new int[size + 1][];
		for (int i = 0; i <= size; i++)
			result[i] = new int[(int) c[size][i]];
		int[] count = new int[size + 1];
		for (int i = 0; i < money.length; i++) {
			int index = size - Integer.bitCount(i);
			result[index][count[index]++] = i;
		}
		for (int i = 0; i <= size; i++) {
			Collections.sort(Array.wrap(result[i]), new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return (money[o1] - money[o2]) * reverse;
				}
			});
		}
		return result;
	}

	private void countBoxes(int[] money, int[] probability, int from, int to, int[] firstMoney, double[] firstProbability) {
		for (int i = 0; i < firstMoney.length; i++) {
			firstProbability[i] = 1;
			for (int j = 0; j < to - from; j++) {
				if ((i >> j & 1) != 0) {
					firstMoney[i] += money[j + from];
					firstProbability[i] *= probability[j + from] / 100d;
				} else
					firstProbability[i] *= (100 - probability[j + from]) / 100d;
			}
		}
	}
}
