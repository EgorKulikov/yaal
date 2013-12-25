package on2012_12.on2012_12_27_Volume_3._1298___Knight;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1298 {
    int[] row;
    int[] column;
    boolean[][] visited;
    private int count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        count = in.readInt();
        row = new int[count * count];
        column = new int[count * count];
        visited = new boolean[count][count];
        if (go(0, 0, 0)) {
            for (int i = 0; i < row.length; i++)
                out.printLine((char)('a' + row[i]) + "" + (1 + column[i]));
        } else {
            out.printLine("IMPOSSIBLE");
        }
	}

    private boolean go(int cRow, int cColumn, int step) {
        if (step == row.length)
            return true;
        if (cRow < 0 || cRow >= count || cColumn < 0 || cColumn >= count || visited[cRow][cColumn])
            return false;
        visited[cRow][cColumn] = true;
        row[step] = cRow;
        column[step] = cColumn;
        for (int i = 0; i < 8; i++) {
            if (go(cRow + MiscUtils.DX_KNIGHT[i], cColumn + MiscUtils.DY_KNIGHT[i], step + 1))
                return true;
        }
        visited[cRow][cColumn] = false;
        return false;
    }
}
