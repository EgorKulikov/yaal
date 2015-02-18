package on2015_02.on2015_02_18_Single_Round_Match_650.TaroFillingAStringDiv1;



import net.egork.misc.ArrayUtils;

public class TaroFillingAStringDiv1 {
	private static final long MOD = (long) (1e9 + 7);

	public int getNumber(int N, int[] position, String value) {
		int[] order = ArrayUtils.order(position);
		long answer = 1;
		for (int i = 1; i < order.length; i++) {
			int inside = position[order[i]] - position[order[i - 1]] - 1;
			if ((inside % 2 == 0) == (value.charAt(order[i - 1]) == value.charAt(order[i]))) {
				answer *= inside + 1;
				answer %= MOD;
			}
		}
		return (int)answer;
    }
}
