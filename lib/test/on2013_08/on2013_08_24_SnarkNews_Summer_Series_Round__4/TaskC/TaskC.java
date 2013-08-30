package on2013_08.on2013_08_24_SnarkNews_Summer_Series_Round__4.TaskC;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.misc.SimpleMapVisitor;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	int pavilion;
	int info;

	char[][] pattern = {
		"......".toCharArray(),
		".[][].".toCharArray(),
		"......".toCharArray()
	};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		final char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		SimpleMapVisitor mapVisitor = new SimpleMapVisitor(rowCount, columnCount, MiscUtils.DX8, MiscUtils.DY8) {
			@Override
			protected void process(int row, int column, int fromRow, int fromColumn) {
				if (map[row][column] == '.' || map[row][column] == '*')
					return;
				if (fromRow == -1)
					pavilion++;
				add(row, column);
			}
		};
		if (rowCount == 1) {
			out.printLine(0, 0);
			return;
		}
		for (int i = 0; i < columnCount; i++)
			mapVisitor.process(rowCount - 2, i);
		for (int i = -1; i <= rowCount - pattern.length; i++) {
			for (int j = -1; j <= columnCount - pattern[0].length + 1; j++) {
				boolean match = true;
				for (int k = 0; k < pattern.length; k++) {
					for (int l = 0; l < pattern[k].length; l++) {
						if (MiscUtils.isValidCell(i + k, j + l, rowCount, columnCount) && map[i + k][j + l] != pattern[k][l])
							match = false;
					}
				}
				if (match)
					info++;
			}
		}
		out.printLine(pavilion, info);
    }
}
