package on2012_03.on2012_2_17.foxandgcdlcm;



import net.egork.numbers.IntegerUtils;

public class FoxAndGCDLCM {
	public long get(long G, long L) {
		if (L % G != 0)
			return -1;
		long value = L / G;
		long answer = G + L;
		for (long i = 1; i * i <= value; i++) {
			if (value % i == 0 && IntegerUtils.gcd(i, value / i) == 1)
				answer = Math.min(answer, G * (i + value / i));
		}
		return answer;
	}


}

