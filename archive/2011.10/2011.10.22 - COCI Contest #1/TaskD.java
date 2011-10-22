package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] boys = IOUtils.readIntArray(in, count);
		int[] girls = IOUtils.readIntArray(in, count);
		Arrays.sort(boys);
		Arrays.sort(girls);
		int index = count - 1;
		int result = 0;
		for (int i = 0; i < count; i++) {
			if (index >= 0 && boys[i] < 0 && girls[index] < 0)
				continue;
			while (index >= 0 && boys[i] > 0 && girls[index] > 0)
				index--;
			while (index >= 0 && girls[index] >= -boys[i] && girls[index] * boys[i] < 0)
				index--;
			if (index >= 0 && girls[index] * boys[i] >= 0)
				continue;
			if (index < 0)
				break;
			result++;
			index--;
		}
		out.println(result);
	}
}
