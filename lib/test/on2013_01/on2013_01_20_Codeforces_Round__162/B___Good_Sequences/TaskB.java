package on2013_01.on2013_01_20_Codeforces_Round__162.B___Good_Sequences;



import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		int[] divisorTable = IntegerUtils.generateDivisorTable((int) (1e5 + 1));
		int[] answer = new int[(int) (1e5 + 1)];
		for (int i : numbers) {
			int current = 0;
			int j = i;
			while (j != 1) {
				int divisor = divisorTable[j];
				current = Math.max(current, answer[divisor]);
				do {
					j /= divisor;
				} while (j % divisor == 0);
			}
			j = i;
			while (j != 1) {
				int divisor = divisorTable[j];
				answer[divisor] = current + 1;
				do {
					j /= divisor;
				} while (j % divisor == 0);
			}
		}
		out.printLine(Math.max(1, new IntArray(answer).max()));
	}
}
