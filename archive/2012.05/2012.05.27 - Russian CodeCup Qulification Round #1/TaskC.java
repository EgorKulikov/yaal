package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
	BigInteger[] twos = new BigInteger[30000];
	BigInteger[] threes = new BigInteger[30000];

	{
		twos[0] = BigInteger.ONE;
		threes[0] = BigInteger.ONE;
		BigInteger two = BigInteger.valueOf(2);
		BigInteger three = BigInteger.valueOf(3);
		for (int i = 1; i < 30000; i++) {
			twos[i] = twos[i - 1].multiply(two);
			threes[i] = threes[i - 1].multiply(three);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int[] twos = new int[count + 1];
		int[] threes = new int[count + 1];
		int[] last = new int[count + 1];
		for (int i = 0; i < count; i++) {
			for (int j = i; j >= i - 3 && j >= 0; j--) {
				boolean positive = false;
				boolean negative = false;
				for (int k = j; k < i; k++) {
					if (heights[k] < heights[k + 1])
						negative = true;
					else if (heights[k] > heights[k + 1])
						positive = true;
				}
				if (negative && positive)
					break;
				int curTwos = twos[j];
				int curThrees = threes[j];
				if (i - j == 1)
					curTwos++;
				else if (i - j == 2)
					curThrees++;
				else if (i - j == 3)
					curTwos += 2;
				int difTwos = curTwos - twos[i + 1];
				int difThrees = curThrees - threes[i + 1];
				if (positive(difTwos, difThrees)) {
					twos[i + 1] = curTwos;
					threes[i + 1] = curThrees;
					last[i + 1] = j;
				}
			}
		}
		List<Integer> answer = new ArrayList<Integer>();
		int current = count;
		while (current != 0) {
			answer.add(current);
			current = last[current];
		}
		Collections.reverse(answer);
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}

	private boolean positive(int difTwos, int difThrees) {
		if (difThrees >= 0 && difTwos >= 0)
			return true;
		if (difThrees <= 0 && difTwos <= 0)
			return false;
		if (difTwos > 0)
			return twos[difTwos].compareTo(threes[-difThrees]) > 0;
		return twos[-difTwos].compareTo(threes[difThrees]) < 0;
	}
}
