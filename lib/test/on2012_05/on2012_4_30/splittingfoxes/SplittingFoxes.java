package on2012_05.on2012_4_30.splittingfoxes;



import net.egork.numbers.Matrix;

import java.util.HashMap;
import java.util.Map;

public class SplittingFoxes {
	private static final long MOD = (long) (1e9 + 7);

	public int sum(long n, int S_, int L_, int R_) {
		long S = S_;
		long L = L_;
		long R = R_;
		Map<String, Integer> i = new HashMap<String, Integer>();
		i.put("a", 0);
		i.put("b", 1);
		i.put("c", 2);
		i.put("d", 3);
		i.put("ax", 4);
		i.put("ay", 5);
		i.put("bx", 6);
		i.put("by", 7);
		i.put("cx", 8);
		i.put("cy", 9);
		i.put("dx", 10);
		i.put("dy", 11);
		i.put("q", 12);
		Matrix matrix = new Matrix(13, 13);
		matrix.data[i.get("a")][i.get("b")] = pretty(L);
		matrix.data[i.get("a")][i.get("d")] = pretty(R);
		matrix.data[i.get("a")][i.get("a")] = pretty(S);
		matrix.data[i.get("a")][i.get("ay")] = pretty(S);
		matrix.data[i.get("b")][i.get("c")] = pretty(L);
		matrix.data[i.get("b")][i.get("a")] = pretty(R);
		matrix.data[i.get("b")][i.get("b")] = pretty(S);
		matrix.data[i.get("b")][i.get("bx")] = pretty(S);
		matrix.data[i.get("c")][i.get("d")] = pretty(L);
		matrix.data[i.get("c")][i.get("b")] = pretty(R);
		matrix.data[i.get("c")][i.get("c")] = pretty(S);
		matrix.data[i.get("c")][i.get("cy")] = pretty(-S);
		matrix.data[i.get("d")][i.get("a")] = pretty(L);
		matrix.data[i.get("d")][i.get("c")] = pretty(R);
		matrix.data[i.get("d")][i.get("d")] = pretty(S);
		matrix.data[i.get("d")][i.get("dx")] = pretty(-S);
		matrix.data[i.get("ax")][i.get("q")] = pretty(S);
		matrix.data[i.get("ax")][i.get("ax")] = pretty(S);
		matrix.data[i.get("ax")][i.get("bx")] = pretty(L);
		matrix.data[i.get("ax")][i.get("dx")] = pretty(R);
		matrix.data[i.get("ay")][i.get("ay")] = pretty(S);
		matrix.data[i.get("ay")][i.get("by")] = pretty(L);
		matrix.data[i.get("ay")][i.get("dy")] = pretty(R);
		matrix.data[i.get("bx")][i.get("bx")] = pretty(S);
		matrix.data[i.get("bx")][i.get("cx")] = pretty(L);
		matrix.data[i.get("bx")][i.get("ax")] = pretty(R);
		matrix.data[i.get("by")][i.get("q")] = pretty(S);
		matrix.data[i.get("by")][i.get("by")] = pretty(S);
		matrix.data[i.get("by")][i.get("cy")] = pretty(L);
		matrix.data[i.get("by")][i.get("ay")] = pretty(R);
		matrix.data[i.get("cx")][i.get("q")] = pretty(-S);
		matrix.data[i.get("cx")][i.get("cx")] = pretty(S);
		matrix.data[i.get("cx")][i.get("dx")] = pretty(L);
		matrix.data[i.get("cx")][i.get("bx")] = pretty(R);
		matrix.data[i.get("cy")][i.get("cy")] = pretty(S);
		matrix.data[i.get("cy")][i.get("dy")] = pretty(L);
		matrix.data[i.get("cy")][i.get("by")] = pretty(R);
		matrix.data[i.get("dx")][i.get("dx")] = pretty(S);
		matrix.data[i.get("dx")][i.get("ax")] = pretty(L);
		matrix.data[i.get("dx")][i.get("cx")] = pretty(R);
		matrix.data[i.get("dy")][i.get("q")] = pretty(-S);
		matrix.data[i.get("dy")][i.get("dy")] = pretty(S);
		matrix.data[i.get("dy")][i.get("ay")] = pretty(L);
		matrix.data[i.get("dy")][i.get("cy")] = pretty(R);
		matrix.data[i.get("q")][i.get("q")] = pretty(S + L + R);
		Matrix.mod = MOD;
		matrix = matrix.power(n);
		return (int) matrix.data[i.get("a")][i.get("q")];
	}

	private long pretty(long value) {
		value %= MOD;
		value += MOD;
		value %= MOD;
		return value;
	}

}

