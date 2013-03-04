package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int module = in.readInt();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			setSystem.join(a, b);
		}
		int[] qty = new int[count];
		for (int i = 0; i < count; i++)
			qty[setSystem.get(i)]++;
		Arrays.sort(qty);
		int index = 0;
		while (qty[index] == 0)
			index++;
		qty = Arrays.copyOfRange(qty, index, count);
		if (qty.length == 1) {
			out.printLine(1 % module);
			return;
		}
		long result = 1;
		for (int q : qty) {
			result *= q;
			result %= module;
		}
		long sum = ArrayUtils.sumArray(qty);
		sum %= module;
		result = result * IntegerUtils.power(sum, qty.length - 2, module) % module;
		out.printLine(result);
	}
}
