package on2012_07.on2012_6_30.bindit;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class BindIt {
    long[][] current;
    private long[][] intersects;
    char[] back;
    boolean[] taken;
    int[] order;
    private int[][] segmentIndex;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (in.isExhausted() || in.peek() == '0')
            throw new UnknownError();
        char[][] map = IOUtils.readTable(in, 7, 7);
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int curIndex = 0;
        char[] s = "sabcdefghijklmnopqrtuvwxyz".toCharArray();
        back = new char[12];
        for (char c : s) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (map[i][j] == c) {
                        back[curIndex] = map[i][j];
                        index[map[i][j]] = curIndex++;
                    }
                }
            }
        }
        segmentIndex = new int[12][12];
        curIndex = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = i + 1; j < 12; j++)
                segmentIndex[i][j] = segmentIndex[j][i] = curIndex++;
        }
        intersects = new long[66][2];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (map[i][j] != '.') {
                    for (int k = 0; k < 7; k++) {
                        for (int l = 0; l < 7; l++) {
                            if (index[map[k][l]] > index[map[i][j]]) {
                                Segment segment1 = new Segment(new Point(i, j), new Point(k, l));
                                for (int i1 = 0; i1 < 7; i1++) {
                                    for (int j1 = 0; j1 < 7; j1++) {
                                        if (map[i1][j1] != '.' && index[map[i1][j1]] > index[map[i][j]] && map[i1][j1] != map[k][l]) {
                                            for (int k1 = 0; k1 < 7; k1++) {
                                                for (int l1 = 0; l1 < 7; l1++) {
                                                    if (index[map[k1][l1]] > index[map[i1][j1]] && map[k1][l1] != map[k][l]) {
                                                        Segment segment2 = new Segment(new Point(i1, j1), new Point(k1, l1));
                                                        if (segment1.intersect(segment2, true) != null || i == k || j == l || j - i == l - k || i + j == k + l ||
                                                                i1 == k1 || j1 == l1 || j1 - i1 == l1 - k1 || i1 + j1 == k1 + l1) {
                                                            int firstIndex = segmentIndex[index[map[i][j]]][index[map[k][l]]];
                                                            int secondIndex = segmentIndex[index[map[i1][j1]]][index[map[k1][l1]]];
                                                            intersects[firstIndex][secondIndex >> 6] |= 1L << (secondIndex & 63);
                                                            intersects[secondIndex][firstIndex >> 6] |= 1L << (firstIndex & 63);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        current = new long[13][2];
        taken = new boolean[12];
        order = new int[12];
        String result = go(1, 0);
        out.printLine(result == null ? "impossible!" : result);
	}

    private String go(int step, int last) {
        if (step == 12) {
            StringBuilder result = new StringBuilder(12);
            for (int i = 0; i < 12; i++)
                result.append(back[order[i]]);
            return result.toString();
        }
        for (int i = 1; i < 12; i++) {
            int sIndex = segmentIndex[last][i];
            if (!taken[i] && (current[step][sIndex >> 6] >> (sIndex & 63) & 1) == 0) {
                current[step + 1][0] = current[step][0] | intersects[sIndex][0];
                current[step + 1][1] = current[step][1] | intersects[sIndex][1];
                taken[i] = true;
                order[step] = i;
                String result = go(step + 1, i);
                if (result != null)
                    return result;
                taken[i] = false;
            }
        }
        return null;
    }
}
