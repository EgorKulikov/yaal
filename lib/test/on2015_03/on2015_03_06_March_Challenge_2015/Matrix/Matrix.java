package on2015_03.on2015_03_06_March_Challenge_2015.Matrix;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Matrix {
	int[] size;
	int largest;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int queryCount = in.readInt();
		int[] type = new int[queryCount];
		int[] x = new int[queryCount];
		int[] y = new int[queryCount];
		int[] x1 = new int[queryCount];
		int[] y1 = new int[queryCount];
		for (int i = 0; i < queryCount; i++) {
			type[i] = in.readInt();
			if (type[i] != 4) {
				x[i] = in.readInt();
				y[i] = in.readInt();
				if (type[i] == 3) {
					x1[i] = in.readInt();
					y1[i] = in.readInt();
				}
			}
		}
		MiscUtils.decreaseByOne(x, y, x1, y1);
		boolean[][] toRight = new boolean[rowCount][columnCount];
		boolean[][] toDown = new boolean[rowCount][columnCount];
		for (int i = 0; i < queryCount; i++) {
			if (type[i] == 2) {
				if (toDown[x[i]][y[i]]) {
					type[i] = -1;
				} else {
					toDown[x[i]][y[i]] = true;
				}
			} else if (type[i] == 1) {
				if (toRight[x[i]][y[i]]) {
					type[i] = -1;
				} else {
					toRight[x[i]][y[i]] = true;
				}
			}
		}
		IndependentSetSystem setSystem = new ListIndependentSetSystem(rowCount * columnCount);
		long answer = 0;
		size = ArrayUtils.createArray(rowCount * columnCount, 1);
		largest = 1;
		setSystem.setListener((joined, root) -> {
			size[root] += size[joined];
			largest = Math.max(largest, size[root]);
		});
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount - 1; j++) {
				if (!toRight[i][j]) {
					setSystem.join(i * columnCount + j, i * columnCount + j + 1);
				}
			}
		}
		for (int i = 0; i < rowCount - 1; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (!toDown[i][j]) {
					setSystem.join(i * columnCount + j, (i + 1) * columnCount + j);
				}
			}
		}
		for (int i = queryCount - 1; i >= 0; i--) {
			if (type[i] == 1) {
				setSystem.join(x[i] * columnCount + y[i], x[i] * columnCount + y[i] + 1);
			} else if (type[i] == 2) {
				setSystem.join(x[i] * columnCount + y[i], (x[i] + 1) * columnCount + y[i]);
			} else if (type[i] == 3) {
				answer += setSystem.get(x[i] * columnCount + y[i]) == setSystem.get(x1[i] * columnCount + y1[i]) ? 1 : 0;
			} else if (type[i] == 4) {
				answer += largest;
			}
		}
		out.printLine(answer);
	}
}
