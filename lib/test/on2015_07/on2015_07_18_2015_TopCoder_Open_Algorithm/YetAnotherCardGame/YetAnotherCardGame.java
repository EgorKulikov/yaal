package on2015_07.on2015_07_18_2015_TopCoder_Open_Algorithm.YetAnotherCardGame;



import net.egork.misc.ArrayUtils;

public class YetAnotherCardGame {
    int[][] answer;

    public int maxCards(int[] petr, int[] snuke) {
        answer = new int[petr.length + snuke.length + 1][101];
        ArrayUtils.fill(answer, -1);
		return go(0, 0, petr, snuke);
    }

    private int go(int moves, int last, int[] current, int[] next) {
        if (answer[moves][last] != -1) {
            return answer[moves][last];
        }
        if (current.length == (moves >> 1)) {
            return answer[moves][last] = 0;
        }
        answer[moves][last] = go(moves + 1, last, next, current);
        for (int i : current) {
            if (i > last) {
                answer[moves][last] = Math.max(answer[moves][last], go(moves + 1, i, next, current) + 1);
            }
        }
        return answer[moves][last];
    }
}
