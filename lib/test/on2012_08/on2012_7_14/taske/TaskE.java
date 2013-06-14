package on2012_08.on2012_7_14.taske;



import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int base = in.readInt();
		int lucky = in.readInt();
		int count = in.readInt();
		int[] digits = IOUtils.readIntArray(in, count);
		long zeroStrings = 0;
		int zeroes = 0;
		for (int i = 0; i < count; i++) {
			if (digits[i] == 0)
				zeroes++;
			else
				zeroes = 0;
			zeroStrings += zeroes;
		}
		Counter<Integer> perSum = new Counter<Integer>();
		long answer = 0;
		int sum = 0;
		perSum.add(0);
		for (int i : digits) {
			sum += i;
			if (sum >= base - 1)
				sum -= base - 1;
			int required = sum - lucky;
			if (required < 0)
				required += base - 1;
			answer += perSum.get(required);
			perSum.add(sum);
		}
		if (lucky == 0) {
			out.printLine(zeroStrings);
			return;
		}
		if (lucky == base - 1) {
			answer -= zeroStrings;
			out.printLine(answer);
			return;
		}
		out.printLine(answer);
	}
}
