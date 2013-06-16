package on2013_06.on2013_06_15_Google_Code_Jam_Round_3_2013.D___Observation_Wheel;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	double[][] answer = new double[21][];

	{
		for (int i = 0; i <= 20; i++)
			answer[i] = new double[1 << i];
		ArrayUtils.fill(answer, -1);
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] description = in.readString().toCharArray();
		int mask = 0;
		for (int i = 0; i < description.length; i++) {
			if (description[i] == 'X')
				mask += 1 << i;
		}
		out.printLine("Case #" + testNumber + ":", calculate(description.length, mask));
    }

	private double calculate(int count, int mask) {
		if (Integer.bitCount(mask) == count)
			return 0;
		while ((mask & (1 << (count - 1))) != 0)
			mask = (mask << 1) + 1 - (1 << count);
		if (answer[count][mask] != -1)
			return answer[count][mask];
		answer[count][mask] = 0;
		for (int i = 0; i < count; i++) {
			if ((mask >> i & 1) == 0) {
				double total = count;
				int multiplier = 1;
				for (int j = i - 1; j >= 0; j--) {
					if ((mask >> j & 1) == 0)
						break;
					total += count - multiplier;
					multiplier++;
				}
				answer[count][mask] += multiplier * calculate(count, mask + (1 << i)) + total;
			}
		}
		return answer[count][mask] /= count;
	}
}
