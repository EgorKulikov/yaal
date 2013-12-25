package on2013_01.on2013_01_13_Codeforces_Round__160.B___Maxim_and_Restaurant;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	int[] perSize;
	long[][] c = IntegerUtils.generateBinomialCoefficients(51);
	double[] f;
	double[][][] result;
	int count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		int[] sizes = IOUtils.readIntArray(in, count);
		int length = in.readInt();
		if (ArrayUtils.sumArray(sizes) <= length) {
			out.printLine(count);
			return;
		}
		f = new double[count + 1];
		f[0] = 1;
		for (int i = 1; i <= count; i++)
			f[i] = f[i - 1] * i;
		perSize = new int[51];
		for (int i : sizes)
			perSize[i]++;
		double answer = 0;
		result = new double[length + 1][52][count];
		for (int i = 1; i <= 50; i++) {
			if (perSize[i] == 0)
				continue;
			perSize[i]--;
			ArrayUtils.fill(result, -1);
			answer += (perSize[i] + 1) * go(length, 1, count - 1, i);
			perSize[i]++;
		}
		for (int i = 1; i <= count; i++)
			answer /= i;
		out.printLine(answer);
	}

	private double go(int remainingLength, int currentLength, int remainingGuests, int targetLength) {
		if (result[remainingLength][currentLength][remainingGuests] != -1)
			return result[remainingLength][currentLength][remainingGuests];
		result[remainingLength][currentLength][remainingGuests] = 0;
		if (currentLength == 51) {
			if (remainingLength < targetLength)
				result[remainingLength][currentLength][remainingGuests] += f[remainingGuests] * f[count - remainingGuests - 1] * (count - remainingGuests - 1);
			return result[remainingLength][currentLength][remainingGuests];
		}
		for (int i = 0; i * currentLength <= remainingLength && i <= perSize[currentLength]; i++)
			result[remainingLength][currentLength][remainingGuests] += go(remainingLength - i * currentLength, currentLength + 1, remainingGuests - i, targetLength) * c[perSize[currentLength]][i];
		return result[remainingLength][currentLength][remainingGuests];
	}
}
