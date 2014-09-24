package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.DoubleUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
	double[] winSkipOnes = new double[1025];
	double[] winSkipTwos = new double[1025];
	double[] removeSkipOnes = new double[2049];
	double[] removeSkipTwos = new double[2049];
	double probability;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int probability = in.readInt();
		this.probability = probability / 100d;
		int[] stack = IOUtils.readIntArray(in, count);
		Arrays.fill(winSkipOnes, -1);
		Arrays.fill(winSkipTwos, -1);
		Arrays.fill(removeSkipOnes, -1);
		Arrays.fill(removeSkipTwos, -1);
		double[] result = new double[count + 1];
		double answer = Double.POSITIVE_INFINITY;
		Arrays.fill(result, Double.POSITIVE_INFINITY);
		result[count] = 0;
		for (int i = count; i > 0; i--) {
			int current = stack[i - 1];
			boolean good = true;
			for (int j = i - 1; j > 0; j--) {
				result[j] = Math.min(result[j], Math.min(removeSkipOnes(current), removeSkipTwos(current)) + result[i]);
				if (current > stack[j - 1]) {
					good = false;
					break;
				}
				current += stack[j - 1];
			}
			if (good) {
				answer = Math.min(answer, Math.min(winSkipOnes(current), winSkipTwos(current)) + result[i]);
				result[0] = Math.min(result[0], Math.min(removeSkipOnes(current), removeSkipTwos(current)) + result[i]);
			}
		}
		answer = Math.min(answer, Math.min(winSkipOnes(0), winSkipTwos(0)) + result[0]);
		if (Double.isInfinite(answer) || Double.isNaN(answer)) {
			throw new RuntimeException();
		}
		out.printLine(answer);
	}

	private double removeSkipTwos(int current) {
		if (current >= removeSkipTwos.length) {
			return Double.POSITIVE_INFINITY;
		}
		if (removeSkipTwos[current] != -1) {
			return removeSkipTwos[current];
		}
		if ((current & 1) == 0) {
			if (probability != 1) {
				removeSkipTwos[current] = probability * removeSkipTwos(current + 1) + (1 - probability) * removeSkipTwos(current + 2);
			} else {
				removeSkipTwos[current] = removeSkipTwos(current + 1);
			}
		} else {
			removeSkipTwos[current] = 1 / probability - 1 + removeSkipTwos(current + 1);
		}
		if ((current & (current - 1)) == 0) {
			removeSkipTwos[current] = Math.min(removeSkipTwos[current], 1);
		}
		return removeSkipTwos[current];
	}

	private double winSkipTwos(int current) {
		if (current >= winSkipTwos.length) {
			return Double.POSITIVE_INFINITY;
		}
		if (winSkipTwos[current] != -1) {
			return winSkipTwos[current];
		}
		if ((current & 1) == 0) {
			if (probability != 1) {
				winSkipTwos[current] = probability * winSkipTwos(current + 1) + (1 - probability) * winSkipTwos(current + 2);
			} else {
				winSkipTwos[current] = winSkipTwos(current + 1);
			}
		} else {
			winSkipTwos[current] = 1 / probability - 1 + winSkipTwos(current + 1);
		}
		if (current == 1024) {
			winSkipTwos[current] = Math.min(winSkipTwos[current], 0);
		}
		return winSkipTwos[current];
	}

	private double removeSkipOnes(int current) {
		if (current >= removeSkipOnes.length || probability == 1) {
			return Double.POSITIVE_INFINITY;
		}
		if (removeSkipOnes[current] != -1) {
			return removeSkipOnes[current];
		}
		if ((current & 1) == 0) {
			removeSkipOnes[current] = 1 / (1 - probability) - 1 + removeSkipOnes(current + 2);
		} else {
			removeSkipOnes[current] = 1 + removeSkipOnes(current - 1);
		}
		if ((current & (current - 1)) == 0) {
			removeSkipOnes[current] = Math.min(removeSkipOnes[current], 1);
		}
		return removeSkipOnes[current];
	}

	private double winSkipOnes(int current) {
		if (current >= winSkipOnes.length || probability == 1) {
			return Double.POSITIVE_INFINITY;
		}
		if (winSkipOnes[current] != -1) {
			return winSkipOnes[current];
		}
		if ((current & 1) == 0) {
			winSkipOnes[current] = 1 / (1 - probability) - 1 + winSkipOnes(current + 2);
		} else {
			winSkipOnes[current] = 1 + winSkipOnes(current - 1);
		}
		if (current == 1024) {
			winSkipOnes[current] = Math.min(winSkipOnes[current], 0);
		}
		return winSkipOnes[current];
	}
}
