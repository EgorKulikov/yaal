package on2014_09.on2014_09_21_September_Cook_Off_2014__ACM_ICPC_Warm_up.Subarray_LCM;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SubarrayLCM {
	int[] last = new int[(int) (1e6 + 1)];
	int[] divisor = IntegerUtils.generateDivisorTable((int) (1e6 + 1));
	int[] used = new int[last.length];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = -1;
		int start = 0;
		for (int i = 0; i < count; i++) {
			int value = in.readInt();
			while (value != 1) {
				int next = divisor[value];
				do {
					value /= next;
				} while (value % next == 0);
				if (used[next] == testNumber) {
					start = Math.max(start, last[next] + 1);
				}
				used[next] = testNumber;
				last[next] = i;
			}
			if (start < i) {
				answer = Math.max(answer, i - start + 1);
			}
		}
		out.printLine(answer);
    }
}
