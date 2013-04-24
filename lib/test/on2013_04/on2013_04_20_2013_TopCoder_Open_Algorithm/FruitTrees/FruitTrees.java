package on2013_04.on2013_04_20_2013_TopCoder_Open_Algorithm.FruitTrees;



import net.egork.numbers.IntegerUtils;

public class FruitTrees {
    public int maxDist(int apple, int kiwi, int grape) {
		int a = IntegerUtils.gcd(apple, kiwi);
		int b = IntegerUtils.gcd(apple, grape);
		int c = IntegerUtils.gcd(kiwi, grape);
		int answer = 0;
		for (int i = 0; i < kiwi; i++) {
			for (int j = 0; j < grape; j++) {
				answer = Math.max(answer, Math.min(distance(0, i % a, a), Math.min(distance(0, j % b, b), distance(i % c, j % c, c))));
			}
		}
		return answer;
    }

	private int distance(int first, int second, int mod) {
		return Math.min(Math.abs(first - second), mod - Math.abs(first - second));
	}
}
