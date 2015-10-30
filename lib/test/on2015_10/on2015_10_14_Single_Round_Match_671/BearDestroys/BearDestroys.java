package on2015_10.on2015_10_14_Single_Round_Match_671.BearDestroys;



import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;

public class BearDestroys {
    long[][][][] ways;
    int mod;
    int w;
    int h;

    public int sumUp(int W, int H, int MOD) {
        w = W;
        h = H;
        mod = MOD;
        ways = new long[H][W][][];
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                ways[j][i] = new long[1 << (Math.min(i + 1, H - j))][1 << Math.min(j + 1, W - i - 1)];
            }
        }
        long answer = 0;
        long total = IntegerUtils.power(2, W * H, MOD);
        long rev = IntegerUtils.reverse(2, MOD);
        ways[0][0][0][0] = 1;
        for (int t = 0; t <= W + H - 2; t++) {
            for (int i = 0; i < H; i++) {
                int row = i;
                int column = t - i;
                int j = column;
                if (column < 0 || column >= W) {
                    continue;
                }
                for (int k = 0; k < ways[i][j].length; k++) {
                    for (int l = 0; l < ways[i][j][k].length; l++) {
                        if (ways[i][j][k][l] == 0) {
                            continue;
                        }
                        if ((k & 1) == 1 || row == H - 1 && l >= (ways[i][j][k].length >> 1)) {
                            add(row, column, k, l, row + 1, column - 1, k >> 1, l, 2);
                        } else if (column == W - 1 || l >= (ways[i][j][k].length >> 1)) {
                            answer += ways[i][j][k][l] * total;
                            answer %= MOD;
                            add(row, column, k, l, row + 1, column - 1, k >> 1, l + ways[i][j][k].length, 2);
                        } else if (row == H - 1) {
                            answer += ways[i][j][k][l] * total;
                            answer %= MOD;
                            add(row, column, k, l, row + 1, column - 1, k >> 1, l + (ways[i][j][k].length >> 1), 2);
                        } else {
                            answer += ways[i][j][k][l] * total;
                            answer %= MOD;
                            add(row, column, k, l, row + 1, column - 1, k >> 1, l + ways[i][j][k].length, 1);
                            add(row, column, k, l, row + 1, column - 1, k >> 1, l + (ways[i][j][k].length >> 1), 1);
                        }
                    }
                }
                total *= rev;
                total %= MOD;
            }
        }
		return (int)answer;
    }

    private void add(int row, int column, int cMask, int nMask, int nRow, int nColumn, int nCMask, int nNMask, int mult) {
        if (row == h - 1 && column == w - 1) {
            return;
        }
        if (!MiscUtils.isValidCell(nRow, nColumn, h, w)) {
            if (nCMask != 0) {
                throw new RuntimeException();
            }
            int sum = row + column + 1;
            if (sum < w - 1) {
                nRow = 0;
                nColumn = sum;
            } else {
                nColumn = w - 1;
                nRow = sum - nColumn;
            }
            nCMask = nNMask;
            nNMask = 0;
        }
        ways[nRow][nColumn][nCMask][nNMask] += mult * ways[row][column][cMask][nMask];
        ways[nRow][nColumn][nCMask][nNMask] %= mod;
    }
}
