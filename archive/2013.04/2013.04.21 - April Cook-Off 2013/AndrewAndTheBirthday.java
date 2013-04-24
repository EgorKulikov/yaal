package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AndrewAndTheBirthday {
	int[] types;
	double[][][][] result = new double[51][50][51][51];
	int typeCount, count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		typeCount = in.readInt();
		types = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(types);
		ArrayUtils.fill(result, -1);
		out.printLine(calculate(0, 0, 0, 0));
    }

	private double calculate(int step, int type, int same, int max) {
		max = Math.max(max, same);
		if (result[step][type][same][max] != -1)
			return result[step][type][same][max];
		if (step == count)
			return result[step][type][same][max] = max;
		if (types[step] != -1)
			return result[step][type][same][max] = calculate(step + 1, types[step], type == types[step] ? same + 1 : 1, max);
		result[step][type][same][max] = 0;
		for (int i = 0; i < typeCount; i++)
			result[step][type][same][max] += calculate(step + 1, i, type == i ? same + 1 : 1, max);
		return result[step][type][same][max] /= typeCount;
	}
}
