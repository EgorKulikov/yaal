package on2014_11.on2014_11_28_Single_Round_Match_639.BoardFolding;



import net.egork.misc.ArrayUtils;

public class BoardFolding {
    public int howMany(int N, int M, String[] compressedPaper) {
        int[][] paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                paper[i][j] = (toNumber(compressedPaper[i].charAt(j / 6)) >> (j % 6)) & 1;
            }
        }
        int waysRows = count(paper);
        paper = ArrayUtils.transpose(paper);
        int waysColumns = count(paper);
		return waysRows * waysColumns;
    }

    int[] rows;

    private int count(int[][] paper) {
        rows = new int[paper.length];
        int id = 0;
        for (int i = 0; i < paper.length; i++) {
            rows[i] = -1;
            for (int j = 0; j < i; j++) {
                boolean good = true;
                for (int k = 0; k < paper[i].length; k++) {
                    if (paper[i][k] != paper[j][k]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    rows[i] = rows[j];
                    break;
                }
            }
            if (rows[i] == -1) {
                rows[i] = id++;
            }
        }
        boolean[][] can = new boolean[paper.length][paper.length];
        can[0][paper.length - 1] = true;
        for (int i = 0; i < paper.length; i++) {
            for (int j = paper.length - 1; j >= i; j--) {
                if (can[i][j]) {
                    continue;
                }
                for (int k = i - 1; k >= 0 && j - k + 1 <= 2 * (j - i + 1); k--) {
                    if (rows[k] != rows[i + (i - k - 1)]) {
                        break;
                    }
                    if (can[k][j]) {
                        can[i][j] = true;
                        break;
                    }
                }
                if (can[i][j]) {
                    continue;
                }
                for (int k = j + 1; k < paper.length && k - i + 1 <= 2 * (j - i + 1); k++) {
                    if (rows[k] != rows[j - (k - j - 1)]) {
                        break;
                    }
                    if (can[i][k]) {
                        can[i][j] = true;
                        break;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < paper.length; i++) {
            for (int j = paper.length - 1; j >= i; j--) {
                if (can[i][j]) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private int toNumber(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        }
        if (Character.isLetter(c)) {
            if (Character.isLowerCase(c)) {
                return c - 'a' + 10;
            }
            return c - 'A' + 36;
        }
        return c == '#' ? 62 : 63;
    }
}
