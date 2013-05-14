package on2011_11.on2011_10_10.morecaterpillars;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MoreCaterpillars {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		char[][] table = new char[size][size];
		ArrayUtils.fill(table, '.');
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			int length = in.readInt();
			int column = in.readInt() - 1;
			int row = in.readInt() - 1;
			while (length != 0) {
				if (type == 'V') {
					table[row][column] = '#';
					table[row][column + 1] = '#';
					table[row][column + 2] = '#';
					table[row + 1][column + 2] = '#';
					row += 2;
					column += 2;
					type = 'v';
				} else if (type == 'v') {
					table[row][column] = '#';
					table[row][column - 1] = '#';
					table[row][column - 2] = '#';
					table[row + 1][column - 2] = '#';
					row += 2;
					column -= 2;
					type = 'V';
				} else if (type == 'H') {
					table[row][column] = '#';
					table[row + 1][column] = '#';
					table[row + 2][column] = '#';
					table[row + 2][column + 1] = '#';
					row += 2;
					column += 2;
					type = 'h';
				} else {
					table[row][column] = '#';
					table[row - 1][column] = '#';
					table[row - 2][column] = '#';
					table[row - 2][column + 1] = '#';
					row -= 2;
					column += 2;
					type = 'H';
				}
				length--;
			}
		}
		IOUtils.printTable(out, table);
	}
}
