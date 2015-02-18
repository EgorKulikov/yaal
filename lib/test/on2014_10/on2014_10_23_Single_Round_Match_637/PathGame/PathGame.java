package on2014_10.on2014_10_23_Single_Round_Match_637.PathGame;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.misc.ArrayUtils;

public class PathGame {
	int[][][] answer;

    public String judge(String[] board) {
		answer = new int[3][3][board[0].length() + 1];
		ArrayUtils.fill(answer, -1);
		int leftEdge = 0;
		int at = -1;
		int result = 0;
		for (int i = 0; i < board[0].length(); i++) {
			if (board[0].charAt(i) == '#' || board[1].charAt(i) == '#') {
				int length = i - at - 1;
				int rightEdge = board[0].charAt(i) == '#' ? 1 : 2;
				result ^= go(leftEdge, rightEdge, length);
				at = i;
				leftEdge = rightEdge;
			}
		}
		result ^= go(leftEdge, 0, board[0].length() - at - 1);
		return result == 0 ? "Sothe" : "Snuke";
    }

	private int go(int leftEdge, int rightEdge, int length) {
		if (answer[leftEdge][rightEdge][length] != -1) {
			return answer[leftEdge][rightEdge][length];
		}
		IntSet present = new IntHashSet();
		for (int i = 0; i < length; i++) {
			for (int j = 1; j <= 2; j++) {
				if (i == 0 && j != leftEdge && leftEdge != 0 || i == length - 1 && j != rightEdge && rightEdge != 0) {
					continue;
				}
				present.add(go(leftEdge, j, i) ^ go(j, rightEdge, length - i - 1));
			}
		}
		for (int i = 0; ; i++) {
			if (!present.contains(i)) {
				return answer[leftEdge][rightEdge][length] = i;
			}
		}
	}
}
