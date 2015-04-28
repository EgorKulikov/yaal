package on2015_04.on2015_04_17_Single_Round_Match_656.RandomPancakeStack;



import net.egork.misc.ArrayUtils;

public class RandomPancakeStack {
    double[][] result;
    int[] value;

    public double expectedDeliciousness(int[] d) {
        value = d;
        result = new double[d.length][d.length];
        ArrayUtils.fill(result, -1);
        double answer = 0;
        for (int i = 0; i < d.length; i++) {
            answer += go(i, d.length - 1);
        }
		return answer / d.length;
    }

    private double go(int at, int remaining) {
        if (result[at][remaining] != -1) {
            return result[at][remaining];
        }
        if (remaining == 0) {
            return result[at][remaining] = value[at];
        }
        double answer = 0;
        for (int i = 0; i < at; i++) {
            answer += go(i, remaining - 1);
        }
        answer /= remaining;
        return result[at][remaining] = answer + value[at];
    }
}
