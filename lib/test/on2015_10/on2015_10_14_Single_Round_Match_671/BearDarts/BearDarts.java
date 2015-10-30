package on2015_10.on2015_10_14_Single_Round_Match_671.BearDarts;



import net.egork.collections.map.Counter;
import net.egork.numbers.Rational;

public class BearDarts {
    public long count(int[] w) {
        Counter<Rational> counter = new Counter<>();
        long answer = 0;
        for (int i = w.length - 2; i > 0; i--) {
            for (int j = i + 2; j < w.length; j++) {
                counter.add(new Rational(w[j], w[i + 1]));
            }
            for (int j = i - 1; j >= 0; j--) {
                answer += counter.get(new Rational(w[j], w[i]));
            }
        }
		return answer;
    }
}
