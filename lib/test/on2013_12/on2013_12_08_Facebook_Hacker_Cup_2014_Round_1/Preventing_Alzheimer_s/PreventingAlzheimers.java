package on2013_12.on2013_12_08_Facebook_Hacker_Cup_2014_Round_1.Preventing_Alzheimer_s;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class PreventingAlzheimers {
	int[] base = IntegerUtils.generatePrimes(50);
	int[] additional = Arrays.copyOfRange(IntegerUtils.generatePrimes(150), 15, 35);
	int[] mask = new int[150];

	{
		for (int i = 0; i < 15; i++) {
			for (int j = base[i]; j < 150; j += base[i])
				mask[j] |= 1 << i;
		}
		for (int i : additional) {
			for (int j = i; j < 150; j += i)
				mask[j] = 0;
		}
	}

	int[] numbers;
	int[][][] result;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println(testNumber);
		int count = in.readInt();
		int divisor = in.readInt();
		numbers = IOUtils.readIntArray(in, count);
		int baseSum = (int) ArrayUtils.sumArray(numbers);
		Arrays.sort(numbers);
		for (int i = 0; i < count; i++) {
			numbers[i] = (numbers[i] + divisor - 1) / divisor;
			if (i != 0)
				numbers[i] = Math.max(numbers[i], 1);
			if (numbers[i] > 1)
				numbers[0] = Math.max(numbers[0], 1);
		}
		result = new int[count][count][1 << 15];
		ArrayUtils.fill(result, -1);
		int answer = go(0, 0, 0) * divisor - baseSum;
		out.printLine("Case #" + testNumber + ":", answer);
    }

	private int go(int step, int nextPrime, int curMask) {
		if (step == numbers.length)
			return 0;
		if (result[step][nextPrime][curMask] != -1)
			return result[step][nextPrime][curMask];
		if (numbers[step] <= 1)
			return result[step][nextPrime][curMask] = numbers[step] + go(step + 1, nextPrime, curMask);
		result[step][nextPrime][curMask] = go(step + 1, nextPrime + 1, curMask) + additional[nextPrime];
		for (int i = numbers[step]; i < additional[nextPrime]; i++) {
			if (mask[i] != 0 && (mask[i] & curMask) == 0)
				result[step][nextPrime][curMask] = Math.min(result[step][nextPrime][curMask], go(step + 1, nextPrime, curMask | mask[i]) + i);
		}
		return result[step][nextPrime][curMask];
	}
}
