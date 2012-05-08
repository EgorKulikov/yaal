package on2012_4_8.taskc;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class TaskCChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		int testCount = input.readInt();
		for (int i = 0; i < testCount; i++) {
			int count = input.readInt();
			long[] numbers = IOUtils.readLongArray(input, count);
			Set<Long> present = new HashSet<Long>(Array.wrap(numbers));
			actual.readLine();
			String firstLine = actual.readLine();
			String secondLine = actual.readLine();
			long[] first = check(firstLine, present);
			long[] second = check(secondLine, present);
			if (first == null || second == null)
				return Verdict.WA;
			if (Arrays.equals(first, second))
				return Verdict.WA;
			if (sumArray(first) != sumArray(second))
				return Verdict.WA;
		}
		return Verdict.OK;
	}

	private long[] check(String line, Set<Long> present) {
		String[] splitted = line.split(" ");
		long[] result = new long[splitted.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Long.parseLong(splitted[i]);
			if (!present.contains(result[i]))
				return null;
		}
		Arrays.sort(result);
		for (int i = 1; i < result.length; i++) {
			if (result[i] == result[i - 1])
				return null;
		}
		return result;
	}

	private long sumArray(long[] array) {
		long result = 0;
		for (long l : array)
			result += l;
		return result;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		Random rnd = new Random(42);
		out.printLine(1);
		for (int i = 0; i < 1; i++) {
			out.printLine(500);
			for (int j = 0; j < 500; j++)
				out.printLine(1 + (Math.abs(rnd.nextLong()) % ((long)1e12)));
		}
		out.flush();
		return Collections.singleton(new Test(sw.toString(), "", 0));
	}
}
