package on2013_09.on2013_09_18_Codeforces_Trainings_Season_1_Episode_2.TaskC;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int[] at = new int[1000001];
	int[] position = new int[1000001];

	{
		int[] next = ArrayUtils.createOrder(1000001);
		boolean[] occupied = new boolean[1000001];
		occupied[1] = occupied[2] = true;
		at[1] = 1;
		at[2] = 2;
		position[1] = 1;
		position[2] = 2;
		int remaining = 300000 - 2;
		next[2] = 4;
		int[] divisor = IntegerUtils.generateDivisorTable(1000001);
		for (int i = 3; remaining > 0; i++) {
			int last = at[i - 1];
			int candidate = Integer.MAX_VALUE;
			while (last != 1) {
				int div = divisor[last];
				candidate = Math.min(candidate, next[div]);
				do {
					last /= div;
				} while (last % div == 0);
			}
			at[i] = candidate;
			position[candidate] = i;
			occupied[candidate] = true;
			if (candidate <= 300000)
				remaining--;
			while (candidate != 1) {
				int div = divisor[candidate];
				while (next[div] <= 1000000 && occupied[next[div]])
					next[div] += div;
				do {
					candidate /= div;
				} while (candidate % div == 0);
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number == 0)
			throw new UnknownError();
		out.printLine("The number", number, "appears in location", position[number] + ".");
    }
}
