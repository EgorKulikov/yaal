package on2013_07.on2013_07_19_Single_Round_Match_585.TrafficCongestion;



import net.egork.numbers.IntegerUtils;

public class TrafficCongestion {
	static final long MOD = (long) (1e9 + 7);

    public int theMinCars(int treeHeight) {
		long answer = 0;
		while (treeHeight >= 2) {
			answer += IntegerUtils.power(2, treeHeight - 1, MOD);
			treeHeight -= 2;
		}
		answer++;
		return (int) (answer % MOD);
    }
}
