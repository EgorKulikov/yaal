package net.egork;

public class BearFills {
	public long countSets(int N, long W, long H) {
		long answer = 0;
		for (int i = N - 1; i >= 0; i--) {
			long side = 1L << i;
			if (side >= W && side >= H) {
				answer += side;
			} else if (side < W && side < H || 2 * side <= W || 2 * side <= H) {
				break;
			} else if (W > H) {
				W -= side;
			} else {
				H -= side;
			}
		}
		return answer;
	}
}
