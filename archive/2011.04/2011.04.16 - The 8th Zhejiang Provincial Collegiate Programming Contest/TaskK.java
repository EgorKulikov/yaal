package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.numbers.BooleanMatrix;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.old.io.stringinputreader.StringInputReader;

import java.io.PrintWriter;

public class TaskK implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		BooleanMatrix matrix = new BooleanMatrix(rowCount * columnCount, rowCount * columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int index = i * columnCount + j;
				String description = in.readString().replace('(', ' ').replace(')', ' ').replace(',', ' ');
				InputReader input = new StringInputReader(description);
				for (int k = 0; k < 4; k++) {
					int row = input.readInt() - 1;
					int column = input.readInt() - 1;
					if (index != matrix.data.length - 1)
						matrix.data[index][row * columnCount + column] = true;
				}
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int query = in.readInt();
			BooleanMatrix power = matrix.power(query);
			boolean can = power.data[0][rowCount * columnCount - 1];
			boolean must = can;
			for (int j = 0; j < rowCount * columnCount - 1; j++)
				must &= !power.data[0][j];
			if (must)
				out.println("True");
			else if (can)
				out.println("Maybe");
			else
				out.println("False");
		}
		out.println();
	}
}

