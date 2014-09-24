package on2014_07.on2014_07_22_Single_Round_Match_628.CircuitsConstruction;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class CircuitsConstruction {
	int at;

    public int maximizeResistance(String circuit, int[] conductors) {
		at = 0;
		int aCount = getCount(circuit.toCharArray());
		Arrays.sort(conductors);
		return (int) ArrayUtils.sumArray(Arrays.copyOfRange(conductors, conductors.length - aCount, conductors.length));
    }

	private int getCount(char[] expression) {
		if (expression[at] == 'X') {
			at++;
			return 1;
		}
		if (expression[at] == 'A') {
			at++;
			return getCount(expression) + getCount(expression);
		}
		if (expression[at] == 'B') {
			at++;
			return Math.max(getCount(expression), getCount(expression));
		}
		throw new RuntimeException();
	}
}
