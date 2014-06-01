package on2014_06.on2014_06_01_Yandex_Algorithm_2014_Round_1_Test_Run.A____________________;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private int[] numbers;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		numbers = IOUtils.readIntArray(in, count);
		out.printLine(solve(0, 0));
    }

	private int solve(int step, int current) {
		if (step == numbers.length)
			return current;
		current += numbers[step];
		IntList list = new IntArrayList();
		while (current != 0) {
			list.add(current % 10);
			current /= 10;
		}
		int result = 0;
		list.inPlaceSort();
		do {
			int number = 0;
			for (int i = 0; i < list.size(); i++) {
				number *= 10;
				number += list.get(i);
			}
			result = Math.max(result, solve(step + 1, number));
		} while (list.nextPermutation());
		return result;
	}
}
