package on2013_01.on2013_01_21_Single_Round_Match_567.Mountains;



import net.egork.misc.ArrayUtils;

public class Mountains {
    private static final long MOD = (long) (1e9 + 9);
    long[][] result;
    int[] heights;
    int[] from;
    int[] to;
    int[] position;
    boolean[] full;
    boolean[] empty;
    int width;
    int count;

	public int countPlacements(int[] heights, String[] visibility) {
        count = heights.length;
        width = visibility[0].length();
        this.heights = heights;
        from = new int[count];
        to = new int[count];
        for (int i = 0; i < count; i++) {
            from[i] = to[i] = -1;
            for (int j = 0; j < width; j++) {
                if (visibility[i].charAt(j) == 'X') {
                    if (from[i] == -1)
                        from[i] = j;
                    to[i] = j;
                }
            }
        }
        full = new boolean[count];
        empty = new boolean[count];
        for (int i = 0; i < count; i++) {
            full[i] = from[i] == 0 && to[i] == width - 1;
            empty[i] = from[i] == -1;
        }
        result = new long[count][width];
        ArrayUtils.fill(result, -1);
        position = new int[count];
        long answer = 0;
        for (int i = 0; i < width; i++) {
            int curFrom = Math.max(i - heights[count - 1] + 1, 0);
            int curTo = Math.min(width - 1, i + heights[count - 1] - 1);
            if (curFrom == from[count - 1] && curTo == to[count - 1])
                answer += go(count - 1, i);
        }
        answer %= MOD;
        return (int) answer;
	}

    private long go(int index, int column) {
        if (full[index] && result[index][column] != -1)
            return result[index][column];
        position[index] = column;
        long result = 0;
        long multiplier = 1;
        for (int i = index - 1; ; i--) {
            if (i == -1)
                return store(index, column, multiplier);
            if (empty[i]) {
                position[i] = -1;
                int current = 0;
                for (int j = 0; j < width; j++) {
                    for (int k = index; k < count; k++) {
                        if (position[k] != -1 && heights[k] - heights[i] >= Math.abs(position[k] - j)) {
                            current++;
                            break;
                        }
                    }
                }
                multiplier *= current;
                multiplier %= MOD;
            } else {
                for (int j = 0; j < width; j++) {
                    int curFrom = Math.max(0, j - heights[i] + 1);
                    int curTo = Math.min(width - 1, j + heights[i] - 1);
                    for (int k = index; k < count; k++) {
                        if (position[k] == -1)
                            continue;
                        if (Math.abs(position[k] - j) < heights[i] - heights[k])
                            continue;
                        if (Math.abs(position[k] - j) <= heights[k] - heights[i]) {
                            curFrom = -1;
                            curTo = -1;
                            break;
                        }
                        if (j < position[k]) {
                            curTo = Math.min(curTo, j + (position[k] - j + (heights[i] - heights[k]) / 2));
                            while (true) {
                                int h1 = heights[i] - (curTo - j);
                                int h2 = heights[k] - (position[k] - curTo);
                                if (h1 > h2)
                                    break;
                                curTo--;
                            }
                        } else {
                            curFrom = Math.max(curFrom, j - (j - position[k] + (heights[i] - heights[k]) / 2));
                            while (true) {
                                int h1 = heights[i] - (j - curFrom);
                                int h2 = heights[k] - (curFrom - position[k]);
                                if (h1 > h2)
                                    break;
                                curFrom++;
                            }
                        }
                    }
                    if (curFrom == from[i] && curTo == to[i])
                        result += go(i, j);
                }
                result %= MOD;
                result *= multiplier;
                result %= MOD;
                return store(index, column, result);
            }
        }
    }

    private long store(int index, int column, long value) {
        if (full[index])
            result[index][column] = value;
        return value;
    }
}
