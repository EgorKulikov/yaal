package on2012_02.on2012_1_25.taskb;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] primes = IntegerUtils.generatePrimes(count + 1);
		int[] divisor = IntegerUtils.generateDivisorTable(count + 1);
		int[][] allDivisors = new int[count][];
		for (int i = 0; i < count; i++) {
			int j = i + 1;
			int curCount = 0;
			while (j != 1) {
				int curDivisor = divisor[j];
				while (j % curDivisor == 0)
					j /= curDivisor;
				curCount++;
			}
			allDivisors[i] = new int[curCount];
			j = i + 1;
			int index = 0;
			while (j != 1) {
				int curDivisor = divisor[j];
				while (j % curDivisor == 0)
					j /= curDivisor;
				allDivisors[i][index++] = Arrays.binarySearch(primes, curDivisor);
			}
		}
		int[] curOn = new int[primes.length];
		int queryCount = in.readInt();
		boolean[] isOn = new boolean[count + 1];
		for (int i = 0; i < queryCount; i++) {
			char type = in.readCharacter();
			int index = in.readInt();
			if (type == '+') {
				if (isOn[index])
					out.printLine("Already on");
				else {
					boolean good = true;
					for (int j : allDivisors[index - 1]) {
						if (curOn[j] != 0) {
							out.printLine("Conflict with", curOn[j]);
							good = false;
							break;
						}
					}
					if (good) {
						out.printLine("Success");
						isOn[index] = true;
						for (int j : allDivisors[index - 1]) {
							curOn[j] = index;
						}
					}
				}
			} else {
				if (!isOn[index])
					out.printLine("Already off");
				else {
					out.printLine("Success");
					isOn[index] = false;
					for (int j : allDivisors[index - 1]) {
						curOn[j] = 0;
					}
				}
			}
		}
	}
}
