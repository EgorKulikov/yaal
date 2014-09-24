package net.egork;

import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int time = in.readInt();
		long[][] matrix = new long[count][count];
		for (int i = 0; i < count; i++) {
			matrix[i][in.readInt() - 1] = 1;
		}
		long[] converted = Matrix.convert(matrix);
		long[] power = Matrix.power(converted, time, Long.MAX_VALUE, count);
		long[] answer = new long[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				answer[i] += power[j * count + i];
			}
		}
		out.printLine(answer);
    }
}
