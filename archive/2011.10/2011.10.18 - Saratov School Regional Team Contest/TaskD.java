import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] field = IOUtils.readIntTable(in, rowCount, columnCount);
		int[] parts = IOUtils.readIntArray(in, 3);
		Arrays.sort(parts);
		int answer = 0;
		int[][] subFields = new int[3][4];
		subFields[0][3] = columnCount;
		subFields[1][3] = columnCount;
		subFields[2][3] = columnCount;
		subFields[2][1] = rowCount;
		for (int i = 1; i < rowCount; i++) {
			for (int j = i + 1; j < rowCount; j++) {
				subFields[0][1] = i;
				subFields[1][0] = i;
				subFields[1][1] = j;
				subFields[2][0] = j;
				if (Arrays.equals(parts, count(field, subFields)))
					answer++;
			}
		}
		ArrayUtils.fill(subFields, 0);
		subFields[0][1] = rowCount;
		subFields[1][1] = rowCount;
		subFields[2][1] = rowCount;
		subFields[2][3] = columnCount;
		for (int i = 1; i < columnCount; i++) {
			for (int j = i + 1; j < columnCount; j++) {
				subFields[0][3] = i;
				subFields[1][2] = i;
				subFields[1][3] = j;
				subFields[2][2] = j;
				if (Arrays.equals(parts, count(field, subFields)))
					answer++;
			}
		}
		out.println(answer);
	}

	private int[] count(int[][] field, int[][] subFields) {
		int[] result = new int[3];
		for (int i = 0; i < 3; i++)
			result[i] = count(field, subFields[i]);
		Arrays.sort(result);
		return result;
	}

	private int count(int[][] field, int[] subField) {
		int result = 0;
		for (int i = subField[0]; i < subField[1]; i++) {
			for (int j = subField[2]; j < subField[3]; j++)
				result += field[i][j];
		}
		return result;
	}
}

