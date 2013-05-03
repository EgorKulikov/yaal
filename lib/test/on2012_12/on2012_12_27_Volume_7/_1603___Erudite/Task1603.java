package on2012_12.on2012_12_27_Volume_7._1603___Erudite;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1603 {
    private char[][] map;
    private char[] word;
    private boolean[][][] visited;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        map = IOUtils.readTable(in, 4, 4);
        int count = in.readInt();
        visited = new boolean[4][4][1 << 16];
        for (int i = 0; i < count; i++) {
            word = in.readString().toCharArray();
            ArrayUtils.fill(visited, false);
            boolean found = false;
            for (int j = 0; j < 4 && !found; j++) {
                for (int k = 0; k < 4 && !found; k++)
                    found = go(j, k, 0);
            }
            out.printLine(new String(word) + ":", found ? "YES" : "NO");
        }
    }

    private boolean go(int row, int column, int mask) {
        int key = row * 4 + column;
        int position = Integer.bitCount(mask);
        if (row < 0 || row >= 4 || column < 0 || column >= 4 || (mask >> key & 1) == 1 || word[position] != map[row][column])
            return false;
        mask += 1 << key;
        visited[row][column][mask] = true;
        if (position == word.length - 1)
            return true;
        for (int i = 0; i < 4; i++) {
            if (go(row + MiscUtils.DX4[i], column + MiscUtils.DY4[i], mask))
                return true;
        }
        return false;
    }
}
