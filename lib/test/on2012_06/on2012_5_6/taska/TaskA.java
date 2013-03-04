package on2012_06.on2012_5_6.taska;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] != '#')
					continue;
				count++;
				table[i][j] = '.';
				int componentCount = componentCount(table);
				if (componentCount == 0) {
					out.printLine(-1);
					return;
				}
				if (componentCount > 1) {
					out.printLine(1);
					return;
				}
				table[i][j] = '#';
			}
		}
		if (count <= 2)
			out.printLine(-1);
		else
			out.printLine(2);
	}

	private int componentCount(char[][] table) {
		boolean[][] processed = new boolean[table.length][table[0].length];
		int[] queueRow = new int[table.length * table[0].length];
		int[] queueColumn = new int[table.length * table[0].length];
		int result = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (processed[i][j] || table[i][j] != '#')
					continue;
				result++;
				int size = 1;
				queueRow[0] = i;
				queueColumn[0] = j;
				processed[i][j] = true;
				for (int k = 0; k < size; k++) {
					int row = queueRow[k];
					int column = queueColumn[k];
					for (int l = 0; l < 4; l++) {
						int newRow = row + MiscUtils.DX4[l];
						int newColumn = column + MiscUtils.DY4[l];
						if (newRow >= 0 && newRow < table.length && newColumn >= 0 && newColumn < table[0].length && !processed[newRow][newColumn] && table[newRow][newColumn] == '#') {
							processed[newRow][newColumn] = true;
							queueRow[size] = newRow;
							queueColumn[size++] = newColumn;
						}
					}
				}
			}
		}
		return result;
	}
}
